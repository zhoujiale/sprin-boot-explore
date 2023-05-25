package com.zjl.spring_boot_statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @classname: com.zjl.spring_boot_statemachine.SpringBootStateMachineApplication
 * @author: zhou
 * @description:
 * @date: 2023/5/23 13:57
 */
@EnableJpaAuditing
@SpringBootApplication
public class SpringBootStateMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStateMachineApplication.class, args);
    }
}
