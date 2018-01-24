package com.sebastian.controllers;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sebastian.clients.OrganizationFeignClient;
import com.sebastian.clients.OrganizationRestTemplateClient;
import com.sebastian.dominio.License;
import com.sebastian.exception.LicenseNotFoundException;
import com.sebastian.services.LicenseService;
import com.sebastian.util.UserContextHolder;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    private static final Logger LOGGER = Logger.getLogger(LicenseServiceController.class);
    @Autowired
    private LicenseService ls;
    @Autowired
    private OrganizationFeignClient ofc;
    @Autowired
    private OrganizationRestTemplateClient ortc;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallBack",
            threadPoolKey = "licences", // nuevo thread pool
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "2")
                , // tamaño del thread pool
                @HystrixProperty(name = "maxQueueSize", value = "10") // maximo encolados
            },
            commandProperties = {
                    // llamadas consecutivas en 10 segundos
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    // % de llamadas que tienen que fallar luego del anterior
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    // duerme antes de volver a intentarlo luego de cumplir los anteriores y cortar la conexion
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    // duracion del monitoreo para que se cumplan las condiciones descritas
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    // numero de coleccion de estadisticas
                @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
            }
    )
    public License getLicences(@PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) throws LicenseNotFoundException {
        LOGGER.info("feign template");        
        LOGGER.info("user context holder " + UserContextHolder.getContext().getCorrelationId());
        LOGGER.info("org: " + ofc.getOrganization(organizationId));
        final License l = ls.getLicense(organizationId, licenseId);
        if (l != null) {
            return ls.getLicense(organizationId, licenseId);
        } else {
            throw new LicenseNotFoundException("error al obtener licencia", licenseId);
        }
    }
//    @HystrixCommand(fallbackMethod = "fallBack",
//            threadPoolKey = "licences", // nuevo thread pool
//            threadPoolProperties = {
//                @HystrixProperty(name = "coreSize", value = "2")
//                , // tamaño del thread pool
//                @HystrixProperty(name = "maxQueueSize", value = "10") // maximo encolados
//            },
//            commandProperties = {
//                    // llamadas consecutivas en 10 segundos
//                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//                    // % de llamadas que tienen que fallar luego del anterior
//                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
//                    // duerme antes de volver a intentarlo luego de cumplir los anteriores y cortar la conexion
//                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
//                    // duracion del monitoreo para que se cumplan las condiciones descritas
//                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
//                    // numero de coleccion de estadisticas
//                @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
//            }
//    )    
    @RequestMapping(value = "/{licenseId}/rt", method = RequestMethod.GET)
    public License getLicencesRestTemplate(@PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) throws LicenseNotFoundException {
        LOGGER.info("rest template");
        LOGGER.info("user context holder " + UserContextHolder.getContext().getCorrelationId());
        LOGGER.info("org: " + ortc.getOrganization(organizationId));
        final License l = ls.getLicense(organizationId, licenseId);
        if (l != null) {
            return ls.getLicense(organizationId, licenseId);
        } else {
            throw new LicenseNotFoundException("error al obtener licencia", licenseId);
        }
    }
    
    @HystrixCommand(fallbackMethod = "fallBack")
    @RequestMapping(value = "/{licenseId}/bd", method = RequestMethod.GET)
    public License getLicencesBD(@PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) throws LicenseNotFoundException {
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final License l = ls.getLicense(organizationId, licenseId);
        if (l != null) {
            return ls.getLicense(organizationId, licenseId);
        } else {
            throw new LicenseNotFoundException("error al obtener licencia", licenseId);
        }
    }

    public License fallBack(String organizationId, String licenseId) throws LicenseNotFoundException {
        final License l = new License();
        l.setId("0");
        l.setLicenceType("0");
        l.setOrganizationId("0");
        l.setProductName("sin servicio de licencias");
        return l;
    }
    
    @PostMapping(consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void persistir(@RequestBody final License lcs) {
        LOGGER.info("nueva licensia:" + lcs);
        ls.saveLicense(lcs);        
    }
}
