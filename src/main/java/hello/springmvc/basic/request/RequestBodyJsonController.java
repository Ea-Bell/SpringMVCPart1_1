package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username": "hello", "age": 20}
 *  content-type : application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username= {}, age = {}",helloData.getUsername(), helloData.getAge());
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJson2(@RequestBody String messageBody) throws JsonProcessingException {

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username= {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "OK";
    }

    /**
     *@RequestBody 객체 파라미터
     *  @RequestBody HelloData data
     *  @RequestBody 에 직접 만든 객체를 지정할 수 있다.
     *
     *  HttpEntity , @RequestBody 를 사용하면 HTTP 메시지 컨버터가 HTTP 메시지 바디의 내용을 우리가 원하는 문자나 객체 등으로 변환해준다.
     *  HTTP 메시지 컨버터는 문자 뿐만 아니라 JSON도 객체로 변환해주는데, 우리가 방금 V2에서 했던 작업을 대신 처리해준다.
     *  자세한 내용은 뒤에 HTTP 메시지 컨버터에서 다룬다.
     *
     * @RequestBody는 생략 불가능
     *  @ModelAttribute 에서 학습한 내용을 떠올려보자.
     *
     *  스프링은 @ModelAttribute , @RequestParam 해당 생략시 다음과 같은 규칙을 적용한다.
     *  String , int , Integer 같은 단순 타입 = @RequestParam
     *  나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
     *
     *  따라서 이 경우 HelloData에 @RequestBody 를 생략하면 @ModelAttribute 가 적용되어버린다.
     *  HelloData data @ModelAttribute HelloData data
     *  따라서 생략하면 HTTP 메시지 바디가 아니라 요청 파라미터를 처리하게 된다.
     *
     * > 주의
     * > HTTP 요청시에 content-type이 application/json인지 꼭! 확인해야 한다. 그래야 JSON을 처리할 수
     * 있는 HTTP 메시지 컨버터가 실행된다.
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData){
        log.info("username= {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException{
        HelloData data = httpEntity.getBody();

        log.info("username= {}, age = {}", data.getUsername(), data.getAge());
        return "OK";
    }

    /**
     *@RequestBody 요청
     *  JSON 요청 HTTP 메시지 컨버터 객체
     * @ResponseBody 응답
     *  객체 HTTP 메시지 컨버터 JSON 응답
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data){
        log.info("username= {}, age = {}", data.getUsername(), data.getAge());
        return data;
    }


}
