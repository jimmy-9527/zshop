package com.ken.zshop.product.vo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AttrValueAndSkuIdVo {

    // skuids组合id
    private String skuIds;

    // 属性值：白色，128G
    private String attrValue;

    // 白色： [3,6]
    //128G： [3,9,8]

}

