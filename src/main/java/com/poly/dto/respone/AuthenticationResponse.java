package com.poly.dto.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
