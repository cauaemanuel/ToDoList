package com.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.todolist.entities.User;
import com.todolist.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String ServeltPath = request.getServletPath();

        if(ServeltPath.startsWith("/task/")){

            String authorization = request.getHeader("Authorization");
            String authEncode = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncode);

            String authString = new String(authDecode);

            String[] credential = authString.split(":");

            String username = credential[0];
            String password = credential[1];

            User usuario = repository.findByUsername(username);
            if(usuario == null){
                response.sendError(401);
            }

            BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), usuario.getPassword());

            if(passwordVerify.verified){
                request.setAttribute("userID", usuario.getID());
                filterChain.doFilter(request, response);
            } else{
                response.sendError(401);
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
