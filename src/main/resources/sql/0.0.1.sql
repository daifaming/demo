DROP TABLE t_test_tb1;
CREATE TABLE IF NOT EXISTS t_test_tb2{
  `id` INT NOT NULL AUTO_INCREMENT,
  `customerId` VARCHAR(64) NOT NULL,
  `customerName` VARCHAR(64) DEFAULT NULL,
  `customerId` VARCHAR(64) NOT NULL,
  `createTime` DATE,
  PRIMARY KEY (`id`)
}