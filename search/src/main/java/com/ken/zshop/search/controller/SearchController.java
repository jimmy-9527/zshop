package com.ken.zshop.search.controller;

import com.ken.zshop.search.model.Page;
import com.ken.zshop.search.service.SpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private SpuInfoService spuInfoService;

    @GetMapping("/search/list")
    public String search(@RequestParam Map<String,String>paramMap, Model model) {
        if (!paramMap.containsKey("keywords")) {
            paramMap.put("keywords","");
        }
        Map<String, Object> resultMap = spuInfoService.search(paramMap);
        //取分页数据
        Long totalRows = Long.parseLong(resultMap.get("totalRows").toString());
        //当前页码
        int pageNum = Integer.parseInt(resultMap.get("pageNum").toString());
        Page page = new Page(totalRows, pageNum, 60);
        //4.url回显  /search/list?keywords=手机&brand=华为&category=手机
        StringBuilder url = new StringBuilder();
        url.append("/search/list");

        //8.排序url特殊处理
        StringBuilder sortUrl = new StringBuilder();
        sortUrl.append("/search/list");
        builderUrl(url, sortUrl, paramMap);
        model.addAttribute("url", url);
        model.addAttribute("sortUrl", sortUrl);

        //向页面返回结果
        model.addAttribute("searchMap", paramMap);
        model.addAttribute("result", resultMap);
        model.addAttribute("url", url);
        model.addAttribute("page", page);
        //返回模板名称
        return "search";
    }

    /**
     * 拼接翻页url地址中的携带参数
     * @param url
     * @param sortUrl
     * @param searchMap
     */
    private void builderUrl(StringBuilder url, StringBuilder sortUrl, Map<String, String> searchMap) {
        if(!CollectionUtils.isEmpty(searchMap)){
            // /search/list?keywords=手机&brand=华为
            url.append("?");
            sortUrl.append("?");
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                //7.排除pageNum参与url拼接
                if(!key.equals("pageNum")){
                    //5.特殊符号 + 处理
                    if(key.startsWith("spec")){
                        value = value.replace("+", "%2B");

                    }
                    //6. 删除版本特殊字符页面问题 482行 改为
                    //  <a th:href="@{${#strings.replace(url,'&'+sm.key+'='+#strings.replace(sm.value,'+','%2B'),'')}}">×</a>
                    //拼接查询条件
                    url.append(key);
                    url.append("=");
                    url.append(value);
                    url.append("&");


                 /*
                    方式二： 将sortUrl拼接放到pageNum的判断中
                    排序url特殊处理不需要拼接排序字段和规则
                 */
                    if(!key.equals("sortRule") && !key.equals("sortField")){
                        sortUrl.append(key);
                        sortUrl.append("=");
                        sortUrl.append(value);
                        sortUrl.append("&");
                    }
                }
            }
            //将最后一位删除 根据下标删除元素
            url.deleteCharAt(url.length()-1);
            sortUrl.deleteCharAt(sortUrl.length()-1);

        }
    }
}
