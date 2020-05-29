package com.biz.cloud.service;

import java.util.List;
import java.util.concurrent.Future;

import com.biz.cloud.domain.CommonResult;
import com.biz.cloud.domain.User;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "biz-learning-user-service", fallback = UserFallbackService.class)
public interface UserFeignService {

    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable Long id);

    @GetMapping("/user/{id}")
    Future<User> getUserFuture(@PathVariable Long id);
    
    @GetMapping("/user/getUserByIds")
    CommonResult<List<User>> getUserByIds(@RequestParam List<Long> ids);
    
}