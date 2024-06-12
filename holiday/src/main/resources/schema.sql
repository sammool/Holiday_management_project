CREATE TABLE IF NOT EXISTS member (
    member_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    degree VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    leftover_days INT default 0,
    points INT default 0
);

CREATE TABLE IF NOT EXISTS leader(
    leader_id VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    degree VARCHAR(5) NOT NULL,
    name VARCHAR(10) NOT NULL,
)
