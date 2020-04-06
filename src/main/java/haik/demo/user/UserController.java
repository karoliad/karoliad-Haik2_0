package haik.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.sql.DataSource;
import java.security.Principal;

@Controller
public class UserController {

    //  Oppretter kontakt med databasen
    @Autowired
    DataSource dataSource;

    //  Oppretter kontakt med UserRepository
    @Autowired
    UserRepository userRepository;

    //  Oppretter kontakt med passordkrypteringen
    @Autowired
    PasswordEncoder encoder;

    //  Sender besøkende til introduksjonssiden når de entrer siden
    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }

    //  Redirecter brukere til forsiden når de logger ut
    @GetMapping("/logout")
    public String logOut() {

        return "rediret:/";
    }

    //  Side hvor brukere kan opprette ny profil
    @GetMapping("/register")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    //  Metode for å lagre den nyopprettede brukeren til databasen
    //  Tar inn @ModelAttribute for å linke User mot instansvariablene i User-klassen
    //  Sender bruker videre til innloggingsside
    @PostMapping("/register/successful")
    public String registerNewUser(@ModelAttribute User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }

    // Sender brukeren til innloggingssiden
    @GetMapping("/login")
    public String logIn() {

        return "login";
    }

    //  Menyside hvor brukeren kan velge ulike handlinger
    //  Bruker principan for å kunne identifisere hvilken bruker som er innlogget
    @GetMapping("/choosestatus")
    public String chooseStatus(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("user", userRepository.findByEmail(email));
        return "chooseStatus";
    }
}
