package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    // Spring data jpa는 메서드 이름을 분석한다. -> findby 다음에 오는 name을 엔티티의 속성으로 인식
}
