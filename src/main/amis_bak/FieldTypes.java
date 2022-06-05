package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * FieldTypes
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */

@Schema(description = "FieldTypes")
public enum FieldTypes {

		text,
		number,
		boolean_,
		date,
		time,
		datetime,
		select,
}
