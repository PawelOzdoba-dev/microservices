package pl.app.product.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.product.domain.Product;
import pl.app.product.repository.ProductRepository;
import pl.app.product.service.ProductService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
//    private final CategoryService categoryService;


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) {
        Product productById = findById(id);
        productById.setName(product.getName());
        return productById;
    }

    @Override
    public Product findById(Long id) {

        log.info("Product not in cache: {} ", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


}
