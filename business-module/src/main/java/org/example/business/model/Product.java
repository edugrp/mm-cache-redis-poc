package org.example.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Product implements Serializable {

    private long id;
    private String name;

    public Product(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}
