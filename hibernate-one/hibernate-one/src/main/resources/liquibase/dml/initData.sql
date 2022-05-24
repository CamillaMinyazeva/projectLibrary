--liquibase formatted sql

--changeset mikelevin:init_post
insert into post (id, text, title, version) values (nextval('post_id_seq'), 'Post 1', 'Title 1', 0);
insert into post (id, text, title, version) values (nextval('post_id_seq'), 'Post 2', 'Title 2', 0);
insert into post (id, text, title, version) values (nextval('post_id_seq'), 'Post 3', 'Title 3', 0);

--changeset mikelevin:init_comments
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 1', 1, 0);
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 2', 1, 0);
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 3', 1, 0);

insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 1', 2, 0);
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 2', 2, 0);
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 3', 2, 0);

insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 1', 3, 0);
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 2', 3, 0);
insert into comment (id, text, post_id, version) values (nextval('comment_id_seq'), 'Comment 3', 3, 0);