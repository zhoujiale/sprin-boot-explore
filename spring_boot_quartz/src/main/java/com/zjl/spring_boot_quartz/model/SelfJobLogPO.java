package com.zjl.spring_boot_quartz.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className SelfJobLogPO
 * @description 任务日志模型
 * @date 2021/03/24 14:37
 **/
@Data
@Entity
@Table(name = "self_job_log")
@org.hibernate.annotations.Table(appliesTo = "self_job_log",comment = "任务日志")
@EntityListeners(AuditingEntityListener.class)
public class SelfJobLogPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id",nullable = false,columnDefinition = "bigint(20) comment '日志id'")
    private Long logId;
    @Column(name = "job_id",nullable = false,columnDefinition = "bigint(20) comment '任务id'")
    private Long jobId;
    @Column(name = "user_time",nullable = false,columnDefinition = "bigint(20) default 0 comment '耗时毫秒'")
    private Long userTime;
    @Column(name = "create_date",nullable = false,columnDefinition = "datetime default current_timestamp comment '开始执行时间'")
    private LocalDateTime createDate;
    @Column(name = "execute_status",nullable = false,columnDefinition = "int(11) default 1 comment '执行状态 0失败 1成功'")
    private Integer executeStatus;
    @Column(name = "error",columnDefinition = "varchar(500) default '' comment '错误'")
    private String error;
}
