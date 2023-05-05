package com.eoi.springwebsecurity;

import com.eoi.springwebsecurity.filemanagement.services.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


/**
 * The type Spring web security application.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class SpringWebSecurityApplication implements CommandLineRunner {

    /**
     * The File system storage service.
     */
    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringWebSecurityApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        fileSystemStorageService.init();





    }
}
