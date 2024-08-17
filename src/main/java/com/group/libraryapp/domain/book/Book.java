package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 데이터베이스가 자동으로 ID값을 생성하도록 지정
    // auto-increment사용
    private Long id = null;

    @Column(nullable = false, length = 255, name = "name")
    private String name;

    protected Book(){
        // JPA 스펙에 따르면 엔티티 클래스는 반드시 인자가 없는 기본 생성자를 가져야 한다.
    }


    public Book(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 %s이 들어왔습니다.",name));
        }
        this.name = name;
    }
}
