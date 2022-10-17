create table exercise_settings
(
    id              bigserial primary key not null,
    operations_type varchar(255),
    digits_amount   int,
    rows_amount     int,
    digits_limit    int
);

alter table exercises
add column settings_id bigint;
alter table exercises
add foreign key(settings_id) references exercise_settings(id);