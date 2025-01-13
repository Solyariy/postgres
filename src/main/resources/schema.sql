drop table if exists "books";
drop table if exists "authors";

create table "authors" (
    "id" serial primary key not null ,
    "name" varchar(255),
    "age" integer
);

create table "books" (
    "isbn" varchar(255) primary key,
    "title" varchar(255),
    "author_id" bigint,
    foreign key ("author_id") references authors(id)
)

