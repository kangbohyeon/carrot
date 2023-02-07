package com.practice.carrot.login;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {
    Logger logger = LogManager.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto responsebody, @RequestParam Long keys) {
        String result = loginService.login(responsebody);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/signup")
    public ResponseEntity signup(HttpServletRequest request, HttpServletResponse response, @Validated @RequestBody LoginDto responsebody, BindingResult bindingResult, @RequestParam Long keys) throws Exception {
        String result = loginService.signup(responsebody, bindingResult);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/check/id")
    public ResponseEntity checkid(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto responsebody, @RequestParam Long keys) {
        String result = loginService.checkid(responsebody);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/check/email")
    public ResponseEntity checkemail(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto responsebody, @RequestParam Long keys) {
        String result = loginService.checkemail(responsebody);
        return ResponseEntity.ok().body(result);
    }
}
