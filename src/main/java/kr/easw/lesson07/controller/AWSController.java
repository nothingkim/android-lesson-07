package kr.easw.lesson07.controller;
import static kr.easw.lesson07.service.AWSService.BUCKET_NAME;

import com.amazonaws.auth.policy.Resource;
import com.amazonaws.services.s3.model.S3Object;
import kr.easw.lesson07.model.dto.AWSKeyDto;
import kr.easw.lesson07.model.dto.UploadFileDto;
import kr.easw.lesson07.service.AWSService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.ByteArrayResource;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;


import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rest/aws")
public class AWSController {

    private final AWSService awsController;

    @PostMapping("/auth")
    private ModelAndView onAuth(AWSKeyDto awsKey) {
        try {
            awsController.initAWSAPI(awsKey);
            return new ModelAndView("redirect:/admin");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ModelAndView("redirect:/server-error?errorStatus=" + ex.getMessage());
        }
    }

    @GetMapping("/list")
    private List<String> onFileList() {
        return awsController.getFileList();
    }

    @PostMapping("/upload")
    private ModelAndView onUpload(@RequestParam MultipartFile file) {
        try {
            awsController.upload(file);
            return new ModelAndView("redirect:/admin");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ModelAndView("redirect:/server-error?errorStatus=" + ex.getMessage());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> onFileDownload(@RequestParam String fileName) {
        try {
            byte[] fileContent = awsController.downloadFile(fileName);
            ByteArrayResource resource = new ByteArrayResource(fileContent);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}