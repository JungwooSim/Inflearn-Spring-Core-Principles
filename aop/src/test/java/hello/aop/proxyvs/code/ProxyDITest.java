package hello.aop.proxyvs.code;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    /**
     * JDK 동적 프록시는 구현체에 의존관계를 주입할 수 없다.
     * CGLIB 프록시는 구현체에 의존관계를 주입할 수 있다.
     */

    @Autowired
    MemberService memberService;

//    @Autowired
//    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
//        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
//        memberServiceImpl.hello("hello");
    }
}
