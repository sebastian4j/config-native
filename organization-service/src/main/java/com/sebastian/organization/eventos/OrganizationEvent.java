package com.sebastian.organization.eventos;

import lombok.Data;

/**
 *
 * @author Sebastian Avila A.
 */
@Data
public class OrganizationEvent {
    private String type;
    private String action;
    private String id;
    private String correlation;
}
