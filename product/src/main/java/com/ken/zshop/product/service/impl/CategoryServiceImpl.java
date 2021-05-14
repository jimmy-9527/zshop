package com.ken.zshop.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.product.dao.CategoryDao;
import com.ken.zshop.product.entity.CategoryEntity;
import com.ken.zshop.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        //获取所有的菜单
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //获取分类的一级菜单
        List<CategoryEntity> firstLevelMenus = categoryEntities.stream().filter(categoryEntity ->
                categoryEntity.getParentId() == 0 //如果父节点ID等于0 就是一级菜单
        ).map(menu->{
            menu.setChildren(getChildren(menu,categoryEntities)); //设置当前节点子节点
            return menu;
        }).sorted((menu1,menu2)->{
            return (menu1.getSeq()==null?0:menu1.getSeq())-(menu2.getSeq()==null?0:menu2.getSeq());
        }).collect(Collectors.toList());
        return firstLevelMenus;
    }

    /**
     * 获取当前节点的子节点
     * @param first
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity first,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId().equals(first.getId()); //当前节点ID等于子节点的父ID
        }).map(categoryEntity -> {
            categoryEntity.setChildren(getChildren(categoryEntity,all)); //递归查询
            return categoryEntity;
        }).collect(Collectors.toList());

        return children;
    }
}