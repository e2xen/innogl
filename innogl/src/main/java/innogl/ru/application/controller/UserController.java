package innogl.ru.application.controller;

import innogl.ru.application.dto.UserCredentialDTO;
import innogl.ru.application.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserCredentialService service;

    @CrossOrigin(originPatterns = {"*"})
    @PostMapping
    ResponseEntity<UserCredentialDTO> createNewUser() {
        return ResponseEntity.ok(service.newUser());
    }
}
