package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {
    // private final List<User> users = new ArrayList<>();
    // 유저를 필드로 관리하던 것을 db와 연결해서 사용
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") //POST/user
    public void saveUser(@RequestBody UserCreateRequest request){
        String sql ="INSERT INTO user (name,age) VALUES (?,?)";
        jdbcTemplate.update(sql,request.getName(),request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql,new RowMapper<UserResponse>(){
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id,name,age);
            }
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        // 예외처리를 하기 위한 코드 -> 데이터 조회
        String readSql = "SELECT * FROM user WHERE id = ?";
        // jdbcTemplate.query는 기본적으로 리스트를 반환
        boolean isNotExisted = jdbcTemplate.query(readSql, (rs,rowNum) -> 0, request.getId()).isEmpty();
        // 값이 존재한다면 [0]반환, 아니라면 빈 리스트
        if(isNotExisted){
            throw new IllegalArgumentException();
        }
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        String readSql = "SELECT * FROM user WHERE name = ?";
        boolean isNotExisted = jdbcTemplate.query(readSql,(rs, rowNum) -> 0, name).isEmpty();
        if(isNotExisted){
            throw new IllegalArgumentException();
        }
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

}
