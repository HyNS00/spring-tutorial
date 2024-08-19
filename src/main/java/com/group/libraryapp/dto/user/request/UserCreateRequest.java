package com.group.libraryapp.dto.user.request;

import lombok.Getter;

@Getter // getter 자동생성
public class UserCreateRequest {
    private String name;
    private Integer age; // null값이 가능하므로 wrapper 클래스인 Integer


}
