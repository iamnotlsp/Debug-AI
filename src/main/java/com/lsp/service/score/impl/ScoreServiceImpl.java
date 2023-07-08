package com.lsp.service.score.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.GroupMapper;
import com.lsp.mapper.ScoreDetailMapper;
import com.lsp.mapper.ScoreMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.score.entity.ScoreDetail;
import com.lsp.pojo.score.response.GroupRank;
import com.lsp.pojo.score.response.ScoreDetailResponse;
import com.lsp.pojo.score.response.ScoreLog;
import com.lsp.pojo.score.response.ScoreRankResponse;
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
    public Integer addScore(Integer add, Integer type) {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        User user = userMapper.selectById(id);
        Score score = scoreMapper.selectById(id);
        score.setScore(score.getScore() + add);
        scoreMapper.updateById(score);

        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
//        if (scoreDetailMapper.selectOne(wrapper) == null) {
//            scoreDetailMapper.insert(new ScoreDetail(user.getUserPhone()));
//        }
        ScoreDetail scoreDetail = scoreDetailMapper.selectOne(wrapper);
        switch (type) {
            case 1:
                scoreDetail.setArticleScore(scoreDetail.getArticleScore() + add);
                break;
            case 2:
                scoreDetail.setViewScore(scoreDetail.getViewScore() + add);
                break;
            case 3:
                scoreDetail.setPkScore(scoreDetail.getPkScore() + add);
                break;
            case 4:
                scoreDetail.setAnswerScore(scoreDetail.getAnswerScore() + add);
                break;
            case 5:
                scoreDetail.setAiScore(scoreDetail.getAiScore() + add);
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
    public ScoreDetailResponse getDetail() {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        String userPhone = user.getUserPhone();
        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", userPhone).orderByDesc("create_time");
        List<ScoreDetail> scoreDetails = scoreDetailMapper.selectList(wrapper);
        ArrayList<ScoreLog> logs = new ArrayList<>();
        for (ScoreDetail sd : scoreDetails) {
            Integer todayGet = sd.getLoginScore() + sd.getArticleScore() + sd.getViewScore() + sd.getAiScore() + sd.getPkScore() + sd.getAnswerScore() - sd.getExpenseScore();
            logs.add(new ScoreLog(todayGet, sd.getLoginScore(), sd.getArticleScore(), sd.getViewScore(), sd.getAnswerScore(), sd.getPkScore(), sd.getAiScore(), sd.getExpenseScore(),
                    sd.getCreateTime()));
        }
        return new ScoreDetailResponse(userPhone, scoreMapper.selectById(id).getScore(), logs);
    }
}
