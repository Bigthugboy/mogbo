package africa.semicolon.mogbo.services;

import africa.semicolon.mogbo.dto.Request.CreatePartyRequest;
import africa.semicolon.mogbo.dto.Response.CreateEventResponse;
import africa.semicolon.mogbo.dto.Response.LoginUserResponse;
import africa.semicolon.mogbo.dto.Request.RegisterUserRequest;
import africa.semicolon.mogbo.dto.Response.RegisterUserResponse;
import africa.semicolon.mogbo.dto.Request.LoginRequest;

public interface UserServices {
        RegisterUserResponse registerUser(RegisterUserRequest request);
       // LoginUserResponse login(LoginRequest loginRequest);

    LoginUserResponse loginUser(LoginRequest request);

    CreateEventResponse addParty(CreatePartyRequest request);


}


