package com.liquidbol.db;

import com.liquidbol.db.persistence.ConnectionManager;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Inserts all requested 3M products into existing DB.
 * @author Franco Montiel
 */
public class DB3MItemsCreator {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String PASSWORD = "134679";
    private static final String USER = "liquiduser";
    private static final String JDBC_URL = "jdbc:derby:liquidbol_db";

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DB3MItemsCreator dbCreator = new DB3MItemsCreator();
                dbCreator.dropDatabase();
                dbCreator.createDatabase();
                dbCreator.insertData();
            }
        });
    }

    public void dropDatabase() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "DROP TABLE inventorys";
            String query2 = "DROP TABLE item_discounts";
            String query3 = "DROP TABLE items";
            result.createStatement().execute(query1);
            result.createStatement().execute(query2);
            result.createStatement().execute(query3);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB3MItemsCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDatabase() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "CREATE TABLE items (\n"
                    + "    item_id VARCHAR(10) NOT NULL PRIMARY KEY,\n"
                    + "    item_measure VARCHAR(15),\n"
                    + "    item_description VARCHAR(100) NOT NULL,\n"
                    + "    item_brand VARCHAR(20),\n"
                    + "    item_industry VARCHAR(20),\n"
                    + "    item_type VARCHAR(50) NOT NULL,\n"
                    + "    item_subtype VARCHAR(50),\n"
                    + "    item_cost REAL NOT NULL,\n"
                    + "    item_price REAL NOT NULL,\n"
                    + "    item_dif REAL NOT NULL,\n"
                    + "    item_profit REAL NOT NULL\n"
                    + ")";
            String query2 = "CREATE TABLE item_discounts (\n"
                    + "    discount_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    min_quantity INTEGER NOT NULL,\n"
                    + "    percentage REAL NOT NULL,\n"
                    + "    CONSTRAINT discounts_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id)\n"
                    + ")";
            String query3 = "CREATE TABLE inventorys (\n"
                    + "    inventory_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    store_id INTEGER NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    CONSTRAINT inventorys_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),\n"
                    + "    CONSTRAINT inventorys_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)\n"
                    + ")";

            result.createStatement().execute(query1);
            result.createStatement().execute(query2);
            result.createStatement().execute(query3);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertData() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1a = "INSERT INTO items(item_id, item_measure, item_description, "
                    + "item_brand, item_industry, item_type, item_subtype, item_cost, "
                    + "item_price, item_dif, item_profit) VALUES('UNK1', 'Pza.', 'Respirador contra "
                    + "polvos plegable VFLEX-9105 (N95)', '3M', 'Estadounidense', "
                    + "'Respiradores', 'Seguridad Industrial', 0, 6, 6, 6)";
            String query1b = "INSERT INTO item_discounts(item_id, min_quantity, percentage) "
                    + "VALUES('UNK1', 100, 4)";
            String query1c = "INSERT INTO inventorys(item_id, store_id, quantity) "
                    + "VALUES('UNK1', 1, 10)";
            String query2a = "INSERT INTO items(item_id, item_measure, item_description, "
                    + "item_brand, item_industry, item_type, item_subtype, item_cost, "
                    + "item_price, item_dif, item_profit) VALUES('UNK2', 'Pza.', 'Respirador contra "
                    + "polvos plegable azul 9910', '3M', 'Estadounidense', "
                    + "'Respiradores', 'Seguridad Industrial', 0, 10, 10, 10)";
            String query2b = "INSERT INTO item_discounts(item_id, min_quantity, percentage) "
                    + "VALUES('UNK2', 15, 4)";
            String query2c = "INSERT INTO inventorys(item_id, store_id, quantity) "
                    + "VALUES('UNK2', 1, 10)";
            String query3a = "INSERT INTO items(item_id, item_measure, item_description, "
                    + "item_brand, item_industry, item_type, item_subtype, item_cost, "
                    + "item_price, item_dif, item_profit) VALUES('TM-1100', 'Pza.', 'Respirador contra "
                    + "polvos 8210 (N95)', '3M', 'Estadounidense', "
                    + "'Respiradores', 'Seguridad Industrial', 0, 9.45, 9.45, 9.45)";
            String query3b = "INSERT INTO item_discounts(item_id, min_quantity, percentage) "
                    + "VALUES('TM-1100', 20, 4)";
            String query3c = "INSERT INTO inventorys(item_id, store_id, quantity) "
                    + "VALUES('TM-1100', 1, 10)";
            String query4a = "INSERT INTO items(item_id, item_measure, item_description, "
                    + "item_brand, item_industry, item_type, item_subtype, item_cost, "
                    + "item_price, item_dif, item_profit) VALUES('UNK3', 'Pza.', 'Respirador contra "
                    + "polvos 8210V (N95)', '3M', 'Estadounidense', "
                    + "'Respiradores', 'Seguridad Industrial', 0, 16.50, 16.50, 16.50)";
            String query4b = "INSERT INTO item_discounts(item_id, min_quantity, percentage) "
                    + "VALUES('UNK3', 10, 4)";
            String query4c = "INSERT INTO inventorys(item_id, store_id, quantity) "
                    + "VALUES('UNK3', 1, 10)";
            String query5a = "INSERT INTO items(item_id, item_measure, item_description, "
                    + "item_brand, item_industry, item_type, item_subtype, item_cost, "
                    + "item_price, item_dif, item_profit) VALUES('TM-1150', 'Pza.', 'Respirador contra "
                    + "vapores orgánicos 8247 (R95)', '3M', 'Estadounidense', "
                    + "'Respiradores', 'Seguridad Industrial', 0, 34, 34, 34)";
            String query5b = "INSERT INTO item_discounts(item_id, min_quantity, percentage) "
                    + "VALUES('TM-1150', 20, 4)";
            String query5c = "INSERT INTO inventorys(item_id, store_id, quantity) "
                    + "VALUES('TM-1150', 1, 10)";
            //data insertion....no need of DIF or PROFIT! talk 2 Allan
            //Should us CompanyServices>CreateItem()...then save using ItemCrud save()
            result.createStatement().execute(query1a);
            result.createStatement().execute(query1b);
            result.createStatement().execute(query1c);            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB3MItemsCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
