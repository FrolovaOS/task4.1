CREATE TABLE IF NOT EXISTS info
(
    firstName  character varying(255) NOT NULL ,
    lastName character varying(255) NOT NULL ,
    age integer  NOT NULL,
    timestamp integer NOT NULL,
    PRIMARY KEY ("timestamp")
);