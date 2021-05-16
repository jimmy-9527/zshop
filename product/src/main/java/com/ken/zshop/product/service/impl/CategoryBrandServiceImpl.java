package com.ken.zshop.product.service.impl;

import com.ken.zshop.product.dao.BrandDao;
import com.ken.zshop.product.dao.CategoryDao;
import com.ken.zshop.product.entity.BrandEntity;
import com.ken.zshop.product.entity.CategoryEntity;
import com.ken.zshop.product.service.BrandService;
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

import com.ken.zshop.product.dao.CategoryBrandDao;
import com.ken.zshop.product.entity.CategoryBrandEntity;
import com.ken.zshop.product.service.CategoryBrandService;


@Service("categoryBrandService")
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandDao, CategoryBrandEntity> implements CategoryBrandService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryBrandDao categoryBrandDao;

    @Autowired
    BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandEntity> page = this.page(
                new Query<CategoryBrandEntity>().getPage(params),
                new QueryWrapper<CategoryBrandEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取品牌分类关系（包含品牌名称、分类名称）
     * @param brand_id
     * @return
     */
    @Override
    public List<CategoryBrandEntity> getCategoryBrands(QueryWrapper<CategoryBrandEntity> brand_id) {
        List<CategoryBrandEntity> list = this.list(brand_id);
        for(CategoryBrandEntity categoryBrandEntity:list){
            Integer brandId = categoryBrandEntity.getBrandId();
            Integer categoryId = categoryBrandEntity.getCategoryId();
            CategoryEntity categoryEntity = categoryDao.selectById(categoryId);
            BrandEntity brandEntity = brandDao.selectById(brandId);
            categoryBrandEntity.setBrandName(brandEntity.getName());
            categoryBrandEntity.setCategoryName(categoryEntity.getName());
        }

        return list;
    }

    @Override
    public void removeCategoryBrandEntity(QueryWrapper<CategoryBrandEntity> queryWrapper) {
        this.remove(queryWrapper);
    }

    //根据分类ID查询品牌列表
    @Override
    public List<BrandEntity> getBrandsByCategoryId(Integer categoryId) {
        //根据分类id查询分类与品牌的对应关系
        List<CategoryBrandEntity> categoryBrandEntities =
                categoryBrandDao.selectList(new QueryWrapper<CategoryBrandEntity>().eq("category_id",categoryId));
        List<BrandEntity> collect = categoryBrandEntities.stream().map(item->{
            Integer brandId = item.getBrandId();
            BrandEntity brandEntity = brandService.getById(brandId);
            return brandEntity; }
        ).collect(Collectors.toList());
        return collect;
    }

}