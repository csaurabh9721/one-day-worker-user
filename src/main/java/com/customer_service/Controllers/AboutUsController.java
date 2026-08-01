package com.customer_service.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping("/customerService/about-us")
    public String aboutUs() {
        return "forward:/about-us.html";
    }
}
