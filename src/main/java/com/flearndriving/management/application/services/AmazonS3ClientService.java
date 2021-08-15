package com.flearndriving.management.application.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonS3ClientService {

    private String awsS3AudioBucket;
    private AmazonS3 amazonS3;

    @Autowired
    public AmazonS3ClientService(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider,
            String awsS3AudioBucket) {
        this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3AudioBucket = awsS3AudioBucket;
    }

    @Async
    public String uploadFileToS3Bucket(MultipartFile multipartFile, String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            PutObjectRequest putObjectRequest = new PutObjectRequest(awsS3AudioBucket, fileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicReadWrite);
            amazonS3.putObject(putObjectRequest);
            // Removing the file created in the server
            file.delete();
        } catch (IOException | AmazonServiceException ex) {
            ex.printStackTrace();
        }
        return amazonS3.getUrl(awsS3AudioBucket, fileName).toString();
    }

    @Async
    public void deleteFileFromS3Bucket(String fileName) {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, fileName));
        } catch (AmazonServiceException ex) {
            ex.printStackTrace();
        }
    }
}
