package com.jingnan.data.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
@EnableAsync //启用异步任务
public class ThreadPoolConfig {
//    @Bean
//    public ThreadPoolTaskExecutor executor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        //配置核心线程数
//        executor.setCorePoolSize(15);
//        //配置最大线程数
//        executor.setMaxPoolSize(30);
//        //配置队列大小
//        executor.setQueueCapacity(1000);
//        //线程的名称前缀
//        executor.setThreadNamePrefix("Executor-");
//        //等待所有任务结束后再关闭线程池
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        //执行初始化
//        executor.initialize();
//        return executor;
//    }

    @Bean("deleteExecutorService")
    public ExecutorService getThreadPool(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService taskExe = new ThreadPoolExecutor(1, 5, 200L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return taskExe;
    }
}

