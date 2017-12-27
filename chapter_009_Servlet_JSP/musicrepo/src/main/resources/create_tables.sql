CREATE TABLE IF NOT EXISTS roles(id INTEGER PRIMARY KEY, name VARCHAR(60) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS addresses(id SERIAL PRIMARY KEY, city VARCHAR(60),
  street VARCHAR(60), home INTEGER);

CREATE TABLE IF NOT EXISTS musictypes(id SERIAL PRIMARY KEY, type VARCHAR(60) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, login VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL, name VARCHAR(60), role_id INTEGER REFERENCES roles(id),
  address_id INTEGER REFERENCES addresses(id));

CREATE TABLE IF NOT EXISTS usersmusic(user_id INTEGER NOT NULL REFERENCES users(id),
  musictype_id INTEGER NOT NULL REFERENCES musictypes(id));

--Добавление первичного составного ключа в usersmusic
ALTER TABLE usersmusic ADD PRIMARY KEY (user_id, musictype_id);