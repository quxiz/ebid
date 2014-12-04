/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.interceptor;

import com.se.ebid.controller.CategoryType;
import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.SellingType;
import com.se.ebid.service.CustomUser;
import com.se.ebid.service.MessageService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Quxiz
 */
public class HeaderModelInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MessageService messageService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        List<CategoryType> categoryList = new ArrayList<CategoryType>(EnumSet.allOf(CategoryType.class));
        if (categoryList == null) {
            categoryList = new ArrayList<CategoryType>(Arrays.asList(CategoryType.values()));
        }
        try {
            if (modelAndView.getView() instanceof RedirectView || modelAndView.getViewName().startsWith("redirect:")) {
                // Do something
            } else {

                modelAndView.addObject("categoryList", categoryList);
                modelAndView.addObject("searchForm", new SearchForm());
                modelAndView.addObject("currentDate", new Date());
         
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (!(auth instanceof AnonymousAuthenticationToken)) {
                    CustomUser customUser = (CustomUser) auth.getPrincipal();
                    modelAndView.addObject("unreadMessageCount", this.messageService.getUnreadCount(customUser.getMemberID()));
                }
            }
        } catch (NullPointerException e) {
            System.out.print("NullPointerException caught");
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
