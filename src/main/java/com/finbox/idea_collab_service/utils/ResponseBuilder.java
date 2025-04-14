package com.finbox.idea_collab_service.utils;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    private ResponseBuilder() {}

    public static <T> ResponseEntity<IdeaColabSvcResponse<T>> build(T body, HttpStatus status) {
        IdeaColabSvcResponse<T> response = new IdeaColabSvcResponse<T>().setData(body).setSuccess(true);
        return ResponseEntity.status(status).body(response);
    }
    public static ResponseEntity<IdeaColabSvcResponse<Void>> buildError(
            HttpStatus status, String code, String message) {
        IdeaColabSvcResponse<Void> response = new IdeaColabSvcResponse<Void>()
                .setSuccess(false)
                .setData(null);

        return ResponseEntity.status(status).body(response);
    }

//    public static <T> ResponseEntity<RawResponseDto<Void>> buildErrorWithInfo(
//            HttpStatus status, RawErrorCode code, String message, T errorInfo) {
//        RawResponseDto<Void> response = new RawResponseDto<Void>()
//                .setError(RawErrorResponseDto.builder()
//                        .code(code)
//                        .message(message)
//                        .errorInfo(errorInfo)
//                        .build());
//        return ResponseEntity.status(status).body(response);
    }


