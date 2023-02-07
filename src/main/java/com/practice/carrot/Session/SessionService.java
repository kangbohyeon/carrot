package com.practice.carrot.Session;


import com.practice.carrot.Util.GetClientip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Service
public class SessionService {
    Logger logger = LogManager.getLogger(SessionService.class);

    @Autowired
    SessionRespository sessionRespository;


    public String sessioninsert(HttpServletRequest request, HttpServletResponse response, Long keys) {
        SessionDao sessionDao = new SessionDao();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 16;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        sessionDao.setId(keys);
        sessionDao.setSession(generatedString);
        sessionRespository.save(sessionDao);
        logger.debug("ip : " + GetClientip.getClientIp(request));
        logger.info("session : " + sessionDao.toDto().getSession());
        return "session : " + sessionDao.toDto().getSession();
    }

}
