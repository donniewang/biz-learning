package com.biz.cloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.biz.cloud.domain.User;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> userList;
    
    public void create(User user) {
        userList.add(user);
    }

    public User getUser(Long id) {
        Optional<User> found = userList.stream().filter(u -> u.getId().equals(id)).findFirst();
        if(found.isPresent()) {
            return found.get();
        }
        return null;
    }

    public void update(User user) {
        userList.stream().filter(u -> u.getId().equals(user.getId())).forEach(u -> {
            BeanUtils.copyProperties(user, u);
        });
    }

    public void delete(Long id) {
        User user = getUser(id);
        if(user!=null) {
            userList.remove(user);
        }
    }

    public User getByUsername(String username) {
        Optional<User> found = userList.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(found.isPresent()) {
            return found.get();
        }
        return null;
    }

    public List<User> getUserByIds(List<Long> ids) {
        return userList.stream().filter(u -> ids.contains(u.getId())).collect(Collectors.toList());
    }

    @PostConstruct
    public void initData() {
        userList = new ArrayList<>();
        userList.add(User.builder().id(1L).username("macro").password("123456").build());
        userList.add(User.builder().id(2L).username("andy").password("123456").build());
        userList.add(User.builder().id(3L).username("mark").password("123456").build());
    }
}