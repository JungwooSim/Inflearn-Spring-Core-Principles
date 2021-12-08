package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    @Retry(value = 4) // 수정 가능, 생략도 ㄱㄱ가능
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
