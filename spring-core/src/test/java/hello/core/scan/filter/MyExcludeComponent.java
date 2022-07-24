package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    // MyExcludeComponent 가 붙은 클래스는 컴포넌트 스캔 대상에서 제외한다.
}
