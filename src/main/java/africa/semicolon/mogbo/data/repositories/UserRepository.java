package africa.semicolon.mogbo.data.repositories;

import africa.semicolon.mogbo.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String>{

    User findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);

}

