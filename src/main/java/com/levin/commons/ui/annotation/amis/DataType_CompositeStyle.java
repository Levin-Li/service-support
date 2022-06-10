package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * DataType_CompositeStyle
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:03
 */
@Schema(description = "DataType_CompositeStyle")
public enum DataType_CompositeStyle {

		clear,
		copy,
		destination_atop,
		destination_in,
		destination_out,
		destination_over,
		source_atop,
		source_in,
		source_out,
		source_over,
		xor,
}
