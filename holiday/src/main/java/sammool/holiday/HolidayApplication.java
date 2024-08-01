package sammool.holiday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class HolidayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayApplication.class, args);


	}

}
