create table exercises(
id bigserial not null unique,
name varchar(55) not null unique,
difficulty integer not null,
data text not null,
type varchar(50) not null,
created_at date default current_timestamp,
primary key (id)
);

create table users(
id bigserial not null primary key,
username varchar(55) not null unique,
password varchar(255) not null,
firstname varchar(55) not null,
lastname varchar(55) not null,
status varchar(55) default 'ACTIVE',
email varchar(55) unique,
role varchar(55) default 'USER',
rating int default 0
);

create table done_exercises(
user_id bigint not null,
exercise_id bigint not null,
score int,
done_at date,
type varchar(55),
primary key(user_id, exercise_id)
);

create table homeworks(
id bigserial not null,
description varchar(255),
primary key (id)
);

insert into exercises(data, name, difficulty, type) VALUES
('На Праценской горе, на том самом месте, где он упал с древком знамени в руках, лежал князь Андрей Болконский, истекая кровью, и, сам не зная того, стонал тихим, жалостным и детским стоном.
К вечеру он перестал стонать и совершенно затих. Он не знал, как долго продолжалось его забытье. Вдруг он опять почувствовал себя живым и страдающим от жгучей и разрывающей что-то боли в голове.
«Где оно, это высокое небо, которого я не знал до сих пор и увидал нынче? — было первою его мыслью. — И страдания этого я не знал до сих пор. Но где я?»
Он стал прислушиваться и услыхал звуки приближающегося топота лошадей и звуки голосов, говоривших по-французски. Он раскрыл глаза. Над ним было опять все то же высокое небо с еще выше поднявшимися плывущими облаками, сквозь которые виднелась синеющая бесконечность. Он не поворачивал головы и не видал тех, которые, судя по звуку копыт и голосов, подъехали к нему и остановились.
Подъехавшие верховые были Наполеон, сопутствуемый двумя адъютантами. Бонапарте, объезжая поле сражения, отдавал последние приказания об усилении батарей, стреляющих по плотине Аугеста, и рассматривал убитых и раненых, оставшихся на поле сражения.
— De beaux hommes! 1 — сказал Наполеон, глядя на убитого русского гренадера, который с уткнутым в землю лицом и почернелым затылком лежал на животе, откинув далеко одну уже закоченевшую руку.
— Les munitions des pièces de position sont épuisées, sire! 2 — сказал в это время адъютант, приехавший с батарей, стрелявших по Аугесту.
— Faites avancer celles de la réserve 3, — сказал Наполеон, и, отъехав несколько шагов, он остановился над князем Андреем, лежавшим навзничь с брошенным подле него древком знамени (знамя уже, как трофей, было взято французами)', 'exercise1', 5, 'READING_SPEED'),
('this is test reading exercise2', 'exercise2', 5, 'READING_SPEED'),
('this is test reading exercise3', 'exercise3', 5, 'READING_SPEED'),
('this is test reading exercise4', 'exercise4', 0,'READING_SPEED'),
('this is test reading exercise5', 'exercise5', 1, 'READING_SPEED'),
('this is test reading exercise6', 'exercise6', 1, 'READING_SPEED'),
('this is test reading exercise7', 'exercise7', 2, 'READING_SPEED'),
('this is test reading exercise8', 'exercise8', 3, 'READING_SPEED'),
('this is test reading exercise9', 'exercise9', 4, 'READING_SPEED'),
('this is test reading exercise10', 'exercise10', 1, 'READING_SPEED');

create table groups(
    id bigserial not null,
    name varchar(255) not null,
    primary key (id)
);

create table users_groups(
    user_id bigint not null,
    group_id bigint not null,
    primary key (user_id, group_id),
    foreign key (user_id) references users(id),
    foreign key (group_id) references groups(id)
);

create table groups_homeworks(
    group_id bigint not null,
    homework_id bigint not null,
    primary key (group_id, homework_id),
    foreign key (group_id) references groups(id),
    foreign key (homework_id) references homeworks(id)
);

create table homeworks_exercises(
    homework_id bigint not null,
    exercise_id bigint not null,
    primary key (homework_id, exercise_id),
    foreign key (homework_id) references homeworks(id),
    foreign key (exercise_id) references exercises(id)
);

insert into users(username, password, firstname, lastname, email, role, status, rating) values
('user1', '$2a$12$4Vxok0Ubj8wUuDk8sUciFeBgCgfNy.4hIl9XmJDkRvkyQBWDxWiUe', 'user1', 'user1', 'user1@gmail.com', 'ADMIN', default, default),
('user2', '$2a$12$JS30AoZb3jwCFNhZ1.QtZeYAChznRsikM1GFPuATSDGlgLgD9Afyy', 'user2', 'user2', 'user2@gmail.com', 'USER', default, default);

insert into groups(name) values
('Bababa');

insert into users_groups(user_id, group_id) values
(1, 1),
(2, 1);

