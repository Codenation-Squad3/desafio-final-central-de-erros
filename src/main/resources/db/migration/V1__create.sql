CREATE TABLE users (
    id uuid NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    email character varying(60) NOT NULL UNIQUE,
    nome character varying(120) NOT NULL,
    password character varying(255) NOT NULL,
    token character varying(255),
    PRIMARY KEY (id)
);

CREATE TABLE log (
    id character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    environment character varying(255) NOT NULL,
    last_occurrence timestamp without time zone DEFAULT now(),
    level character varying(255) NOT NULL,
    origin character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    user_id uuid NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE RESTRICT
);

CREATE TABLE occurrences (
    id uuid NOT NULL,
    dt_created timestamp without time zone NOT NULL,
    log_id character varying(255),
    user_id uuid NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE RESTRICT,
    FOREIGN KEY(log_id) REFERENCES log(id) ON DELETE RESTRICT
);