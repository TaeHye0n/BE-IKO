package com.iko.iko.service.image;

import com.iko.iko.domain.repository.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Repository
public class GetBannerImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public List<String> getBannerImage(Integer count){
        List<String> bannerList=imageRepository.getBannerImage();

        List<String> result = new ArrayList<>();
        if(count>bannerList.size()) {
            result.add(bannerList.size() + "이하 만큼 request 해주세요.");
            return result;
        }
        else {
            for (int i = 0; i < count; i++) {
                int size = bannerList.size();
                Random rand = new Random();
                int index = rand.nextInt(size);
                result.add(bannerList.get(index));
                result.remove(bannerList);
            }

            return result;
        }
    }
}
