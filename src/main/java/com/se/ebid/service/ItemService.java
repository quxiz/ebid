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
    public static final int ERR_MISSING_MEMBER = -1;
    public static final int ERR_MISSING_ITEM = -2;
    public static final int ERR_MISSING_AUTOBID = -3;
    public static final int ERR_LOW_PRICE = -4;
    public static final int ERR_BLACKLIST = -5;
    public static final int ERR_NO_PAY_ACC = -6;
    public static final int ERR_NOT_ENOUGH_QTY = -7;
    public static final int ERR_NO_AUTOBID = -8;
    
    public List<Item> search(SearchForm searchForm);
    public Item getItem(long itemID);
    public Item getItemByTransactionID(long transactionID);
    public List<Photo> getPhoto(long itemID);
    public List<Comment> getComment(long itemID);
    public int bid(BidForm bidForm);
    public boolean sendOutbidEmail(Member member,Item item);
    public Invoice buy(BuyForm buyForm);
    public long confirmBuy(BuyForm buyForm);
    public long registerItem(RegisterItemForm registerItemForm);
    public boolean reportBidResult(long itemID);
    public long getMaxBidderID(long itemID);
}
