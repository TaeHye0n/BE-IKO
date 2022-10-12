package com.iko.iko.domain.repository.linkProductDetailsImage;

import java.util.List;

public interface LinkProductDetailsImageRepositoryCustom {
    Long deleteLinkProductDetailsImage(Integer imageId);
    List<Integer> searchImageIdByProductId(Integer productId);
}
