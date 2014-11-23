/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.ItemService;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Locale.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mtmmoei
 */
@Controller
public class SearchController {
  // รอ ItemService
     
    private ItemService itemService;
    
    @Autowired
    public void setItemService(ItemService itemService){
        this.itemService = itemService;
    } 
    
    @RequestMapping(value="/search", method = RequestMethod.POST)
    public String search(@ModelAttribute ("searchForm") SearchForm searchForm, Model model) {
        String keyword = searchForm.getKeyword();
        if (searchForm.getCategory() != null) {
            String category = searchForm.getCategory().toString();
            model.addAttribute("category",category);
        } else {
            model.addAttribute("category","ทุกประเภท");
        }
        List<Item> listItems = this.itemService.search(searchForm);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listItems", listItems);
        List<Photo> listPhotos = null;
        for(int i=0;i<listItems.size();i++){
            
            listPhotos = this.itemService.getPhoto(listItems.get(i).getItemID());
            model.addAttribute("listPhotos"+i, listPhotos);
            /*
            if(this.itemService.getPhoto(listItems.get(i).getItemID())==null){
                Photo photo = new Photo();
                photo.setPhotoURL("photo");
                listPhotos.add(photo);
            }else{
                listPhotos.add(this.itemService.getPhoto(listItems.get(i).getItemID()).get(0));
            }*/
        }
        return "searchResultView";
    }
   
}
