package com.learn.rest;

import com.learn.domain.Product;
import com.learn.service.OrderService;
import com.learn.service.ProductService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping
    public String findProducts(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException  e){
            e.printStackTrace();
        }
        List list =productService.findProductList();
        return list.toString();
    }

    @GetMapping("/rest")
    public String findProductByRest(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException  e){
            e.printStackTrace();
        }
        List list = new ArrayList(); //orderService.findProductList();
        return list.toString();
    }

    @GetMapping("/merge")
    public String findProductByMerge(){
        // 模拟同一时间用户发起多个请求。
        Future<Product> p1 = orderService.selectProductById(1);
        Future<Product> p2 = orderService.selectProductById(2);
        Future<Product> p3 = orderService.selectProductById(3);
        Future<Product> p4 = orderService.selectProductById(4);
        Future<Product> p5 = orderService.selectProductById(5);
        try {
            System.out.println(p1.get());
            System.out.println(p2.get());
            System.out.println(p3.get());
            System.out.println(p4.get());
            System.out.println(p5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "1111111111111";
    }


    @GetMapping("/test")
    public Product findProductBytest(@RequestParam String id){
        Product p1 = orderService.selectProductById2(Integer.parseInt(id));
        return p1;
    }

    @GetMapping("/test1")
    public Product findProductBytest1(@RequestParam String id){
        Product p1 = orderService.selectProductById2(Integer.parseInt(id));
        return p1;
    }
}
