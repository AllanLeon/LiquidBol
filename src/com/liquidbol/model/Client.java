package com.liquidbol.model;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a client.
 * @author Allan Leon
 */
public class Client extends Person implements Serializable {
    
    private int nit;
    private String companyName;
    private int frequency;
    private String billName;
    private boolean route;
    private Collection<CXC> receivableAccounts;
    private Collection<RechargeableItem> rechargeableItems;
    private Collection<ItemEstimate> itemEstimates;
    private Collection<Bill> bills;

    /**
     * Constructor method.
     * @param nit
     * @param companyName
     * @param frequency
     * @param billName
     * @param route
     * @param id
     * @param name
     * @param lastname
     * @param address
     * @param phone
     * @param phone2
     * @param email
     * @param regDate 
     */
    public Client(int id, String name, String lastname, int nit, String billName, String address, int phone, int phone2, String email, Date regDate, String companyName, int frequency, boolean route) {
        super(id, name, lastname, address, phone, phone2, email, regDate);
        this.nit = nit;
        this.companyName = companyName;
        this.frequency = frequency;
        this.billName = billName;
        this.route = route;
        this.receivableAccounts = new HashSet<>();
        this.rechargeableItems = new HashSet<>();
        this.itemEstimates = new HashSet<>();
        this.bills = new HashSet<>();
    }

    /**
     * @return the nit
     */
    public int getNit() {
        return nit;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }
    
    /**
     * @return the billName
     */
    public String getBillName() {
        return billName;
    }

    /**
     * @return the route
     */
    public boolean isRoute() {
        return route;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(int nit) {
        this.nit = nit;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    /**
     * @param billName the billName to set
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(boolean route) {
        this.route = route;
    }
    
    public int getNumberOfReceivableAccounts() {
        return receivableAccounts.size();
    }
    
    public Collection<CXC> getAllReceivableAccounts() {
        return receivableAccounts;
    }
    
    public void addReceivableAccount(CXC cxc) {
        receivableAccounts.add(cxc);
    }
    
    public int getNumberOfRechargeableItems() {
        return rechargeableItems.size();
    }
    
    public Collection<RechargeableItem> getAllRechargeableItems() {
        return rechargeableItems;
    }
    
    public void addRechargeableItem(RechargeableItem rechargeableItem) {
        rechargeableItems.add(rechargeableItem);
    }
    
    public int getNumberOfItemEstimates() {
        return itemEstimates.size();
    }
    
    public Collection<ItemEstimate> getAllItemEstimates() {
        return itemEstimates;
    }
    
    public void addItemEstimate(ItemEstimate itemEstimate) {
        itemEstimates.add(itemEstimate);
    }
    
    public int getNumberOfBills() {
        return bills.size();
    }
    
    public Collection<Bill> getAllBills() {
        return bills;
    }
    
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * @param receivableAccounts the receivableAccounts to set
     */
    public void setReceivableAccounts(Collection<CXC> receivableAccounts) {
        this.receivableAccounts = receivableAccounts;
    }

    /**
     * @param rechargeableItems the rechargeableItems to set
     */
    public void setRechargeableItems(Collection<RechargeableItem> rechargeableItems) {
        this.rechargeableItems = rechargeableItems;
    }

    /**
     * @param itemEstimates the itemEstimates to set
     */
    public void setItemEstimates(Collection<ItemEstimate> itemEstimates) {
        this.itemEstimates = itemEstimates;
    }

    /**
     * @param bills the itemBills to set
     */
    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }
    
    public void increaseFrequency() {
        frequency++;
    }
    
    public Collection<CXC> getValidReceivableAccounts() {
        Set<CXC> result = new HashSet<>();
        Date today = new Date(new java.util.Date().getTime());
        for (CXC cxc : receivableAccounts) {
            if (/*cxc.getCreditLimitDate().compareTo(today) < 0 && */cxc.getDebt() > 0) {
                result.add(cxc);
            }
        }
        return result;
    }
    
    public Collection<CXC> searchReceivableAccountsByState(String state) {
        Set<CXC> result = new HashSet<>();
        for (CXC cxc : receivableAccounts) {
            if (cxc.getState().contains(state)) {
                result.add(cxc);
            }
        }
        return result;
    }
    
    public Collection<RechargeableItem> searchRechargeableItemsById(String id) {
        Set<RechargeableItem> result = new HashSet<>();
        for (RechargeableItem recItem : rechargeableItems) {
            if (recItem.getId().contains(id)) {
                result.add(recItem);
            }
        }
        return result;
    }
    
    public Collection<RechargeableItem> searchRechargeableItemsByDescription(String description) {
        Set<RechargeableItem> result = new HashSet<>();
        for (RechargeableItem recItem : rechargeableItems) {
            if (recItem.getDescription().contains(description)) {
                result.add(recItem);
            }
        }
        return result;
    }
    
    public Collection<RechargeableItem> searchRechargeableItemsByType(String type) {
        Set<RechargeableItem> result = new HashSet<>();
        for (RechargeableItem recItem : rechargeableItems) {
            if (recItem.getType().contains(type)) {
                result.add(recItem);
            }
        }
        return result;
    }
    
    public Collection<Bill> searchBillsByEmployeeId(int id) {
        Set<Bill> result = new HashSet<>();
        for (Bill current : bills) {
            if (current.getEmployee().getId() == id) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<Bill> searchBillsByStoreId(int id) {
        Set<Bill> result = new HashSet<>();
        for (Bill current : bills) {
            if (current.getStore().getId() == id) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<Bill> searchBillsByBilled(boolean billed) {
        Set<Bill> result = new HashSet<>();
        for (Bill current : bills) {
            if (current.isBilled() == billed) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<Bill> searchBillsByRoute(boolean route) {
        Set<Bill> result = new HashSet<>();
        for (Bill current : bills) {
            if (current.isRoute() == route) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<Bill> searchBillsByDate(Date date) {
        Set<Bill> result = new HashSet<>();
        for (Bill bill : bills) {
            if (bill.getDate().compareTo(date) == 0) {
                result.add(bill);
            }
        }
        return result;
    }
    
    public Collection<Bill> searchBillsBetweenDates(Date startDate, Date endDate) {
        Set<Bill> result = new HashSet<>();
        for (Bill bills : bills) {
            Date expenseDate = bills.getDate();
            if (expenseDate.compareTo(startDate) >= 0 && expenseDate.compareTo(endDate) <= 0) {
                result.add(bills);
            }
        }
        return result;
    }
    
    public Collection<ItemEstimate> searchItemEstimatesByDate(Date date) {
        Set<ItemEstimate> result = new HashSet<>();
        for (ItemEstimate current : itemEstimates) {
            if (current.getRequestDate().compareTo(date) == 0) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ItemEstimate> searchItemEstimatesBetweenDates(Date startDate, Date endDate) {
        Set<ItemEstimate> result = new HashSet<>();
        for (ItemEstimate current : itemEstimates) {
            Date expenseDate = current.getRequestDate();
            if (expenseDate.compareTo(startDate) >= 0 && expenseDate.compareTo(endDate) <= 0) {
                result.add(current);
            }
        }
        return result;
    }
    
    public Collection<ItemEstimate> getValidItemEstimates() {
        Set<ItemEstimate> result = new HashSet<>();
        Date today = new Date(new java.util.Date().getTime());
        for (ItemEstimate current : itemEstimates) {
            if (current.getLimitDate().compareTo(today) < 0) {
                result.add(current);
            }
        }
        return result;
    }
}