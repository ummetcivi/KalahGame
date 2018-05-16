package com.ummetcivi.kalahgame.config;

import com.ummetcivi.kalahgame.repository.GameRepository;
import com.ummetcivi.kalahgame.repository.impl.InMemoryGameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("inMemory")
public class InMemoryStorageConfig {
    @Bean
    public GameRepository gameRepository() {
        return new InMemoryGameRepository();
    }
}
