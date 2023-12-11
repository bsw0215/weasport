package com.connect.weasport.repository;

import com.connect.weasport.domain.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Integer> {

    @Modifying
    @Query(value="INSERT INTO boardreply(userId, boardId, content, createdAt) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌.
}
