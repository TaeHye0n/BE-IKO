package com.iko.iko.controller.image;

import com.iko.iko.common.response.Response;
import com.iko.iko.service.image.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageFacade imageFacade;

    @GetMapping("/banner")
    public ResponseEntity<Response<List<String>>> getBannerImage
            (
                    @RequestParam (value="count") Integer count
            ){
        return ResponseEntity.ok(
                Response.of(
                        imageFacade.getBannerImage(count),
                        "배너이미지 불러오기 완료"
                )
        );
    }
}
