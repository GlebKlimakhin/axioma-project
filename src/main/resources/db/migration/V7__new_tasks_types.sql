create table questions_answers
(
    id                  bigserial not null primary key,
    right_answer        text
);

alter table exercises
add column qa_id bigint;
alter table exercises
add foreign key(QA_id) references questions_answers(id);