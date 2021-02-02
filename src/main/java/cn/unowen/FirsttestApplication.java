package cn.unowen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.unowen.mapper")
@EnableSwagger2
public class FirsttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirsttestApplication.class, args);
	}

}
