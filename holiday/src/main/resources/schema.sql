CREATE TABLE IF NOT EXISTS member (
    member_id VARCHAR(20) PRIMARY KEY,
    degree VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    leftover_days INT default 0,
    points INT default 0
);
