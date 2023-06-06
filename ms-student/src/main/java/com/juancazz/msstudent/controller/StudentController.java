package com.juancazz.msstudent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    @GetMapping(path = "/anonymous")
    public ResponseEntity<Object> getAnonymousMessage(){

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Only for anonymous");

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/role")
    @PreAuthorize("hasRole('ROLE_user') or hasRole('ROLE_app_user')")
    public ResponseEntity<Object> getRoleUserMessage(){

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Only for users");

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/scope")
    @PreAuthorize("hasAuthority('SCOPE_email')")
    public ResponseEntity<Object> getScopeEmailMessage(){

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Only for email scope");

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/admin")
    @PreAuthorize("hasRole('ROLE_admin') and hasRole('ROLE_app_admin')")
    public ResponseEntity<Object> getRoleAdminMessage(){

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Only for admin roles");

        return ResponseEntity.ok(body);
    }

}