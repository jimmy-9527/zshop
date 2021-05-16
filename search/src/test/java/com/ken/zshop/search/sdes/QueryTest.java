package com.ken.zshop.search.sdes;

import com.ken.zshop.search.ZShopSearchApplication;
import com.ken.zshop.search.model.Blog;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @Author: sublun
 * @Date: 2021/4/26 16:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZShopSearchApplication.class)
public class QueryTest {
    @Autowired
    private ElasticsearchRestTemplate template;

    @Test
    public void testQuery1() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .build();
        SearchHits<Blog> searchHits = template.search(query, Blog.class);
        //取总记录数
        long totalHits = searchHits.getTotalHits();
        System.out.println("总记录数：" +  totalHits);
        //取文档列表
        List<SearchHit<Blog>> hits = searchHits.getSearchHits();
        hits.stream().forEach(h-> System.out.println(h));

    }

    @Test
    public void testQuery2() throws Exception {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //查询设置
                .withQuery(QueryBuilders.multiMatchQuery("看电影", "title", "content"))
                //过滤条件，可以设置多个，支持查询条件
                .withFilter(QueryBuilders.boolQuery()
                        .should(QueryBuilders.termQuery("title", "电影"))
                        .should(QueryBuilders.termQuery("content", "祝福"))
                )
                .withFilter(QueryBuilders.termQuery("mobile", "13890112230"))
                .withFilter(QueryBuilders.termQuery("title", "喜剧"))
                //分页设置
                .withPageable(PageRequest.of(0,10))
                //高亮设置
                .withHighlightBuilder(new HighlightBuilder()
                        //高亮显示的字段
                        .field("title")
                        .field("content")
                        //高亮显示的前缀
                        .preTags("<em>")
                        //高亮显示的后缀
                        .postTags("</em>")
                )
                .addAggregation(new TermsAggregationBuilder("mobile_group").field("mobile"))
                .build();
        //使用template对象执行查询
        SearchHits<Blog> searchHits = template.search(query, Blog.class);
        //取返回结果
        //取总记录数
        long totalHits = searchHits.getTotalHits();
        System.out.println("总记录数：" + totalHits);
        //取文档列表
        List<SearchHit<Blog>> hits = searchHits.getSearchHits();
        hits.stream().forEach(hit->{
            //取文档对象
            Blog blog = hit.getContent();
            //System.out.println(blog);
            //取高亮结果
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            //System.out.println(highlightFields);
            String title = highlightFields.get("title").get(0);
            String content = highlightFields.get("content").get(0);
            blog.setTitle(title);
            blog.setContent(content);
            System.out.println(blog);
        });
        Aggregations aggregations = searchHits.getAggregations();
        ParsedStringTerms aggregation = aggregations.get("mobile_group");
        List<? extends Terms.Bucket> buckets = aggregation.getBuckets();
        buckets.forEach(e-> {
            System.out.println(e.getKeyAsString());
            System.out.println(e.getDocCount());
        });
    }
}
