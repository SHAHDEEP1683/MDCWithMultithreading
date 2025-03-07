package com.MT.MDCWithMultiThreading.Service;

import com.MT.MDCWithMultiThreading.Entity.User;
import com.MT.MDCWithMultiThreading.Repository.UserRepository;
import com.MT.MDCWithMultiThreading.dto.UserDto;
import com.MT.MDCWithMultiThreading.mapper.Usermapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Usermapper mapper;

    public List<User> getAllUser(){
        log.info("Get All Service Layer");
        return userRepository.findAll();
    }


   @Async("customExecutor")
    @Transactional(rollbackFor = Exception.class)
    public CompletableFuture<UserDto> updateUSer(Long id, UserDto userDto) {
        log.info("Processing request for User ID: {} | Thread Name: {} | Request ID: {}",
                id, Thread.currentThread().getName(), MDC.get("requestId"));

        var existingUser = userRepository.findById(id).orElseThrow();
        log.info("User found: {} | Processing on Thread: {}", existingUser, Thread.currentThread().getName());

        var updatedUser = mapper.toUser(userDto, existingUser);
        log.info("Updated User: {} | Thread Name: {}", updatedUser, Thread.currentThread().getName());

        var saved = userRepository.save(updatedUser);
        log.info("Saved User: {} | Processed by Thread: {}", saved, Thread.currentThread().getName());

        return CompletableFuture.completedFuture(mapper.toUserDto(updatedUser));
    }



}
