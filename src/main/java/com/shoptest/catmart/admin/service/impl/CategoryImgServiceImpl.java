package com.shoptest.catmart.admin.service.impl;

import com.shoptest.catmart.admin.domain.Category;
import com.shoptest.catmart.admin.domain.CategoryImg;
import com.shoptest.catmart.admin.repository.CategoryImgRepository;
import com.shoptest.catmart.admin.service.CategoryImgService;
import com.shoptest.catmart.common.service.FileUploadService;
import java.io.File;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


@Service
@RequiredArgsConstructor
public class CategoryImgServiceImpl implements CategoryImgService {

  @Value("${categoryImgLocation}")
  private String categoryImgLocation;

  private final CategoryImgRepository categoryImgRepository;

  private final FileUploadService fileUploadService;

  @Override
  public void saveCategoryImg(Category saveCategory, MultipartFile uploadFile) throws Exception {

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
      filePaths = fileUploadService.uploadFile(categoryImgLocation, originFileName,
          uploadFile.getBytes());
      imgName = filePaths[0];
      originFilePath = filePaths[1];
      withSubFolderFilePath = filePaths[2];

      imgUrl = File.separator + "images" + File.separator + "category" + File.separator + withSubFolderFilePath;
    }

    //DB insert
    CategoryImg saveCategoryImg = CategoryImg.builder()
        .imgName(imgName)
        .originImgPath(originFilePath)
        .urlImgPath(imgUrl)
        .categoryId(saveCategory.getCategoryId())
        .createdAt(LocalDateTime.now())
        .build();
    categoryImgRepository.save(saveCategoryImg);
  }


}
