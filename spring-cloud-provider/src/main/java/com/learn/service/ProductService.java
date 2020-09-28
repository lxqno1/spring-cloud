package com.learn.service;

import com.learn.domain.Product;

import java.util.List;

public interface ProductService {

    List findProducts();

    void saveProductList();

    Product findProductById(Integer id);

    List findByIdIn(List<Integer> idList);
}
