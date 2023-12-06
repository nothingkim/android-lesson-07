package kr.easw.lesson07.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserAuthenticationDto {
    private final String token;
}
