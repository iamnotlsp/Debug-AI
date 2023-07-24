package com.lsp.service.user.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.ScoreDetailMapper;
import com.lsp.mapper.ScoreMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.Result;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.score.entity.ScoreDetail;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.request.RegisterRequest;
import com.lsp.service.user.UserService;
import com.lsp.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private ScoreDetailMapper scoreDetailMapper;


    @Override
    public Result<SaTokenInfo> doLogin(String userPhone, String password) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_phone", userPhone);
        if (userMapper.exists(userQueryWrapper)) {
            userQueryWrapper.eq("user_password", password);
            if (userMapper.exists(userQueryWrapper)) {
                User user = userMapper.selectOne(userQueryWrapper);
                System.out.println(user);
                StpUtil.login(user.getId());

                //积分明细表新建一个数据
                QueryWrapper<ScoreDetail> wrapper = new QueryWrapper<ScoreDetail>()
                        .eq("user_phone", user.getUserPhone());
                wrapper.ge("create_time", NumberUtil.getTodayLocalDateTime());
                if (scoreDetailMapper.selectOne(wrapper) == null) {
                    scoreDetailMapper.insert(new ScoreDetail(user.getUserPhone()));
                }
                //总积分更改
                Score score = scoreMapper.selectOne(new QueryWrapper<Score>()
                        .eq("user_phone",user.getUserPhone()));
                score.setScore(score.getScore() + 1);
                scoreMapper.updateById(score);

                return Result.success("登录成功", StpUtil.getTokenInfo());
            } else {
                return Result.error("密码错误");
            }
        } else {
            return Result.error("账号不存在");
        }
    }

    @Override
    public boolean register(RegisterRequest request) {
        String mobile = request.getMobile();
        String password = request.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", mobile);
        if (userMapper.exists(wrapper)) {
            return false;
        }else {
            userMapper.insert(new User(mobile, password));
            scoreMapper.insert(new Score(mobile));

            return true;
        }
    }



    @Override
    public boolean isRegister(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone",phone);
        if (userMapper.exists(wrapper)){
            return false;
        }else {
            return true;
        }
    }


}
