CREATE OR REPLACE FUNCTION save_query_to_logs() RETURNS TRIGGER AS
$BODY$
DECLARE 
BEGIN
  INSERT INTO logging.history (id_time, query, flag) VALUES (now()::TIMESTAMP, current_query()::VARCHAR(2000), false::BOOLEAN);
  RETURN NULL ;
END;
$BODY$
LANGUAGE plpgsql VOLATILE ;

CREATE TRIGGER trg_save_query_to_logs
  AFTER INSERT OR UPDATE OR DELETE
  ON logging.main
  FOR EACH ROW
EXECUTE PROCEDURE save_query_to_logs();