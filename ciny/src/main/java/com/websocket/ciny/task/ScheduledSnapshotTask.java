package com.websocket.ciny.task;

import com.websocket.ciny.constant.Const;
import com.websocket.ciny.model.Transaction;
import com.websocket.ciny.util.JsonUtils;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ScheduledSnapshotTask {

    private ScheduledSnapshotTask(){}

    private Set<String> branchWhiteList = null;

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

    public List<Transaction> snapshot(String filePath){
        // 1. Convert txt content to json object.
        String fileContent = JsonUtils.getFileContent(filePath);
        if (null == fileContent)
        {
            return null;
        }

        // 2. Parse json to get target branch data.
        JSONArray branchDatas = JsonUtils.parseBranchData(Const.RRT_VIEW_KEY_BRANCH,
                Const.RRT_VIEW_KEY_BRANCH_DATA, fileContent);
        if (null == branchDatas)
        {
            return null;
        }

        // 3. Return transactions.
        return JsonUtils.getTargetBranchData(branchDatas);
    }
}
