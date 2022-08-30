package com.application.exception;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ModelError> entityNotFound(EntityNotFoundException e, HttpServletRequest req){
		ModelError modelError= new ModelError();
		modelError.setTimestamp(Instant.now());
		modelError.setError("Erro ao buscar os dados informados ");
		modelError.setMessage(e.getMessage());
		modelError.setPath(req.getRequestURI());
		modelError.setStatus(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelError);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ModelError> entityNotValid(MethodArgumentNotValidException e, HttpServletRequest req){
		ModelError modelError= new ModelError();
		modelError.setTimestamp(Instant.now());
		modelError.setError("Requisição incorreta");
		modelError.setMessage(e.getMessage());
		modelError.setPath(req.getRequestURI());
		modelError.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelError);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ModelError> entityNotValid(EmptyResultDataAccessException e, HttpServletRequest req){
		ModelError modelError= new ModelError();
		modelError.setTimestamp(Instant.now());
		modelError.setError("Erro de Servidor: os dados requeridos não existem ");
		modelError.setMessage(e.getMessage());
		modelError.setPath(req.getRequestURI());
		modelError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modelError);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ModelError> entityNotValid(ConstraintViolationException e, HttpServletRequest req){
		ModelError modelError= new ModelError();
		modelError.setTimestamp(Instant.now());
		modelError.setError("Erro de Servidor: os dados não são válidos");
		modelError.setMessage(e.getMessage());
		modelError.setPath(req.getRequestURI());
		modelError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modelError);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ModelError> entityNotValid(DataIntegrityViolationException e, HttpServletRequest req){
		ModelError modelError= new ModelError();
		modelError.setTimestamp(Instant.now());
		modelError.setError("Erro de Servidor: tentativa de vinculação a dados inexistentes");
		modelError.setMessage(e.getMessage());
		modelError.setPath(req.getRequestURI());
		modelError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modelError);
	}
}

