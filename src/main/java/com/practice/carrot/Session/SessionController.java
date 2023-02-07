package com.practice.carrot.Session;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SessionController {
    Logger logger = LogManager.getLogger(SessionController.class);

    @Autowired
    SessionService sessionService;

    @GetMapping("/session")
    public ResponseEntity session(HttpServletRequest request, HttpServletResponse response, @RequestParam Long keys){
        String result = sessionService.sessioninsert(request,response,keys);
        return ResponseEntity.ok().body(result);
    }
}
