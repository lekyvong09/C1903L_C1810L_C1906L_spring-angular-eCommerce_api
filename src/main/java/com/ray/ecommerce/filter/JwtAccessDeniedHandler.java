package com.ray.ecommerce.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ray.ecommerce.domain.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        HttpResponse httpResponse = new HttpResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED,
                 HttpStatus.UNAUTHORIZED.getReasonPhrase(), "You do not have permission to access this page");

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }
}
