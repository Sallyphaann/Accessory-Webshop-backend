CREATE TABLE category
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50),
    PRIMARY KEY (id),
    UNIQUE (name)
);
CREATE TABLE product
(
    id         int NOT NULL AUTO_INCREMENT,
    name       varchar(50),
    quantity   int NOT NULL,
    price      float NOT NULL ,
    category_id int,
    PRIMARY KEY (id),
    UNIQUE (name),
    FOREIGN KEY (category_id) REFERENCES category (id)
);
CREATE TABLE "user"
(
    id          int          NOT NULL AUTO_INCREMENT,
    firstname   varchar(200) NOT NULL,
    lastname    varchar(100) NOT NULL,
    email       varchar(100) NOT NULL,
    address     varchar(200) NOT NULL,
    phoneNumber varchar(200) NOT NULL ,
    role_name   varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);
CREATE TABLE account
(
    id        int          NOT NULL AUTO_INCREMENT,
    username  varchar(200) NOT NULL,
    password  varchar(100) NOT NULL,
    user_id int NULL,
    PRIMARY KEY (id),
    UNIQUE (username),
    FOREIGN KEY (user_id) REFERENCES "user"(id)

);



