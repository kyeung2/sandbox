# jdbc_basics

## setup
- Created a Postgres DB locally using a `docker-compose`. See docker-compose.yml

- Manually created the DB and inserted one row into it (Using IntelliJ's Database view) before using the application.

```sql
CREATE TABLE account(
                        user_id serial PRIMARY KEY,
                        username VARCHAR (50) UNIQUE NOT NULL,
                        password VARCHAR (50) NOT NULL,
                        email VARCHAR (355) UNIQUE NOT NULL,
                        created_on TIMESTAMP NOT NULL,
                        last_login TIMESTAMP
);

INSERT INTO account ( username, password, email, created_on) VALUES ('kyeung','password', 'yeung.kye@pm.me', CURRENT_TIMESTAMP )
```

- db details
    - username: postgres // default for Postgres
    - password: example