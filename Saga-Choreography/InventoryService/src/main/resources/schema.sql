DROP TABLE IF EXISTS  PO_ORDER;
CREATE TABLE PO_ORDER(
                      id SERIAL PRIMARY KEY ,
                      user_id NUMERIC,
                      product_id NUMERIC,
                      price NUMERIC,
                      order_status CHAR(50),
                      payment_status CHAR(50),
                      inventory_status CHAR(50)
);