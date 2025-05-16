package DuplicateSubmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZT
 * @version 1.0.0
 * @description $
 * @date 2025/2/3
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("重复提交Demo启动成功");
    }
}
