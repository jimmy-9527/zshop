package com.ken.zshop.product.dao;

import com.ken.zshop.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌表
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-11 22:47:06
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
