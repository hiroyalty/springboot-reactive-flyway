package com.hiroyalty.reactive.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody CustomerRequest request) {
        return customerService.save(request);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Customer> findById(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/filter")
    public Flux<Customer> findByAuthor(@RequestParam String name) {
        return customerService.findByFirstname(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
