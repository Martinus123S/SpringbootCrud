package belajar.blibli.demo.request;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

public class ResponseCustom extends ResponseEntity implements Serializable {

  public ResponseCustom(HttpStatus status) {
    super(status);
  }

  public ResponseCustom(Object body, HttpStatus status) {
    super(body, status);
  }

  public ResponseCustom(MultiValueMap headers, HttpStatus status) {
    super(headers, status);
  }

  public ResponseCustom(Object body, MultiValueMap headers, HttpStatus status) {
    super(body, headers, status);
  }

  public ResponseCustom(Object body, MultiValueMap headers, int rawStatus) {
    super(body, headers, rawStatus);
  }
}
