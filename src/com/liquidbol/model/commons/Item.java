/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.model.commons;

/**
 * Class that represent an item.
 * @author Allan Leon
 */
public class Item {
    
    private String id;
    private String measure;
    private String name;
    private String brand;
    private String industry;
    private String type;
    private String subtype;
    private Double cost;
    private Double price;
    private Double dif;
    private Double profit;

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
        this.id = id;
        this.measure = measure;
        this.name = name;
        this.brand = brand;
        this.industry = industry;
        this.type = type;
        this.subtype = subtype;
        this.cost = cost;
        this.price = price;
        this.dif = price - cost;
        this.profit = 100 * dif / cost;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @return the dif
     */
    public Double getDif() {
        return dif;
    }

    /**
     * @return the profit
     */
    public Double getProfit() {
        return profit;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param measure the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param subtype the subtype to set
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @param dif the dif to set
     */
    public void setDif(Double dif) {
        this.dif = dif;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
