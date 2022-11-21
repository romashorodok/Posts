package com.example.server.controllers.configs.security;

import com.example.server.services.user.impls.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AuthComponent {
    @Autowired
    EntityManager entityManager;
    public boolean hasPermissionComment(int id) {
        Integer userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return (boolean) entityManager.
                createNativeQuery("SELECT EXISTS (SELECT * FROM comments where id=" + id + " and user_id=" + userId + ");").getSingleResult();
    }
    public boolean hasPermissionPost(int id) {
        Integer userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return (boolean) entityManager.
                createNativeQuery("SELECT EXISTS (SELECT * FROM posts where id=" + id + " and user_id=" + userId + ");").getSingleResult();
    }
}
