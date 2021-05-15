package com.ken.zshop.product.dao;

import com.ken.zshop.product.entity.CategoryBrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类品牌关系表
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 09:26:12
 */
@Mapper
public interface CategoryBrandDao extends BaseMapper<CategoryBrandEntity> {
	
}
