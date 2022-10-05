package pl.app.product.mapper;


import org.mapstruct.Mapper;
import pl.app.common.ProductDto;
import pl.app.product.domain.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDao(ProductDto productDto);
    ProductDto toDto(Product product);

}
