package com.shoptest.catmart.product.service;

import com.shoptest.catmart.product.dto.ProductItemDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {


  boolean saveProduct(ProductItemDto productItemDto, MultipartFile fileInput);


}
