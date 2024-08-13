package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExisted(long userId){
        // 예외처리를 하기 위한 코드 -> 데이터 조회
        String readSql = "SELECT * FROM user WHERE id = ?";
        // jdbcTemplate.query는 기본적으로 리스트를 반환
        return jdbcTemplate.query(readSql, (rs,rowNum) -> 0, userId).isEmpty();
    }
    public void updateUserNow(String name, long id){
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name,id);
    }

    public boolean isUserNotExisted(String name){
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql,(rs,rowNum) -> 0, name).isEmpty();
    }

    public void deleteUserNow(String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql,name);
    }
}
