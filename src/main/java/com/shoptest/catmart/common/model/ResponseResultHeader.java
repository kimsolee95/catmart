package com.shoptest.catmart.common.model;

import lombok.Data;

/*
* RestController 공통 헤더
* */
@Data
public class ResponseResultHeader {

  boolean result;
  String message;

  public ResponseResultHeader(boolean result, String message) {
    this.result = result;
    this.message = message;
  }

  public ResponseResultHeader(boolean result) {
    this.result = result;
  }

}
