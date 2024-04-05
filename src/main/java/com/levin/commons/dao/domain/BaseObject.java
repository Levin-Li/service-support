package com.levin.commons.dao.domain;


import com.levin.commons.service.domain.Identifiable;

/**
 * 基本对象
 *
 * @author llw
 */
public interface BaseObject
        extends
        Identifiable,
        EnableObject,
        EditableObject,
        SortableObject {

}
