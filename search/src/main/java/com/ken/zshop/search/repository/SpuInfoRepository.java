package com.ken.zshop.search.repository;

import com.ken.zshop.search.model.SpuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: sublun
 * @Date: 2021/4/26 10:58
 */
public interface SpuInfoRepository extends ElasticsearchRepository<SpuInfo, Long> {

}
