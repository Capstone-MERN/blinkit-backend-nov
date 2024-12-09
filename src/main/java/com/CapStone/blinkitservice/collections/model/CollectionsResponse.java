package com.CapStone.blinkitservice.collections.model;

import com.CapStone.blinkitservice.common.response.GenericResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionsResponse implements GenericResponse {

    List<CollectionResponse> collections;

}
