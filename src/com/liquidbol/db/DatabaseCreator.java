/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liquidbol.db;

import com.liquidbol.db.persistence.ConnectionManager;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the database creation and updates.
 *
 * @author Allan Leon
 */
public class DatabaseCreator {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String PASSWORD = "134679";
    private static final String USER = "liquiduser";
    private static final String JDBC_URL = "jdbc:derby:liquidbol_db";

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DatabaseCreator dbCreator = new DatabaseCreator();
                dbCreator.dropDatabase();
                dbCreator.createDatabase();
            }
        });
    }

    public void dropDatabase() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "DROP TABLE item_requests";
            String query2 = "DROP TABLE item_estimates";
            String query3 = "DROP TABLE item_payments";
            String query4 = "DROP TABLE item_sales";
            String query5 = "DROP TABLE item_bills";
            String query6 = "DROP TABLE service_payments";
            String query7 = "DROP TABLE service_receptions";
            String query8 = "DROP TABLE service_bills";
            String query9 = "DROP TABLE rechargeable_items";
            String query10 = "DROP TABLE services";
            String query11 = "DROP TABLE clients_cxcc";
            String query12 = "DROP TABLE clients_cxc";
            String query13 = "DROP TABLE clients";
            String query14 = "DROP TABLE employees";
            String query15 = "DROP TABLE inventorys";
            String query16 = "DROP TABLE expenses";
            String query17 = "DROP TABLE stores";
            String query18 = "DROP TABLE offers";
            String query19 = "DROP TABLE item_discounts";
            String query20 = "DROP TABLE item_purchases";
            String query21 = "DROP TABLE purchases";
            String query22 = "DROP TABLE items";
            String query23 = "DROP TABLE debt_payments";
            String query24 = "DROP TABLE supplier_debts";
            String query25 = "DROP TABLE suppliers";

            result.createStatement().execute(query1);
            result.createStatement().execute(query2);
            result.createStatement().execute(query3);
            result.createStatement().execute(query4);
            result.createStatement().execute(query5);
            result.createStatement().execute(query6);
            result.createStatement().execute(query7);
            result.createStatement().execute(query8);
            result.createStatement().execute(query9);
            result.createStatement().execute(query10);
            result.createStatement().execute(query11);
            result.createStatement().execute(query12);
            result.createStatement().execute(query13);
            result.createStatement().execute(query14);
            result.createStatement().execute(query15);
            result.createStatement().execute(query16);
            result.createStatement().execute(query17);
            result.createStatement().execute(query18);
            result.createStatement().execute(query19);
            result.createStatement().execute(query20);
            result.createStatement().execute(query21);
            result.createStatement().execute(query22);
            result.createStatement().execute(query23);
            result.createStatement().execute(query24);
            result.createStatement().execute(query25);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDatabase() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "CREATE TABLE suppliers (\n"
                    + "    supplier_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    supplier_name VARCHAR(20) NOT NULL,\n"
                    + "    supplier_lastname VARCHAR(30) NOT NULL,\n"
                    + "    supplier_phone INTEGER NOT NULL,\n"
                    + "    supplier_phone2 INTEGER,\n"
                    + "    supplier_company VARCHAR(50),\n"
                    + "    supplier_address VARCHAR(100),\n"
                    + "    supplier_email VARCHAR(50),\n"
                    + "    supplier_city VARCHAR(25) NOT NULL,\n"
                    + "    supplier_regdate DATE NOT NULL\n"
                    + ")";
            String query2 = "CREATE TABLE supplier_debts (\n"
                    + "    debt_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    supplier_id INTEGER NOT NULL,\n"
                    + "    amount REAL NOT NULL,\n"
                    + "    limit_date DATE,\n"
                    + "    max_amount REAL NOT NULL,\n"
                    + "    CONSTRAINT debts_supplier_id_ref FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)\n"
                    + ")";
            String query3 = "CREATE TABLE debt_payments (\n"
                    + "    debt_payment_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    debt_id INTEGER NOT NULL,\n"
                    + "    pay_date DATE NOT NULL,\n"
                    + "    amount REAL NOT NULL,\n"
                    + "    CONSTRAINT payments_debt_id_ref FOREIGN KEY (debt_id) REFERENCES supplier_debts(debt_id)\n"
                    + ")";
            String query4 = "CREATE TABLE items (\n"
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
            String query5 = "CREATE TABLE purchases (\n"
                    + "    purchase_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    supplier_id INTEGER NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    purchase_date DATE NOT NULL,\n"
                    + "    CONSTRAINT purchases_supplier_id_ref FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)\n"
                    + ")";
            String query6 = "CREATE TABLE item_purchases (\n"
                    + "    item_purchase_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    purchase_id INTEGER NOT NULL,\n"
                    + "    unit_cost REAL NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    CONSTRAINT itempurchases_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),\n"
                    + "    CONSTRAINT itempurchases_purchase_id_ref FOREIGN KEY (purchase_id) REFERENCES purchases(purchase_id)\n"
                    + ")";
            String query7 = "CREATE TABLE item_discounts (\n"
                    + "    discount_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    min_quantity INTEGER NOT NULL,\n"
                    + "    percentage REAL NOT NULL,\n"
                    + "    CONSTRAINT discounts_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id)\n"
                    + ")";
            String query8 = "CREATE TABLE offers (\n"
                    + "    offer_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    type VARCHAR(50) NOT NULL,\n"
                    + "    percentage REAL NOT NULL,\n"
                    + "    start_date DATE NOT NULL,\n"
                    + "    end_date DATE NOT NULL\n"
                    + ")";
            String query9 = "CREATE TABLE stores (\n"
                    + "    store_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    store_name VARCHAR(20) NOT NULL,\n"
                    + "    store_address VARCHAR(100) NOT NULL,\n"
                    + "    store_phone INTEGER NOT NULL\n"
                    + ")";
            String query10 = "CREATE TABLE expenses (\n"
                    + "    expense_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    store_id INTEGER NOT NULL,\n"
                    + "    pay_date DATE NOT NULL,\n"
                    + "    description VARCHAR(100) NOT NULL,\n"
                    + "    amount REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT expenses_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)\n"
                    + ")";
            String query11 = "CREATE TABLE inventorys (\n"
                    + "    inventory_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    store_id INTEGER NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    CONSTRAINT inventorys_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),\n"
                    + "    CONSTRAINT inventorys_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)\n"
                    + ")";
            String query12 = "CREATE TABLE employees (\n"
                    + "    employee_id INTEGER NOT NULL PRIMARY KEY,\n"
                    + "    store_id INTEGER NOT NULL,\n"
                    + "    employee_name VARCHAR(20) NOT NULL,\n"
                    + "    employee_lastname VARCHAR(30) NOT NULL,\n"
                    + "    employee_address VARCHAR(100),\n"
                    + "    employee_phone INTEGER NOT NULL,\n"
                    + "    employee_phone2 INTEGER,\n"
                    + "    employee_email VARCHAR(50),\n"
                    + "    employee_regdate DATE NOT NULL,\n"
                    + "    employee_password VARCHAR(20) NOT NULL,\n"
                    + "    employee_type VARCHAR(20) NOT NULL,\n"
                    + "    CONSTRAINT employees_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)\n"
                    + ")";
            String query13 = "CREATE TABLE clients (\n"
                    + "    client_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    client_name VARCHAR(30) NOT  NULL,\n"
                    + "    client_lastname VARCHAR(30) NOT NULL,\n"
                    + "    client_nit INTEGER NOT NULL,\n"
                    + "    client_billname VARCHAR(30),\n"
                    + "    client_address VARCHAR(50),\n"
                    + "    client_phone INTEGER,\n"
                    + "    client_phone2 INTEGER,\n"
                    + "    client_email VARCHAR(50),\n"
                    + "    client_companyname VARCHAR(50),\n"
                    + "    client_frequency INTEGER NOT NULL,\n"
                    + "    client_regdate DATE NOT NULL,\n"
                    + "    client_isroute BOOLEAN \n"
                    + ")";
            String query14 = "CREATE TABLE clients_cxc (\n"
                    + "    clientscxc_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    client_id INTEGER NOT NULL,\n"
                    + "    clientscxc_debt REAL NOT NULL,\n"
                    + "    clientscxc_creditamount REAL NOT NULL,\n"
                    + "    clientscxc_creditdate DATE NOT NULL,\n"
                    + "    clientscxc_state VARCHAR(15),\n"
                    + "    CONSTRAINT cxc_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id)\n"
                    + ")";
            String query15 = "CREATE TABLE clients_cxcc (\n"
                    + "    clientscxcc_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    clientscxc_id INTEGER NOT NULL,\n"
                    + "    amount_paid REAL NOT NULL,\n"
                    + "    pay_date DATE NOT NULL,\n"
                    + "    CONSTRAINT cxcc_clientscxc_id_ref FOREIGN KEY (clientscxc_id) REFERENCES clients_cxc(clientscxc_id)\n"
                    + ")";
            String query16 = "CREATE TABLE services (\n"
                    + "    service_id VARCHAR(10) NOT NULL PRIMARY KEY,\n"
                    + "    service_capacity REAL NOT NULL,\n"
                    + "    service_unit VARCHAR(7) NOT NULL,\n"
                    + "    service_description VARCHAR(100) NOT NULL,\n"
                    + "    service_type VARCHAR(50),\n"
                    + "    service_cost REAL NOT NULL,\n"
                    + "    service_price REAL NOT NULL,\n"
                    + "    service_dif REAL NOT NULL,\n"
                    + "    service_profit REAL NOT NULL\n"
                    + ")";
            String query17 = "CREATE TABLE rechargeable_items (\n"
                    + "    rechargeableitem_id VARCHAR(10) NOT NULL PRIMARY KEY,\n"
                    + "    client_id INTEGER NOT NULL,\n"
                    + "    description VARCHAR(50) NOT NULL,\n"
                    + "    type VARCHAR(20) NOT NULL,\n"
                    + "    capacity REAL NOT NULL,\n"
                    + "    unit VARCHAR(7) NOT NULL,\n"
                    + "    warranty_limit_date DATE NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT rechargeableitems_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id)\n"
                    + ")";
            String query18 = "CREATE TABLE service_bills (\n"
                    + "    servicebill_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    client_id INTEGER NOT NULL,\n"
                    + "    employee_id INTEGER NOT NULL,\n"
                    + "    bill_date DATE NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    is_billed BOOLEAN NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT servicebills_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),\n"
                    + "    CONSTRAINT servicebills_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)\n"
                    + ")";
            String query19 = "CREATE TABLE service_receptions (\n"
                    + "    servicereception_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    servicebill_id INTEGER NOT NULL,\n"
                    + "    service_id VARCHAR(10) NOT NULL,\n"
                    + "    rechargeableitem_id VARCHAR(10) NOT NULL,\n"
                    + "    reception_date DATE NOT NULL,\n"
                    + "    deliver_time TIMESTAMP NOT NULL,\n"
                    + "    quantity REAL NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT servicereceptions_service_id_ref FOREIGN KEY (service_id) REFERENCES services(service_id),\n"
                    + "    CONSTRAINT servicereceptions_rechargeableitem_id_ref FOREIGN KEY (rechargeableitem_id) REFERENCES rechargeable_items(rechargeableitem_id),\n"
                    + "    CONSTRAINT servicereceptions_servicebill_id_ref FOREIGN KEY (servicebill_id) REFERENCES service_bills(servicebill_id)\n"
                    + ")";
            String query20 = "CREATE TABLE service_payments (\n"
                    + "    servicepayment_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    servicebill_id INTEGER NOT NULL,\n"
                    + "    employee_id INTEGER NOT NULL,\n"
                    + "    pay_date DATE NOT NULL,\n"
                    + "    amount_paid REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT servicepayments_servicebill_id_ref FOREIGN KEY (servicebill_id) REFERENCES service_bills(servicebill_id),\n"
                    + "    CONSTRAINT servicepayments_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)\n"
                    + ")";
            String query21 = "CREATE TABLE item_bills (\n"
                    + "    itembill_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    client_id INTEGER NOT NULL,\n"
                    + "    store_id INTEGER NOT NULL,\n"
                    + "    employee_id INTEGER NOT NULL,\n"
                    + "    bill_date DATE NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    is_billed BOOLEAN NOT NULL,\n"
                    + "    is_route BOOLEAN,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT itembills_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),\n"
                    + "    CONSTRAINT itembills_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id),\n"
                    + "    CONSTRAINT itembills_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)\n"
                    + ")";
            String query22 = "CREATE TABLE item_sales (\n"
                    + "    itemsale_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    itembill_id INTEGER NOT NULL,\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT itemsales_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),\n"
                    + "    CONSTRAINT itemsales_itembill_id_ref FOREIGN KEY (itembill_id) REFERENCES item_bills(itembill_id)\n"
                    + ")";
            String query23 = "CREATE TABLE item_payments (\n"
                    + "    itempayment_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    itembill_id INTEGER NOT NULL,\n"
                    + "    employee_id INTEGER NOT NULL,\n"
                    + "    pay_date DATE NOT NULL,\n"
                    + "    amount_paid REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT itempayments_itembill_id_ref FOREIGN KEY (itembill_id) REFERENCES item_bills(itembill_id),\n"
                    + "    CONSTRAINT itempayments_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)\n"
                    + ")";
            String query24 = "CREATE TABLE item_estimates (\n"
                    + "    itemestimate_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    client_id INTEGER NOT NULL,\n"
                    + "    store_id INTEGER NOT NULL,\n"
                    + "    request_date DATE NOT NULL,\n"
                    + "    limit_date DATE NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT estimate_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),\n"
                    + "    CONSTRAINT estimate_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)\n"
                    + ")";
            String query25 = "CREATE TABLE item_requests (\n"
                    + "    itemrequest_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    itemestimate_id INTEGER NOT NULL,\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    CONSTRAINT requests_itemestimate_id_ref FOREIGN KEY (itemestimate_id) REFERENCES item_estimates(itemestimate_id),\n"
                    + "    CONSTRAINT requests_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id)\n"
                    + ")";

            result.createStatement().execute(query1);
            result.createStatement().execute(query2);
            result.createStatement().execute(query3);
            result.createStatement().execute(query4);
            result.createStatement().execute(query5);
            result.createStatement().execute(query6);
            result.createStatement().execute(query7);
            result.createStatement().execute(query8);
            result.createStatement().execute(query9);
            result.createStatement().execute(query10);
            result.createStatement().execute(query11);
            result.createStatement().execute(query12);
            result.createStatement().execute(query13);
            result.createStatement().execute(query14);
            result.createStatement().execute(query15);
            result.createStatement().execute(query16);
            result.createStatement().execute(query17);
            result.createStatement().execute(query18);
            result.createStatement().execute(query19);
            result.createStatement().execute(query20);
            result.createStatement().execute(query21);
            result.createStatement().execute(query22);
            result.createStatement().execute(query23);
            result.createStatement().execute(query24);
            result.createStatement().execute(query25);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDatabase() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "DROP TABLE item_offers";
            String query2 = "CREATE TABLE offers (\n"
                    + "    offer_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    type VARCHAR(50) NOT NULL,\n"
                    + "    percentage REAL NOT NULL,\n"
                    + "    start_date DATE NOT NULL,\n"
                    + "    end_date DATE NOT NULL\n"
                    + ")";
            result.createStatement().execute(query1);
            result.createStatement().execute(query2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}