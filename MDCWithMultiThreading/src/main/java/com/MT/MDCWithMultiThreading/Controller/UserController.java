package com.MT.MDCWithMultiThreading.Controller;

import com.MT.MDCWithMultiThreading.Entity.User;
import com.MT.MDCWithMultiThreading.Service.UserService;
import com.MT.MDCWithMultiThreading.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mdc")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAllUserApi(){
        log.info("Get All Api");
        List<User> allUser = userService.getAllUser();
        log.info("User List : ",allUser);
        return allUser;
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<UserDto> updateUserApi(@PathVariable Long id, @RequestBody UserDto userDto){
//        log.info("In Update Controller, Id : {}", id);
//        return ResponseEntity.ok(userService.updateUSer(id, userDto));
//    }

    @PatchMapping("/{id}")
    public CompletableFuture<ResponseEntity<UserDto>> updateUserApi(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.info("Inside Update Controller, Id : {} | Request ID: {}", id, MDC.get("requestId"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userService.updateUSer(id, userDto).thenApply(ResponseEntity::ok);
    }



}
