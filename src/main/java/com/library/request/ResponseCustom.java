package com.library.request;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseCustom implements Serializable {
  static final long serialVersionUID = 6600726619212566706L;
  public static ResponseEntity<?> generateResponse(String message, HttpStatus status, Object responseObj) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", message);
    map.put("status", status.value());
    map.put("data", responseObj);

    return new ResponseEntity(map,status);
  }
}
