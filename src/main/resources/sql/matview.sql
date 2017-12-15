CREATE EXTENSION dblink;

-- dblink connection
SELECT dblink_connect('dblink_connection', 'dbname=tdb user=postgres password=postgres host=127.0.0.1 port=5432');

-- create materialized view
CREATE MATERIALIZED VIEW logging_main_mat_view AS
  SELECT * FROM dblink('dbname=tdb user=postgres password=postgres host=127.0.0.1 port=5432', 'SELECT * FROM logging.main')
    AS copy (id INT, value VARCHAR);

-- refreshing
REFRESH MATERIALIZED VIEW logging_main_mat_view;

-- insert smth into logging.main table
-- select from mat view will return old data
-- refresh
-- select from mat view will return refreshed data

-- select from refreshed mat view
SELECT * FROM logging_main_mat_view;

-- disconnect
SELECT dblink_disconnect('dblink_connection');
