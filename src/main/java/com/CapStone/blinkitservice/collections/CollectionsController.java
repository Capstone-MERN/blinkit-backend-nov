package com.CapStone.blinkitservice.collections;

import com.CapStone.blinkitservice.collections.model.CollectionsResponse;
import com.CapStone.blinkitservice.common.error.GenericErrorResponse;
import com.CapStone.blinkitservice.common.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    CollectionsService collectionsService;

    @GetMapping("/getActiveCollections")
    public ResponseEntity<GenericResponse> getActiveCollections(@AuthenticationPrincipal String userEmail){
        try {
            CollectionsResponse response = collectionsService.getActiveCollections(userEmail);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
