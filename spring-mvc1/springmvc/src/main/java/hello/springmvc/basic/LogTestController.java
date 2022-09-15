package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
// @Controller 의 경우 return 값을 String 으로 주게 되면 View 이름이 반환되는 것.
// @RestController 를 사용하면 return 값이 String 일 경우 http message body 에 해당 문자를 바로 넣어버림.
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // sout 같은 경우는 레벨을 나눌 수 없고, 자세한 정보도 알기 어려움.
        System.out.println("name = " + name);

        // log 를 레벨을 나눠서 볼 수 있음.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        // log.trace("trace log="+ name) 과 같이 써도 똑같이 출력되지만, 이렇게 사용하면 안됨.
        // 이건 로그 출력 여부를 결정하기 전에 concat 에 필요한 연산을 실행하기 때문.

        // log.debug("data={}", data)
        // 이것이 로그의 올바른 사용법이고, 이렇게 해야 무의미한 연산이 일어나지 않는다.

        return "ok";
    }
}
