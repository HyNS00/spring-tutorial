package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// User 객체와 user테이블을 같은 것으로 바라보게 만듦
public class User {
    @Id // primary key로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary Key는 자동생성되는 값이다
    private Long id  = null;

    // @Column을 통해 객체의 name과 테이블의 name을 매핑
    @Column(nullable = false, length = 20) // name varchar(20) , 필드명과 테이블 컬럼 명이 같아서 생략가능
    private String name;
    @OneToMany(mappedBy = "user")
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();
    private Integer age;
    // jpa에는 기본생성자가 꼭 필요
    protected User(){}
    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 (%s)이 들어왔습니다.",name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }
}
