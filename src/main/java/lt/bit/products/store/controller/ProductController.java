package lt.bit.products.store.controller;

import java.util.List;
import lt.bit.products.store.model.Product;
import lt.bit.products.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProductController.ROOT_MAPPING)
class ProductController {

  public static final String ROOT_MAPPING = "/products";
  private final ProductService service;

  ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  List<Product> fetchProducts() {
    return service.findProducts();
  }

  @GetMapping("/{id}")
  ResponseEntity<Product> fetchProduct(@PathVariable Integer id) {
    Product product = service.findProduct(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  void deleteProduct(@PathVariable("id") Integer productId) {
    service.deleteProduct(productId);
  }
}
