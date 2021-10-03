package innogl.ru.application.controller;

import innogl.ru.application.annotation.AuthorizedAction;
import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    @PostMapping("/chat-session")
    public ResponseEntity<NewChatDTO> registerChat(@RequestBody(required = false) RegisterChatDTO registerDTO, HttpSession session) {
        return ResponseEntity.ok(chatSessionService.createChatSession(registerDTO, session.getId()));
    }

    @AuthorizedAction
    @DeleteMapping("/chat-session")
    public void closeChat(@RequestParam UUID uuid, HttpSession session) {

    }



}
