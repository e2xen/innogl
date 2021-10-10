package innogl.ru.application.controller;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.service.ChatSessionApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatSessionController {

    private final ChatSessionApiService chatSessionService;

    @CrossOrigin(originPatterns = {"*"})
    @PostMapping("/chat-session")
    public ResponseEntity<NewChatDTO> createOrFindChat(@RequestBody RegisterChatDTO registerDTO) {
        return ResponseEntity.ok(chatSessionService.createOrFindChatSession(registerDTO));
    }

    @GetMapping("/topics")
    public ResponseEntity<List<String>> getTopics() {
        return ResponseEntity.ok(chatSessionService.getTopics());
    }

}
