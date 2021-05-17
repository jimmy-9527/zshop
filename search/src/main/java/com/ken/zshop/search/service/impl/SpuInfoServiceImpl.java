package com.ken.zshop.search.service.impl;

import com.ken.zshop.search.dao.SpuInfoDao;
import com.ken.zshop.search.model.SpuInfo;
import com.ken.zshop.search.repository.SpuInfoRepository;
import com.ken.zshop.search.service.SpuInfoService;
import com.ken.zshop.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: sublun
 * @Date: 2021/4/27 11:50
 */
@Service
public class SpuInfoServiceImpl implements SpuInfoService {

    @Autowired
    private SpuInfoDao spuInfoDao;
    @Autowired
    private SpuInfoRepository spuInfoRepository;

    @Override
    public R putOnSale(long spuId)  {
        //1.根据spuID查询对象的商品数据。
        SpuInfo spuInfo = spuInfoDao.getSpuInfoById(spuId);
        //2.商品数据包含的字段（待研究）Entity中包含的字段。
        //3.使用ElasticSearchRepository对象将数据添加到索引库中
        spuInfoRepository.save(spuInfo);
        //4.返回结果
        return R.ok();
    }

    @Override
    public R syncSpuInfo() {
        //1）把所有的商品数据查询出来
        List<SpuInfo> infoList = spuInfoDao.getSpuInfoList();
        //2）把商品数据导入到ES中
        spuInfoRepository.saveAll(infoList);
        //返回结果
        return R.ok();
    }
}
