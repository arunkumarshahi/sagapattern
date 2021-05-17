DROP TABLE IF EXISTS  USER_BALANCE;
CREATE TABLE USER_BALANCE(
                      user_Id SERIAL PRIMARY KEY ,
                      balance NUMERIC
);