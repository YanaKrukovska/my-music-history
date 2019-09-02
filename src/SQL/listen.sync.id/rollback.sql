


ALTER TABLE listen ALTER COLUMN sync_id DROP NOT NULL;
ALTER TABLE listen DROP CONSTRAINT sync_id_constraint;

ALTER TABLE listen drop COLUMN sync_id;
