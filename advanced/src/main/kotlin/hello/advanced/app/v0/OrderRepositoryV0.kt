package hello.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {

    fun save(itemId: String) {
        //저장 로직
        if (itemId.equals("ex")) {
            throw IllegalArgumentException("예외 발생!")
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
