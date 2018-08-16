package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MAIBENBEN on 2018-07-23.
 */
@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse  detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }


    //产品搜索及动态排序
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false) String keyword,
                                         @RequestParam(value = "categaryId",required = false)Integer categaryId,
                                         @RequestParam(value ="pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value ="pageSize",defaultValue = "10")int pageSize,
                                         @RequestParam(value ="orderBy",defaultValue = "")String  orderBy){

        return iProductService.getProductByKeywordCategory(keyword,categaryId,pageNum,pageSize,orderBy);
    }
}
