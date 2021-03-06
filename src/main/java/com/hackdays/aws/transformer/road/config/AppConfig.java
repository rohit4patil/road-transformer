package com.hackdays.aws.transformer.road.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.regions.Regions.US_EAST_1;

@Configuration
public class AppConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(this.accessKey, this.secretKey);
    }

    @Bean
    public AmazonS3 amazonS3(AWSCredentials awsCredentials) {
        // @formatter:off
        return AmazonS3ClientBuilder.
                standard()
                .withRegion(US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        // @formatter:on
    }

    @Bean
    public AmazonRekognition amazonRekognition(AWSCredentials awsCredentials) {
        // @formatter:off
        return AmazonRekognitionClientBuilder
                .standard()
                .withRegion(US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        // @formatter:on
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        // @formatter:off
        return AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withRegion(US_EAST_1)
                .build();
        // @formatter:on
    }
}
