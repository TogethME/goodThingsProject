package com.ni.projectw.goodthingsweb.service;

import com.ni.projectw.goodthingsweb.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

}
