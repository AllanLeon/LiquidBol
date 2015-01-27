DROP TABLE clients;
DROP TABLE clients_cxc;
DROP TABLE clients_cxcc;
DROP TABLE employees;
DROP TABLE employees_stores;
DROP TABLE expenses;
DROP TABLE inventorys;
DROP TABLE items;
DROP TABLE item_discounts;
DROP TABLE item_estimates;
DROP TABLE item_promos;
DROP TABLE item_requests;
DROP TABLE item_sales;
DROP TABLE paid_debts;
DROP TABLE purchases;
DROP TABLE rechargeable_items;
DROP TABLE services;
DROP TABLE service_receptions;
DROP TABLE service_sales;
DROP TABLE stores;
DROP TABLE suppliers;
DROP TABLE suppliers_debts;

CREATE TABLE suppliers (
    supplier_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    supplier_name VARCHAR(20) NOT NULL,
    supplier_lastname VARCHAR(30) NOT NULL,
    supplier_phone INTEGER NOT NULL,
    supplier_phone2 INTEGER,
    supplier_company VARCHAR(50),
    supplier_address VARCHAR(100),
    supplier_email VARCHAR(50),
    supplier_city VARCHAR(25) NOT NULL,
    supplier_regdate DATE NOT NULL
);

CREATE TABLE supplier_debts (
    debt_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    supplier_id INTEGER NOT NULL,
    amount REAL NOT NULL,
    limit_date DATE,
    max_amount REAL NOT NULL,
    CONSTRAINT debts_supplier_id_ref FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE debt_payments (
    debt_payment_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    debt_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    amount REAL NOT NULL,
    CONSTRAINT payments_debt_id_ref FOREIGN KEY (debt_id) REFERENCES supplier_debts(debt_id)
);

CREATE TABLE items (
    item_id VARCHAR(10) NOT NULL PRIMARY KEY,
    item_measure VARCHAR(15),
    item_description VARCHAR(100) NOT NULL,
    item_brand VARCHAR(20),
    item_industry VARCHAR(20),
    item_type VARCHAR(50) NOT NULL,
    item_subtype VARCHAR(50),
    item_cost REAL NOT NULL,
    item_price REAL NOT NULL,
    item_dif REAL NOT NULL,
    item_profit REAL NOT NULL
);

CREATE TABLE purchases (
    purchase_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    supplier_id INTEGER NOT NULL,
    total_amount REAL NOT NULL,
    purchase_date DATE NOT NULL,
    CONSTRAINT purchases_supplier_id_ref FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE item_purchases (
    item_purchase_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_id VARCHAR(10) NOT NULL,
    purchase_id INTEGER NOT NULL,
    unit_cost REAL NOT NULL,
    quantity INTEGER NOT NULL,
    total_amount REAL NOT NULL,
    CONSTRAINT itempurchases_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),
    CONSTRAINT itempurchases_purchase_id_ref FOREIGN KEY (purchase_id) REFERENCES purchases(purchase_id)
);

CREATE TABLE item_discounts (
    discount_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_id VARCHAR(10) NOT NULL,
    min_quantity INTEGER NOT NULL,
    percentage REAL NOT NULL,
    CONSTRAINT discounts_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id)
);

CREATE TABLE offers (
    offer_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    type VARCHAR(50) NOT NULL,
    percentage REAL NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE stores (
    store_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    store_name VARCHAR(20) NOT NULL,
    store_address VARCHAR(100) NOT NULL,
    store_phone INTEGER NOT NULL
);

CREATE TABLE expenses (
    expense_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    store_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    description VARCHAR(100) NOT NULL,
    amount REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT expenses_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE inventorys (
    inventory_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_id VARCHAR(10) NOT NULL,
    store_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT inventorys_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),
    CONSTRAINT inventorys_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE employees (
    employee_id INTEGER NOT NULL PRIMARY KEY,
    store_id INTEGER NOT NULL,
    employee_name VARCHAR(20) NOT NULL,
    employee_lastname VARCHAR(30) NOT NULL,
    employee_address VARCHAR(100),
    employee_phone INTEGER NOT NULL,
    employee_phone2 INTEGER,
    employee_email VARCHAR(50),
    employee_regdate DATE NOT NULL,
    employee_password VARCHAR(20) NOT NULL,
    employee_type VARCHAR(20) NOT NULL,
    CONSTRAINT employees_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE clients (
    client_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_name VARCHAR(30) NOT  NULL,
    client_lastname VARCHAR(30) NOT NULL,
    client_nit INTEGER NOT NULL,
    client_billname VARCHAR(30),
    client_address VARCHAR(50),
    client_phone INTEGER,
    client_phone2 INTEGER,
    client_email VARCHAR(50),
    client_companyname VARCHAR(50),
    client_frequency INTEGER NOT NULL,
    client_regdate DATE NOT NULL,
    client_isroute BOOLEAN 
);

CREATE TABLE clients_cxc (
    clientscxc_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_id INTEGER NOT NULL,
    clientscxc_debt REAL NOT NULL,
    clientscxc_creditamount REAL NOT NULL,
    clientscxc_creditdate DATE NOT NULL,
    clientscxc_state VARCHAR(15),
    CONSTRAINT cxc_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE clients_cxcc (
    clientscxcc_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    clientscxc_id INTEGER NOT NULL,
    amount_paid REAL NOT NULL,
    pay_date DATE NOT NULL,
    CONSTRAINT cxcc_clientscxc_id_ref FOREIGN KEY (clientscxc_id) REFERENCES clients_cxc(clientscxc_id)
);

CREATE TABLE services (
    service_id VARCHAR(10) NOT NULL PRIMARY KEY,
    service_capacity REAL NOT NULL,
    service_unit VARCHAR(7) NOT NULL,
    service_description VARCHAR(100) NOT NULL,
    service_type VARCHAR(50),
    service_cost REAL NOT NULL,
    service_price REAL NOT NULL,
    service_dif REAL NOT NULL,
    service_profit REAL NOT NULL
);

CREATE TABLE rechargeable_items (
    rechargeableitem_id VARCHAR(10) NOT NULL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    description VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL,
    capacity REAL NOT NULL,
    unit VARCHAR(7) NOT NULL,
    warranty_limit_date DATE NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT rechargeableitems_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE service_bills (
    servicebill_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    bill_date DATE NOT NULL,
    total_amount REAL NOT NULL,
    is_billed BOOLEAN NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT servicebills_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),
    CONSTRAINT servicebills_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE service_receptions (
    servicereception_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    servicebill_id INTEGER NOT NULL,
    service_id VARCHAR(10) NOT NULL,
    rechargeableitem_id VARCHAR(10) NOT NULL,
    reception_date DATE NOT NULL,
    deliver_time TIMESTAMP NOT NULL,
    total_amount REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT servicereceptions_service_id_ref FOREIGN KEY (service_id) REFERENCES services(service_id),
    CONSTRAINT servicereceptions_rechargeableitem_id_ref FOREIGN KEY (rechargeableitem_id) REFERENCES rechargeable_items(rechargeableitem_id),
    CONSTRAINT servicereceptions_servicebill_id_ref FOREIGN KEY (servicebill_id) REFERENCES service_bills(servicebill_id)
);

/*CREATE TABLE service_sales (
    servicesale_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    servicebill_id INTEGER NOT NULL,
    servicereception_id INTEGER NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT servicesales_servicereception_id_ref FOREIGN KEY (servicereception_id) REFERENCES service_receptions(servicereception_id),
    CONSTRAINT servicesales_servicebill_id_ref FOREIGN KEY (servicebill_id) REFERENCES service_bills(servicebill_id)
);*/

CREATE TABLE service_payments (
    servicepayment_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    servicebill_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    amount_paid REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT servicepayments_servicebill_id_ref FOREIGN KEY (servicebill_id) REFERENCES service_bills(servicebill_id),
    CONSTRAINT servicepayments_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE item_bills (
    itembill_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_id INTEGER NOT NULL,
    store_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    bill_date DATE NOT NULL,
    total_amount REAL NOT NULL,
    is_billed BOOLEAN NOT NULL,
    is_route BOOLEAN,
    obs VARCHAR(100),
    CONSTRAINT itembills_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),
    CONSTRAINT itembills_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id),
    CONSTRAINT itembills_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE item_sales (
    itemsale_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    itembill_id INTEGER NOT NULL,
    item_id VARCHAR(10) NOT NULL,
    quantity INTEGER NOT NULL,
    total_amount REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT itemsales_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),
    CONSTRAINT itemsales_itembill_id_ref FOREIGN KEY (itembill_id) REFERENCES item_bills(itembill_id)
);

CREATE TABLE item_payments (
    itempayment_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    itembill_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    amount_paid REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT itempayments_itembill_id_ref FOREIGN KEY (itembill_id) REFERENCES item_bills(itembill_id),
    CONSTRAINT itempayments_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE item_estimates (
    itemestimate_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_id INTEGER NOT NULL,
    store_id INTEGER NOT NULL,
    request_date DATE NOT NULL,
    limit_date DATE NOT NULL,
    total_amount REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT estimate_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),
    CONSTRAINT estimate_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE item_requests (
    itemrequest_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    itemestimate_id INTEGER NOT NULL,
    item_id VARCHAR(10) NOT NULL,
    quantity INTEGER NOT NULL,
    total_amount REAL NOT NULL,
    CONSTRAINT requests_itemestimate_id_ref FOREIGN KEY (itemestimate_id) REFERENCES item_estimates(itemestimate_id),
    CONSTRAINT requests_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id)
);