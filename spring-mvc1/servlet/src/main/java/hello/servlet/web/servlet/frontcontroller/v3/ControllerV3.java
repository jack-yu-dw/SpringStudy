package hello.servlet.web.servlet.frontcontroller.v3;

import hello.servlet.web.servlet.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    //서블릿에 종속적이지 않음.
    ModelView process(Map<String, String> paramMap);
}
