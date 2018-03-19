package com.websocket.ciny.model;

public enum EnumStatus {
    /*
     *  正常
     */
    NORMAL(1),

    /*
     *  异常
     */
    ABNORMAL(2),

    /*
     *  严重
     */
    SERIOUS(3);

    private final int id;

    EnumStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * @Author: John
     * @Description: Status getter by id.
     * @Date: 13:07 2018/3/18
     */
    public static EnumStatus getStatusById(int id) {
        for (EnumStatus enumStatus : EnumStatus.values())
        {
            if (enumStatus.getId() == id)
            {
                return enumStatus;
            }
        }
        return null;
    }

    /**
     * @Author: John
     * @Description: Status getter by string
     * @Date: 13:07 2018/3/18
     */
    public static EnumStatus getStatusByString(String status) {
        for (EnumStatus enumStatus : EnumStatus.values())
        {
            if (enumStatus.toString().toLowerCase().equals(status.toLowerCase()))
            {
                return enumStatus;
            }
        }
        return null;
    }
}
