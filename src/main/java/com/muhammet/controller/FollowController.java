package com.muhammet.controller;

import com.muhammet.exception.ErrorType;
import com.muhammet.exception.Java7MonoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.muhammet.constants.EndPointList.*;
@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {

    @GetMapping("/hata")
    public ResponseEntity<String> getHata(){
            int i =0;
            double sonuc = 10/i;
        return ResponseEntity.ok("Hata olmadan işlem tamamlandı");
    }

    /**
     * http://localhost:9090/follow/myfollower?token=aliveli
     * @param token
     * @return
     */
    @GetMapping("/myfollower")
    public ResponseEntity<List<String>> getMyFollower(String token){
        if(token.startsWith("token.: ")){
            return ResponseEntity.ok(List.of("Muhammet","Ali","Veli"));
        }else {
            /**
             * Burada uygulamaya özel hata oluşacak. hata olayı kullanıcının
             * token bilgisinin geçirsiz olması.
             */
            throw new Java7MonoException(ErrorType.ERROR_INVALID_TOKEN);
        }
    }
}
