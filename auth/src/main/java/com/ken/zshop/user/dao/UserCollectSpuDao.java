package com.ken.zshop.user.dao;

import com.ken.zshop.user.entity.UserCollectSpuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收藏的商品
 * 
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-18 11:22:48
 */
@Mapper
public interface UserCollectSpuDao extends BaseMapper<UserCollectSpuEntity> {
	
}
