package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceV1 {
    private final UserJdbcRepository userJdbcRepository;
    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }
    public void saveUser(UserCreateRequest request){
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request){
        boolean isNotExisted = userJdbcRepository.isUserNotExisted(request.getId());
        if(isNotExisted){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserNow(request.getName(),request.getId());
    }

    public void deleteUser(String name) {
        // 지우려는 사용자가 있는지 체크
        boolean isNotExisted = userJdbcRepository.isUserNotExisted(name);
        if(isNotExisted){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.deleteUserNow(name);
    }
}
