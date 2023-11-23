CREATE TABLE `order` (
                         id INT NOT NULL AUTO_INCREMENT,
                         orderDate DATETIME,
                         customer_id INT NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (customer_id) REFERENCES `user` (id)
);

CREATE TABLE orderDetail (
                             id INT NOT NULL AUTO_INCREMENT,
                             orderId INT NOT NULL,
                             product_id INT NOT NULL,
                             price FLOAT NOT NULL,
                             amount INT NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (orderId) REFERENCES `order` (id),
                             FOREIGN KEY (product_id) REFERENCES product (id)
);
