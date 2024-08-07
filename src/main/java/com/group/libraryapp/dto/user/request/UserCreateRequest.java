package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name;
    private Integer age; // null값이 가능하므로 wrapper 클래스인 Integer

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
