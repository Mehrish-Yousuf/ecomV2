package com.ecomv2.productcatalog.repository;

import com.ecomv2.productcatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByCategory(String category);
    public List<Product> findAllByProductName(String name);
    Product findByProductCode(String productCode);
}
