package com.learn.controller;

import com.learn.domain.Product;
import org.springframework.web.bind.annotation.*;

import com.learn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

        @Autowired
        ProductService productService;

        @GetMapping
        public ResponseEntity findProducts(){
           List<Product> list = productService.findProducts();
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @GetMapping("/listById/{id}")
        public ResponseEntity findProductById(@PathVariable Integer id){
            Product product = productService.findProductById(id);
            return new ResponseEntity(product, HttpStatus.OK);
        }

        @GetMapping("/listByIds")
        public ResponseEntity findProductById(@RequestParam("id") List<Integer> ids){
            List<Product> list = productService.findByIdIn(ids);
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @PostMapping("/save")
        public ResponseEntity saveProducts(){
             productService.saveProductList();
            return new ResponseEntity(HttpStatus.OK);
        }
}
