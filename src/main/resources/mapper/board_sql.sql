create table board_table(
board_index number not null PRIMARY KEY,
board_subject varchar2(500) not null,
board_content long not null,
board_file varchar2(500),
board_date date not null,
board_excelFile varchar2(500) not null
);

create SEQUENCE board_seq
increment by 1
start with 1
minvalue 1;

commit;


create table excel_table(
    excel_name varchar2(50),
    excel_address varchar2(500),
    excel_phone varchar2(50),
    board_index number
);