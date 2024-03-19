package com.youth.image;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class YouthImageApplicationTests {

    @Test
    void contextLoads() throws IOException {
        final String fileName = "testImage";
        final String contentType = "jpg";
        final String filePath = "src/test/resources/testImage/" + fileName + "." + contentType;
        //upload/product/202310/~.jpa

        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile image1 = new MockMultipartFile("images", fileName + "." + contentType, contentType, fileInputStream);
        MockMultipartFile image2 = new MockMultipartFile("images", fileName + "." + contentType, contentType, fileInputStream);

//        WebClient web = WebClient.builder().build();
        WebClient web = WebClient.create("https://example.org");
        web.get()
                .uri("/sdsd/sdsd")
                .retrieve()
                .bodyToMono(String.class)
                .block();


        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("files", image1.getResource());
        builder.part("files", image2.getResource());

        Mono<HttpStatus> httpStatusMono = web.post()
                .uri("/images/new")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(HttpStatus.class).thenReturn(response.statusCode());
                    } else {
                        throw new ServiceException("Error uploading file");
                    }
                });
    }

}
