package pl.app.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.app.user.domain.Product;

public interface ProductService {

    Product create(Product product);

    Product update(Long id, Product ProductDto);

    Product findById(Long id);

    void delete(Long id);

    Page<Product> getPage(Pageable pageable);
}
