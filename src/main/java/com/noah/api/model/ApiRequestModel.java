package com.noah.api.model;

import java.io.Serializable; 

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApiRequestModel<T> implements Serializable {

    private static final long serialVersionUID = 421882996212265081L;

    @JsonProperty(value = "Header")
    private T header;

    @JsonProperty(value = "Body")
    private T body;
}
