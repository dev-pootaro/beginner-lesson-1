package com.example.lesson1.fileapi.form;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import lombok.NonNull;

/**
 * file upload form.
 * 
 * @author pootaro.suzuki
 */
@Data
public class FileUploadForm {
  /**
   * file.
   */
  @NonNull
  private MultipartFile file;
}
