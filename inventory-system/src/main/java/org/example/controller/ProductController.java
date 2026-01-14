package org.example.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.dto.CategorySummaryDTO;
import org.example.dto.ProductRequest;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public Product save(@RequestBody ProductRequest req) {
        return productService.save(req);
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }



    @GetMapping("/count")
    public long count() {
        return productService.count();
    }


    @GetMapping("/brand-summary")
    public List<Map<String, Object>> brandSummary() {

        return productService.brandSummary()
                .stream()
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("brand", row[0]);
                    map.put("count", row[1]);
                    return map;
                })
                .toList();
    }


    @GetMapping("/category-summary")
    public List<CategorySummaryDTO> categorySummary() {

        return productService.categorySummary()
                .stream()
                .map(row -> new CategorySummaryDTO(
                        (String) row[0],
                        (Long) row[1]
                ))
                .toList();
    }
}