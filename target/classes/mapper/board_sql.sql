create table board_table(
board_index number not null PRIMARY KEY,
board_subject varchar2(500) not null,
board_content long not null,
board_file varchar2(500),
board_date date not null
);

create SEQUENCE board_seq
increment by 1
start with 1
minvalue 1;

commit;