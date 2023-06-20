package com.mphasis.timesheetlogin.service;

import com.mphasis.timesheetlogin.dto.UserDto;
import com.mphasis.timesheetlogin.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    List<UserDto> findAllUsers();

    UserDto findUserByEmail(String email);

    UserDto saveUser(UserDto userDto);

    UserDto findUserByName(String userName);
}
