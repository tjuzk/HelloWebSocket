package com.websocket.ciny;

import com.websocket.ciny.model.Transaction;
import com.websocket.ciny.task.ScheduledSnapshotTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@EnableScheduling
public class SnapshotController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @Value("${conf.srcFilePath}")
//    private String srcFilePath;

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/snapshot")
    public Object rrtViewSnapshot() throws Exception {

        List<Transaction> transactions = ScheduledSnapshotTask.getInstance().snapshot();
        if (null != transactions)
        {
            messagingTemplate.convertAndSend("/topic/snapshot", transactions);
        }
        return "snapshot";
    }
}
