/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.se.ebid.controller.SearchForm;
import com.se.ebid.controller.UploadedFile;
import com.se.ebid.dao.AutoBidDAO;
import com.se.ebid.dao.BlacklistDAO;
import com.se.ebid.dao.CommentDAO;
import com.se.ebid.dao.ComplaintDAO;
import com.se.ebid.dao.FeedbackDAO;
import com.se.ebid.dao.ItemDAO;
import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.MessageDAO;
import com.se.ebid.dao.PhotoDAO;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.entity.AutoBid;
import com.se.ebid.entity.Blacklist;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Complaint;
import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Photo;
import com.se.ebid.entity.Transaction;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Quxiz
 */
@Service
public class TestDBServiceImpl implements TestDBService {

    private SecureRandom random = new SecureRandom();
    private MemberDAO memberDAO;
    private MessageDAO messageDAO;
    private ItemDAO itemDAO;
    private PhotoDAO photoDAO;
    private ComplaintDAO complaintDAO;
    private BlacklistDAO blacklistDAO;
    private CommentDAO commentDAO;
    private FeedbackDAO feedbackDAO;
    private TransactionDAO transactionDAO;
    private AutoBidDAO autoBidDAO;

    @Autowired
    ServletContext servletContext;

    @Autowired
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Autowired
    public void setPhotoDAO(PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    @Autowired
    public void setComplaintDAO(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    @Autowired
    public void setBlacklistDAO(BlacklistDAO blacklistDAO) {
        this.blacklistDAO = blacklistDAO;
    }

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Autowired
    public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Autowired
    public void setAutoBidDAO(AutoBidDAO autoBidDAO) {
        this.autoBidDAO = autoBidDAO;
    }

    /* ============================================================
     ========================== Member ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveMember(Member m) {
        m.setPassword(toSHA256(m.getPassword()));
        m.setActivated(true);
        java.util.Date date = new java.util.Date();
        m.setTimestamp(new Timestamp(date.getTime()));
        m.setBlacklisted(false);
        m.setActivateKey(new BigInteger(130, random).toString(32));
        this.memberDAO.save(m);
    }

    @Override
    @Transactional
    public List<Member> listMembers() {
        return this.memberDAO.list();
    }

    @Override
    @Transactional
    public Member findMemberByMemberID(long memberID) {
        return this.memberDAO.findByMemberID(memberID);
    }

    @Override
    @Transactional
    public Member findMemberByUserID(String userID) {
        return this.memberDAO.findByUserID(userID);
    }

    @Override
    @Transactional
    public Member findMemberByActivateKey(String activateKey) {
        return this.memberDAO.findByActivateKey(activateKey);
    }

    @Override
    @Transactional
    public Member findMemberByEmail(String email) {
        return this.memberDAO.findByEmail(email);
    }

    private static String toSHA256(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /* ============================================================
     ========================== Message ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveMessage(Message message) {
        this.messageDAO.save(message);
    }

    @Override
    @Transactional
    public List<Message> listMessages() {
        return this.messageDAO.list();
    }

    @Override
    @Transactional
    public List<Message> getMessages(long receiverID) {
        return this.messageDAO.findByReceiverID(receiverID);
    }

    /* ============================================================
     ========================== Item ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveItem(Item item) {
        this.itemDAO.save(item);
    }

    @Override
    @Transactional
    public List<Item> listItems() {
        return this.itemDAO.list();
    }

    @Override
    @Transactional
    public Item findItemByItemID(long itemID) {
        return this.itemDAO.findByItemID(itemID);
    }

    @Override
    @Transactional
    public List<Item> searchItem(SearchForm searchForm) {
        return this.itemDAO.search(searchForm);
    }

    /* ============================================================
     ========================== Photo ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void savePhoto(UploadedFile uploadedFile) {
        Photo photo = new Photo();
        photo.setItemID(1);
        long photoID = this.photoDAO.save(photo);
        MultipartFile multipartFile = uploadedFile.getFile();
        //String fileExtension = multipartFile.getContentType();
        int extensionIndex = multipartFile.getOriginalFilename().lastIndexOf(".");
        String fileExtension = multipartFile.getOriginalFilename().substring(extensionIndex, multipartFile.getOriginalFilename().length());

        String savePath = servletContext.getRealPath("resources/uploadedImg/") + "\\" + photoID + fileExtension;
        System.out.println(savePath);
        String photoURL = "/resources/uploadedImg/" + photoID + fileExtension;
        try {
            multipartFile.transferTo(new File(savePath));
        } catch (IOException ex) {
            Logger.getLogger(TestDBServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TestDBServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        photo.setPhotoURL(photoURL);
        this.photoDAO.save(photo);
    }

    @Override
    @Transactional
    public List<Photo> listPhotos() {
        return this.photoDAO.list();
    }

    @Override
    @Transactional
    public List<Photo> findPhotoByItemID(long itemID) {
        return this.photoDAO.findByItemID(itemID);
    }

    /* ============================================================
     ========================== Complaint ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveComplaint(Complaint complaint) {
        this.complaintDAO.save(complaint);
    }

    @Override
    @Transactional
    public List<Complaint> listComplaints() {
        return this.complaintDAO.list();
    }

    @Override
    @Transactional
    public Complaint findComplaintByComplaintID(long complaintID) {
        return this.complaintDAO.findByComplaintID(complaintID);
    }

    @Override
    @Transactional
    public List<Complaint> getComplaint() {
        return this.complaintDAO.getComplaint();
    }

    /* ============================================================
     ========================== Blacklist ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveBlacklist(Blacklist blacklist) {
        this.blacklistDAO.save(blacklist);
    }

    @Override
    @Transactional
    public List<Blacklist> listBlacklists() {
        return this.blacklistDAO.list();
    }

    /* ============================================================
     ========================== Comment ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveComment(Comment comment) {
        this.commentDAO.save(comment);
    }

    @Override
    @Transactional
    public List<Comment> listComments() {
        return this.commentDAO.list();
    }

    @Override
    @Transactional
    public List<Comment> findCommentByItemID(long itemID) {
        return this.commentDAO.findByItemID(itemID);
    }

    /* ============================================================
     ========================== Feedback ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveFeedback(Feedback feedback) {
        this.feedbackDAO.save(feedback);
    }

    @Override
    @Transactional
    public List<Feedback> listFeedbacks() {
        return this.feedbackDAO.list();
    }

    @Override
    @Transactional
    public Feedback findFeedbackByTransactionID(long transactionID) {
        return this.feedbackDAO.findByTransactionID(transactionID);
    }

    @Override
    @Transactional
    public List<Feedback> findFeedbackBySellerID(long sellerID) {
        return this.feedbackDAO.findBySellerID(sellerID);
    }

    @Override
    @Transactional
    public List<Feedback> findFeedbackByBuyerID(long buyerID) {
        return this.feedbackDAO.findByBuyerID(buyerID);
    }

    /* ============================================================
     ========================== Transaction ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveTransaction(Transaction transaction) {
        this.transactionDAO.save(transaction);
    }

    @Override
    @Transactional
    public List<Transaction> listTransactions() {
        return this.transactionDAO.list();
    }

    @Override
    @Transactional
    public Transaction findTransactionByTransactionID(long transactionID) {
        return this.transactionDAO.findByTransactionID(transactionID);
    }

    @Override
    @Transactional
    public List<Transaction> findTransactionBySellerID(long sellerID) {
        return this.transactionDAO.findBySellerID(sellerID);
    }

    @Override
    @Transactional
    public List<Transaction> findTransactionByBuyerID(long buyerID) {
        return this.transactionDAO.findByBuyerID(buyerID);
    }

    @Override
    @Transactional
    public List<Transaction> findCompletedByTimestamp() {
        java.util.Date nowDate = new java.util.Date();
        java.util.Date startDate = new java.util.Date(nowDate.getTime() - (1000 * 60 * 60 * 24));
        java.util.Date endDate = new java.util.Date(nowDate.getTime());
        Timestamp startTime = new Timestamp(startDate.getTime());
        Timestamp endTime = new Timestamp(endDate.getTime());
        System.out.println("startTime : " + startTime);
        System.out.println("endTime : " + endTime);

        return this.transactionDAO.findCompletedByTimestamp(startTime, endTime);
    }

    /* ============================================================
     ========================== AutoBid ==========================
     ============================================================
     */
    @Override
    @Transactional
    public void saveAutoBid(AutoBid autoBid) {
        this.autoBidDAO.save(autoBid);
    }

    @Override
    @Transactional
    public List<AutoBid> listAutoBids() {
        return this.autoBidDAO.list();
    }

    @Override
    @Transactional
    public AutoBid findAutoBidByItemID(long autoBidID) {
        return this.autoBidDAO.findByItemID(autoBidID);
    }
}
