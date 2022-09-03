package hello.servlet.web.servlet.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //Controller 에서 View 로 이동할 때 request.getRequestDispatcher 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //dispatcher.forward 를 통해 다른 서블릿이나 JSP 로 이동할 수 있음.
        //서버 내부에서 다시 호출이 발생하는 것이고, 리다이렉트가 아님.
        dispatcher.forward(request, response);
        /**
         * redirect vs forward
         * 리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청한다.
         * 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. (2번 호출)
         * 반면에 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다. (1번 호출)
         */
    }
}
