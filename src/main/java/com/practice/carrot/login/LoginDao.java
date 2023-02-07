package com.practice.carrot.login;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class LoginDao {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column(unique = true)
    private String userid;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    private LocalDateTime localDateTime;



    public LoginDto toDto() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setId(id);
        loginDto.setUserid(userid);
        loginDto.setPassword(password);
        loginDto.setUsername(username);
        loginDto.setLocalDateTime(localDateTime);
        return loginDto;
    }

}
