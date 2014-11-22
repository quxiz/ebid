/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.interceptor;

import com.se.ebid.controller.CategoryType;
import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.SellingType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Quxiz
 */
public class HeaderModelInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        List<CategoryType> categoryList = new ArrayList<CategoryType>(EnumSet.allOf(CategoryType.class));
        if (categoryList == null) {
            categoryList = new ArrayList<CategoryType>(Arrays.asList(CategoryType.values()));
        }
        try {
            modelAndView.addObject("categoryList", categoryList);
            modelAndView.addObject("searchForm", new SearchForm());
        } catch (NullPointerException e) {
            System.out.print("NullPointerException caught");
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
