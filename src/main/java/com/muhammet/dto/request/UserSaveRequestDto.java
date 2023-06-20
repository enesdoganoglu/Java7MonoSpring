package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSaveRequestDto {
    @NotBlank(message = "Kullanıcı adını boş geçemezsiniz.")
    @Size(min = 3,max = 20,message = "Kullanıcı adı en az 3 en fazla 20 karakter olabilir.")
    String username;
    @NotBlank(message = "Şifreyi boş geçemezsiniz.")
    @Size(min = 8,max=64)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifreniz en az bir büyük harf, bir küçük harf, bir sayı ve bir özel karakter içermelidir.")
    String password;
    String ad;
    String il;
    String telefon;
    String avatar;
}
