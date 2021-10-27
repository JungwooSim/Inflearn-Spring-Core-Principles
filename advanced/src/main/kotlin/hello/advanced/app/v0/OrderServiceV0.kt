package hello.advanced.app.v0

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceV0(@Autowired private val orderRepository: OrderRepositoryV0) {
    fun orderItem(itemId: String) {
        orderRepository.save(itemId);
    }
}
