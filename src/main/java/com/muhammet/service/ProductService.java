package com.muhammet.service;

import com.muhammet.repository.IProductRepository;
import com.muhammet.repository.entity.Product;
import com.muhammet.utility.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Bir sınıfın servis olduğunu belirtmek ve spring tarafından bir nesne
 * yaratılması için @Service eklenmelidir.
 */
@Service
public class ProductService extends ServiceManager<Product,Long> {
    /**
     * Servisin temel olarak ilk erişim yaptığı varlık Repository dir. Ancak,
     * bir servis diğer servislerden de bilgi tranfer edebilir. yaptığı işlemeleri
     * servislere geçebilir.
     * !!!!!! DİKKATTTT !!!!
     * Bir servis Asla AMA ASLA başka servislerin Repository lerine erişim yapmaz.
     */
    /**
     * Bir servis içinde kullanılacak olan sınıflardan nesne yaratma işlemi spring
     * taradından devralınır. Bu işlemi 2 farklı şekilde yaparız.
     * 1- @Autowired ile
     */
//    @Autowired
//    IProductRepository repository;

    /**
     * 2- class final olarak tanımlanır ve constructor içinde inject edilmesi beklenir.
     */
    private final IProductRepository repository;

    /**
     * NOT, eğer lombok üzerinden zorunlu constructor methodunu kullanabilecek bir
     * yapınız var ise aşağıda bulunan contructor ı yazmak zorunda değilsiniz
     * bunun yerine @RequiredArgsConstructor kullanabilirsiniz.
     *
     */

   public ProductService(IProductRepository repository){
       super(repository);
       this.repository=repository;
   }













}
