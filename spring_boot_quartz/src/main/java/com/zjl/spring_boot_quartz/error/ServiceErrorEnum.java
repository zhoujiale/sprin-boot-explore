package com.zjl.spring_boot_quartz.error;

import lombok.Getter;

/**
 * @Auther: zhou
 * @Date: 2020-10-11 18:20
 * @Description: 从5000开始
 */
@Getter
public enum ServiceErrorEnum {

    UN_KNOWN_ACCOUNT("5000", "账号不存在"),
    ERROR_CREDENTIALS("5001", "账号或密码错误"),
    LOCK_ACCOUNT("5002", "账号被锁定"),
    AUTH_ERROR("5003", "认证异常"),
    NEED_LOGIN("5004","未登录或登录过期"),
    NEED_PERMISSION("5079","该账号没有这项操作权限"),
    ADD_ROLE_ERROR("5080", "添加角色异常"),
    OPERATE_VALID("5081", "非法的操作常量"),
    JOB_ID("5082", "缺少job_id"),
    JAVA_BEAN("5083", "缺少java_bean"),
    JOB_NAME("5084", "缺少任务名称"),
    CORN("5085","缺少cron表达式" ),
    LOG_EMPTY("5086", "请选择日志"),
    JOB_EMPTY("5087", "任务不存在"),
    ADD_JOB_ERROR("5087","添加定时任务失败"),
    JOB_IS_PAUSE("5088", "定时任务已经暂停"),
    JOB_IS_RUNNING("5089", "定时任务已经在运行"),
    MODIFY_JOB_ERROR("5090", "更新定时任务失败"),
    PAUSE_JOB_ERROR("5091", "暂停定时任务失败"),
    RESUME_JOB_ERROR("5092", "恢复定时任务失败"),
    DELETE_JOB_ERROR("5093", "删除定时任务失败"),
    EXEC_JOB_ERROR("5094", "执行定时任务失败")
    ;
    private String errCode;

    private String errMsg;

    ServiceErrorEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
