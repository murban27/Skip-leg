package fitnessapp.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * WebSocket endpoint for real-time workout updates, consumed by the React
 * Native mobile app. Contract: client sends to {@code /app/workout}, updates
 * broadcast on {@code /topic/workout-updates}.
 */
@Controller
public class WorkoutWebSocketController {

    /**
     * Broadcasts workout updates to all connected clients.
     *
     * @param update the workout update message
     * @return the update to broadcast
     */
    @MessageMapping("/workout")
    @SendTo("/topic/workout-updates")
    public String broadcastWorkoutUpdate(String update) {
        return update;
    }
}
