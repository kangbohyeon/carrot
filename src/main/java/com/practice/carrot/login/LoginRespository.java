package com.practice.carrot.login;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface LoginRespository extends JpaRepository<LoginDao, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into sampledb.login_dao (id, email,password,username)values ((select max(u.id) from sampledb.login_dao as u)+1,:#{#login.email},:#{#login.password},:#{#login.username});", nativeQuery = true)
    void InsertLoginDao(@Param("login") LoginDao loginDao);

//    @Query(value = "select * from sampledb.login_dao as l where l.username=:#{#loginDao.username}",nativeQuery = true)
//    Optional<LoginDto> findByUsername(String username);

    @Query(value = "select count(*) from sampledb.login_dao as l where l.email=:#{#loginDao.email}",nativeQuery = true)
    Long findByEmail(LoginDao loginDao);


    Optional<LoginDao> findByUserid(String username);
}
