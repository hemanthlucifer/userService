package com.localOrder.userService.config;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;

@Configuration
public class AWSStorageConfig {
	
	@Value("${ACCESS_KEY}")
	private String accessKey;
	
	@Value("${SECRET_ACCESS_KEY}")
	private String secretKey;
	
	@Value("${BUCKET_REGION}")
	private String region;
	
	@Bean
	public AmazonS3 getS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
		System.out.println(region);
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
	}
	

}
