package com.shop.service;

import com.shop.entity.Answer;
import com.shop.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer addAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> findAnswer() {
        return answerRepository.findAll();
    }


    public void createAnswer(String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        this.answerRepository.save(answer);
    }

    public Page<Answer> getAnswerPage(Pageable pageable, Long itemId) {
        return answerRepository.findAll(pageable, itemId);
    }

    public Long commentCount(Long itemId) {
        return answerRepository.countBy(itemId);
    }


}
