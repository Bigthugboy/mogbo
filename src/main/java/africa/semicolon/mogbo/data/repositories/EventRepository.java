package africa.semicolon.mogbo.data.repositories;

import africa.semicolon.mogbo.data.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event,String> {
}
