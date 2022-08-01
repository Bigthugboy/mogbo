package africa.semicolon.mogbo.utils;

import africa.semicolon.mogbo.data.models.User;
import africa.semicolon.mogbo.dto.Request.RegisterUserRequest;
import africa.semicolon.mogbo.dto.Response.RegisterUserResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {

    public static void map(RegisterUserRequest request, User user){
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

    }
    public static void map(User savedUser, RegisterUserResponse response){
//        User savedUser = userRepository.save(user);
//        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(savedUser.getEmail());

        response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(savedUser.getDateRegistered()));

    }

}
