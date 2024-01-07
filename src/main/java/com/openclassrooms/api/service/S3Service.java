package com.openclassrooms.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * This service class handle th interaction with Amazon S3 for file uploading
 * @author Kyrian ANIECOLE
 */
@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3;

    @Value("${aws.s3.s3imagestorage}")
    private String bucketName;


    /**
     * This class uploads a file to Amazon S3
     *
     * @param file The multipart file to be uploaded
     * @return The generated file name after uploading
     * @author Kyrian ANIECOLE
     */
    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultipleFileToFile(file);
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
        fileObj.delete();
        return fileName;
    }

    /**
     * This class converts a MultipartFile to a local File
     *
     * @param file The MultipartFile to be converted
     * @return The converted File
     * @author Kyrian ANIECOLE
     */

    private File convertMultipleFileToFile(MultipartFile file) {
        File convertedFile = new  File(file.getOriginalFilename());
        try(FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erorr converting mutlipartFile to file."+e);
        }
        return convertedFile;
    }
}
