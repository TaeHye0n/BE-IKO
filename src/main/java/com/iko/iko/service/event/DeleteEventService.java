package com.iko.iko.service.event;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Event;
import com.iko.iko.domain.repository.event.EventRepository;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.linkEventImage.LinkEventImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeleteEventService {

    private final EventRepository eventRepository;
    private final LinkEventImageRepository linkEventImageRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public String deleteEvent(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            List<Integer> imageIdList = linkEventImageRepository.findImageIdByEventId(eventId);
            linkEventImageRepository.deleteLinkEventImage(eventId);
            eventRepository.deleteEvent(eventId);
            for (Integer imageId : imageIdList) {
                imageRepository.deleteImage(imageId);
            }
        } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        return "Ok";
    }
}
