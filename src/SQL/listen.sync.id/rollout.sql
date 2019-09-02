
ALTER TABLE listen ADD COLUMN sync_id BIGINT;
UPDATE listen SET sync_id = id;
ALTER TABLE listen ALTER COLUMN sync_id SET NOT NULL;
ALTER TABLE listen ADD CONSTRAINT sync_id_constraint UNIQUE (user_id, sync_id);


