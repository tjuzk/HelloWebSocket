package com.websocket.ciny.task;

import com.websocket.ciny.constant.Const;
import com.websocket.ciny.model.Transaction;
import com.websocket.ciny.util.ConfUtils;
import com.websocket.ciny.util.JsonUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduledSnapshotTask {

    private ScheduledSnapshotTask(){}

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledSnapshotTask.class);

    /**
     * @Author: John
     * @Description: Get snapshot task instance.
     * @Date: 13:38 2018/3/18
     */
    public static ScheduledSnapshotTask getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;
        private ScheduledSnapshotTask singleton;
        Singleton(){
            singleton = new ScheduledSnapshotTask();
        }
        public ScheduledSnapshotTask getInstance(){
            return singleton;
        }
    }

    public List<Transaction> snapshot(){

        LOGGER.info("RRT view snapshot start");

        // 0. Get src file path
        String filePath = ConfUtils.getSrcFilePath();

        // 1. Convert txt content to json object.
        String fileContent = JsonUtils.getFileContent(filePath);
        if (null == fileContent)
        {
            LOGGER.error("File content is invalid!");
            LOGGER.info("RRT view snapshot end");
            return new ArrayList<>(0);
        }

        // 2. Parse json to get target branch data.
        JSONArray branchDatas = JsonUtils.parseBranchData(Const.RRT_VIEW_KEY_BRANCH,
                Const.RRT_VIEW_KEY_BRANCH_DATA, fileContent);
        if (null == branchDatas)
        {
            LOGGER.error("Branch datas is not found!");
            LOGGER.info("RRT view snapshot end");
            return new ArrayList<>(0);
        }

        // 3. Return transactions.
        List<Transaction> transactions = JsonUtils.getTargetBranchData(ConfUtils.getBranchWhiteList(), branchDatas);
        LOGGER.info("Target transactions is " + transactions.toString());
        LOGGER.info("RRT view snapshot end");
        return transactions;
    }
}
