package com.karalexsandr.coreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreServiceApplication.class, args);
        String message = "HELLO";
        Hello hello = new Hello();
        hello.sendMessage(message);
    }

}
