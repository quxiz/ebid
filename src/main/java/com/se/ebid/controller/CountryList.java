/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

/**
 *
 * @author mtmmoei
 */
public class CountryList {
  private  List<Country> countries = new ArrayList<Country>();

    public List<Country> getCountries() {
        return countries;
    }

  public CountryList() {
    Locale[] locales = Locale.getAvailableLocales();
    for (Locale locale : locales) {
        try{
      String iso = locale.getISO3Country();
      String code = locale.getCountry();
      String name = locale.getDisplayCountry();

      if (!"".equals(iso) && !"".equals(code) && !"".equals(name)) {
        countries.add(new Country(iso, code, name));
      }
        }
        catch (MissingResourceException e){
     //do nothing
   }
    }

    Collections.sort(countries, new CountryComparator());
  }
}

class CountryComparator implements Comparator<Country> {
  private Comparator comparator;

  CountryComparator() {
    comparator = Collator.getInstance();
  }

  public int compare(Country o1, Country o2) {
    return comparator.compare(o1.name, o2.name);
  }
}

class Country {
    private String iso;
    private String code;
    public String name;


  Country(String iso, String code, String name) {
    this.iso = iso;
    this.code = code;
    this.name = name;
  }
 public String toString() {
//    return iso + " - " + code + " - " + name.toUpperCase();
     return name;
  }
}

