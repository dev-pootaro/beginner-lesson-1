-- create tables
CREATE TABLE IF NOT EXISTS file_mst (
  id SERIAL NOT NULL,
  file_name varchar(150) NOT NULL,
  regist_datetime timestamp NOT NULL,
  last_update_datetime timestamp NOT NULL,
  file_data bytea NOT NULL,
  content_type varchar(150) NOT NULL,
  PRIMARY KEY (id)
);
COMMENT ON TABLE file_mst IS 'ファイルマスタ';
COMMENT ON COLUMN file_mst.id IS 'ID';
COMMENT ON COLUMN file_mst.file_name IS 'ファイル名';
COMMENT ON COLUMN file_mst.regist_datetime IS '登録日時';
COMMENT ON COLUMN file_mst.last_update_datetime IS '最終更新日時';
COMMENT ON COLUMN file_mst.file_data IS 'ファイルデータ';
COMMENT ON COLUMN file_mst.content_type IS 'コンテンツタイプ';
