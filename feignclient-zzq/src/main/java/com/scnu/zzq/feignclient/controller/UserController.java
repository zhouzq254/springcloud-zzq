package com.scnu.zzq.feignclient.controller;

import com.scnu.zzq.feignclient.service.UserServcie;
import com.scnu.zzq.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServcie userServcie;

    @GetMapping("/list")
    public List<UserDTO> getUsers(){
        return userServcie.getUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") String userId){
        return userServcie.getUserById(userId);
    }


}
