package com.learn.service;

import com.learn.domain.Product;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    // 获取日志，在需要捕获异常的方法中进行处理
    Logger logger = LoggerFactory.getLogger(ProductServiceFallbackFactory.class);

    @Override
    public ProductService create(Throwable throwable){
        return new ProductService() {
            @Override
            public List findProductList() {
               logger.error("调用服务provider方法异常"+throwable);
               return Arrays.asList(
                       new Product(1, "托底数据-华为手机", 1, 5800D),
                       new Product(2, "托底数据-联想笔记本", 1, 6888D),
                       new Product(3, "托底数据-小米平板", 5, 2020D)
               );
            }

            @Override
            public List<Product> selectProductListByIds(List<Integer> ids) {
                logger.error("调用服务provider方法异常"+throwable);
                List<Product> products = new ArrayList<>();
                ids.forEach(id -> products.add(new Product(id, "托底数据-电视机" + id, 1, 5800D)));
                return products;
            }

            @Override
            public Product selectProductById(Integer id) {
                logger.error("调用服务provider方法异常"+throwable);
                return new Product(id, "托底数据", 1, 2666D);
            }
        };
    }
 }
