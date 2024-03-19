package com.youth.image.presentation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ImageControllerTest {

    @Autowired
    private MockMvc mvc;

    private String object_id;

    @BeforeAll
    void before(){
        object_id = "5121112";
    }


    @Test
    void post() throws Exception {
        final String fileName = "testImage";
        final String contentType = "jpg";
        final String filePath = "src/test/resources/testImage/" + fileName + "." + contentType;
        //upload/product/202310/~.jpa

        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile image1 = new MockMultipartFile("images", fileName + "." + contentType, contentType, fileInputStream);
        MockMultipartFile image2 = new MockMultipartFile("images", fileName + "." + contentType, contentType, fileInputStream);


        ResultActions actions = mvc.perform(
                multipart("/images/new")
                        .file(image1)
                        .file(image2)
                        .param("title", "제목1")
                        .param("content", "내용2")
        ).andExpect(status().isOk());

        MvcResult mvcResult = actions.andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 201);

    }
    
    
    @Test
    void get() throws Exception {



        mvc.perform(MockMvcRequestBuilders.get("/fdf/d").param("object_id", object_id))
                .andExpect(status().isOk())
                .andExpect(content().string("This will return posts's URI."));


    }
    
    //    https://www.daangn.com/images?image_index=1&object_id=657902233&object_type=Article

    // localhost:8979/images?image_index=0&object_id=5311230&object_type=Product
    //이렇게 요청하면 url 한 개 리턴

    //localhost:8979/images?object_id=5311230 //이렇게 요청하면 url 세개 리턴

    //생성
    //localhost:8979/images/new generation
    //
}
