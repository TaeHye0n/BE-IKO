package com.iko.iko.service.event;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.event.dto.EventRequest.AddEventRequest;
import com.iko.iko.domain.entity.Event;
import com.iko.iko.domain.entity.Image;
import com.iko.iko.domain.entity.LinkEventImage;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.event.EventRepository;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.linkEventImage.LinkEventImageRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddEventService {
    private final EventRepository eventRepository;
    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    private final LinkEventImageRepository linkEventImageRepository;
    @Transactional
    public String addEvent(AddEventRequest addEventRequest){
        Member member = validateLoginStatus();
        Event event=eventRepository.save(addEventRequest.toEntity());
        Image image = new Image();
        image.setImageType(1);
        image.setImageUrl(addEventRequest.getMainImageUrl());
        Image newImage=imageRepository.save(image);

        Image image1=new Image();
        image1.setImageUrl(addEventRequest.getExplainImageUrl());
        image1.setImageType(3);
        Image newImage2=imageRepository.save(image1);

        LinkEventImage linkEventImage=new LinkEventImage();
        linkEventImage.setEventId(event.getEventId());
        linkEventImage.setImageId(image.getImageId());

        LinkEventImage linkEventImage1=linkEventImageRepository.save(linkEventImage);
        if(linkEventImage1.getEventId()==null){
            throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }


        LinkEventImage linkEventImage2=new LinkEventImage();
        linkEventImage2.setEventId(event.getEventId());
        linkEventImage2.setImageId(image1.getImageId());

        LinkEventImage linkEventImage3=linkEventImageRepository.save(linkEventImage2);
        if(linkEventImage3.getEventId()==null){
            throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }
        return "Ok";
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
