package com.practice.carrot.session;


public class SessionDto {
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

    private Long id ;

    private String Session;

    public SessionDao toDao(){
        SessionDao sessionDao = new SessionDao();
        sessionDao.setId(id);
        sessionDao.setSession(Session);
        return sessionDao;
    }

}
