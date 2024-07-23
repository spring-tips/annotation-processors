package com.example.processors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
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
    ApplicationRunner mapstruct() {
        return args -> {
            var dto = CustomerMapper.INSTANCE
                .customerToCustomerDto(new Customer(1, "Felix"));
            System.out.println(dto.id());
        };
    }

}

record Customer(Integer id,  String name) {
}

record CustomerDto(Integer id) {
}

@Mapper
interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer car);
}