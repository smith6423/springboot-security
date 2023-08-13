package com.jh.shopapi.controller;

import com.jh.shopapi.dto.MemberLoginDto;
import com.jh.shopapi.dto.MemberLoginResponseDto;
import com.jh.shopapi.sercurity.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final JwtTokenizer jwtTokenizer;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto){
        Long memberId = 1L;
        String email = loginDto.getEmail();
        List<String> roles = List.of("ROLE_USER"); //권한 설정

        String accessToken = jwtTokenizer.createAccessToken(memberId, email, roles);
        String refreshToken = jwtTokenizer.createRefreshToken(memberId, email, roles);

        MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(memberId)
                .nickname("nickname")
                .build();
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }
}
