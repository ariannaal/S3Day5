package S3D5.repositories;

import S3D5.entities.Event;
import S3D5.entities.Reservation;
import S3D5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    int countByEvent(Event event);

    boolean existsByEventAndUser(Event event, User user);

    List<Reservation> findByUser(User user);

}
