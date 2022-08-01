package africa.semicolon.mogbo.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
@Document("User")

public class User {
    @Id
    private String id;
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    public String password;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    @DBRef
    private List<Event> events = new ArrayList<>();

}
