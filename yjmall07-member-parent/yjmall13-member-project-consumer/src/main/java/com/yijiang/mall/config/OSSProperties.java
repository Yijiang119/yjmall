package com.yijiang.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName OSSProperties
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/29 16:31
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProperties {
    private String endPoint;
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketDomain;

    @Override
    public String toString() {
        return "OSSProperties{" +
                "endPoint='" + endPoint + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", bucketDomain='" + bucketDomain + '\'' +
                '}';
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketDomain() {
        return bucketDomain;
    }

    public void setBucketDomain(String bucketDomain) {
        this.bucketDomain = bucketDomain;
    }

    public OSSProperties() {
    }

    public OSSProperties(String endPoint, String bucketName, String accessKeyId, String accessKeySecret, String bucketDomain) {
        this.endPoint = endPoint;
        this.bucketName = bucketName;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketDomain = bucketDomain;
    }
}

