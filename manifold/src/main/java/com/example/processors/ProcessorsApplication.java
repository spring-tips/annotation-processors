package com.example.processors;

import manifold.ext.rt.api.Jailbreak;
import manifold.science.measures.Length;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static manifold.science.util.UnitConstants.km;
import static manifold.science.util.UnitConstants.mi;

@SpringBootApplication
public class ProcessorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorsApplication.class, args);
    }

    private static void log(String o, Object... args) {
        System.out.println(o.formatted(args));
    }

    @Bean
    ApplicationRunner manifold() {
        return args -> {
            // tuples work
            var namedTuple = (name:"Josh", twitter:"starbuxman", youtube:"https://youtube.com/@coffeesoftware");
            log("name tuple youtube: %s", namedTuple.youtube);

            var anonymousTuple = (1, 2, 3);
            log("item2: %s", anonymousTuple.item2);

            // tuples work
            @Jailbreak // why doesn't this work w/ `var`?
            Cart cart = new Cart(new Customer("Josh", new Address("777 Spring St.", "88888")));
            log("the total is %s", cart.total.get());

            var distanceInKm = (Length) (4 * 2km);
            log("distance in Kilometers: " + distanceInKm);

            var distanceInMiles = (Length) (5 * 2mi);
            log("distance in Miles: " + distanceInMiles);

            var data = new ArrayList<Number>(List.of(1, 1, 2, 3, 4, 5, 5));
            log("chaos! " + data.chaos());

            cart += new LineItem("spam", 42.7f);
            cart += new LineItem("eggs", 777.42f);
            cart += new LineItem("java", 8888.88f);
            log("total: " + cart.total.get());
        };
    }

}

class Cart {

    private final List<LineItem> lineItems = new ArrayList<>();

    private final AtomicReference<Double> total = new AtomicReference<>(0.0d);

    private final Customer customer;

    Cart(Customer customer) {
        this.customer = customer;
    }

    public Cart plus(LineItem lineItem) {
        this.lineItems.add(lineItem);
        this.recalc();
        return this;
    }

    private void recalc() {
        synchronized (this.lineItems) {
            this.total.set(this.lineItems.stream()
                    .map(LineItem::price)
                    .mapToDouble(Float::doubleValue)
                    .sum());
        }
    }

}

record LineItem(String product, float price) {
}

record Address(String street, String zipcode) {
}

record Customer(String name, Address address) {
}
