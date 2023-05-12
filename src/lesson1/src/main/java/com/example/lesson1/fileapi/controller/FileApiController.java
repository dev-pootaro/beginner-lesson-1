package com.example.lesson1.fileapi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.lesson1.fileapi.entity.FileMst;
import com.example.lesson1.fileapi.form.FileUploadForm;
import com.example.lesson1.fileapi.service.FileApiService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * file upload controller.
 *
 * @author pootaro.suzuki
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class FileApiController {

  /** file api service. */
  private final FileApiService service;

  /**
   * get file list.
   *
   * @return {List<FileMst>}
   */
  @GetMapping("/get-files")
  public List<FileMst> getFiles() throws Exception {
    throw new Exception("");
    // return this.service.getFiles();
  }

  /**
   * get file.
   *
   * @param {int} id
   * @param {HttpServletResponse} response
   */
  @GetMapping("/get-file")
  public void getFile(
    @RequestParam("id") final int id,
    final HttpServletResponse response
  ) throws IOException {
    final Optional<FileMst> opt = this.service.getFile(id);
    if (opt.isEmpty()) {
      return;
    }
    final FileMst fileMst = opt.get();
    response.setHeader(
      "Content-Disposition",
      "attachment; filename=" + URLEncoder.encode(fileMst.getFileName(), "UTF-8")
    );
    response.setContentType(fileMst.getContentType());
    OutputStream outputStream = response.getOutputStream();
    outputStream.write(fileMst.getFileData());
    outputStream.flush();
    outputStream.close();
  }

  /**
   * file upload.
   *
   * @param {FileUploadForm} form
   * @param {HttpServletResponse} response
   */
  @RequestMapping("/upload")
  public void upload(
    @ModelAttribute final FileUploadForm form,
    final HttpServletResponse response
  ) throws Exception {
    this.service.upload(form);
    response.getWriter().write("upload success");
  }

  /**
   * delete file.
   *
   * @param {int} id
   * @param {HttpServletResponse} response
   * @throws Exception
   */
  @DeleteMapping("/delete")
  public void delete(
    @RequestParam("id") final int id,
    final HttpServletResponse response
  ) throws Exception {
    this.service.delete(id);
    response.getWriter().write("delete success");
  }
}


