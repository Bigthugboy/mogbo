package africa.semicolon.mogbo.controllers;

import africa.semicolon.mogbo.dto.Request.CreatePartyRequest;
import africa.semicolon.mogbo.dto.Response.ApiResponse;
import africa.semicolon.mogbo.dto.Request.LoginRequest;

import africa.semicolon.mogbo.dto.Request.RegisterUserRequest;
import africa.semicolon.mogbo.exceptions.DuplicateEmailException;
import africa.semicolon.mogbo.exceptions.UserDoesNotExistException;
import africa.semicolon.mogbo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserServices userServices;

    public UserController(@Autowired UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            var serviceResponse = userServices.registerUser(registerUserRequest);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (DuplicateEmailException ex){
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(userServices.loginUser(loginRequest), HttpStatus.OK);
        }
        catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/party")
    public ResponseEntity<?> createEvent(@RequestBody CreatePartyRequest createPartyRequest){
        try {
            var serviceResponse = userServices.addParty(createPartyRequest);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (UserDoesNotExistException ex){
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
