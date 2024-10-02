package com.example.ticketing_service;

import com.example.ticketing_service.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarter {
    @Autowired
    private Menu menu;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            menu.displayMenu();
        };
    }
}
