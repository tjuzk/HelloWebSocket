package com.websocket.ciny.task;

import com.websocket.ciny.conf.ServiceConfig;
import com.websocket.ciny.constant.Const;
import com.websocket.ciny.model.Transaction;
import com.websocket.ciny.util.JsonUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ScheduledSnapshotTask {

    private ScheduledSnapshotTask(){}

    @Autowired
    private ServiceConfig config;

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
        // 0. Get file.
//        String filePath = config.getSrcFilePath();
        String filePath = "\\\\127.0.0.1\\data\\json_rrtView_28951.txt";

        // 1. Convert txt content to json object.
        String fileContent = JsonUtils.getFileContent(filePath);
        if (null == fileContent)
        {
            return null;
        }

        // 2. Parse json to get target branch data.
        JSONArray branchDatas = JsonUtils.parseBranchData(Const.RRT_VIEW_KEY_BRANCH, Const.RRT_VIEW_KEY_BRANCH_DATA, fileContent);
        if (null == branchDatas)
        {
            return null;
        }

        // 3. Return transactions.
//        List<String> branchWhiteList = config.getBranchWhiteList();
        List<String> branchWhiteList = new ArrayList<>();
        branchWhiteList.add("澳门");
        return JsonUtils.getTargetBranchData(branchWhiteList, branchDatas);
    }
}
