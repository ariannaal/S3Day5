package S3D5.repositories;

import S3D5.entities.Event;
import S3D5.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    int countByEvent(Event event);

}
