package com.mphasis.timesheetlogin.service;

import com.mphasis.timesheetlogin.dto.UserDto;
import com.mphasis.timesheetlogin.dto.UserMapper;
import com.mphasis.timesheetlogin.entity.Role;
import com.mphasis.timesheetlogin.entity.User;
import com.mphasis.timesheetlogin.repository.RoleRepository;
import com.mphasis.timesheetlogin.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<UserDto> findAllUsers() {

        List< User> users=userRepository.findAll();

        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null)
            return null;
        return userMapper.toDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user=userMapper.toEntity(userDto);
        List<Role> roles=new ArrayList<>();
        for(String roleName:userDto.getRoles())
        {
            Role role=roleRepository.findByName(roleName);
            if(role==null){
                role= Role.builder().name(roleName).build();
                roleRepository.save(role);
            }
            roles.add(role);
        }

        /*Role role=roleRepository.findByName("ROLE_ADMIN");
        if(role==null)
        {
            role=checkRoleExist();
        }*/
        user.setRoles(roles);
        User savedUser=userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto findUserByName(String userName) {
        User user= userRepository.findByEmail(userName);
        if(user!=null)
        return userMapper.toDto(user);
        else
            return null;

    }

    /*private Role checkRoleExist()
    {
        Role role=new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }*/

}
