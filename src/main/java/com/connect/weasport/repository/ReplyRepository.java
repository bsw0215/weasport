package com.connect.weasport.repository;

import com.connect.weasport.domain.Club;
import com.connect.weasport.domain.Reply;
import com.connect.weasport.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {



}
