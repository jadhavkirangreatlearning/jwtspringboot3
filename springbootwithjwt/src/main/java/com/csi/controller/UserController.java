package com.csi.controller;

import com.csi.dto.AuthRequest;
import com.csi.entity.User;
import com.csi.service.UserInfoDetailService;
import com.csi.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
//@CrossOrigin(origins = "http://localhost:9002", allowCredentials = "true")
@Tag(name = "Auth", description = "API Of signin request ")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserInfoDetailService userInfoDetailService;

    @PostMapping("/authenticate")
    @Operation(summary = "Sign In", description = "Signin using userName and password. Click on Authorized button and paste the token for accessing GST Controller",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<String> authenticateUser(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword()));
        return ResponseEntity.ok(jwtUtil.generateToken(authRequest.getUserName()));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return ResponseEntity.ok(userInfoDetailService.save(user));
    }
}
