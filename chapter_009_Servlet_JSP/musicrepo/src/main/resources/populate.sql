INSERT INTO roles (id, name) VALUES (1, 'admin');
INSERT INTO roles (id, name) VALUES (2, 'mandator');
INSERT INTO roles (id, name) VALUES (3, 'user');

INSERT INTO musictypes (type) VALUES ('rap');
INSERT INTO musictypes (type) VALUES ('rock');
INSERT INTO musictypes (type) VALUES ('jazz');
INSERT INTO musictypes (type) VALUES ('reggae');
INSERT INTO musictypes (type) VALUES ('pop');

INSERT INTO users (login, password, name, role_id) VALUES ('admin', 'admin', 'Admin', 1);