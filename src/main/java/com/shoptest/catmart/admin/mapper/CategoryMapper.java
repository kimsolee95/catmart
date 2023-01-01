package com.shoptest.catmart.admin.mapper;

import com.shoptest.catmart.admin.dto.CategorySelectionDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

  List<CategorySelectionDto> categorySelectionList();

}
