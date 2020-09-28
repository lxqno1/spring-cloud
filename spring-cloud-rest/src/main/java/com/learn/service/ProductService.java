package com.learn.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("service-provider")
public interface ProductService {

    @GetMapping("/product")
    public List findProductList();

}
