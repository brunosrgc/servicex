package br.com.brunosrgc.servicex.exceptios;

public class ExceptionDataIntegrityViolation extends RuntimeException {


    public ExceptionDataIntegrityViolation(String message) {
        super(message);
    }
}

