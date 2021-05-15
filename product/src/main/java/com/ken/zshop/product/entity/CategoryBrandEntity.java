package com.ken.zshop.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 分类品牌关系表
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 09:26:12
 */
@Data
@TableName("tb_category_brand")
public class CategoryBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;
	/**
	 * 分类ID
	 */
	private Integer categoryId;
	/**
	 * 品牌ID
	 */
	private Integer brandId;

}
