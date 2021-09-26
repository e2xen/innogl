package innogl.ru.application.controller;

import innogl.ru.application.model.AnonymousRegistration;
import innogl.ru.application.repository.AnonymousRegistrationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AnonymousRegistrationController {

    private final AnonymousRegistrationRepository anonymousRegistrationRepository;

    public AnonymousRegistrationController(AnonymousRegistrationRepository anonymousRegistrationRepository) {
        this.anonymousRegistrationRepository= anonymousRegistrationRepository;
    }

    @PostMapping("/form/register")
    public AnonymousRegistration registerUser(HttpSession session) {
        return anonymousRegistrationRepository.save(new AnonymousRegistration(session.getId()));
    }

    @PostMapping("/form/register-topic")
    public AnonymousRegistration registerTopic(String topic, HttpSession session) {
        return anonymousRegistrationRepository.save(new AnonymousRegistration(session.getId(), topic));
    }

}
