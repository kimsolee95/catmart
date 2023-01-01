package com.shoptest.catmart.product.service.impl;

import com.shoptest.catmart.product.domain.ProductItem;
import com.shoptest.catmart.product.dto.ProductItemDto;
import com.shoptest.catmart.product.repository.ProductItemRepository;
import com.shoptest.catmart.product.service.ItemImgService;
import com.shoptest.catmart.product.service.ProductService;
import com.shoptest.catmart.product.type.ItemStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductItemRepository productItemRepository;
  private final ItemImgService itemImgService;

  @Override
  public boolean saveProduct(ProductItemDto productItemDto, MultipartFile fileInput) {

    ProductItem productItem = ProductItem.builder()
        .categoryId(productItemDto.getCategoryId())
        .itemName(productItemDto.getItemName())
        .price(productItemDto.getPrice())
        .itemStatus(ItemStatus.SAIL) //기본값은 판매중으로 set
        .itemSubject(productItemDto.getItemSubject())
        .stock(productItemDto.getStock())
        .createdAt(LocalDateTime.now())
        .build();
    ProductItem saveProduct = productItemRepository.save(productItem);

    try {
      itemImgService.saveProductImg(saveProduct, fileInput);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }
}
