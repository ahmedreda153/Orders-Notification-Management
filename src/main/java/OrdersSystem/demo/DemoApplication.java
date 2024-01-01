// Program Description: Applying Orders and Notifications Management using Spring Boot 
// Last Modification Date: 31/12/2023
// First author - ID : Ahmed Reda El-Sayed / 20210018
// Second author - ID : Habiba Alaa Mohammed / 20210121
// Third author - ID : Salma Mohammed Mahmoud / 20210161
// Fourth author - ID : Shrouk Tarek Ibrahim / 20210175
// Under The Supervision of: Dr. Soha Makady
package OrdersSystem.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
