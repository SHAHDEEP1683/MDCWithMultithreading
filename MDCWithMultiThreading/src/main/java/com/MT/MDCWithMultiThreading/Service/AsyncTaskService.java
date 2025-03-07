package com.MT.MDCWithMultiThreading.Service;

import com.MT.MDCWithMultiThreading.Entity.User;
import com.MT.MDCWithMultiThreading.Repository.UserRepository;
import com.MT.MDCWithMultiThreading.dto.UserDto;
import com.MT.MDCWithMultiThreading.mapper.Usermapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncTaskService {

    private final UserRepository userRepository;

    @Async
    public CompletableFuture<Void> processTask(Long taskId) {
        String requestId = MDC.get("requestId");
        System.out.println("[Async Thread] Processing Task " + taskId + " with ID: " + requestId);
        return CompletableFuture.completedFuture(null);
    }

}
