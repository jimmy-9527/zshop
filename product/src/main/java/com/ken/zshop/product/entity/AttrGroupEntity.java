package com.ken.zshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性分组表
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
@Data
@TableName("tb_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 图表
	 */
	private String icon;
	/**
	 * 分类ID
	 */
	private Integer categoryId;

}
