package com.tendencias.app.Usuarios.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.tendencias.app.Usuarios.model.vm.Asset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class S3Service {
    private final static String bucket = "demospringboots3bucketmai";
    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucket, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucket, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try ( FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public String getObjectUrl(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", bucket, key);
    }

    public Asset getObjet(String key) {
        S3Object s3objeto = s3Client.getObject(bucket, key);
        ObjectMetadata metadata = s3objeto.getObjectMetadata();

        try {
            S3ObjectInputStream inpustream= s3objeto.getObjectContent();
            byte[] bytes=IOUtils.toByteArray(inpustream);

            return new Asset(bytes,metadata.getContentType());

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
