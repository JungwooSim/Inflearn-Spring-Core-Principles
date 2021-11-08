package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Slf4j
@Component
class HelloTraceV2 {
    companion object {
        val START_PREFIX: String = "-->"
        val COMPLETE_PREFIX: String = "<--"
        val EX_PREFIX: String = "<X-"
        val logger = KotlinLogging.logger {}
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs:Long = System.currentTimeMillis()

        // log
        logger.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)

        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(beforeTraceId: TraceId, message: String): TraceStatus {
        val nextId = beforeTraceId.createNextId()
        val startTimeMs:Long = System.currentTimeMillis()

        // log
        logger.info("[{}] {}{}", nextId.id, addSpace(START_PREFIX, nextId.level), message)

        return TraceStatus(nextId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus?, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus?, e: Exception?) {
        val stopTimeMs:Long = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status!!.startTimeMs
        val traceId: TraceId = status.traceId

        if (e == null) {
            logger.info("[{}] {}{} time={}ms", traceId.id, addSpace(COMPLETE_PREFIX, traceId.level), status.message, resultTimeMs)
        } else {
            logger.info("[{}] {}{} time={}ms ex={}", traceId.id, addSpace(EX_PREFIX, traceId.level), status.message, resultTimeMs, e.toString())
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        var sb: StringBuilder = StringBuilder()

        for (i:Int in 0..level)
            sb.append(if(i == level - 1) "|$prefix" else "|    ")

        return sb.toString()
    }
}
