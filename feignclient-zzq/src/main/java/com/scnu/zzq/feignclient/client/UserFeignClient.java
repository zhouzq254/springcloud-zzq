package com.scnu.zzq.feignclient.client;

import com.scnu.zzq.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "service-zzq")
public interface UserFeignClient extends UserService {


}
