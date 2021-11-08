package hello.advanced.app.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceV1(
    @Autowired private val orderRepository: OrderRepositoryV1,
    @Autowired private val trace: HelloTraceV1
) {

    fun orderItem(itemId: String) {
        var status: TraceStatus?= null
        try {
            status = trace.begin("OrderService.request()")
            orderRepository.save(itemId);
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
