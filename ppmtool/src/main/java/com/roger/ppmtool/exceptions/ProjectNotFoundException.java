package com.roger.ppmtool.exceptions;

import com.roger.ppmtool.domain.ProjectNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(String response) {
        super(response);
    }
}
