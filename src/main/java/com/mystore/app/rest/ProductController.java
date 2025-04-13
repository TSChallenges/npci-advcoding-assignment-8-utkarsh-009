package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }


    // Old
    // @GetMapping("")
    // public List<Product> getAllProducts() {
    //     return productService.getAllProducts();
    // }

    // Updated
    @GetMapping("")
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return productService.getAllProducts(page, pageSize, sortBy, sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO: API to search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParams("name") String name) {
        List<Product> results = productService.searchProductsByName(name);
        return ResponseEntity<>(results, HttpStatus.OK);
    }

    // TODO: API to filter products by category
    @GetMapping("/filter/category")
    public ResponseEntity<List<Product>> searchByCategory(@RequestParams("category") String category) {
        List<Product> results = productService.searchByCategory(category);
        return ResponseEntity<>(results, HttpStatus.OK);
    }

    // TODO: API to filter products by price range
    @GetMapping("/products/filter/price")
    public ResponseEntity<List<Product>> filterByPriceRange(@RequestParams("minPrice") Double minPrice, @RequestParams("maxPrice") Double maxPrice) {
        List<Product> results = productService.filterByCategory(minPrice, maxPrice);
        return ResponseEntity<>(results, HttpStatus.OK);
    }

    // TODO: API to filter products by stock quantity range
    @GetMapping("/product/filter/stock")
    public ResponseEntity<List<Product>> filterByStockQuantityRange(@RequestParams("minStock") Integer minStock, @RequestParams("maxStock") Integer maxStock) {
        List<Product> results = productService.filterByStockQuantityRange(minStock, maxStock);
        return ResponseEntity<>(results, HttpStatus.OK);
    }
}
