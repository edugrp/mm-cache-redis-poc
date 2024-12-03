package org.example.msapicustomers;

import org.example.cachemodule.CachingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CachingConfig.class)
public class MsApiCustomersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiCustomersApplication.class, args);
    }

}
