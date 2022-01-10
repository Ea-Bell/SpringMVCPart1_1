package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 요청 파라미터를 순차적으로 생략하는걸 보여줌.
     */
    @RequestMapping("/request-param-v1")
    public  void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username")String memberName,
            @RequestParam("age")int memberAge){
        log.info("username = {}, age = {}", memberName, memberAge);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
      log.info("username = {}, memberAge = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
            log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    /**
     * 필수 파라미터 여부 가능
     */

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true)String username,
            @RequestParam(required = false)Integer age){
        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam( defaultValue = "guest") String username,
            @RequestParam( defaultValue = "-1") int age){
        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    /**
     * 파라미터를 Map으로 조회하기
     * MultiValueMap도 있음
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String , Object>paramMap){
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }

    //HTTP 요청 파라미터 - @ModelAttribute 작성
    
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }
}
