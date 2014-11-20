/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.controller.BidForm;
import com.se.ebid.controller.BuyForm;
import com.se.ebid.controller.Invoice;
import com.se.ebid.controller.RegisterItemForm;
import com.se.ebid.controller.SearchForm;
import com.se.ebid.dao.ItemDAO;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Quxiz
 */
@Service
public class ItemServiceImpl implements ItemService{
    
    private ItemDAO itemDAO;
    
    @Autowired
    public void setItemDAO(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Item> search(SearchForm searchForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Item getItem(long itemID) {
        return this.itemDAO.findByItemID(itemID);
    }

    @Override
    public boolean bid(BidForm bidForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sendOutbidEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Invoice buy(BuyForm buyForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean confirmBuy(BuyForm buyForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerItem(RegisterItemForm registerItemForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sendSellerEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sendBuyerEmail(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
