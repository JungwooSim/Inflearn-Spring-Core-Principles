package hello.advanced.app.v1

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2(@Autowired private val trace: HelloTraceV2) {

    fun save(traceId: TraceId, itemId: String) {

        var status: TraceStatus?= null
        try {
            status = trace.begin("OrderRepository.request()")

            //저장 로직
            if (itemId.equals("ex")) {
                throw IllegalArgumentException("예외 발생!")
            }
            sleep(1000)

            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
        }
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
