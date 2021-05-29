package com.ken.zshop.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class SpuAttrGroupVo {

    private String groupName;

    // 属性参数
    private List<Attr> attrs;


}

