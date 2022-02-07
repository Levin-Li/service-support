package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * OperatorType
 *
 * 
 *
 * @author auto gen by service-support at 2022-2-7 23:06:29
 */

@Schema(description = "OperatorType")
public enum OperatorType {

		equal,
		not_equal,
		is_empty,
		is_not_empty,
		like,
		not_like,
		starts_with,
		ends_with,
		less,
		less_or_equal,
		greater,
		greater_or_equal,
		between,
		not_between,
		select_equals,
		select_not_equals,
		select_any_in,
		select_not_any_in,
}
