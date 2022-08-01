package africa.semicolon.mogbo.services;

import africa.semicolon.mogbo.data.models.Event;
import africa.semicolon.mogbo.data.models.User;
import africa.semicolon.mogbo.data.repositories.UserRepository;
import africa.semicolon.mogbo.dto.Request.CreatePartyRequest;
import africa.semicolon.mogbo.dto.Request.LoginRequest;
import africa.semicolon.mogbo.dto.Response.CreateEventResponse;
import africa.semicolon.mogbo.dto.Response.LoginUserResponse;
import africa.semicolon.mogbo.dto.Request.RegisterUserRequest;
import africa.semicolon.mogbo.dto.Response.RegisterUserResponse;

import africa.semicolon.mogbo.exceptions.DuplicateEmailException;
import africa.semicolon.mogbo.exceptions.InvalidDetailsException;
import africa.semicolon.mogbo.exceptions.UserDoesNotExistException;
import africa.semicolon.mogbo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    private UserRepository userRepository;

    private EventServices eventServices;


    public UserServiceImpl(@Autowired UserRepository userRepository,@Autowired EventServices eventServices) {
        this.userRepository = userRepository;
        this.eventServices = eventServices;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateEmailException(request.getEmail() + " already exists");

//        Optional<User> userOptional = userRepository.findByEmail(request.getEmail()).getEmail();
//        if (userOptional.isPresent()) throw new DuplicateEmailException(request.getEmail() + " already exists")

        User user = new User();
        Mapper.map(request, user);
        User savedUser = userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        Mapper.map(savedUser, response);
        return response;
    }

//    @Override
//    public LoginUserResponse login(LoginRequest loginRequest) {
//        return null;
//    }


    @Override
    public LoginUserResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        LoginUserResponse response = new LoginUserResponse();
        if (user.getPassword().equals(request.getPassword())) {
            response.setText("Logged in successfully");
            return response;
        }
        throw new InvalidDetailsException("Invalid login details");
    }

    @Override
    public CreateEventResponse addParty(CreatePartyRequest request) {
        Optional<User> optionalUser = userRepository.findUserByEmail(request.getEmail());
        if (optionalUser.isEmpty()) throw new UserDoesNotExistException(request.getEmail() + " does not exist");
        User foundUser = optionalUser.get();
        Event event = new Event();
        event.setLocation(request.getPartyLocation());
        event.setName(request.getPartyName());
        Event savedEvent = eventServices.saveEvent(event);
        foundUser.getEvents().add(savedEvent);
        userRepository.save(foundUser);


        CreateEventResponse response = new CreateEventResponse();
        response.setPartyLocation(savedEvent.getLocation());
        response.setCreatedBy(foundUser.getFirstName());
        response.setPartyName(savedEvent.getName());
        return response;
    }


}



