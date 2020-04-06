package haik.demo.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByFirstName(String firstName);

    //  Spørring for å hente ut navnet på sjåfør ved å joine User og Ride - tabellen vha. user_id.
    @Query(nativeQuery = true, value = "select * from user join ride on ride.createdById = user.user_id and user.user_id")
    Set<User> getNameOfDriver();

    @Query(nativeQuery = true, value = "SELECT user_id FROM user")
    Set<User> findByUser_id();
}
