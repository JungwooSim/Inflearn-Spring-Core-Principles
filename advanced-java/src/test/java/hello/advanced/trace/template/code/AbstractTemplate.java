package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        call();

        //비즈니스 로직 종료
        long endTIme = System.currentTimeMillis();
        long resultTime = endTIme - startTime;
        log.info("resultTime = {}", resultTime);
    }

    protected abstract void call();
}
