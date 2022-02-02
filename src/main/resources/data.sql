INSERT INTO users (username, password, enabled)
values ('Wien', 'password', true);
INSERT INTO users (username, password, enabled)
values ('Barcelona', 'password', true);
INSERT INTO users (username, password, enabled)
values ('Munich', 'password', true);
INSERT INTO users (username, password, enabled)
values ('admin', 'password', true);
INSERT INTO authorities(username, authority)
values ('Wien', 'ROLE_USER');
INSERT INTO authorities(username, authority)
values ('Barcelona', 'ROLE_USER');
INSERT INTO authorities(username, authority)
values ('Munich', 'ROLE_USER');
INSERT INTO authorities(username, authority)
values ('admin', 'ROLE_ADMIN');