package com.unit.whisper.error;

import static com.unit.whisper.error.ErrorCode.INPUT_INVALID_TYPE_ERROR;
import static com.unit.whisper.error.ErrorCode.INPUT_INVALID_VALUE_ERROR;
import static com.unit.whisper.error.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.unit.whisper.error.ErrorCode.REQUEST_PARAMETER_NOT_FOUND_ERROR;
import static com.unit.whisper.error.ErrorCode.REQUEST_PARAMETER_TYPE_NOT_MATCH_ERROR;

import com.unit.whisper.exception.BusinessException;
import javax.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleGlobalException(final Exception e) {
        log.error(e.getMessage(), e);

        final ErrorResponse errorResponse = ErrorResponse.fromErrorCode(INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error(e.getMessage(), e);

        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse errorResponse = ErrorResponse.fromErrorCode(errorCode);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleRequestArgumentNotValidException(
            MethodArgumentNotValidException e) {
        log.warn(e.getMessage(), e);

        final ErrorResponse response =
                ErrorResponse.ofBindingResult(INPUT_INVALID_VALUE_ERROR, e.getBindingResult());
        return ResponseEntity.status(INPUT_INVALID_VALUE_ERROR.getStatus()).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleRequestTypeNotValidException(
            HttpMessageNotReadableException e) {
        log.warn(e.getMessage(), e);

        final ErrorResponse response = ErrorResponse.fromErrorCode(INPUT_INVALID_TYPE_ERROR);
        return ResponseEntity.status(INPUT_INVALID_VALUE_ERROR.getStatus()).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        log.warn(e.getMessage(), e);

        final ErrorResponse errorResponse =
                ErrorResponse.fromParameter(
                        REQUEST_PARAMETER_NOT_FOUND_ERROR, e.getParameterName());

        return ResponseEntity.status(REQUEST_PARAMETER_NOT_FOUND_ERROR.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.warn(e.getMessage(), e);

        final ErrorResponse errorResponse =
                ErrorResponse.fromType(
                        REQUEST_PARAMETER_TYPE_NOT_MATCH_ERROR,
                        e.getParameter().getParameterName(),
                        String.valueOf(e.getValue()));

        return ResponseEntity.status(REQUEST_PARAMETER_NOT_FOUND_ERROR.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException e) {
        log.warn(e.getMessage(), e);

        ErrorCode errorCode = INPUT_INVALID_VALUE_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.fromErrorCode(errorCode);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(TransactionalException.class)
    protected ResponseEntity<ErrorResponse> handleTransactionException(TransactionalException e) {
        log.warn(e.getMessage(), e);

        ErrorCode errorCode = INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.fromErrorCode(errorCode);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
