package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * FieldTypes
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Schema(title = "FieldTypes")
public enum FieldTypes {

		text,
		number,
		__boolean,
		date,
		time,
		datetime,
		select,
		custom,
}
