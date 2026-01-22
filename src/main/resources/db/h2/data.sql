
-- Film
INSERT INTO film (titel, varighed_minutter) VALUES
  ('One Upon Time in the West', 165),
  ('Oppenheimer', 180);

-- Sale
INSERT INTO sal (navn, kapacitet) VALUES
  ('Sal 1', 50),
  ('Sal 2', 50);

-- Forestillinger
INSERT INTO forestilling (film_id, sal_id, start_tid) VALUES
  (1, 1, TIMESTAMP '2026-01-20 19:30:00'),
  (2, 2, TIMESTAMP '2026-01-21 20:00:00');


-- Reservationposts (reserverede pladser)
INSERT INTO reservation_post (reservation_id, forestilling_id, antal_pladser) VALUES
  (1, 1, 2),
  (1, 1, 3);
