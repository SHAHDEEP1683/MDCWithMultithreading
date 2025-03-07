package com.MT.MDCWithMultiThreading.ThreadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class CustomThreadPoolConfig implements AsyncConfigurer {

   @Bean(name = "customExecutor")
   public Executor taskExecutor() {
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       executor.setCorePoolSize(1);  // Minimum threads
       executor.setMaxPoolSize(2);
       executor.setQueueCapacity(1);
       executor.setThreadNamePrefix("MDC-Async-Thread-");
       executor.setTaskDecorator(new MdcTaskDecorator());
       executor.initialize();
       return executor;
   }
}
