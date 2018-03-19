package com.websocket.ciny.util;

import com.websocket.ciny.constant.Const;
import com.websocket.ciny.model.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String ENCODING = "UTF-8";

    public static String getFileContent(String filePath) {
        String dataTxt = null;
        File file = new File(filePath);
        if (null == file || !file.exists() || !file.isFile() || !file.canRead())
        {
            return dataTxt;
        }

        // try-with-resource structure
        try (FileInputStream fInputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fInputStream, ENCODING);
             BufferedReader bufferedReader = new BufferedReader(reader))
        {
            StringBuffer dataTxtBuffer = new StringBuffer();
            String lineTxt;
            while((lineTxt = bufferedReader.readLine()) != null)
            {
                dataTxtBuffer.append(lineTxt);
            }

            // 0. Get the whole txt content.
            dataTxt = dataTxtBuffer.toString();
            // 1. Delete all \r \n \s
            dataTxt = dataTxt.replaceAll("[\\n\\r\\s]", "");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return dataTxt;
    }

    public static JSONArray parseBranchData(String branchKey, String dataKey, String data) {
        if (null == data || data.isEmpty())
        {
            return null;
        }

        JSONObject rrtView = new JSONObject(data);
        JSONObject branchRRTStatus = rrtView.getJSONObject(branchKey);
        if (null == branchRRTStatus)
        {
            return null;
        }
        return branchRRTStatus.getJSONArray(dataKey);
    }

    public static List<Transaction> getTargetBranchData(JSONArray datas) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        if (null == datas)
        {
            return transactions;
        }
        for (int index = 0; index < datas.length(); index++)
        {
            JSONObject branchData = (JSONObject)datas.get(index);
            String branchName = branchData.getString(Const.BRANCH_DATA_KEY_NAME);
            if (Const.BRANCH_WHITE_LIST.equals(branchName))
            {
                Transaction targetTransaction = new Transaction(branchData.getString(Const.BRANCH_DATA_KEY_NAME),
                        branchData.getString(Const.BRANCH_DATA_KEY_STATUS), branchData.getString(Const.BRANCH_DATA_KEY_SERVICE_TYPE),
                        branchData.getDouble(Const.BRANCH_DATA_KEY_SERVICE_STATUS), branchData.getDouble(Const.BRANCH_DATA_KEY_CLIENT_TIME),
                        branchData.getString(Const.BRANCH_DATA_KEY_CLIENT_STATUS), branchData.getDouble(Const.BRANCH_DATA_KEY_NETWORK_TIME),
                        branchData.getString(Const.BRANCH_DATA_KEY_NETWORK_STATUS), branchData.getDouble(Const.BRANCH_DATA_KEY_SYS_PROC_TIME),
                        branchData.getString(Const.BRANCH_DATA_KEY_SYS_STATUS), branchData.getDouble(Const.BRANCH_DATA_KEY_TRANSACTION_TIME),
                        branchData.getString(Const.BRANCH_DATA_KEY_TRANSACTION_STATUS));
                transactions.add(targetTransaction);
            }
        }
        return transactions;
    }

//    public static void main(String[] args) {
//        String content = getFileContent("\\\\127.0.0.1\\data\\json_rrtView_28951.txt");
//
//        JSONArray datas = parseBranchData("branchRRTStatus", "data", content);
//
//        Set<String> list = new HashSet<>();
//        list.add("澳门");
//        List<Transaction> transactions = getTargetBranchData(list, datas);
//    }
}
