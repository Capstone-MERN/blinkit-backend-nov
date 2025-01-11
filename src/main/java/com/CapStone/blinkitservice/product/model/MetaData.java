package com.CapStone.blinkitservice.product.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetaData {
    List<Map<String, String>> details;
    List<String> images;
}
