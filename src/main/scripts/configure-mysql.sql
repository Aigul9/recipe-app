create database recipe_dev;
create database recipe_prod;

create user 'dev_user'@'localhost' identified by 'recipe';
create user 'prod_user'@'localhost' identified by 'recipe';

grant select on dev_user.* to 'dev_user'@'localhost';
grant insert on dev_user.* to 'dev_user'@'localhost';
grant delete on dev_user.* to 'dev_user'@'localhost';
grant update on dev_user.* to 'dev_user'@'localhost';

grant select on prod_user.* to 'prod_user'@'localhost';
grant insert on prod_user.* to 'prod_user'@'localhost';
grant delete on prod_user.* to 'prod_user'@'localhost';
grant update on prod_user.* to 'prod_user'@'localhost';