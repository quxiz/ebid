/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

/**
 *
 * @author mtmmoei
 */
public enum CategoryType {
    All("ทุกประเภท"),Antiques("ของเก่า"), Art("ศิลปะ"), Baby("เด็ก"), Books("หนังสือ"), Camera("กล้องและรูปถ่าย"), 
    CellPhones("โทรศัพท์มือถือ"), Clothing("เสื้อผ้า"), Music("เพลง"), MusicalInstruments("เครื่องดนตรี"),
    Pets("สัตว์เลี้ยง"), VideoGame("วิดีโอเกม"), Other("อื่นๆ");
     private String name;

    private CategoryType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
