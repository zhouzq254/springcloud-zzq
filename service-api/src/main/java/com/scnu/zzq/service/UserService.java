package com.scnu.zzq.service;

import com.scnu.zzq.service.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserService {

    @GetMapping("/users/list")
    List<UserDTO> getUsers();

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") String userId);
}
