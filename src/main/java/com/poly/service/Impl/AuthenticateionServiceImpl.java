package com.poly.service.Impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.poly.dto.request.UserAuthenticateRequest;
import com.poly.dto.respone.UserAuthRespone;
import com.poly.repository.UserRepository;
import com.poly.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateionServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticateionServiceImpl.class);
    UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY = "prgi7mv2HSRPAK9RyBHUmWfDw2WWcIX55kA3yIAsYafed+zkL2EniVVO79Q3BdPF";

    @Override
    public UserAuthRespone authenticate(UserAuthenticateRequest request) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticate = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticate) {
            throw new RuntimeException("Unauthentication");

        }

        var token = generateToken(request.getUsername());
        return UserAuthRespone.builder()
                .token(token)
                .authenticate(true)
                .build();
    }

    public String generateToken(String username){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512); // XÁC ĐỊNH THUẬT TOÁN MÃ HÓA ĐỄ LÀM GÌ

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username) // show username
                .issuer("huunghia.com") // ten host cua minh
                .issueTime(new Date()) // current time
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() // Thoi gian het han la 1 gio
                ))
                .claim("customclaim", "custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        // ky token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }

    }
}
