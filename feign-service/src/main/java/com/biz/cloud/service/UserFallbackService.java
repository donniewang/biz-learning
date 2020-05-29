package com.biz.cloud.service;

import java.util.List;
import java.util.concurrent.Future;

import com.biz.cloud.domain.CommonResult;
import com.biz.cloud.domain.User;

import org.springframework.stereotype.Component;

@Component
public class UserFallbackService implements UserFeignService {

    @Override
    public CommonResult create(User user) {
        User defaultUser = User.builder().id(-1L).username("defaultUser2").password("123456").build();
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = User.builder().id(-1L).username("defaultUser2").password("123456").build();
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult<User> getByUsername(String username) {
        User defaultUser = User.builder().id(-1L).username("defaultUser2").password("123456").build();
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult update(User user) {
        return new CommonResult("调用失败，服务被降级", 500);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("调用失败，服务被降级", 500);
    }

    @Override
    public Future<User> getUserFuture(Long id) {
        return null;
    }

    @Override
    public CommonResult<List<User>> getUserByIds(List<Long> ids) {
        return null;
    }

}