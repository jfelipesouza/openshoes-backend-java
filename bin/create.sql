
    create table category (
       id  bigserial not null,
        type varchar(50) not null,
        primary key (id)
    ); 

    create table product (
       id  bigserial not null,
        created_at TIMESTAMP WITHOUT TIME ZONE,
        image varchar(255) not null,
        logist_code varchar(255) not null,
        model varchar(50) not null,
        price float8 not null,
        size bytea not null,
        update_at TIMESTAMP WITHOUT TIME ZONE,
        category_id int8,
        primary key (id)
    ); 

    alter table category 
       add constraint UK_c2491gxyu6bsjw346i1fgojqf unique (type); 

    alter table product 
       add constraint FK1mtsbur82frn64de7balymq9s 
       foreign key (category_id) 
       references category; 
