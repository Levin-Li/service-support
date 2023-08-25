package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * CRUDBultinToolbarType
 *
 * 
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Schema(title = "CRUDBultinToolbarType")
public enum CRUDBultinToolbarType {

		columns_toggler,
		drag_toggler,
		pagination,
		bulkActions,
		bulk_actions,
		statistics,
		switch_per_page,
		load_more,
		filter_toggler,
		export_csv,
		export_excel,
}
