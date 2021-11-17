package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceStatus
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HelloTraceV1Test {

    @Test
    fun begin_end() {
        val trace = HelloTraceV1()
        val status: TraceStatus = trace.begin("hello")
        trace.end(status)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV2()
        val status1: TraceStatus = trace.begin(traceId, "hello")
        val status2: TraceStatus = trace.beginSync(status1.traceId, "hello")
        trace.exception(status2, IllegalStateException())
        trace.exception(status1, IllegalStateException())
    }
}
