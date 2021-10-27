package hello.advanced.trace

import java.util.*

class TraceId() {
    var id: String = createId()
    var level: Int = 0

    constructor(id: String, level: Int) : this() {
        this.id = id
    }

    private fun createId(): String {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    fun createNextId(): TraceId {
        return TraceId(id, level + 1)
    }

    fun createPreviousId(): TraceId {
        return TraceId(id, level - 1)
    }

    fun isFirstLevel(): Boolean {
        return level == 0
    }
}
