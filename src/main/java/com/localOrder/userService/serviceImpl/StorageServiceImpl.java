package com.localOrder.userService.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.localOrder.userService.model.User;
import com.localOrder.userService.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageServiceImpl {

	@Value("${BUCKET_NAME}")
	private String bucketName;

	@Value("${BUCKET_REGION}")
	private String region;

	@Autowired
	private AmazonS3 s3Client;

	
	
	@Autowired
	private UserRepository userRepository;

	

	public String uploadProfileImage(int userId,MultipartFile file) throws FileNotFoundException, IOException {
		String fileName = "profilePic"+userId;
		File fileObj = convertMultiPartFileToFile(file);
		PutObjectRequest putObjectrequest = new PutObjectRequest(bucketName,fileName,fileObj);
        s3Client.putObject(putObjectrequest);
        fileObj.delete();
        String imageUrl = imageLink(fileName);
        return imageUrl;
	}

	public File convertMultiPartFileToFile(MultipartFile file) throws FileNotFoundException, IOException {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return convertedFile;
	}
	
	public String imageLink(String imageName) {
		try {
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, imageName)
                    .withMethod(HttpMethod.GET);
            URL presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            System.out.println(presignedUrl);
            return presignedUrl.toString();
        } catch (Exception e) {
            log.error("Error generating presigned URL:", e);
            throw new RuntimeException("Error generating presigned URL"); 
        }
	}

}
