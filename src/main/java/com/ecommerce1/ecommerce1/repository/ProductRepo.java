package com.ecommerce1.ecommerce1.repository;

import com.ecommerce1.ecommerce1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
