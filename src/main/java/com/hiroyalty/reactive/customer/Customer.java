package com.hiroyalty.reactive.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers") // In reactive programming there is no @Entity annotation, we only have Table as seen here
public class Customer {

    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private int age;
}
