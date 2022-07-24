package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // MyIncludeComponent 가 붙은 클래스는 컴포넌트 스캔 대상으로 추가한다.
}
