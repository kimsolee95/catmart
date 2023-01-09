package com.shoptest.catmart.common.model;

import lombok.Data;
/*
 * RestController 공통 응답 format
 * */
@Data
public class ResponseResult {

  ResponseResultHeader responseResultHeader;
  Object body;

  public ResponseResult(boolean result, String message) {

    responseResultHeader = new ResponseResultHeader(result, message);
  }

  public ResponseResult(boolean result) {

    responseResultHeader = new ResponseResultHeader(result);
  }


}
