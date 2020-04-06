package haik.demo;

import haik.demo.ride.Ride;
import haik.demo.ride.RideRepository;
import haik.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    //  Sørger for tilgang til UserRepository
    @Autowired
    private UserRepository userRepository;

    //  Sørger for tilgang til RideRepository;
    @Autowired
    private RideRepository rideRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void shouldFindNull() {
        assertEquals(null, userRepository.findByEmail("test@test.com"));
    }

    @Test
    void shouldFindUser() {
        assertEquals("Mirdon", userRepository.findByEmail("mirdon_g@hotmail.com").getFirstName());
    }

    @Test
    void shoudFindByFirstName() {
        assertEquals("Mirdon", userRepository.findByFirstName("Mirdon").getFirstName());
    }

    @Test
    void shouldFindUserOneById() {
        assertEquals("Mirdon", userRepository.findById(1L).get().getFirstName());
    }

    @Test
    void getAvailableSeats() {
        List<Ride> ride = (List<Ride>) rideRepository.findAllBySeatsavailable(2);
        assertEquals("Ullevål Hageby", ride.get(0).getStartlocation());
    }

    @Test
    void shouldFindAllPassengersInARide() {
        Optional<Ride> ride = rideRepository.findById(3L);

        if (ride.isPresent()) {
            Ride r = ride.get();
            assertEquals(4, r.getPassengers().size());
        }

    }





}