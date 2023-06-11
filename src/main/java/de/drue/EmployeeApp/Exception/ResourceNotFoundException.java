package de.drue.EmployeeApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message){
        super(message);
    }
}
