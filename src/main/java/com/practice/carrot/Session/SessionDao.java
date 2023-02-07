package com.practice.carrot.Session;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SessionDao {

    @Id
    private Long id;

    private String Session;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public SessionDto toDto(){
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(id);
        sessionDto.setSession(Session);
        return sessionDto;
    }
}
