package com.poly.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.poly.dto.request.AuthenticationRequest;
import com.poly.dto.request.IntrospectRequest;
import com.poly.dto.request.LogoutRequest;
import com.poly.dto.respone.AuthenticationResponse;
import com.poly.dto.respone.IntrospectResponse;
import com.poly.entity.User;

import java.text.ParseException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);
    AuthenticationResponse authentication(AuthenticationRequest request);
    String generateToken(User user);
    String buildRoles(User user);
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    SignedJWT verifyToken(String token) throws JOSEException, ParseException;
    String getRoleFromToken(String token) throws ParseException, JOSEException;
}
