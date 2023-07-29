package com.lsp.service.member.impl;


import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.*;
import com.lsp.pojo.MyPage;
import com.lsp.pojo.member.entity.UserFollow;
import com.lsp.pojo.member.entity.UserHistory;
import com.lsp.pojo.member.entity.UserNote;
import com.lsp.pojo.member.request.PlanRequest;
import com.lsp.pojo.member.response.*;
import com.lsp.pojo.member.response.subclass.*;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.response.subclass.ResourceInfo;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.score.entity.ScoreDetail;
import com.lsp.pojo.score.response.ScoreRankResponse;
import com.lsp.pojo.study.entity.StudyPlan;
import com.lsp.pojo.study.entity.StudyPlanEvent;
import com.lsp.pojo.study.response.PlanResponse;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.member.entity.UserCollection;
import com.lsp.pojo.user.request.UserInfoRequest;
import com.lsp.service.group.GroupService;
import com.lsp.service.member.MemberService;
import com.lsp.service.score.ScoreService;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private ScoreMapper scoreMapper;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private StudyPlanMapper studyPlanMapper;

    @Autowired
    private StudyEventMapper studyEventMapper;

    @Autowired
    private GroupService groupService;


    @Override
    public boolean finishInfo(UserInfoRequest info) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_phone", info.getUserPhone());
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return false;
        } else {
            userMapper.update(info, wrapper);
            Score score = new Score();
            score.setGroupId(info.getGroupId());
            scoreMapper.update(score, new QueryWrapper<Score>().eq("user_phone", info.getUserPhone()));
            groupService.getGroupNums();
            return true;
        }

    }

    @Override
    public MemberInfoResponse getInfo() {
        User user = userMapper.selectById(Integer.parseInt(String.valueOf(StpUtil.getLoginId())));
        return new MemberInfoResponse(user);
    }

    @Override
    public CollectionResponse getCollections() {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<UserCollection>()
                .eq("user_phone", user.getUserPhone());
        List<UserCollection> collections = collectionsMapper.selectList(wrapper);
        ArrayList<ResourceInfo> list = new ArrayList<>();
        for (UserCollection collection : collections) {
            Integer resourceId = collection.getResourceId();
            Resource resource = resourceMapper.selectById(resourceId);
            list.add(new ResourceInfo(resource));
        }
        Integer count = Math.toIntExact(collectionsMapper.selectCount(wrapper));
        return new CollectionResponse(user.getUserPhone(), count, list);
    }

    @Override
    public boolean addCollection(Integer resourceId) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<UserCollection>()
                .eq("resource_id", resourceId).eq("user_phone", user.getUserPhone());
        if (collectionsMapper.selectOne(wrapper) != null) {
            return false;
        } else {
            UserCollection collection = new UserCollection();
            collection.setUserPhone(user.getUserPhone());
            collection.setResourceId(resourceId);
            collectionsMapper.insert(collection);
            return true;
        }
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
            list.add(new Notes(record.getId(), record.getResourceId(),
                    resourceMapper.selectById(record.getResourceId()).getResourceTitle(),
                    record.getNoteContent(), record.getCreateTime(), record.getUpdateTime()));
        }
        return new NoteResponse(user.getUserPhone(), Math.toIntExact(notePage.getTotal()),
                new MyPage(notePage.getCurrent(), notePage.getPages(), notePage.getSize(), notePage.getTotal()),
                list);
    }


    @Override
    public boolean addNote(Integer resourceId, String content) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        UserNote note = new UserNote();
        note.setUserPhone(user.getUserPhone());
        note.setResourceId(resourceId);
        note.setNoteContent(content);
        notesMapper.insert(note);
        return true;
    }

    @Override
    public boolean updateNote(Integer id, String content) {
        UserNote note = notesMapper.selectById(id);
        if (note == null) {
            return false;
        }
        note.setNoteContent(content);
        notesMapper.updateById(note);
        return true;
    }

    @Override
    public boolean deleteNoteById(Integer noteId) {
        if (notesMapper.selectById(noteId) != null) {
            notesMapper.deleteById(noteId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取学习周报
     *
     * @return
     */
    @Override
    public ReportResponse getReport() {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        //得到一周的积分详细（周报）
        List<ScoreDetail> scoreDetails = scoreDetailMapper.selectList(new QueryWrapper<ScoreDetail>()
                .eq("user_phone", user.getUserPhone())
                .ge("create_time", NumberUtil.getLastWeekLocalDateTime()));
        Integer scoreSum = 0;
        Integer scoreConsume = 0;
        for (ScoreDetail scoreDetail : scoreDetails) {
            scoreSum += scoreDetail.getSumScore();
            scoreConsume += scoreDetail.getExpenseScore();
        }
        WeekReportInfo reportInfo = new WeekReportInfo(scoreSum, scoreConsume);

        //得到两个积分数据
        Score score = scoreMapper.selectOne(new QueryWrapper<Score>()
                .eq("user_phone", user.getUserPhone()));
        WeekScoreInfo scoreInfo = new WeekScoreInfo(scoreSum, score.getScore());

        //学习数据（死数据）
        ArrayList<WeekStudyInfo> studyInfos = new ArrayList<>();
        studyInfos.add(new WeekStudyInfo("视频学习","2.5h"));
        studyInfos.add(new WeekStudyInfo("阅读学习","3.5h"));
        studyInfos.add(new WeekStudyInfo("请教别人","4.0h"));
        studyInfos.add(new WeekStudyInfo("答题训练","2.5h"));

        //成就数据
        ArrayList<WeekAchievementInfo> achievementInfos = new ArrayList<>();
        String date = MyUtil.getTodayDate();
        achievementInfos.add(new WeekAchievementInfo("#初学者","学习完五篇文章",date));
        achievementInfos.add(new WeekAchievementInfo("#持之以恒","每天坚持学习半个小时以上",date));
        achievementInfos.add(new WeekAchievementInfo("#答题天才","答题正确率超过80%",date));
        achievementInfos.add(new WeekAchievementInfo("#AI革新家","累计与AI交互达到3小时",date));
        achievementInfos.add(new WeekAchievementInfo("#社交达人","多次评论点赞",date));


        return new ReportResponse(reportInfo,scoreInfo,studyInfos,achievementInfos);
    }

    @Override
    public boolean addEvent(PlanRequest planRequest, Integer planId) {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);
        for (PlanPost request : planRequest.getPlanPosts()) {
            StudyPlanEvent studyPlanEvent = new StudyPlanEvent();
            studyPlanEvent.setUserPhone(user.getUserPhone());
            studyPlanEvent.setPlanId(planId);
            studyPlanEvent.setEventName(request.getEventName());
            studyPlanEvent.setEventTime(request.getEventTime());
            studyPlanEvent.setEventState(0);
            studyEventMapper.insert(studyPlanEvent);
        }
        return true;
    }

    @Override
    public Integer addPlan(String planName, String startTime, String endTime) {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        if (studyPlanMapper.exists(new QueryWrapper<StudyPlan>()
                .eq("plan_name", planName).eq("start_time", startTime))) {
            return 0;
        }

        StudyPlan studyPlan = new StudyPlan();
        studyPlan.setPlanState(0);
        studyPlan.setPlanName(planName);
        studyPlan.setStartTime(startTime);
        studyPlan.setEndTime(endTime);
        studyPlan.setUserPhone(user.getUserPhone());
        studyPlanMapper.insert(studyPlan);


        StudyPlan plan = studyPlanMapper.selectOne(new QueryWrapper<StudyPlan>()
                .eq("plan_name", planName).eq("start_time", startTime));
        return plan.getId();
    }

    @Override
    public List<PlanResponse> getPlan() {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);
        List<StudyPlan> studyPlans = studyPlanMapper.selectList
                (new QueryWrapper<StudyPlan>().eq("user_phone", user.getUserPhone()));
        ArrayList<PlanResponse> list = new ArrayList<>();
        for (StudyPlan studyPlan : studyPlans) {
            PlanResponse response = new PlanResponse(studyPlan.getId(), studyPlan.getPlanName(), studyPlan.getStartTime(),
                    studyPlan.getEndTime(), studyPlan.getPlanState(), studyPlan.getCreateTime());
            list.add(response);
        }
        return list;
    }

    @Override
    public List<MemberStudyEvent> getEventByPlan(Integer planId) {
        QueryWrapper<StudyPlanEvent> wrapper = new QueryWrapper<StudyPlanEvent>().eq("plan_id", planId);
        ArrayList<MemberStudyEvent> list = new ArrayList<>();
        for (StudyPlanEvent event : studyEventMapper.selectList(wrapper)) {
            list.add(new MemberStudyEvent(event.getPlanId(), event.getEventName(),
                    event.getEventTime(), event.getEventState()));
        }
        return list;
    }

    @Override
    public boolean deletePlanById(Integer planId) {
        if (studyPlanMapper.selectById(planId) != null) {
            studyPlanMapper.deleteById(planId);
            studyEventMapper.delete(new QueryWrapper<StudyPlanEvent>().eq("plan_id", planId));
            return true;
        } else {
            return false;
        }
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
                    resource.getResourceLabel(), resource.getResourceTitle(), resource.getResourceAuthorName(),
                    resource.getResourceAuthorHead(), record.getCreateTime()));
        }
        return new HistoryResponse(user.getUserPhone(), Math.toIntExact(historyPage.getTotal()),
                new MyPage(historyPage.getCurrent(), historyPage.getPages(), historyPage.getSize(), historyPage.getTotal()),
                list);
    }

    @Override
    public MemberMainResponse getMain() {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);

        //积分明细表新建一个数据
        QueryWrapper<ScoreDetail> wrapper1 = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper1.ge("create_time", NumberUtil.getTodayLocalDateTime());
        if (scoreDetailMapper.selectOne(wrapper1) == null) {
            scoreDetailMapper.insert(new ScoreDetail(user.getUserPhone()));
        }


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

        //得到积分简略情况(今天得分,总分,近七天得分)
        //今天得分
        QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>().eq("user_phone", user.getUserPhone());
        wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
        ScoreDetail sd = scoreDetailMapper.selectOne(wrapper);
        Integer todayGet = MyUtil.getTodaySum(sd);
        /*近七天得分
            将获得前6天时间戳改为倒序获得7天数据
        */
        wrapper.clear();
//        LocalDateTime lastWeekLocalDateTime = NumberUtil.getPreviousSixDaysLocalDateTime();
//        wrapper.eq("user_phone", user.getUserPhone()).ge("create_time", lastWeekLocalDateTime);
//        System.out.println(lastWeekLocalDateTime);

        //limit 7
        Page<ScoreDetail> page = new Page<>(1, 7);
        wrapper.eq("user_phone", user.getUserPhone()).orderByDesc("create_time");
        ArrayList<Day7Score> list = new ArrayList<>();
        for (ScoreDetail sd1 : scoreDetailMapper.selectPage(page, wrapper).getRecords()) {
            Integer todayGet1 = MyUtil.getTodaySum(sd1);
            String createTime = sd1.getCreateTime();
            Day7Score score = new Day7Score(createTime, todayGet1);
            list.add(score);
        }
        MemberScore memberScore = new MemberScore(todayGet, scoreService.getSumScore(), list);

        //学习计划

        List<StudyPlan> studyPlans = studyPlanMapper.selectList(
                new QueryWrapper<StudyPlan>().eq("user_phone", user.getUserPhone()));

        MemberStudyPlan memberStudyPlan = new MemberStudyPlan();
        memberStudyPlan.setName("快去制定自己的学习计划吧！");
        if (studyPlans.size() != 0) {
            StudyPlan newSP = studyPlans.get(0);
            for (StudyPlan studyPlan : studyPlans) {
                if (studyPlan.getPlanState() == 1) {
                    newSP = studyPlan;
                    break;
                }
            }
            List<StudyPlanEvent> planEventList = studyEventMapper.selectList(
                    new QueryWrapper<StudyPlanEvent>().eq("plan_id", newSP.getId()));
            ArrayList<MemberStudyEvent> events = new ArrayList<>();
            for (StudyPlanEvent planEvent : planEventList) {
                MemberStudyEvent event = new MemberStudyEvent(planEvent.getPlanId(), planEvent.getEventName(),
                        planEvent.getEventTime(), planEvent.getEventState());
                events.add(event);
            }
            memberStudyPlan.setPlanId(newSP.getId());
            memberStudyPlan.setName(newSP.getPlanName());
            memberStudyPlan.setState(newSP.getPlanState());
            memberStudyPlan.setStartTime(newSP.getStartTime());
            memberStudyPlan.setEndTime(newSP.getEndTime());
            memberStudyPlan.setStudyEvent(events);
        }

        //得到排行榜
        ScoreRankResponse rank = scoreService.getSumRank3();
        MemberGroup memberGroup = new MemberGroup(rank.getGroupId(), rank.getGroupName(),
                rank.getPeopleNums(), rank.getGroupRankList());

        //淘友圈
        GroupForumResponse groupForum = groupService.getGroupForum(1, 3);
        return new MemberMainResponse(memberBrief, member4Part, memberScore, memberStudyPlan,
                memberGroup, groupForum);
    }


}
