package com.scnu.zzq.feignclient.service;

import com.scnu.zzq.feignclient.client.UserFeignClient;
import com.scnu.zzq.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServcie {

    @Autowired
    private UserFeignClient userFeignClient;

    public List<UserDTO> getUsers(){
        return userFeignClient.getUsers();
    }

    public UserDTO getUserById(String userId){
        return userFeignClient.getUserById(userId);
    }
}
