drop table if exists groups_homeworks;
delete from homeworks;
delete from users_groups;
delete from groups;
alter table homeworks
add column group_id bigint;
alter table homeworks
add foreign key(group_id) references groups(id);