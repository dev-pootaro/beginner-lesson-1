package com.example.lesson1.fileapi.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.example.lesson1.fileapi.entity.FileMst;
import com.example.lesson1.fileapi.form.FileUploadForm;

/**
 * file api service.
 *
 * @author pootaro.suzuki
 */
public interface FileApiService {
  /**
   * get file.
   * @param {int} id
   * @return {FileMst}
   */
  public Optional<FileMst> getFile(int id);

  /**
   * get files
   * @return {List<FileMst>}
   */
  public List<FileMst> getFiles();

  /**
   * upload.
   *
   * @param {FileUploadForm} form
   * @throws IOException
   */
  public void upload(FileUploadForm form) throws IOException;

  /**
   * delete.
   *
   * @param {int} id
   */
  public void delete(int id);
}
