package fr.gharrowbm.occhatopbackend.controllers;

import fr.gharrowbm.occhatopbackend.models.BaseExceptionResponse;
import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.ChatopMessagePostRequestDTO;
import fr.gharrowbm.occhatopbackend.services.ChatopMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final ChatopMessageService chatopMessageService;

    public final static String BASE_PATH = "/api/messages";

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message posted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseMessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseExceptionResponse.class))),
    })
    @Operation(summary = "Post a message about a rental by a specified user")
    @PostMapping(BASE_PATH)
    public ResponseEntity<BaseMessageResponse> postMessage(@RequestBody ChatopMessagePostRequestDTO dto) {
        return ResponseEntity.ok(chatopMessageService.postMessage(dto));
    }
}
