package lt.bit.products.store.service;

import java.util.List;
import lt.bit.products.store.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public List<Product> findProducts() {
    return repository.findAll();
  }

  public Product findProduct(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public void deleteProduct(Integer id) {
    repository.deleteStoreItems(id);
    repository.deleteById(id);
  }

  public Product saveProduct(Product product) {
    return repository.save(product);
  }
}
