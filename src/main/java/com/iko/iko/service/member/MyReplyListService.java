package com.iko.iko.service.member;

import com.iko.iko.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyReplyListService {

    private final MemberRepository memberRepository;
}
