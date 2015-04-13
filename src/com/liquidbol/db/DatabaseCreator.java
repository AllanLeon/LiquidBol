/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liquidbol.db;

import com.liquidbol.db.persistence.ConnectionManager;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                dbCreator.createInitialInfo();
            }
        });
    }

    public void dropDatabase() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "DROP TABLE item_requests";
            String query2 = "DROP TABLE item_estimates";
            String query3 = "DROP TABLE item_sales";
            String query4 = "DROP TABLE service_receptions";
            String query5 = "DROP TABLE bill_payments";
            String query6 = "DROP TABLE bills";
            String query7 = "DROP TABLE rechargeable_items";
            String query8 = "DROP TABLE services";
            String query9 = "DROP TABLE clients_cxcc";
            String query10 = "DROP TABLE clients_cxc";
            String query11 = "DROP TABLE clients";
            String query12 = "DROP TABLE employees";
            String query13 = "DROP TABLE inventorys";
            String query14 = "DROP TABLE expenses";
            String query15 = "DROP TABLE stores";
            String query16 = "DROP TABLE offers";
            String query17 = "DROP TABLE item_discounts";
            String query18 = "DROP TABLE item_purchases";
            String query19 = "DROP TABLE purchases";
            String query20 = "DROP TABLE items";
            String query21 = "DROP TABLE debt_payments";
            String query22 = "DROP TABLE supplier_debts";
            String query23 = "DROP TABLE suppliers";

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
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n"
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
            String query18 = "CREATE TABLE bills (\n" +
                    "    bill_id INTEGER NOT NULL PRIMARY KEY \n" +
                    "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                    "    client_id INTEGER NOT NULL,\n" +
                    "    store_id INTEGER NOT NULL,\n" +
                    "    employee_id INTEGER NOT NULL,\n" +
                    "    bill_date DATE NOT NULL,\n" +
                    "    total_amount REAL NOT NULL,\n" +
                    "    is_billed BOOLEAN NOT NULL,\n" +
                    "    is_route BOOLEAN,\n" +
                    "    obs VARCHAR(100),\n" +
                    "    CONSTRAINT bills_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),\n" +
                    "    CONSTRAINT bills_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id),\n" +
                    "    CONSTRAINT bills_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)\n" +
                    ")";
            String query19 = "CREATE TABLE service_receptions (\n"
                    + "    servicereception_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    bill_id INTEGER NOT NULL,\n"
                    + "    service_id VARCHAR(10) NOT NULL,\n"
                    + "    rechargeableitem_id VARCHAR(10) NOT NULL,\n"
                    + "    reception_date DATE NOT NULL,\n"
                    + "    deliver_time TIMESTAMP NOT NULL,\n"
                    + "    quantity REAL NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT servicereceptions_service_id_ref FOREIGN KEY (service_id) REFERENCES services(service_id),\n"
                    + "    CONSTRAINT servicereceptions_rechargeableitem_id_ref FOREIGN KEY (rechargeableitem_id) REFERENCES rechargeable_items(rechargeableitem_id),\n"
                    + "    CONSTRAINT servicereceptions_bill_id_ref FOREIGN KEY (bill_id) REFERENCES bills(bill_id)\n"
                    + ")";
            String query20 = "CREATE TABLE item_sales (\n"
                    + "    itemsale_id INTEGER NOT NULL PRIMARY KEY \n"
                    + "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                    + "    bill_id INTEGER NOT NULL,\n"
                    + "    item_id VARCHAR(10) NOT NULL,\n"
                    + "    quantity INTEGER NOT NULL,\n"
                    + "    total_amount REAL NOT NULL,\n"
                    + "    obs VARCHAR(100),\n"
                    + "    CONSTRAINT itemsales_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),\n"
                    + "    CONSTRAINT itemsales_bill_id_ref FOREIGN KEY (bill_id) REFERENCES bills(bill_id)\n"
                    + ")";
            String query21 = "CREATE TABLE bill_payments (\n" +
                    "    billpayment_id INTEGER NOT NULL PRIMARY KEY \n" +
                    "                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                    "    bill_id INTEGER NOT NULL,\n" +
                    "    employee_id INTEGER NOT NULL,\n" +
                    "    pay_date DATE NOT NULL,\n" +
                    "    amount_paid REAL NOT NULL,\n" +
                    "    obs VARCHAR(100),\n" +
                    "    CONSTRAINT billpayments_bill_id_ref FOREIGN KEY (bill_id) REFERENCES bills(bill_id),\n" +
                    "    CONSTRAINT billpayments_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)\n" +
                    ")";
            String query22 = "CREATE TABLE item_estimates (\n"
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
            String query23 = "CREATE TABLE item_requests (\n"
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

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createInitialInfo() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query24 = "INSERT INTO clients(client_name, client_lastname, "
                        + "client_nit, client_billname, client_address, client_phone, "
                        + "client_phone2, client_email, client_companyname, "
                        + "client_frequency, client_regdate, client_isroute) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = result.prepareStatement(query24);
                statement.setString(1, "");
                statement.setString(2, "");
                statement.setInt(3, 0);
                statement.setString(4, "");
                statement.setString(5, "");
                statement.setInt(6, 0);
                statement.setInt(7, 0);
                statement.setString(8, "");
                statement.setString(9, "");
                statement.setInt(10, 0);
                statement.setDate(11, new Date(new java.util.Date().getTime()));
                statement.setBoolean(12, false);
                statement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void insertData() {
        try {
            Class.forName(DRIVER);
            Connection result = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            String query1 = "INSERT INTO suppliers(supplier_name, supplier_lastname,"
                    + "supplier_phone, supplier_phone2, supplier_company, supplier_address,"
                    + "supplier_email, supplier_city, supplier_regdate) VALUES('Pedro', 'Rojas',"
                    + "456132, 56413298, 'Apol', 'Calle 13 #666', 'asdwq@dsaf.com',"
                    + "'Guachinton', '2015-04-10')";
            String query2 = "INSERT INTO supplier_debts(supplier_id, amount, limit_date,"
                    + "max_amount) VALUES(1, 50, '2015-04-15', 500)";
            String query3 = "INSERT INTO debt_payments(debt_id, pay_date,  )"
                    + "VALUES(1, 2015-04-10, 20)";
            String query4 = "INSERT INTO items(item_id, item_measure, item_description,"
                    + "item_brand, item_industry, item_type, item_subtype, item_cost,"
                    + "item_price, item_dif, item_profit) VALUES('5641Q', 'm', 'Item de prueba',"
                    + "'3M', 'Estadounidense', 'Prueba', 'Prueba2', 17.5, 20, 2.5, 14.3)";
            String query5 = "INSERT INTO puchases(supplier_id, total_amount, purchase_date) "
                    + "VALUES(1, 200, '2015-04-10')";
            String query6 = "INSERT INTO item_purchases(item_id, purchase_id, unit_cost,"
                    + "quantity, total_amount) VALUES(1, 1, 17.5, 10, 175)";
            String query7 = "INSERT INTO item_discounts(item_id, min_quantity, percentage) "
                    + "VALUES(1, 3, 4)";
            String query8 = "INSERT INTO offers(type, percentage, start_date, end_date) "
                    + "VALUES('Prueba', 10, '2015-04-13', '2015-04-20')";
            String query9 = "INSERT INTO stores(store_name, store_address,"
                    + "store_phone) VALUES('La tienda', 'Al fondo a la derecha', 489156)";
            String query10 = "INSERT INTO expenses(store_id, pay_date, description,"
                    + "amount, obs) VALUES(1, '2015-04-10', 'Prueba', 100, 'obs')";
            String query11 = "INSERT INTO inventorys(item_id, store_id, quantity) "
                    + "VALUES('5641Q', 1, 10)";
            String query12 = "INSERT INTO employees(employee_id, store_id, employee_name,"
                    + "employee_lastname, employee_address, employee_phone, employee_phone2,"
                    + "employee_email, employee_regdate, employee_password, employee_type) "
                    + "VALUES(1, 1, 'Luis', 'Lopez', 'qwertw', 1234567, 645123, 'asd@qw.aje',"
                    + "'2015-04-13', 'pass', 'Admin')";
            String query13 = "INSERT INTO clients(client_name, client_lastname, client_nit,"
                    + "client_billname, client_address, client_phone, client_phone2,"
                    + "client_email, client_companyname, client_frecuency, client_regdate,"
                    + "client_isroute) VALUES('Laura', 'Perez', 9813, 'Perez', 'qwertfa',"
                    + "1567984, 65798513, 'weop@eqie.com', 'qwerq', 0, '2015-04-13', false)";
            String query14 = "INSERT INTO clients_cxc(client_id, clientscxc_debt,"
                    + "clientscxc_creditamount, clientscxc_creditdate, clientscxc_state) "
                    + "VALUES(1, 50, 100, '2015-08-30', 'Deuda')";
            String query15 = "INSERT INTO clients_cxcc(clientscxc_id, amount_paid, pay_date) "
                    + "VALUES(1, 10, '2015-04-10')";
            String query16 = "INSERT INTO services(service_id, service_capacity, service_unit,"
                    + "service_description, service_type, service_cost, service_price,"
                    + "service_dif, service_profit) VALUES('123S', 3, 's', 'Servicio prueba',"
                    + "'Prueba', 10, 20, 10, 50)";
            String query17 = "INSERT INTO rechargeable_items(rechargeableitem_id, client_id,"
                    + "description, type, capacity, unit, warranty_limit_date, obs) "
                    + "VALUES('1RI', 1, 'Extintor', 'Extintor', 1, 'm3', '2015-06-10', 'obs')";
            String query18 = "INSERT INTO bills(client_id, store_id, employee_id, bill_date,"
                    + "total_amount, is_billed, is_route, obs) VALUES(1, 1, 1, '2015-04-13',"
                    + "50, false, false, 'obs')";
            String query19 = "INSERT INTO service_receptions(bill_id, service_id,"
                    + "rechargeableitem_id, reception_date, deliver_time, quantity,"
                    + "total_amount, obs) VALUES(1, '123S', '1RI', '2015-04-13',"
                    + "'2015-04-20 15:20:00', 1, 30, 'obs')";
            String query20 = "INSERT INTO item_sales(bill_id, item_id, quantity,"
                    + "total_amount, obs) VALUES(1, '5641Q', 2, 20, 'obs')";
            String query21 = "INSERT INTO bill_payments(bill_id, employee_id, pay_date,"
                    + "amount_paid, obs) VALUES(1, 1, '2015-04-13', 10, 'obs')";
            String query22 = "INSERT INTO item_estimates(client_id, store_id, request_date,"
                    + "limit_date, total_amount, obs) VALUES(1, 1, '2015-04-13', '2015-04-20',"
                    + "200, 'obs')";
            String query23 = "INSERT INTO item_requests(itemestimate_id, item_id, quantity,"
                    + "total_amount) VALUES(1, '5641Q', 2, 20)";
            
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
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}