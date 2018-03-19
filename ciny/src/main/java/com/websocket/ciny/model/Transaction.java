package com.websocket.ciny.model;

public class Transaction {

    private String branchName;

    private EnumStatus branchStatus;

    private String serviceType;

    private double serviceStatus;

    private double clientTime;

    private EnumStatus clientStatus;

    private double netWorkTime;

    private EnumStatus networkStatus;

    private double sysProcTime;

    private EnumStatus sysProcStatus;

    private double transactionTime;

    private EnumStatus transactionStatus;

    public Transaction(String branchName, String branchStatus, String serviceType, double serviceStatus,
                       double clientTime, String clientStatus, double netWorkTime, String networkStatus,
                       double sysProcTime, String sysProcStatus, double transactionTime, String transactionStatus) {
        this.branchName = branchName;
        this.branchStatus = EnumStatus.getStatusById(Integer.parseInt(branchStatus));
        this.serviceType = serviceType;
        this.serviceStatus = serviceStatus;
        this.clientTime = clientTime;
        this.clientStatus = EnumStatus.getStatusById(Integer.parseInt(clientStatus));
        this.netWorkTime = netWorkTime;
        this.networkStatus = EnumStatus.getStatusById(Integer.parseInt(networkStatus));
        this.sysProcTime = sysProcTime;
        this.sysProcStatus = EnumStatus.getStatusById(Integer.parseInt(sysProcStatus));
        this.transactionTime = transactionTime;
        this.transactionStatus = EnumStatus.getStatusById(Integer.parseInt(transactionStatus));
    }

    public String getBranchName() {
        return branchName;
    }

    public EnumStatus getBranchStatus() {
        return branchStatus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public double getServiceStatus() {
        return serviceStatus;
    }

    public double getClientTime() {
        return clientTime;
    }

    public EnumStatus getClientStatus() {
        return clientStatus;
    }

    public double getNetWorkTime() {
        return netWorkTime;
    }

    public EnumStatus getNetworkStatus() {
        return networkStatus;
    }

    public double getSysProcTime() {
        return sysProcTime;
    }

    public EnumStatus getSysProcStatus() {
        return sysProcStatus;
    }

    public double getTransactionTime() {
        return transactionTime;
    }

    public EnumStatus getTransactionStatus() {
        return transactionStatus;
    }
}
