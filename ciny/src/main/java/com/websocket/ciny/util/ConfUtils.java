package com.websocket.ciny.util;

import com.websocket.ciny.conf.ServiceConfig;
import com.websocket.ciny.constant.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ConfUtils {

    private static Set<String> branchWhiteList = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfUtils.class);

    public static String getSrcFilePath() {
        ServiceConfig conf = IOCUtils.getBean(ServiceConfig.class);
        if (null == conf)
        {
            LOGGER.error("Java bean ServiceConfig is invalid, src file path is not found!");
            return "";
        }
        String filePath = conf.getSrcFilePath();
        LOGGER.info("Src file path is " + filePath);
        return filePath;
    }

    public static Set<String> getBranchWhiteList() {
        if (null != branchWhiteList)
        {
            LOGGER.info("Branch white list is " + branchWhiteList.toString());
            return branchWhiteList;
        }

        ServiceConfig conf = IOCUtils.getBean(ServiceConfig.class);
        if (null == conf)
        {
            LOGGER.error("Java bean ServiceConfig is invalid, branch white list is not found!");
            branchWhiteList = new HashSet<String>(0);
            return branchWhiteList;
        }

        if (conf.isMock())
        {
            LOGGER.info("Application is under mock model, the default branch is 澳门");
            branchWhiteList = new HashSet<>(1);
            branchWhiteList.add(Const.BRANCH_WHITE_LIST_MOCK);
            LOGGER.info("Branch white list is " + branchWhiteList.toString());
            return branchWhiteList;
        }

        String branchFilter = conf.getBranchFilter();
        LOGGER.info("BranchFilter in conf.properties is " + branchFilter);
        if (!branchFilter.contains("|"))
        {
            branchWhiteList = new HashSet<>(1);
            branchWhiteList.add(branchFilter.trim());
            LOGGER.info("Branch white list is " + branchWhiteList.toString());
            return branchWhiteList;
        }

        String[] branches = branchFilter.split("\\|");
        branchWhiteList = new HashSet<>(branches.length);
        for (String branch : branches)
        {
            branchWhiteList.add(branch.trim());
        }
        LOGGER.info("Branch white list is " + branchWhiteList.toString());
        return branchWhiteList;
    }
}
