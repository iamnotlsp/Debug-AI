package com.lsp.service.member.impl;


import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.*;
import com.lsp.pojo.MyPage;
import com.lsp.pojo.Result;
import com.lsp.pojo.member.entity.UserFollow;
import com.lsp.pojo.member.entity.UserHistory;
import com.lsp.pojo.member.entity.UserNote;
import com.lsp.pojo.member.response.*;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.score.entity.ScoreDetail;
import com.lsp.pojo.score.response.ScoreLog;
import com.lsp.pojo.score.response.ScoreRankResponse;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.member.entity.UserCollection;
import com.lsp.pojo.user.resquest.UserInfoRequest;
import com.lsp.service.group.GroupService;
import com.lsp.service.member.MemberService;
import com.lsp.service.score.ScoreService;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CollectionsMapper collectionsMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private FollowersMapper followersMapper;

    @Autowired
    private NotesMapper notesMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private ScoreDetailMapper scoreDetailMapper;

    @Autowired
    private ScoreService scoreService;


    @Override
    public boolean finishInfo(UserInfoRequest info) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_phone", info.getUserPhone());
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return false;
        } else {
            userMapper.update(info, wrapper);
            return true;
        }

    }

    @Override
    public Result<User> getInfo() {
        User user = userMapper.selectById(Integer.parseInt(String.valueOf(StpUtil.getLoginId())));
        return Result.success("用户信息:", user);
    }

    @Override
    public CollectionResponse getCollections() {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<UserCollection>()
                .eq("user_phone", user.getUserPhone());
        List<UserCollection> collections = collectionsMapper.selectList(wrapper);
        ArrayList<Resource> list = new ArrayList<>();
        for (UserCollection collection : collections) {
            Integer resourceId = collection.getResourceId();
            Resource resource = resourceMapper.selectById(resourceId);
            list.add(resource);
        }
        Integer count = Math.toIntExact(collectionsMapper.selectCount(wrapper));
        return new CollectionResponse(user.getUserPhone(), count, list);
    }

    @Override
    public FollowResponse getFollower() {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>()
                .eq("user_phone", user.getUserPhone());
        ArrayList<Followers> list = new ArrayList<>();
        for (UserFollow follow : followersMapper.selectList(wrapper)) {
            list.add(new Followers(follow.getFollowName(), follow.getFollowDescribe(),
                    follow.getHeadPhoto(), follow.getCreateTime()));
        }
        Integer count = Math.toIntExact(followersMapper.selectCount(wrapper));
        return new FollowResponse(user.getUserPhone(), count, list);
    }

    @Override
    public NoteResponse getNote(Integer start, Integer pageSize) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        Page<UserNote> page = new Page(start, pageSize);
        QueryWrapper<UserNote> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", user.getUserPhone());
        Page<UserNote> notePage = notesMapper.selectPage(page, wrapper);
        ArrayList<Notes> list = new ArrayList<>();
        for (UserNote record : notePage.getRecords()) {
            list.add(new Notes(record.getResourceId(), record.getNoteContent(), record.getCreateTime(), record.getUpdateTime()));
        }
        return new NoteResponse(user.getUserPhone(), Math.toIntExact(notePage.getTotal()),
                new MyPage(notePage.getCurrent(), notePage.getPages(), notePage.getSize(), notePage.getTotal()),
                list);
    }

    @Override
    public HistoryResponse getHistory(Integer start, Integer pageSize) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        Page<UserHistory> page = new Page<>(start, pageSize);
        QueryWrapper<UserHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", user.getUserPhone());
        Page<UserHistory> historyPage = historyMapper.selectPage(page, wrapper);
        ArrayList<Histories> list = new ArrayList<>();
        for (UserHistory record : historyPage.getRecords()) {
            Resource resource = resourceMapper.selectById(record.getResourceId());
            list.add(new Histories(resource.getResourceId(), resource.getResourceType(),
                    resource.getResourceLabel(), resource.getResourceTitle(), resource.getResourceDescribe()));
        }
        return new HistoryResponse(user.getUserPhone(), Math.toIntExact(historyPage.getTotal()),
                new MyPage(historyPage.getCurrent(), historyPage.getPages(), historyPage.getSize(), historyPage.getTotal()),
                list);
    }

    @Override
    public MemberMainResponse getMain() {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        //得到用户简略消息
        MemberBrief memberBrief = new MemberBrief(user.getUserPhone(), user.getUserName(), user.getHeadPhoto(),
                user.getUserLikes(), user.getUserInfo(), user.getAchievement(),
                NumberUtil.getTimeDifference(user.getCreateTime(), NumberUtil.getTodayLocalDateTime()));

        //得到用户四个功能数量
        Integer count1 = Math.toIntExact(collectionsMapper.selectCount
                (new QueryWrapper<UserCollection>().eq("user_phone", user.getUserPhone())));
        Integer count2 = Math.toIntExact(followersMapper.selectCount
                (new QueryWrapper<UserFollow>().eq("user_phone", user.getUserPhone())));
        Integer count3 = Math.toIntExact(historyMapper.selectCount
                (new QueryWrapper<UserHistory>().eq("user_phone", user.getUserPhone())));
        Integer count4 = Math.toIntExact(notesMapper.selectCount
                (new QueryWrapper<UserNote>().eq("user_phone", user.getUserPhone())));
        Member4Part member4Part = new Member4Part(count1, count2, count3, count4);

        //得到积分简略情况，冗余很大，有修改空间
        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
        ScoreDetail sd = scoreDetailMapper.selectOne(wrapper);
        Integer todayGet = sd.getLoginScore() + sd.getArticleScore() + sd.getViewScore() + sd.getAiScore() + sd.getPkScore() + sd.getAnswerScore() - sd.getExpenseScore();

        wrapper.clear();
        LocalDateTime lastWeekLocalDateTime = NumberUtil.getLastWeekLocalDateTime();
        long lastWeekTimestamp = NumberUtil.getTimestamp(lastWeekLocalDateTime);
        wrapper.eq("user_phone", user.getUserPhone()).ge("create_time", lastWeekTimestamp);
        ArrayList<HashMap> list = new ArrayList<>();
        for (ScoreDetail sd1 : scoreDetailMapper.selectList(wrapper)) {
            Integer todayGet1 = sd.getLoginScore() + sd.getArticleScore() + sd.getViewScore()
                    + sd.getAiScore() + sd.getPkScore() + sd.getAnswerScore() - sd.getExpenseScore();
            String createTime = sd1.getCreateTime();
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(createTime, todayGet1);
            list.add(hashMap);
        }
        MemberScore memberScore = new MemberScore(todayGet, scoreService.getSumScore(), list);

        //没想好学习计划
        MemberStudyPlan memberStudyPlan = new MemberStudyPlan("学习计划");

        //得到排行榜
        ScoreRankResponse rank = scoreService.getSumRank();
        MemberGroup memberGroup = new MemberGroup(rank.getGroupId(), rank.getGroupName(),
                rank.getPeopleNums(), rank.getGroupRankList());

        return new MemberMainResponse(memberBrief, member4Part, memberScore, memberStudyPlan, memberGroup);
    }
}
