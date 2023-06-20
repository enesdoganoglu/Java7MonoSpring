package com.muhammet.mapper;

import com.muhammet.dto.request.UserSaveRequestDto;
import com.muhammet.dto.request.UserUpdateRequestDto;
import com.muhammet.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Bu interface' in bir mapper sınıfı olarak kullanılabilmesi için @Mapper ile işaretlenir, ancak
 * mapstruct farklı platformalar için işlem gördüğünden hangi framework ile çalışıldığı bilgisi
 * kendisine iletilmelidir. Biz spring kullandığımız için bunu belirtmek zorundayız.
 * Mapper en nihayetince dto ile entity arasında dönüşüm yapacaktır, bu işloemin bire bir
 * örtüşmesi her daim gerçekleşmeyecektir.Bu nedenle mapper çalıırken eşleşmeyen alanların
 * görmezden gelinmesi ve null olarak aktarılması mapper a bir politika olarak iletilir.
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    /**
     * Bu sınıf bir interface olduğu için bu interfave new işlemine tabi tutulamaz, bu nedenle
     * bir instance a ihtiyacımız var bunu spring bean ile değil maptruct ile sağlayacağız.
     */
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    /**
     * Buradan itibaren artık mapper kullanarak dto->entity ve entity->dto dönüşümlerini
     * yapabiliriz.
     * 1- Geri dönüş tipi sınıf olarak belirtilir.
     * 2- method un adı yazılır, method adı için özel bir kullanım yoktur.
     * 3- dönüşümünü yapmak istediğiniz kaynak nesneyi yazıyoruz.
     */
    User kullanici_transfer_objesinden_yenibir_user_olustur(final UserSaveRequestDto dto);

    User toUser(final UserSaveRequestDto dto);
    User toUSer(final UserUpdateRequestDto dto);

}
