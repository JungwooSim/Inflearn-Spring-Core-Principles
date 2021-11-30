package hello.proxy.jdkdynamic.code;

public class BImpl implements BInterface {
    @Override
    public String call() {
        log.info("B 호출");
        return "b";
    }
}
