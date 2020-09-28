package com.learn.rest;

import com.learn.domain.Product;
import com.learn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;


    /**
     * 根据主键查询订单-调用商品服务 /product/list
     *
     * @return
     */
    @GetMapping("/list")
    public List findProductList() {
        return productService.findProductList();
    }

    /**
     * 根据主键查询订单-调用商品服务 /product/listByIds
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/product/listByIds")
    public Product selectProductById(@PathVariable("id") Integer id) {
        return productService.selectProductById(id);
    }



}
