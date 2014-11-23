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
    public String search(@ModelAttribute SearchForm searchForm, Model model) {
        //new itemService = ItemService();
        String keyword = searchForm.getKeyword();
        if (searchForm.getCategory() != null) {
            String category = searchForm.getCategory().toString();
            model.addAttribute("category",category);
        } else {
            model.addAttribute("category","null");
        }
        List<Item> listItems = itemService.search(searchForm);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listItems", listItems);
        List<Photo> listPhotos = null;
        for(int i=0;i<listItems.size();i++){
            if(this.itemService.getPhoto(listItems.get(i).getItemID()).isEmpty()){
                Photo tmp = new Photo();
                boolean add = listPhotos.add(tmp);
            }else{
                boolean add = listPhotos.add(this.itemService.getPhoto(listItems.get(i).getItemID()).get(0));
            }
        
            }
        return "searchResultView";
    }
    
    /*@RequestMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword,@RequestParam(value = "category", required = false) Category category, Model model) {
        //new itemService = ItemService();
        model.addAttribute("keyword", keyword);
        model.addAttribute("category",category);
        SearchForm searchForm = new SearchForm();
        searchForm.setCategory(category);
        searchForm.setKeyword(keyword);
        List<Item> listItems;
     //   listItems = this.itemService.search(searchForm);
      //  model.addAttribute("listItems",listItems);
        return "searchResultView";
    }*/
   
}
