package pl.kurs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MonitoringApp {
    public static void main(String[] args) {
        SpringApplication.run(MonitoringApp.class);
    }
}
