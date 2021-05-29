package com.ken.zshop.product.service.impl;

import com.ken.zshop.product.entity.AttrEntity;
import com.ken.zshop.product.service.AttrService;
import com.ken.zshop.product.vo.AttrGroupWithAttrsVo;
import com.ken.zshop.product.vo.GroupAttrParamVo;
import com.ken.zshop.product.vo.SpuAttrGroupVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.product.dao.AttrGroupDao;
import com.ken.zshop.product.entity.AttrGroupEntity;
import com.ken.zshop.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer categoryId) {

        String key = (String)params.get("key");//模糊搜索关键字
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>(); //查询条件
        if(!StringUtils.isEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("id",key).or().like("name",key); //ID精确查找已经名称模糊查询
            });
        }

        if(categoryId==0){ //分类如果为0，全部查询
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),wrapper);
            return new PageUtils(page);
        }else{ //根据分类进行过滤
            wrapper.eq("category_id",categoryId);
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),wrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithattrByCategroyId(Integer categoryId) {
        //根据分类ID查询分类的下的属性分组
        List<AttrGroupEntity> attrGroupEntities =
                this.list(new QueryWrapper<AttrGroupEntity>().eq("category_id",categoryId));
        List<AttrGroupWithAttrsVo> result = attrGroupEntities.stream().map(group->{
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrsVo);
            //根据属性分组ID获取相对应的属性
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SpuAttrGroupVo> getGroupAttr(Long spuId, Long categoryId) {
        GroupAttrParamVo paramVo = new GroupAttrParamVo();
        paramVo.setSpuId(spuId);
        paramVo.setCategoryId(categoryId);

        List<SpuAttrGroupVo> attrGroupVos =  this.baseMapper.getGroupAttr(paramVo);

        return attrGroupVos;
    }
}