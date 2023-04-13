package idusw.springboot.boardlgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //(exclude = DataSourceAutoConfiguration.class)
public class BoardLghApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardLghApplication.class, args);
    }

}
