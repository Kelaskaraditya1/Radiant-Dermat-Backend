package com.StarkIndustries.RadientDermat.authentication.scheduler;

import com.StarkIndustries.RadientDermat.keys.Keys;
import jakarta.servlet.http.HttpSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(fixedRate = 3000000)
    public void clearSession(HttpSession httpSession){
        httpSession.removeAttribute(Keys.OTP);
    }

}
