-- PostgreSQL DDL

CREATE TABLE film (
  id                 BIGSERIAL PRIMARY KEY,
  titel              VARCHAR(255) NOT NULL,
  varighed_minutter  INTEGER NOT NULL CHECK (varighed_minutter > 0)
);

CREATE TABLE sal (
  id         BIGSERIAL PRIMARY KEY,
  navn       VARCHAR(255) NOT NULL,
  kapacitet  INTEGER NOT NULL CHECK (kapacitet > 0)
);

CREATE TABLE forestilling (
  id         BIGSERIAL PRIMARY KEY,
  film_id    BIGINT NOT NULL REFERENCES film(id),
  sal_id     BIGINT NOT NULL REFERENCES sal(id),
  start_tid  TIMESTAMPTZ NOT NULL
);

CREATE TABLE reservation (
  id            BIGSERIAL PRIMARY KEY,
  oprettet_tid  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE reservation_post (
  id              BIGSERIAL PRIMARY KEY,
  reservation_id  BIGINT NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
  forestilling_id BIGINT NOT NULL REFERENCES forestilling(id),
  antal_pladser   INTEGER NOT NULL CHECK (antal_pladser > 0)
);

-- Indeks (god praksis for performance på joins og dine queries)
CREATE INDEX idx_forestilling_start_tid ON forestilling (start_tid);
CREATE INDEX idx_rp_forestilling_id ON reservation_post (forestilling_id);
CREATE INDEX idx_rp_reservation_id ON reservation_post (reservation_id);

-- (Optional) Undgå dublet-linjer for samme reservation+forestilling
-- (Hvis du ønsker max 1 post pr forestilling pr reservation)
-- ALTER TABLE reservation_post
--   ADD CONSTRAINT uq_rp_reservation_forestilling UNIQUE (reservation_id, forestilling_id);
