package liga.medical.medicalmonitoring.router.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.router.model.RabbitMessageDto;

public interface RabbitSenderService {
    void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException;

    void sendError(String message);
}
