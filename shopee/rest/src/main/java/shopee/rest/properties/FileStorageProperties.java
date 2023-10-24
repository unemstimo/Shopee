package shopee.rest;

public class FileStorageProperties {


/**
 * Configures properties when preparing upload.
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
  private String uploadDir;

  public String getUploadDir() {
    return uploadDir;
  }

  public void setUploadDir(String uploadDir) {
    this.uploadDir = System.getProperty("user.home") + uploadDir;
  }

}

}
