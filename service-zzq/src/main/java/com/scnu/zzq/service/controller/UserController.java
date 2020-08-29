package com.scnu.zzq.service.controller;

import com.google.common.collect.Lists;
import com.scnu.zzq.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("{}",userList);
        return userList;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") String userId){
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(31);
        userDTO.setName("zzq");
        userDTO.setUserId("1");
        log.info("{}",userDTO);
        return userDTO;
    }

}
