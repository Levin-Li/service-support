package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * ${name}
 *
 * ${ui.description!''}
 *
 * @author auto gen by service-support at ${.now}
 */

@Schema(description = "${ui.description!name}")
public enum ${name} {
${ui.getEnums()}
}
