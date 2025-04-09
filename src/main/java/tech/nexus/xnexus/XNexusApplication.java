package tech.nexus.xnexus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("tech.nexus.xnexus.mapper")
public class XNexusApplication {

    public static void main(String[] args) {
        SpringApplication.run(XNexusApplication.class, args);
    }

}
