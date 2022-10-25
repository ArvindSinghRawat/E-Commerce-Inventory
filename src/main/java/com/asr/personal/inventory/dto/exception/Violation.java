package com.asr.personal.inventory.dto.exception;

import lombok.Value;

@Value
public class Violation {

  String fieldName;

  String message;
}