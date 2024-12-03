package org.example.msapiproducts.controller;

import org.example.business.model.Product;
import org.example.msapiproducts.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getById(@PathVariable("id") long id) throws Exception {
        return this.productService.findById(id);
    }

    @PostMapping("/product/{id}/name")
    public Product updateName(@PathVariable("id") long id, @RequestBody Product product) throws Exception {
        return this.productService.updateName(id, product.getName());
    }

}
