package com.zjl.spring_boot_web.controller;

import com.zjl.spring_boot_web.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @className TestController
 * @descrption TODO
 * @date 2022/9/5 11:50
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/test")
public class TestController {


    private TestService testBakService;

}
