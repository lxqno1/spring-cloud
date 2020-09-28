package com.learn.respository;

import com.learn.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPository  extends JpaRepository<Product,Long>{

        Product findByProductName(String name);
}
