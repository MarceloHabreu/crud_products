package api.api_products.controller;

import api.api_products.entity.Product;
import api.api_products.repository.ProductRepository;
import api.api_products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository action;

    @GetMapping("/apiProducts")
    public List<Product> listProducts() {
        return action.findAll();
    }

    @PostMapping("/apiProducts")
    public ResponseEntity<?> addProduct(@RequestBody Product obj) {
        return service.addProduct(obj);
    }

    @PutMapping("/apiProducts/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product obj) {
        return service.updateProduct(id, obj);
    }

    @DeleteMapping("/apiProducts/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id);
    }

}
