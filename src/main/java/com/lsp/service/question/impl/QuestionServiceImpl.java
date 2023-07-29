package com.lsp.service.question.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.QuestionCollectionMapper;
import com.lsp.mapper.QuestionMapper;
import com.lsp.mapper.QuestionMistakeMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.question.entity.Question;
import com.lsp.pojo.question.entity.QuestionCollection;
import com.lsp.pojo.question.entity.QuestionMistake;
import com.lsp.pojo.question.response.QuestionAnswerResponse;
import com.lsp.pojo.question.response.QuestionCollectionResponse;
import com.lsp.pojo.question.response.QuestionResponse;
import com.lsp.pojo.question.response.subclass.CollectionInfo;
import com.lsp.pojo.question.response.subclass.QuestionInfo;
import com.lsp.pojo.user.entity.User;
import com.lsp.service.question.QuestionService;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: LinShanPeng
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionCollectionMapper questionCollectionMapper;

    @Autowired
    private QuestionMistakeMapper questionMistakeMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 获得问题
     *
     * @param count
     * @return
     */
    @Override
    public QuestionResponse getQuestion(int count) {
        //@param num 长度
        //@param min 最小值（包括）
        //@param max 最大值（包括）
        ArrayList<QuestionInfo> questionList = new ArrayList<>();
        List<Integer> randomNumberList = NumberUtil.getRandomNumberList(count, 1, 11);

        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        for (Integer random : randomNumberList) {
            Question question = questionMapper.selectOne(new QueryWrapper<Question>()
                    .eq("id", random));
            QuestionInfo questionInfo = new QuestionInfo(question);
            //是否收藏
            if (questionCollectionMapper.exists(new QueryWrapper<QuestionCollection>()
                    .eq("question_id", question.getId())
                    .eq("user_phone", user.getUserPhone()))) {
                questionInfo.setCollectState(1);
            } else {
                questionInfo.setCollectState(0);
            }

            questionList.add(questionInfo);
        }
        return new QuestionResponse(questionList, count);
    }

    /**
     * 回答问题
     *
     * @param questionId
     * @param answer
     * @return
     */
    @Override
    public QuestionAnswerResponse answerQuestion(int questionId, String answer) {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        Question question = getQuestionById(questionId);
        String rightAnswer = question.getAnswer();
        String situation = "回答正确！";
        if (!rightAnswer.equals(answer)) {
            situation = "回答错误！";
            if (!questionMistakeMapper.exists(new QueryWrapper<QuestionMistake>()
                    .eq("id", questionId).eq("user_phone", user.getUserPhone()))) {
                QuestionMistake mistake = new QuestionMistake(user.getUserPhone(), questionId);
                questionMistakeMapper.insert(mistake);
                situation = "回答错误！已加入错题集";
            }
        }
        return new QuestionAnswerResponse(situation, rightAnswer, question.getParse());
    }

    /**
     * 收藏题目
     *
     * @param questionId
     * @return
     */
    @Override
    public boolean postCollection(int questionId) {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        if (questionCollectionMapper.exists(new QueryWrapper<QuestionCollection>()
                .eq("user_phone", user.getUserPhone())
                .eq("question_id", questionId))) {
            return false;
        } else {
            QuestionCollection collection = new QuestionCollection(user.getUserPhone(), questionId);
            questionCollectionMapper.insert(collection);
            return true;
        }
    }

    /**
     * 取消收藏
     *
     * @param questionId
     * @return
     */
    @Override
    public boolean deleteCollection(int questionId) {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        if (!questionCollectionMapper.exists(new QueryWrapper<QuestionCollection>()
                .eq("user_phone", user.getUserPhone())
                .eq("question_id", questionId))) {
            return false;
        } else {
            questionCollectionMapper.delete(new QueryWrapper<QuestionCollection>()
                    .eq("question_id", questionId)
                    .eq("user_phone", user.getUserPhone()));
            return true;
        }
    }

    /**
     * 得到收藏列表
     *
     * @return
     */
    @Override
    public QuestionCollectionResponse getCollections() {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        QueryWrapper<QuestionCollection> wrapper = new QueryWrapper<QuestionCollection>()
                .eq("user_phone", user.getUserPhone());

        ArrayList<CollectionInfo> list = new ArrayList<>();
        List<QuestionCollection> collections = questionCollectionMapper.selectList(wrapper);
        for (QuestionCollection collection : collections) {
            Integer questionId = collection.getQuestionId();
            Question question = questionMapper.selectById(questionId);
            list.add(new CollectionInfo(question));
        }

        Long count = questionCollectionMapper.selectCount(wrapper);
        return new QuestionCollectionResponse(count.intValue(), list);
    }

    /**
     * 得到错题列表
     * @return
     */
    @Override
    public QuestionCollectionResponse getMistakes() {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        QueryWrapper<QuestionMistake> wrapper = new QueryWrapper<QuestionMistake>()
                .eq("user_phone", user.getUserPhone());

        ArrayList<CollectionInfo> list = new ArrayList<>();
        List<QuestionMistake> mistakes = questionMistakeMapper.selectList(wrapper);
        for (QuestionMistake mistake : mistakes) {
            Integer questionId = mistake.getQuestionId();
            Question question = questionMapper.selectById(questionId);
            list.add(new CollectionInfo(question));
        }

        Long count = questionMistakeMapper.selectCount(wrapper);
        return new QuestionCollectionResponse(count.intValue(), list);
    }

    /**
     * 删除错题
     * @param questionId
     * @return
     */
    @Override
    public boolean deleteMistake(int questionId) {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        if (!questionMistakeMapper.exists(new QueryWrapper<QuestionMistake>()
                .eq("user_phone", user.getUserPhone())
                .eq("question_id", questionId))) {
            return false;
        } else {
            questionMistakeMapper.delete(new QueryWrapper<QuestionMistake>()
                    .eq("question_id", questionId)
                    .eq("user_phone", user.getUserPhone()));
            return true;
        }
    }

    /**
     * 根据id获得问题
     */
    public Question getQuestionById(int questionId) {
        return questionMapper.selectOne(new QueryWrapper<Question>()
                .eq("id", questionId));
    }


}
