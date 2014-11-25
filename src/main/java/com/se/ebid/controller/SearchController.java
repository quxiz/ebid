/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Photo;
import com.se.ebid.service.ItemService;
import java.io.UnsupportedEncodingException;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Locale.Category;
import javax.servlet.http.HttpServletRequest;
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
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("searchForm") SearchForm searchForm, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        searchForm.setKeyword(new String(searchForm.getKeyword().getBytes("iso8859-1"), "UTF-8"));
        String keyword = searchForm.getKeyword();
        if (searchForm.getCategory() != null) {
            String category = searchForm.getCategory().toString();
            model.addAttribute("category", category);
        } else {
            model.addAttribute("category", "ทุกประเภท");
        }
        
        List<Item> listItems = this.itemService.search(searchForm);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listItems", listItems);
        List<Photo> listPhotos = new ArrayList();
        for (int i = 0; i < listItems.size(); i++) {

            List<Photo> photos = this.itemService.getPhoto(listItems.get(i).getItemID());
            if (photos.size() == 0) {
                Photo photo = new Photo();
                photo.setPhotoURL(request.getContextPath() + "/resources/img/ebay1.png");
                listPhotos.add(photo);
            } else {
                listPhotos.add(photos.get(0));
            }
        }
        model.addAttribute("listPhotos", listPhotos);
        model.addAttribute("title", "สินค้า");
        return "searchResultView";
    }

}
