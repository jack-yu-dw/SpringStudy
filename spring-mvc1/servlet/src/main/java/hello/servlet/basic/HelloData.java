package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {
    // Json 형식으로 파싱할 수 있는 객체

    private String username;
    private int age;
}
