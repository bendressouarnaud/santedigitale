package com.maternite.gestion.mesobjets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TacheSynchrone {
    @Bean(name="taskExecutor")
    public Executor taskExecutor(){
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); /* number of THREADS that will be running
        tasks in the pool */
        executor.setQueueCapacity(15);
        executor.setMaxPoolSize(200);/* number of tasks that  */
        executor.setThreadNamePrefix("MesThreads");
        executor.initialize();
        return executor;
    }
}
