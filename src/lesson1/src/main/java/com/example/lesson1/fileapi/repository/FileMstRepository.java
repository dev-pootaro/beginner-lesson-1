package com.example.lesson1.fileapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lesson1.fileapi.entity.FileMst;

/**
 * file_list table repository.
 *
 * @author pootaro.suzuki
 */
@Repository
public interface FileMstRepository extends JpaRepository<FileMst, Integer> {
  /**
   * find by file_name
   *
   * @param {String} fileName
   * @return {List<FileMst>}
   */
  List<FileMst> findByFileName(String fileName);
}
