package fitnessapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WorkoutWebSocketController {

    /**
     * Broadcasts workout updates to all connected clients.
     * @param update The workout update message.
     * @return The update to broadcast.
     */
    @MessageMapping("/workout")
    @SendTo("/topic/workout-updates")
    public String broadcastWorkoutUpdate(String update) {
        return update;
    }
}