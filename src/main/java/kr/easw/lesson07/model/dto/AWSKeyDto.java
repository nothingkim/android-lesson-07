package kr.easw.lesson07.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AWSKeyDto {
    @Getter
    private final String apiKey;

    @Getter
    private final String apiSecretKey;
}
