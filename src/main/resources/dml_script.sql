INSERT INTO restdata VALUES(123, 'Srimanta', 123.45, CURRENT_TIMESTAMP);

UPDATE restdata SET username = 'Mr.Srimanta' WHERE username = 'Srimanta';

COMMIT;

SELECT * FROM restdata;