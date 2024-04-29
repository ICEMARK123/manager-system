package com.ice.manager.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ice.manager.system.model.UserAccess;
import com.ice.manager.system.service.UserService;
import com.ice.manager.system.uitils.Base64DecoderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：lhl.
 * @ Date       ：Created in 14:51 2024/4/25
 * @ Description：admin
 * @ Modified By：lhl
 * @Version:
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUserAccess(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserAccess userAccess) {
        UserAccess decodedUserAccess = null; // Remove "Basic " prefix
        try {
            decodedUserAccess = Base64DecoderUtil.decodeHeader(authorizationHeader);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Parsing the header information failed.", HttpStatus.UNAUTHORIZED);
        }
        if (!"admin".equals(decodedUserAccess.getRole())) {
            return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
        }
        userService.addUserAccess(userAccess);
        return new ResponseEntity<>("User access added", HttpStatus.OK);
    }
}
