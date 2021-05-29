package com.ken.zshop.product.dao;

import com.ken.zshop.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.zshop.product.vo.GroupAttrParamVo;
import com.ken.zshop.product.vo.SpuAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 属性分组表
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {
    //根据spuID,categoryId 查询 sku分组规格参数属性值
    public List<SpuAttrGroupVo> getGroupAttr(GroupAttrParamVo paramVo);
}
