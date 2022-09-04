package hello.servlet.web.servlet.frontcontroller.v3;

import hello.servlet.web.servlet.frontcontroller.ModelView;
import hello.servlet.web.servlet.frontcontroller.MyView;
import hello.servlet.web.servlet.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.servlet.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.servlet.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //컨트롤러 매핑 정보
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        //paramMap 을 넘겨주는 part
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        //논리이름 -> 물리이름 (view resolver)
        String viewName = mv.getViewName(); //논리이름 ; new-form
        MyView view = viewResolver(viewName);

        //Model과 View를 함께 넘겨줌
        view.render(mv.getModel(), request, response);
    }

    //paramMap 을 넘겨주는 part
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    //논리이름 -> 물리이름 (view resolver)
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
        // 이렇게 논리적 이름과 물리적 이름을 나눠놓으면, 이와 관련한 변경사항(경로 변경 등) 이 발생했을 때,
        // controller 는 건드리지 않고 이 viewResolver 의 코드만 변경하면 된다는 장점이 있다.
    }

    //서블릿 종속성이 제거되고, View 이름의 중복도 제거되었음.
}
