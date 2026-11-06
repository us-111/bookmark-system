use bookmark_db;
select * from bookmark_db.category where id = 1;
create table user(
                     id bigint primary key auto_increment,
                     username varchar(50) not null unique,
                     password varchar(100) not null,
                     nickname varchar(50) default null,
                     create_time datetime default current_timestamp,
                     update_time datetime default current_timestamp on update current_timestamp,
                     deleted tinyint not null default 0
);
alter table category add column user_id bigint not null ;
alter table bookmark add column user_id bigint not null ;

CREATE TABLE sys_log (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         user_id BIGINT,
                         username VARCHAR(50),
                         operation VARCHAR(100),
                         method VARCHAR(200),
                         params TEXT,
                         create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM category;
SELECT * FROM sys_log;