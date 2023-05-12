package com.example.lesson1.fileapi.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * file_list table entity.
 *
 * @author pootaro.suzuki
 */
@Entity
@Data
@Table(name = "file_mst")
public class FileMst {
  /**
   * ID.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  /**
   * file_name.
   */
  @Column(name = "file_name")
  private String fileName;
  /**
   * regist_datetime.
   */
  @Column(name = "regist_datetime")
  private Timestamp registDatetime;
  /**
   * last_update_datetime.
   */
  @Column(name = "last_update_datetime")
  private Timestamp lastUpdateDatetime;
  /**
   * file_data.
   */
  @Column(name = "file_data")
  private byte[] fileData;
  /**
   * content_type.
   */
  @Column(name = "content_type")
  private String contentType;
}
