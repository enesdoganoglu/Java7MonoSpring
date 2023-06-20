package com.muhammet.controller;

import com.muhammet.constants.EndPointList;
import com.muhammet.dto.request.UserSaveRequestDto;
import com.muhammet.dto.request.UserUpdateRequestDto;
import com.muhammet.dto.response.FindAllByUserNameResponseDto;
import com.muhammet.mapper.IUserMapper;
import com.muhammet.repository.entity.User;
import com.muhammet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.muhammet.constants.EndPointList.*;
import java.util.List;

/**
 * com.muhammet.UserController
 * http://localhost:9090/user
 */
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * http://localhost:9090/user/save?username=muhammet&password=123456
     * @param username
     * @param password
     * @return
     *
     * https://www.google.com.tr/search?q=araba
     *
     * GetMapping Nedir? -> bir istemcinin belli bir sunucuya bilgi almak amcıyla gönderdiği
     * isteklerdir. Burada temel amaç bir bilgiye ulaşmaktır.
     * Eğer get isteği içinde bir parametre iletilmesi gerekli ise bu parametre, Header içinde
     * açık bir şekilde iletilir. Güvenliği yoktur, herkese açık olmayan istekler ve bilgiler
     * get ile İLETİLEMEZ.
     */


    /**
     * http://localhost:9090/user/findall
     * @return
     */
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<User>> findAll(){
        List<User> result = userService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(FIND_BY_USERNAME)
    public ResponseEntity<List<FindAllByUserNameResponseDto>> findAllByUsername(String username){
       List<FindAllByUserNameResponseDto> result= userService.findByUsername(username);
       return ResponseEntity.ok(result);
    }

    /**
     * Belli işlemler için kullanılacak olan parametrelerde tek tek kullanım asla yapılmamalıdır.
     * Bunun yerine Entity kullanımı yapılabilir ancak güvenlik ve diğer sorunlar nedeni ile bunu
     * kullanmakta sağlıklı değildir. Bu nedenle;
     * Tüm Rest işlemlerinizde data alımı ve iletimi için DTO(Data Transfer Object) kullanılmalıdır.
     *
     * http://localhost:9090/dev/v1/user/update?id=2&username=ayhantekin&adres=İzmir&yas=99
     */
    @GetMapping(UPDATE)
    public ResponseEntity<Void> update(UserUpdateRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * http://localhost:9090/dev/v1/user/save?username=muhammet&password=123456
     */
    @GetMapping(SAVE)
    public ResponseEntity<User> save(String username,String password){
        User user= userService.save(username, password);
        System.out.println("User.....: "+ user.toString());
        return ResponseEntity.ok(user);
    }

    /**
     * http://localhost:9090/dev/v1/user/save?username=muhammet&password=123456
     * !!!! DİKKAT !!!!
     * backend yazılımcısı olarak ASLA ve ASLA forntend ten gelen bilgilere güvenemeyiz.
     * tüm gelen datayı kontrol etmek zorundasınız ve ayrıca belli kriterleri zorulu kılmalısınız.
     */
    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody @Valid UserSaveRequestDto dto){
        /**
         * Düşünelim,
         * Bir Entity içinde onalrca bilgi barındırabilir, ayin şekilde dto içinde de bir çok bilgi
         * olabilir. böyle bir durumda dto içindeki bilgiler entity içine aktarılır.Ancak, bu
         * işlem ilk kullanımDA sorun yaratmaza bile zaman içinde değişen koşullar nedeniile
         * parameterlerin değişömesi, yeni parametrelerin eklenmesi gibi durumlar olabilir.
         * İşte böyle bir senaryoda bir çok endpoint için yüzlerce değişiklik yapmak
         * zorunda kalabilirsiniz.
         */





//        User user = User.builder()
//                .username(dto.getUsername())
//                .password(dto.getPassword())
//                .il(dto.getSehir())
//                .build();
//        User user =  IUserMapper.INSTANCE.kullanici_transfer_objesinden_yenibir_user_olustur(dto);
        userService.save(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * http://localhost:9090/swagger-ui/index.html
     */



}
