package com.websocket.ciny;

import com.websocket.ciny.task.ScheduledSnapshotTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class SnapshotController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/snapshot")
    public Object rrtViewSnapshot() throws Exception {
        messagingTemplate.convertAndSend("/topic/snapshot", ScheduledSnapshotTask.getInstance().snapshot());
        return "snapshot";
    }
}
