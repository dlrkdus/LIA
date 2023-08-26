package com.project.LIA.service;

import com.project.LIA.domain.NoteDomain;
import com.project.LIA.domain.UserDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    // 쪽지 찾기 ( 사용자 , 수신인 )
    List<NoteDomain> findNote(UserDomain user, UserDomain receiver);

    // 쪽지 찾기 ( 내가 쓴 쪽지 )
    List<NoteDomain> findMyNote(UserDomain user);

    // 쪽지 찾기 ( 내가 받은 쪽지 )
    List<NoteDomain> findreceivNote(UserDomain receiver);

    // 쪽지 쓰기 ( user = 작성자 / receiver = 상대 )
    void writeNote(NoteDomain note);

    // 내가 쓰거나 받은 쪽지 (가장 최신 쪽지를 위로)
    List<NoteDomain> findMyNoteList(UserDomain user);
}
