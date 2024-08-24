package com.ruwaytech.shared.dto;

import com.ruwaytech.shared.DateUtil;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String dateTime;
    private Boolean success;
    private String message;
    private Object data;

    public static ResponseDto ok(Object data, String message) {
        String dateTime = DateUtil.convertLocalDateTimeToString(LocalDateTime.now());

        return ResponseDto.builder()
                .dateTime(dateTime)
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static ResponseDto error(String message) {
        String dateTime = DateUtil.convertLocalDateTimeToString(LocalDateTime.now());

        return ResponseDto.builder()
                .dateTime(dateTime)
                .success(false)
                .message(message)
                .data(null)
                .build();
    }

}
