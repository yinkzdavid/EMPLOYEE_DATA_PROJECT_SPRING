DROP USER 'employee_user'@'localhost';

CREATE USER 'employee_user'@'localhost' identified by 'employee123';

GRANT ALL privileges on employee_db.* to 'employee_user'@'localhost';

flush privileges;

create DATABASE employee_db;