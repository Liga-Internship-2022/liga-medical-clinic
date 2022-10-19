package liga.medical.medicalmonitoring.router.model;

import lombok.Data;

@Data
public class RabbitMessageDto {
    private MessageType messageType;
    private String content;
}
