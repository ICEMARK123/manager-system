package com.ice.manager.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ice.manager.system.model.UserAccess;
import com.ice.manager.system.service.UserService;
import com.ice.manager.system.uitils.Base64DecoderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

/**
 * @ Author     ：lhl.
 * @ Date       ：Created in 14:51 2024/4/25
 * @ Description：user
 * @ Modified By：
 * @Version:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{resource}")
    public ResponseEntity<?> checkUserAccess(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String resource) {
        UserAccess decodedUserAccess = null;
        try {
            decodedUserAccess = Base64DecoderUtil.decodeHeader(authorizationHeader);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Parsing the header information failed.", HttpStatus.UNAUTHORIZED);
        }
        if (userService.hasAccess(decodedUserAccess.getUserId(), resource)) {
            return new ResponseEntity<>("Access granted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);
        }
    }

}
