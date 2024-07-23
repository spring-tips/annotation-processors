package com.example.processors;

import lombok.Data;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorsApplication.class, args);
    }


    @Bean
    ApplicationRunner lombok() {
        return args -> {
            var pc = new LombokCustomer(null, "Josh", false);
            System.out.println(pc.toString());
        };
    }

}


// LOMBOK
@Data
class LombokCustomer {
    private final Integer id;
    private final String name;
    private final boolean usesPhp;
}

