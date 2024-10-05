package api.api_products.service;

import api.api_products.entity.Message;
import api.api_products.entity.Product;
import api.api_products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private Message message;

    @Autowired
    private ProductRepository action;

    public ResponseEntity<?> addProduct(Product obj) {
        if (obj.getName().equals("")){
            message.setMessage("The name field must be filled in!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (obj.getPrice() <= 0) {
            message.setMessage("Enter a valid value in the price field!!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (obj.getQuantity() < 0) {
            message.setMessage("Enter a valid value in the quantity field!!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> updateProduct(Long id, Product newProduct) {
        // verify if the product exist
        Optional<Product> optionalProduct = action.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            existingProduct.setName(newProduct.getName());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setQuantity(newProduct.getQuantity());

            action.save(existingProduct);

            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID: " + id + " not found!!!");
        }
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        Optional<Product> optionalProduct = action.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            action.delete(existingProduct);

            return ResponseEntity.ok("Product successfully deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID: " + id + " not found!!!");
        }
    }

}
