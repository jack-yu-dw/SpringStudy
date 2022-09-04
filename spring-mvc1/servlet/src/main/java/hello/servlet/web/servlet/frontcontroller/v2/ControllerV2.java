package hello.servlet.web.servlet.frontcontroller.v2;

import hello.servlet.web.servlet.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    //기존 v1에서는 컨트롤러에서 forward 하여 jsp 로 이동했는데, 이제는 MyView 를 만들어서 해당 객체를 반환하도록 헀음.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
