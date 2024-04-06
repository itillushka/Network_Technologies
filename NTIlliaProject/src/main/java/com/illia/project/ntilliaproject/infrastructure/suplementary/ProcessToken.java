package com.illia.project.ntilliaproject.infrastructure.suplementary;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ProcessToken {

    public String getToken(HttpServletRequest request){
        //get token from header
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //remove Bearer from token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return token;
    }
}
