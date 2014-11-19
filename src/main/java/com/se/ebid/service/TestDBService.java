/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se.ebid.service;

import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.UploadedFile;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Photo;
import java.util.List;

/**
 *
 * @author Quxiz
 */
public interface TestDBService {
    
    public void saveMember(Member m);
    public List<Member> listMembers();
    public Member findMemberByMemberID(long memberID);
    public Member findMemberByUserID(String userID);
    public Member findMemberByActivateKey(String activateKey);
    public Member findMemberByEmail(String email);
    
    public void saveMessage(Message message);
    public List<Message> listMessages();
    public List<Message> getMessages(long receiverID);
    
    public void saveItem(Item item);
    public List<Item> listItems();
    public Item findItemByItemID(long itemID);
    public List<Item> searchItem(SearchForm searchForm);
    
    public void savePhoto(UploadedFile uploadedFile);
    public List<Photo> listPhotos();
    public List<Photo> findPhotoByItemID(long itemID);
}
