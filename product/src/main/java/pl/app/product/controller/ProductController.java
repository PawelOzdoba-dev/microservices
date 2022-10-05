package pl.app.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.app.common.ProductDto;
import pl.app.product.mapper.ProductMapper;
import pl.app.product.service.impl.ProductServiceImpl;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;
    private final ProductMapper productMapper;

//    @GetMapping("/{id}")
//    public ProductDto getProductById(@PathVariable Long id){
//        log.info("Jestem w kontrolerze produktu {}",id);
//        return new ProductDto(id, "BUTY", 20L, "Fajne buty",150.99d);
//    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")//dla wszystkich
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        log.info("Get product by id from controller {}", id);
       return  ResponseEntity.ok()
               .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
               .body(productMapper.toDto(productServiceImpl.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")//dla admina, mozna dodać walidację do ProductDto
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productMapper.toDto(productServiceImpl.update(id, productMapper.toDao(productDto)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping//dla admina,  mozna dgiodać walidację do ProductDto
    public ProductDto createProduct(@RequestBody @Valid ProductDto productDto) {//, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
        return productMapper.toDto(productServiceImpl.create(productMapper.toDao(productDto)));
    }

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    void handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
//        log.error("handlerMethodArgumentTypeMismatchException: ", e);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")//dla admina
    public void deleteProduct(@PathVariable Long id) {
        productServiceImpl.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping//dla wszystkich
    public Page<ProductDto> getPages(@RequestParam int page, @RequestParam int size) {
//        Page<Product> page1 = productServiceImpl.getPage(PageRequest.of(page, size));
        return productServiceImpl.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

}
