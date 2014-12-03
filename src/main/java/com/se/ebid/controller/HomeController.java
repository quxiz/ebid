/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.ItemService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
     
//    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//     
//    /**
//     * Simply selects the home view to render by returning its name.
//     */
//    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
//    public String home(Locale locale, Model model) {
//        logger.info("Welcome home! The client locale is {}.", locale);
//         
//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//         
//        String formattedDate = dateFormat.format(date);
//         
//        model.addAttribute("serverTime", formattedDate );
//        
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName(); //get logged in username
//        model.addAttribute("username", name);
//        model.addAttribute("title", "Home Page");
//        return "homeView";
//    }
    private ItemService itemService;
    
    @Autowired
    public void setItemService(ItemService itemService){
        this.itemService = itemService;
    }
    
       @RequestMapping("/")
    public String viewHome(Model model,HttpServletRequest request) {
        model.addAttribute("title", "หน้าหลัก");
        SearchForm searchForm = new SearchForm();
        searchForm.setCategory(CategoryType.All);
        searchForm.setKeyword("");
        List<Item> listRecentItems= new ArrayList<>();
        listRecentItems = this.itemService.search(searchForm);
        List<Photo> listPhotos = new ArrayList<>();        
        for (int i = 0; i < 4 && i < listRecentItems.size(); i++) {
            List<Photo> photos = this.itemService.getPhoto(listRecentItems.get(i).getItemID());
            if ( (photos.size() == 0) || photos.get(0).getPhotoURL()==null) {
                Photo photo = new Photo();
                photo.setPhotoURL(request.getContextPath() + "/resources/img/logo.jpg");
                listPhotos.add(photo);
            } else {
                listPhotos.add(photos.get(0));
            }
        }
        
        model.addAttribute("listPhotos", listPhotos); 
        model.addAttribute("maxIndex", listPhotos.size()-1);
        model.addAttribute("listRecentItems",listRecentItems);
        return "homeView";
    }
}