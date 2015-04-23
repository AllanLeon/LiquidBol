package com.liquidbol.services;

import static com.liquidbol.addons.MagikarpScreen.ANSI_CYAN;
import static com.liquidbol.addons.MagikarpScreen.ANSI_PURPLE;
import static com.liquidbol.addons.MagikarpScreen.ANSI_RESET;
import com.liquidbol.db.persistence.ItemDiscountCrud;
import com.liquidbol.db.persistence.PersistenceException;
import com.liquidbol.model.Discount;
import com.liquidbol.model.Item;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the operations an item can execute.
 * @author Allan Leon
 */
public class ItemServices {
    
    private static final Logger LOG = Logger.getLogger(ItemServices.class.getName());
    private final ItemDiscountCrud discountCrudManager;
    
    public ItemServices() {
        this.discountCrudManager = new ItemDiscountCrud();
    }
    
    public Discount createDiscount(int id, int minQuantity, Double percentage) {
        if (minQuantity == 0) {
            throw new IllegalArgumentException("Invalid discount minimum quantity");
        } else {
            Discount discount = new Discount(id, minQuantity, percentage);
            return discount;
        }
    }
    
    public Discount addDiscountToItem(Discount element, Item parent) throws PersistenceException, ClassNotFoundException {
        element = discountCrudManager.save(element, parent);
        parent.addDiscount(element);
        LOG.info(String.format(ANSI_PURPLE + "Item discount: %d saved" + ANSI_RESET, element.getId()));
        return element;
    }
    
    public void loadItemDiscounts(Item parent) throws PersistenceException, ClassNotFoundException {
        parent.setDiscounts(discountCrudManager.findByItemId(parent.getId()));
        LOG.info(String.format(ANSI_CYAN + "%d item discounts loaded" + ANSI_RESET, parent.getAllDiscounts().size()));
    }
    
    public void loadAllItemInfo(Item parent) {
        try {
            loadItemDiscounts(parent);
        } catch (PersistenceException | ClassNotFoundException ex) {
            LOG.info("Couldn't load company info");
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Discount mergeItemDiscount(int id, int minQuantity, Double percentage)
            throws PersistenceException, ClassNotFoundException {
        Discount oldDiscount = discountCrudManager.find(id);
        Discount newDiscount = new Discount(id, minQuantity, percentage);
        oldDiscount = discountCrudManager.merge(newDiscount);
        return oldDiscount;
    }
}