package pl.app.basket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.app.basket.client.ProductClient;
import pl.app.common.ProductDto;

@RestController
@RequestMapping("/api/baskets/")
@RequiredArgsConstructor
public class BasketController {

    private final ProductClient productClient;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productClient.getById(id);
    }

}
//zrobić pakiet config w której zrobię RestTemplate klasę w której zrobię bean która zwraca restTemplate i bean ma być oznaczony adnotacją: @LoadBalanced
//w linku muszę wstawić nazwę aplikacji czyli (url) PRODUCT-SERVICE
//zrobić security user
//endpoiinty przenieść z monolitu z usera i produktu.

//elasticsearch co to ? https://smartbees.pl/blog/elasticsearch-czyli-wszystko-o-wyszukiwaniu-pelnotekstowym
//product maven projekt ten jest drugą instancją mikroserwisu PRODUCT-SERVICE - odpalany na porcie 8084.
