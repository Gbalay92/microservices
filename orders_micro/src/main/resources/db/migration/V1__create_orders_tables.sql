CREATE TABLE orders (
    id          UUID        NOT NULL PRIMARY KEY,
    user_id     UUID        NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    status      VARCHAR(20) NOT NULL,
    created_at  TIMESTAMP   NOT NULL
);

CREATE TABLE order_items (
    id          UUID        NOT NULL PRIMARY KEY,
    order_id    UUID        NOT NULL REFERENCES orders(id),
    product_id  UUID        NOT NULL,
    quantity    INT         NOT NULL,
    unit_price  NUMERIC(10, 2) NOT NULL
);
