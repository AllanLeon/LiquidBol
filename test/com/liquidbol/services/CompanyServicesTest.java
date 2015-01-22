/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liquidbol.services;

import com.liquidbol.model.Company;
import com.liquidbol.model.Item;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of CompanyServices class.
 * @author Allan Leon
 */
public class CompanyServicesTest {
    
    private Company company;
    private CompanyServices companyServices;
    
    @Before
    public void setUp() {
        company = new Company();
        companyServices = new CompanyServices(company);
    }

    /**
     * Test of createItem method, of class CompanyServices.
     */
    @Test
    public void testCreateItem() {
        Item expResult = createTestItem();
        String id = "123";
        String measure = "";
        String description = "Test item";
        String brand = "";
        String industry = "";
        String type = "";
        String subtype = "";
        Double cost = 10.0;
        Double price = 15.0;
        Item result = companyServices.createItem(id, measure, description, brand, industry, type, subtype, cost, price);
        assertEquals(expResult, result);
    }

    /**
     * Test of saveItem method, of class CompanyServices.
     */
    @Test
    public void testSaveItem() throws Exception {
        Item item = createTestItem();
        companyServices.saveItem(item);
        Item expResult = item;
        Item result = company.findItemById(item.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of loadItems method, of class CompanyServices.
     */
    @Test
    public void testLoadItems() throws Exception {
        companyServices.loadItems();
        int expResult = 1;
        int result = company.getAllItems().size();
        assertEquals(expResult, result);
    }
    
    private Item createTestItem() {
        String id = "123";
        String measure = "";
        String description = "Test item";
        String brand = "";
        String industry = "";
        String type = "";
        String subtype = "";
        Double cost = 10.0;
        Double price = 15.0;
        return new Item(id, measure, description, brand, industry, type, subtype, cost, price);
    }
}
