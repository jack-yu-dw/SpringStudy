package hello.servlet.web.servlet.frontcontroller.v1;

import hello.servlet.web.servlet.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.servlet.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.servlet.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // /front-controller/v1/ 의 모든 하위호출에 대해서는 이 서블릿 호출.
public class FrontControllerServletV1 extends HttpServlet {

    //컨트롤러 매핑 정보
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        //request.getRequestURI() 를 통해서 localhost:8080/front-controller/v1/members/new-form 이라 했을 때,
        //front-controller/v1/members/new-form 을 바로 받을 수 있다.
        String requestURI = request.getRequestURI();

        //그리고 해당 URI 를 key 로 하여 Map 에서 해당 Controller 를 찾는다. (다형성에 의해 인터페이스로 받을 수 있음)
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        //Controller 가 잘 조회되었다면, 인터페이스의 메서드 호출해주면 된다.
        //다형성에 의해 Override 된 메서드가 호출될 것.
        controller.process(request, response);
    }
}
