package com.practice.carrot.login;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class LoginDto {


    private Long id;
    @NotBlank(message = "이름을 입력하시오")
    @Size(min = 2, max = 8 ,message = "2~8자 사이로 입력해주세요.")
    private String username;

    @NotBlank(message = "id는 공백일 수 없습니다.")
    @Size(min = 2, max = 8, message = "id를 2~8자 사이로 입력해주세요.")
    private String userid;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,12}", message = "비밀번호는 영문 대 소문자, 숫자, 특수문자 6~12자리 이내로 입력해주세요.")
    private String password;
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "올바른 형식의 이메일 주소여야 합니다")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LoginDao toDao() {
        LoginDao login = new LoginDao();
        login.setId(id);
        login.setEmail(email);
        login.setUserid(userid);
        login.setPassword(password);
        login.setUsername(username);
        login.setLocalDateTime(localDateTime);
        return login;
    }
}
