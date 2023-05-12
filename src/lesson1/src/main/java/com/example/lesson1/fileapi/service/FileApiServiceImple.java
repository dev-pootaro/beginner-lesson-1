package com.example.lesson1.fileapi.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.lesson1.fileapi.entity.FileMst;
import com.example.lesson1.fileapi.form.FileUploadForm;
import com.example.lesson1.fileapi.repository.FileMstRepository;
import lombok.RequiredArgsConstructor;

/**
 * file api service imple.
 *
 * @author pootaro.suzuki
 */
@Service
@RequiredArgsConstructor
public class FileApiServiceImple implements FileApiService {

  /** file_mst repository. */
  private final FileMstRepository repository;

  @Override
  public void upload(final FileUploadForm form) throws IOException {
    final String fileName = form.getFile().getOriginalFilename();
    final List<FileMst> opFileMst = this.repository.findByFileName(fileName);
    final FileMst fileMst = (opFileMst.size() > 0) ? opFileMst.get(0) : new FileMst();
    fileMst.setFileName(fileName);
    fileMst.setRegistDatetime(new Timestamp(System.currentTimeMillis()));
    fileMst.setLastUpdateDatetime(new Timestamp(System.currentTimeMillis()));
    fileMst.setFileData(form.getFile().getBytes());
    fileMst.setContentType(form.getFile().getContentType());
    this.repository.save(fileMst);
  }

  @Override
  public Optional<FileMst> getFile(final int id) {
    return this.repository.findById(id);
  }

  @Override
  public List<FileMst> getFiles() {
    return this.repository.findAll();
  }

  @Override
  public void delete(final int id) {
    this.repository.deleteById(id);
  }
}
