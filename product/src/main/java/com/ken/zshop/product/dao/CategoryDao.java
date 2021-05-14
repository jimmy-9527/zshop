package com.ken.zshop.product.dao;

import com.ken.zshop.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类目
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-13 21:24:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
