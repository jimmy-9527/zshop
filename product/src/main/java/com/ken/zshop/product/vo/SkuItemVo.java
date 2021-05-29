package com.ken.zshop.product.vo;

import com.ken.zshop.product.entity.SkuImagesEntity;
import com.ken.zshop.product.entity.SkuInfoEntity;
import com.ken.zshop.product.entity.SpuInfoDescEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class SkuItemVo {

    // 1、 sku基本信息
    private SkuInfoEntity info;

    // 2、sku图片信息
    private List<SkuImagesEntity> images;

    //3、spu的销售属性组合
    private List<SkuItemSaleAttrVo> attrSales;

    //4、spu描述信息
    private SpuInfoDescEntity desc;

    //5、spu分组（主体，基本信息...）规格属性
    private List<SpuAttrGroupVo> attrGroups;


}

