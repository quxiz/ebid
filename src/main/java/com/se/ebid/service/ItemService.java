/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.BidForm;
import com.se.ebid.controller.BuyForm;
import com.se.ebid.controller.Invoice;
import com.se.ebid.controller.RegisterItemForm;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Photo;
import java.util.List;

/**
 *
 * @author Nuttapong
 */
public interface ItemService {
    public List<Item> search(SearchForm searchForm);
    public Item getItem(long itemID);
    public List<Photo> getPhoto(long itemID);
    public List<Comment> getComment(long itemID);
    public boolean bid(BidForm bidForm);
    public boolean sendOutbidEmail(Member member);
    public Invoice buy(BuyForm buyForm);
    public boolean confirmBuy(BuyForm buyForm);
    public boolean registerItem(RegisterItemForm registerItemForm);
    public boolean sendSellerEmail(Member member);
    public boolean sendBuyerEmail(Member member);
}
