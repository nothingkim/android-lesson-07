package kr.easw.lesson07.controller;

import kr.easw.lesson07.model.dto.ExceptionalResultDto;
import kr.easw.lesson07.model.dto.UserAuthenticationDto;
import kr.easw.lesson07.model.dto.UserDataEntity;
import kr.easw.lesson07.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserAuthEndpoint {
    private final UserDataService userDataService;


    // JWT 인증을 위해 사용되는 엔드포인트입니다.
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDataEntity entity) {
        try {
            // 로그인을 시도합니다.
            return ResponseEntity.ok(userDataService.createTokenWith(entity));
        } catch (Exception ex) {
            // 만약 로그인에 실패했다면, 400 Bad Request를 반환합니다.
            return ResponseEntity.badRequest().body(new ExceptionalResultDto(ex.getMessage()));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDataEntity entity) {
        try {
            // 회원가입을 시도하고 성공하면 201 Created를 반환합니다.
            // 실패할 경우는 UserDataService에서 예외가 발생하므로, 그에 따른 처리를 해줍니다.
            userDataService.registerUser(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception ex) {
            // 회원가입에 실패했다면, 400 Bad Request를 반환합니다.
            return ResponseEntity.badRequest().body(new ExceptionalResultDto(ex.getMessage()));
        }
    }
}