package com.github.zhoujiale.spring.boot.netty.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhou
 * @version 1.0
 * @className NettyConfiguration
 * @description
 * @date 2022/02/10 10:53
 **/
@Data
@Component
@ConfigurationProperties(prefix = "netty.server")
public class NettyConfiguration {

    private Integer port;
}
