/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class that represent an item.
 * @author Allan Leon
 */
public class Item extends Product implements Serializable {
    
    private String name;
    private String measure;
    private String brand;
    private String industry;
    private String subtype;
    private Collection<Discount> discounts;

    /**
     * Simple constructor method.
     * @param id 
     */
    public Item(String id) {
        super(id);
    }

    /**
     * Constructor of the class that includes all the variables as parameters.
     * @param id
     * @param measure
     * @param name
     * @param brand
     * @param industry
     * @param type
     * @param subtype
     * @param cost
     * @param price
     */
    public Item(String id, String measure, String name, String brand, String industry, String type, String subtype, Double cost, Double price) {
        super(id, type, cost, price);
        this.name = name;
        this.measure = measure;
        this.brand = brand;
        this.industry = industry;
        this.subtype = subtype;
        this.discounts = new HashSet<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @return the subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param measure the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @param industry the industry to set
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * @param subtype the subtype to set
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    
    public int getNumberOfDiscounts() {
        return discounts.size();
    }
    
    public Collection<Discount> getAllDiscounts() {
        return discounts;
    }
    
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }
}
