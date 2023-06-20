package com.muhammet.repository.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VwUserSearch {
    /**
     * View larda değişken tanımlarken asıl entity nin
     * alan adlarını kullanın. bire bir uyduğundan emin olun
     */
    Long id;
    String username;
}
