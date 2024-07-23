package com.example.processors;

import io.soabase.recordbuilder.core.RecordBuilder;
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
    ApplicationRunner recordBuilder() {
        return args -> {

            // 'withers'
            var huiren = new RecordBuilderCustomer(null, null, true);
            var wooHuiren = RecordBuilderCustomerBuilder
                    .from(huiren)
                    .withName("Huiren");

            // no 'withers'
            var noPhpHuiren = RecordBuilderCustomerBuilder
                    .builder(huiren)
                    .name("Woo Huiren")
                    .usesPhp(false)
                    .build();

            System.out.println(noPhpHuiren);
        };
    }
}

@RecordBuilder
record RecordBuilderCustomer(Integer id, String name, boolean usesPhp) {
}
