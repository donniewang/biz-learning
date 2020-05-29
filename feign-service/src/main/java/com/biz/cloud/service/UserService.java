package com.biz.cloud.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.biz.cloud.domain.CommonResult;
import com.biz.cloud.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.ribbon.proxy.annotation.Hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

@Service
public class UserService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserFeignService userFeignService;

    public CommonResult create(User user) {
        return userFeignService.create(user);
    }

    public CommonResult<User> getUser(Long id) {
        return userFeignService.getUser(id);
    }

    public CommonResult<User> getByUsername(String username) {
        return userFeignService.getByUsername(username);
    }

    public CommonResult update(User user) {
        return userFeignService.update(user);
    }

    public CommonResult delete(Long id) {
        return userFeignService.delete(id);
    }

    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {
        @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
    })
    public Future<User> getUserFuture(Long id) {
        return new AsyncResult<User>(){
            @Override
            public User invoke() {
                CommonResult commonResult = userFeignService.getUser(id);
                User user = (User) commonResult.getData();
                LOGGER.info("getUserById username:{}", user.getUsername());
                return user;
            }
            @Override
            public User get() {
                return invoke();
            }
        };
    }
    
    @HystrixCommand
    public List<User> getUserByIds(List<Long> ids) {
        LOGGER.info("getUserByIds:{}", ids);
        CommonResult commonResult = userFeignService.getUserByIds(ids);
        LOGGER.info("getUserByIds:{}", commonResult);
        return (List<User>) commonResult.getData();
    }
    
}