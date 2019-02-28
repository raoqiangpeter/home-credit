CREATE TABLE IF NOT EXISTS hc_stream(
  hc_id BIGINT PRIMARY KEY,
  hc_result CHAR(1) NOT NULL,
  hc_status CHAR(3) NOT NULL,
  hc_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)