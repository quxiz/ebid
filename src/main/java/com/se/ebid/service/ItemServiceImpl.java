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
import com.se.ebid.controller.SellingType;
import com.se.ebid.dao.ItemDAO;
import com.se.ebid.dao.MemberDAO;
import com.se.ebid.dao.AutoBidDAO;
import com.se.ebid.dao.FeedbackDAO;
import com.se.ebid.dao.MessageDAO;
import com.se.ebid.dao.PhotoDAO;
import com.se.ebid.dao.TransactionDAO;
import com.se.ebid.dao.CommentDAO;
import com.se.ebid.entity.AutoBid;
import com.se.ebid.entity.Comment;
import com.se.ebid.entity.Feedback;
import com.se.ebid.entity.Item;
import com.se.ebid.entity.Member;
import com.se.ebid.entity.Message;
import com.se.ebid.entity.Photo;
import com.se.ebid.entity.Transaction;
import com.se.ebid.quartz.QuartzJob;
import com.se.ebid.quartz.ReportBidResultJob;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Quxiz
 */
@Service
public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO;
    private MemberDAO memberDAO;
    private AutoBidDAO autoBidDAO;
    private MessageDAO messageDAO;
    private TransactionDAO transactionDAO;
    private FeedbackDAO feedbackDAO;
    private PhotoDAO photoDAO;
    private CommentDAO commentDAO;

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Autowired
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Autowired
    public void setAutoBidDAO(AutoBidDAO autoBidDAO) {
        this.autoBidDAO = autoBidDAO;
    }

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Autowired
    public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    @Autowired
    public void setPhotoDAO(PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Autowired
    ServletContext servletContext;

    @Override
    public List<Item> search(SearchForm searchForm) {
        return this.itemDAO.search(searchForm);
    }

    @Override
    @Transactional
    public Item getItem(long itemID) {
        return this.itemDAO.findByItemID(itemID);
    }

    @Override
    @Transactional
    public Item getItemByTransactionID(long transactionID) {
        Transaction transaction = this.transactionDAO.findByTransactionID(transactionID);
        if (transaction != null) {
            return this.itemDAO.findByItemID(transaction.getItemID());
        }
        return null;
    }

    @Override
    public List<Photo> getPhoto(long itemID) {
        return this.photoDAO.findByItemID(itemID);
    }

    @Override
    public List<Comment> getComment(long itemID) {
        return this.commentDAO.findByItemID(itemID);
    }

    @Override
    @Transactional
    public int bid(BidForm bidForm) {
        System.out.println("step1");
        long memberID = Common.getMemberID();
        Member member = this.memberDAO.findByMemberID(memberID);
        if (member == null) {
            return ItemService.ERR_MISSING_MEMBER;
        }
        if (member.isBlacklisted()) {
            return ItemService.ERR_BLACKLIST;
        }
        if (member.getPaymentAccount() == null) {
            return ItemService.ERR_NO_PAY_ACC;
        }
        System.out.println("step1.1");
        AutoBid autoBid = this.autoBidDAO.findByItemID(bidForm.getItemID());
        if (autoBid == null) {
            return ItemService.ERR_MISSING_AUTOBID;
        }
        System.out.println("step1.2");
        Item item = this.itemDAO.findByItemID(bidForm.getItemID());
        if (item == null) {
            return ItemService.ERR_MISSING_ITEM;
        }
        if (memberID == item.getSellerID()){
            return ItemService.ERR_SAME_PERSON;
        }
        System.out.println("step2");
        double newMaxBid = bidForm.getMaxBid();
        if (newMaxBid < item.getPrice()) {
            return ItemService.ERR_LOW_PRICE;
        }
        double oldMaxBid = autoBid.getMaxBid();
        double newBidIncrement = bidForm.getBidIncrement();
        double oldBidIncrement = autoBid.getBidIncrement();
        System.out.println("step3");
        if (newMaxBid > oldMaxBid) {
            double price = oldMaxBid + newBidIncrement;
            if (price > newMaxBid) {
                price = newMaxBid;
            }
            item.setPrice(price);
            this.itemDAO.save(item);

            long outBidderID = autoBid.getBidderID();
            autoBid.setBidderID(memberID);
            autoBid.setMaxBid(newMaxBid);
            autoBid.setBidIncrement(newBidIncrement);
            autoBid.setTimestamp(new Timestamp(System.currentTimeMillis()));
            this.autoBidDAO.save(autoBid);
            System.out.println("step4");
            if (outBidderID >= 0) {
                Member outBidder = this.memberDAO.findByMemberID(outBidderID);
                if (outBidder != null && outBidderID != memberID) {
                    sendOutbidEmail(outBidder, item);
                    Message message = new Message();
                    message.setSenderID(Common.ADMIN_ID);
                    message.setReceiverID(outBidderID);
                    message.setMessage("You were outbitted at " + item.getTitle() + "<br/>"
                            + "Current price: " + item.getPrice() + "<br/>"
                            + "<br/>"
                            + "Beat it now!!!<br/>"
                            + "<a href=\"" + Common.BASE_URL + Common.VIEW_ITEM_URL + item.getItemID() + "\">"
                            + "Click to view" + "</a>");
                    message.setTimestamp(new Timestamp(System.currentTimeMillis()));
                    message.setSeen(false);
                    message.setSenderName(Common.ADMIN_NAME);
                    this.messageDAO.save(message);
                }
            }
        } else {
            double price = newMaxBid + oldBidIncrement;
            if (price > oldMaxBid) {
                price = oldMaxBid;
            }
            item.setPrice(price);
            this.itemDAO.save(item);
        }
        System.out.println("step5");
        return 1;
    }

    @Override
    public boolean sendOutbidEmail(Member member, Item item) {
        return Common.sendMail(member.getEmail(), "[ebid] Outbid notification",
                "You were outbitted at " + item.getTitle() + "\n"
                + "Current price: " + item.getPrice() + "\n"
                + "\n"
                + "Beat it now!!!\n"
                + Common.BASE_URL + Common.VIEW_ITEM_URL + item.getItemID()
        );
    }

    @Override
    @Transactional
    public Invoice buy(BuyForm buyForm) {
        Member member = this.memberDAO.findByMemberID(Common.getMemberID());
        Invoice invoice = new Invoice();
        if (member == null) {
            invoice.setItemID(ItemService.ERR_MISSING_MEMBER);
            return invoice;
        }
        if (member.isBlacklisted()) {
            invoice.setItemID(ItemService.ERR_BLACKLIST);
            return invoice;
        }
        if (member.getPaymentAccount() == null) {
            invoice.setItemID(ItemService.ERR_NO_PAY_ACC);
            return invoice;
        }

        long itemID = buyForm.getItemID();
        Item item = this.itemDAO.findByItemID(itemID);
        if (item == null) {
            invoice.setItemID(ItemService.ERR_MISSING_ITEM);
            return invoice;
        }
        if (item.getQuantity() < buyForm.getQuantity()) {
            invoice.setItemID(ItemService.ERR_NOT_ENOUGH_QTY);
            return invoice;
        }

        invoice.setItemID(itemID);
        invoice.setQuantity(buyForm.getQuantity());
        invoice.setTotal(buyForm.getQuantity() * item.getPrice());

        return invoice;
    }

    @Override
    @Transactional
    public long confirmBuy(BuyForm buyForm) {
        long itemID = buyForm.getItemID();
        Item item = this.itemDAO.findByItemID(itemID);
        long sellerID = item.getSellerID();
        long buyerID = Common.getMemberID();
        String sellerName = item.getSellerName();
        String buyerName = Common.getUserID();
        if (buyForm.getQuantity() > item.getQuantity()) {
            return -1;
        }
        Transaction transaction = new Transaction();
        transaction.setSellerID(sellerID);
        transaction.setSellerName(sellerName);
        transaction.setBuyerID(buyerID);
        transaction.setBuyerName(buyerName);
        transaction.setSellingType(SellingType.BUY);
        transaction.setItemID(itemID);
        transaction.setTitle(item.getTitle());
        transaction.setQuantity(buyForm.getQuantity());
        transaction.setPrice(item.getPrice() * buyForm.getQuantity());
        transaction.setDetail(item.getDetail());
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        this.transactionDAO.save(transaction);

        Feedback feedback = new Feedback();
        feedback.setTransactionID(transaction.getTransactionID());
        feedback.setSellerID(sellerID);
        feedback.setBuyerID(buyerID);
        feedback.setSellerName(sellerName);
        feedback.setBuyerName(buyerName);
        feedback.setItemID(itemID);
        this.feedbackDAO.save(feedback);

        return transaction.getTransactionID();
    }

    @Override
    @Transactional
    public long registerItem(RegisterItemForm registerItemForm) {
        System.out.println("REQ : register item");
        Item item = new Item();
        long memberID = Common.getMemberID();
        item.setSellerID(memberID);
        item.setSellerName(Common.getUserID());
        item.setTitle(registerItemForm.getTitle());
        item.setSpecifics(registerItemForm.getSpecifics());
        item.setDetail(registerItemForm.getDetail());
        item.setCategory(registerItemForm.getCategory());
        item.setSellingType(registerItemForm.getSellingType());
        item.setPrice(registerItemForm.getPrice());
        item.setQuantity(registerItemForm.getQuantity());
        item.setStartTime(registerItemForm.getStartTime());
        item.setSellerName(this.memberDAO.findByMemberID(memberID).getUserID());
        //     item.setEndTime(registerItemForm.getEndTime());
        item.setSellerID(Common.getMemberID());
        // item.setPaymentMethod(registerItemForm.getPaymentMethod());
        item.setShippingService(registerItemForm.getShippingService());
        item.setShippingCost(registerItemForm.getShippingCost());
        item.setPackageDetail(registerItemForm.getPackageDetail());
        item.setReturnPolicy(registerItemForm.getReturnPolicy());
        item.setTimestamp(new Timestamp(System.currentTimeMillis()));

        MultipartFile[] photoList = registerItemForm.getPhotos();
        if (registerItemForm.getEndTime() != null) {
            System.out.println(registerItemForm.getEndTime());
            System.out.println(registerItemForm.getEndTime().getTime());
            System.out.println(System.currentTimeMillis());
            System.currentTimeMillis();
            item.setEndTime(new Timestamp(registerItemForm.getEndTime().getTime()));
        }
        this.itemDAO.save(item);
        long itemID = item.getItemID();
        for (MultipartFile aPhoto : photoList) {
            if (!aPhoto.getContentType().substring(0, 5).equals("image")) {
                System.out.println(aPhoto.getContentType().substring(0, 5));
                continue;
            }

            Photo photo = new Photo();
            photo.setItemID(itemID);
            photo.setPhotoURL(null);
            long photoID = this.photoDAO.save(photo);
            String photoURL = servletContext.getRealPath("resources/uploadedImg/") + "/" + +photoID + "." + aPhoto.getContentType().substring(6);
            try {
                aPhoto.transferTo(new File(photoURL));
            } catch (IOException ex) {
                Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            photoURL = "/ebid/resources/uploadedImg/" + +photoID + "." + aPhoto.getContentType().substring(6);
            photo.setPhotoURL(photoURL);
            this.photoDAO.save(photo);
        }
        if (registerItemForm.getSellingType() == null) {
            System.out.println("null selling type");
        }
        if (registerItemForm.getSellingType() == SellingType.BID) {
            System.out.println("is bid");

            AutoBid autoBid = new AutoBid();
            autoBid.setItemID(itemID);
            autoBid.setBidderID(-1);
            autoBid.setMaxBid(item.getPrice());
            autoBid.setBidIncrement(0);
            autoBid.setTimestamp(new Timestamp(System.currentTimeMillis()));
            this.autoBidDAO.save(autoBid);

            Date date = new Date(registerItemForm.getEndTime().getTime());

            SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
            String cronDate = formatter.format(date);
            try {
                //SchedulerFactory schdFact = new StdSchedulerFactory("quartz.properties");
                //Scheduler scheduler = schdFact.getScheduler();
                Scheduler scheduler;
                // Setup the Job class and the Job group
                JobDetail job = newJob(ReportBidResultJob.class).withIdentity(
                        "CronQuartzJob" + new Timestamp(date.getTime()), "Group").build();

                job.getJobDataMap().put("itemID", itemID);
                // Create a Trigger that fires every 5 minutes.
                Trigger trigger = newTrigger()
                        .withIdentity("" + new Timestamp(date.getTime()), "Group")
                        .withSchedule(cronSchedule(cronDate).inTimeZone(TimeZone.getTimeZone("Asia/Bangkok")))
                        .build();

                // Setup the Job and Trigger with Scheduler & schedule jobs
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        System.out.println("item save complete");
        return item.getItemID();
    }

    public long getMaxBidderID(long itemID) {
        AutoBid autoBid = this.autoBidDAO.findByItemID(itemID);
        if (autoBid == null) {
            return ERR_NO_AUTOBID;
        }
        return autoBid.getBidderID();
    }

    public boolean reportBidResult(long itemID) {
        System.out.println("Activate reportBidResult : " + itemID);
        System.out.println("bidResult 1");
        if(this.itemDAO == null) System.out.println("itemDAO == null");
        Item item = this.itemDAO.findByItemID(itemID);
        System.out.println("bidResult 1.1");
        if (item == null) {
            System.out.println("item null");
            return false;
        }
        System.out.println("bidResult 2");
        AutoBid autoBid = this.autoBidDAO.findByItemID(itemID);
        if (autoBid == null) {
            return false;
        }
        if (autoBid.getBidderID() == -1) {
            sendNoBidderSellerEmail(this.memberDAO.findByMemberID(item.getSellerID()), item.getItemID());
            return false;
        } // no one bid
        System.out.println("bidResult 3");
        long sellerID = item.getSellerID();
        long buyerID = autoBid.getBidderID();
        String sellerName = item.getSellerName();
        String buyerName = this.memberDAO.findByMemberID(buyerID).getUserID();

        Transaction transaction = new Transaction();
        transaction.setSellerID(sellerID);
        transaction.setBuyerID(buyerID);
        transaction.setItemID(itemID);
        transaction.setSellerName(sellerName);
        transaction.setTitle(item.getTitle());
        transaction.setBuyerName(buyerName);
        transaction.setSellingType(SellingType.BID);
        transaction.setQuantity(item.getQuantity());
        transaction.setPrice(item.getPrice());
        transaction.setDetail(item.getDetail());
        //transaction.setDelivery(item.getDelivery()); ???
        transaction.setTimestamp(autoBid.getTimestamp());
        this.transactionDAO.save(transaction);
        System.out.println("bidResult 4");
        Feedback feedback = new Feedback();
        feedback.setTransactionID(transaction.getTransactionID());
        feedback.setSellerID(sellerID);
        feedback.setBuyerID(buyerID);
        feedback.setSellerName(sellerName);
        feedback.setBuyerName(buyerName);
        feedback.setItemID(itemID);
        this.feedbackDAO.save(feedback);
        System.out.println("bidResult 5");
        Member buyer = this.memberDAO.findByMemberID(buyerID);
        if (buyer == null) {
            return false;
        }
        if (!sendBuyerEmail(buyer, transaction)) {
            return false;
        }
        System.out.println("bidResult 6");
        Message messageBuyer = new Message();
        messageBuyer.setSenderID(Common.ADMIN_ID);
        messageBuyer.setReceiverID(buyerID);
        messageBuyer.setMessage("Congratulations, you won the auction!<br/>"
                + "<a href=\"" + Common.BASE_URL + Common.VIEW_ITEM_URL + item.getItemID() + "\">"
                + item.getTitle() + "</a><br/><br/>"
                + "<a href=\"" + Common.BASE_URL + Common.CHECK_OUT_URL + transaction.getTransactionID() + "\">"
                + "Click to check out" + "</a>");
        messageBuyer.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageBuyer.setSeen(false);
        messageBuyer.setSenderName(Common.ADMIN_NAME);
        this.messageDAO.save(messageBuyer);
        System.out.println("bidResult 7");
        Member seller = this.memberDAO.findByMemberID(sellerID);
        if (seller == null) {
            return false;
        }
        if (!sendSellerEmail(seller, transaction)) {
            return false;
        }
        System.out.println("bidResult 8");
        Message messageSeller = new Message();
        messageSeller.setSenderID(Common.ADMIN_ID);
        messageSeller.setReceiverID(sellerID);
        messageSeller.setMessage("There is a winner for your auction item!<br/>"
                + "<a href=\"" + Common.BASE_URL + Common.VIEW_ITEM_URL + item.getItemID() + "\">"
                + item.getTitle() + "</a><br/><br/>"
                + "<a href=\"" + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID() + "\">"
                + "Click to enter the feedback for your seller" + "</a>");
        messageSeller.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageSeller.setSeen(false);
        messageSeller.setSenderName(Common.ADMIN_NAME);
        this.messageDAO.save(messageSeller);
        System.out.println("bidResult 9");
        /*BidSchedule bidSchedule = BidScheduleDAO.findByItemID(itemID);
         if(bidSchedule == null) return false;
         bidSchedule.setCompleted(true);
         BidScheduleDAO.save(bidSchedule);*/
        //return reScheduler();
        return false;
    }

    private boolean sendNoBidderSellerEmail(Member member, long itemID) {
        return Common.sendMail(member.getEmail(), "[ebid] Your auction item is timed out!",
                "No one bid for your auction item ID: "
                + itemID
                + ".\n\n"
                + "If you want to reopen the auction, please register your auction item again.");
    }

    private boolean sendSellerEmail(Member member, Transaction transaction) {
        return Common.sendMail(member.getEmail(), "[ebid] There is a winner for your auction item!",
                "To enter the feedback for your seller, click on the link below (or copy and paste the URL into your browser): \n"
                + Common.BASE_URL + Common.GIVE_FEEDBACK_URL + transaction.getTransactionID());
    }

    private boolean sendBuyerEmail(Member member, Transaction transaction) {
        return Common.sendMail(member.getEmail(), "[ebid] Congratulations, you won the auction!",
                "To complete transaction, click on the link below (or copy and paste the URL into your browser): \n"
                + Common.BASE_URL + Common.CHECK_OUT_URL + transaction.getTransactionID());
    }

}
