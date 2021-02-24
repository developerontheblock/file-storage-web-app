//package com.example.demo.service;
//
//import com.example.demo.mapper.UserMapper;
//import com.example.demo.model.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class AuthenticationService implements AuthenticationProvider {
//    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
//
//    private final UserMapper userMapper;
//    private final HashService hashService;
//
//    public AuthenticationService(UserMapper userMapper, HashService hashService) {
//        this.userMapper = userMapper;
//        this.hashService = hashService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        logger.info("Authenticating user " + username);
//        User user = userMapper.getUser(username);
//        if (user != null) {
//            String encodedSalt = user.getSalt();
//            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
//            if (user.getPassword().equals(hashedPassword)) {
//                logger.info("Authentication successful.");
//                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
//            }
//        }
//
//        logger.warn("Authentication failed for user " + username);
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
//
