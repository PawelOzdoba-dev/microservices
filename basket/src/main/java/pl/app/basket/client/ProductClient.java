package pl.app.basket.client;

import org.apache.tomcat.jni.Proc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.app.common.ProductDto;

@FeignClient(name = "PRODUCT-SERVICE") //łączenie za pomocą tej nazwy z innym mikroserwisem
//@FeignClient(url = "link") -> łączenie z zewnętrznymi serwisami podobnie jak RestTemplate
//Można używać WebClient'a który jest wersja nieblokującego restTemplate
@RequestMapping("/api/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ProductDto getById(@PathVariable Long id); //za pomocą resta łączymy się z getById z product

}
