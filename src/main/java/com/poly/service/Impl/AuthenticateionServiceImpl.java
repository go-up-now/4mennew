package com.poly.service.Impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.poly.dto.request.AuthenticationRequest;
import com.poly.dto.request.IntrospectRequest;
import com.poly.dto.request.LogoutRequest;
import com.poly.dto.respone.AuthenticationResponse;
import com.poly.dto.respone.IntrospectResponse;
import com.poly.entity.InvalidatedToken;
import com.poly.entity.User;
import com.poly.repository.InvalidatedTokenRepository;
import com.poly.repository.UserRepository;
import com.poly.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateionServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    UserRepository userRepository;
    //    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidateTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGN_KEY;

    // Kiểm tra tính hợp lệ của 1 token
    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        // Lấy 1 token từ request
        var token = request.getToken();

        var valid = true;
        try {
            verifyToken(token);
        } catch (RuntimeException | JOSEException | ParseException e) {
            valid = false;
        }

        return IntrospectResponse.builder()
                .valid(valid)
                .build();
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("ErrorCode.USER_NOT_FOUND"));


        boolean authenticate = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticate)
            throw new RuntimeException("ErrorCode.UNAUTHENTICATED");

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public String generateToken(User user) {
        // Tạo header cho token
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        // Tạo nội dung data cho phần body của payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("huunghia.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildRoles(user))
                .build();

        // Tạo payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        // Thêm vào token
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        // Tạo chữ ký cho token
        try {
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String buildRoles(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())){
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> {
                        stringJoiner.add(permission.getName());
                    });
                }
            });
        }
        return stringJoiner.toString();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken());

        var jit = signToken.getJWTClaimsSet().getJWTID();
        var expiry = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .expiryTime(expiry)
                .id(jit)
                .build();
        invalidateTokenRepository.save(invalidatedToken);
    }

    @Override
    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
       // Tạo một đối tượng MACVerifier để xác minh chữ ký
        JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());

        // Chuyển đổi token thành đối tượng SignedJWT
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Lấy thời gian hết hạn của token
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        // Xác minh chữ ký(value là boolen)
        var verified = signedJWT.verify(verifier);

        if(!(verified && expiryTime.after(new Date())))
            throw new RuntimeException("ErrorCode.UNAUTHENTICATED");

        if(invalidateTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new RuntimeException("ErrorCode.UNAUTHENTICATED");

        return signedJWT;
    }

    @Override
    public String getRoleFromToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(token);
        // Lấy giá trị của claim "role"
        String role = signedJWT.getJWTClaimsSet().getStringClaim("scope");


        return role;
    }
}
