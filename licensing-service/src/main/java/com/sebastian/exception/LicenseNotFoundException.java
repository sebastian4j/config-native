package com.sebastian.exception;

import lombok.Data;

@Data
public class LicenseNotFoundException extends Exception {
    private final String errorLicencia;
    
    public LicenseNotFoundException(final String txt, final String id) {
        super(txt);
        errorLicencia = id;
    }
    public LicenseNotFoundException(final Throwable err, final String id) {
        super(err);
        errorLicencia = id;
    }
    public LicenseNotFoundException(final String txt, final Throwable err, final String id) {
        super(txt, err);   
        errorLicencia = id;
    }    
}
