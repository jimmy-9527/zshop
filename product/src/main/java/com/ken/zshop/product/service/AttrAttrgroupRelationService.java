package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.AttrAttrgroupRelationEntity;
import com.ken.zshop.product.vo.AttrGroupReationVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组关联表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrGroupReationVo> vos);
}

