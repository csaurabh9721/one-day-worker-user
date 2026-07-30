package com.customer_service.service;
import com.customer_service.AppConfiguration.FeignConfig;
import com.customer_service.dto.IdentityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "Auth-Service",
        configuration = FeignConfig.class
)
public interface AuthFeignClient {

    @GetMapping("/authApi/v1/auth/me")
    IdentityDto authMe();

}
