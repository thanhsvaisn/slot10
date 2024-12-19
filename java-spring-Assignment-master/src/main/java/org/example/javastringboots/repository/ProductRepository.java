package org.example.javastringboots.repository;

import org.example.javastringboots.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% OR p.price = :price")
    List<Product> findAllByNameOrPrice(String name, Double price);// name = name or price = price

    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.price = :price")
    List<Product> findAllByNameAndPrice(@Param("name") String name, @Param("price") Double price);

    // filter
    @Query("SELECT p from Product p"+
            " WHERE (:name is NULL or p.name LIKE %:name%)"+
            " AND (:minPrice is NULL or p.price >= :minPrice)"+
            " AND (:maxPrice is NULL or p.price <= :maxPrice)"
    )
    List<Product> filter(@Param("name") String name, @Param("minPrice") Double minPrice,
                         @Param("maxPrice") Double maxPrice);
}
