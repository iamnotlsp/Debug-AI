package com.lsp.service.score.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.GroupMapper;
import com.lsp.mapper.ScoreDetailMapper;
import com.lsp.mapper.ScoreMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.MyPage;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.score.entity.ScoreDetail;
import com.lsp.pojo.score.response.*;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.entity.UserGroup;
import com.lsp.service.group.GroupService;
import com.lsp.service.score.ScoreService;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ScoreDetailMapper scoreDetailMapper;

    @Override
    public Integer getSumScore() {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        Score score = scoreMapper.selectById(id);
        return score.getScore();
    }

    @Override
    public Integer addScore(Integer type) {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        User user = userMapper.selectById(id);
        Score score = scoreMapper.selectById(id);
        score.setScore(score.getScore() + 1);
        scoreMapper.updateById(score);

        //找到今天的score日志
        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
        ScoreDetail scoreDetail = scoreDetailMapper.selectOne(wrapper);

        switch (type) {
            case 1:
                scoreDetail.setArticleScore(scoreDetail.getArticleScore() + 1);
                break;
            case 2:
                scoreDetail.setViewScore(scoreDetail.getViewScore() + 1);
                break;
            case 3:
                scoreDetail.setPkScore(scoreDetail.getPkScore() + 6);
                break;
            case 4:
                scoreDetail.setAnswerScore(scoreDetail.getAnswerScore() + 1);
                break;
            case 5:
                scoreDetail.setAiScore(scoreDetail.getAiScore() + 2);
                break;
            default:
                break;
        }
        scoreDetailMapper.update(scoreDetail, wrapper);
        return score.getScore();
    }

    @Override
    public Integer reduceScore(Integer reduce) {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        User user = userMapper.selectById(id);
        Score score = scoreMapper.selectById(id);
        score.setScore(score.getScore() - reduce);
        scoreMapper.updateById(score);

        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
        if (scoreDetailMapper.selectOne(wrapper) == null) {
            scoreDetailMapper.insert(new ScoreDetail(user.getUserPhone()));
        }
        ScoreDetail scoreDetail = scoreDetailMapper.selectOne(wrapper);
        scoreDetail.setExpenseScore(scoreDetail.getExpenseScore() + reduce);
        scoreDetailMapper.update(scoreDetail, wrapper);

        return score.getScore();
    }

    @Override
    public ScoreRankResponse getSumRank() {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        User user = userMapper.selectById(id);
        Integer groupId = user.getGroupId();
        UserGroup group = groupMapper.selectByGroupId(groupId);
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId).orderByDesc("score");
        List<Score> scores = scoreMapper.selectList(wrapper);
        ArrayList<GroupRank> list = new ArrayList<>();
        for (int i = 1; i <= scores.size(); i++) {
            Score score = scores.get(i - 1);
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            User user1 = userMapper.selectOne(wrapper1.eq("user_phone", score.getUserPhone()));
            list.add(new GroupRank(i, user1.getUserName(), score.getScore()));
        }
        return new ScoreRankResponse(groupId, group.getGroupName(), groupService.getGroupNums(), list);

    }

    @Override
    public ScoreDetailResponse getDetail(Integer start, Integer pageSize) {
        //当前用户手机号
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        String userPhone = user.getUserPhone();
        //一起的日子
        String togetherDay = NumberUtil.getTimeDifference(user.getCreateTime(), NumberUtil.getTodayLocalDateTime());
        //倒序日志
        Page<ScoreDetail> page = new Page<>(start, pageSize);
        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", userPhone).orderByDesc("create_time");
        Page<ScoreDetail> scoreDetails = scoreDetailMapper.selectPage(page, wrapper);

        ArrayList<ScoreLog> logs = new ArrayList<>();
        for (ScoreDetail sd : scoreDetails.getRecords()) {
//            不知道为什么找不到statement,只能作罢
//            Integer todayGet = scoreDetailMapper.selectToday(sd);
            Integer todayGet = MyUtil.getTodaySum(sd);
            ArrayList<ScoreTask> list = new ArrayList<>();
            list.add(new ScoreTask("登录积分", sd.getLoginScore()));
            list.add(new ScoreTask("文章积分", sd.getArticleScore()));
            list.add(new ScoreTask("视频积分", sd.getViewScore()));
            list.add(new ScoreTask("PK积分", sd.getPkScore()));
            list.add(new ScoreTask("AI积分", sd.getAiScore()));
            list.add(new ScoreTask("消费积分", sd.getExpenseScore()));

            logs.add(new ScoreLog(todayGet, list, sd.getCreateTime()));
        }
        return new ScoreDetailResponse(userPhone, scoreMapper.selectById(id).getScore(), togetherDay,
                new MyPage(scoreDetails.getCurrent(), scoreDetails.getPages(),
                        scoreDetails.getSize(), scoreDetails.getTotal()), logs);
    }

    @Override
    public ScoreTaskResponse getScoreTask() {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);
        String userPhone = user.getUserPhone();

        Integer sumScore = getSumScore();

        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
        ScoreDetail sd = scoreDetailMapper.selectOne(wrapper);
        Integer todaySum = MyUtil.getTodaySum(sd);


        ArrayList<ScoreTaskDetail> list = new ArrayList<>();
        list.add(new ScoreTaskDetail("看文章", "每有效阅读一篇文章+1分", sd.getArticleScore(), 6));
        list.add(new ScoreTaskDetail("看视频", "每有效观看一个视频+1分", sd.getViewScore(), 6));
        list.add(new ScoreTaskDetail("回答问题", "每成功回答一个问题+1分", sd.getAnswerScore(), 6));
        list.add(new ScoreTaskDetail("完成pk", "胜利+6分,失败+3分", sd.getPkScore(), 6));
        list.add(new ScoreTaskDetail("完成ai模块的学习", "每学习一个模块+2分", sd.getAiScore(), 6));

        return new ScoreTaskResponse(sumScore, todaySum, NumberUtil.getTimeDifference
                (user.getCreateTime(), NumberUtil.getTodayLocalDateTime()), list);
    }
}
