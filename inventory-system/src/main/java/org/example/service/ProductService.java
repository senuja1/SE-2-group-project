package org.example.service;

import java.util.List;

import org.example.dto.ProductRequest;
import org.example.model.Brand;
import org.example.model.Product;
import org.example.repository.BrandRepository;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final BrandRepository brandRepo;

    public ProductService(ProductRepository productRepo, BrandRepository brandRepo) {
        this.productRepo = productRepo;
        this.brandRepo = brandRepo;
    }


    public Product save(ProductRequest req) {

        Brand brand = brandRepo.findById(req.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Product p = new Product();
        p.setName(req.getName());
        p.setQuantity(req.getQuantity());
        p.setPrice(req.getPrice());
        p.setCategory(req.getCategory());
        p.setBrand(brand);

        return productRepo.save(p);
    }


    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }


    public long count() {
        return productRepo.count();
    }


    public List<Object[]> brandSummary() {
        return productRepo.brandSummary();
    }


    public List<Object[]> categorySummary() {
        return productRepo.categorySummary();
    }
}