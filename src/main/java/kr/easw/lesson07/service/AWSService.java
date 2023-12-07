package kr.easw.lesson07.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import kr.easw.lesson07.model.dto.AWSKeyDto;
import org.springframework.beans.factory.DisposableBean;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class AWSService implements DisposableBean {
    public static final String BUCKET_NAME = "nothingkim-bucket-231207";
    private AmazonS3 s3Client;

    public void initAWSAPI(AWSKeyDto awsKey) {
        // Initialize the s3Client
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsKey.getApiKey(), awsKey.getApiSecretKey())))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        // Check if s3Client is not null before performing operations
        if (s3Client != null) {
            // Delete existing files in the bucket and the bucket itself
            deleteBucketAndContents(BUCKET_NAME);

            // Create the bucket if it doesn't exist
            if (!s3Client.doesBucketExistV2(BUCKET_NAME)) {
                s3Client.createBucket(BUCKET_NAME);
            }
        }
    }

    private void deleteBucketAndContents(String bucketNameToDelete) {
        // Check if the bucket exists before attempting to delete
        if (s3Client.doesBucketExistV2(bucketNameToDelete)) {
            List<S3ObjectSummary> objectSummaries = s3Client.listObjects(bucketNameToDelete).getObjectSummaries();
            for (S3ObjectSummary objectSummary : objectSummaries) {
                s3Client.deleteObject(bucketNameToDelete, objectSummary.getKey());
            }
            s3Client.deleteBucket(bucketNameToDelete);
            System.out.println("Bucket deleted: " + bucketNameToDelete);
        } else {
            System.out.println("Bucket does not exist: " + bucketNameToDelete);
        }
    }


    public List<String> getFileList() {
        if (s3Client != null) {
            return s3Client.listObjects(BUCKET_NAME).getObjectSummaries().stream().map(S3ObjectSummary::getKey).toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void destroy() {
        //
    }

    public byte[] downloadFile(String fileName) {
        if (s3Client != null) {
            S3Object object = s3Client.getObject(BUCKET_NAME, fileName);
            S3ObjectInputStream objectContent = object.getObjectContent();

            try {
                return IOUtils.toByteArray(objectContent);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to download file: " + fileName);
            }
        } else {
            throw new RuntimeException("AWS API not initialized");
        }
    }


    public boolean isInitialized() {
        return s3Client != null;
    }

    @SneakyThrows
    public void upload(MultipartFile file) {
        if (s3Client != null && BUCKET_NAME != null) {
            s3Client.putObject(BUCKET_NAME, file.getOriginalFilename(), new ByteArrayInputStream(file.getResource().getContentAsByteArray()), new ObjectMetadata());
        }
    }


}
