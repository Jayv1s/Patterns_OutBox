CREATE TABLE `user` (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    money INT
);

CREATE TABLE user_events (
    id_event INT PRIMARY KEY AUTO_INCREMENT,
    id_user INT,
    money INT,
    status enum('PENDING', 'IN_PROCESS', 'COMPLETED') NOT NULL
);

CREATE TABLE global_aggregation (
    id INT PRIMARY KEY,
    total_money INT,
    transaction_count INT,
    last_updated TIMESTAMP
);

CREATE TABLE user_aggregations (
    user_id INT PRIMARY KEY,
    total_money INT,
    transaction_count INT,
    last_updated TIMESTAMP
);