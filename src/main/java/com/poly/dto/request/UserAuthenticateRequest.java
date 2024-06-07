package com.poly.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAuthenticateRequest {
    String username;
    String password;
}
