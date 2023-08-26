package com.project.LIA.service;

import com.project.LIA.domain.QnADomain;
import com.project.LIA.repository.QnARepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QnAServiceImpl implements QnAService {

    private QnARepository qnARepository;

    public QnAServiceImpl(QnARepository qnaRepository){
        this.qnARepository = qnaRepository;

    }
    @Override   // 질문 리스트
    public List<QnADomain> getAllQnA() {
        return qnARepository.findAll();
    }

//    @Override   // 해당 Id 질문하기
//    public QnADomain createQnA(QnADomain qnalist) {
//        return qnaRepository.save(qnalist);
//    }

    @Override   // 해당 Id 질문검색
    public QnADomain getQnAById(Long user) {
        return (QnADomain) qnARepository.findById(user).orElse(null) ;
    }
//
    @Override   // 해당 Id QnA 항목 수정
    public QnADomain updateQnA(Long user, QnADomain question) {
        QnADomain existingQnA = (QnADomain) qnARepository.findById(user).orElse(null);
        if (existingQnA != null){
            existingQnA.setQuestion(question.getQuestion());
            existingQnA.setAnswer(question.getAnswer());
            return qnARepository.save(existingQnA);
        } else
        return null;
    }

    @Override   // 해당 Id QnA 항목수정
    public void deleteQnA(Long user) {
        qnARepository.deleteById(user);
    }

    @Override // 페이지가 표시된 질문 및 답변 목록 검색
    public List<QnADomain> getAllQnA(Pageable pageable) {

        return qnARepository.findAll();
    }

}