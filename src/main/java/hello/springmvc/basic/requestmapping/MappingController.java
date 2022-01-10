package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "OK";
    }


    @RequestMapping(value = "/hello-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("helloBasic");
        return "OK";
    }

    /**
     * 편리한 축약 애노테이션(코드보기)
     *
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapoping-get-v2");
        return "OK";
    }

    /**
     * PathVatiable 사용(경로변수)
     * 변수명이 같으면 생략 가능
     *
     * @PathVariable("userId")String userId -> @PathVariable uerId
     * /mapping/userA
     */
 /*   1번
 @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
    log.info("mappingPath userId={}", data);
        return "OK";
    }*/
    //2번 변수명이랑 PathVariable("pathName")이 같을 경우 생략 가능
    @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "OK";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "OK";
    }

    /**
     * 잘 사용하지 않는다.
     * 파라미터로 추가 매핑
     * params = "mode"
     * params = "!mode"
     * params = "mode=debug"
     * params = "mode!=debug"(!=)
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "OK";
    }

    /**
     * 특정 헤도로 추가 매핑
     * headers = "mode"
     * headers = "!mode"
     * headers = "mode=debug"
     * headers = "mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingGeader(){
        log.info("mappingHeader");
        return "OK";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes = "application/json"
     * consumes = "!application/json"
     * consumes = "application/*"
     * consumes = "*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "OK";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "OK";
    }
}
