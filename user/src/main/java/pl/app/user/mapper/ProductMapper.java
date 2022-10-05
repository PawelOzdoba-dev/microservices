package pl.app.user.mapper;

import org.mapstruct.Mapper;
import pl.app.user.domain.Product;
import pl.app.user.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "id", target = "id")
    Product toDao(ProductDto productDto);

//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "id", target = "id")
    ProductDto toDto(Product product);

}
