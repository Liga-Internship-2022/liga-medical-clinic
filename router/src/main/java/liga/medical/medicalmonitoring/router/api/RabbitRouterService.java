package liga.medical.medicalmonitoring.router.api;

public interface RabbitRouterService {
    void routeMessage(String message);
}
