package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));
        // save()에 객체를 넣어주면 INSERT가 자동으로 날라감
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll(); // 모든 데이터를 가져온다
        return users.stream().
                map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());

    }
    public void updateUser(UserUpdateRequest request){
       User user =  userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
       user.updateName(request.getName());
       userRepository.save(user);
    }

    public void deleteUser(String name){
       User user =  userRepository.findByName(name);
       // 반환값이 user니까 user가 없다면 null호출
        if(user == null){
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}
