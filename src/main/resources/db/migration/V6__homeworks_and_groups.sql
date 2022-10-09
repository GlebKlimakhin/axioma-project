delete from done_exercises;
alter table homeworks
add column group_id bigint;
alter table homeworks
add foreign key(group_id) references groups(id);