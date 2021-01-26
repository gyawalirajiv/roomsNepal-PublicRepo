package com.gyawalibros.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        String message = "";

        if (exception.getMessage().equalsIgnoreCase("Bad Credentials")) {
            message = "Invalid Username or password";
        } else if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            message = "Account is not activated. Please check your mail.";
        } else if (exception.getClass() == UsernameNotFoundException.class) {
            message = "User doesn't extst";
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, message);
        getRedirectStrategy().sendRedirect(request, response, "/login?error=true");
    }
}
