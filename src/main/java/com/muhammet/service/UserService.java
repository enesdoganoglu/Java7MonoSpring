package com.muhammet.service;

import com.muhammet.dto.request.UserSaveRequestDto;
import com.muhammet.dto.request.UserUpdateRequestDto;
import com.muhammet.dto.response.FindAllByUserNameResponseDto;
import com.muhammet.mapper.IUserMapper;
import com.muhammet.repository.IUserRepository;
import com.muhammet.repository.entity.User;
import com.muhammet.utility.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor // Bu zorunlu olan, final değişkenlerinze değer atamak için iş görür
public class UserService extends ServiceManager<User,Long> {
    /**
     * Üezerinde gelen nesneden bir bean yaratır. Burada oluşturulan tüm nesneler spring taradından
     * devralındığı için tek referans olarak oluşturulur.
     */
//    @Autowired
//
    private final IUserRepository repository;

    public UserService(IUserRepository repository){
        super(repository);
        this.repository=repository;
    }
    /**
     * Constructor Injection
     *
     */
//    public UserService(IUserRepository repository){
//        this.repository=repository;
//    }
//
    public User save(String username,String password){
      return  repository.save(User.builder()
                        .username(username)
                        .password(password)
                .build());
    }

    public User save(UserSaveRequestDto dto){
//        User user = IUserMapper.INSTANCE.kullanici_transfer_objesinden_yenibir_user_olustur(dto);
//        return repository.save(user);
        return repository
                .save(IUserMapper.INSTANCE.kullanici_transfer_objesinden_yenibir_user_olustur(dto));
    }

    //public void save(User user){
   //     repository.save(user);
  //  }
    public List<User> findAll() {
        return repository.findAll();
    }
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }
    /**
     * Alternatif kullanıcı doğrulama hantal ve db yi çoooooooooooooooooooook yorabilecek bişey.
     * @param username
     * @param password
     * @return
     */
    public boolean doLogin(String username,String password){
        List<User> users = repository.findAll().stream()
                .filter(x->x.getUsername().equals(username) && x.getPassword().equals(password))
                .collect(Collectors.toList());
        return users.size()>0;
    }

    public boolean doLogin2(String username,String password){
        User user = repository.findByUsernameAndPassword(username, password);
        return  user!=null;
    }

    public List<FindAllByUserNameResponseDto> findByUsername(String username){
        /**
         * Yöntem 1-> Kullanıcı adına göre DB den dönen kayıtları döngü içinde
         * tek tek DTO ya cevirip liste halinde almak
         * tbluser -> user(id,ad,adres,telefon.....)
         * dto -> FindAllByUserNameResponseDto(username,ad,avatar)
         */
//        List<User> userList = repository.findAllByUsernameStartingWith(username);
//        List<FindAllByUserNameResponseDto> result = new ArrayList<>();
//        userList.forEach(item->{
//            result.add(FindAllByUserNameResponseDto.builder()
//                            .ad(item.getAd())
//                            .avatar(item.getAvatar())
//                            .username(item.getUsername())
//                    .build());
//        });
//       List<FindAllByUserNameResponseDto> result = repository.findAllByUsername(username);
//       return result;
        return repository.findAllByUsername(username);
    }

    public void update(UserUpdateRequestDto dto){
        /**
         * Yöntem 1 -> dto içindeki bilgiler user nesnesine aktarılıp güncelleme yapılabilir.
         */
//        User user = User.builder()
//                .id(dto.getId())
//                .username(dto.getUsername())
//                .adres(dto.getAdres())
//                .yas(dto.getYas())
//                .build();
        Optional<User> user = repository.findById(dto.getId());
        if(user.isPresent()){
            user.get().setUsername(dto.getUsername());
            user.get().setAdres(dto.getAdres());
            user.get().setYas(dto.getYas());
            repository.save(user.get());
        }

    }

}
