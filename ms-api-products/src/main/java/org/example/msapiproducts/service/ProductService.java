package org.example.msapiproducts.service;

import org.example.business.model.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> sample = new ArrayList<Product>(
            Arrays.asList(new Product(1, "Product A"),
                    new Product(2, "Product B"),
                    new Product(3, "Product C"))
    );

    @Cacheable(value = "product-by-id", key = "#id")
    public Optional<Product> findById(Long id) throws Exception {
        Thread.sleep(1500);
        return sample.stream().filter( x -> x.getId() == id).findFirst();
    }
    @CacheEvict(value = "product-by-id", key = "#id")
    public Product updateName(long id, String newName) throws Exception {
        Optional<Product> product = sample.stream().filter( x -> x.getId() == id).findFirst();
        if (product.isPresent()) {
            product.get().setName(newName);
            return product.get();
        } else {
            throw new Exception("Product not found");
        }
    }
}
