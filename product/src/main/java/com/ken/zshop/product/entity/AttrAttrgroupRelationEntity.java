package com.ken.zshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性分组关联表
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
@Data
@TableName("tb_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 属性ID
	 */
	private Long attrId;
	/**
	 * 属性分组ID
	 */
	private Long attrGroupId;
	/**
	 * 排序
	 */
	private Integer attrSort;

}
