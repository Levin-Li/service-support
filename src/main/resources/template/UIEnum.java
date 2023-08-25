package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * ${name}
 *
 * ${ui.description!''}
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at ${.now}
 */
@Schema(title = "${ui.description!name}")
public enum ${name} {
${ui.getEnums()}
}
