package com.levin.commons.dao.domain;


import com.levin.commons.service.domain.Identifiable;

import java.io.Serializable;

/**
 * 实体对象，通常是指ORM中的实体对象
 *
 * @author llw
 */
public interface EntityObject
        extends
        Identifiable,
        Serializable,
        Cloneable {

}
