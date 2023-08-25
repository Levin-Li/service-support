package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
/**
 * StepStatus
 *
 *
 *
 * @author auto gen by service-support at 2022-2-10 12:04:42
 */

@Schema(title = "StepStatus")
public enum StepStatus {

		wait,
		process,
		finish,
		error,
}
