package pl.coderslab.charity.controller.api;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ResponseErrors {

    private Map<String, List<String>> errors = new HashMap<>();

    public static ResponseErrors of(BindingResult result) {
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            if (errors.containsKey(fieldError.getField())) {
                errors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            } else {
                ArrayList<String> messages = new ArrayList<>();
                messages.add(fieldError.getDefaultMessage());
                errors.put(fieldError.getField(), messages);
            }
        }
        ResponseErrors responseErrors = new ResponseErrors();
        responseErrors.errors = errors;
        return responseErrors;
    }
}
