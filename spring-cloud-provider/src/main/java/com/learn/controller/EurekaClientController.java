/**
 * Copyright 厦门中软海晟信息技术有限公司 版权所有 违者必究 2020
 */
package com.learn.springeurekaclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 *@author : lvxq(lvxq@hsit.com.cn)
 *@date: 2020/9/14
 */
@RestController
@RequestMapping("hi")
public class EurekaClientController {

    @GetMapping
    public String sayHiFromClientOne(@RequestParam(value="name")String name)
    {
                 System.out.println("provider微服务接口调入");
                return name + "：test11111111111111";
    }
}
