package org.example.controller;

import org.example.model.Brand;
import org.example.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    public Brand add(@RequestBody Brand brand) {
        return service.save(brand);
    }

    @GetMapping
    public List<Brand> getAll() {
        return service.getAll();
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }
}
