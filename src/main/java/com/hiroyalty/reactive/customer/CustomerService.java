package com.hiroyalty.reactive.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CustomerService {

    // though all calls are already non-blocking since we are using ReactiveRepository, I added the various
    // calls to simulate the non-blocking calls you would need in a reactive program.
    private final CustomerRepository customerRepository;

    public Mono<Customer> save(CustomerRequest request) {
                return customerRepository.save(
                Customer.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .age(request.getAge())
                        .build()
        );
        // Wrapping in Mono.fromSupplier to ensure the creation of Customer object is non-blocking
//        return Mono.fromSupplier(() -> Customer.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .age(request.getAge())
//                .build())
//                .flatMap(customerRepository::save);
    }

    public Flux<Customer> findAll() {
        // here we showcase the real world scenario where we apply a treatment or logic to return data
        // without blocking the user by returning each data when they become available.
        return customerRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

    public Mono<Customer> findById(Long id) {
        return customerRepository.findById(id);
        // Wrapping in Mono.defer to handle any potential blocking operation
        //return Mono.defer(() -> customerRepository.findById(id));
    }

    public Flux<Customer> findByFirstname(String firstname) {
        return customerRepository.findAllByFirstnameContainingIgnoreCase(firstname);

        // Wrapping in Mono.defer ensures that the logic is deferred until subscription time
//        return Mono.defer(() -> Mono.just(firstname))
//                .flatMapMany(customerRepository::findAllByFirstnameContainingIgnoreCase);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id).subscribe();
        // Wrapping delete logic to make it fully reactive
        //return Mono.defer(() -> customerRepository.deleteById(id));
    }
}
