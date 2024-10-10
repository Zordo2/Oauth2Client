package com.oauth2client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @Autowired
    ResourceServerClient resourceServerClient;

    @GetMapping("/test")
    public String tsfds(){
        return resourceServerClient.callResourceServer();
    }
}
