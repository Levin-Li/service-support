package com.levin.commons.rbac;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 领域对象
 *
 * @author echo
 */

@Schema(title = "领域信息", description = "不是域名，通常是业务领域，或是业务应用")
public interface RbacDomainInfo extends RbacCoreObject {

}
