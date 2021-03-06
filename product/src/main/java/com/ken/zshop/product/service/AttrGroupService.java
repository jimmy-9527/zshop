package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.AttrGroupEntity;
import com.ken.zshop.product.vo.AttrGroupWithAttrsVo;
import com.ken.zshop.product.vo.SpuAttrGroupVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Integer categoryId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithattrByCategroyId(Integer categoryId);

    List<SpuAttrGroupVo> getGroupAttr(Long spuId, Long categoryId);
}

