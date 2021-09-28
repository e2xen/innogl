package innogl.ru.application.controller;

import innogl.ru.application.annotation.AuthorizedAction;
import innogl.ru.application.model.AnonymousRegistration;
import innogl.ru.application.repository.AnonymousRegistrationRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AnonymousRegistrationController {

    private final AnonymousRegistrationRepository anonymousRegistrationRepository;

    public AnonymousRegistrationController(AnonymousRegistrationRepository anonymousRegistrationRepository) {
        this.anonymousRegistrationRepository= anonymousRegistrationRepository;
    }

    @PostMapping("/session/")
    public AnonymousRegistration registerUser(@RequestParam(required = false) String topic, HttpSession session) {
        return anonymousRegistrationRepository.save(new AnonymousRegistration(session.getId(), topic));
    }

    @AuthorizedAction
    @DeleteMapping("/session/")
    public void unregisterUser(@RequestParam UUID uuid, HttpSession session) {

    }

}
