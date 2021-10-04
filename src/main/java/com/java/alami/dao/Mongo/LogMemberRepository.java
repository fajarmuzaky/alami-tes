package com.java.alami.dao.Mongo;

import com.java.alami.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogMemberRepository extends MongoRepository<Member, Long> {
    Member findByEmail(String email);
}
