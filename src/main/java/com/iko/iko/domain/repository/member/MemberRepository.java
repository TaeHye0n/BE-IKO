package com.iko.iko.domain.repository.member;

import com.iko.iko.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}
