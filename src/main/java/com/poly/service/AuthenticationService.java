package com.poly.service;

import com.poly.dto.request.UserAuthenticateRequest;
import com.poly.dto.respone.UserAuthRespone;

public interface AuthenticationService {
    UserAuthRespone authenticate(UserAuthenticateRequest request);
}
