package ru.filippov.neatvue.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.filippov.neatvue.config.jwt.TokenProvider;
import ru.filippov.neatvue.domain.User;
import ru.filippov.neatvue.dto.ProfileDto;
import ru.filippov.neatvue.dto.SignInDto;
import ru.filippov.neatvue.dto.SignUpDto;
import ru.filippov.neatvue.dto.TokenDto;
import ru.filippov.neatvue.service.auth.AuthService;
import ru.filippov.neatvue.service.user.UserDetailsServiceImpl;
import ru.filippov.neatvue.service.user.UserPrinciple;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/auth")
@Slf4j
public class AuthenticationRestAPI {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    AuthService authService;

    @Autowired
    TokenProvider jwtProvider;

    @PutMapping("/refresh-token")
    public ResponseEntity<TokenDto> refreshTokens(@Valid @RequestBody TokenDto tokens) {
        TokenDto newTokens = null;
        try {
            newTokens = authService.refreshTokens(tokens.getRefreshToken(), this.jwtProvider);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<TokenDto>( new TokenDto("", "", 0, 0),
                    HttpStatus.UNAUTHORIZED);
        }


        return ResponseEntity.ok().body(newTokens);
    }
}