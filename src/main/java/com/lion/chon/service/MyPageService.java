package com.lion.chon.service;

import com.lion.chon.dto.MyPageDTO;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;

    // 마이페이지 유저 정보 조회
    public MyPageDTO getUserProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        return userRepository.findById(username)
                .map(MyPageDTO::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 마이페이지 유저 정보 수정
    public MyPageDTO updateProfile(MyPageDTO myPageDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userEntity.setName(myPageDTO.getName());
        userEntity.setEmail(myPageDTO.getEmail());
        userEntity.setPhoneNum(myPageDTO.getPhoneNum());
        userEntity.setBirth(myPageDTO.getBirth());
        userEntity.setResidence(myPageDTO.getResidence());
        userEntity.setAge(myPageDTO.getAge());
        userEntity.setGender(myPageDTO.getGender());

        userRepository.save(userEntity);
        return new MyPageDTO(userEntity);
    }

    public void withdrawl() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();
        userRepository.deleteById(username);
    }
}
