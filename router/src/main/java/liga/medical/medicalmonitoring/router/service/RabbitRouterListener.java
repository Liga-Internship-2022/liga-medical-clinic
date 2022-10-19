package liga.medical.medicalmonitoring.router.service;

import liga.medical.medicalmonitoring.router.api.RabbitRouterService;
import liga.medical.medicalmonitoring.router.model.QueueNames;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitRouterListener {

    private final RabbitRouterService rabbitRouterService;

    @RabbitListener(queues = QueueNames.ROUTER_QUEUE_NAME)
    public void receiveAndRedirectMessage(String message) {
        rabbitRouterService.routeMessage(message);
    }
}
