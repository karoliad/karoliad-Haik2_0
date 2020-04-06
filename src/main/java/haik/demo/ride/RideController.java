package haik.demo.ride;

import haik.demo.user.User;
import haik.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;


@Controller
public class RideController {

    //  Oppretter kontakt med databasen
    @Autowired
    DataSource dataSource;

    //  Oppretter kontakt med RideRepository
    @Autowired
    RideRepository rideRepository;

    //  Oppretter kontakt med UserRepository
    @Autowired
    UserRepository userRepository;

    //  Metode for å opprette en ny Ha!k
    @GetMapping("/createride")
    public String newRide(Model model) {
        model.addAttribute("createRide", new Ride());

        return "createRide";
    }

    //  Metode for å lagre en Ha!ks til databasen.
    //  Bruker Principal for å aksessere brukeren som er innlogget vha. sessionId.
    //  Lagrer deretter brukeren som sjåfør.
    @PostMapping("/saveride")
    public String saveRide(@ModelAttribute Ride ride, Principal principal) {
        String email = principal.getName();
        ride.setDriver(userRepository.findByEmail(email));
        rideRepository.save(ride);

        return "redirect:/myrides";
    }

    //  Metode for å vise frem alle tilgjengelige Ha!ks
    @GetMapping("/rides")
    public String getRides(Model model) {
        Iterable<Ride> allRides = rideRepository.findAll();
        model.addAttribute("rides", allRides);
        return "rides";
    }

    //  Metode for å vise alle Ha!ks du som bruker er tilknyttet
    //  Bruker Principal for å aksessere brukeren som er innlogget vha. sessionId.
    //  Viser hvilke turer du kjører og hvilke turer du Ha!ker på
    @GetMapping("/myrides")
    public String showMyRides(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        Iterable<Ride> myRidesList = user.getRides();
        model.addAttribute("myrides", myRidesList);
        model.addAttribute("driverRides", rideRepository.findAllByDriver(user));

        return "myrides";
    }

    //  Metode hvor passasjer kan melde seg på en Ha!k
    //  Passasjeren sendes videre til en side for å bekrefte Ha!ken
    //  Bruker principal og PathVariable for å sikre at riktig bruker linkes mot riktig tur
    @GetMapping("/confirmride/{rideId}")
    public String confirmRide(Model model, Principal principal, @PathVariable Long rideId) {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);
        String email = principal.getName();
       User newPassenger = userRepository.findByEmail(email);

        Ride ride = null;
        if (optionalRide.isPresent()) {
            ride = optionalRide.get();
        }

        model.addAttribute("confirmride", ride);

        return "confirmride";
    }

    //  Metode for å lagre en passasjer til en Ha!k
    //  Bruker rideId og principal for å sikre at korrekt passasjer kobles mot korrekt tur
    @PostMapping("/saveUserToRide")
    public String addPassenger(@RequestParam String rideId, Principal principal) {
        String email = principal.getName();
        User newPassenger = userRepository.findByEmail(email);

        Long longRideId = Long.parseLong(rideId);
        Optional<Ride> optionalRide = rideRepository.findById(longRideId);

        Ride ride = null;
        if (optionalRide.isPresent()) {
            ride = optionalRide.get();
            ride.addPassenger(newPassenger);
            ride.setSeatsavailable(ride.getSeatsavailable() - 1);
        }

        rideRepository.save(ride);

        return "redirect:/myrides";
    }
}
