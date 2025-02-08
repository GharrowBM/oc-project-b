package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.ChatopMessagePostRequestDTO;

import java.util.List;

public interface ChatopMessageService {
    BaseMessageResponse postMessage(ChatopMessagePostRequestDTO dto);
}
