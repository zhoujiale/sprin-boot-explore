package com.github.zhoujiale.spring.boot.netty.server.config;

import java.util.concurrent.ThreadFactory;

/**
 * @author zhou
 * @version 1.0
 * @className NettyThreadGroup
 * @description
 * @date 2022/02/11 17:05
 **/
public class NettyThreadGroup implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
