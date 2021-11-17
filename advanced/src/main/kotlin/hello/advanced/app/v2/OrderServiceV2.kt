package hello.advanced.app.v1

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceV2(
    @Autowired private val orderRepository: OrderRepositoryV2,
    @Autowired private val trace: HelloTraceV2
) {

    fun orderItem(traceId: TraceId, itemId: String) {
        var status: TraceStatus?= null
        try {
            status = trace.beginSync(traceId, "OrderService.request()")
            orderRepository.save(status.traceId, itemId);
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
