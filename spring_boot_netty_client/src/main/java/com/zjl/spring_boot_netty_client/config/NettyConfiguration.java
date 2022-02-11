package com.zjl.spring_boot_netty_client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhou
 * @version 1.0
 * @className NettyConfiguration
 * @description
 * @date 2022/02/10 15:27
 **/
@Data
@Component
@ConfigurationProperties(prefix = "netty.server")
public class NettyConfiguration {

    private String host;

    private Integer port;

    private Integer size;
}
