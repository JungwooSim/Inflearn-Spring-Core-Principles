package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceStatus
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HelloTraceV2Test {

    @Test
    fun begin_end() {
        val trace = HelloTraceV2()
        val status1: TraceStatus = trace.begin("hello1")
        val status2: TraceStatus = trace.beginSync(status1.traceId, "hello2")
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV2()
        val status: TraceStatus = trace.begin("hello")
        trace.exception(status, IllegalStateException())
    }
}
