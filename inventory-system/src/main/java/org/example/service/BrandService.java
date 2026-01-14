package org.example.service;

import org.example.model.Brand;
import org.example.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository repo;

    public BrandService(BrandRepository repo) {
        this.repo = repo;
    }

    public Brand save(Brand brand) {
        return repo.save(brand);
    }

    public List<Brand> getAll() {
        return repo.findAll();
    }

    public long count() {
        return repo.count();
    }
}
