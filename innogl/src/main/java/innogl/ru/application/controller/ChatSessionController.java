package innogl.ru.application.controller;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    @CrossOrigin(originPatterns = {"*"})
    @PostMapping("/chat-session")
    public ResponseEntity<NewChatDTO> createOrFindChat(@RequestBody(required = false) RegisterChatDTO registerDTO) {
        return ResponseEntity.ok(chatSessionService.createOrFindChatSession(registerDTO));
    }

    @DeleteMapping("/chat-session/{chatId}")
    public void closeChat(@PathVariable UUID chatId) {

    }

    @GetMapping("/topics")
    public ResponseEntity<List<String>> getTopics() {
        return ResponseEntity.ok(chatSessionService.getTopics());
    }

}
