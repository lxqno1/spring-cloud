package com.learn.repository;

import com.learn.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductPository extends JpaRepository<Product,Long> {

        Product findByProductName(String name);

        List findByIdIn(Integer[] ids);

        Product  findById(Integer id);
}
