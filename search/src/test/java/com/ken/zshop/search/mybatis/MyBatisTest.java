package com.ken.zshop.search.mybatis;

import com.ken.zshop.search.ZShopSearchApplication;
import com.ken.zshop.search.dao.SpuInfoDao;
import com.ken.zshop.search.entity.SpuInfoEntity;
import com.ken.zshop.search.model.SpuInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: sublun
 * @Date: 2021/4/27 11:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZShopSearchApplication.class)
public class MyBatisTest {
    @Autowired
    private SpuInfoDao spuInfoDao;

    @Test
    public void testSpuInfoDao() {
        SpuInfoEntity entity = spuInfoDao.selectById(54687200);
        System.out.println(entity);
    }

    @Test
    public void testGetSpuById() {
        SpuInfo info = spuInfoDao.getSpuInfoById(54687200l);
        System.out.println(info);
    }
}
