package com.iko.iko.service.image.facade;

import com.iko.iko.service.image.AddBannerImageService;
import com.iko.iko.service.image.GetBannerImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageFacade {
    private final AddBannerImageService addBannerImageService;
    private final GetBannerImageService getBannerImageService;

    @Transactional
    public String addBannerImage(String imageUrl){
        return addBannerImageService.addBannerImage(imageUrl);
    }
    @Transactional
    public List<String> getBannerImage(Integer count){
        return getBannerImageService.getBannerImage(count);
    }

}
