package com.ecomv2.productcatalog.service;


import com.ecomv2.productcatalog.entity.Product;

import java.util.List;


public interface ProductService {
    public List<Product> getAllProduct();
    public List<Product> getAllProductByCategory(String category);
    public Product getProductById(Long id);
    public Product getProductByProductCode(String productCode);
    public List<Product> getAllProductsByName(String name);
    public Product addProduct(Product product);
    Product updateProduct(Product product);
    public void deleteProduct(Long productId);
}

