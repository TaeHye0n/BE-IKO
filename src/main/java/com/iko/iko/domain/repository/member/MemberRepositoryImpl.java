package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.iko.iko.domain.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long updateInfo(
            UpdateInfoRequestDto requestDto
    ){
        return jpaQueryFactory
                .update(member)
                .set(member.address, requestDto.getAddress())
                .set(member.name, requestDto.getName())
                .set(member.birthday, requestDto.getBirthday())
                .set(member.phone, requestDto.getPhone())
                .set(member.readname, requestDto.getReadname())
                .where(member.memberId.eq(requestDto.getMemberId()))
                .execute();
    }

}
