package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  // 설정 정보
@ComponentScan( // @Component 가 붙은 클래스를 찾아서 스프링 빈으로 자동으로 등록해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 컴포넌트 스캔에서 제외할 대상 지정 (여기서는 AppConfig 가 해당)
)
public class AutoAppConfig {
}
