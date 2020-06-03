package com.biz.cloud.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.biz.cloud.domain.CommonResult;
import com.biz.cloud.domain.User;
import com.biz.cloud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return userService.getUser(id);
    }

    @GetMapping("/getByFuture")
    public CommonResult getByFuture(@RequestParam Long id) throws ExecutionException, InterruptedException {
        // Future<User> future1 = userService.getUserFuture(1L);
        // User user1 = future1.get();
        Future<User> future2 = userService.getUserFuture(id);
        User user2 = future2.get();
        // Future<User> future3 = userService.getUserFuture(id);
        // User user3 = future3.get();
        if (user2!=null) {
            return new CommonResult<>(user2);
        } else {
            return new CommonResult("操作失败", 500);
        }
    }

    @GetMapping("/getByUsername")
    public CommonResult getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        return userService.delete(id);
    }
    
}