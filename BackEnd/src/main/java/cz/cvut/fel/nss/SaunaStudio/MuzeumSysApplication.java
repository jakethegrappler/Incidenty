package cz.cvut.fel.nss.SaunaStudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MuzeumSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuzeumSysApplication.class, args);
	}

}
