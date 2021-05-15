package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.AttrEntity;
import com.ken.zshop.product.vo.AttrGroupReationVo;
import com.ken.zshop.product.vo.AttrRespVo;
import com.ken.zshop.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 属性表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Integer categoryId, String attrType);

    AttrRespVo getAttrInfo(Long id);

    void updateAttr(AttrVo attrVo);

    List<AttrEntity> getRelationAttr(Long attrGroupId);

    void deleteRelation(AttrGroupReationVo[] vos);
}

