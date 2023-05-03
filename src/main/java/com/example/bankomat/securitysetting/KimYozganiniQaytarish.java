package com.example.bankomat.securitysetting;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class KimYozganiniQaytarish {
    AuditorAware<Integer> auditorAware(){
        return new KimYozganiniBilish();
    }
}
