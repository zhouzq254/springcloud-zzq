package com.scnu.zzq.service.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.Lists;
import com.scnu.zzq.service.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/list")
    public List<UserDTO> getUsers(){
        List<UserDTO> userList = Lists.newArrayList();
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(31);
        userDTO.setName("zzq");
        userDTO.setUserId("1");

        userList.add(userDTO);
        return userList;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") String userId){
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(31);
        userDTO.setName("zzq");
        userDTO.setUserId("1");
        System.out.println(userDTO);
        return userDTO;
    }

}
