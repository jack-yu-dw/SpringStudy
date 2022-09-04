package hello.servlet.web.servlet.frontcontroller.v4;

import hello.servlet.web.servlet.frontcontroller.ModelView;
import hello.servlet.web.servlet.frontcontroller.MyView;
import hello.servlet.web.servlet.frontcontroller.v3.ControllerV3;
import hello.servlet.web.servlet.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.servlet.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.servlet.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.servlet.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.servlet.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.servlet.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    //컨트롤러 매핑 정보
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        //paramMap 과 model 을 넘겨주는 part
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        //논리이름 -> 물리이름 (view resolver)
        MyView view = viewResolver(viewName);

        //Model과 View를 함께 넘겨줌
        view.render(model, request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //model 자체도 parameter 로 전달함으로써 다른 구현 controller 의 코드들이 단순해짐

    /**
     * 이번 버전의 컨트롤러들은 매우 단순하고 실용적이다.
     * 기존 구조에서 모델을 파라미터로 넘기고, 뷰의 논리 이름을 반환한다는 작은 아이디어를 적용했을 뿐이다.
     * 그 결과, 컨트롤러를 구현하는 개발자 입장에서는 이제 군더더기 없는 단순하고 간단한 코드를 작성할 수 있다.
     */
}
