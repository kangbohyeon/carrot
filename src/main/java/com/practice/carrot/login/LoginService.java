package com.practice.carrot.login;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class LoginService implements UserDetailsService {
    Logger logger = LogManager.getLogger(LoginService.class);
    @Autowired
    LoginRespository loginRespository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String signup(LoginDto requestbody, BindingResult bindingResult) {
        if (requestbody.getUserid() == null || requestbody.getUsername() == null || requestbody.getPassword() == null || requestbody.getEmail() == null) {
            logger.info("parameter is null");
            return "parameter is null";
        }
        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();//valid error log print()
            logger.info(objectError.getDefaultMessage());
            return objectError.getDefaultMessage();
        }
        requestbody.setLocalDateTime(LocalDateTime.now());
        requestbody.setPassword(passwordEncoder.encode(requestbody.getPassword()));
        LoginDao login = requestbody.toDao();
        loginRespository.save(login);
        logger.info("sign success");
        return "sign success";
    }


    public String checkid(LoginDto requestbody) {
        if (requestbody.getEmail() == null) {
            logger.info("parameter is null");
            return "parameter is null";
        }
        Optional<LoginDao> result = loginRespository.findByUserid(requestbody.toDao().getUserid());
        if (!result.isPresent()) {
            logger.info("사용가능한 id입니다.");
            return "사용가능한 id입니다.";
        } else {
            logger.info("존재하는 id입니다.");
            return "존재하는 id입니다.";
        }

    }

    public String checkemail(LoginDto requestbody) {
        if (requestbody.getEmail() == null) {
            logger.info("parameter is null");
            return "parameter is null";
        }
        Long result = loginRespository.findByEmail(requestbody.toDao());
        if (result > 0) {
            logger.info("존재하는 email입니다.");
            return "존재하는 email입니다.";
        } else {
            logger.info("사용가능한 email입니다.");
            return "사용가능한 email입니다.";
        }
    }

    public String login(LoginDto requestbody) {
        if (requestbody.getUserid() == null || requestbody.getPassword() == null) {
            logger.info("parameter is null");
            return "parameter is null";
        }
        Optional<LoginDao> result = loginRespository.findByUserid(requestbody.getUserid());
        if (!result.isPresent()) {
            return "id error";
        } else if (result.isPresent() && passwordEncoder.matches(requestbody.getPassword(), result.get().getPassword())) {
            String uuid = UUID.randomUUID().toString();
            logger.info("login Marker : " + uuid);
            return "login Marker : " + uuid;
        } else {
            return "password error";
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


}
