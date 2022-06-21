package lt.bit.products.store.controller;

import java.util.List;
import lt.bit.products.store.model.Product;
import lt.bit.products.store.model.ProductItems;
import lt.bit.products.store.model.ProductRequest;
import lt.bit.products.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProductController.ROOT_MAPPING)
class ProductController {

  public static final String ROOT_MAPPING = "/products";
  public static final String ID_MAPPING = "/{id}";
  private final ProductService service;

  ProductController(ProductService service) {
    this.service = service;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  Product createProduct(@RequestBody ProductRequest productRequest) {
    return service.saveProduct(Product.from(productRequest));
  }

  @PutMapping(ID_MAPPING)
  ResponseEntity<Product> updateProduct(
      @RequestBody ProductRequest productRequest,
      @PathVariable Integer id) {
    Product product = service.findProduct(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(service.saveProduct(Product.from(productRequest, id)));
  }

  @GetMapping
// TODO: add boolean withItems (e.g. /products?withItems=true)
  List<Product> fetchProducts() {
    return service.findProducts();
  }

  @GetMapping(ID_MAPPING)
// TODO: add boolean withItems (e.g. /products?withItems=true)
  ResponseEntity<Product> fetchProduct(@PathVariable Integer id) {
    Product product = service.findProduct(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @GetMapping("/count")
  long countProducts() {
    return service.countProducts();
  }

  @GetMapping(ID_MAPPING + "/items")
  ResponseEntity<ProductItems> fetchProductItems(@PathVariable("id") Integer productId) {
    ProductItems items = service.getProductItems(productId);
    return items == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(items);
  }

  @DeleteMapping(ID_MAPPING)
  ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer productId) {
    Product product = service.findProduct(productId);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    service.deleteProduct(productId);
    return ResponseEntity.noContent().build();
  }
}
