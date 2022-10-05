package pl.app.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.user.domain.Product;
import pl.app.user.repository.ProductRepository;
import pl.app.user.service.ProductService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


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

    public List<Product> getCurentProduct() {
//        Page<Product> page = productRepository.findTop1ByStartDateLessThanEqualAndEndDate(LocalDate.now(), PageRequest.of(0, 1));
//        System.out.println(page.getContent().get(0));

        Optional<Product> products = productRepository.findTop1ByStartDateLessThanEqualAndEndDate2(LocalDate.now());
        System.out.println(products);

        List<Product> all = productRepository.findAll();
        LocalDate now = LocalDate.now();
        all.stream().filter(p -> !p.getStartDate().isAfter(now) && (p.getEndDate() == null || !p.getEndDate().isBefore(now))
        ).findFirst().ifPresent(p -> p.setActive(true));

        return all;
    }
}
