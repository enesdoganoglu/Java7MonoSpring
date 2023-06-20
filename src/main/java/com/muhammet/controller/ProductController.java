package com.muhammet.controller;

import com.muhammet.repository.entity.Product;
import com.muhammet.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller kısmını temelde 2 şekilde kullanırız.
 * Bu kullanımlar, Web Sayfalarını dönen Mvc Kavramı ve Kullanıcılara Json ile Data dönebilen Rest Servisleridir.
 * @Controller ile Web MVC sayfalarını inşa edebiliriz.
 * @RestController ile restfull servislerini oluşturabiliriz.
 * Controller sayfalarımız gelen istekleri yakalamak için URL path ini kullanır. böyleye isteğin nerede gitmesi
 *
 * gerektiği belirlenir. Bizde kendi kontrol sayfamızda bunu @RequestMapping ile sağlarız.
 * {HOST_NAME}/product -> http://localhost:9090/product -> http://localhost:9090/api/v1/product
 * Gelen isteğin yakalanabilmesi için Mapping içinde yazdığınız adresin bire bir uyumlu olması gereklidir.
 *
 *
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * http://localhost:9090/product/getname
     * @return
     */
    @GetMapping("/getname")
    public String getName(){
        return "<h2 style=\"color:red\">Muhammet</h2>";
    }


}
