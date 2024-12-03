package org.example.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Customer implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private float income;

    public Customer(long id, String firstName, String lastName, float income) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.income = income;
    }

}
