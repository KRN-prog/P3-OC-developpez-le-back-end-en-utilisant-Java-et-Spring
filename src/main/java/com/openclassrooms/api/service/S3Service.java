package com.openclassrooms.api.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Service
//@Slf4j
public class S3Service {

    @Autowired
    private AmazonS3 s3;

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.s3imagestorage}")
    private String bucketName;

    /*public String generateUrl(String fileName, HttpMethod http) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);
        URL url = s3.generatePresignedUrl(bucketName,fileName,calendar.getTime(),http);
        return url.toString();
    }*/






    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultipleFileToFile(file);
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
        fileObj.delete();
        return fileName;
    }


    public ResponseEntity<InputStreamResource> getPicture(String fileName) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        InputStream inputStream = s3Client.getObject(getObjectRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(inputStream));
    }


    public String deleteFile(String fileName){
        s3.deleteObject(bucketName, fileName);
        return fileName+"removed...";
    }
    private File convertMultipleFileToFile(MultipartFile file) {
        File convertedFile = new  File(file.getOriginalFilename());
        try(FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Erorr converting mutlipartFile to file."+e);
        }
        return convertedFile;
    }
}
