package com.practice.carrot.aesCryption;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class AESController {

    @Autowired
    AESService aesService;

    @GetMapping("/encry")
    public ResponseEntity encry(HttpServletRequest request, HttpServletResponse response, @RequestBody AESdto str, @RequestParam Long keys) throws Exception {
        return ResponseEntity.ok().body(aesService.encrypt(str.getStr(), keys));
    }

    @GetMapping("/decry")
    public ResponseEntity decry(HttpServletRequest request, HttpServletResponse response, @RequestBody AESdto str, @RequestParam Long keys) throws Exception {
        return ResponseEntity.ok().body(aesService.decrypt(str.getStr(), keys));
    }
}
