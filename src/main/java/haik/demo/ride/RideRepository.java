package haik.demo.ride;

import haik.demo.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface RideRepository extends CrudRepository<Ride, Long> {

    Iterable<Ride> findAllBySeatsavailable(int seats);

    Optional<Ride> findById(Long id);

    Iterable<Ride> findAllByDriver(User driver);


}
