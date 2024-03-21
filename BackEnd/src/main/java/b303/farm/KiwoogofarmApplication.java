package b303.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KiwoogofarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiwoogofarmApplication.class, args);
	}

}
