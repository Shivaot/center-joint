package com.hitachi.epdi2.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ToString
@Getter
@Setter
public class ResponseDto<T> {
    private String message;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
