DROP TABLE IF EXISTS feature_list_table;
 
CREATE TABLE feature_list_table (
  title VARCHAR(20) NOT NULL,
  description VARCHAR(200) NOT NULL,
  client VARCHAR(1),
  client_priority INT NOT NULL,
  target_date DATE NOT NULL,
  product_area VARCHAR(100) NOT NULL,
  feature_status VARCHAR(50) NOT NULL,
  PRIMARY KEY (title, client)
);
 
INSERT INTO FEATURE_LIST_TABLE 
(title, description, client, client_priority, target_date, product_area, feature_status) 
VALUES
('WILL FAIL', 'All actions on this feature will simulate failure condition.', 'A', '1','2020-10-05', 'Claims','In Development'),
('FEATURE1', 'TEST DESCRIPTION FOR FEATURE 3', 'B', '1','2020-10-05', 'Claims','In Development'),
('FEATURE2', 'TEST DESCRIPTION FOR FEATURE 4', 'B', '4','2020-10-05', 'Claims','In Development'),
('FEATURE4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut ', 'C', '1','2020-12-12', 'Policies','Enabled')
;