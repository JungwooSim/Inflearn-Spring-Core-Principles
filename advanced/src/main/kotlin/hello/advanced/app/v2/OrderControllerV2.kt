package hello.advanced.app.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV2(
    @Autowired private val orderService: OrderServiceV2,
    @Autowired private val trace: HelloTraceV2
) {
    @GetMapping("/v2/request")
    fun request(itemId: String): String {

        var status: TraceStatus ?= null
        try {
            status = trace.begin("OrderController.request()")
            orderService.orderItem(status.traceId, itemId)
            trace.end(status)
            return "ok"
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }

    }
}
