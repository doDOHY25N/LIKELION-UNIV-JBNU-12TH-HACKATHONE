package com.lion.chon.repository;

import com.lion.chon.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> { // 수정된 부분
}
