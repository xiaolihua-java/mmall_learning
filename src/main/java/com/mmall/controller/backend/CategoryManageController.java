package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by MAIBENBEN on 2018-07-17.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;


    //增加节点
    @RequestMapping(value = "/add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId){
            User user= (User) session.getAttribute(Const.CURRENT_USER);
            if (user==null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登陆，请登陆");
            }
        //校验一下是否是管理员
       if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
           //增加我们处理分类的逻辑
           return iCategoryService.addCategory(categoryName,parentId);

       }else {
           return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
       }
    }


    //修改品类名字
    @RequestMapping(value = "/set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登陆，请登陆");
        }
//校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //更新categoryName
            return iCategoryService.updateCategoryName( categoryId,categoryName);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }


    //获得品类子节点(平级)
    @RequestMapping(value = "/get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParalleCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登陆，请登陆");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //查询子节点的category信息，并且不递归，保持平级
            return iCategoryService.getChildrenParalleCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }



    //获取当前分类id及递归子节点id
    @RequestMapping(value = "/get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登陆，请登陆");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //查询当前节点的id和递归子节点的id
            //0--->10000-->100000
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }


}
