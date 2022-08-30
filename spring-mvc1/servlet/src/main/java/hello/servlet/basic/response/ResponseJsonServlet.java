package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        //HTTP 응답으로 JSON 을 반환할 때는 content-type 을 application/json 으로 지정해야 함
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("yu");
        helloData.setAge(20);

        //{"username":"kim", "age":20}
        //Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString() 을 사용하여 객체를 JSON 문자로 변경 가능
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
