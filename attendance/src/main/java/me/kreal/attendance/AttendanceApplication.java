package me.kreal.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyStoreException;

@SpringBootApplication
public class AttendanceApplication {

    public static void main(String[] args) throws KeyStoreException {

        SpringApplication.run(AttendanceApplication.class, args);
    }

}
