alter table homeworks
add creation_time date default CURRENT_TIMESTAMP,
add expiration_date date;