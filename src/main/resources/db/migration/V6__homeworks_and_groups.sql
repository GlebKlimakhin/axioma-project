drop table if exists groups_homeworks;
alter table homeworks
add column group_id bigint;
alter table homeworks
add foreign key(group_id) references groups(id);