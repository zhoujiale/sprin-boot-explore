package com.zjl.spring_boot_rocketmq.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @classname: SpringPullConsumer
 * @author: zhou
 * @description: 拉取
 * @date: 2023/5/4 15:37
 */
@Component
@RequiredArgsConstructor
public class SpringPullConsumer implements CommandLineRunner {

    private final RocketMQTemplate rocketMQTemplate;

    private final DefaultLitePullConsumer defaultLitePullConsumer;

    @Override
    public void run(String... args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 3600, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<String> receive = rocketMQTemplate.receive(String.class);
                if (CollectionUtils.isNotEmpty(receive)){
                    for (String s : receive) {
                        System.out.println(s);

                    }
                }
            }
        });
    }
}
