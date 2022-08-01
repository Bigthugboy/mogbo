package africa.semicolon.mogbo.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
        private String email;
//        private String firstName;
//        private String lastName;
//        private String password;
        private String dateCreated;
        private String text;
//
//    @Data
//    public class RegisterUserRequest {
//        private String firstName;
//        private String lastName;
//        private String email;
//        private String password;
//    }
}

