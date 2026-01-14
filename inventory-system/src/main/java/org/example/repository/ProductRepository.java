package org.example.repository;

import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("""
        SELECT p.brand.name, COUNT(p)
        FROM Product p
        GROUP BY p.brand.name
    """)
    List<Object[]> brandSummary();


    @Query("""
        SELECT 
            CASE 
                WHEN LOWER(p.category) LIKE '%phone%' THEN 'Phone'
                WHEN LOWER(p.category) LIKE '%laptop%' THEN 'Laptop'
                WHEN LOWER(p.category) LIKE '%desktop%' THEN 'Desktop'
                ELSE 'Other'
            END,
            COUNT(p)
        FROM Product p
        GROUP BY 
            CASE 
                WHEN LOWER(p.category) LIKE '%phone%' THEN 'Phone'
                WHEN LOWER(p.category) LIKE '%laptop%' THEN 'Laptop'
                WHEN LOWER(p.category) LIKE '%desktop%' THEN 'Desktop'
                ELSE 'Other'
            END
    """)
    List<Object[]> categorySummary();
}