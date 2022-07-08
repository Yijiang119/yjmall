package com.yijiang.mall.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.yijiang.mall.constant.MallConstant;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName MallUtil
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/20 15:56
 * @Version 1.0
 */
public class MallUtil {

    /**
     * @return com.yijiang.mall.util.ResultEntity<java.lang.String> 包含上传结果以及上传的文件在 OSS 上的访问路径
     * @Author 姜泽昊 专门负责上传文件到 OSS 服务器的工具方法
     * @Description
     * @Date 16:44
     * @Param [endpoint, accessKeyId, accessKeySecret, inputStream, bucketName, bucketDomain, originalName]
     */
    public static ResultEntity<String> uploadFileToOss(
            String endpoint,
            String accessKeyId,
            String accessKeySecret,
            InputStream inputStream,
            String bucketName,
            String bucketDomain,
            String originalName) {
        // 创建 OSSClient 实例。 
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 生成上传文件的目录 
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 生成上传文件在 OSS 服务器上保存时的文件名 
        // 原始文件名：beautfulgirl.jpg 
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg 
        // 使用 UUID 生成文件主体名称 
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        // 从原始文件名中获取文件扩展名 
        String extensionName = originalName.substring(originalName.lastIndexOf("."));
        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称 
        String objectName = folderName + "/" + fileMainName + extensionName;
        try {
            // 调用 OSS 客户端对象的方法上传文件并获取响应结果数据 
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
            // 从响应结果中获取具体响应消息 
            ResponseMessage responseMessage = putObjectResult.getResponse();
            // 根据响应状态码判断请求是否成功 
            if (responseMessage == null) {
                // 拼接访问刚刚上传的文件的路径 
                String ossFileAccessPath = bucketDomain + "/" + objectName;
                // 当前方法返回成功 
                return ResultEntity.successWithData(ossFileAccessPath);
            } else {
                // 获取响应状态码 
                int statusCode = responseMessage.getStatusCode();
                // 如果请求没有成功，获取错误消息 
                String errorMessage = responseMessage.getErrorResponseAsString();
                // 当前方法返回失败 
                return ResultEntity.failed(" 当 前 响 应 状 态 码 =" + statusCode + " 错 误 消 息" + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 当前方法返回失败 
            return ResultEntity.failed(e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭 OSSClient。 
                ossClient.shutdown();
            }
        }
    }

    /**
     * @return java.lang.String加密结果
     * @Author 姜泽昊
     * @Description 对明文字符串进行MD5加密
     * @Date 10:23
     * @Param [source] 传入的明文字符串
     */
    public static String md5(String source) {

        // 1.判断 source 是否有效
        if (source == null || source.length() == 0) {
            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(MallConstant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            // 3.获取 MessageDigest 对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            // 5.执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建 BigInteger 对象
            // 1表示转换后的BigInteger为正数
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7.按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return boolean true 是 false 不是
     * @Author 姜泽昊
     * @Description 判断当前请求是否为Ajax请求
     * @Date 16:07
     * @Param [request]
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        //1.获取请求信息头
        String accept = request.getHeader("Accept");
        String requestHeader = request.getHeader("X-Requested-With");
        //2.判断
        return (accept != null && accept.equals("application/json"))
                ||
                (requestHeader != null && requestHeader.equals("XMLHttpRequest"));
    }

}
