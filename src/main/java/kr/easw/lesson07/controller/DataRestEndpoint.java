package kr.easw.lesson07.controller;

import kr.easw.lesson07.model.dto.ExceptionalResultDto;
import kr.easw.lesson07.model.dto.TextDataDto;
import kr.easw.lesson07.model.dto.UserAuthenticationDto;
import kr.easw.lesson07.model.dto.UserDataEntity;
import kr.easw.lesson07.service.TextDataService;
import kr.easw.lesson07.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

// @RestController 어노테이션을 사용하여 이 클래스가 REST 컨트롤러임을 선언합니다.
@RestController
// @RequestMapping 어노테이션을 사용하여 이 클래스의 기반 엔드포인트를 /api/v1/data로 설정합니다.
@RequestMapping("/api/v1/data")
// final로 지정된 모든 필드를 파라미터로 가지는 생성자를 생성합니다.
@RequiredArgsConstructor
public class DataRestEndpoint {
    private final TextDataService textDataService;

    // 이 메서드의 엔드포인트를 /api/v1/data/list로 설정합니다. GET만 허용됩니다.
    @GetMapping("/list")
    public List<TextDataDto> listText() {
        return textDataService.listText();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadText(@PathVariable Long id) {
        TextDataDto textData = textDataService.getTextById(id);

        // Create a ByteArrayResource from the text data
        ByteArrayResource resource = new ByteArrayResource(textData.getText().getBytes());

        // Build the response entity with the content, headers, and status
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=text.txt")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(textData.getText().getBytes().length)
                .body(resource);
    }
}
