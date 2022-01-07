package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name ="Spring";


        // 이 밑으로가 레벨 맨 위부터 아래로 내려간다. 맨위가 1.
        //절대로 로그 찍을때 log.trace("trace log = {} " + name); <<<< + 를 쓰면 안된다.
        //이유는 +를 쓰면 메모리에서 연산을 한번더 하기떄문에 쓸대없는 메모리 낭비가 생기기 떄문이다.
        log.trace("trace log ={} ",name);
        log.debug("debug log ={} ",name);
        log.info("info log={} ", name);
        log.warn("warn log ={} ",name);
        log.error("error log ={} ",name);


        return "ok";

    }
}
