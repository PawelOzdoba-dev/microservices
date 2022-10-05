package pl.app.product.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.app.common.ProductDto;
import pl.app.product.domain.Product;

public interface ProductService {

    @CachePut(cacheNames = "product", key = "#result.id")
//to co zostanie zwrócone z metody create i z tego bierzemy id
    Product create(Product product);

    @CachePut(cacheNames = "product", key = "#result.id")
    Product update(Long id, Product product);

    @Cacheable(cacheNames = "product", key = "#id")
        //z parametru metody bierzemy id
    Product findById(Long id);

    @CacheEvict(cacheNames = "product", key = "#id")
        //jeśli metoda delete zwróci błąd to nie zostanie usunięty ten obiekt z cache
    void delete(Long id);

    Page<Product> getPage(Pageable pageable);


}
