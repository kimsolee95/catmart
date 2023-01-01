package com.shoptest.catmart.product.service.impl;

import com.shoptest.catmart.admin.domain.Category;
import com.shoptest.catmart.admin.repository.CategoryImgRepository;
import com.shoptest.catmart.common.FileUploadService;
import com.shoptest.catmart.product.domain.ItemImg;
import com.shoptest.catmart.product.domain.ProductItem;
import com.shoptest.catmart.product.repository.ItemImgRepository;
import com.shoptest.catmart.product.repository.ProductItemRepository;
import com.shoptest.catmart.product.service.ItemImgService;
import java.io.File;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ItemImgServiceImpl implements ItemImgService {

  @Value("${productImgLocation}")
  private String productImgLocation;

  private final ProductItemRepository productItemRepository;
  private final ItemImgRepository itemImgRepository;
  private final FileUploadService fileUploadService;

  @Override
  public void saveProductImg(ProductItem saveProductItem, MultipartFile uploadFile) throws Exception {

    String originFileName = uploadFile.getOriginalFilename();

    String[] filePaths = null;

    String imgName = ""; //파일명(uuid 적용)
    String imgUrl = ""; //WebConfig 소스 적용된 url
    String originFilePath = ""; //실제 파일 저장 경로
    String withSubFolderFilePath = ""; //하위폴더 포함 경로

    if (!StringUtils.isEmpty(originFileName)) {

      /*
       * [0] 확장자명 포함 파일명
       * [1] 실제 파일 적재 경로
       * [2] 하위폴더 포함한 경로 (yyyy/MM/dd / 파일명.확장자)
       * */
      filePaths = fileUploadService.uploadFile(productImgLocation, originFileName,
          uploadFile.getBytes());
      imgName = filePaths[0];
      originFilePath = filePaths[1];
      withSubFolderFilePath = filePaths[2];

      imgUrl = File.separator + "images" + File.separator + "product" + File.separator + withSubFolderFilePath;
    }

    //DB insert
    ItemImg saveItemImg = ItemImg.builder()
        .imgName(imgName)
        .originImgPath(originFilePath)
        .urlImgPath(imgUrl)
        .productItemId(saveProductItem.getProductItemId())
        .createdAt(LocalDateTime.now())
        .build();
    itemImgRepository.save(saveItemImg);
  }



}
