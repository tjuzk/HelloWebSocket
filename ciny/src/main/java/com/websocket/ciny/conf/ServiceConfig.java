package com.websocket.ciny.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "conf")
@PropertySource(value = "classpath:conf.properties")
public class ServiceConfig {

    @Value("${conf.srcFilePath}")
    private String srcFilePath;

    @Value("${conf.branchWhiteList}")
    private String branchWhiteList;

    public void setSrcFilePath(String srcFilePath) {
        this.srcFilePath = srcFilePath;
    }

    public void setBranchWhiteList(String branchWhiteList) {
        this.branchWhiteList = branchWhiteList;
    }

    public String getSrcFilePath() {
        return srcFilePath;
    }

    public List<String> getBranchWhiteList() {
        if (null == branchWhiteList)
        {
            return null;
        }
        else if (branchWhiteList.contains("|"))
        {
            return Arrays.asList(branchWhiteList.trim());
        }
        String[] branches = branchWhiteList.split("|");
        List<String> branchList = new ArrayList<>(branches.length);
        for (String branch : branches)
        {
            branchList.add(branch.trim());
        }
        return branchList;
    }
}
