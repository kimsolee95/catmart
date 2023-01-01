package com.shoptest.catmart.product.service;

import com.shoptest.catmart.product.domain.ProductItem;
import org.springframework.web.multipart.MultipartFile;

public interface ItemImgService {


  void saveProductImg(ProductItem saveProductItem, MultipartFile uploadFile) throws Exception;
}
