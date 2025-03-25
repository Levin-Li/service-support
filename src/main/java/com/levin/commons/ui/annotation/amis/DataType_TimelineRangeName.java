package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * DataType_TimelineRangeName
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2025年3月24日 下午10:00:51
 */
@Schema(title = "DataType_TimelineRangeName")
public enum DataType_TimelineRangeName {

		contain,
		cover,
		entry,
		entry_crossing,
		exit,
		exit_crossing,
}
