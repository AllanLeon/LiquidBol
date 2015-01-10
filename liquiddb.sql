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
    ammount REAL NOT NULL,
    limit_days INTEGER,
    max_ammount REAL NOT NULL,
    CONSTRAINT debts_supplier_id_ref FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE paid_debts (
    paid_dept_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    debt_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    ammount REAL NOT NULL,
    CONSTRAINT paiddebts_debt_id_ref FOREIGN KEY (debt_id) REFERENCES supplier_debts(debt_id)
);

CREATE TABLE items (
    item_id VARCHAR(10) NOT NULL PRIMARY KEY,
    item_measure VARCHAR(15),
    item_name VARCHAR(100) NOT NULL,
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
    item_id VARCHAR(10) NOT NULL,
    supplier_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    purchase_date DATE NOT NULL,
    total_ammount REAL NOT NULL,
    CONSTRAINT purchases_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),
    CONSTRAINT purchases_supplier_id_ref FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE item_discounts (
    discount_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_id VARCHAR(10) NOT NULL,
    min_quantity INTEGER NOT NULL,
    percentage REAL NOT NULL,
    CONSTRAINT discounts_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id)
);

CREATE TABLE item_promos (
    promo_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_type VARCHAR(50) NOT NULL,
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
    ammount REAL NOT NULL,
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
    employee_name VARCHAR(20) NOT NULL,
    employee_lastname VARCHAR(30) NOT NULL,
    employee_address VARCHAR(100),
    employee_phone INTEGER NOT NULL,
    employee_phone2 INTEGER,
    employee_email INTEGER
);

CREATE TABLE employees_stores (
    employee_store_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    employee_id INTEGER NOT NULL,
    store_id INTEGER NOT NULL,
    CONSTRAINT es_employee_id_ref FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    CONSTRAINT es_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE clients (
    client_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_name VARCHAR(30) NOT NULL,
    client_lastname VARCHAR(30) NOT NULL,
    client_nit INTEGER NOT NULL,
    client_address VARCHAR(50),
    client_phone INTEGER,
    client_phone2 INTEGER,
    client_companyname VARCHAR(50),
    client_frequency INTEGER NOT NULL,
    client_regdate DATE NOT NULL
);

CREATE TABLE clients_cxc (
    clientscxc_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    client_id INTEGER NOT NULL,
    clientscxc_debt REAL NOT NULL,
    clientscxc_creditammount REAL NOT NULL,
    clientscxc_credittime DATE NOT NULL,
    clientscxc_state VARCHAR(15),
    CONSTRAINT cxc_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE clients_cxcc (
    clientscxcc_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    clientscxc_id INTEGER NOT NULL,
    ammount_paid REAL NOT NULL,
    pay_date DATE NOT NULL,
    CONSTRAINT cxcc_clientscxc_id_ref FOREIGN KEY (clientscxc_id) REFERENCES clients_cxc(clientscxc_id)
);

CREATE TABLE services (
    service_id VARCHAR(10) NOT NULL PRIMARY KEY,
    service_measure VARCHAR(15),
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
    capacity REAL NOT NULL,
    unit VARCHAR(5) NOT NULL,
    item_type VARCHAR(20) NOT NULL,
    warranty_limit_date DATE NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT rechargeableitems_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE service_receptions (
    servicereception_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    rechargeableitem_id VARCHAR(10) NOT NULL,
    reception_date DATE NOT NULL,
    deliver_date DATE NOT NULL,
    deliver_time TIME NOT NULL,
    amount_paid REAL NOT NULL,
    total_amount REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT servicereceptions_rechargeableitem_id_ref FOREIGN KEY (rechargeableitem_id) REFERENCES rechargeable_items(rechargeableitem_id)
);

CREATE TABLE service_sales (
    servicesale_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    servicereception_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    ammount_paid REAL NOT NULL,
    obs VARCHAR(100),
    CONSTRAINT servicesales_servicereception_id_ref FOREIGN KEY (servicereception_id) REFERENCES service_receptions(servicereception_id)
);

CREATE TABLE item_sales (
    item_sale_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_id VARCHAR(10) NOT NULL,
    client_id INTEGER NOT NULL,
    store_id INTEGER NOT NULL,
    pay_date DATE NOT NULL,
    quantity INTEGER NOT NULL,
    total_ammount REAL NOT NULL,
    paid_ammount REAL NOT NULL,
    is_route BOOLEAN,
    obs VARCHAR(100),
    CONSTRAINT sales_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),
    CONSTRAINT sales_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),
    CONSTRAINT sales_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE item_estimates (
    item_estimate_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    request_date DATE NOT NULL,
    limit_date DATE NOT NULL,
    total_ammount REAL NOT NULL,
    obs VARCHAR(100)
);

CREATE TABLE item_requests (
    item_request_id INTEGER NOT NULL PRIMARY KEY 
                GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    item_estimate_id INTEGER NOT NULL,
    item_id VARCHAR(10) NOT NULL,
    client_id INTEGER NOT NULL,
    store_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    total_ammount REAL NOT NULL,
    CONSTRAINT requests_item_estimate_id_ref FOREIGN KEY (item_estimate_id) REFERENCES item_estimates(item_estimate_id),
    CONSTRAINT requests_item_id_ref FOREIGN KEY (item_id) REFERENCES items(item_id),
    CONSTRAINT requests_client_id_ref FOREIGN KEY (client_id) REFERENCES clients(client_id),
    CONSTRAINT requests_store_id_ref FOREIGN KEY (store_id) REFERENCES stores(store_id)
);