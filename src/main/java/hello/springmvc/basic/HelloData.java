package hello.springmvc.basic;

import lombok.Data;


/**
 * @Data를 쓰면
 * @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 자동 생성해준다.
 */
@Data
public class HelloData {
    private String username;
    private int age;
}
