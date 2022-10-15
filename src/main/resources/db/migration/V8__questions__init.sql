create table questions
(
    id              bigserial not null primary key,
    question        varchar(255),
    right_answer    varchar(255),
    exercise_id     bigint
);

create table answers
(
    answers          varchar(255),
    owner_id        bigint references questions(id)
);