package com.muhammet.utility;

import com.muhammet.repository.entity.User;
import com.muhammet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DemoDataImpl {

    private final UserService userService;


    //@PostConstruct
    public void init(){
           userService.save(User.builder()
                           .username("ali")
                           .avatar("https://picsum.photos/200/300")
                           .ad("Ali Tekin")
                   .build());
        userService.save(User.builder()
                .username("ayhan")
                .avatar("https://picsum.photos/200/300")
                .ad("Ayhan Tekin")
                .build());
        userService.save(User.builder()
                .username("anıl")
                .avatar("https://picsum.photos/200/300")
                .ad("Anıl Tekin")
                .build());
        userService.save(User.builder()
                .username("bahar")
                .avatar("https://picsum.photos/200/300")
                .ad("Bahar Tekin")
                .build());
        userService.save(User.builder()
                .username("benan")
                .avatar("https://picsum.photos/200/300")
                .ad("Benan Tekin")
                .build());
        userService.save(User.builder()
                .username("begisu")
                .avatar("https://picsum.photos/200/300")
                .ad("BengiSu Tekin")
                .build());
        userService.save(User.builder()
                .username("demet")
                .avatar("https://picsum.photos/200/300")
                .ad("Demet Tekin")
                .build());
        userService.save(User.builder()
                .username("Erhan")
                .avatar("https://picsum.photos/200/300")
                .ad("Erhan Tekin")
                .build());
        userService.save(User.builder()
                .username("Kemal")
                .avatar("https://picsum.photos/200/300")
                .ad("Kemal Tekin")
                .build());
        userService.save(User.builder()
                .username("Nedim")
                .avatar("https://picsum.photos/200/300")
                .ad("Nedim Tekin")
                .build());
        userService.save(User.builder()
                .username("Eylül")
                .avatar("https://picsum.photos/200/300")
                .ad("Eylül Tekin")
                .build());
        userService.save(User.builder()
                .username("Demet")
                .avatar("https://picsum.photos/200/300")
                .ad("Demet Tekin")
                .build());
        userService.save(User.builder()
                .username("Zeynep")
                .avatar("https://picsum.photos/200/300")
                .ad("Zepnep Tekin")
                .build());

    }
}
