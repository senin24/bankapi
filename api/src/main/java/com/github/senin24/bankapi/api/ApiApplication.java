package com.github.senin24.bankapi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Server;

import java.sql.SQLException;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) throws SQLException {
        Server.createTcpServer().start(); //jdbc:h2:tcp://localhost/mem:testdb
        SpringApplication.run(ApiApplication.class, args);
    }
}
