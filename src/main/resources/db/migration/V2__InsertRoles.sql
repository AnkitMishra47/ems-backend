-- Create ems_role table
CREATE TABLE ems_role (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

-- Insert initial data into ems_role table
INSERT INTO ems_role (id, name) VALUES (1, 'ADMIN_ROLE');
INSERT INTO ems_role (id, name) VALUES (2, 'EMPLOYEE_ROLE');

-- Add additional tables and schema changes as needed