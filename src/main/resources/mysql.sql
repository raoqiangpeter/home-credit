CREATE TABLE IF NOT EXISTS hc_stream(
  hc_id BIGINT PRIMARY KEY,
  hc_result CHAR(1) NOT NULL,
  hc_status CHAR(3) NOT NULL,
  hc_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- SK_ID_CURR

CREATE TABLE IF NOT EXISTS t_result(
  hc_id char(20) PRIMARY KEY,
  hc_sk_id char(10) not null ,
  hc_score decimal(20,10) not null,
  hc_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

