package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.ChatopMessagePostRequestDTO;
import fr.gharrowbm.occhatopbackend.services.ChatopMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final ChatopMessageService chatopMessageService;

    public final static String BASE_PATH = "/api/messages";

    @PostMapping(BASE_PATH)
    public ResponseEntity<BaseMessageResponse> postMessage(@RequestBody ChatopMessagePostRequestDTO dto) {
        return ResponseEntity.ok(chatopMessageService.postMessage(dto));
    }
}
