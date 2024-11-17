package com.hiroyalty.reactive.customer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    // in reactive programming when we need a stream we need a FLUX
    Flux<Customer> findAllByFirstnameContainingIgnoreCase(String firstname);
}
