package net.slip.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// UserDao와 비슷한 기능. 
// 이렇게 인터페이스만 만들어 놓으면 spring boot에서 자동으로 bean을 자동으로 생성

public interface UserRepository extends JpaRepository<User, Long>{ // table, pk
	User findByUserId(String userId); // 그냥 인터페이스에만 findBy** 형식만 지켜 선언하면된다. 구체화할 필요 없음.
}

