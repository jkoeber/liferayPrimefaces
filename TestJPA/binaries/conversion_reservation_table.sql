CREATE TABLE test_reservation
(
   id      BIGINT          NOT NULL,
   bezng   VARCHAR(75),
   beschr  VARCHAR(2000),
   crdate  DATETIME,
   userid  BIGINT          NOT NULL
)
ENGINE=InnoDB;

