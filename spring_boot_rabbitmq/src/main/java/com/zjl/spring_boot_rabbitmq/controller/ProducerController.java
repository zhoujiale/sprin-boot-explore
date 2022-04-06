package com.zjl.spring_boot_rabbitmq.controller;

import com.zjl.spring_boot_rabbitmq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @version 1.0
 * @className ProducerController
 * @description
 * @date 2021/07/12 13:04
 **/
@RestController
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping(value = "/delayInfo")
    public void delayInfo(@RequestParam(value = "info")String info){
        producerService.delayInfo(info);
    }

    @GetMapping(value = "/delayPlugin")
    public void delayPlugin(@RequestParam(value = "info")String info){
        producerService.delayPlugin(info);
    }

    @GetMapping(value = "/confirmInfo")
    public void confirmInfo(@RequestParam(value = "confirm")String confirm){
        producerService.confirmInfo(confirm);
    }
}
