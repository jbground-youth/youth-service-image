package com.youth.image.domain.entity;

import com.youth.image.application.Generators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private String id; //11ee6d8432fdfa03ac502fa21c756e16 파일명

    @PrePersist
    public void createUniqId() {
        //sequential uuid 생성
        this.id = Generators.prefixUUIDString();
    }

    @Column
    private int index;

    @Column
    private String object_id;

    @Column
    private String object_type;

    @Column
    private String url;

    @Column
    private String extension; //확장자

    @Column
    private LocalDateTime created_at; //생성일시

}

//    https://dnvefa72aowie.cloudfront.net/origin/article/202310/6145e557b68cc96e29a36ee07f4969c7d18eb9d4f2f73a3cefb9492fc45c5bb9.jpg?q=95&s=1440x1440&t=inside&f=webp
//인덱스
//파일명
//오브젝트 타입
//오브젝트 아이디
//저장경로
//파일명
//url

//6145e
//557b6
//8cc96
//e29a3
//6ee07
//f4969
//c7d18
//eb9d4
//f2f73
//a3cef
//b9492
//fc45c
//5bb9 64