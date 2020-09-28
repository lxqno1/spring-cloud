package com.learn.service;

import com.learn.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-provider", fallbackFactory = ProductServiceFallbackFactory.class)//fallback =ProductServiceFallback.class
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    @GetMapping("/product")
    List  findProductList();

    /**
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    @GetMapping("/product/listByIds")
    List<Product> selectProductListByIds(@RequestParam("id") List<Integer> ids);

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @GetMapping("/product/listById/{id}")
    Product selectProductById(@PathVariable("id") Integer id);
}
