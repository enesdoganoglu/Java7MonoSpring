package com.muhammet.repository;

import com.muhammet.dto.response.FindAllByUserNameResponseDto;
import com.muhammet.repository.entity.User;
import com.muhammet.repository.view.VwUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Bu sınıftan bir bean ile nesne yaratması için bu annotasyonu kullanıyoruz.
 * Veritabanı işlemlerini (CRUD) yapmak için kullanıyoruz.
 * @Repository -> bir interface new yapılamaz ancak bir nesnenin referansını taşıyabilir.
 * Spring işaretlenen bu interface in kullanabileceği bir nesne oluşturuyor. burada
 * dikkat edilmesi gereken kısım, miras alının tüm methodlar haricinde,spring koşullarına uyarak,
 * kendi yazdığımız methodlarında gövdeleri spring tarafından doldurulur.
 * !!! DİKKAT !!!! Eğer sınıfı respository anotasyonu eklenmez ise spring bu sınıftanbir referans yaratmaz. böylelikle
 * kullanmakta olduğunuz yerlerde nullpointer exception hatası alırsınız.
 *
 */
@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    /**
     *  !! DİKKAT !!
     * Spring kendine özel kelimeler ile ve bunları bell kurallara göre birleştirilmesi ile sorgularınız
     * otomatik olarak oluştabilir. önr:
     * findByUsernameAndPassword -> select * from tblusers where username=?1 and password=?2
     * peki nasıl sorfu yazarı?
     * Öncelikle burada yazılan kelimeler BÜYÜK küçük fark yazımına dikkat edilerek yazılmalıdır. Spring her kelimeyi
     * bu şekilde bir birinden ayırabilir.
     * 1- find: nerede ise tüm isteklerde kullanılır.
     * 2- By: ne için arama yapacağımızı belirtme için kullanıyoruz. bundan sonra gelen entity içindeki değişkenin adını niteler
     * 3- [Entity-> değişken adı]-> İşlem yaptığınız entity içindeki sorglayacağınız değişkeninadını yazıyorsunuz. DİKKAT,
     * burada değişken adını yazarken Mutlaka değişkenin ilk harfini büyük harf ile başlatmak zorundasınız,
     * 4- Eğer başka sorgulara devam edilecek ise ek parametreler ile devam edilir. And,Or,In,Not,True v.s.
     * 5- yazılan sorguda parametre girilmesi gerekiyor ise method parametreleri sıra ile talep edilir.
     * 6- methodun çalışmasından sonra geriye öneceği değeri belirtmeniz gereklidir.
     *
     */
    User findByUsernameAndPassword(String buraya_canin_ne_isterse_yazabilirsin,String bunlar_degisken_adi);
    /**
     * Şifresi aynı olan kullanıcılar
     * select * from tbluser where password=1?
     */
    List<User> findAllByPassword(String password);

    /**
     * belli bir yaş üzerindeki kullanıcıları bulacağız
     * select * from tbluser where yas>?1
     */
    List<User> findAllByYasGreaterThan(Integer yas);

    /**
     * Bir arama işleminde belli bir alanın kısmi aramasının yapılması.
     * Örn: username: ak -> Liste talep ediyorsunuz
     * select * from tbluser where userename like 'ak%' DİKKAT
     */
    List<User> findAllByUsernameLike(String username);

    /**
     * Aranan kelime ile başlayan dataları bulmaksa niyetimiz,
     * o zaman belli bir şeyle başlıyor ise deriz.
     */
    List<User> findAllByUsernameStartingWith(String username);

    /**
     * birden çok alan için kısıtlı aramalar yapmak istediğimiz
     * zamana, and ile birleştirerek sorgu yazarız.
     * select * from tbluser where ad like 'a%' and adres like 't%'
     */
    List<User> findAllByUsernameStartingWithAndAdresStartingWith(String username,String adres);
    User findByUsernameAndAdresAndCinsiyet(String username,String adres,String cinsiyet);

    /**
     * Sıralama işlemlerinde kullanıyoruz.
     * ORDERBY
     * ASC -> A...Z, 0...9
     * DESC -> Z..A, 9..0
     */
    List<User> findByOrderByYas(); // A...Z
    List<User> findAllByOrderByYasDesc(); //z..a
    List<User> findByUsernameStartingWithOrderByYasDesc(String username);

    /**
     * Sistemde kayıtlı en yaşlı n adet kişi
     * select * from tbluser limit 3
     */
    User findTopByYasLessThan(Integer yas); //? Top default 1
    List<User> findTop3ByOrderByYasDesc(); // sıralı 3 kişiyi döner

    /**
     *1-  Belli aralıklar içndeki kayıtları bulmak için,
     *2- belli bir aralıkta yaşı olanların adı ahmet olanlarını getir
     */
    List<User> findAllByYasBetween(Integer start,Integer end); // start>= and end<=
    List<User> findAllByUsernameAndYasBetween(String username,int start,int end);
    /**
     * Bazen veritananından veri talep ederiz ancak kriterlerimize uygun data
     * gelmeyebilir. Böyle durumlarda Optional kullanmak önemlidir.
     * Optional nedir?
     * User user = null;
     * user.getUsername(); -> Ne olur? -> NullPointerException
     * if(user!=null)
     *  user.getUsername();
     * Optional<User> user;  ->
     * user.get().getUsername(); -> optional içindeki değere ulaşmak için kullanılır.
     * if(user.IsEmpty()) HATAAAAAA
     * Optional bir nesneyi samalayarak, onu kutu gibi içine alır. kullanmadan önce içinde bir şey olup olmadığını
     * kontrol edebilrisiniz.
     * !!!!!!DİKKATTTTTTTTT!!!!!!
     * Geri dönüş değeri olarak optional sorgular yazıyoriseniz. geriye dönecek değerin
     * mutlaka tekil olduğundan emmin olunuz. Eğer sorgunuz Optional<User> gibi bir şey ise
     * tekil kayıt dönmediğinde uygulama istisna fırlatır. Örn:
     * Bir kullanıcı oturum açma işleminde username,password tekil olmalıdır. kayıt yok ise null dönmelidir.
     * istenmeyen durumlarda db de mükerrer kayıt olabilir buda sorgunuzun patlamasına neden olabilir.
     *
     */
    Optional<User> findOptionalByUsernameAndAdres(String username,String adres);

    /**
     * delete from tbluser where id=666545 -> tercih etmiyorum. bilgi güçtür.
     *
     */
    List<User> findAllByActiveTrue();
    List<User> findAllByActiveFalse();
    List<User> findAllByActive(boolean active);

    /**
     *
     * JPQL -> Java Persistance(Jakarta) için sorgulamalar
     * HQL -> Hibernate için sorgulamalar
     * NATIVESQL -> Native, bildiniz SQL için sorgulamalar
     *
     * @Query -> ile sorgulamalar yazarız. burada bize method ve parametreler için ve en önemlisi
     * return type lar için esneklik sağlar.
     */
     // List<User> findAllByAdres(String adres);
    @Query("select u from User u where u.adres= ?1")
    List<User> senGetirBanaTumListeyiAdresiAyniOlanlari(String adresver);

    @Query("select u from User u where u.yas= ?2 and u.adres= ?1")
    List<User> findAllByYasAndAdres(String adres,Integer yas);

    /**
     * Native SQL yazalım
     * native yazmak demek tablo adını yazmak zorundayız demek değildir. Burada Spring Repository kullandığınız için
     * Repos a verdiğiniz entity yi yazmak zorundasınız.
     */
    @Query(nativeQuery = true, value = "select * from User where adres= ?1")
    List<User> findAllByAdresGellBakalim(String adres);

    /**
     * Soru için gerekli olan parameterleri ad vererek parametre tanımı yapılarak çekilebilir.
     */
    @Query("select u from User u where u.username= :paramusername and u.password= :parampassword")
    User kullaniciVarMi(
            @Param("paramusername") String username,
            @Param("parampassword") String password);

    /**
     * Mesela; kullanıcı adı ve şifresini girerek bir kişinin kaydının olup olmadığını Boolen olarak dönmek istesem ne yaparım?
     *
     */
    @Query("select COUNT(u)>0 from User u where UPPER(u.username)= UPPER(?1) and u.password= ?2")
    Boolean isExistUser(String username,String password);

    @Query("select new com.muhammet.repository.view.VwUserSearch(u.id,u.username) from User u")
    List<VwUserSearch> findAllVwUser();

    /**
     * İhtiyacımız olan sorguyu yazacağız ayrıca bu sorgu neticesinde dönen bilgiyi kısıtlı olarak
     * ç.ekip bir DTO içine tranfer edeceğiz.
     * username verildiğinde sonuna % ekleyerek aram kriterini genişletecektir.
     */
    @Query("select new com.muhammet.dto.response.FindAllByUserNameResponseDto(u.ad,u.avatar,u.username) from User u where u.username like ?1%")
    List<FindAllByUserNameResponseDto> findAllByUsername(String username);


}
