package com.learn.service;


import com.learn.domain.Product;

import java.util.List;
import java.util.concurrent.Future;

public interface OrderService {

    public List findProductList();

    public List<Product> findProductList2(List<Integer> ids);

    Future<Product> selectProductById(Integer id);

    Product selectProductById2(Integer id);
}
