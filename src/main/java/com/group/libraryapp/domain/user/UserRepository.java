package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    // Spring data jpa는 메서드 이름을 분석한다. -> findby 다음에 오는 name을 엔티티의 속성으로 인식
}
