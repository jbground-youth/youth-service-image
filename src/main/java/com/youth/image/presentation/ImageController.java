package com.youth.image.presentation;

import com.youth.image.application.ImageUploader;
import com.youth.image.domain.entity.ApiResult;
import com.youth.image.domain.entity.ErrorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class ImageController {

    @Resource
    private ImageUploader uploader;


    @PostMapping(value = "/images")
    public ResponseEntity<ApiResult> createImages(@RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response){

        try{

            //파일 S3에 업로드


            //업로드 성공할 경우 DB 파일 정보 저장


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .status(ErrorStatus.RUNTIME_EXCEPTION.getStatus())
                    .body(ApiResult.builder()
                            .status("error")
                            .message(e.getMessage())
                            .build());
        }



        return ResponseEntity.ok(ApiResult.builder()
                .status("success")
                .message("")
                .build());
    }

    /**
     * 입력받은 정보로 DB에서 파일정보 조회를 통해 S3의 url 획득
     * @param object_id 게시글 ID
     * @param object_type 게시글 타입
     */
    @GetMapping(value = "/images")
    public ResponseEntity<String> findImages(@RequestParam("object_id") String object_id, @RequestParam("object_type") String object_type){

        //DB 조회



        //조회한 데이터로 ULR 획득




        return ResponseEntity.ok("");
    }
}
