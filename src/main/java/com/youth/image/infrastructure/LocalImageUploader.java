package com.youth.image.infrastructure;

import com.youth.image.application.Generators;
import com.youth.image.domain.entity.Image;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class LocalImageUploader {

    public void fileUpload(MultipartFile file){
        Path copyOfLocation = Paths.get("uploadDir" + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));

        try {
            String fileName = Generators.prefixUUIDString();

            Image image = new Image();
            // inputStream을 가져와서
            // copyOfLocation (저장위치)로 파일을 쓴다.
            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createMultipartFile(File file) throws IOException {

//        InputStream stream = new FileInputStream(zipFile);
//        MockMultipartFile multipartFileToSend = new MockMultipartFile("file", zipFile.getName(),
//        String.valueOf(MediaType.MULTIPART_FORM_DATA), stream);


//        MultipartFile multipartFile2 = new MockMultipartFile("test.xlsx", new FileInputStream(new File("/home/admin/test.xlsx")));
//        String writerData = "str1,str2,str3,str4";
//        MultipartFile multipartFile3 = new MockMultipartFile("files", "파일명.csv", "text/plain", writerData.getBytes(StandardCharsets.UTF_8));


    }

//    public void example(){
//        File file = new File("/path/to/file");
//
//        try {
//            FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
//            InputStream input = Files.newInputStream(file.toPath());
//            OutputStream os = fileItem.getOutputStream();
//            IOUtils.copy(input, os);
//            // Or faster..
//            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
//
//            MultipartFile multipartFile = new CommonsMultipartFile(null);
//
//
//        } catch (IOException ex) {
//            // do something.
//        }
//
//
//    }
//
//    public File multipartFileToFile(MultipartFile multipartFile) throws IOException {
//        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        multipartFile.transferTo(file);
//        return file;
//    }
}
