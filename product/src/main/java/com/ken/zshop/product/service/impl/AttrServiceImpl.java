package com.ken.zshop.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ken.zshop.common.constant.ProductConstant;
import com.ken.zshop.product.dao.AttrAttrgroupRelationDao;
import com.ken.zshop.product.dao.AttrGroupDao;
import com.ken.zshop.product.dao.CategoryDao;
import com.ken.zshop.product.entity.AttrAttrgroupRelationEntity;
import com.ken.zshop.product.entity.AttrGroupEntity;
import com.ken.zshop.product.entity.CategoryEntity;
import com.ken.zshop.product.service.CategoryService;
import com.ken.zshop.product.vo.AttrRespVo;
import com.ken.zshop.product.vo.AttrVo;
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

import com.ken.zshop.product.dao.AttrDao;
import com.ken.zshop.product.entity.AttrEntity;
import com.ken.zshop.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        this.save(attrEntity);
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            //保存attrgroupId
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId()); //属性分组ID
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getId()); //属性ID
            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }

    //service关联分类名称、分组名称
    /**
     *
     * @param params
     * @param categoryId 分类ID
     * @param attrType
     * @return
     */
    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Integer categoryId, String attrType) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type","base".equalsIgnoreCase(attrType)? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() :ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());//基本属性或销售属性
        if(categoryId!=0){ //如果等于0，查询全部
            queryWrapper.eq("category_id",categoryId);
        }
        String key = (String)params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((wrapper) ->{
                wrapper.eq("id",key).or().like("name",key);
            } );
        }

        IPage<AttrEntity> page= this.page(new Query<AttrEntity>().getPage(params),queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        //以下是获取分类名称、分组名称
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> respVos = records.stream().map((attrEntity)->{

            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity,attrRespVo);

            //获取分组名称，通过关联表
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity =
                    attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrEntity.getId()));
            if(attrAttrgroupRelationEntity!=null&&attrAttrgroupRelationEntity.getAttrGroupId()!=null){
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                //设置分组名称
                attrRespVo.setGroupName(attrGroupEntity.getName());
            }
            //获取分类名称
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCategoryId());
            if(categoryEntity!=null){
                attrRespVo.setCategoryName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(respVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long id) {

        AttrRespVo attrRespVo = new AttrRespVo();
        //根据数据库查询
        AttrEntity attrEntity = this.getById(id);
        BeanUtils.copyProperties(attrEntity,attrRespVo);
        if(attrEntity.getAttrType()==1){
            //获取分组名称，通过关联表
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity =
                    attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrEntity.getId()));
            if(attrAttrgroupRelationEntity!=null&&attrAttrgroupRelationEntity.getAttrGroupId()!=null){
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                //设置分组名称
                attrRespVo.setGroupName(attrGroupEntity.getName());
                //设置分组ID
                attrRespVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
            }
        }

        Integer categoryId = attrEntity.getCategoryId();
        Integer[] categoryPath = categoryService.findCategoryPath(categoryId);
        attrRespVo.setCategoryPath(categoryPath);

        //获取分类名称
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCategoryId());
        if(categoryEntity!=null){
            attrRespVo.setCategoryName(categoryEntity.getName());
        }
        return attrRespVo;
    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo,attrEntity);
        this.updateById(attrEntity);
        if (attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) { //判断基本属性添加分组，销售属性不添加分组
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity =
                    new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrId(attrVo.getId());
            attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());

            //判断当前关联关系是新增还是修改
            Integer count = attrAttrgroupRelationDao.
                    selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrVo.getId()));
            if(count>0){ //修改
                attrAttrgroupRelationDao.update(attrAttrgroupRelationEntity,
                        new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrVo.getId()));
            }else {
                attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
            }
        }
    }
}