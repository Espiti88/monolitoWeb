CREATE TABLE IF NOT EXISTS product (
    id serial NOT NULL,
    title varchar(250) NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app_user (
    id serial NOT NULL,
    email varchar(250) NOT NULL,
    username varchar(250) NOT NULL,
    password varchar(250) NOT NULL,
    role varchar(250) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE  IF NOT EXISTS ordenes
(
    id serial NOT NULL,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.app_user (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
    
);