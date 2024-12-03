package org.example.msapiproducts;

import org.example.cachemodule.CachingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CachingConfig.class)
public class MsApiProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiProductsApplication.class, args);
    }

}
