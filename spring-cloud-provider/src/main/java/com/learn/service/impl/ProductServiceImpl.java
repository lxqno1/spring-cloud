package com.learn.service.impl;

import com.learn.domain.Product;
import com.learn.repository.ProductPository;
import com.learn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductPository productPository;

    public List findProducts(){
        return productPository.findAll();
    }

    public void saveProductList(){
        List<Product> productList = new ArrayList<Product>();
        Product product1 = new Product(1,"漫画",10,33d);
        Product product2 = new Product(2,"绘本",20,33d);
        Product product3 = new Product();
        product3.setProductName("漫画");
        productPository.save(product3);
       // productPository.save(product2);

    }

    public Product findProductById(Integer id){
         return productPository.findById(id);
    }

    public List findByIdIn(List<Integer> idList){
        Integer[] ids = new Integer[idList.size()];
        return productPository.findByIdIn(ids);
    }
}
