package com.challenge.Employees.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
    private boolean success;
    private String message;
    private Object data;

    public static EmployeeResponseDTO success(String message, Object data) {
        return new EmployeeResponseDTO(true, message, data);
    }

    public static EmployeeResponseDTO error(String message) {
        return new EmployeeResponseDTO(false, message, null);
    }
}