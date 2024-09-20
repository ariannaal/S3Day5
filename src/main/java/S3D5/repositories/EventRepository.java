package S3D5.repositories;


import S3D5.entities.Event;
import S3D5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByUser(User user);

}
