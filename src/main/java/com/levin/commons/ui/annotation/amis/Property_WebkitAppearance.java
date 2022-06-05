package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;

/**
 * Property_WebkitAppearance
 *
 * 
 *
 * @author auto gen by service-support at 2022-6-5 16:24:12
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(description = "Property_WebkitAppearance")
public @interface Property_WebkitAppearance {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-apple-pay-button"},{"type":"string","const":"button"},{"type":"string","const":"button-bevel"},{"type":"string","const":"caret"},{"type":"string","const":"checkbox"},{"type":"string","const":"default-button"},{"type":"string","const":"inner-spin-button"},{"type":"string","const":"listbox"},{"type":"string","const":"listitem"},{"type":"string","const":"media-controls-background"},{"type":"string","const":"media-controls-fullscreen-background"},{"type":"string","const":"media-current-time-display"},{"type":"string","const":"media-enter-fullscreen-button"},{"type":"string","const":"media-exit-fullscreen-button"},{"type":"string","const":"media-fullscreen-button"},{"type":"string","const":"media-mute-button"},{"type":"string","const":"media-overlay-play-button"},{"type":"string","const":"media-play-button"},{"type":"string","const":"media-seek-back-button"},{"type":"string","const":"media-seek-forward-button"},{"type":"string","const":"media-slider"},{"type":"string","const":"media-sliderthumb"},{"type":"string","const":"media-time-remaining-display"},{"type":"string","const":"media-toggle-closed-captions-button"},{"type":"string","const":"media-volume-slider"},{"type":"string","const":"media-volume-slider-container"},{"type":"string","const":"media-volume-sliderthumb"},{"type":"string","const":"menulist"},{"type":"string","const":"menulist-button"},{"type":"string","const":"menulist-text"},{"type":"string","const":"menulist-textfield"},{"type":"string","const":"meter"},{"type":"string","const":"none"},{"type":"string","const":"progress-bar"},{"type":"string","const":"progress-bar-value"},{"type":"string","const":"push-button"},{"type":"string","const":"radio"},{"type":"string","const":"searchfield"},{"type":"string","const":"searchfield-cancel-button"},{"type":"string","const":"searchfield-decoration"},{"type":"string","const":"searchfield-results-button"},{"type":"string","const":"searchfield-results-decoration"},{"type":"string","const":"slider-horizontal"},{"type":"string","const":"slider-vertical"},{"type":"string","const":"sliderthumb-horizontal"},{"type":"string","const":"sliderthumb-vertical"},{"type":"string","const":"square-button"},{"type":"string","const":"textarea"},{"type":"string","const":"textfield"}]
   */

    String[] consts = { "-apple-pay-button", "button", "button-bevel", "caret", "checkbox", "default-button", "inner-spin-button", "listbox", "listitem", "media-controls-background", "media-controls-fullscreen-background", "media-current-time-display", "media-enter-fullscreen-button", "media-exit-fullscreen-button", "media-fullscreen-button", "media-mute-button", "media-overlay-play-button", "media-play-button", "media-seek-back-button", "media-seek-forward-button", "media-slider", "media-sliderthumb", "media-time-remaining-display", "media-toggle-closed-captions-button", "media-volume-slider", "media-volume-slider-container", "media-volume-sliderthumb", "menulist", "menulist-button", "menulist-text", "menulist-textfield", "meter", "none", "progress-bar", "progress-bar-value", "push-button", "radio", "searchfield", "searchfield-cancel-button", "searchfield-decoration", "searchfield-results-button", "searchfield-results-decoration", "slider-horizontal", "slider-vertical", "sliderthumb-horizontal", "sliderthumb-vertical", "square-button", "textarea", "textfield" };

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

}
