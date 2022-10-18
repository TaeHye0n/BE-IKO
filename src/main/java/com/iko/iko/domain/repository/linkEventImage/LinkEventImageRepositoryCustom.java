package com.iko.iko.domain.repository.linkEventImage;


import java.util.List;

public interface LinkEventImageRepositoryCustom{

    Long deleteLinkEventImage(Integer eventId);
    List<Integer> findImageIdByEventId(Integer eventId);
}
