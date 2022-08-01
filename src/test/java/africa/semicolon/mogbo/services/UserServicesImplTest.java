package africa.semicolon.mogbo.services;

import africa.semicolon.mogbo.data.repositories.UserRepository;
import africa.semicolon.mogbo.dto.Request.RegisterUserRequest;
import africa.semicolon.mogbo.exceptions.DuplicateEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
private UserServices userServices;
    @Test
     public void registerUserTest()  {
        thugLife();
        assertEquals(1,userRepository.count());
     }

    private void thugLife() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("thug@gmail.com");
        registerUserRequest.setFirstName("deji");
        registerUserRequest.setLastName("dammy");
        registerUserRequest.setPassword("damilola");
        userServices.registerUser(registerUserRequest);
    }

    @Test

        public void duplicateEmailThrowsExceptionTest(){
            thugLife();
            assertThrows(DuplicateEmailException.class, this::thugLife);
            try {
                thugLife();
            }catch (DuplicateEmailException ex){
                assertEquals("email exist", ex.getMessage());
            }
        }
     }
