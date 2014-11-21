/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Locale.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mtmmoei
 */
@Controller
public class SearchController {
  // รอ ItemService
    /* 
    private ItemService itemService;
    
    @Autowired
    public void setItemService(Itemservice itemService){
        this.itemService = itemService;
    }   */ 
    
    @RequestMapping("/search")
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
    }
   
}
