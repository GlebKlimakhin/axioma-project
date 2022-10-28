package com.axioma.axiomatrainee.service.scheduler;

import com.axioma.axiomatrainee.repository.IHomeworkRepository;
import com.axioma.axiomatrainee.utill.DateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class HomeworkScheduler {
    private final IHomeworkRepository homeworkRepository;
    private static final long minusMonthTimeUnix = System.currentTimeMillis() - 30 * 86400;

    @Scheduled(fixedRate = 86400000)
    @Transactional
    public void deleteDoneHomeworks() {
        homeworkRepository.findAll()
                .stream()
                .filter(h ->
                    DateParser.parseFromDate(h.getExpirationDate()) <= minusMonthTimeUnix)
                .forEach(homeworkRepository::delete);
    }
}
