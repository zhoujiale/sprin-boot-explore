package com.zjl.spring_boot_quartz.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className SelfJobPO
 * @description 任务模型
 * @date 2021/03/24 14:37
 **/

@Data
@Entity
@Table(name = "self_job")
@org.hibernate.annotations.Table(appliesTo = "self_job",comment = "任务模型")
@EntityListeners(AuditingEntityListener.class)
public class SelfJobPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id",nullable = false,columnDefinition = "bigint(20) comment '主键'")
    private Long jobId;
    @Column(name = "job_name",nullable = false,columnDefinition = "varchar(45) comment '任务名称'")
    private String jobName;
    @Column(name = "bean_name",nullable = false,columnDefinition = "varchar(45) comment 'java容器名称'")
    private String beanName;
    @Column(name = "cron_expression",nullable = false,columnDefinition = "varchar(100) comment 'cron表达式'")
    private String cronExpression;
    @Column(name = "params",nullable = false,columnDefinition = "varchar(100) default '' comment '参数'")
    private String params;
    @Column(name = "job_status",nullable = false,columnDefinition = "int(1) default 0 comment '任务状态'")
    private Integer jobStatus;
    @Column(name = "create_date",nullable = false,columnDefinition = "datetime default current_timestamp comment '创建时间'")
    private LocalDateTime createDate;
    @Column(name = "update_date",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp comment '更新时间'")
    private LocalDateTime updateDate;
}
