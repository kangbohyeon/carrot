package com.practice.carrot.session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRespository extends JpaRepository<SessionDao,Long> {
}
