package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(UserCreateRequest request){
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request){
        boolean isNotExisted = userRepository.isUserNotExisted(request.getId());
        if(isNotExisted){
            throw new IllegalArgumentException();
        }
        userRepository.updateUserNow(request.getName(),request.getId());
    }

    public void deleteUser(String name) {
        // 지우려는 사용자가 있는지 체크
        boolean isNotExisted = userRepository.isUserNotExisted(name);
        if(isNotExisted){
            throw new IllegalArgumentException();
        }
        userRepository.deleteUserNow(name);
    }
}
