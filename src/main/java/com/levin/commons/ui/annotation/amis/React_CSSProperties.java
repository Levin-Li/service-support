package com.levin.commons.ui.annotation.amis;

import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.*;
import com.levin.commons.ui.annotation.*;

/**
 * React_CSSProperties
 *
 *
 * 根据 "amis": "1.9.1-beta.11", schema.json 自动生成
 * @author auto gen by service-support at 2022-6-6 23:52:02
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Schema(title = "React_CSSProperties")
public @interface React_CSSProperties {
///////////////////////////////////////////

////////////////////////////////////////////

  /**
   * Any Of
   *
   *
   *
   */

//////////////////////////////////////////////
   /**
    *
    */
   String value() default "	";

    /**
     * alignmentBaseline
     *
     * 参考定义: "#/definitions/Property.AlignmentBaseline"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"after-edge"},{"type":"string","const":"alphabetic"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"before-edge"},{"type":"string","const":"central"},{"type":"string","const":"hanging"},{"type":"string","const":"ideographic"},{"type":"string","const":"mathematical"},{"type":"string","const":"middle"},{"type":"string","const":"text-after-edge"},{"type":"string","const":"text-before-edge"}]
     *
     * @see Property_AlignmentBaseline
     */
    @Const({"after-edge", "alphabetic", "auto", "baseline", "before-edge", "central", "hanging", "ideographic", "mathematical", "middle", "text-after-edge", "text-before-edge"})
    @Schema(title = "alignmentBaseline")
    String alignmentBaseline() default "	";

    /**
     * baselineShift
     *
     * 参考定义: "#/definitions/Property.BaselineShift%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"baseline"},{"type":"string","const":"sub"},{"type":"string","const":"super"}]
     *
     * @see Property_BaselineShift
     */
    @Const({"baseline", "sub", "super"})
    @Schema(title = "baselineShift")
    String baselineShift() default "	";

    /**
     * The **`clip`** CSS property defines a visible portion of an element. The `clip` property applies only to absolutely positioned elements — that is, elements with `position:absolute` or `position:fixed`.\n\n**Syntax**: `<shape> | auto`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.Clip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_Clip
     */
    @Const({"auto"})
    @Schema(title = "The **`clip`** CSS property defines a visible portion of an element. The `clip` property applies only to absolutely positioned elements — that is, elements with `position:absolute` or `position:fixed`.\n\n**Syntax**: `<shape> | auto`\n\n**Initial value**: `auto`")
    String clip() default "	";

    /**
     * The `**clip-path**` CSS property creates a clipping region that sets what part of an element should be shown. Parts that are inside the region are shown, while those outside are hidden.\n\n**Syntax**: `<clip-source> | [ <basic-shape> || <geometry-box> ] | none`\n\n**Initial value**: `none`\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **55**  | **3.5** |  **9.1**  | **12** | **10** | | 23 _-x-_ |         | 6.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ClipPath"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ClipPath
     */
    @Const({"none"})
    @Schema(title = "The `**clip-path**` CSS property creates a clipping region that sets what part of an element should be shown. Parts that are inside the region are shown, while those outside are hidden.\n\n**Syntax**: `<clip-source> | [ <basic-shape> || <geometry-box> ] | none`\n\n**Initial value**: `none`\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **55**  | **3.5** |  **9.1**  | **12** | **10** | | 23 _-x-_ |         | 6.1 _-x-_ |        |        |")
    String clipPath() default "	";

    /**
     * clipRule
     *
     * 参考定义: "#/definitions/Property.ClipRule"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"evenodd"},{"type":"string","const":"nonzero"}]
     *
     * @see Property_ClipRule
     */
    @Const({"evenodd", "nonzero"})
    @Schema(title = "clipRule")
    String clipRule() default "	";

    /**
     * The **`color`** CSS property sets the foreground color value of an element's text and text decorations, and sets the `<currentcolor>` value. `currentcolor` may be used as an indirect value on _other_ properties and is the default for other color properties, such as `border-color`.\n\n**Syntax**: `<color>`\n\n**Initial value**: Varies from one browser to another\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.Color"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_Color
     */

    @Schema(title = "The **`color`** CSS property sets the foreground color value of an element's text and text decorations, and sets the `<currentcolor>` value. `currentcolor` may be used as an indirect value on _other_ properties and is the default for other color properties, such as `border-color`.\n\n**Syntax**: `<color>`\n\n**Initial value**: Varies from one browser to another\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String color() default "	";

    /**
     * colorInterpolation
     *
     * 参考定义: "#/definitions/Property.ColorInterpolation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"linearRGB"},{"type":"string","const":"sRGB"}]
     *
     * @see Property_ColorInterpolation
     */
    @Const({"auto", "linearRGB", "sRGB"})
    @Schema(title = "colorInterpolation")
    String colorInterpolation() default "	";

    /**
     * colorRendering
     *
     * 参考定义: "#/definitions/Property.ColorRendering"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"optimizeQuality"},{"type":"string","const":"optimizeSpeed"}]
     *
     * @see Property_ColorRendering
     */
    @Const({"auto", "optimizeQuality", "optimizeSpeed"})
    @Schema(title = "colorRendering")
    String colorRendering() default "	";

    /**
     * The **`cursor`** CSS property sets the type of mouse cursor, if any, to show when the mouse pointer is over an element.\n\n**Syntax**: `[ [ <url> [ <x> <y> ]? , ]* [ auto | default | none | context-menu | help | pointer | progress | wait | cell | crosshair | text | vertical-text | alias | copy | move | no-drop | not-allowed | e-resize | n-resize | ne-resize | nw-resize | s-resize | se-resize | sw-resize | w-resize | ew-resize | ns-resize | nesw-resize | nwse-resize | col-resize | row-resize | all-scroll | zoom-in | zoom-out | grab | grabbing ] ]`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Cursor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-grab"},{"type":"string","const":"-webkit-grab"},{"type":"string","const":"alias"},{"type":"string","const":"all-scroll"},{"type":"string","const":"auto"},{"type":"string","const":"cell"},{"type":"string","const":"col-resize"},{"type":"string","const":"context-menu"},{"type":"string","const":"copy"},{"type":"string","const":"crosshair"},{"type":"string","const":"default"},{"type":"string","const":"e-resize"},{"type":"string","const":"ew-resize"},{"type":"string","const":"grab"},{"type":"string","const":"grabbing"},{"type":"string","const":"help"},{"type":"string","const":"move"},{"type":"string","const":"n-resize"},{"type":"string","const":"ne-resize"},{"type":"string","const":"nesw-resize"},{"type":"string","const":"no-drop"},{"type":"string","const":"none"},{"type":"string","const":"not-allowed"},{"type":"string","const":"ns-resize"},{"type":"string","const":"nw-resize"},{"type":"string","const":"nwse-resize"},{"type":"string","const":"pointer"},{"type":"string","const":"progress"},{"type":"string","const":"row-resize"},{"type":"string","const":"s-resize"},{"type":"string","const":"se-resize"},{"type":"string","const":"sw-resize"},{"type":"string","const":"text"},{"type":"string","const":"vertical-text"},{"type":"string","const":"w-resize"},{"type":"string","const":"wait"},{"type":"string","const":"zoom-in"},{"type":"string","const":"zoom-out"},{"type":"string"}]
     *
     * @see Property_Cursor
     */
    @Const({"-moz-grab", "-webkit-grab", "alias", "all-scroll", "auto", "cell", "col-resize", "context-menu", "copy", "crosshair", "default", "e-resize", "ew-resize", "grab", "grabbing", "help", "move", "n-resize", "ne-resize", "nesw-resize", "no-drop", "none", "not-allowed", "ns-resize", "nw-resize", "nwse-resize", "pointer", "progress", "row-resize", "s-resize", "se-resize", "sw-resize", "text", "vertical-text", "w-resize", "wait", "zoom-in", "zoom-out"})
    @Schema(title = "The **`cursor`** CSS property sets the type of mouse cursor, if any, to show when the mouse pointer is over an element.\n\n**Syntax**: `[ [ <url> [ <x> <y> ]? , ]* [ auto | default | none | context-menu | help | pointer | progress | wait | cell | crosshair | text | vertical-text | alias | copy | move | no-drop | not-allowed | e-resize | n-resize | ne-resize | nw-resize | s-resize | se-resize | sw-resize | w-resize | ew-resize | ns-resize | nesw-resize | nwse-resize | col-resize | row-resize | all-scroll | zoom-in | zoom-out | grab | grabbing ] ]`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **4** |")
    String cursor() default "	";

    /**
     * The **`direction`** CSS property sets the direction of text, table columns, and horizontal overflow. Use `rtl` for languages written from right to left (like Hebrew or Arabic), and `ltr` for those written from left to right (like English and most other languages).\n\n**Syntax**: `ltr | rtl`\n\n**Initial value**: `ltr`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **2**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.Direction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ltr"},{"type":"string","const":"rtl"}]
     *
     * @see Property_Direction
     */
    @Const({"ltr", "rtl"})
    @Schema(title = "The **`direction`** CSS property sets the direction of text, table columns, and horizontal overflow. Use `rtl` for languages written from right to left (like Hebrew or Arabic), and `ltr` for those written from left to right (like English and most other languages).\n\n**Syntax**: `ltr | rtl`\n\n**Initial value**: `ltr`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **2**  |  **1**  | **1**  | **12** | **5.5** |")
    String direction() default "	";

    /**
     * The **`display`** CSS property sets whether an element is treated as a block or inline element and the layout used for its children, such as flow layout, grid or flex.\n\n**Syntax**: `[ <display-outside> || <display-inside> ] | <display-listitem> | <display-internal> | <display-box> | <display-legacy>`\n\n**Initial value**: `inline`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Display"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.DisplayOutside"},{"$ref":"#/definitions/DataType.DisplayInside"},{"$ref":"#/definitions/DataType.DisplayInternal"},{"$ref":"#/definitions/DataType.DisplayLegacy"},{"type":"string","const":"contents"},{"type":"string","const":"list-item"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Display
     */
    @Const({"contents", "list-item", "none"})
    @Schema(title = "The **`display`** CSS property sets whether an element is treated as a block or inline element and the layout used for its children, such as flow layout, grid or flex.\n\n**Syntax**: `[ <display-outside> || <display-inside> ] | <display-listitem> | <display-internal> | <display-box> | <display-legacy>`\n\n**Initial value**: `inline`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String display() default "	";

    /**
     * dominantBaseline
     *
     * 参考定义: "#/definitions/Property.DominantBaseline"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alphabetic"},{"type":"string","const":"auto"},{"type":"string","const":"central"},{"type":"string","const":"hanging"},{"type":"string","const":"ideographic"},{"type":"string","const":"mathematical"},{"type":"string","const":"middle"},{"type":"string","const":"no-change"},{"type":"string","const":"reset-size"},{"type":"string","const":"text-after-edge"},{"type":"string","const":"text-before-edge"},{"type":"string","const":"use-script"}]
     *
     * @see Property_DominantBaseline
     */
    @Const({"alphabetic", "auto", "central", "hanging", "ideographic", "mathematical", "middle", "no-change", "reset-size", "text-after-edge", "text-before-edge", "use-script"})
    @Schema(title = "dominantBaseline")
    String dominantBaseline() default "	";

    /**
     * fill
     *
     * 参考定义: "#/definitions/Property.Fill"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Paint"}]
     *
     * @see Property_Fill
     */

    @Schema(title = "fill")
    String fill() default "	";

    /**
     * fillOpacity
     *
     * 参考定义: "#/definitions/Property.FillOpacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FillOpacity
     */

    @Schema(title = "fillOpacity")
    String fillOpacity() default "	";

    /**
     * fillRule
     *
     * 参考定义: "#/definitions/Property.FillRule"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"evenodd"},{"type":"string","const":"nonzero"}]
     *
     * @see Property_FillRule
     */
    @Const({"evenodd", "nonzero"})
    @Schema(title = "fillRule")
    String fillRule() default "	";

    /**
     * The **`filter`** CSS property applies graphical effects like blur or color shift to an element. Filters are commonly used to adjust the rendering of images, backgrounds, and borders.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`\n\n|  Chrome  | Firefox | Safari  |  Edge  | IE  | | :------: | :-----: | :-----: | :----: | :-: | |  **53**  | **35**  | **9.1** | **12** | No  | | 18 _-x-_ |         | 6 _-x-_ |        |     |
     *
     * 参考定义: "#/definitions/Property.Filter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Filter
     */
    @Const({"none"})
    @Schema(title = "The **`filter`** CSS property applies graphical effects like blur or color shift to an element. Filters are commonly used to adjust the rendering of images, backgrounds, and borders.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`\n\n|  Chrome  | Firefox | Safari  |  Edge  | IE  | | :------: | :-----: | :-----: | :----: | :-: | |  **53**  | **35**  | **9.1** | **12** | No  | | 18 _-x-_ |         | 6 _-x-_ |        |     |")
    String filter() default "	";

    /**
     * floodColor
     *
     * 参考定义: "#/definitions/Property.FloodColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"currentColor"}]
     *
     * @see Property_FloodColor
     */
    @Const({"currentColor"})
    @Schema(title = "floodColor")
    String floodColor() default "	";

    /**
     * floodOpacity
     *
     * 参考定义: "#/definitions/Property.FloodOpacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FloodOpacity
     */

    @Schema(title = "floodOpacity")
    String floodOpacity() default "	";

    /**
     * The **`font`** CSS shorthand property sets all the different properties of an element's font. Alternatively, it sets an element's font to a system font.\n\n**Syntax**: `[ [ <'font-style'> || <font-variant-css21> || <'font-weight'> || <'font-stretch'> ]? <'font-size'> [ / <'line-height'> ]? <'font-family'> ] | caption | icon | menu | message-box | small-caption | status-bar`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.Font"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"caption"},{"type":"string","const":"icon"},{"type":"string","const":"menu"},{"type":"string","const":"message-box"},{"type":"string","const":"small-caption"},{"type":"string","const":"status-bar"},{"type":"string"}]
     *
     * @see Property_Font
     */
    @Const({"caption", "icon", "menu", "message-box", "small-caption", "status-bar"})
    @Schema(title = "The **`font`** CSS shorthand property sets all the different properties of an element's font. Alternatively, it sets an element's font to a system font.\n\n**Syntax**: `[ [ <'font-style'> || <font-variant-css21> || <'font-weight'> || <'font-stretch'> ]? <'font-size'> [ / <'line-height'> ]? <'font-family'> ] | caption | icon | menu | message-box | small-caption | status-bar`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String font() default "	";

    /**
     * The **`font-family`** CSS property specifies a prioritized list of one or more font family names and/or generic family names for the selected element.\n\n**Syntax**: `[ <family-name> | <generic-family> ]#`\n\n**Initial value**: depends on user agent\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.FontFamily"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GenericFamily"},{"type":"string"}]
     *
     * @see Property_FontFamily
     */

    @Schema(title = "The **`font-family`** CSS property specifies a prioritized list of one or more font family names and/or generic family names for the selected element.\n\n**Syntax**: `[ <family-name> | <generic-family> ]#`\n\n**Initial value**: depends on user agent\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String fontFamily() default "	";

    /**
     * The **`font-size`** CSS property sets the size of the font. Changing the font size also updates the sizes of the font size-relative `<length>` units, such as `em`, `ex`, and so forth.\n\n**Syntax**: `<absolute-size> | <relative-size> | <length-percentage>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.FontSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"larger"},{"type":"string","const":"smaller"}]
     *
     * @see Property_FontSize
     */
    @Const({"larger", "smaller"})
    @Schema(title = "The **`font-size`** CSS property sets the size of the font. Changing the font size also updates the sizes of the font size-relative `<length>` units, such as `em`, `ex`, and so forth.\n\n**Syntax**: `<absolute-size> | <relative-size> | <length-percentage>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String fontSize() default "	";

    /**
     * The **`font-size-adjust`** CSS property sets the size of lower-case letters relative to the current font size (which defines the size of upper-case letters).\n\n**Syntax**: `none | [ ex-height | cap-height | ch-width | ic-width | ic-height ]? [ from-font | <number> ]`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |  n/a   |  **1**  |   No   | n/a  | No  |
     *
     * 参考定义: "#/definitions/Property.FontSizeAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"from-font"},{"type":"string","const":"none"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_FontSizeAdjust
     */
    @Const({"from-font", "none"})
    @Schema(title = "The **`font-size-adjust`** CSS property sets the size of lower-case letters relative to the current font size (which defines the size of upper-case letters).\n\n**Syntax**: `none | [ ex-height | cap-height | ch-width | ic-width | ic-height ]? [ from-font | <number> ]`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |  n/a   |  **1**  |   No   | n/a  | No  |")
    String fontSizeAdjust() default "	";

    /**
     * The **`font-stretch`** CSS property selects a normal, condensed, or expanded face from a font.\n\n**Syntax**: `<font-stretch-absolute>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **60** |  **9**  | **11** | **12** | **9** |
     *
     * 参考定义: "#/definitions/Property.FontStretch"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.FontStretchAbsolute"}]
     *
     * @see Property_FontStretch
     */

    @Schema(title = "The **`font-stretch`** CSS property selects a normal, condensed, or expanded face from a font.\n\n**Syntax**: `<font-stretch-absolute>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **60** |  **9**  | **11** | **12** | **9** |")
    String fontStretch() default "	";

    /**
     * The **`font-style`** CSS property sets whether a font should be styled with a normal, italic, or oblique face from its `font-family`.\n\n**Syntax**: `normal | italic | oblique <angle>?`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.FontStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"italic"},{"type":"string","const":"normal"},{"type":"string","const":"oblique"},{"type":"string"}]
     *
     * @see Property_FontStyle
     */
    @Const({"italic", "normal", "oblique"})
    @Schema(title = "The **`font-style`** CSS property sets whether a font should be styled with a normal, italic, or oblique face from its `font-family`.\n\n**Syntax**: `normal | italic | oblique <angle>?`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String fontStyle() default "	";

    /**
     * The **`font-variant`** CSS shorthand property allows you to set all the font variants for a font.\n\n**Syntax**: `normal | none | [ <common-lig-values> || <discretionary-lig-values> || <historical-lig-values> || <contextual-alt-values> || stylistic( <feature-value-name> ) || historical-forms || styleset( <feature-value-name># ) || character-variant( <feature-value-name># ) || swash( <feature-value-name> ) || ornaments( <feature-value-name> ) || annotation( <feature-value-name> ) || [ small-caps | all-small-caps | petite-caps | all-petite-caps | unicase | titling-caps ] || <numeric-figure-values> || <numeric-spacing-values> || <numeric-fraction-values> || ordinal || slashed-zero || <east-asian-variant-values> || <east-asian-width-values> || ruby ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.FontVariant"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EastAsianVariantValues"},{"type":"string","const":"all-petite-caps"},{"type":"string","const":"all-small-caps"},{"type":"string","const":"common-ligatures"},{"type":"string","const":"contextual"},{"type":"string","const":"diagonal-fractions"},{"type":"string","const":"discretionary-ligatures"},{"type":"string","const":"full-width"},{"type":"string","const":"historical-forms"},{"type":"string","const":"historical-ligatures"},{"type":"string","const":"lining-nums"},{"type":"string","const":"no-common-ligatures"},{"type":"string","const":"no-contextual"},{"type":"string","const":"no-discretionary-ligatures"},{"type":"string","const":"no-historical-ligatures"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string","const":"oldstyle-nums"},{"type":"string","const":"ordinal"},{"type":"string","const":"petite-caps"},{"type":"string","const":"proportional-nums"},{"type":"string","const":"proportional-width"},{"type":"string","const":"ruby"},{"type":"string","const":"slashed-zero"},{"type":"string","const":"small-caps"},{"type":"string","const":"stacked-fractions"},{"type":"string","const":"tabular-nums"},{"type":"string","const":"titling-caps"},{"type":"string","const":"unicase"},{"type":"string"}]
     *
     * @see Property_FontVariant
     */
    @Const({"all-petite-caps", "all-small-caps", "common-ligatures", "contextual", "diagonal-fractions", "discretionary-ligatures", "full-width", "historical-forms", "historical-ligatures", "lining-nums", "no-common-ligatures", "no-contextual", "no-discretionary-ligatures", "no-historical-ligatures", "none", "normal", "oldstyle-nums", "ordinal", "petite-caps", "proportional-nums", "proportional-width", "ruby", "slashed-zero", "small-caps", "stacked-fractions", "tabular-nums", "titling-caps", "unicase"})
    @Schema(title = "The **`font-variant`** CSS shorthand property allows you to set all the font variants for a font.\n\n**Syntax**: `normal | none | [ <common-lig-values> || <discretionary-lig-values> || <historical-lig-values> || <contextual-alt-values> || stylistic( <feature-value-name> ) || historical-forms || styleset( <feature-value-name># ) || character-variant( <feature-value-name># ) || swash( <feature-value-name> ) || ornaments( <feature-value-name> ) || annotation( <feature-value-name> ) || [ small-caps | all-small-caps | petite-caps | all-petite-caps | unicase | titling-caps ] || <numeric-figure-values> || <numeric-spacing-values> || <numeric-fraction-values> || ordinal || slashed-zero || <east-asian-variant-values> || <east-asian-width-values> || ruby ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String fontVariant() default "	";

    /**
     * The **`font-weight`** CSS property sets the weight (or boldness) of the font. The weights available depend on the `font-family` that is currently set.\n\n**Syntax**: `<font-weight-absolute> | bolder | lighter`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **2**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.FontWeight"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.FontWeightAbsolute"},{"type":"string","const":"bolder"},{"type":"string","const":"lighter"}]
     *
     * @see Property_FontWeight
     */
    @Const({"bolder", "lighter"})
    @Schema(title = "The **`font-weight`** CSS property sets the weight (or boldness) of the font. The weights available depend on the `font-family` that is currently set.\n\n**Syntax**: `<font-weight-absolute> | bolder | lighter`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **2**  |  **1**  | **1**  | **12** | **3** |")
    String fontWeight() default "	";

    /**
     * glyphOrientationVertical
     *
     * 参考定义: "#/definitions/Property.GlyphOrientationVertical"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_GlyphOrientationVertical
     */
    @Const({"auto"})
    @Schema(title = "glyphOrientationVertical")
    String glyphOrientationVertical() default "	";

    /**
     * The **`image-rendering`** CSS property sets an image scaling algorithm. The property applies to an element itself, to any images set in its other properties, and to its descendants.\n\n**Syntax**: `auto | crisp-edges | pixelated`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **13** | **3.6** | **6**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ImageRendering"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-crisp-edges"},{"type":"string","const":"-webkit-optimize-contrast"},{"type":"string","const":"auto"},{"type":"string","const":"crisp-edges"},{"type":"string","const":"pixelated"}]
     *
     * @see Property_ImageRendering
     */
    @Const({"-moz-crisp-edges", "-webkit-optimize-contrast", "auto", "crisp-edges", "pixelated"})
    @Schema(title = "The **`image-rendering`** CSS property sets an image scaling algorithm. The property applies to an element itself, to any images set in its other properties, and to its descendants.\n\n**Syntax**: `auto | crisp-edges | pixelated`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **13** | **3.6** | **6**  | **79** | No  |")
    String imageRendering() default "	";

    /**
     * The **`letter-spacing`** CSS property sets the horizontal spacing behavior between text characters. This value is added to the natural spacing between characters while rendering the text. Positive values of `letter-spacing` causes characters to spread farther apart, while negative values of `letter-spacing` bring characters closer together.\n\n**Syntax**: `normal | <length>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.LetterSpacing%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_LetterSpacing
     */
    @Const({"normal"})
    @Schema(title = "The **`letter-spacing`** CSS property sets the horizontal spacing behavior between text characters. This value is added to the natural spacing between characters while rendering the text. Positive values of `letter-spacing` causes characters to spread farther apart, while negative values of `letter-spacing` bring characters closer together.\n\n**Syntax**: `normal | <length>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String letterSpacing() default "	";

    /**
     * lightingColor
     *
     * 参考定义: "#/definitions/Property.LightingColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"currentColor"}]
     *
     * @see Property_LightingColor
     */
    @Const({"currentColor"})
    @Schema(title = "lightingColor")
    String lightingColor() default "	";

    /**
     * The **`line-height`** CSS property sets the height of a line box. It's commonly used to set the distance between lines of text. On block-level elements, it specifies the minimum height of line boxes within the element. On non-replaced inline elements, it specifies the height that is used to calculate line box height.\n\n**Syntax**: `normal | <number> | <length> | <percentage>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.LineHeight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_LineHeight
     */
    @Const({"normal"})
    @Schema(title = "The **`line-height`** CSS property sets the height of a line box. It's commonly used to set the distance between lines of text. On block-level elements, it specifies the minimum height of line boxes within the element. On non-replaced inline elements, it specifies the height that is used to calculate line box height.\n\n**Syntax**: `normal | <number> | <length> | <percentage>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String lineHeight() default "	";

    /**
     * marker
     *
     * 参考定义: "#/definitions/Property.Marker"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Marker
     */
    @Const({"none"})
    @Schema(title = "marker")
    String marker() default "	";

    /**
     * markerEnd
     *
     * 参考定义: "#/definitions/Property.MarkerEnd"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MarkerEnd
     */
    @Const({"none"})
    @Schema(title = "markerEnd")
    String markerEnd() default "	";

    /**
     * markerMid
     *
     * 参考定义: "#/definitions/Property.MarkerMid"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MarkerMid
     */
    @Const({"none"})
    @Schema(title = "markerMid")
    String markerMid() default "	";

    /**
     * markerStart
     *
     * 参考定义: "#/definitions/Property.MarkerStart"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MarkerStart
     */
    @Const({"none"})
    @Schema(title = "markerStart")
    String markerStart() default "	";

    /**
     * The **`mask`** CSS shorthand property hides an element (partially or fully) by masking or clipping the image at specific points.\n\n**Syntax**: `<mask-layer>#`\n\n| Chrome | Firefox | Safari  | Edge  | IE  | | :----: | :-----: | :-----: | :---: | :-: | | **1**  |  **2**  | **3.1** | 12-79 | No  |
     *
     * 参考定义: "#/definitions/Property.Mask%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.MaskLayer%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_Mask
     */

    @Schema(title = "The **`mask`** CSS shorthand property hides an element (partially or fully) by masking or clipping the image at specific points.\n\n**Syntax**: `<mask-layer>#`\n\n| Chrome | Firefox | Safari  | Edge  | IE  | | :----: | :-----: | :-----: | :---: | :-: | | **1**  |  **2**  | **3.1** | 12-79 | No  |")
    String mask() default "	";

    /**
     * The **`opacity`** CSS property sets the opacity of an element. Opacity is the degree to which content behind an element is hidden, and is the opposite of transparency.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `1.0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **2**  | **12** | **9** |
     *
     * 参考定义: "#/definitions/Property.Opacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_Opacity
     */

    @Schema(title = "The **`opacity`** CSS property sets the opacity of an element. Opacity is the degree to which content behind an element is hidden, and is the opposite of transparency.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `1.0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **2**  | **12** | **9** |")
    String opacity() default "	";

    /**
     * The **`overflow`** CSS shorthand property sets the desired behavior for an element's overflow — i.e. when an element's content is too big to fit in its block formatting context — in both directions.\n\n**Syntax**: `[ visible | hidden | clip | scroll | auto ]{1,2}`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Overflow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-hidden-unscrollable"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"},{"type":"string"}]
     *
     * @see Property_Overflow
     */
    @Const({"-moz-hidden-unscrollable", "auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "The **`overflow`** CSS shorthand property sets the desired behavior for an element's overflow — i.e. when an element's content is too big to fit in its block formatting context — in both directions.\n\n**Syntax**: `[ visible | hidden | clip | scroll | auto ]{1,2}`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String overflow() default "	";

    /**
     * The **`paint-order`** CSS property lets you control the order in which the fill and stroke (and painting markers) of text content and shapes are drawn.\n\n**Syntax**: `normal | [ fill || stroke || markers ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **35** | **60**  | **8**  | **17** | No  |
     *
     * 参考定义: "#/definitions/Property.PaintOrder"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"fill"},{"type":"string","const":"markers"},{"type":"string","const":"normal"},{"type":"string","const":"stroke"},{"type":"string"}]
     *
     * @see Property_PaintOrder
     */
    @Const({"fill", "markers", "normal", "stroke"})
    @Schema(title = "The **`paint-order`** CSS property lets you control the order in which the fill and stroke (and painting markers) of text content and shapes are drawn.\n\n**Syntax**: `normal | [ fill || stroke || markers ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **35** | **60**  | **8**  | **17** | No  |")
    String paintOrder() default "	";

    /**
     * The **`pointer-events`** CSS property sets under what circumstances (if any) a particular graphic element can become the target of pointer events.\n\n**Syntax**: `auto | none | visiblePainted | visibleFill | visibleStroke | visible | painted | fill | stroke | all | inherit`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **1**  | **1.5** | **4**  | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.PointerEvents"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"fill"},{"type":"string","const":"inherit"},{"type":"string","const":"none"},{"type":"string","const":"painted"},{"type":"string","const":"stroke"},{"type":"string","const":"visible"},{"type":"string","const":"visibleFill"},{"type":"string","const":"visiblePainted"},{"type":"string","const":"visibleStroke"}]
     *
     * @see Property_PointerEvents
     */
    @Const({"all", "auto", "fill", "inherit", "none", "painted", "stroke", "visible", "visibleFill", "visiblePainted", "visibleStroke"})
    @Schema(title = "The **`pointer-events`** CSS property sets under what circumstances (if any) a particular graphic element can become the target of pointer events.\n\n**Syntax**: `auto | none | visiblePainted | visibleFill | visibleStroke | visible | painted | fill | stroke | all | inherit`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **1**  | **1.5** | **4**  | **12** | **11** |")
    String pointerEvents() default "	";

    /**
     * shapeRendering
     *
     * 参考定义: "#/definitions/Property.ShapeRendering"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"crispEdges"},{"type":"string","const":"geometricPrecision"},{"type":"string","const":"optimizeSpeed"}]
     *
     * @see Property_ShapeRendering
     */
    @Const({"auto", "crispEdges", "geometricPrecision", "optimizeSpeed"})
    @Schema(title = "shapeRendering")
    String shapeRendering() default "	";

    /**
     * stopColor
     *
     * 参考定义: "#/definitions/Property.StopColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"currentColor"}]
     *
     * @see Property_StopColor
     */
    @Const({"currentColor"})
    @Schema(title = "stopColor")
    String stopColor() default "	";

    /**
     * stopOpacity
     *
     * 参考定义: "#/definitions/Property.StopOpacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_StopOpacity
     */

    @Schema(title = "stopOpacity")
    String stopOpacity() default "	";

    /**
     * stroke
     *
     * 参考定义: "#/definitions/Property.Stroke"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Paint"}]
     *
     * @see Property_Stroke
     */

    @Schema(title = "stroke")
    String stroke() default "	";

    /**
     * strokeDasharray
     *
     * 参考定义: "#/definitions/Property.StrokeDasharray%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Dasharray%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"}]
     *
     * @see Property_StrokeDasharray
     */
    @Const({"none"})
    @Schema(title = "strokeDasharray")
    String strokeDasharray() default "	";

    /**
     * strokeDashoffset
     *
     * 参考定义: "#/definitions/Property.StrokeDashoffset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_StrokeDashoffset
     */

    @Schema(title = "strokeDashoffset")
    String strokeDashoffset() default "	";

    /**
     * strokeLinecap
     *
     * 参考定义: "#/definitions/Property.StrokeLinecap"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"butt"},{"type":"string","const":"round"},{"type":"string","const":"square"}]
     *
     * @see Property_StrokeLinecap
     */
    @Const({"butt", "round", "square"})
    @Schema(title = "strokeLinecap")
    String strokeLinecap() default "	";

    /**
     * strokeLinejoin
     *
     * 参考定义: "#/definitions/Property.StrokeLinejoin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"bevel"},{"type":"string","const":"miter"},{"type":"string","const":"round"}]
     *
     * @see Property_StrokeLinejoin
     */
    @Const({"bevel", "miter", "round"})
    @Schema(title = "strokeLinejoin")
    String strokeLinejoin() default "	";

    /**
     * strokeMiterlimit
     *
     * 参考定义: "#/definitions/Property.StrokeMiterlimit"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_StrokeMiterlimit
     */

    @Schema(title = "strokeMiterlimit")
    String strokeMiterlimit() default "	";

    /**
     * strokeOpacity
     *
     * 参考定义: "#/definitions/Property.StrokeOpacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_StrokeOpacity
     */

    @Schema(title = "strokeOpacity")
    String strokeOpacity() default "	";

    /**
     * strokeWidth
     *
     * 参考定义: "#/definitions/Property.StrokeWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_StrokeWidth
     */

    @Schema(title = "strokeWidth")
    String strokeWidth() default "	";

    /**
     * textAnchor
     *
     * 参考定义: "#/definitions/Property.TextAnchor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"end"},{"type":"string","const":"middle"},{"type":"string","const":"start"}]
     *
     * @see Property_TextAnchor
     */
    @Const({"end", "middle", "start"})
    @Schema(title = "textAnchor")
    String textAnchor() default "	";

    /**
     * The **`text-decoration`** shorthand CSS property sets the appearance of decorative lines on text. It is a shorthand for `text-decoration-line`, `text-decoration-color`, `text-decoration-style`, and the newer `text-decoration-thickness` property.\n\n**Syntax**: `<'text-decoration-line'> || <'text-decoration-style'> || <'text-decoration-color'> || <'text-decoration-thickness'>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.TextDecoration%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"blink"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"from-font"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"solid"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string","const":"wavy"}]
     *
     * @see Property_TextDecoration
     */
    @Const({"auto", "blink", "dashed", "dotted", "double", "from-font", "grammar-error", "line-through", "none", "overline", "solid", "spelling-error", "underline", "wavy"})
    @Schema(title = "The **`text-decoration`** shorthand CSS property sets the appearance of decorative lines on text. It is a shorthand for `text-decoration-line`, `text-decoration-color`, `text-decoration-style`, and the newer `text-decoration-thickness` property.\n\n**Syntax**: `<'text-decoration-line'> || <'text-decoration-style'> || <'text-decoration-color'> || <'text-decoration-thickness'>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String textDecoration() default "	";

    /**
     * The **`text-rendering`** CSS property provides information to the rendering engine about what to optimize for when rendering text.\n\n**Syntax**: `auto | optimizeSpeed | optimizeLegibility | geometricPrecision`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **4**  |  **1**  | **5**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.TextRendering"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"geometricPrecision"},{"type":"string","const":"optimizeLegibility"},{"type":"string","const":"optimizeSpeed"}]
     *
     * @see Property_TextRendering
     */
    @Const({"auto", "geometricPrecision", "optimizeLegibility", "optimizeSpeed"})
    @Schema(title = "The **`text-rendering`** CSS property provides information to the rendering engine about what to optimize for when rendering text.\n\n**Syntax**: `auto | optimizeSpeed | optimizeLegibility | geometricPrecision`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **4**  |  **1**  | **5**  | **79** | No  |")
    String textRendering() default "	";

    /**
     * The **`unicode-bidi`** CSS property, together with the `direction` property, determines how bidirectional text in a document is handled. For example, if a block of content contains both left-to-right and right-to-left text, the user-agent uses a complex Unicode algorithm to decide how to display the text. The `unicode-bidi` property overrides this algorithm and allows the developer to control the text embedding.\n\n**Syntax**: `normal | embed | isolate | bidi-override | isolate-override | plaintext`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE    | | :----: | :-----: | :-----: | :----: | :-----: | | **2**  |  **1**  | **1.3** | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.UnicodeBidi"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-isolate"},{"type":"string","const":"-moz-isolate-override"},{"type":"string","const":"-moz-plaintext"},{"type":"string","const":"-webkit-isolate"},{"type":"string","const":"-webkit-isolate-override"},{"type":"string","const":"-webkit-plaintext"},{"type":"string","const":"bidi-override"},{"type":"string","const":"embed"},{"type":"string","const":"isolate"},{"type":"string","const":"isolate-override"},{"type":"string","const":"normal"},{"type":"string","const":"plaintext"}]
     *
     * @see Property_UnicodeBidi
     */
    @Const({"-moz-isolate", "-moz-isolate-override", "-moz-plaintext", "-webkit-isolate", "-webkit-isolate-override", "-webkit-plaintext", "bidi-override", "embed", "isolate", "isolate-override", "normal", "plaintext"})
    @Schema(title = "The **`unicode-bidi`** CSS property, together with the `direction` property, determines how bidirectional text in a document is handled. For example, if a block of content contains both left-to-right and right-to-left text, the user-agent uses a complex Unicode algorithm to decide how to display the text. The `unicode-bidi` property overrides this algorithm and allows the developer to control the text embedding.\n\n**Syntax**: `normal | embed | isolate | bidi-override | isolate-override | plaintext`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE    | | :----: | :-----: | :-----: | :----: | :-----: | | **2**  |  **1**  | **1.3** | **12** | **5.5** |")
    String unicodeBidi() default "	";

    /**
     * vectorEffect
     *
     * 参考定义: "#/definitions/Property.VectorEffect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"non-scaling-stroke"},{"type":"string","const":"none"}]
     *
     * @see Property_VectorEffect
     */
    @Const({"non-scaling-stroke", "none"})
    @Schema(title = "vectorEffect")
    String vectorEffect() default "	";

    /**
     * The **`visibility`** CSS property shows or hides an element without changing the layout of a document. The property can also hide rows or columns in a `<table>`.\n\n**Syntax**: `visible | hidden | collapse`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Visibility"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"collapse"},{"type":"string","const":"hidden"},{"type":"string","const":"visible"}]
     *
     * @see Property_Visibility
     */
    @Const({"collapse", "hidden", "visible"})
    @Schema(title = "The **`visibility`** CSS property shows or hides an element without changing the layout of a document. The property can also hide rows or columns in a `<table>`.\n\n**Syntax**: `visible | hidden | collapse`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String visibility() default "	";

    /**
     * The **`white-space`** CSS property sets how white space inside an element is handled.\n\n**Syntax**: `normal | pre | nowrap | pre-wrap | pre-line | break-spaces`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.WhiteSpace"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-pre-wrap"},{"type":"string","const":"break-spaces"},{"type":"string","const":"normal"},{"type":"string","const":"nowrap"},{"type":"string","const":"pre"},{"type":"string","const":"pre-line"},{"type":"string","const":"pre-wrap"}]
     *
     * @see Property_WhiteSpace
     */
    @Const({"-moz-pre-wrap", "break-spaces", "normal", "nowrap", "pre", "pre-line", "pre-wrap"})
    @Schema(title = "The **`white-space`** CSS property sets how white space inside an element is handled.\n\n**Syntax**: `normal | pre | nowrap | pre-wrap | pre-line | break-spaces`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String whiteSpace() default "	";

    /**
     * The **`word-spacing`** CSS property sets the length of space between words and between tags.\n\n**Syntax**: `normal | <length>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **6** |
     *
     * 参考定义: "#/definitions/Property.WordSpacing%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_WordSpacing
     */
    @Const({"normal"})
    @Schema(title = "The **`word-spacing`** CSS property sets the length of space between words and between tags.\n\n**Syntax**: `normal | <length>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **6** |")
    String wordSpacing() default "	";

    /**
     * The **`writing-mode`** CSS property sets whether lines of text are laid out horizontally or vertically, as well as the direction in which blocks progress. When set for an entire document, it should be set on the root element (`html` element for HTML documents).\n\n**Syntax**: `horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr`\n\n**Initial value**: `horizontal-tb`\n\n| Chrome  | Firefox |  Safari   |  Edge  |  IE   | | :-----: | :-----: | :-------: | :----: | :---: | | **48**  | **41**  | **10.1**  | **12** | **9** | | 8 _-x-_ |         | 5.1 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.WritingMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"horizontal-tb"},{"type":"string","const":"sideways-lr"},{"type":"string","const":"sideways-rl"},{"type":"string","const":"vertical-lr"},{"type":"string","const":"vertical-rl"}]
     *
     * @see Property_WritingMode
     */
    @Const({"horizontal-tb", "sideways-lr", "sideways-rl", "vertical-lr", "vertical-rl"})
    @Schema(title = "The **`writing-mode`** CSS property sets whether lines of text are laid out horizontally or vertically, as well as the direction in which blocks progress. When set for an entire document, it should be set on the root element (`html` element for HTML documents).\n\n**Syntax**: `horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr`\n\n**Initial value**: `horizontal-tb`\n\n| Chrome  | Firefox |  Safari   |  Edge  |  IE   | | :-----: | :-----: | :-------: | :----: | :---: | | **48**  | **41**  | **10.1**  | **12** | **9** | | 8 _-x-_ |         | 5.1 _-x-_ |        |       |")
    String writingMode() default "	";

    /**
     * In combination with `elevation`, the **`azimuth`** CSS property enables different audio sources to be positioned spatially for aural presentation. This is important in that it provides a natural way to tell several voices apart, as each can be positioned to originate at a different location on the sound stage. Stereo output produce a lateral sound stage, while binaural headphones and multi-speaker setups allow for a fully three-dimensional stage.\n\n**Syntax**: `<angle> | [ [ left-side | far-left | left | center-left | center | center-right | right | far-right | right-side ] || behind ] | leftwards | rightwards`\n\n**Initial value**: `center`
     *
     * 参考定义: "#/definitions/Property.Azimuth"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"behind"},{"type":"string","const":"center"},{"type":"string","const":"center-left"},{"type":"string","const":"center-right"},{"type":"string","const":"far-left"},{"type":"string","const":"far-right"},{"type":"string","const":"left"},{"type":"string","const":"left-side"},{"type":"string","const":"leftwards"},{"type":"string","const":"right"},{"type":"string","const":"right-side"},{"type":"string","const":"rightwards"},{"type":"string"}]
     *
     * @see Property_Azimuth
     */
    @Const({"behind", "center", "center-left", "center-right", "far-left", "far-right", "left", "left-side", "leftwards", "right", "right-side", "rightwards"})
    @Schema(title = "In combination with `elevation`, the **`azimuth`** CSS property enables different audio sources to be positioned spatially for aural presentation. This is important in that it provides a natural way to tell several voices apart, as each can be positioned to originate at a different location on the sound stage. Stereo output produce a lateral sound stage, while binaural headphones and multi-speaker setups allow for a fully three-dimensional stage.\n\n**Syntax**: `<angle> | [ [ left-side | far-left | left | center-left | center | center-right | right | far-right | right-side ] || behind ] | leftwards | rightwards`\n\n**Initial value**: `center`")
    String azimuth() default "	";

    /**
     * The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`
     *
     * 参考定义: "#/definitions/Property.BoxAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"baseline"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"start"},{"type":"string","const":"stretch"}]
     *
     * @see Property_BoxAlign
     */
    @Const({"baseline", "center", "end", "start", "stretch"})
    @Schema(title = "The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`")
    String boxAlign() default "	";

    /**
     * The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.BoxDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inherit"},{"type":"string","const":"normal"},{"type":"string","const":"reverse"}]
     *
     * @see Property_BoxDirection
     */
    @Const({"inherit", "normal", "reverse"})
    @Schema(title = "The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`")
    String boxDirection() default "	";

    /**
     * The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BoxFlex"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlex
     */

    @Schema(title = "The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`")
    String boxFlex() default "	";

    /**
     * The **`box-flex-group`** CSS property assigns the flexbox's child elements to a flex group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxFlexGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlexGroup
     */

    @Schema(title = "The **`box-flex-group`** CSS property assigns the flexbox's child elements to a flex group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String boxFlexGroup() default "	";

    /**
     * The **`box-lines`** CSS property determines whether the box may have a single or multiple lines (rows for horizontally oriented boxes, columns for vertically oriented boxes).\n\n**Syntax**: `single | multiple`\n\n**Initial value**: `single`
     *
     * 参考定义: "#/definitions/Property.BoxLines"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"multiple"},{"type":"string","const":"single"}]
     *
     * @see Property_BoxLines
     */
    @Const({"multiple", "single"})
    @Schema(title = "The **`box-lines`** CSS property determines whether the box may have a single or multiple lines (rows for horizontally oriented boxes, columns for vertically oriented boxes).\n\n**Syntax**: `single | multiple`\n\n**Initial value**: `single`")
    String boxLines() default "	";

    /**
     * The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxOrdinalGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxOrdinalGroup
     */

    @Schema(title = "The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String boxOrdinalGroup() default "	";

    /**
     * This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)
     *
     * 参考定义: "#/definitions/Property.BoxOrient"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-axis"},{"type":"string","const":"horizontal"},{"type":"string","const":"inherit"},{"type":"string","const":"inline-axis"},{"type":"string","const":"vertical"}]
     *
     * @see Property_BoxOrient
     */
    @Const({"block-axis", "horizontal", "inherit", "inline-axis", "vertical"})
    @Schema(title = "This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)")
    String boxOrient() default "	";

    /**
     * The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`
     *
     * 参考定义: "#/definitions/Property.BoxPack"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"start"}]
     *
     * @see Property_BoxPack
     */
    @Const({"center", "end", "justify", "start"})
    @Schema(title = "The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`")
    String boxPack() default "	";

    /**
     * The **`font-variant-alternates`** CSS property controls the usage of alternate glyphs. These alternate glyphs may be referenced by alternative names defined in `@font-feature-values`.\n\n**Syntax**: `normal | [ stylistic( <feature-value-name> ) || historical-forms || styleset( <feature-value-name># ) || character-variant( <feature-value-name># ) || swash( <feature-value-name> ) || ornaments( <feature-value-name> ) || annotation( <feature-value-name> ) ]`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.FontVariantAlternates"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"historical-forms"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontVariantAlternates
     */
    @Const({"historical-forms", "normal"})
    @Schema(title = "The **`font-variant-alternates`** CSS property controls the usage of alternate glyphs. These alternate glyphs may be referenced by alternative names defined in `@font-feature-values`.\n\n**Syntax**: `normal | [ stylistic( <feature-value-name> ) || historical-forms || styleset( <feature-value-name># ) || character-variant( <feature-value-name># ) || swash( <feature-value-name> ) || ornaments( <feature-value-name> ) || annotation( <feature-value-name> ) ]`\n\n**Initial value**: `normal`")
    String fontVariantAlternates() default "	";

    /**
     * The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.GridColumnGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_GridColumnGap
     */

    @Schema(title = "The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`")
    String gridColumnGap() default "	";

    /**
     * The **`gap`** CSS property sets the gaps (gutters) between rows and columns. It is a shorthand for `row-gap` and `column-gap`.\n\n**Syntax**: `<'grid-row-gap'> <'grid-column-gap'>?`
     *
     * 参考定义: "#/definitions/Property.GridGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_GridGap
     */

    @Schema(title = "The **`gap`** CSS property sets the gaps (gutters) between rows and columns. It is a shorthand for `row-gap` and `column-gap`.\n\n**Syntax**: `<'grid-row-gap'> <'grid-column-gap'>?`")
    String gridGap() default "	";

    /**
     * The **`row-gap`** CSS property sets the size of the gap (gutter) between an element's grid rows.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.GridRowGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_GridRowGap
     */

    @Schema(title = "The **`row-gap`** CSS property sets the size of the gap (gutter) between an element's grid rows.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`")
    String gridRowGap() default "	";

    /**
     * The **`ime-mode`** CSS property controls the state of the input method editor (IME) for text fields. This property is obsolete.\n\n**Syntax**: `auto | normal | active | inactive | disabled`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.ImeMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"active"},{"type":"string","const":"auto"},{"type":"string","const":"disabled"},{"type":"string","const":"inactive"},{"type":"string","const":"normal"}]
     *
     * @see Property_ImeMode
     */
    @Const({"active", "auto", "disabled", "inactive", "normal"})
    @Schema(title = "The **`ime-mode`** CSS property controls the state of the input method editor (IME) for text fields. This property is obsolete.\n\n**Syntax**: `auto | normal | active | inactive | disabled`\n\n**Initial value**: `auto`")
    String imeMode() default "	";

    /**
     * The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.InsetBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetBlock
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`")
    String offsetBlock() default "	";

    /**
     * The **`inset-block-end`** CSS property defines the logical block end offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.InsetBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetBlockEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-block-end`** CSS property defines the logical block end offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`")
    String offsetBlockEnd() default "	";

    /**
     * The **`inset-block-start`** CSS property defines the logical block start offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.InsetBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetBlockStart
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-block-start`** CSS property defines the logical block start offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`")
    String offsetBlockStart() default "	";

    /**
     * The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.InsetInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetInline
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`")
    String offsetInline() default "	";

    /**
     * The **`inset-inline-end`** CSS property defines the logical inline end inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.InsetInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetInlineEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline-end`** CSS property defines the logical inline end inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`")
    String offsetInlineEnd() default "	";

    /**
     * The **`inset-inline-start`** CSS property defines the logical inline start inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.InsetInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetInlineStart
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline-start`** CSS property defines the logical inline start inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`")
    String offsetInlineStart() default "	";

    /**
     * The **`scroll-snap-coordinate`** CSS property defines the x and y coordinate positions within an element that will align with its nearest ancestor scroll container's `scroll-snap-destination` for each respective axis.\n\n**Syntax**: `none | <position>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapCoordinate%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ScrollSnapCoordinate
     */
    @Const({"none"})
    @Schema(title = "The **`scroll-snap-coordinate`** CSS property defines the x and y coordinate positions within an element that will align with its nearest ancestor scroll container's `scroll-snap-destination` for each respective axis.\n\n**Syntax**: `none | <position>#`\n\n**Initial value**: `none`")
    String scrollSnapCoordinate() default "	";

    /**
     * The **`scroll-snap-destination`** CSS property defines the position in x and y coordinates within the scroll container's visual viewport which element snap points align with.\n\n**Syntax**: `<position>`\n\n**Initial value**: `0px 0px`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapDestination%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_ScrollSnapDestination
     */

    @Schema(title = "The **`scroll-snap-destination`** CSS property defines the position in x and y coordinates within the scroll container's visual viewport which element snap points align with.\n\n**Syntax**: `<position>`\n\n**Initial value**: `0px 0px`")
    String scrollSnapDestination() default "	";

    /**
     * The **`scroll-snap-points-x`** CSS property defines the horizontal positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapPointsX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ScrollSnapPointsX
     */
    @Const({"none"})
    @Schema(title = "The **`scroll-snap-points-x`** CSS property defines the horizontal positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`")
    String scrollSnapPointsX() default "	";

    /**
     * The **`scroll-snap-points-y`** CSS property defines the vertical positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapPointsY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ScrollSnapPointsY
     */
    @Const({"none"})
    @Schema(title = "The **`scroll-snap-points-y`** CSS property defines the vertical positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`")
    String scrollSnapPointsY() default "	";

    /**
     * The **`scroll-snap-type-x`** CSS property defines how strictly snap points are enforced on the horizontal axis of the scroll container in case there is one.\n\n**Syntax**: `none | mandatory | proximity`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapTypeX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"}]
     *
     * @see Property_ScrollSnapTypeX
     */
    @Const({"mandatory", "none", "proximity"})
    @Schema(title = "The **`scroll-snap-type-x`** CSS property defines how strictly snap points are enforced on the horizontal axis of the scroll container in case there is one.\n\n**Syntax**: `none | mandatory | proximity`\n\n**Initial value**: `none`")
    String scrollSnapTypeX() default "	";

    /**
     * The **`scroll-snap-type-y`** CSS property defines how strictly snap points are enforced on the vertical axis of the scroll container in case there is one.\n\n**Syntax**: `none | mandatory | proximity`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapTypeY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"}]
     *
     * @see Property_ScrollSnapTypeY
     */
    @Const({"mandatory", "none", "proximity"})
    @Schema(title = "The **`scroll-snap-type-y`** CSS property defines how strictly snap points are enforced on the vertical axis of the scroll container in case there is one.\n\n**Syntax**: `none | mandatory | proximity`\n\n**Initial value**: `none`")
    String scrollSnapTypeY() default "	";

    /**
     * The **`-ms-scrollbar-track-color`** CSS property is a Microsoft extension that specifies the color of the track element of a scrollbar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `Scrollbar`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarTrackColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarTrackColor
     */

    @Schema(title = "The **`-ms-scrollbar-track-color`** CSS property is a Microsoft extension that specifies the color of the track element of a scrollbar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `Scrollbar`")
    String scrollbarTrackColor() default "	";

    /**
     * The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`
     *
     * 参考定义: "#/definitions/Property.BoxAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"baseline"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"start"},{"type":"string","const":"stretch"}]
     *
     * @see Property_BoxAlign
     */
    @Const({"baseline", "center", "end", "start", "stretch"})
    @Schema(title = "The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`")
    String KhtmlBoxAlign() default "	";

    /**
     * The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.BoxDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inherit"},{"type":"string","const":"normal"},{"type":"string","const":"reverse"}]
     *
     * @see Property_BoxDirection
     */
    @Const({"inherit", "normal", "reverse"})
    @Schema(title = "The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`")
    String KhtmlBoxDirection() default "	";

    /**
     * The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BoxFlex"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlex
     */

    @Schema(title = "The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`")
    String KhtmlBoxFlex() default "	";

    /**
     * The **`box-flex-group`** CSS property assigns the flexbox's child elements to a flex group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxFlexGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlexGroup
     */

    @Schema(title = "The **`box-flex-group`** CSS property assigns the flexbox's child elements to a flex group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String KhtmlBoxFlexGroup() default "	";

    /**
     * The **`box-lines`** CSS property determines whether the box may have a single or multiple lines (rows for horizontally oriented boxes, columns for vertically oriented boxes).\n\n**Syntax**: `single | multiple`\n\n**Initial value**: `single`
     *
     * 参考定义: "#/definitions/Property.BoxLines"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"multiple"},{"type":"string","const":"single"}]
     *
     * @see Property_BoxLines
     */
    @Const({"multiple", "single"})
    @Schema(title = "The **`box-lines`** CSS property determines whether the box may have a single or multiple lines (rows for horizontally oriented boxes, columns for vertically oriented boxes).\n\n**Syntax**: `single | multiple`\n\n**Initial value**: `single`")
    String KhtmlBoxLines() default "	";

    /**
     * The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxOrdinalGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxOrdinalGroup
     */

    @Schema(title = "The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String KhtmlBoxOrdinalGroup() default "	";

    /**
     * This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)
     *
     * 参考定义: "#/definitions/Property.BoxOrient"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-axis"},{"type":"string","const":"horizontal"},{"type":"string","const":"inherit"},{"type":"string","const":"inline-axis"},{"type":"string","const":"vertical"}]
     *
     * @see Property_BoxOrient
     */
    @Const({"block-axis", "horizontal", "inherit", "inline-axis", "vertical"})
    @Schema(title = "This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)")
    String KhtmlBoxOrient() default "	";

    /**
     * The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`
     *
     * 参考定义: "#/definitions/Property.BoxPack"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"start"}]
     *
     * @see Property_BoxPack
     */
    @Const({"center", "end", "justify", "start"})
    @Schema(title = "The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`")
    String KhtmlBoxPack() default "	";

    /**
     * The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.LineBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"auto"},{"type":"string","const":"loose"},{"type":"string","const":"normal"},{"type":"string","const":"strict"}]
     *
     * @see Property_LineBreak
     */
    @Const({"anywhere", "auto", "loose", "normal", "strict"})
    @Schema(title = "The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`")
    String KhtmlLineBreak() default "	";

    /**
     * The **`opacity`** CSS property sets the opacity of an element. Opacity is the degree to which content behind an element is hidden, and is the opposite of transparency.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `1.0`
     *
     * 参考定义: "#/definitions/Property.Opacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_Opacity
     */

    @Schema(title = "The **`opacity`** CSS property sets the opacity of an element. Opacity is the degree to which content behind an element is hidden, and is the opposite of transparency.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `1.0`")
    String KhtmlOpacity() default "	";

    /**
     * The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.UserSelect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-none"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"element"},{"type":"string","const":"none"},{"type":"string","const":"text"}]
     *
     * @see Property_UserSelect
     */
    @Const({"-moz-none", "all", "auto", "contain", "element", "none", "text"})
    @Schema(title = "The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`")
    String KhtmlUserSelect() default "	";

    /**
     * The **`background-clip`** CSS property sets whether an element's background extends underneath its border box, padding box, or content box.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `border-box`
     *
     * 参考定义: "#/definitions/Property.BackgroundClip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string"}]
     *
     * @see Property_BackgroundClip
     */

    @Schema(title = "The **`background-clip`** CSS property sets whether an element's background extends underneath its border box, padding box, or content box.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `border-box`")
    String MozBackgroundClip() default "	";

    /**
     * The **`box-decoration-break`** CSS property specifies how an element's fragments should be rendered when broken across multiple lines, columns, or pages.\n\n**Syntax**: `slice | clone`\n\n**Initial value**: `slice`
     *
     * 参考定义: "#/definitions/Property.BoxDecorationBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clone"},{"type":"string","const":"slice"}]
     *
     * @see Property_BoxDecorationBreak
     */
    @Const({"clone", "slice"})
    @Schema(title = "The **`box-decoration-break`** CSS property specifies how an element's fragments should be rendered when broken across multiple lines, columns, or pages.\n\n**Syntax**: `slice | clone`\n\n**Initial value**: `slice`")
    String MozBackgroundInlinePolicy() default "	";

    /**
     * The **`background-origin`** CSS property sets the background's origin: from the border start, inside the border, or inside the padding.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `padding-box`
     *
     * 参考定义: "#/definitions/Property.BackgroundOrigin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string"}]
     *
     * @see Property_BackgroundOrigin
     */

    @Schema(title = "The **`background-origin`** CSS property sets the background's origin: from the border start, inside the border, or inside the padding.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `padding-box`")
    String MozBackgroundOrigin() default "	";

    /**
     * The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`
     *
     * 参考定义: "#/definitions/Property.BackgroundSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgSize%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_BackgroundSize
     */

    @Schema(title = "The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`")
    String MozBackgroundSize() default "	";

    /**
     * The **`-moz-binding`** CSS property is used by Mozilla-based applications to attach an XBL binding to a DOM element.\n\n**Syntax**: `<url> | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozBinding"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MozBinding
     */
    @Const({"none"})
    @Schema(title = "The **`-moz-binding`** CSS property is used by Mozilla-based applications to attach an XBL binding to a DOM element.\n\n**Syntax**: `<url> | none`\n\n**Initial value**: `none`")
    String MozBinding() default "	";

    /**
     * The **`border-radius`** CSS property rounds the corners of an element's outer border edge. You can set a single radius to make circular corners, or two radii to make elliptical corners.\n\n**Syntax**: `<length-percentage>{1,4} [ / <length-percentage>{1,4} ]?`
     *
     * 参考定义: "#/definitions/Property.BorderRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderRadius
     */

    @Schema(title = "The **`border-radius`** CSS property rounds the corners of an element's outer border edge. You can set a single radius to make circular corners, or two radii to make elliptical corners.\n\n**Syntax**: `<length-percentage>{1,4} [ / <length-percentage>{1,4} ]?`")
    String MozBorderRadius() default "	";

    /**
     * The **`border-bottom-left-radius`** CSS property rounds the bottom-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderBottomLeftRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderBottomLeftRadius
     */

    @Schema(title = "The **`border-bottom-left-radius`** CSS property rounds the bottom-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String MozBorderRadiusBottomleft() default "	";

    /**
     * The **`border-bottom-right-radius`** CSS property rounds the bottom-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderBottomRightRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderBottomRightRadius
     */

    @Schema(title = "The **`border-bottom-right-radius`** CSS property rounds the bottom-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String MozBorderRadiusBottomright() default "	";

    /**
     * The **`border-top-left-radius`** CSS property rounds the top-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderTopLeftRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderTopLeftRadius
     */

    @Schema(title = "The **`border-top-left-radius`** CSS property rounds the top-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String MozBorderRadiusTopleft() default "	";

    /**
     * The **`border-top-right-radius`** CSS property rounds the top-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderTopRightRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderTopRightRadius
     */

    @Schema(title = "The **`border-top-right-radius`** CSS property rounds the top-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String MozBorderRadiusTopright() default "	";

    /**
     * The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`
     *
     * 参考定义: "#/definitions/Property.BoxAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"baseline"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"start"},{"type":"string","const":"stretch"}]
     *
     * @see Property_BoxAlign
     */
    @Const({"baseline", "center", "end", "start", "stretch"})
    @Schema(title = "The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`")
    String MozBoxAlign() default "	";

    /**
     * The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.BoxDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inherit"},{"type":"string","const":"normal"},{"type":"string","const":"reverse"}]
     *
     * @see Property_BoxDirection
     */
    @Const({"inherit", "normal", "reverse"})
    @Schema(title = "The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`")
    String MozBoxDirection() default "	";

    /**
     * The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BoxFlex"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlex
     */

    @Schema(title = "The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`")
    String MozBoxFlex() default "	";

    /**
     * The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxOrdinalGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxOrdinalGroup
     */

    @Schema(title = "The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String MozBoxOrdinalGroup() default "	";

    /**
     * This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)
     *
     * 参考定义: "#/definitions/Property.BoxOrient"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-axis"},{"type":"string","const":"horizontal"},{"type":"string","const":"inherit"},{"type":"string","const":"inline-axis"},{"type":"string","const":"vertical"}]
     *
     * @see Property_BoxOrient
     */
    @Const({"block-axis", "horizontal", "inherit", "inline-axis", "vertical"})
    @Schema(title = "This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)")
    String MozBoxOrient() default "	";

    /**
     * The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`
     *
     * 参考定义: "#/definitions/Property.BoxPack"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"start"}]
     *
     * @see Property_BoxPack
     */
    @Const({"center", "end", "justify", "start"})
    @Schema(title = "The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`")
    String MozBoxPack() default "	";

    /**
     * The **`box-shadow`** CSS property adds shadow effects around an element's frame. You can set multiple effects separated by commas. A box shadow is described by X and Y offsets relative to the element, blur and spread radius, and color.\n\n**Syntax**: `none | <shadow>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.BoxShadow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BoxShadow
     */
    @Const({"none"})
    @Schema(title = "The **`box-shadow`** CSS property adds shadow effects around an element's frame. You can set multiple effects separated by commas. A box shadow is described by X and Y offsets relative to the element, blur and spread radius, and color.\n\n**Syntax**: `none | <shadow>#`\n\n**Initial value**: `none`")
    String MozBoxShadow() default "	";

    /**
     * The non-standard **`-moz-float-edge`** CSS property specifies whether the height and width properties of the element include the margin, border, or padding thickness.\n\n**Syntax**: `border-box | content-box | margin-box | padding-box`\n\n**Initial value**: `content-box`
     *
     * 参考定义: "#/definitions/Property.MozFloatEdge"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"},{"type":"string","const":"margin-box"},{"type":"string","const":"padding-box"}]
     *
     * @see Property_MozFloatEdge
     */
    @Const({"border-box", "content-box", "margin-box", "padding-box"})
    @Schema(title = "The non-standard **`-moz-float-edge`** CSS property specifies whether the height and width properties of the element include the margin, border, or padding thickness.\n\n**Syntax**: `border-box | content-box | margin-box | padding-box`\n\n**Initial value**: `content-box`")
    String MozFloatEdge() default "	";

    /**
     * The **`-moz-force-broken-image-icon`** extended CSS property can be used to force the broken image icon to be shown even when a broken image has an `alt` attribute.\n\n**Syntax**: `0 | 1`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MozForceBrokenImageIcon"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number","const":0},{"type":"string"},{"type":"number","const":1}]
     *
     * @see Property_MozForceBrokenImageIcon
     */
    @Const({"0", "1"})
    @Schema(title = "The **`-moz-force-broken-image-icon`** extended CSS property can be used to force the broken image icon to be shown even when a broken image has an `alt` attribute.\n\n**Syntax**: `0 | 1`\n\n**Initial value**: `0`")
    String MozForceBrokenImageIcon() default "	";

    /**
     * The **`opacity`** CSS property sets the opacity of an element. Opacity is the degree to which content behind an element is hidden, and is the opposite of transparency.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `1.0`
     *
     * 参考定义: "#/definitions/Property.Opacity"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_Opacity
     */

    @Schema(title = "The **`opacity`** CSS property sets the opacity of an element. Opacity is the degree to which content behind an element is hidden, and is the opposite of transparency.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `1.0`")
    String MozOpacity() default "	";

    /**
     * The **`outline`** CSS shorthand property set all the outline properties in a single declaration.\n\n**Syntax**: `[ <'outline-color'> || <'outline-style'> || <'outline-width'> ]`
     *
     * 参考定义: "#/definitions/Property.Outline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string","const":"auto"},{"type":"string","const":"invert"},{"type":"string"}]
     *
     * @see Property_Outline
     */
    @Const({"auto", "invert"})
    @Schema(title = "The **`outline`** CSS shorthand property set all the outline properties in a single declaration.\n\n**Syntax**: `[ <'outline-color'> || <'outline-style'> || <'outline-width'> ]`")
    String MozOutline() default "	";

    /**
     * The **`outline-color`** CSS property sets the color of an element's outline.\n\n**Syntax**: `<color> | invert`\n\n**Initial value**: `invert`, for browsers supporting it, `currentColor` for the other
     *
     * 参考定义: "#/definitions/Property.OutlineColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"invert"}]
     *
     * @see Property_OutlineColor
     */
    @Const({"invert"})
    @Schema(title = "The **`outline-color`** CSS property sets the color of an element's outline.\n\n**Syntax**: `<color> | invert`\n\n**Initial value**: `invert`, for browsers supporting it, `currentColor` for the other")
    String MozOutlineColor() default "	";

    /**
     * In Mozilla applications like Firefox, the **`-moz-outline-radius`** CSS shorthand property can be used to give an element's `outline` rounded corners.\n\n**Syntax**: `<outline-radius>{1,4} [ / <outline-radius>{1,4} ]?`
     *
     * 参考定义: "#/definitions/Property.MozOutlineRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MozOutlineRadius
     */

    @Schema(title = "In Mozilla applications like Firefox, the **`-moz-outline-radius`** CSS shorthand property can be used to give an element's `outline` rounded corners.\n\n**Syntax**: `<outline-radius>{1,4} [ / <outline-radius>{1,4} ]?`")
    String MozOutlineRadius() default "	";

    /**
     * In Mozilla applications, the **`-moz-outline-radius-bottomleft`** CSS property can be used to round the bottom-left corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MozOutlineRadiusBottomleft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MozOutlineRadiusBottomleft
     */

    @Schema(title = "In Mozilla applications, the **`-moz-outline-radius-bottomleft`** CSS property can be used to round the bottom-left corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`")
    String MozOutlineRadiusBottomleft() default "	";

    /**
     * In Mozilla applications, the **`-moz-outline-radius-bottomright`** CSS property can be used to round the bottom-right corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MozOutlineRadiusBottomright%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MozOutlineRadiusBottomright
     */

    @Schema(title = "In Mozilla applications, the **`-moz-outline-radius-bottomright`** CSS property can be used to round the bottom-right corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`")
    String MozOutlineRadiusBottomright() default "	";

    /**
     * In Mozilla applications, the **`-moz-outline-radius-topleft`** CSS property can be used to round the top-left corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MozOutlineRadiusTopleft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MozOutlineRadiusTopleft
     */

    @Schema(title = "In Mozilla applications, the **`-moz-outline-radius-topleft`** CSS property can be used to round the top-left corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`")
    String MozOutlineRadiusTopleft() default "	";

    /**
     * In Mozilla applications, the **`-moz-outline-radius-topright`** CSS property can be used to round the top-right corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MozOutlineRadiusTopright%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MozOutlineRadiusTopright
     */

    @Schema(title = "In Mozilla applications, the **`-moz-outline-radius-topright`** CSS property can be used to round the top-right corner of an element's `outline`.\n\n**Syntax**: `<outline-radius>`\n\n**Initial value**: `0`")
    String MozOutlineRadiusTopright() default "	";

    /**
     * The **`outline-style`** CSS property sets the style of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `auto | <'border-style'>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.OutlineStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_OutlineStyle
     */
    @Const({"auto"})
    @Schema(title = "The **`outline-style`** CSS property sets the style of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `auto | <'border-style'>`\n\n**Initial value**: `none`")
    String MozOutlineStyle() default "	";

    /**
     * The CSS **`outline-width`** property sets the thickness of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`
     *
     * 参考定义: "#/definitions/Property.OutlineWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_OutlineWidth
     */

    @Schema(title = "The CSS **`outline-width`** property sets the thickness of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`")
    String MozOutlineWidth() default "	";

    /**
     * The **`text-align-last`** CSS property sets how the last line of a block or a line, right before a forced line break, is aligned.\n\n**Syntax**: `auto | start | end | left | right | center | justify`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.TextAlignLast"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"start"}]
     *
     * @see Property_TextAlignLast
     */
    @Const({"auto", "center", "end", "justify", "left", "right", "start"})
    @Schema(title = "The **`text-align-last`** CSS property sets how the last line of a block or a line, right before a forced line break, is aligned.\n\n**Syntax**: `auto | start | end | left | right | center | justify`\n\n**Initial value**: `auto`")
    String MozTextAlignLast() default "	";

    /**
     * The **`text-decoration-color`** CSS property sets the color of decorations added to text by `text-decoration-line`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.TextDecorationColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_TextDecorationColor
     */

    @Schema(title = "The **`text-decoration-color`** CSS property sets the color of decorations added to text by `text-decoration-line`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String MozTextDecorationColor() default "	";

    /**
     * The **`text-decoration-line`** CSS property sets the kind of decoration that is used on text in an element, such as an underline or overline.\n\n**Syntax**: `none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.TextDecorationLine"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"blink"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string"}]
     *
     * @see Property_TextDecorationLine
     */
    @Const({"blink", "grammar-error", "line-through", "none", "overline", "spelling-error", "underline"})
    @Schema(title = "The **`text-decoration-line`** CSS property sets the kind of decoration that is used on text in an element, such as an underline or overline.\n\n**Syntax**: `none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error`\n\n**Initial value**: `none`")
    String MozTextDecorationLine() default "	";

    /**
     * The **`text-decoration-style`** CSS property sets the style of the lines specified by `text-decoration-line`. The style applies to all lines that are set with `text-decoration-line`.\n\n**Syntax**: `solid | double | dotted | dashed | wavy`\n\n**Initial value**: `solid`
     *
     * 参考定义: "#/definitions/Property.TextDecorationStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"solid"},{"type":"string","const":"wavy"}]
     *
     * @see Property_TextDecorationStyle
     */
    @Const({"dashed", "dotted", "double", "solid", "wavy"})
    @Schema(title = "The **`text-decoration-style`** CSS property sets the style of the lines specified by `text-decoration-line`. The style applies to all lines that are set with `text-decoration-line`.\n\n**Syntax**: `solid | double | dotted | dashed | wavy`\n\n**Initial value**: `solid`")
    String MozTextDecorationStyle() default "	";

    /**
     * In Mozilla applications, **`-moz-user-input`** determines if an element will accept user input.\n\n**Syntax**: `auto | none | enabled | disabled`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MozUserInput"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"disabled"},{"type":"string","const":"enabled"},{"type":"string","const":"none"}]
     *
     * @see Property_MozUserInput
     */
    @Const({"auto", "disabled", "enabled", "none"})
    @Schema(title = "In Mozilla applications, **`-moz-user-input`** determines if an element will accept user input.\n\n**Syntax**: `auto | none | enabled | disabled`\n\n**Initial value**: `auto`")
    String MozUserInput() default "	";

    /**
     * The **`ime-mode`** CSS property controls the state of the input method editor (IME) for text fields. This property is obsolete.\n\n**Syntax**: `auto | normal | active | inactive | disabled`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.ImeMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"active"},{"type":"string","const":"auto"},{"type":"string","const":"disabled"},{"type":"string","const":"inactive"},{"type":"string","const":"normal"}]
     *
     * @see Property_ImeMode
     */
    @Const({"active", "auto", "disabled", "inactive", "normal"})
    @Schema(title = "The **`ime-mode`** CSS property controls the state of the input method editor (IME) for text fields. This property is obsolete.\n\n**Syntax**: `auto | normal | active | inactive | disabled`\n\n**Initial value**: `auto`")
    String msImeMode() default "	";

    /**
     * The **`-ms-scrollbar-track-color`** CSS property is a Microsoft extension that specifies the color of the track element of a scrollbar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `Scrollbar`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarTrackColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarTrackColor
     */

    @Schema(title = "The **`-ms-scrollbar-track-color`** CSS property is a Microsoft extension that specifies the color of the track element of a scrollbar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `Scrollbar`")
    String msScrollbarTrackColor() default "	";

    /**
     * The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`
     *
     * 参考定义: "#/definitions/Property.Animation%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimation%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Animation
     */

    @Schema(title = "The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`")
    String OAnimation() default "	";

    /**
     * The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.AnimationDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDelay
     */

    @Schema(title = "The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String OAnimationDelay() default "	";

    /**
     * The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.AnimationDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationDirection"},{"type":"string"}]
     *
     * @see Property_AnimationDirection
     */

    @Schema(title = "The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`")
    String OAnimationDirection() default "	";

    /**
     * The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.AnimationDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDuration
     */

    @Schema(title = "The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String OAnimationDuration() default "	";

    /**
     * The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.AnimationFillMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationFillMode"},{"type":"string"}]
     *
     * @see Property_AnimationFillMode
     */

    @Schema(title = "The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`")
    String OAnimationFillMode() default "	";

    /**
     * The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.AnimationIterationCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"infinite"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_AnimationIterationCount
     */
    @Const({"infinite"})
    @Schema(title = "The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`")
    String OAnimationIterationCount() default "	";

    /**
     * The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.AnimationName"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_AnimationName
     */
    @Const({"none"})
    @Schema(title = "The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`")
    String OAnimationName() default "	";

    /**
     * The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`
     *
     * 参考定义: "#/definitions/Property.AnimationPlayState"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"string"}]
     *
     * @see Property_AnimationPlayState
     */
    @Const({"paused", "running"})
    @Schema(title = "The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`")
    String OAnimationPlayState() default "	";

    /**
     * The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.AnimationTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_AnimationTimingFunction
     */

    @Schema(title = "The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String OAnimationTimingFunction() default "	";

    /**
     * The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`
     *
     * 参考定义: "#/definitions/Property.BackgroundSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgSize%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_BackgroundSize
     */

    @Schema(title = "The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`")
    String OBackgroundSize() default "	";

    /**
     * The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`
     *
     * 参考定义: "#/definitions/Property.BorderImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImage
     */
    @Const({"none", "repeat", "round", "space", "stretch"})
    @Schema(title = "The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`")
    String OBorderImage() default "	";

    /**
     * The **`object-fit`** CSS property sets how the content of a replaced element, such as an `<img>` or `<video>`, should be resized to fit its container.\n\n**Syntax**: `fill | contain | cover | none | scale-down`\n\n**Initial value**: `fill`
     *
     * 参考定义: "#/definitions/Property.ObjectFit"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"contain"},{"type":"string","const":"cover"},{"type":"string","const":"fill"},{"type":"string","const":"none"},{"type":"string","const":"scale-down"}]
     *
     * @see Property_ObjectFit
     */
    @Const({"contain", "cover", "fill", "none", "scale-down"})
    @Schema(title = "The **`object-fit`** CSS property sets how the content of a replaced element, such as an `<img>` or `<video>`, should be resized to fit its container.\n\n**Syntax**: `fill | contain | cover | none | scale-down`\n\n**Initial value**: `fill`")
    String OObjectFit() default "	";

    /**
     * The **`object-position`** CSS property specifies the alignment of the selected replaced element's contents within the element's box. Areas of the box which aren't covered by the replaced element's object will show the element's background.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`
     *
     * 参考定义: "#/definitions/Property.ObjectPosition%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_ObjectPosition
     */

    @Schema(title = "The **`object-position`** CSS property specifies the alignment of the selected replaced element's contents within the element's box. Areas of the box which aren't covered by the replaced element's object will show the element's background.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`")
    String OObjectPosition() default "	";

    /**
     * The **`tab-size`** CSS property is used to customize the width of tab characters (U+0009).\n\n**Syntax**: `<integer> | <length>`\n\n**Initial value**: `8`
     *
     * 参考定义: "#/definitions/Property.TabSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_TabSize
     */

    @Schema(title = "The **`tab-size`** CSS property is used to customize the width of tab characters (U+0009).\n\n**Syntax**: `<integer> | <length>`\n\n**Initial value**: `8`")
    String OTabSize() default "	";

    /**
     * The **`text-overflow`** CSS property sets how hidden overflow content is signaled to users. It can be clipped, display an ellipsis ('`…`'), or display a custom string.\n\n**Syntax**: `[ clip | ellipsis | <string> ]{1,2}`\n\n**Initial value**: `clip`
     *
     * 参考定义: "#/definitions/Property.TextOverflow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clip"},{"type":"string","const":"ellipsis"},{"type":"string"}]
     *
     * @see Property_TextOverflow
     */
    @Const({"clip", "ellipsis"})
    @Schema(title = "The **`text-overflow`** CSS property sets how hidden overflow content is signaled to users. It can be clipped, display an ellipsis ('`…`'), or display a custom string.\n\n**Syntax**: `[ clip | ellipsis | <string> ]{1,2}`\n\n**Initial value**: `clip`")
    String OTextOverflow() default "	";

    /**
     * The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.Transform"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Transform
     */
    @Const({"none"})
    @Schema(title = "The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`")
    String OTransform() default "	";

    /**
     * The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`
     *
     * 参考定义: "#/definitions/Property.TransformOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
     *
     * @see Property_TransformOrigin
     */
    @Const({"bottom", "center", "left", "right", "top"})
    @Schema(title = "The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`")
    String OTransformOrigin() default "	";

    /**
     * The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`
     *
     * 参考定义: "#/definitions/Property.Transition%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleTransition%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Transition
     */

    @Schema(title = "The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`")
    String OTransition() default "	";

    /**
     * The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDelay
     */

    @Schema(title = "The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String OTransitionDelay() default "	";

    /**
     * The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDuration
     */

    @Schema(title = "The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String OTransitionDuration() default "	";

    /**
     * The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all
     *
     * 参考定义: "#/definitions/Property.TransitionProperty"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TransitionProperty
     */
    @Const({"all", "none"})
    @Schema(title = "The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all")
    String OTransitionProperty() default "	";

    /**
     * The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.TransitionTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_TransitionTimingFunction
     */

    @Schema(title = "The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String OTransitionTimingFunction() default "	";

    /**
     * The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`
     *
     * 参考定义: "#/definitions/Property.BoxAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"baseline"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"start"},{"type":"string","const":"stretch"}]
     *
     * @see Property_BoxAlign
     */
    @Const({"baseline", "center", "end", "start", "stretch"})
    @Schema(title = "The **`box-align`** CSS property specifies how an element aligns its contents across its layout in a perpendicular direction. The effect of the property is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | baseline | stretch`\n\n**Initial value**: `stretch`")
    String WebkitBoxAlign() default "	";

    /**
     * The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.BoxDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inherit"},{"type":"string","const":"normal"},{"type":"string","const":"reverse"}]
     *
     * @see Property_BoxDirection
     */
    @Const({"inherit", "normal", "reverse"})
    @Schema(title = "The **`box-direction`** CSS property specifies whether a box lays out its contents normally (from the top or left edge), or in reverse (from the bottom or right edge).\n\n**Syntax**: `normal | reverse | inherit`\n\n**Initial value**: `normal`")
    String WebkitBoxDirection() default "	";

    /**
     * The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BoxFlex"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlex
     */

    @Schema(title = "The **`-moz-box-flex`** and **`-webkit-box-flex`** CSS properties specify how a `-moz-box` or `-webkit-box` grows to fill the box that contains it, in the direction of the containing box's layout.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`")
    String WebkitBoxFlex() default "	";

    /**
     * The **`box-flex-group`** CSS property assigns the flexbox's child elements to a flex group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxFlexGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxFlexGroup
     */

    @Schema(title = "The **`box-flex-group`** CSS property assigns the flexbox's child elements to a flex group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String WebkitBoxFlexGroup() default "	";

    /**
     * The **`box-lines`** CSS property determines whether the box may have a single or multiple lines (rows for horizontally oriented boxes, columns for vertically oriented boxes).\n\n**Syntax**: `single | multiple`\n\n**Initial value**: `single`
     *
     * 参考定义: "#/definitions/Property.BoxLines"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"multiple"},{"type":"string","const":"single"}]
     *
     * @see Property_BoxLines
     */
    @Const({"multiple", "single"})
    @Schema(title = "The **`box-lines`** CSS property determines whether the box may have a single or multiple lines (rows for horizontally oriented boxes, columns for vertically oriented boxes).\n\n**Syntax**: `single | multiple`\n\n**Initial value**: `single`")
    String WebkitBoxLines() default "	";

    /**
     * The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.BoxOrdinalGroup"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_BoxOrdinalGroup
     */

    @Schema(title = "The **`box-ordinal-group`** CSS property assigns the flexbox's child elements to an ordinal group.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `1`")
    String WebkitBoxOrdinalGroup() default "	";

    /**
     * This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)
     *
     * 参考定义: "#/definitions/Property.BoxOrient"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-axis"},{"type":"string","const":"horizontal"},{"type":"string","const":"inherit"},{"type":"string","const":"inline-axis"},{"type":"string","const":"vertical"}]
     *
     * @see Property_BoxOrient
     */
    @Const({"block-axis", "horizontal", "inherit", "inline-axis", "vertical"})
    @Schema(title = "This is a property of the original CSS Flexible Box Layout Module draft, and has been replaced by a newer standard. See flexbox for information about the current standard.\n\n**Syntax**: `horizontal | vertical | inline-axis | block-axis | inherit`\n\n**Initial value**: `inline-axis` (`horizontal` in XUL)")
    String WebkitBoxOrient() default "	";

    /**
     * The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`
     *
     * 参考定义: "#/definitions/Property.BoxPack"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"start"}]
     *
     * @see Property_BoxPack
     */
    @Const({"center", "end", "justify", "start"})
    @Schema(title = "The **`-moz-box-pack`** and **`-webkit-box-pack`** CSS properties specify how a `-moz-box` or `-webkit-box` packs its contents in the direction of its layout. The effect of this is only visible if there is extra space in the box.\n\n**Syntax**: `start | center | end | justify`\n\n**Initial value**: `start`")
    String WebkitBoxPack() default "	";

    /**
     * The **`scroll-snap-points-x`** CSS property defines the horizontal positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapPointsX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ScrollSnapPointsX
     */
    @Const({"none"})
    @Schema(title = "The **`scroll-snap-points-x`** CSS property defines the horizontal positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`")
    String WebkitScrollSnapPointsX() default "	";

    /**
     * The **`scroll-snap-points-y`** CSS property defines the vertical positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapPointsY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ScrollSnapPointsY
     */
    @Const({"none"})
    @Schema(title = "The **`scroll-snap-points-y`** CSS property defines the vertical positioning of snap points within the content of the scroll container they are applied to.\n\n**Syntax**: `none | repeat( <length-percentage> )`\n\n**Initial value**: `none`")
    String WebkitScrollSnapPointsY() default "	";

    /**
     * The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`
     *
     * 参考定义: "#/definitions/Property.Animation%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimation%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Animation
     */

    @Schema(title = "The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`")
    String MozAnimation() default "	";

    /**
     * The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`
     *
     * 参考定义: "#/definitions/Property.BorderImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImage
     */
    @Const({"none", "repeat", "round", "space", "stretch"})
    @Schema(title = "The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`")
    String MozBorderImage() default "	";

    /**
     * The **`column-rule`** shorthand CSS property sets the width, style, and color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'column-rule-width'> || <'column-rule-style'> || <'column-rule-color'>`
     *
     * 参考定义: "#/definitions/Property.ColumnRule%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_ColumnRule
     */

    @Schema(title = "The **`column-rule`** shorthand CSS property sets the width, style, and color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'column-rule-width'> || <'column-rule-style'> || <'column-rule-color'>`")
    String MozColumnRule() default "	";

    /**
     * The **`columns`** CSS shorthand property sets the number of columns to use when drawing an element's contents, as well as those columns' widths.\n\n**Syntax**: `<'column-width'> || <'column-count'>`
     *
     * 参考定义: "#/definitions/Property.Columns%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Columns
     */
    @Const({"auto"})
    @Schema(title = "The **`columns`** CSS shorthand property sets the number of columns to use when drawing an element's contents, as well as those columns' widths.\n\n**Syntax**: `<'column-width'> || <'column-count'>`")
    String MozColumns() default "	";

    /**
     * The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`
     *
     * 参考定义: "#/definitions/Property.Transition%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleTransition%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Transition
     */

    @Schema(title = "The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`")
    String MozTransition() default "	";

    /**
     * The **`-ms-content-zoom-limit`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-content-zoom-limit-min` and `-ms-content-zoom-limit-max` properties.\n\n**Syntax**: `<'-ms-content-zoom-limit-min'> <'-ms-content-zoom-limit-max'>`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomLimit"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsContentZoomLimit
     */

    @Schema(title = "The **`-ms-content-zoom-limit`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-content-zoom-limit-min` and `-ms-content-zoom-limit-max` properties.\n\n**Syntax**: `<'-ms-content-zoom-limit-min'> <'-ms-content-zoom-limit-max'>`")
    String msContentZoomLimit() default "	";

    /**
     * The **`-ms-content-zoom-snap`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-content-zoom-snap-type` and `-ms-content-zoom-snap-points` properties.\n\n**Syntax**: `<'-ms-content-zoom-snap-type'> || <'-ms-content-zoom-snap-points'>`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomSnap"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"},{"type":"string"}]
     *
     * @see Property_MsContentZoomSnap
     */
    @Const({"mandatory", "none", "proximity"})
    @Schema(title = "The **`-ms-content-zoom-snap`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-content-zoom-snap-type` and `-ms-content-zoom-snap-points` properties.\n\n**Syntax**: `<'-ms-content-zoom-snap-type'> || <'-ms-content-zoom-snap-points'>`")
    String msContentZoomSnap() default "	";

    /**
     * The **`flex`** CSS shorthand property sets how a flex _item_ will grow or shrink to fit the space available in its flex container.\n\n**Syntax**: `none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]`
     *
     * 参考定义: "#/definitions/Property.Flex%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"content"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_Flex
     */
    @Const({"auto", "content", "fit-content", "max-content", "min-content", "none"})
    @Schema(title = "The **`flex`** CSS shorthand property sets how a flex _item_ will grow or shrink to fit the space available in its flex container.\n\n**Syntax**: `none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]`")
    String msFlex() default "	";

    /**
     * The **-ms-scroll-limit** CSS property is a Microsoft extension that specifies values for the `-ms-scroll-limit-x-min`, `-ms-scroll-limit-y-min`, `-ms-scroll-limit-x-max`, and `-ms-scroll-limit-y-max` properties.\n\n**Syntax**: `<'-ms-scroll-limit-x-min'> <'-ms-scroll-limit-y-min'> <'-ms-scroll-limit-x-max'> <'-ms-scroll-limit-y-max'>`
     *
     * 参考定义: "#/definitions/Property.MsScrollLimit"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsScrollLimit
     */

    @Schema(title = "The **-ms-scroll-limit** CSS property is a Microsoft extension that specifies values for the `-ms-scroll-limit-x-min`, `-ms-scroll-limit-y-min`, `-ms-scroll-limit-x-max`, and `-ms-scroll-limit-y-max` properties.\n\n**Syntax**: `<'-ms-scroll-limit-x-min'> <'-ms-scroll-limit-y-min'> <'-ms-scroll-limit-x-max'> <'-ms-scroll-limit-y-max'>`")
    String msScrollLimit() default "	";

    /**
     * The **`-ms-scroll-snap-x`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-scroll-snap-type` and `-ms-scroll-snap-points-x` properties.\n\n**Syntax**: `<'-ms-scroll-snap-type'> <'-ms-scroll-snap-points-x'>`
     *
     * 参考定义: "#/definitions/Property.MsScrollSnapX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsScrollSnapX
     */

    @Schema(title = "The **`-ms-scroll-snap-x`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-scroll-snap-type` and `-ms-scroll-snap-points-x` properties.\n\n**Syntax**: `<'-ms-scroll-snap-type'> <'-ms-scroll-snap-points-x'>`")
    String msScrollSnapX() default "	";

    /**
     * The **`-ms-scroll-snap-x`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-scroll-snap-type` and `-ms-scroll-snap-points-y` properties.\n\n**Syntax**: `<'-ms-scroll-snap-type'> <'-ms-scroll-snap-points-y'>`
     *
     * 参考定义: "#/definitions/Property.MsScrollSnapY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsScrollSnapY
     */

    @Schema(title = "The **`-ms-scroll-snap-x`** CSS shorthand property is a Microsoft extension that specifies values for the `-ms-scroll-snap-type` and `-ms-scroll-snap-points-y` properties.\n\n**Syntax**: `<'-ms-scroll-snap-type'> <'-ms-scroll-snap-points-y'>`")
    String msScrollSnapY() default "	";

    /**
     * The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`
     *
     * 参考定义: "#/definitions/Property.Transition%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleTransition%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Transition
     */

    @Schema(title = "The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`")
    String msTransition() default "	";

    /**
     * The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`
     *
     * 参考定义: "#/definitions/Property.Animation%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimation%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Animation
     */

    @Schema(title = "The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`")
    String WebkitAnimation() default "	";

    /**
     * The **`-webkit-border-before`** CSS property is a shorthand property for setting the individual logical block start border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-width'> || <'border-style'> || <color>`
     *
     * 参考定义: "#/definitions/Property.WebkitBorderBefore%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_WebkitBorderBefore
     */

    @Schema(title = "The **`-webkit-border-before`** CSS property is a shorthand property for setting the individual logical block start border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-width'> || <'border-style'> || <color>`")
    String WebkitBorderBefore() default "	";

    /**
     * The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`
     *
     * 参考定义: "#/definitions/Property.BorderImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImage
     */
    @Const({"none", "repeat", "round", "space", "stretch"})
    @Schema(title = "The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`")
    String WebkitBorderImage() default "	";

    /**
     * The **`border-radius`** CSS property rounds the corners of an element's outer border edge. You can set a single radius to make circular corners, or two radii to make elliptical corners.\n\n**Syntax**: `<length-percentage>{1,4} [ / <length-percentage>{1,4} ]?`
     *
     * 参考定义: "#/definitions/Property.BorderRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderRadius
     */

    @Schema(title = "The **`border-radius`** CSS property rounds the corners of an element's outer border edge. You can set a single radius to make circular corners, or two radii to make elliptical corners.\n\n**Syntax**: `<length-percentage>{1,4} [ / <length-percentage>{1,4} ]?`")
    String WebkitBorderRadius() default "	";

    /**
     * The **`column-rule`** shorthand CSS property sets the width, style, and color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'column-rule-width'> || <'column-rule-style'> || <'column-rule-color'>`
     *
     * 参考定义: "#/definitions/Property.ColumnRule%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_ColumnRule
     */

    @Schema(title = "The **`column-rule`** shorthand CSS property sets the width, style, and color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'column-rule-width'> || <'column-rule-style'> || <'column-rule-color'>`")
    String WebkitColumnRule() default "	";

    /**
     * The **`columns`** CSS shorthand property sets the number of columns to use when drawing an element's contents, as well as those columns' widths.\n\n**Syntax**: `<'column-width'> || <'column-count'>`
     *
     * 参考定义: "#/definitions/Property.Columns%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Columns
     */
    @Const({"auto"})
    @Schema(title = "The **`columns`** CSS shorthand property sets the number of columns to use when drawing an element's contents, as well as those columns' widths.\n\n**Syntax**: `<'column-width'> || <'column-count'>`")
    String WebkitColumns() default "	";

    /**
     * The **`flex`** CSS shorthand property sets how a flex _item_ will grow or shrink to fit the space available in its flex container.\n\n**Syntax**: `none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]`
     *
     * 参考定义: "#/definitions/Property.Flex%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"content"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_Flex
     */
    @Const({"auto", "content", "fit-content", "max-content", "min-content", "none"})
    @Schema(title = "The **`flex`** CSS shorthand property sets how a flex _item_ will grow or shrink to fit the space available in its flex container.\n\n**Syntax**: `none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]`")
    String WebkitFlex() default "	";

    /**
     * The **`flex-flow`** CSS shorthand property specifies the direction of a flex container, as well as its wrapping behavior.\n\n**Syntax**: `<'flex-direction'> || <'flex-wrap'>`
     *
     * 参考定义: "#/definitions/Property.FlexFlow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"nowrap"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"},{"type":"string","const":"wrap"},{"type":"string","const":"wrap-reverse"},{"type":"string"}]
     *
     * @see Property_FlexFlow
     */
    @Const({"column", "column-reverse", "nowrap", "row", "row-reverse", "wrap", "wrap-reverse"})
    @Schema(title = "The **`flex-flow`** CSS shorthand property specifies the direction of a flex container, as well as its wrapping behavior.\n\n**Syntax**: `<'flex-direction'> || <'flex-wrap'>`")
    String WebkitFlexFlow() default "	";

    /**
     * The **`mask`** CSS shorthand property hides an element (partially or fully) by masking or clipping the image at specific points.\n\n**Syntax**: `[ <mask-reference> || <position> [ / <bg-size> ]? || <repeat-style> || [ <box> | border | padding | content | text ] || [ <box> | border | padding | content ] ]#`
     *
     * 参考定义: "#/definitions/Property.WebkitMask%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"border"},{"type":"string","const":"content"},{"type":"string","const":"none"},{"type":"string","const":"padding"},{"type":"string","const":"text"},{"type":"string"}]
     *
     * @see Property_WebkitMask
     */
    @Const({"border", "content", "none", "padding", "text"})
    @Schema(title = "The **`mask`** CSS shorthand property hides an element (partially or fully) by masking or clipping the image at specific points.\n\n**Syntax**: `[ <mask-reference> || <position> [ / <bg-size> ]? || <repeat-style> || [ <box> | border | padding | content | text ] || [ <box> | border | padding | content ] ]#`")
    String WebkitMask() default "	";

    /**
     * The **`mask-border`** CSS shorthand property lets you create a mask along the edge of an element's border.\n\n**Syntax**: `<'mask-border-source'> || <'mask-border-slice'> [ / <'mask-border-width'>? [ / <'mask-border-outset'> ]? ]? || <'mask-border-repeat'> || <'mask-border-mode'>`
     *
     * 参考定义: "#/definitions/Property.MaskBorder"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alpha"},{"type":"string","const":"luminance"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MaskBorder
     */
    @Const({"alpha", "luminance", "none", "repeat", "round", "space", "stretch"})
    @Schema(title = "The **`mask-border`** CSS shorthand property lets you create a mask along the edge of an element's border.\n\n**Syntax**: `<'mask-border-source'> || <'mask-border-slice'> [ / <'mask-border-width'>? [ / <'mask-border-outset'> ]? ]? || <'mask-border-repeat'> || <'mask-border-mode'>`")
    String WebkitMaskBoxImage() default "	";

    /**
     * The **`text-emphasis`** CSS property applies emphasis marks to text (except spaces and control characters). It is a shorthand for `text-emphasis-style` and `text-emphasis-color`.\n\n**Syntax**: `<'text-emphasis-style'> || <'text-emphasis-color'>`
     *
     * 参考定义: "#/definitions/Property.TextEmphasis"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"circle"},{"type":"string","const":"dot"},{"type":"string","const":"double-circle"},{"type":"string","const":"filled"},{"type":"string","const":"none"},{"type":"string","const":"open"},{"type":"string","const":"sesame"},{"type":"string","const":"triangle"},{"type":"string"}]
     *
     * @see Property_TextEmphasis
     */
    @Const({"circle", "dot", "double-circle", "filled", "none", "open", "sesame", "triangle"})
    @Schema(title = "The **`text-emphasis`** CSS property applies emphasis marks to text (except spaces and control characters). It is a shorthand for `text-emphasis-style` and `text-emphasis-color`.\n\n**Syntax**: `<'text-emphasis-style'> || <'text-emphasis-color'>`")
    String WebkitTextEmphasis() default "	";

    /**
     * The **`-webkit-text-stroke`** CSS property specifies the width and color of strokes for text characters. This is a shorthand property for the longhand properties `-webkit-text-stroke-width` and `-webkit-text-stroke-color`.\n\n**Syntax**: `<length> || <color>`
     *
     * 参考定义: "#/definitions/Property.WebkitTextStroke%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_WebkitTextStroke
     */

    @Schema(title = "The **`-webkit-text-stroke`** CSS property specifies the width and color of strokes for text characters. This is a shorthand property for the longhand properties `-webkit-text-stroke-width` and `-webkit-text-stroke-color`.\n\n**Syntax**: `<length> || <color>`")
    String WebkitTextStroke() default "	";

    /**
     * The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`
     *
     * 参考定义: "#/definitions/Property.Transition%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleTransition%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Transition
     */

    @Schema(title = "The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`")
    String WebkitTransition() default "	";

    /**
     * The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.AnimationDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDelay
     */

    @Schema(title = "The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String MozAnimationDelay() default "	";

    /**
     * The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.AnimationDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationDirection"},{"type":"string"}]
     *
     * @see Property_AnimationDirection
     */

    @Schema(title = "The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`")
    String MozAnimationDirection() default "	";

    /**
     * The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.AnimationDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDuration
     */

    @Schema(title = "The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String MozAnimationDuration() default "	";

    /**
     * The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.AnimationFillMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationFillMode"},{"type":"string"}]
     *
     * @see Property_AnimationFillMode
     */

    @Schema(title = "The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`")
    String MozAnimationFillMode() default "	";

    /**
     * The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.AnimationIterationCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"infinite"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_AnimationIterationCount
     */
    @Const({"infinite"})
    @Schema(title = "The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`")
    String MozAnimationIterationCount() default "	";

    /**
     * The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.AnimationName"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_AnimationName
     */
    @Const({"none"})
    @Schema(title = "The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`")
    String MozAnimationName() default "	";

    /**
     * The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`
     *
     * 参考定义: "#/definitions/Property.AnimationPlayState"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"string"}]
     *
     * @see Property_AnimationPlayState
     */
    @Const({"paused", "running"})
    @Schema(title = "The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`")
    String MozAnimationPlayState() default "	";

    /**
     * The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.AnimationTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_AnimationTimingFunction
     */

    @Schema(title = "The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String MozAnimationTimingFunction() default "	";

    /**
     * The `**appearance**` CSS property is used to display an element using platform-native styling, based on the operating system's theme. The **`-moz-appearance`** and **`-webkit-appearance`** properties are non-standard versions of this property, used (respectively) by Gecko (Firefox) and by WebKit-based (e.g., Safari) and Blink-based (e.g., Chrome, Opera) browsers to achieve the same thing. Note that Firefox and Edge also support **`-webkit-appearance`**, for compatibility reasons.\n\n**Syntax**: `none | button | button-arrow-down | button-arrow-next | button-arrow-previous | button-arrow-up | button-bevel | button-focus | caret | checkbox | checkbox-container | checkbox-label | checkmenuitem | dualbutton | groupbox | listbox | listitem | menuarrow | menubar | menucheckbox | menuimage | menuitem | menuitemtext | menulist | menulist-button | menulist-text | menulist-textfield | menupopup | menuradio | menuseparator | meterbar | meterchunk | progressbar | progressbar-vertical | progresschunk | progresschunk-vertical | radio | radio-container | radio-label | radiomenuitem | range | range-thumb | resizer | resizerpanel | scale-horizontal | scalethumbend | scalethumb-horizontal | scalethumbstart | scalethumbtick | scalethumb-vertical | scale-vertical | scrollbarbutton-down | scrollbarbutton-left | scrollbarbutton-right | scrollbarbutton-up | scrollbarthumb-horizontal | scrollbarthumb-vertical | scrollbartrack-horizontal | scrollbartrack-vertical | searchfield | separator | sheet | spinner | spinner-downbutton | spinner-textfield | spinner-upbutton | splitter | statusbar | statusbarpanel | tab | tabpanel | tabpanels | tab-scroll-arrow-back | tab-scroll-arrow-forward | textfield | textfield-multiline | toolbar | toolbarbutton | toolbarbutton-dropdown | toolbargripper | toolbox | tooltip | treeheader | treeheadercell | treeheadersortarrow | treeitem | treeline | treetwisty | treetwistyopen | treeview | -moz-mac-unified-toolbar | -moz-win-borderless-glass | -moz-win-browsertabbar-toolbox | -moz-win-communicationstext | -moz-win-communications-toolbox | -moz-win-exclude-glass | -moz-win-glass | -moz-win-mediatext | -moz-win-media-toolbox | -moz-window-button-box | -moz-window-button-box-maximized | -moz-window-button-close | -moz-window-button-maximize | -moz-window-button-minimize | -moz-window-button-restore | -moz-window-frame-bottom | -moz-window-frame-left | -moz-window-frame-right | -moz-window-titlebar | -moz-window-titlebar-maximized`\n\n**Initial value**: `none` (but this value is overridden in the user agent CSS)
     *
     * 参考定义: "#/definitions/Property.MozAppearance"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-mac-unified-toolbar"},{"type":"string","const":"-moz-win-borderless-glass"},{"type":"string","const":"-moz-win-browsertabbar-toolbox"},{"type":"string","const":"-moz-win-communications-toolbox"},{"type":"string","const":"-moz-win-communicationstext"},{"type":"string","const":"-moz-win-exclude-glass"},{"type":"string","const":"-moz-win-glass"},{"type":"string","const":"-moz-win-media-toolbox"},{"type":"string","const":"-moz-win-mediatext"},{"type":"string","const":"-moz-window-button-box"},{"type":"string","const":"-moz-window-button-box-maximized"},{"type":"string","const":"-moz-window-button-close"},{"type":"string","const":"-moz-window-button-maximize"},{"type":"string","const":"-moz-window-button-minimize"},{"type":"string","const":"-moz-window-button-restore"},{"type":"string","const":"-moz-window-frame-bottom"},{"type":"string","const":"-moz-window-frame-left"},{"type":"string","const":"-moz-window-frame-right"},{"type":"string","const":"-moz-window-titlebar"},{"type":"string","const":"-moz-window-titlebar-maximized"},{"type":"string","const":"button"},{"type":"string","const":"button-arrow-down"},{"type":"string","const":"button-arrow-next"},{"type":"string","const":"button-arrow-previous"},{"type":"string","const":"button-arrow-up"},{"type":"string","const":"button-bevel"},{"type":"string","const":"button-focus"},{"type":"string","const":"caret"},{"type":"string","const":"checkbox"},{"type":"string","const":"checkbox-container"},{"type":"string","const":"checkbox-label"},{"type":"string","const":"checkmenuitem"},{"type":"string","const":"dualbutton"},{"type":"string","const":"groupbox"},{"type":"string","const":"listbox"},{"type":"string","const":"listitem"},{"type":"string","const":"menuarrow"},{"type":"string","const":"menubar"},{"type":"string","const":"menucheckbox"},{"type":"string","const":"menuimage"},{"type":"string","const":"menuitem"},{"type":"string","const":"menuitemtext"},{"type":"string","const":"menulist"},{"type":"string","const":"menulist-button"},{"type":"string","const":"menulist-text"},{"type":"string","const":"menulist-textfield"},{"type":"string","const":"menupopup"},{"type":"string","const":"menuradio"},{"type":"string","const":"menuseparator"},{"type":"string","const":"meterbar"},{"type":"string","const":"meterchunk"},{"type":"string","const":"none"},{"type":"string","const":"progressbar"},{"type":"string","const":"progressbar-vertical"},{"type":"string","const":"progresschunk"},{"type":"string","const":"progresschunk-vertical"},{"type":"string","const":"radio"},{"type":"string","const":"radio-container"},{"type":"string","const":"radio-label"},{"type":"string","const":"radiomenuitem"},{"type":"string","const":"range"},{"type":"string","const":"range-thumb"},{"type":"string","const":"resizer"},{"type":"string","const":"resizerpanel"},{"type":"string","const":"scale-horizontal"},{"type":"string","const":"scale-vertical"},{"type":"string","const":"scalethumb-horizontal"},{"type":"string","const":"scalethumb-vertical"},{"type":"string","const":"scalethumbend"},{"type":"string","const":"scalethumbstart"},{"type":"string","const":"scalethumbtick"},{"type":"string","const":"scrollbarbutton-down"},{"type":"string","const":"scrollbarbutton-left"},{"type":"string","const":"scrollbarbutton-right"},{"type":"string","const":"scrollbarbutton-up"},{"type":"string","const":"scrollbarthumb-horizontal"},{"type":"string","const":"scrollbarthumb-vertical"},{"type":"string","const":"scrollbartrack-horizontal"},{"type":"string","const":"scrollbartrack-vertical"},{"type":"string","const":"searchfield"},{"type":"string","const":"separator"},{"type":"string","const":"sheet"},{"type":"string","const":"spinner"},{"type":"string","const":"spinner-downbutton"},{"type":"string","const":"spinner-textfield"},{"type":"string","const":"spinner-upbutton"},{"type":"string","const":"splitter"},{"type":"string","const":"statusbar"},{"type":"string","const":"statusbarpanel"},{"type":"string","const":"tab"},{"type":"string","const":"tab-scroll-arrow-back"},{"type":"string","const":"tab-scroll-arrow-forward"},{"type":"string","const":"tabpanel"},{"type":"string","const":"tabpanels"},{"type":"string","const":"textfield"},{"type":"string","const":"textfield-multiline"},{"type":"string","const":"toolbar"},{"type":"string","const":"toolbarbutton"},{"type":"string","const":"toolbarbutton-dropdown"},{"type":"string","const":"toolbargripper"},{"type":"string","const":"toolbox"},{"type":"string","const":"tooltip"},{"type":"string","const":"treeheader"},{"type":"string","const":"treeheadercell"},{"type":"string","const":"treeheadersortarrow"},{"type":"string","const":"treeitem"},{"type":"string","const":"treeline"},{"type":"string","const":"treetwisty"},{"type":"string","const":"treetwistyopen"},{"type":"string","const":"treeview"}]
     *
     * @see Property_MozAppearance
     */
    @Const({"-moz-mac-unified-toolbar", "-moz-win-borderless-glass", "-moz-win-browsertabbar-toolbox", "-moz-win-communications-toolbox", "-moz-win-communicationstext", "-moz-win-exclude-glass", "-moz-win-glass", "-moz-win-media-toolbox", "-moz-win-mediatext", "-moz-window-button-box", "-moz-window-button-box-maximized", "-moz-window-button-close", "-moz-window-button-maximize", "-moz-window-button-minimize", "-moz-window-button-restore", "-moz-window-frame-bottom", "-moz-window-frame-left", "-moz-window-frame-right", "-moz-window-titlebar", "-moz-window-titlebar-maximized", "button", "button-arrow-down", "button-arrow-next", "button-arrow-previous", "button-arrow-up", "button-bevel", "button-focus", "caret", "checkbox", "checkbox-container", "checkbox-label", "checkmenuitem", "dualbutton", "groupbox", "listbox", "listitem", "menuarrow", "menubar", "menucheckbox", "menuimage", "menuitem", "menuitemtext", "menulist", "menulist-button", "menulist-text", "menulist-textfield", "menupopup", "menuradio", "menuseparator", "meterbar", "meterchunk", "none", "progressbar", "progressbar-vertical", "progresschunk", "progresschunk-vertical", "radio", "radio-container", "radio-label", "radiomenuitem", "range", "range-thumb", "resizer", "resizerpanel", "scale-horizontal", "scale-vertical", "scalethumb-horizontal", "scalethumb-vertical", "scalethumbend", "scalethumbstart", "scalethumbtick", "scrollbarbutton-down", "scrollbarbutton-left", "scrollbarbutton-right", "scrollbarbutton-up", "scrollbarthumb-horizontal", "scrollbarthumb-vertical", "scrollbartrack-horizontal", "scrollbartrack-vertical", "searchfield", "separator", "sheet", "spinner", "spinner-downbutton", "spinner-textfield", "spinner-upbutton", "splitter", "statusbar", "statusbarpanel", "tab", "tab-scroll-arrow-back", "tab-scroll-arrow-forward", "tabpanel", "tabpanels", "textfield", "textfield-multiline", "toolbar", "toolbarbutton", "toolbarbutton-dropdown", "toolbargripper", "toolbox", "tooltip", "treeheader", "treeheadercell", "treeheadersortarrow", "treeitem", "treeline", "treetwisty", "treetwistyopen", "treeview"})
    @Schema(title = "The `**appearance**` CSS property is used to display an element using platform-native styling, based on the operating system's theme. The **`-moz-appearance`** and **`-webkit-appearance`** properties are non-standard versions of this property, used (respectively) by Gecko (Firefox) and by WebKit-based (e.g., Safari) and Blink-based (e.g., Chrome, Opera) browsers to achieve the same thing. Note that Firefox and Edge also support **`-webkit-appearance`**, for compatibility reasons.\n\n**Syntax**: `none | button | button-arrow-down | button-arrow-next | button-arrow-previous | button-arrow-up | button-bevel | button-focus | caret | checkbox | checkbox-container | checkbox-label | checkmenuitem | dualbutton | groupbox | listbox | listitem | menuarrow | menubar | menucheckbox | menuimage | menuitem | menuitemtext | menulist | menulist-button | menulist-text | menulist-textfield | menupopup | menuradio | menuseparator | meterbar | meterchunk | progressbar | progressbar-vertical | progresschunk | progresschunk-vertical | radio | radio-container | radio-label | radiomenuitem | range | range-thumb | resizer | resizerpanel | scale-horizontal | scalethumbend | scalethumb-horizontal | scalethumbstart | scalethumbtick | scalethumb-vertical | scale-vertical | scrollbarbutton-down | scrollbarbutton-left | scrollbarbutton-right | scrollbarbutton-up | scrollbarthumb-horizontal | scrollbarthumb-vertical | scrollbartrack-horizontal | scrollbartrack-vertical | searchfield | separator | sheet | spinner | spinner-downbutton | spinner-textfield | spinner-upbutton | splitter | statusbar | statusbarpanel | tab | tabpanel | tabpanels | tab-scroll-arrow-back | tab-scroll-arrow-forward | textfield | textfield-multiline | toolbar | toolbarbutton | toolbarbutton-dropdown | toolbargripper | toolbox | tooltip | treeheader | treeheadercell | treeheadersortarrow | treeitem | treeline | treetwisty | treetwistyopen | treeview | -moz-mac-unified-toolbar | -moz-win-borderless-glass | -moz-win-browsertabbar-toolbox | -moz-win-communicationstext | -moz-win-communications-toolbox | -moz-win-exclude-glass | -moz-win-glass | -moz-win-mediatext | -moz-win-media-toolbox | -moz-window-button-box | -moz-window-button-box-maximized | -moz-window-button-close | -moz-window-button-maximize | -moz-window-button-minimize | -moz-window-button-restore | -moz-window-frame-bottom | -moz-window-frame-left | -moz-window-frame-right | -moz-window-titlebar | -moz-window-titlebar-maximized`\n\n**Initial value**: `none` (but this value is overridden in the user agent CSS)")
    String MozAppearance() default "	";

    /**
     * The **`backface-visibility`** CSS property sets whether the back face of an element is visible when turned towards the user.\n\n**Syntax**: `visible | hidden`\n\n**Initial value**: `visible`
     *
     * 参考定义: "#/definitions/Property.BackfaceVisibility"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"hidden"},{"type":"string","const":"visible"}]
     *
     * @see Property_BackfaceVisibility
     */
    @Const({"hidden", "visible"})
    @Schema(title = "The **`backface-visibility`** CSS property sets whether the back face of an element is visible when turned towards the user.\n\n**Syntax**: `visible | hidden`\n\n**Initial value**: `visible`")
    String MozBackfaceVisibility() default "	";

    /**
     * In Mozilla applications like Firefox, the **`-moz-border-bottom-colors`** CSS property sets a list of colors for the bottom border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozBorderBottomColors"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MozBorderBottomColors
     */
    @Const({"none"})
    @Schema(title = "In Mozilla applications like Firefox, the **`-moz-border-bottom-colors`** CSS property sets a list of colors for the bottom border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`")
    String MozBorderBottomColors() default "	";

    /**
     * The **`border-inline-end-color`** CSS property defines the color of the logical inline-end border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.BorderInlineEndColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderInlineEndColor
     */

    @Schema(title = "The **`border-inline-end-color`** CSS property defines the color of the logical inline-end border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`")
    String MozBorderEndColor() default "	";

    /**
     * The **`border-inline-end-style`** CSS property defines the style of the logical inline end border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.BorderInlineEndStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderInlineEndStyle
     */

    @Schema(title = "The **`border-inline-end-style`** CSS property defines the style of the logical inline end border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`")
    String MozBorderEndStyle() default "	";

    /**
     * The **`border-inline-end-width`** CSS property defines the width of the logical inline-end border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`
     *
     * 参考定义: "#/definitions/Property.BorderInlineEndWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderInlineEndWidth
     */

    @Schema(title = "The **`border-inline-end-width`** CSS property defines the width of the logical inline-end border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`")
    String MozBorderEndWidth() default "	";

    /**
     * In Mozilla applications like Firefox, the **`-moz-border-left-colors`** CSS property sets a list of colors for the left border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozBorderLeftColors"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MozBorderLeftColors
     */
    @Const({"none"})
    @Schema(title = "In Mozilla applications like Firefox, the **`-moz-border-left-colors`** CSS property sets a list of colors for the left border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`")
    String MozBorderLeftColors() default "	";

    /**
     * In Mozilla applications like Firefox, the **`-moz-border-right-colors`** CSS property sets a list of colors for the right border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozBorderRightColors"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MozBorderRightColors
     */
    @Const({"none"})
    @Schema(title = "In Mozilla applications like Firefox, the **`-moz-border-right-colors`** CSS property sets a list of colors for the right border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`")
    String MozBorderRightColors() default "	";

    /**
     * The **`border-inline-start-color`** CSS property defines the color of the logical inline start border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.BorderInlineStartColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderInlineStartColor
     */

    @Schema(title = "The **`border-inline-start-color`** CSS property defines the color of the logical inline start border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`")
    String MozBorderStartColor() default "	";

    /**
     * The **`border-inline-start-style`** CSS property defines the style of the logical inline start border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.BorderInlineStartStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderInlineStartStyle
     */

    @Schema(title = "The **`border-inline-start-style`** CSS property defines the style of the logical inline start border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`")
    String MozBorderStartStyle() default "	";

    /**
     * In Mozilla applications like Firefox, the **`-moz-border-top-colors`** CSS property sets a list of colors for the top border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozBorderTopColors"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MozBorderTopColors
     */
    @Const({"none"})
    @Schema(title = "In Mozilla applications like Firefox, the **`-moz-border-top-colors`** CSS property sets a list of colors for the top border.\n\n**Syntax**: `<color>+ | none`\n\n**Initial value**: `none`")
    String MozBorderTopColors() default "	";

    /**
     * The **`box-sizing`** CSS property sets how the total width and height of an element is calculated.\n\n**Syntax**: `content-box | border-box`\n\n**Initial value**: `content-box`
     *
     * 参考定义: "#/definitions/Property.BoxSizing"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"}]
     *
     * @see Property_BoxSizing
     */
    @Const({"border-box", "content-box"})
    @Schema(title = "The **`box-sizing`** CSS property sets how the total width and height of an element is calculated.\n\n**Syntax**: `content-box | border-box`\n\n**Initial value**: `content-box`")
    String MozBoxSizing() default "	";

    /**
     * The **`column-count`** CSS property breaks an element's content into the specified number of columns.\n\n**Syntax**: `<integer> | auto`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.ColumnCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_ColumnCount
     */
    @Const({"auto"})
    @Schema(title = "The **`column-count`** CSS property breaks an element's content into the specified number of columns.\n\n**Syntax**: `<integer> | auto`\n\n**Initial value**: `auto`")
    String MozColumnCount() default "	";

    /**
     * The **`column-fill`** CSS property controls how an element's contents are balanced when broken into columns.\n\n**Syntax**: `auto | balance | balance-all`\n\n**Initial value**: `balance`
     *
     * 参考定义: "#/definitions/Property.ColumnFill"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"balance"},{"type":"string","const":"balance-all"}]
     *
     * @see Property_ColumnFill
     */
    @Const({"auto", "balance", "balance-all"})
    @Schema(title = "The **`column-fill`** CSS property controls how an element's contents are balanced when broken into columns.\n\n**Syntax**: `auto | balance | balance-all`\n\n**Initial value**: `balance`")
    String MozColumnFill() default "	";

    /**
     * The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.ColumnGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_ColumnGap
     */
    @Const({"normal"})
    @Schema(title = "The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`")
    String MozColumnGap() default "	";

    /**
     * The **`column-rule-color`** CSS property sets the color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.ColumnRuleColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_ColumnRuleColor
     */

    @Schema(title = "The **`column-rule-color`** CSS property sets the color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String MozColumnRuleColor() default "	";

    /**
     * The **`column-rule-style`** CSS property sets the style of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ColumnRuleStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string"}]
     *
     * @see Property_ColumnRuleStyle
     */

    @Schema(title = "The **`column-rule-style`** CSS property sets the style of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`")
    String MozColumnRuleStyle() default "	";

    /**
     * The **`column-rule-width`** CSS property sets the width of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`
     *
     * 参考定义: "#/definitions/Property.ColumnRuleWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_ColumnRuleWidth
     */

    @Schema(title = "The **`column-rule-width`** CSS property sets the width of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`")
    String MozColumnRuleWidth() default "	";

    /**
     * The **`column-width`** CSS property sets the ideal column width in a multi-column layout. The container will have as many columns as can fit without any of them having a width less than the `column-width` value. If the width of the container is narrower than the specified value, the single column's width will be smaller than the declared column width.\n\n**Syntax**: `<length> | auto`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.ColumnWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ColumnWidth
     */
    @Const({"auto"})
    @Schema(title = "The **`column-width`** CSS property sets the ideal column width in a multi-column layout. The container will have as many columns as can fit without any of them having a width less than the `column-width` value. If the width of the container is narrower than the specified value, the single column's width will be smaller than the declared column width.\n\n**Syntax**: `<length> | auto`\n\n**Initial value**: `auto`")
    String MozColumnWidth() default "	";

    /**
     * The `**-moz-context-properties**` property can be used within privileged contexts in Firefox to share the values of specified properties of the element with a child SVG image.\n\n**Syntax**: `none | [ fill | fill-opacity | stroke | stroke-opacity ]#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozContextProperties"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"fill"},{"type":"string","const":"fill-opacity"},{"type":"string","const":"none"},{"type":"string","const":"stroke"},{"type":"string","const":"stroke-opacity"},{"type":"string"}]
     *
     * @see Property_MozContextProperties
     */
    @Const({"fill", "fill-opacity", "none", "stroke", "stroke-opacity"})
    @Schema(title = "The `**-moz-context-properties**` property can be used within privileged contexts in Firefox to share the values of specified properties of the element with a child SVG image.\n\n**Syntax**: `none | [ fill | fill-opacity | stroke | stroke-opacity ]#`\n\n**Initial value**: `none`")
    String MozContextProperties() default "	";

    /**
     * The **`font-feature-settings`** CSS property controls advanced typographic features in OpenType fonts.\n\n**Syntax**: `normal | <feature-tag-value>#`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.FontFeatureSettings"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontFeatureSettings
     */
    @Const({"normal"})
    @Schema(title = "The **`font-feature-settings`** CSS property controls advanced typographic features in OpenType fonts.\n\n**Syntax**: `normal | <feature-tag-value>#`\n\n**Initial value**: `normal`")
    String MozFontFeatureSettings() default "	";

    /**
     * The **`font-language-override`** CSS property controls the use of language-specific glyphs in a typeface.\n\n**Syntax**: `normal | <string>`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.FontLanguageOverride"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontLanguageOverride
     */
    @Const({"normal"})
    @Schema(title = "The **`font-language-override`** CSS property controls the use of language-specific glyphs in a typeface.\n\n**Syntax**: `normal | <string>`\n\n**Initial value**: `normal`")
    String MozFontLanguageOverride() default "	";

    /**
     * The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`
     *
     * 参考定义: "#/definitions/Property.Hyphens"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"manual"},{"type":"string","const":"none"}]
     *
     * @see Property_Hyphens
     */
    @Const({"auto", "manual", "none"})
    @Schema(title = "The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`")
    String MozHyphens() default "	";

    /**
     * For certain XUL elements and pseudo-elements that use an image from the `list-style-image` property, this property specifies a region of the image that is used in place of the whole image. This allows elements to use different pieces of the same image to improve performance.\n\n**Syntax**: `<shape> | auto`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MozImageRegion"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_MozImageRegion
     */
    @Const({"auto"})
    @Schema(title = "For certain XUL elements and pseudo-elements that use an image from the `list-style-image` property, this property specifies a region of the image that is used in place of the whole image. This allows elements to use different pieces of the same image to improve performance.\n\n**Syntax**: `<shape> | auto`\n\n**Initial value**: `auto`")
    String MozImageRegion() default "	";

    /**
     * The **`margin-inline-end`** CSS property defines the logical inline end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. In other words, it corresponds to the `margin-top`, `margin-right`, `margin-bottom` or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MarginInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInlineEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline-end`** CSS property defines the logical inline end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. In other words, it corresponds to the `margin-top`, `margin-right`, `margin-bottom` or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`")
    String MozMarginEnd() default "	";

    /**
     * The **`margin-inline-start`** CSS property defines the logical inline start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. It corresponds to the `margin-top`, `margin-right`, `margin-bottom`, or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MarginInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInlineStart
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline-start`** CSS property defines the logical inline start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. It corresponds to the `margin-top`, `margin-right`, `margin-bottom`, or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`")
    String MozMarginStart() default "	";

    /**
     * The **`-moz-orient`** CSS property specifies the orientation of the element to which it's applied.\n\n**Syntax**: `inline | block | horizontal | vertical`\n\n**Initial value**: `inline`
     *
     * 参考定义: "#/definitions/Property.MozOrient"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"horizontal"},{"type":"string","const":"inline"},{"type":"string","const":"vertical"}]
     *
     * @see Property_MozOrient
     */
    @Const({"block", "horizontal", "inline", "vertical"})
    @Schema(title = "The **`-moz-orient`** CSS property specifies the orientation of the element to which it's applied.\n\n**Syntax**: `inline | block | horizontal | vertical`\n\n**Initial value**: `inline`")
    String MozOrient() default "	";

    /**
     * The **`font-smooth`** CSS property controls the application of anti-aliasing when fonts are rendered.\n\n**Syntax**: `auto | never | always | <absolute-size> | <length>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.FontSmooth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"never"}]
     *
     * @see Property_FontSmooth
     */
    @Const({"always", "auto", "never"})
    @Schema(title = "The **`font-smooth`** CSS property controls the application of anti-aliasing when fonts are rendered.\n\n**Syntax**: `auto | never | always | <absolute-size> | <length>`\n\n**Initial value**: `auto`")
    String MozOsxFontSmoothing() default "	";

    /**
     * The **`padding-inline-end`** CSS property defines the logical inline end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.PaddingInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInlineEnd
     */

    @Schema(title = "The **`padding-inline-end`** CSS property defines the logical inline end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`")
    String MozPaddingEnd() default "	";

    /**
     * The **`padding-inline-start`** CSS property defines the logical inline start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.PaddingInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInlineStart
     */

    @Schema(title = "The **`padding-inline-start`** CSS property defines the logical inline start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`")
    String MozPaddingStart() default "	";

    /**
     * The **`perspective`** CSS property determines the distance between the z=0 plane and the user in order to give a 3D-positioned element some perspective.\n\n**Syntax**: `none | <length>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.Perspective%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"none"}]
     *
     * @see Property_Perspective
     */
    @Const({"none"})
    @Schema(title = "The **`perspective`** CSS property determines the distance between the z=0 plane and the user in order to give a 3D-positioned element some perspective.\n\n**Syntax**: `none | <length>`\n\n**Initial value**: `none`")
    String MozPerspective() default "	";

    /**
     * The **`perspective-origin`** CSS property determines the position at which the viewer is looking. It is used as the _vanishing point_ by the `perspective` property.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`
     *
     * 参考定义: "#/definitions/Property.PerspectiveOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_PerspectiveOrigin
     */

    @Schema(title = "The **`perspective-origin`** CSS property determines the position at which the viewer is looking. It is used as the _vanishing point_ by the `perspective` property.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`")
    String MozPerspectiveOrigin() default "	";

    /**
     * **`-moz-stack-sizing`** is an extended CSS property. Normally, a `<xul:stack>` will change its size so that all of its child elements are completely visible. For example, moving a child of the stack far to the right will widen the stack so the child remains visible.\n\n**Syntax**: `ignore | stretch-to-fit`\n\n**Initial value**: `stretch-to-fit`
     *
     * 参考定义: "#/definitions/Property.MozStackSizing"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ignore"},{"type":"string","const":"stretch-to-fit"}]
     *
     * @see Property_MozStackSizing
     */
    @Const({"ignore", "stretch-to-fit"})
    @Schema(title = "**`-moz-stack-sizing`** is an extended CSS property. Normally, a `<xul:stack>` will change its size so that all of its child elements are completely visible. For example, moving a child of the stack far to the right will widen the stack so the child remains visible.\n\n**Syntax**: `ignore | stretch-to-fit`\n\n**Initial value**: `stretch-to-fit`")
    String MozStackSizing() default "	";

    /**
     * The **`tab-size`** CSS property is used to customize the width of tab characters (U+0009).\n\n**Syntax**: `<integer> | <length>`\n\n**Initial value**: `8`
     *
     * 参考定义: "#/definitions/Property.TabSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_TabSize
     */

    @Schema(title = "The **`tab-size`** CSS property is used to customize the width of tab characters (U+0009).\n\n**Syntax**: `<integer> | <length>`\n\n**Initial value**: `8`")
    String MozTabSize() default "	";

    /**
     * The **`-moz-text-blink`** non-standard Mozilla CSS extension specifies the blink mode.\n\n**Syntax**: `none | blink`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozTextBlink"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"blink"},{"type":"string","const":"none"}]
     *
     * @see Property_MozTextBlink
     */
    @Const({"blink", "none"})
    @Schema(title = "The **`-moz-text-blink`** non-standard Mozilla CSS extension specifies the blink mode.\n\n**Syntax**: `none | blink`\n\n**Initial value**: `none`")
    String MozTextBlink() default "	";

    /**
     * The **`text-size-adjust`** CSS property controls the text inflation algorithm used on some smartphones and tablets. Other browsers will ignore this property.\n\n**Syntax**: `none | auto | <percentage>`\n\n**Initial value**: `auto` for smartphone browsers supporting inflation, `none` in other cases (and then not modifiable).
     *
     * 参考定义: "#/definitions/Property.TextSizeAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextSizeAdjust
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`text-size-adjust`** CSS property controls the text inflation algorithm used on some smartphones and tablets. Other browsers will ignore this property.\n\n**Syntax**: `none | auto | <percentage>`\n\n**Initial value**: `auto` for smartphone browsers supporting inflation, `none` in other cases (and then not modifiable).")
    String MozTextSizeAdjust() default "	";

    /**
     * The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`
     *
     * 参考定义: "#/definitions/Property.TransformOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
     *
     * @see Property_TransformOrigin
     */
    @Const({"bottom", "center", "left", "right", "top"})
    @Schema(title = "The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`")
    String MozTransformOrigin() default "	";

    /**
     * The **`transform-style`** CSS property sets whether children of an element are positioned in the 3D space or are flattened in the plane of the element.\n\n**Syntax**: `flat | preserve-3d`\n\n**Initial value**: `flat`
     *
     * 参考定义: "#/definitions/Property.TransformStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"flat"},{"type":"string","const":"preserve-3d"}]
     *
     * @see Property_TransformStyle
     */
    @Const({"flat", "preserve-3d"})
    @Schema(title = "The **`transform-style`** CSS property sets whether children of an element are positioned in the 3D space or are flattened in the plane of the element.\n\n**Syntax**: `flat | preserve-3d`\n\n**Initial value**: `flat`")
    String MozTransformStyle() default "	";

    /**
     * The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDelay
     */

    @Schema(title = "The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String MozTransitionDelay() default "	";

    /**
     * The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDuration
     */

    @Schema(title = "The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String MozTransitionDuration() default "	";

    /**
     * The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all
     *
     * 参考定义: "#/definitions/Property.TransitionProperty"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TransitionProperty
     */
    @Const({"all", "none"})
    @Schema(title = "The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all")
    String MozTransitionProperty() default "	";

    /**
     * The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.TransitionTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_TransitionTimingFunction
     */

    @Schema(title = "The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String MozTransitionTimingFunction() default "	";

    /**
     * The **`-moz-user-focus`** CSS property is used to indicate whether an element can have the focus.\n\n**Syntax**: `ignore | normal | select-after | select-before | select-menu | select-same | select-all | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MozUserFocus"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ignore"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string","const":"select-after"},{"type":"string","const":"select-all"},{"type":"string","const":"select-before"},{"type":"string","const":"select-menu"},{"type":"string","const":"select-same"}]
     *
     * @see Property_MozUserFocus
     */
    @Const({"ignore", "none", "normal", "select-after", "select-all", "select-before", "select-menu", "select-same"})
    @Schema(title = "The **`-moz-user-focus`** CSS property is used to indicate whether an element can have the focus.\n\n**Syntax**: `ignore | normal | select-after | select-before | select-menu | select-same | select-all | none`\n\n**Initial value**: `none`")
    String MozUserFocus() default "	";

    /**
     * The **`user-modify`** property has no effect in Firefox. It was originally planned to determine whether or not the content of an element can be edited by a user.\n\n**Syntax**: `read-only | read-write | write-only`\n\n**Initial value**: `read-only`
     *
     * 参考定义: "#/definitions/Property.MozUserModify"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"read-only"},{"type":"string","const":"read-write"},{"type":"string","const":"write-only"}]
     *
     * @see Property_MozUserModify
     */
    @Const({"read-only", "read-write", "write-only"})
    @Schema(title = "The **`user-modify`** property has no effect in Firefox. It was originally planned to determine whether or not the content of an element can be edited by a user.\n\n**Syntax**: `read-only | read-write | write-only`\n\n**Initial value**: `read-only`")
    String MozUserModify() default "	";

    /**
     * The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.UserSelect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-none"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"element"},{"type":"string","const":"none"},{"type":"string","const":"text"}]
     *
     * @see Property_UserSelect
     */
    @Const({"-moz-none", "all", "auto", "contain", "element", "none", "text"})
    @Schema(title = "The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`")
    String MozUserSelect() default "	";

    /**
     * The **`-moz-window-dragging`** CSS property specifies whether a window is draggable or not. It only works in Chrome code, and only on Mac OS X.\n\n**Syntax**: `drag | no-drag`\n\n**Initial value**: `drag`
     *
     * 参考定义: "#/definitions/Property.MozWindowDragging"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"drag"},{"type":"string","const":"no-drag"}]
     *
     * @see Property_MozWindowDragging
     */
    @Const({"drag", "no-drag"})
    @Schema(title = "The **`-moz-window-dragging`** CSS property specifies whether a window is draggable or not. It only works in Chrome code, and only on Mac OS X.\n\n**Syntax**: `drag | no-drag`\n\n**Initial value**: `drag`")
    String MozWindowDragging() default "	";

    /**
     * The **`-moz-window-shadow`** CSS property specifies whether a window will have a shadow. It only works on Mac OS X.\n\n**Syntax**: `default | menu | tooltip | sheet | none`\n\n**Initial value**: `default`
     *
     * 参考定义: "#/definitions/Property.MozWindowShadow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"default"},{"type":"string","const":"menu"},{"type":"string","const":"none"},{"type":"string","const":"sheet"},{"type":"string","const":"tooltip"}]
     *
     * @see Property_MozWindowShadow
     */
    @Const({"default", "menu", "none", "sheet", "tooltip"})
    @Schema(title = "The **`-moz-window-shadow`** CSS property specifies whether a window will have a shadow. It only works on Mac OS X.\n\n**Syntax**: `default | menu | tooltip | sheet | none`\n\n**Initial value**: `default`")
    String MozWindowShadow() default "	";

    /**
     * The **`-ms-accelerator`** CSS property is a Microsoft extension that sets or retrieves a string indicating whether the object represents a keyboard shortcut.\n\n**Syntax**: `false | true`\n\n**Initial value**: `false`
     *
     * 参考定义: "#/definitions/Property.MsAccelerator"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"false"},{"type":"string","const":"true"}]
     *
     * @see Property_MsAccelerator
     */
    @Const({"false", "true"})
    @Schema(title = "The **`-ms-accelerator`** CSS property is a Microsoft extension that sets or retrieves a string indicating whether the object represents a keyboard shortcut.\n\n**Syntax**: `false | true`\n\n**Initial value**: `false`")
    String msAccelerator() default "	";

    /**
     * The **`align-self`** CSS property overrides a grid or flex item's `align-items` value. In Grid, it aligns the item inside the grid area. In Flexbox, it aligns the item on the cross axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? <self-position>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.AlignSelf"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_AlignSelf
     */
    @Const({"auto", "baseline", "normal", "stretch"})
    @Schema(title = "The **`align-self`** CSS property overrides a grid or flex item's `align-items` value. In Grid, it aligns the item inside the grid area. In Flexbox, it aligns the item on the cross axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? <self-position>`\n\n**Initial value**: `auto`")
    String msAlignSelf() default "	";

    /**
     * The **`-ms-block-progression`** CSS property is a Microsoft extension that specifies the block progression and layout orientation.\n\n**Syntax**: `tb | rl | bt | lr`\n\n**Initial value**: `tb`
     *
     * 参考定义: "#/definitions/Property.MsBlockProgression"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"bt"},{"type":"string","const":"lr"},{"type":"string","const":"rl"},{"type":"string","const":"tb"}]
     *
     * @see Property_MsBlockProgression
     */
    @Const({"bt", "lr", "rl", "tb"})
    @Schema(title = "The **`-ms-block-progression`** CSS property is a Microsoft extension that specifies the block progression and layout orientation.\n\n**Syntax**: `tb | rl | bt | lr`\n\n**Initial value**: `tb`")
    String msBlockProgression() default "	";

    /**
     * The **`-ms-content-zoom-chaining`** CSS property is a Microsoft extension specifying the zoom behavior that occurs when a user hits the zoom limit during page manipulation.\n\n**Syntax**: `none | chained`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomChaining"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"chained"},{"type":"string","const":"none"}]
     *
     * @see Property_MsContentZoomChaining
     */
    @Const({"chained", "none"})
    @Schema(title = "The **`-ms-content-zoom-chaining`** CSS property is a Microsoft extension specifying the zoom behavior that occurs when a user hits the zoom limit during page manipulation.\n\n**Syntax**: `none | chained`\n\n**Initial value**: `none`")
    String msContentZoomChaining() default "	";

    /**
     * The **`-ms-content-zoom-limit-max`** CSS property is a Microsoft extension that specifies the selected elements' maximum zoom factor.\n\n**Syntax**: `<percentage>`\n\n**Initial value**: `400%`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomLimitMax"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsContentZoomLimitMax
     */

    @Schema(title = "The **`-ms-content-zoom-limit-max`** CSS property is a Microsoft extension that specifies the selected elements' maximum zoom factor.\n\n**Syntax**: `<percentage>`\n\n**Initial value**: `400%`")
    String msContentZoomLimitMax() default "	";

    /**
     * The **`-ms-content-zoom-limit-min`** CSS property is a Microsoft extension that specifies the minimum zoom factor.\n\n**Syntax**: `<percentage>`\n\n**Initial value**: `100%`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomLimitMin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsContentZoomLimitMin
     */

    @Schema(title = "The **`-ms-content-zoom-limit-min`** CSS property is a Microsoft extension that specifies the minimum zoom factor.\n\n**Syntax**: `<percentage>`\n\n**Initial value**: `100%`")
    String msContentZoomLimitMin() default "	";

    /**
     * The **`-ms-content-zoom-snap-points`** CSS property is a Microsoft extension that specifies where zoom snap-points are located.\n\n**Syntax**: `snapInterval( <percentage>, <percentage> ) | snapList( <percentage># )`\n\n**Initial value**: `snapInterval(0%, 100%)`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomSnapPoints"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsContentZoomSnapPoints
     */

    @Schema(title = "The **`-ms-content-zoom-snap-points`** CSS property is a Microsoft extension that specifies where zoom snap-points are located.\n\n**Syntax**: `snapInterval( <percentage>, <percentage> ) | snapList( <percentage># )`\n\n**Initial value**: `snapInterval(0%, 100%)`")
    String msContentZoomSnapPoints() default "	";

    /**
     * The **`-ms-content-zoom-snap-type`** CSS property is a Microsoft extension that specifies how zooming is affected by defined snap-points.\n\n**Syntax**: `none | proximity | mandatory`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsContentZoomSnapType"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"}]
     *
     * @see Property_MsContentZoomSnapType
     */
    @Const({"mandatory", "none", "proximity"})
    @Schema(title = "The **`-ms-content-zoom-snap-type`** CSS property is a Microsoft extension that specifies how zooming is affected by defined snap-points.\n\n**Syntax**: `none | proximity | mandatory`\n\n**Initial value**: `none`")
    String msContentZoomSnapType() default "	";

    /**
     * The **`-ms-content-zooming`** CSS property is a Microsoft extension that specifies whether zooming is enabled.\n\n**Syntax**: `none | zoom`\n\n**Initial value**: zoom for the top level element, none for all other elements
     *
     * 参考定义: "#/definitions/Property.MsContentZooming"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"zoom"}]
     *
     * @see Property_MsContentZooming
     */
    @Const({"none", "zoom"})
    @Schema(title = "The **`-ms-content-zooming`** CSS property is a Microsoft extension that specifies whether zooming is enabled.\n\n**Syntax**: `none | zoom`\n\n**Initial value**: zoom for the top level element, none for all other elements")
    String msContentZooming() default "	";

    /**
     * The `-ms-filter` CSS property is a Microsoft extension that sets or retrieves the filter or collection of filters applied to an object.\n\n**Syntax**: `<string>`\n\n**Initial value**:  (the empty string)
     *
     * 参考定义: "#/definitions/Property.MsFilter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsFilter
     */

    @Schema(title = "The `-ms-filter` CSS property is a Microsoft extension that sets or retrieves the filter or collection of filters applied to an object.\n\n**Syntax**: `<string>`\n\n**Initial value**:  (the empty string)")
    String msFilter() default "	";

    /**
     * The **`flex-direction`** CSS property sets how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).\n\n**Syntax**: `row | row-reverse | column | column-reverse`\n\n**Initial value**: `row`
     *
     * 参考定义: "#/definitions/Property.FlexDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"}]
     *
     * @see Property_FlexDirection
     */
    @Const({"column", "column-reverse", "row", "row-reverse"})
    @Schema(title = "The **`flex-direction`** CSS property sets how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).\n\n**Syntax**: `row | row-reverse | column | column-reverse`\n\n**Initial value**: `row`")
    String msFlexDirection() default "	";

    /**
     * The **`flex-grow`** CSS property sets the flex grow factor of a flex item main size.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.FlexGrow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FlexGrow
     */

    @Schema(title = "The **`flex-grow`** CSS property sets the flex grow factor of a flex item main size.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`")
    String msFlexPositive() default "	";

    /**
     * The **`-ms-flow-from`** CSS property is a Microsoft extension that gets or sets a value identifying a region container in the document that accepts the content flow from the data source.\n\n**Syntax**: `[ none | <custom-ident> ]#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsFlowFrom"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MsFlowFrom
     */
    @Const({"none"})
    @Schema(title = "The **`-ms-flow-from`** CSS property is a Microsoft extension that gets or sets a value identifying a region container in the document that accepts the content flow from the data source.\n\n**Syntax**: `[ none | <custom-ident> ]#`\n\n**Initial value**: `none`")
    String msFlowFrom() default "	";

    /**
     * The **`-ms-flow-into`** CSS property is a Microsoft extension that gets or sets a value identifying an iframe container in the document that serves as the region's data source.\n\n**Syntax**: `[ none | <custom-ident> ]#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsFlowInto"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MsFlowInto
     */
    @Const({"none"})
    @Schema(title = "The **`-ms-flow-into`** CSS property is a Microsoft extension that gets or sets a value identifying an iframe container in the document that serves as the region's data source.\n\n**Syntax**: `[ none | <custom-ident> ]#`\n\n**Initial value**: `none`")
    String msFlowInto() default "	";

    /**
     * The **`grid-template-columns`** CSS property defines the line names and track sizing functions of the grid columns.\n\n**Syntax**: `none | <track-list> | <auto-track-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsGridColumns%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MsGridColumns
     */
    @Const({"none"})
    @Schema(title = "The **`grid-template-columns`** CSS property defines the line names and track sizing functions of the grid columns.\n\n**Syntax**: `none | <track-list> | <auto-track-list>`\n\n**Initial value**: `none`")
    String msGridColumns() default "	";

    /**
     * The **`grid-template-rows`** CSS property defines the line names and track sizing functions of the grid rows.\n\n**Syntax**: `none | <track-list> | <auto-track-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsGridRows%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MsGridRows
     */
    @Const({"none"})
    @Schema(title = "The **`grid-template-rows`** CSS property defines the line names and track sizing functions of the grid rows.\n\n**Syntax**: `none | <track-list> | <auto-track-list>`\n\n**Initial value**: `none`")
    String msGridRows() default "	";

    /**
     * The **`-ms-high-contrast-adjust`** CSS property is a Microsoft extension that gets or sets a value indicating whether to override any CSS properties that would have been set in high contrast mode.\n\n**Syntax**: `auto | none`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsHighContrastAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"}]
     *
     * @see Property_MsHighContrastAdjust
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`-ms-high-contrast-adjust`** CSS property is a Microsoft extension that gets or sets a value indicating whether to override any CSS properties that would have been set in high contrast mode.\n\n**Syntax**: `auto | none`\n\n**Initial value**: `auto`")
    String msHighContrastAdjust() default "	";

    /**
     * The **`-ms-hyphenate-limit-chars`** CSS property is a Microsoft extension that specifies one to three values indicating the minimum number of characters in a hyphenated word. If the word does not meet the required minimum number of characters in the word, before the hyphen, or after the hyphen, then the word is not hyphenated.\n\n**Syntax**: `auto | <integer>{1,3}`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsHyphenateLimitChars"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MsHyphenateLimitChars
     */
    @Const({"auto"})
    @Schema(title = "The **`-ms-hyphenate-limit-chars`** CSS property is a Microsoft extension that specifies one to three values indicating the minimum number of characters in a hyphenated word. If the word does not meet the required minimum number of characters in the word, before the hyphen, or after the hyphen, then the word is not hyphenated.\n\n**Syntax**: `auto | <integer>{1,3}`\n\n**Initial value**: `auto`")
    String msHyphenateLimitChars() default "	";

    /**
     * The **`-ms-hyphenate-limit-lines`** CSS property is a Microsoft extension specifying the maximum number of consecutive lines in an element that may be ended with a hyphenated word.\n\n**Syntax**: `no-limit | <integer>`\n\n**Initial value**: `no-limit`
     *
     * 参考定义: "#/definitions/Property.MsHyphenateLimitLines"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"no-limit"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_MsHyphenateLimitLines
     */
    @Const({"no-limit"})
    @Schema(title = "The **`-ms-hyphenate-limit-lines`** CSS property is a Microsoft extension specifying the maximum number of consecutive lines in an element that may be ended with a hyphenated word.\n\n**Syntax**: `no-limit | <integer>`\n\n**Initial value**: `no-limit`")
    String msHyphenateLimitLines() default "	";

    /**
     * The `**-ms-hyphenate-limit-zone**` CSS property is a Microsoft extension specifying the width of the hyphenation zone.\n\n**Syntax**: `<percentage> | <length>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MsHyphenateLimitZone%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MsHyphenateLimitZone
     */

    @Schema(title = "The `**-ms-hyphenate-limit-zone**` CSS property is a Microsoft extension specifying the width of the hyphenation zone.\n\n**Syntax**: `<percentage> | <length>`\n\n**Initial value**: `0`")
    String msHyphenateLimitZone() default "	";

    /**
     * The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`
     *
     * 参考定义: "#/definitions/Property.Hyphens"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"manual"},{"type":"string","const":"none"}]
     *
     * @see Property_Hyphens
     */
    @Const({"auto", "manual", "none"})
    @Schema(title = "The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`")
    String msHyphens() default "	";

    /**
     * The **`-ms-ime-align`** CSS property is a Microsoft extension aligning the Input Method Editor (IME) candidate window box relative to the element on which the IME composition is active. The extension is implemented in Microsoft Edge and Internet Explorer 11.\n\n**Syntax**: `auto | after`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsImeAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"after"},{"type":"string","const":"auto"}]
     *
     * @see Property_MsImeAlign
     */
    @Const({"after", "auto"})
    @Schema(title = "The **`-ms-ime-align`** CSS property is a Microsoft extension aligning the Input Method Editor (IME) candidate window box relative to the element on which the IME composition is active. The extension is implemented in Microsoft Edge and Internet Explorer 11.\n\n**Syntax**: `auto | after`\n\n**Initial value**: `auto`")
    String msImeAlign() default "	";

    /**
     * The CSS **`justify-self`** property sets the way a box is justified inside its alignment container along the appropriate axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ]`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.JustifySelf"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_JustifySelf
     */
    @Const({"auto", "baseline", "left", "normal", "right", "stretch"})
    @Schema(title = "The CSS **`justify-self`** property sets the way a box is justified inside its alignment container along the appropriate axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ]`\n\n**Initial value**: `auto`")
    String msJustifySelf() default "	";

    /**
     * The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.LineBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"auto"},{"type":"string","const":"loose"},{"type":"string","const":"normal"},{"type":"string","const":"strict"}]
     *
     * @see Property_LineBreak
     */
    @Const({"anywhere", "auto", "loose", "normal", "strict"})
    @Schema(title = "The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`")
    String msLineBreak() default "	";

    /**
     * The **`order`** CSS property sets the order to lay out an item in a flex or grid container. Items in a container are sorted by ascending `order` value and then by their source code order.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.Order"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_Order
     */

    @Schema(title = "The **`order`** CSS property sets the order to lay out an item in a flex or grid container. Items in a container are sorted by ascending `order` value and then by their source code order.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `0`")
    String msOrder() default "	";

    /**
     * The **`-ms-overflow-style`** CSS property is a Microsoft extension controlling the behavior of scrollbars when the content of an element overflows.\n\n**Syntax**: `auto | none | scrollbar | -ms-autohiding-scrollbar`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsOverflowStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-ms-autohiding-scrollbar"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string","const":"scrollbar"}]
     *
     * @see Property_MsOverflowStyle
     */
    @Const({"-ms-autohiding-scrollbar", "auto", "none", "scrollbar"})
    @Schema(title = "The **`-ms-overflow-style`** CSS property is a Microsoft extension controlling the behavior of scrollbars when the content of an element overflows.\n\n**Syntax**: `auto | none | scrollbar | -ms-autohiding-scrollbar`\n\n**Initial value**: `auto`")
    String msOverflowStyle() default "	";

    /**
     * The **`overflow-x`** CSS property sets what shows when content overflows a block-level element's left and right edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`
     *
     * 参考定义: "#/definitions/Property.OverflowX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-hidden-unscrollable"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
     *
     * @see Property_OverflowX
     */
    @Const({"-moz-hidden-unscrollable", "auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "The **`overflow-x`** CSS property sets what shows when content overflows a block-level element's left and right edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`")
    String msOverflowX() default "	";

    /**
     * The **`overflow-y`** CSS property sets what shows when content overflows a block-level element's top and bottom edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`
     *
     * 参考定义: "#/definitions/Property.OverflowY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-hidden-unscrollable"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
     *
     * @see Property_OverflowY
     */
    @Const({"-moz-hidden-unscrollable", "auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "The **`overflow-y`** CSS property sets what shows when content overflows a block-level element's top and bottom edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`")
    String msOverflowY() default "	";

    /**
     * The `**-ms-scroll-chaining**` CSS property is a Microsoft extension that specifies the scrolling behavior that occurs when a user hits the scroll limit during a manipulation.\n\n**Syntax**: `chained | none`\n\n**Initial value**: `chained`
     *
     * 参考定义: "#/definitions/Property.MsScrollChaining"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"chained"},{"type":"string","const":"none"}]
     *
     * @see Property_MsScrollChaining
     */
    @Const({"chained", "none"})
    @Schema(title = "The `**-ms-scroll-chaining**` CSS property is a Microsoft extension that specifies the scrolling behavior that occurs when a user hits the scroll limit during a manipulation.\n\n**Syntax**: `chained | none`\n\n**Initial value**: `chained`")
    String msScrollChaining() default "	";

    /**
     * The `**-ms-scroll-limit-x-max**` CSS property is a Microsoft extension that specifies the maximum value for the `Element.scrollLeft` property.\n\n**Syntax**: `auto | <length>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsScrollLimitXMax%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MsScrollLimitXMax
     */
    @Const({"auto"})
    @Schema(title = "The `**-ms-scroll-limit-x-max**` CSS property is a Microsoft extension that specifies the maximum value for the `Element.scrollLeft` property.\n\n**Syntax**: `auto | <length>`\n\n**Initial value**: `auto`")
    String msScrollLimitXMax() default "	";

    /**
     * The **`-ms-scroll-limit-x-min`** CSS property is a Microsoft extension that specifies the minimum value for the `Element.scrollLeft` property.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MsScrollLimitXMin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MsScrollLimitXMin
     */

    @Schema(title = "The **`-ms-scroll-limit-x-min`** CSS property is a Microsoft extension that specifies the minimum value for the `Element.scrollLeft` property.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`")
    String msScrollLimitXMin() default "	";

    /**
     * The **`-ms-scroll-limit-y-max`** CSS property is a Microsoft extension that specifies the maximum value for the `Element.scrollTop` property.\n\n**Syntax**: `auto | <length>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsScrollLimitYMax%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MsScrollLimitYMax
     */
    @Const({"auto"})
    @Schema(title = "The **`-ms-scroll-limit-y-max`** CSS property is a Microsoft extension that specifies the maximum value for the `Element.scrollTop` property.\n\n**Syntax**: `auto | <length>`\n\n**Initial value**: `auto`")
    String msScrollLimitYMax() default "	";

    /**
     * The **`-ms-scroll-limit-y-min`** CSS property is a Microsoft extension that specifies the minimum value for the `Element.scrollTop` property.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MsScrollLimitYMin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MsScrollLimitYMin
     */

    @Schema(title = "The **`-ms-scroll-limit-y-min`** CSS property is a Microsoft extension that specifies the minimum value for the `Element.scrollTop` property.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`")
    String msScrollLimitYMin() default "	";

    /**
     * The **`-ms-scroll-rails`** CSS property is a Microsoft extension that specifies whether scrolling locks to the primary axis of motion.\n\n**Syntax**: `none | railed`\n\n**Initial value**: `railed`
     *
     * 参考定义: "#/definitions/Property.MsScrollRails"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"railed"}]
     *
     * @see Property_MsScrollRails
     */
    @Const({"none", "railed"})
    @Schema(title = "The **`-ms-scroll-rails`** CSS property is a Microsoft extension that specifies whether scrolling locks to the primary axis of motion.\n\n**Syntax**: `none | railed`\n\n**Initial value**: `railed`")
    String msScrollRails() default "	";

    /**
     * The **`-ms-scroll-snap-points-x`** CSS property is a Microsoft extension that specifies where snap-points will be located along the x-axis.\n\n**Syntax**: `snapInterval( <length-percentage>, <length-percentage> ) | snapList( <length-percentage># )`\n\n**Initial value**: `snapInterval(0px, 100%)`
     *
     * 参考定义: "#/definitions/Property.MsScrollSnapPointsX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsScrollSnapPointsX
     */

    @Schema(title = "The **`-ms-scroll-snap-points-x`** CSS property is a Microsoft extension that specifies where snap-points will be located along the x-axis.\n\n**Syntax**: `snapInterval( <length-percentage>, <length-percentage> ) | snapList( <length-percentage># )`\n\n**Initial value**: `snapInterval(0px, 100%)`")
    String msScrollSnapPointsX() default "	";

    /**
     * The **`-ms-scroll-snap-points-y`** CSS property is a Microsoft extension that specifies where snap-points will be located along the y-axis.\n\n**Syntax**: `snapInterval( <length-percentage>, <length-percentage> ) | snapList( <length-percentage># )`\n\n**Initial value**: `snapInterval(0px, 100%)`
     *
     * 参考定义: "#/definitions/Property.MsScrollSnapPointsY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_MsScrollSnapPointsY
     */

    @Schema(title = "The **`-ms-scroll-snap-points-y`** CSS property is a Microsoft extension that specifies where snap-points will be located along the y-axis.\n\n**Syntax**: `snapInterval( <length-percentage>, <length-percentage> ) | snapList( <length-percentage># )`\n\n**Initial value**: `snapInterval(0px, 100%)`")
    String msScrollSnapPointsY() default "	";

    /**
     * The **`scroll-snap-type`** CSS property sets how strictly snap points are enforced on the scroll container in case there is one.\n\n**Syntax**: `none | proximity | mandatory`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsScrollSnapType"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mandatory"},{"type":"string","const":"none"},{"type":"string","const":"proximity"}]
     *
     * @see Property_MsScrollSnapType
     */
    @Const({"mandatory", "none", "proximity"})
    @Schema(title = "The **`scroll-snap-type`** CSS property sets how strictly snap points are enforced on the scroll container in case there is one.\n\n**Syntax**: `none | proximity | mandatory`\n\n**Initial value**: `none`")
    String msScrollSnapType() default "	";

    /**
     * The **`-ms-scroll-translation`** CSS property is a Microsoft extension that specifies whether vertical-to-horizontal scroll wheel translation occurs on the specified element.\n\n**Syntax**: `none | vertical-to-horizontal`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsScrollTranslation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"vertical-to-horizontal"}]
     *
     * @see Property_MsScrollTranslation
     */
    @Const({"none", "vertical-to-horizontal"})
    @Schema(title = "The **`-ms-scroll-translation`** CSS property is a Microsoft extension that specifies whether vertical-to-horizontal scroll wheel translation occurs on the specified element.\n\n**Syntax**: `none | vertical-to-horizontal`\n\n**Initial value**: `none`")
    String msScrollTranslation() default "	";

    /**
     * The **`-ms-scrollbar-3dlight-color`** CSS property is a Microsoft extension specifying the color of the top and left edges of the scroll box and scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: depends on user agent
     *
     * 参考定义: "#/definitions/Property.MsScrollbar3dlightColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbar3dlightColor
     */

    @Schema(title = "The **`-ms-scrollbar-3dlight-color`** CSS property is a Microsoft extension specifying the color of the top and left edges of the scroll box and scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: depends on user agent")
    String msScrollbar3dlightColor() default "	";

    /**
     * The **`-ms-scrollbar-arrow-color`** CSS property is a Microsoft extension that specifies the color of the arrow elements of a scroll arrow.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ButtonText`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarArrowColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarArrowColor
     */

    @Schema(title = "The **`-ms-scrollbar-arrow-color`** CSS property is a Microsoft extension that specifies the color of the arrow elements of a scroll arrow.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ButtonText`")
    String msScrollbarArrowColor() default "	";

    /**
     * The `**-ms-scrollbar-base-color**` CSS property is a Microsoft extension that specifies the base color of the main elements of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: depends on user agent
     *
     * 参考定义: "#/definitions/Property.MsScrollbarBaseColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarBaseColor
     */

    @Schema(title = "The `**-ms-scrollbar-base-color**` CSS property is a Microsoft extension that specifies the base color of the main elements of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: depends on user agent")
    String msScrollbarBaseColor() default "	";

    /**
     * The **`-ms-scrollbar-darkshadow-color`** CSS property is a Microsoft extension that specifies the color of a scroll bar's gutter.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDDarkShadow`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarDarkshadowColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarDarkshadowColor
     */

    @Schema(title = "The **`-ms-scrollbar-darkshadow-color`** CSS property is a Microsoft extension that specifies the color of a scroll bar's gutter.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDDarkShadow`")
    String msScrollbarDarkshadowColor() default "	";

    /**
     * The `**-ms-scrollbar-face-color**` CSS property is a Microsoft extension that specifies the color of the scroll box and scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDFace`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarFaceColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarFaceColor
     */

    @Schema(title = "The `**-ms-scrollbar-face-color**` CSS property is a Microsoft extension that specifies the color of the scroll box and scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDFace`")
    String msScrollbarFaceColor() default "	";

    /**
     * The `**-ms-scrollbar-highlight-color**` CSS property is a Microsoft extension that specifies the color of the slider tray, the top and left edges of the scroll box, and the scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDHighlight`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarHighlightColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarHighlightColor
     */

    @Schema(title = "The `**-ms-scrollbar-highlight-color**` CSS property is a Microsoft extension that specifies the color of the slider tray, the top and left edges of the scroll box, and the scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDHighlight`")
    String msScrollbarHighlightColor() default "	";

    /**
     * The **`-ms-scrollbar-shadow-color`** CSS property is a Microsoft extension that specifies the color of the bottom and right edges of the scroll box and scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDDarkShadow`
     *
     * 参考定义: "#/definitions/Property.MsScrollbarShadowColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_MsScrollbarShadowColor
     */

    @Schema(title = "The **`-ms-scrollbar-shadow-color`** CSS property is a Microsoft extension that specifies the color of the bottom and right edges of the scroll box and scroll arrows of a scroll bar.\n\n**Syntax**: `<color>`\n\n**Initial value**: `ThreeDDarkShadow`")
    String msScrollbarShadowColor() default "	";

    /**
     * The **`-ms-text-autospace`** CSS property is a Microsoft extension that specifies the autospacing and narrow space width adjustment of text.\n\n**Syntax**: `none | ideograph-alpha | ideograph-numeric | ideograph-parenthesis | ideograph-space`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MsTextAutospace"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"ideograph-alpha"},{"type":"string","const":"ideograph-numeric"},{"type":"string","const":"ideograph-parenthesis"},{"type":"string","const":"ideograph-space"},{"type":"string","const":"none"}]
     *
     * @see Property_MsTextAutospace
     */
    @Const({"ideograph-alpha", "ideograph-numeric", "ideograph-parenthesis", "ideograph-space", "none"})
    @Schema(title = "The **`-ms-text-autospace`** CSS property is a Microsoft extension that specifies the autospacing and narrow space width adjustment of text.\n\n**Syntax**: `none | ideograph-alpha | ideograph-numeric | ideograph-parenthesis | ideograph-space`\n\n**Initial value**: `none`")
    String msTextAutospace() default "	";

    /**
     * The **`text-combine-upright`** CSS property sets the combination of characters into the space of a single character. If the combined text is wider than 1em, the user agent must fit the contents within 1em. The resulting composition is treated as a single upright glyph for layout and decoration. This property only has an effect in vertical writing modes.\n\n**Syntax**: `none | all | [ digits <integer>? ]`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.TextCombineUpright"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextCombineUpright
     */
    @Const({"all", "none"})
    @Schema(title = "The **`text-combine-upright`** CSS property sets the combination of characters into the space of a single character. If the combined text is wider than 1em, the user agent must fit the contents within 1em. The resulting composition is treated as a single upright glyph for layout and decoration. This property only has an effect in vertical writing modes.\n\n**Syntax**: `none | all | [ digits <integer>? ]`\n\n**Initial value**: `none`")
    String msTextCombineHorizontal() default "	";

    /**
     * The **`text-overflow`** CSS property sets how hidden overflow content is signaled to users. It can be clipped, display an ellipsis ('`…`'), or display a custom string.\n\n**Syntax**: `[ clip | ellipsis | <string> ]{1,2}`\n\n**Initial value**: `clip`
     *
     * 参考定义: "#/definitions/Property.TextOverflow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clip"},{"type":"string","const":"ellipsis"},{"type":"string"}]
     *
     * @see Property_TextOverflow
     */
    @Const({"clip", "ellipsis"})
    @Schema(title = "The **`text-overflow`** CSS property sets how hidden overflow content is signaled to users. It can be clipped, display an ellipsis ('`…`'), or display a custom string.\n\n**Syntax**: `[ clip | ellipsis | <string> ]{1,2}`\n\n**Initial value**: `clip`")
    String msTextOverflow() default "	";

    /**
     * The **`touch-action`** CSS property sets how an element's region can be manipulated by a touchscreen user (for example, by zooming features built into the browser).\n\n**Syntax**: `auto | none | [ [ pan-x | pan-left | pan-right ] || [ pan-y | pan-up | pan-down ] || pinch-zoom ] | manipulation`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.TouchAction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-ms-manipulation"},{"type":"string","const":"-ms-none"},{"type":"string","const":"-ms-pinch-zoom"},{"type":"string","const":"auto"},{"type":"string","const":"manipulation"},{"type":"string","const":"none"},{"type":"string","const":"pan-down"},{"type":"string","const":"pan-left"},{"type":"string","const":"pan-right"},{"type":"string","const":"pan-up"},{"type":"string","const":"pan-x"},{"type":"string","const":"pan-y"},{"type":"string","const":"pinch-zoom"},{"type":"string"}]
     *
     * @see Property_TouchAction
     */
    @Const({"-ms-manipulation", "-ms-none", "-ms-pinch-zoom", "auto", "manipulation", "none", "pan-down", "pan-left", "pan-right", "pan-up", "pan-x", "pan-y", "pinch-zoom"})
    @Schema(title = "The **`touch-action`** CSS property sets how an element's region can be manipulated by a touchscreen user (for example, by zooming features built into the browser).\n\n**Syntax**: `auto | none | [ [ pan-x | pan-left | pan-right ] || [ pan-y | pan-up | pan-down ] || pinch-zoom ] | manipulation`\n\n**Initial value**: `auto`")
    String msTouchAction() default "	";

    /**
     * The **`-ms-touch-select`** CSS property is a Microsoft extension that toggles the gripper visual elements that enable touch text selection.\n\n**Syntax**: `grippers | none`\n\n**Initial value**: `grippers`
     *
     * 参考定义: "#/definitions/Property.MsTouchSelect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"grippers"},{"type":"string","const":"none"}]
     *
     * @see Property_MsTouchSelect
     */
    @Const({"grippers", "none"})
    @Schema(title = "The **`-ms-touch-select`** CSS property is a Microsoft extension that toggles the gripper visual elements that enable touch text selection.\n\n**Syntax**: `grippers | none`\n\n**Initial value**: `grippers`")
    String msTouchSelect() default "	";

    /**
     * The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.Transform"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Transform
     */
    @Const({"none"})
    @Schema(title = "The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`")
    String msTransform() default "	";

    /**
     * The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`
     *
     * 参考定义: "#/definitions/Property.TransformOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
     *
     * @see Property_TransformOrigin
     */
    @Const({"bottom", "center", "left", "right", "top"})
    @Schema(title = "The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`")
    String msTransformOrigin() default "	";

    /**
     * The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDelay
     */

    @Schema(title = "The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String msTransitionDelay() default "	";

    /**
     * The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDuration
     */

    @Schema(title = "The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String msTransitionDuration() default "	";

    /**
     * The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all
     *
     * 参考定义: "#/definitions/Property.TransitionProperty"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TransitionProperty
     */
    @Const({"all", "none"})
    @Schema(title = "The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all")
    String msTransitionProperty() default "	";

    /**
     * The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.TransitionTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_TransitionTimingFunction
     */

    @Schema(title = "The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String msTransitionTimingFunction() default "	";

    /**
     * The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `none | element | text`\n\n**Initial value**: `text`
     *
     * 参考定义: "#/definitions/Property.MsUserSelect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"element"},{"type":"string","const":"none"},{"type":"string","const":"text"}]
     *
     * @see Property_MsUserSelect
     */
    @Const({"element", "none", "text"})
    @Schema(title = "The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `none | element | text`\n\n**Initial value**: `text`")
    String msUserSelect() default "	";

    /**
     * The **`word-break`** CSS property sets whether line breaks appear wherever the text would otherwise overflow its content box.\n\n**Syntax**: `normal | break-all | keep-all | break-word`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.WordBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"break-all"},{"type":"string","const":"break-word"},{"type":"string","const":"keep-all"},{"type":"string","const":"normal"}]
     *
     * @see Property_WordBreak
     */
    @Const({"break-all", "break-word", "keep-all", "normal"})
    @Schema(title = "The **`word-break`** CSS property sets whether line breaks appear wherever the text would otherwise overflow its content box.\n\n**Syntax**: `normal | break-all | keep-all | break-word`\n\n**Initial value**: `normal`")
    String msWordBreak() default "	";

    /**
     * The **`-ms-wrap-flow`** CSS property is a Microsoft extension that specifies how exclusions impact inline content within block-level elements.\n\n**Syntax**: `auto | both | start | end | maximum | clear`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MsWrapFlow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"both"},{"type":"string","const":"clear"},{"type":"string","const":"end"},{"type":"string","const":"maximum"},{"type":"string","const":"start"}]
     *
     * @see Property_MsWrapFlow
     */
    @Const({"auto", "both", "clear", "end", "maximum", "start"})
    @Schema(title = "The **`-ms-wrap-flow`** CSS property is a Microsoft extension that specifies how exclusions impact inline content within block-level elements.\n\n**Syntax**: `auto | both | start | end | maximum | clear`\n\n**Initial value**: `auto`")
    String msWrapFlow() default "	";

    /**
     * The **`-ms-wrap-margin`** CSS property is a Microsoft extension that specifies a margin that offsets the inner wrap shape from other shapes.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MsWrapMargin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MsWrapMargin
     */

    @Schema(title = "The **`-ms-wrap-margin`** CSS property is a Microsoft extension that specifies a margin that offsets the inner wrap shape from other shapes.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`")
    String msWrapMargin() default "	";

    /**
     * The **`-ms-wrap-through`** CSS property is a Microsoft extension that specifies how content should wrap around an exclusion element.\n\n**Syntax**: `wrap | none`\n\n**Initial value**: `wrap`
     *
     * 参考定义: "#/definitions/Property.MsWrapThrough"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"wrap"}]
     *
     * @see Property_MsWrapThrough
     */
    @Const({"none", "wrap"})
    @Schema(title = "The **`-ms-wrap-through`** CSS property is a Microsoft extension that specifies how content should wrap around an exclusion element.\n\n**Syntax**: `wrap | none`\n\n**Initial value**: `wrap`")
    String msWrapThrough() default "	";

    /**
     * The **`writing-mode`** CSS property sets whether lines of text are laid out horizontally or vertically, as well as the direction in which blocks progress. When set for an entire document, it should be set on the root element (`html` element for HTML documents).\n\n**Syntax**: `horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr`\n\n**Initial value**: `horizontal-tb`
     *
     * 参考定义: "#/definitions/Property.WritingMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"horizontal-tb"},{"type":"string","const":"sideways-lr"},{"type":"string","const":"sideways-rl"},{"type":"string","const":"vertical-lr"},{"type":"string","const":"vertical-rl"}]
     *
     * @see Property_WritingMode
     */
    @Const({"horizontal-tb", "sideways-lr", "sideways-rl", "vertical-lr", "vertical-rl"})
    @Schema(title = "The **`writing-mode`** CSS property sets whether lines of text are laid out horizontally or vertically, as well as the direction in which blocks progress. When set for an entire document, it should be set on the root element (`html` element for HTML documents).\n\n**Syntax**: `horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr`\n\n**Initial value**: `horizontal-tb`")
    String msWritingMode() default "	";

    /**
     * The CSS **`align-content`** property sets the distribution of space between and around content items along a flexbox's cross-axis or a grid's block axis.\n\n**Syntax**: `normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position>`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.AlignContent"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_AlignContent
     */
    @Const({"baseline", "normal"})
    @Schema(title = "The CSS **`align-content`** property sets the distribution of space between and around content items along a flexbox's cross-axis or a grid's block axis.\n\n**Syntax**: `normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position>`\n\n**Initial value**: `normal`")
    String WebkitAlignContent() default "	";

    /**
     * The CSS **`align-items`** property sets the `align-self` value on all direct children as a group. In Flexbox, it controls the alignment of items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis within their grid area.\n\n**Syntax**: `normal | stretch | <baseline-position> | [ <overflow-position>? <self-position> ]`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.AlignItems"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_AlignItems
     */
    @Const({"baseline", "normal", "stretch"})
    @Schema(title = "The CSS **`align-items`** property sets the `align-self` value on all direct children as a group. In Flexbox, it controls the alignment of items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis within their grid area.\n\n**Syntax**: `normal | stretch | <baseline-position> | [ <overflow-position>? <self-position> ]`\n\n**Initial value**: `normal`")
    String WebkitAlignItems() default "	";

    /**
     * The **`align-self`** CSS property overrides a grid or flex item's `align-items` value. In Grid, it aligns the item inside the grid area. In Flexbox, it aligns the item on the cross axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? <self-position>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.AlignSelf"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_AlignSelf
     */
    @Const({"auto", "baseline", "normal", "stretch"})
    @Schema(title = "The **`align-self`** CSS property overrides a grid or flex item's `align-items` value. In Grid, it aligns the item inside the grid area. In Flexbox, it aligns the item on the cross axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? <self-position>`\n\n**Initial value**: `auto`")
    String WebkitAlignSelf() default "	";

    /**
     * The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.AnimationDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDelay
     */

    @Schema(title = "The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String WebkitAnimationDelay() default "	";

    /**
     * The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.AnimationDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationDirection"},{"type":"string"}]
     *
     * @see Property_AnimationDirection
     */

    @Schema(title = "The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`")
    String WebkitAnimationDirection() default "	";

    /**
     * The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.AnimationDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDuration
     */

    @Schema(title = "The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String WebkitAnimationDuration() default "	";

    /**
     * The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.AnimationFillMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationFillMode"},{"type":"string"}]
     *
     * @see Property_AnimationFillMode
     */

    @Schema(title = "The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`")
    String WebkitAnimationFillMode() default "	";

    /**
     * The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.AnimationIterationCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"infinite"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_AnimationIterationCount
     */
    @Const({"infinite"})
    @Schema(title = "The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`")
    String WebkitAnimationIterationCount() default "	";

    /**
     * The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.AnimationName"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_AnimationName
     */
    @Const({"none"})
    @Schema(title = "The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`")
    String WebkitAnimationName() default "	";

    /**
     * The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`
     *
     * 参考定义: "#/definitions/Property.AnimationPlayState"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"string"}]
     *
     * @see Property_AnimationPlayState
     */
    @Const({"paused", "running"})
    @Schema(title = "The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`")
    String WebkitAnimationPlayState() default "	";

    /**
     * The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.AnimationTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_AnimationTimingFunction
     */

    @Schema(title = "The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String WebkitAnimationTimingFunction() default "	";

    /**
     * The `**appearance**` CSS property is used to display an element using platform-native styling, based on the operating system's theme. The **`-moz-appearance`** and **`-webkit-appearance`** properties are non-standard versions of this property, used (respectively) by Gecko (Firefox) and by WebKit-based (e.g., Safari) and Blink-based (e.g., Chrome, Opera) browsers to achieve the same thing. Note that Firefox and Edge also support **`-webkit-appearance`**, for compatibility reasons.\n\n**Syntax**: `none | button | button-bevel | caret | checkbox | default-button | inner-spin-button | listbox | listitem | media-controls-background | media-controls-fullscreen-background | media-current-time-display | media-enter-fullscreen-button | media-exit-fullscreen-button | media-fullscreen-button | media-mute-button | media-overlay-play-button | media-play-button | media-seek-back-button | media-seek-forward-button | media-slider | media-sliderthumb | media-time-remaining-display | media-toggle-closed-captions-button | media-volume-slider | media-volume-slider-container | media-volume-sliderthumb | menulist | menulist-button | menulist-text | menulist-textfield | meter | progress-bar | progress-bar-value | push-button | radio | searchfield | searchfield-cancel-button | searchfield-decoration | searchfield-results-button | searchfield-results-decoration | slider-horizontal | slider-vertical | sliderthumb-horizontal | sliderthumb-vertical | square-button | textarea | textfield | -apple-pay-button`\n\n**Initial value**: `none` (but this value is overridden in the user agent CSS)
     *
     * 参考定义: "#/definitions/Property.WebkitAppearance"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-apple-pay-button"},{"type":"string","const":"button"},{"type":"string","const":"button-bevel"},{"type":"string","const":"caret"},{"type":"string","const":"checkbox"},{"type":"string","const":"default-button"},{"type":"string","const":"inner-spin-button"},{"type":"string","const":"listbox"},{"type":"string","const":"listitem"},{"type":"string","const":"media-controls-background"},{"type":"string","const":"media-controls-fullscreen-background"},{"type":"string","const":"media-current-time-display"},{"type":"string","const":"media-enter-fullscreen-button"},{"type":"string","const":"media-exit-fullscreen-button"},{"type":"string","const":"media-fullscreen-button"},{"type":"string","const":"media-mute-button"},{"type":"string","const":"media-overlay-play-button"},{"type":"string","const":"media-play-button"},{"type":"string","const":"media-seek-back-button"},{"type":"string","const":"media-seek-forward-button"},{"type":"string","const":"media-slider"},{"type":"string","const":"media-sliderthumb"},{"type":"string","const":"media-time-remaining-display"},{"type":"string","const":"media-toggle-closed-captions-button"},{"type":"string","const":"media-volume-slider"},{"type":"string","const":"media-volume-slider-container"},{"type":"string","const":"media-volume-sliderthumb"},{"type":"string","const":"menulist"},{"type":"string","const":"menulist-button"},{"type":"string","const":"menulist-text"},{"type":"string","const":"menulist-textfield"},{"type":"string","const":"meter"},{"type":"string","const":"none"},{"type":"string","const":"progress-bar"},{"type":"string","const":"progress-bar-value"},{"type":"string","const":"push-button"},{"type":"string","const":"radio"},{"type":"string","const":"searchfield"},{"type":"string","const":"searchfield-cancel-button"},{"type":"string","const":"searchfield-decoration"},{"type":"string","const":"searchfield-results-button"},{"type":"string","const":"searchfield-results-decoration"},{"type":"string","const":"slider-horizontal"},{"type":"string","const":"slider-vertical"},{"type":"string","const":"sliderthumb-horizontal"},{"type":"string","const":"sliderthumb-vertical"},{"type":"string","const":"square-button"},{"type":"string","const":"textarea"},{"type":"string","const":"textfield"}]
     *
     * @see Property_WebkitAppearance
     */
    @Const({"-apple-pay-button", "button", "button-bevel", "caret", "checkbox", "default-button", "inner-spin-button", "listbox", "listitem", "media-controls-background", "media-controls-fullscreen-background", "media-current-time-display", "media-enter-fullscreen-button", "media-exit-fullscreen-button", "media-fullscreen-button", "media-mute-button", "media-overlay-play-button", "media-play-button", "media-seek-back-button", "media-seek-forward-button", "media-slider", "media-sliderthumb", "media-time-remaining-display", "media-toggle-closed-captions-button", "media-volume-slider", "media-volume-slider-container", "media-volume-sliderthumb", "menulist", "menulist-button", "menulist-text", "menulist-textfield", "meter", "none", "progress-bar", "progress-bar-value", "push-button", "radio", "searchfield", "searchfield-cancel-button", "searchfield-decoration", "searchfield-results-button", "searchfield-results-decoration", "slider-horizontal", "slider-vertical", "sliderthumb-horizontal", "sliderthumb-vertical", "square-button", "textarea", "textfield"})
    @Schema(title = "The `**appearance**` CSS property is used to display an element using platform-native styling, based on the operating system's theme. The **`-moz-appearance`** and **`-webkit-appearance`** properties are non-standard versions of this property, used (respectively) by Gecko (Firefox) and by WebKit-based (e.g., Safari) and Blink-based (e.g., Chrome, Opera) browsers to achieve the same thing. Note that Firefox and Edge also support **`-webkit-appearance`**, for compatibility reasons.\n\n**Syntax**: `none | button | button-bevel | caret | checkbox | default-button | inner-spin-button | listbox | listitem | media-controls-background | media-controls-fullscreen-background | media-current-time-display | media-enter-fullscreen-button | media-exit-fullscreen-button | media-fullscreen-button | media-mute-button | media-overlay-play-button | media-play-button | media-seek-back-button | media-seek-forward-button | media-slider | media-sliderthumb | media-time-remaining-display | media-toggle-closed-captions-button | media-volume-slider | media-volume-slider-container | media-volume-sliderthumb | menulist | menulist-button | menulist-text | menulist-textfield | meter | progress-bar | progress-bar-value | push-button | radio | searchfield | searchfield-cancel-button | searchfield-decoration | searchfield-results-button | searchfield-results-decoration | slider-horizontal | slider-vertical | sliderthumb-horizontal | sliderthumb-vertical | square-button | textarea | textfield | -apple-pay-button`\n\n**Initial value**: `none` (but this value is overridden in the user agent CSS)")
    String WebkitAppearance() default "	";

    /**
     * The **`backdrop-filter`** CSS property lets you apply graphical effects such as blurring or color shifting to the area behind an element. Because it applies to everything _behind_ the element, to see the effect you must make the element or its background at least partially transparent.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.BackdropFilter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BackdropFilter
     */
    @Const({"none"})
    @Schema(title = "The **`backdrop-filter`** CSS property lets you apply graphical effects such as blurring or color shifting to the area behind an element. Because it applies to everything _behind_ the element, to see the effect you must make the element or its background at least partially transparent.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`")
    String WebkitBackdropFilter() default "	";

    /**
     * The **`backface-visibility`** CSS property sets whether the back face of an element is visible when turned towards the user.\n\n**Syntax**: `visible | hidden`\n\n**Initial value**: `visible`
     *
     * 参考定义: "#/definitions/Property.BackfaceVisibility"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"hidden"},{"type":"string","const":"visible"}]
     *
     * @see Property_BackfaceVisibility
     */
    @Const({"hidden", "visible"})
    @Schema(title = "The **`backface-visibility`** CSS property sets whether the back face of an element is visible when turned towards the user.\n\n**Syntax**: `visible | hidden`\n\n**Initial value**: `visible`")
    String WebkitBackfaceVisibility() default "	";

    /**
     * The **`background-clip`** CSS property sets whether an element's background extends underneath its border box, padding box, or content box.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `border-box`
     *
     * 参考定义: "#/definitions/Property.BackgroundClip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string"}]
     *
     * @see Property_BackgroundClip
     */

    @Schema(title = "The **`background-clip`** CSS property sets whether an element's background extends underneath its border box, padding box, or content box.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `border-box`")
    String WebkitBackgroundClip() default "	";

    /**
     * The **`background-origin`** CSS property sets the background's origin: from the border start, inside the border, or inside the padding.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `padding-box`
     *
     * 参考定义: "#/definitions/Property.BackgroundOrigin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string"}]
     *
     * @see Property_BackgroundOrigin
     */

    @Schema(title = "The **`background-origin`** CSS property sets the background's origin: from the border start, inside the border, or inside the padding.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `padding-box`")
    String WebkitBackgroundOrigin() default "	";

    /**
     * The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`
     *
     * 参考定义: "#/definitions/Property.BackgroundSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgSize%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_BackgroundSize
     */

    @Schema(title = "The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`")
    String WebkitBackgroundSize() default "	";

    /**
     * **Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.WebkitBorderBeforeColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_WebkitBorderBeforeColor
     */

    @Schema(title = "**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String WebkitBorderBeforeColor() default "	";

    /**
     * **Syntax**: `<'border-style'>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.WebkitBorderBeforeStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string"}]
     *
     * @see Property_WebkitBorderBeforeStyle
     */

    @Schema(title = "**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`")
    String WebkitBorderBeforeStyle() default "	";

    /**
     * **Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`
     *
     * 参考定义: "#/definitions/Property.WebkitBorderBeforeWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_WebkitBorderBeforeWidth
     */

    @Schema(title = "**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`")
    String WebkitBorderBeforeWidth() default "	";

    /**
     * The **`border-bottom-left-radius`** CSS property rounds the bottom-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderBottomLeftRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderBottomLeftRadius
     */

    @Schema(title = "The **`border-bottom-left-radius`** CSS property rounds the bottom-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String WebkitBorderBottomLeftRadius() default "	";

    /**
     * The **`border-bottom-right-radius`** CSS property rounds the bottom-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderBottomRightRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderBottomRightRadius
     */

    @Schema(title = "The **`border-bottom-right-radius`** CSS property rounds the bottom-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String WebkitBorderBottomRightRadius() default "	";

    /**
     * The **`border-image-slice`** CSS property divides the image specified by `border-image-source` into regions. These regions form the components of an element's border image.\n\n**Syntax**: `<number-percentage>{1,4} && fill?`\n\n**Initial value**: `100%`
     *
     * 参考定义: "#/definitions/Property.BorderImageSlice"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImageSlice
     */

    @Schema(title = "The **`border-image-slice`** CSS property divides the image specified by `border-image-source` into regions. These regions form the components of an element's border image.\n\n**Syntax**: `<number-percentage>{1,4} && fill?`\n\n**Initial value**: `100%`")
    String WebkitBorderImageSlice() default "	";

    /**
     * The **`border-top-left-radius`** CSS property rounds the top-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderTopLeftRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderTopLeftRadius
     */

    @Schema(title = "The **`border-top-left-radius`** CSS property rounds the top-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String WebkitBorderTopLeftRadius() default "	";

    /**
     * The **`border-top-right-radius`** CSS property rounds the top-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.BorderTopRightRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderTopRightRadius
     */

    @Schema(title = "The **`border-top-right-radius`** CSS property rounds the top-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`")
    String WebkitBorderTopRightRadius() default "	";

    /**
     * The **`box-decoration-break`** CSS property specifies how an element's fragments should be rendered when broken across multiple lines, columns, or pages.\n\n**Syntax**: `slice | clone`\n\n**Initial value**: `slice`
     *
     * 参考定义: "#/definitions/Property.BoxDecorationBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clone"},{"type":"string","const":"slice"}]
     *
     * @see Property_BoxDecorationBreak
     */
    @Const({"clone", "slice"})
    @Schema(title = "The **`box-decoration-break`** CSS property specifies how an element's fragments should be rendered when broken across multiple lines, columns, or pages.\n\n**Syntax**: `slice | clone`\n\n**Initial value**: `slice`")
    String WebkitBoxDecorationBreak() default "	";

    /**
     * The **`-webkit-box-reflect`** CSS property lets you reflect the content of an element in one specific direction.\n\n**Syntax**: `[ above | below | right | left ]? <length>? <image>?`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.WebkitBoxReflect%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"above"},{"type":"string","const":"below"},{"type":"string","const":"left"},{"type":"string","const":"right"}]
     *
     * @see Property_WebkitBoxReflect
     */
    @Const({"above", "below", "left", "right"})
    @Schema(title = "The **`-webkit-box-reflect`** CSS property lets you reflect the content of an element in one specific direction.\n\n**Syntax**: `[ above | below | right | left ]? <length>? <image>?`\n\n**Initial value**: `none`")
    String WebkitBoxReflect() default "	";

    /**
     * The **`box-shadow`** CSS property adds shadow effects around an element's frame. You can set multiple effects separated by commas. A box shadow is described by X and Y offsets relative to the element, blur and spread radius, and color.\n\n**Syntax**: `none | <shadow>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.BoxShadow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BoxShadow
     */
    @Const({"none"})
    @Schema(title = "The **`box-shadow`** CSS property adds shadow effects around an element's frame. You can set multiple effects separated by commas. A box shadow is described by X and Y offsets relative to the element, blur and spread radius, and color.\n\n**Syntax**: `none | <shadow>#`\n\n**Initial value**: `none`")
    String WebkitBoxShadow() default "	";

    /**
     * The **`box-sizing`** CSS property sets how the total width and height of an element is calculated.\n\n**Syntax**: `content-box | border-box`\n\n**Initial value**: `content-box`
     *
     * 参考定义: "#/definitions/Property.BoxSizing"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"}]
     *
     * @see Property_BoxSizing
     */
    @Const({"border-box", "content-box"})
    @Schema(title = "The **`box-sizing`** CSS property sets how the total width and height of an element is calculated.\n\n**Syntax**: `content-box | border-box`\n\n**Initial value**: `content-box`")
    String WebkitBoxSizing() default "	";

    /**
     * The `**clip-path**` CSS property creates a clipping region that sets what part of an element should be shown. Parts that are inside the region are shown, while those outside are hidden.\n\n**Syntax**: `<clip-source> | [ <basic-shape> || <geometry-box> ] | none`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ClipPath"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ClipPath
     */
    @Const({"none"})
    @Schema(title = "The `**clip-path**` CSS property creates a clipping region that sets what part of an element should be shown. Parts that are inside the region are shown, while those outside are hidden.\n\n**Syntax**: `<clip-source> | [ <basic-shape> || <geometry-box> ] | none`\n\n**Initial value**: `none`")
    String WebkitClipPath() default "	";

    /**
     * The **`column-count`** CSS property breaks an element's content into the specified number of columns.\n\n**Syntax**: `<integer> | auto`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.ColumnCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_ColumnCount
     */
    @Const({"auto"})
    @Schema(title = "The **`column-count`** CSS property breaks an element's content into the specified number of columns.\n\n**Syntax**: `<integer> | auto`\n\n**Initial value**: `auto`")
    String WebkitColumnCount() default "	";

    /**
     * The **`column-fill`** CSS property controls how an element's contents are balanced when broken into columns.\n\n**Syntax**: `auto | balance | balance-all`\n\n**Initial value**: `balance`
     *
     * 参考定义: "#/definitions/Property.ColumnFill"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"balance"},{"type":"string","const":"balance-all"}]
     *
     * @see Property_ColumnFill
     */
    @Const({"auto", "balance", "balance-all"})
    @Schema(title = "The **`column-fill`** CSS property controls how an element's contents are balanced when broken into columns.\n\n**Syntax**: `auto | balance | balance-all`\n\n**Initial value**: `balance`")
    String WebkitColumnFill() default "	";

    /**
     * The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.ColumnGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_ColumnGap
     */
    @Const({"normal"})
    @Schema(title = "The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`")
    String WebkitColumnGap() default "	";

    /**
     * The **`column-rule-color`** CSS property sets the color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.ColumnRuleColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_ColumnRuleColor
     */

    @Schema(title = "The **`column-rule-color`** CSS property sets the color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String WebkitColumnRuleColor() default "	";

    /**
     * The **`column-rule-style`** CSS property sets the style of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ColumnRuleStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string"}]
     *
     * @see Property_ColumnRuleStyle
     */

    @Schema(title = "The **`column-rule-style`** CSS property sets the style of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`")
    String WebkitColumnRuleStyle() default "	";

    /**
     * The **`column-rule-width`** CSS property sets the width of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`
     *
     * 参考定义: "#/definitions/Property.ColumnRuleWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_ColumnRuleWidth
     */

    @Schema(title = "The **`column-rule-width`** CSS property sets the width of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`")
    String WebkitColumnRuleWidth() default "	";

    /**
     * The **`column-span`** CSS property makes it possible for an element to span across all columns when its value is set to `all`.\n\n**Syntax**: `none | all`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ColumnSpan"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"}]
     *
     * @see Property_ColumnSpan
     */
    @Const({"all", "none"})
    @Schema(title = "The **`column-span`** CSS property makes it possible for an element to span across all columns when its value is set to `all`.\n\n**Syntax**: `none | all`\n\n**Initial value**: `none`")
    String WebkitColumnSpan() default "	";

    /**
     * The **`column-width`** CSS property sets the ideal column width in a multi-column layout. The container will have as many columns as can fit without any of them having a width less than the `column-width` value. If the width of the container is narrower than the specified value, the single column's width will be smaller than the declared column width.\n\n**Syntax**: `<length> | auto`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.ColumnWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ColumnWidth
     */
    @Const({"auto"})
    @Schema(title = "The **`column-width`** CSS property sets the ideal column width in a multi-column layout. The container will have as many columns as can fit without any of them having a width less than the `column-width` value. If the width of the container is narrower than the specified value, the single column's width will be smaller than the declared column width.\n\n**Syntax**: `<length> | auto`\n\n**Initial value**: `auto`")
    String WebkitColumnWidth() default "	";

    /**
     * The **`filter`** CSS property applies graphical effects like blur or color shift to an element. Filters are commonly used to adjust the rendering of images, backgrounds, and borders.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.Filter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Filter
     */
    @Const({"none"})
    @Schema(title = "The **`filter`** CSS property applies graphical effects like blur or color shift to an element. Filters are commonly used to adjust the rendering of images, backgrounds, and borders.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`")
    String WebkitFilter() default "	";

    /**
     * The **`flex-basis`** CSS property sets the initial main size of a flex item. It sets the size of the content box unless otherwise set with `box-sizing`.\n\n**Syntax**: `content | <'width'>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.FlexBasis%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-auto"},{"type":"string","const":"auto"},{"type":"string","const":"content"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_FlexBasis
     */
    @Const({"-moz-max-content", "-moz-min-content", "-webkit-auto", "auto", "content", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`flex-basis`** CSS property sets the initial main size of a flex item. It sets the size of the content box unless otherwise set with `box-sizing`.\n\n**Syntax**: `content | <'width'>`\n\n**Initial value**: `auto`")
    String WebkitFlexBasis() default "	";

    /**
     * The **`flex-direction`** CSS property sets how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).\n\n**Syntax**: `row | row-reverse | column | column-reverse`\n\n**Initial value**: `row`
     *
     * 参考定义: "#/definitions/Property.FlexDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"}]
     *
     * @see Property_FlexDirection
     */
    @Const({"column", "column-reverse", "row", "row-reverse"})
    @Schema(title = "The **`flex-direction`** CSS property sets how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).\n\n**Syntax**: `row | row-reverse | column | column-reverse`\n\n**Initial value**: `row`")
    String WebkitFlexDirection() default "	";

    /**
     * The **`flex-grow`** CSS property sets the flex grow factor of a flex item main size.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.FlexGrow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FlexGrow
     */

    @Schema(title = "The **`flex-grow`** CSS property sets the flex grow factor of a flex item main size.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`")
    String WebkitFlexGrow() default "	";

    /**
     * The **`flex-shrink`** CSS property sets the flex shrink factor of a flex item. If the size of all flex items is larger than the flex container, items shrink to fit according to `flex-shrink`.\n\n**Syntax**: `<number>`\n\n**Initial value**: `1`
     *
     * 参考定义: "#/definitions/Property.FlexShrink"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FlexShrink
     */

    @Schema(title = "The **`flex-shrink`** CSS property sets the flex shrink factor of a flex item. If the size of all flex items is larger than the flex container, items shrink to fit according to `flex-shrink`.\n\n**Syntax**: `<number>`\n\n**Initial value**: `1`")
    String WebkitFlexShrink() default "	";

    /**
     * The **`flex-wrap`** CSS property sets whether flex items are forced onto one line or can wrap onto multiple lines. If wrapping is allowed, it sets the direction that lines are stacked.\n\n**Syntax**: `nowrap | wrap | wrap-reverse`\n\n**Initial value**: `nowrap`
     *
     * 参考定义: "#/definitions/Property.FlexWrap"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"nowrap"},{"type":"string","const":"wrap"},{"type":"string","const":"wrap-reverse"}]
     *
     * @see Property_FlexWrap
     */
    @Const({"nowrap", "wrap", "wrap-reverse"})
    @Schema(title = "The **`flex-wrap`** CSS property sets whether flex items are forced onto one line or can wrap onto multiple lines. If wrapping is allowed, it sets the direction that lines are stacked.\n\n**Syntax**: `nowrap | wrap | wrap-reverse`\n\n**Initial value**: `nowrap`")
    String WebkitFlexWrap() default "	";

    /**
     * The **`font-feature-settings`** CSS property controls advanced typographic features in OpenType fonts.\n\n**Syntax**: `normal | <feature-tag-value>#`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.FontFeatureSettings"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontFeatureSettings
     */
    @Const({"normal"})
    @Schema(title = "The **`font-feature-settings`** CSS property controls advanced typographic features in OpenType fonts.\n\n**Syntax**: `normal | <feature-tag-value>#`\n\n**Initial value**: `normal`")
    String WebkitFontFeatureSettings() default "	";

    /**
     * The **`font-kerning`** CSS property sets the use of the kerning information stored in a font.\n\n**Syntax**: `auto | normal | none`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.FontKerning"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string","const":"normal"}]
     *
     * @see Property_FontKerning
     */
    @Const({"auto", "none", "normal"})
    @Schema(title = "The **`font-kerning`** CSS property sets the use of the kerning information stored in a font.\n\n**Syntax**: `auto | normal | none`\n\n**Initial value**: `auto`")
    String WebkitFontKerning() default "	";

    /**
     * The **`font-smooth`** CSS property controls the application of anti-aliasing when fonts are rendered.\n\n**Syntax**: `auto | never | always | <absolute-size> | <length>`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.FontSmooth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"never"}]
     *
     * @see Property_FontSmooth
     */
    @Const({"always", "auto", "never"})
    @Schema(title = "The **`font-smooth`** CSS property controls the application of anti-aliasing when fonts are rendered.\n\n**Syntax**: `auto | never | always | <absolute-size> | <length>`\n\n**Initial value**: `auto`")
    String WebkitFontSmoothing() default "	";

    /**
     * The **`font-variant-ligatures`** CSS property controls which ligatures and contextual forms are used in textual content of the elements it applies to. This leads to more harmonized forms in the resulting text.\n\n**Syntax**: `normal | none | [ <common-lig-values> || <discretionary-lig-values> || <historical-lig-values> || <contextual-alt-values> ]`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.FontVariantLigatures"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"common-ligatures"},{"type":"string","const":"contextual"},{"type":"string","const":"discretionary-ligatures"},{"type":"string","const":"historical-ligatures"},{"type":"string","const":"no-common-ligatures"},{"type":"string","const":"no-contextual"},{"type":"string","const":"no-discretionary-ligatures"},{"type":"string","const":"no-historical-ligatures"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontVariantLigatures
     */
    @Const({"common-ligatures", "contextual", "discretionary-ligatures", "historical-ligatures", "no-common-ligatures", "no-contextual", "no-discretionary-ligatures", "no-historical-ligatures", "none", "normal"})
    @Schema(title = "The **`font-variant-ligatures`** CSS property controls which ligatures and contextual forms are used in textual content of the elements it applies to. This leads to more harmonized forms in the resulting text.\n\n**Syntax**: `normal | none | [ <common-lig-values> || <discretionary-lig-values> || <historical-lig-values> || <contextual-alt-values> ]`\n\n**Initial value**: `normal`")
    String WebkitFontVariantLigatures() default "	";

    /**
     * The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`
     *
     * 参考定义: "#/definitions/Property.Hyphens"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"manual"},{"type":"string","const":"none"}]
     *
     * @see Property_Hyphens
     */
    @Const({"auto", "manual", "none"})
    @Schema(title = "The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`")
    String WebkitHyphens() default "	";

    /**
     * The `initial-letter` CSS property sets styling for dropped, raised, and sunken initial letters.\n\n**Syntax**: `normal | [ <number> <integer>? ]`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.InitialLetter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_InitialLetter
     */
    @Const({"normal"})
    @Schema(title = "The `initial-letter` CSS property sets styling for dropped, raised, and sunken initial letters.\n\n**Syntax**: `normal | [ <number> <integer>? ]`\n\n**Initial value**: `normal`")
    String WebkitInitialLetter() default "	";

    /**
     * The CSS **`justify-content`** property defines how the browser distributes space between and around content items along the main-axis of a flex container, and the inline axis of a grid container.\n\n**Syntax**: `normal | <content-distribution> | <overflow-position>? [ <content-position> | left | right ]`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.JustifyContent"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string"}]
     *
     * @see Property_JustifyContent
     */
    @Const({"left", "normal", "right"})
    @Schema(title = "The CSS **`justify-content`** property defines how the browser distributes space between and around content items along the main-axis of a flex container, and the inline axis of a grid container.\n\n**Syntax**: `normal | <content-distribution> | <overflow-position>? [ <content-position> | left | right ]`\n\n**Initial value**: `normal`")
    String WebkitJustifyContent() default "	";

    /**
     * The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.LineBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"auto"},{"type":"string","const":"loose"},{"type":"string","const":"normal"},{"type":"string","const":"strict"}]
     *
     * @see Property_LineBreak
     */
    @Const({"anywhere", "auto", "loose", "normal", "strict"})
    @Schema(title = "The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`")
    String WebkitLineBreak() default "	";

    /**
     * The **`-webkit-line-clamp`** CSS property allows limiting of the contents of a block container to the specified number of lines.\n\n**Syntax**: `none | <integer>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.WebkitLineClamp"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_WebkitLineClamp
     */
    @Const({"none"})
    @Schema(title = "The **`-webkit-line-clamp`** CSS property allows limiting of the contents of a block container to the specified number of lines.\n\n**Syntax**: `none | <integer>`\n\n**Initial value**: `none`")
    String WebkitLineClamp() default "	";

    /**
     * The **`margin-inline-end`** CSS property defines the logical inline end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. In other words, it corresponds to the `margin-top`, `margin-right`, `margin-bottom` or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MarginInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInlineEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline-end`** CSS property defines the logical inline end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. In other words, it corresponds to the `margin-top`, `margin-right`, `margin-bottom` or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`")
    String WebkitMarginEnd() default "	";

    /**
     * The **`margin-inline-start`** CSS property defines the logical inline start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. It corresponds to the `margin-top`, `margin-right`, `margin-bottom`, or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MarginInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInlineStart
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline-start`** CSS property defines the logical inline start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. It corresponds to the `margin-top`, `margin-right`, `margin-bottom`, or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`")
    String WebkitMarginStart() default "	";

    /**
     * If a `-webkit-mask-image` is specified, `-webkit-mask-attachment` determines whether the mask image's position is fixed within the viewport, or scrolls along with its containing block.\n\n**Syntax**: `<attachment>#`\n\n**Initial value**: `scroll`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskAttachment"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Attachment"},{"type":"string"}]
     *
     * @see Property_WebkitMaskAttachment
     */

    @Schema(title = "If a `-webkit-mask-image` is specified, `-webkit-mask-attachment` determines whether the mask image's position is fixed within the viewport, or scrolls along with its containing block.\n\n**Syntax**: `<attachment>#`\n\n**Initial value**: `scroll`")
    String WebkitMaskAttachment() default "	";

    /**
     * The **`mask-border-outset`** CSS property specifies the distance by which an element's mask border is set out from its border box.\n\n**Syntax**: `[ <length> | <number> ]{1,4}`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MaskBorderOutset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MaskBorderOutset
     */

    @Schema(title = "The **`mask-border-outset`** CSS property specifies the distance by which an element's mask border is set out from its border box.\n\n**Syntax**: `[ <length> | <number> ]{1,4}`\n\n**Initial value**: `0`")
    String WebkitMaskBoxImageOutset() default "	";

    /**
     * The **`mask-border-repeat`** CSS property sets how the edge regions of a source image are adjusted to fit the dimensions of an element's mask border.\n\n**Syntax**: `[ stretch | repeat | round | space ]{1,2}`\n\n**Initial value**: `stretch`
     *
     * 参考定义: "#/definitions/Property.MaskBorderRepeat"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_MaskBorderRepeat
     */
    @Const({"repeat", "round", "space", "stretch"})
    @Schema(title = "The **`mask-border-repeat`** CSS property sets how the edge regions of a source image are adjusted to fit the dimensions of an element's mask border.\n\n**Syntax**: `[ stretch | repeat | round | space ]{1,2}`\n\n**Initial value**: `stretch`")
    String WebkitMaskBoxImageRepeat() default "	";

    /**
     * The **`mask-border-slice`** CSS property divides the image set by `mask-border-source` into regions. These regions are used to form the components of an element's mask border.\n\n**Syntax**: `<number-percentage>{1,4} fill?`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MaskBorderSlice"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MaskBorderSlice
     */

    @Schema(title = "The **`mask-border-slice`** CSS property divides the image set by `mask-border-source` into regions. These regions are used to form the components of an element's mask border.\n\n**Syntax**: `<number-percentage>{1,4} fill?`\n\n**Initial value**: `0`")
    String WebkitMaskBoxImageSlice() default "	";

    /**
     * The **`mask-border-source`** CSS property sets the source image used to create an element's mask border.\n\n**Syntax**: `none | <image>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MaskBorderSource"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MaskBorderSource
     */
    @Const({"none"})
    @Schema(title = "The **`mask-border-source`** CSS property sets the source image used to create an element's mask border.\n\n**Syntax**: `none | <image>`\n\n**Initial value**: `none`")
    String WebkitMaskBoxImageSource() default "	";

    /**
     * The **`mask-border-width`** CSS property sets the width of an element's mask border.\n\n**Syntax**: `[ <length-percentage> | <number> | auto ]{1,4}`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.MaskBorderWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MaskBorderWidth
     */
    @Const({"auto"})
    @Schema(title = "The **`mask-border-width`** CSS property sets the width of an element's mask border.\n\n**Syntax**: `[ <length-percentage> | <number> | auto ]{1,4}`\n\n**Initial value**: `auto`")
    String WebkitMaskBoxImageWidth() default "	";

    /**
     * The **`mask-clip`** CSS property determines the area which is affected by a mask. The painted content of an element must be restricted to this area.\n\n**Syntax**: `[ <box> | border | padding | content | text ]#`\n\n**Initial value**: `border`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskClip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"border"},{"type":"string","const":"content"},{"type":"string","const":"padding"},{"type":"string","const":"text"},{"type":"string"}]
     *
     * @see Property_WebkitMaskClip
     */
    @Const({"border", "content", "padding", "text"})
    @Schema(title = "The **`mask-clip`** CSS property determines the area which is affected by a mask. The painted content of an element must be restricted to this area.\n\n**Syntax**: `[ <box> | border | padding | content | text ]#`\n\n**Initial value**: `border`")
    String WebkitMaskClip() default "	";

    /**
     * The **`-webkit-mask-composite`** property specifies the manner in which multiple mask images applied to the same element are composited with one another. Mask images are composited in the opposite order that they are declared with the `-webkit-mask-image` property.\n\n**Syntax**: `<composite-style>#`\n\n**Initial value**: `source-over`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskComposite"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.CompositeStyle"},{"type":"string"}]
     *
     * @see Property_WebkitMaskComposite
     */

    @Schema(title = "The **`-webkit-mask-composite`** property specifies the manner in which multiple mask images applied to the same element are composited with one another. Mask images are composited in the opposite order that they are declared with the `-webkit-mask-image` property.\n\n**Syntax**: `<composite-style>#`\n\n**Initial value**: `source-over`")
    String WebkitMaskComposite() default "	";

    /**
     * The **`mask-image`** CSS property sets the image that is used as mask layer for an element.\n\n**Syntax**: `<mask-reference>#`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_WebkitMaskImage
     */
    @Const({"none"})
    @Schema(title = "The **`mask-image`** CSS property sets the image that is used as mask layer for an element.\n\n**Syntax**: `<mask-reference>#`\n\n**Initial value**: `none`")
    String WebkitMaskImage() default "	";

    /**
     * The **`mask-origin`** CSS property sets the origin of a mask.\n\n**Syntax**: `[ <box> | border | padding | content ]#`\n\n**Initial value**: `padding`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskOrigin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"border"},{"type":"string","const":"content"},{"type":"string","const":"padding"},{"type":"string"}]
     *
     * @see Property_WebkitMaskOrigin
     */
    @Const({"border", "content", "padding"})
    @Schema(title = "The **`mask-origin`** CSS property sets the origin of a mask.\n\n**Syntax**: `[ <box> | border | padding | content ]#`\n\n**Initial value**: `padding`")
    String WebkitMaskOrigin() default "	";

    /**
     * The **`mask-position`** CSS property sets the initial position, relative to the mask position layer set by `mask-origin`, for each defined mask image.\n\n**Syntax**: `<position>#`\n\n**Initial value**: `0% 0%`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskPosition%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_WebkitMaskPosition
     */

    @Schema(title = "The **`mask-position`** CSS property sets the initial position, relative to the mask position layer set by `mask-origin`, for each defined mask image.\n\n**Syntax**: `<position>#`\n\n**Initial value**: `0% 0%`")
    String WebkitMaskPosition() default "	";

    /**
     * The `-webkit-mask-position-x` CSS property sets the initial horizontal position of a mask image.\n\n**Syntax**: `[ <length-percentage> | left | center | right ]#`\n\n**Initial value**: `0%`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskPositionX%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"}]
     *
     * @see Property_WebkitMaskPositionX
     */
    @Const({"center", "left", "right"})
    @Schema(title = "The `-webkit-mask-position-x` CSS property sets the initial horizontal position of a mask image.\n\n**Syntax**: `[ <length-percentage> | left | center | right ]#`\n\n**Initial value**: `0%`")
    String WebkitMaskPositionX() default "	";

    /**
     * The `-webkit-mask-position-y` CSS property sets the initial vertical position of a mask image.\n\n**Syntax**: `[ <length-percentage> | top | center | bottom ]#`\n\n**Initial value**: `0%`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskPositionY%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"top"}]
     *
     * @see Property_WebkitMaskPositionY
     */
    @Const({"bottom", "center", "top"})
    @Schema(title = "The `-webkit-mask-position-y` CSS property sets the initial vertical position of a mask image.\n\n**Syntax**: `[ <length-percentage> | top | center | bottom ]#`\n\n**Initial value**: `0%`")
    String WebkitMaskPositionY() default "	";

    /**
     * The **`mask-repeat`** CSS property sets how mask images are repeated. A mask image can be repeated along the horizontal axis, the vertical axis, both axes, or not repeated at all.\n\n**Syntax**: `<repeat-style>#`\n\n**Initial value**: `repeat`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskRepeat"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"type":"string"}]
     *
     * @see Property_WebkitMaskRepeat
     */

    @Schema(title = "The **`mask-repeat`** CSS property sets how mask images are repeated. A mask image can be repeated along the horizontal axis, the vertical axis, both axes, or not repeated at all.\n\n**Syntax**: `<repeat-style>#`\n\n**Initial value**: `repeat`")
    String WebkitMaskRepeat() default "	";

    /**
     * The `-webkit-mask-repeat-x` property specifies whether and how a mask image is repeated (tiled) horizontally.\n\n**Syntax**: `repeat | no-repeat | space | round`\n\n**Initial value**: `repeat`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskRepeatX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"no-repeat"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"}]
     *
     * @see Property_WebkitMaskRepeatX
     */
    @Const({"no-repeat", "repeat", "round", "space"})
    @Schema(title = "The `-webkit-mask-repeat-x` property specifies whether and how a mask image is repeated (tiled) horizontally.\n\n**Syntax**: `repeat | no-repeat | space | round`\n\n**Initial value**: `repeat`")
    String WebkitMaskRepeatX() default "	";

    /**
     * The `-webkit-mask-repeat-y` property sets whether and how a mask image is repeated (tiled) vertically.\n\n**Syntax**: `repeat | no-repeat | space | round`\n\n**Initial value**: `repeat`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskRepeatY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"no-repeat"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"}]
     *
     * @see Property_WebkitMaskRepeatY
     */
    @Const({"no-repeat", "repeat", "round", "space"})
    @Schema(title = "The `-webkit-mask-repeat-y` property sets whether and how a mask image is repeated (tiled) vertically.\n\n**Syntax**: `repeat | no-repeat | space | round`\n\n**Initial value**: `repeat`")
    String WebkitMaskRepeatY() default "	";

    /**
     * The **`mask-size`** CSS property specifies the sizes of the mask images. The size of the image can be fully or partially constrained in order to preserve its intrinsic ratio.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`
     *
     * 参考定义: "#/definitions/Property.WebkitMaskSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgSize%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_WebkitMaskSize
     */

    @Schema(title = "The **`mask-size`** CSS property specifies the sizes of the mask images. The size of the image can be fully or partially constrained in order to preserve its intrinsic ratio.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`")
    String WebkitMaskSize() default "	";

    /**
     * The **`max-inline-size`** CSS property defines the horizontal or vertical maximum size of an element's block, depending on its writing mode. It corresponds to either the `max-width` or the `max-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'max-width'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.MaxInlineSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_MaxInlineSize
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "fit-content", "max-content", "min-content", "none"})
    @Schema(title = "The **`max-inline-size`** CSS property defines the horizontal or vertical maximum size of an element's block, depending on its writing mode. It corresponds to either the `max-width` or the `max-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'max-width'>`\n\n**Initial value**: `0`")
    String WebkitMaxInlineSize() default "	";

    /**
     * The **`order`** CSS property sets the order to lay out an item in a flex or grid container. Items in a container are sorted by ascending `order` value and then by their source code order.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.Order"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_Order
     */

    @Schema(title = "The **`order`** CSS property sets the order to lay out an item in a flex or grid container. Items in a container are sorted by ascending `order` value and then by their source code order.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `0`")
    String WebkitOrder() default "	";

    /**
     * The `-webkit-overflow-scrolling` CSS property controls whether or not touch devices use momentum-based scrolling for a given element.\n\n**Syntax**: `auto | touch`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.WebkitOverflowScrolling"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"touch"}]
     *
     * @see Property_WebkitOverflowScrolling
     */
    @Const({"auto", "touch"})
    @Schema(title = "The `-webkit-overflow-scrolling` CSS property controls whether or not touch devices use momentum-based scrolling for a given element.\n\n**Syntax**: `auto | touch`\n\n**Initial value**: `auto`")
    String WebkitOverflowScrolling() default "	";

    /**
     * The **`padding-inline-end`** CSS property defines the logical inline end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.PaddingInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInlineEnd
     */

    @Schema(title = "The **`padding-inline-end`** CSS property defines the logical inline end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`")
    String WebkitPaddingEnd() default "	";

    /**
     * The **`padding-inline-start`** CSS property defines the logical inline start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.PaddingInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInlineStart
     */

    @Schema(title = "The **`padding-inline-start`** CSS property defines the logical inline start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`")
    String WebkitPaddingStart() default "	";

    /**
     * The **`perspective`** CSS property determines the distance between the z=0 plane and the user in order to give a 3D-positioned element some perspective.\n\n**Syntax**: `none | <length>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.Perspective%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"none"}]
     *
     * @see Property_Perspective
     */
    @Const({"none"})
    @Schema(title = "The **`perspective`** CSS property determines the distance between the z=0 plane and the user in order to give a 3D-positioned element some perspective.\n\n**Syntax**: `none | <length>`\n\n**Initial value**: `none`")
    String WebkitPerspective() default "	";

    /**
     * The **`perspective-origin`** CSS property determines the position at which the viewer is looking. It is used as the _vanishing point_ by the `perspective` property.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`
     *
     * 参考定义: "#/definitions/Property.PerspectiveOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_PerspectiveOrigin
     */

    @Schema(title = "The **`perspective-origin`** CSS property determines the position at which the viewer is looking. It is used as the _vanishing point_ by the `perspective` property.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`")
    String WebkitPerspectiveOrigin() default "	";

    /**
     * The **`color-adjust`** CSS property sets what, if anything, the user agent may do to optimize the appearance of the element on the output device. By default, the browser is allowed to make any adjustments to the element's appearance it determines to be necessary and prudent given the type and capabilities of the output device.\n\n**Syntax**: `economy | exact`\n\n**Initial value**: `economy`
     *
     * 参考定义: "#/definitions/Property.ColorAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"economy"},{"type":"string","const":"exact"}]
     *
     * @see Property_ColorAdjust
     */
    @Const({"economy", "exact"})
    @Schema(title = "The **`color-adjust`** CSS property sets what, if anything, the user agent may do to optimize the appearance of the element on the output device. By default, the browser is allowed to make any adjustments to the element's appearance it determines to be necessary and prudent given the type and capabilities of the output device.\n\n**Syntax**: `economy | exact`\n\n**Initial value**: `economy`")
    String WebkitPrintColorAdjust() default "	";

    /**
     * The `**ruby-position**` CSS property defines the position of a ruby element relatives to its base element. It can be position over the element (`over`), under it (`under`), or between the characters, on their right side (`inter-character`).\n\n**Syntax**: `[ alternate || [ over | under ] ] | inter-character`\n\n**Initial value**: `alternate`
     *
     * 参考定义: "#/definitions/Property.RubyPosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alternate"},{"type":"string","const":"inter-character"},{"type":"string","const":"over"},{"type":"string","const":"under"},{"type":"string"}]
     *
     * @see Property_RubyPosition
     */
    @Const({"alternate", "inter-character", "over", "under"})
    @Schema(title = "The `**ruby-position**` CSS property defines the position of a ruby element relatives to its base element. It can be position over the element (`over`), under it (`under`), or between the characters, on their right side (`inter-character`).\n\n**Syntax**: `[ alternate || [ over | under ] ] | inter-character`\n\n**Initial value**: `alternate`")
    String WebkitRubyPosition() default "	";

    /**
     * The **`scroll-snap-type`** CSS property sets how strictly snap points are enforced on the scroll container in case there is one.\n\n**Syntax**: `none | [ x | y | block | inline | both ] [ mandatory | proximity ]?`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.ScrollSnapType"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"both"},{"type":"string","const":"inline"},{"type":"string","const":"none"},{"type":"string","const":"x"},{"type":"string","const":"y"},{"type":"string"}]
     *
     * @see Property_ScrollSnapType
     */
    @Const({"block", "both", "inline", "none", "x", "y"})
    @Schema(title = "The **`scroll-snap-type`** CSS property sets how strictly snap points are enforced on the scroll container in case there is one.\n\n**Syntax**: `none | [ x | y | block | inline | both ] [ mandatory | proximity ]?`\n\n**Initial value**: `none`")
    String WebkitScrollSnapType() default "	";

    /**
     * The **`shape-margin`** CSS property sets a margin for a CSS shape created using `shape-outside`.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.ShapeMargin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ShapeMargin
     */

    @Schema(title = "The **`shape-margin`** CSS property sets a margin for a CSS shape created using `shape-outside`.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`")
    String WebkitShapeMargin() default "	";

    /**
     * **`-webkit-tap-highlight-color`** is a non-standard CSS property that sets the color of the highlight that appears over a link while it's being tapped. The highlighting indicates to the user that their tap is being successfully recognized, and indicates which element they're tapping on.\n\n**Syntax**: `<color>`\n\n**Initial value**: `black`
     *
     * 参考定义: "#/definitions/Property.WebkitTapHighlightColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_WebkitTapHighlightColor
     */

    @Schema(title = "**`-webkit-tap-highlight-color`** is a non-standard CSS property that sets the color of the highlight that appears over a link while it's being tapped. The highlighting indicates to the user that their tap is being successfully recognized, and indicates which element they're tapping on.\n\n**Syntax**: `<color>`\n\n**Initial value**: `black`")
    String WebkitTapHighlightColor() default "	";

    /**
     * The **`text-combine-upright`** CSS property sets the combination of characters into the space of a single character. If the combined text is wider than 1em, the user agent must fit the contents within 1em. The resulting composition is treated as a single upright glyph for layout and decoration. This property only has an effect in vertical writing modes.\n\n**Syntax**: `none | all | [ digits <integer>? ]`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.TextCombineUpright"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextCombineUpright
     */
    @Const({"all", "none"})
    @Schema(title = "The **`text-combine-upright`** CSS property sets the combination of characters into the space of a single character. If the combined text is wider than 1em, the user agent must fit the contents within 1em. The resulting composition is treated as a single upright glyph for layout and decoration. This property only has an effect in vertical writing modes.\n\n**Syntax**: `none | all | [ digits <integer>? ]`\n\n**Initial value**: `none`")
    String WebkitTextCombine() default "	";

    /**
     * The **`text-decoration-color`** CSS property sets the color of decorations added to text by `text-decoration-line`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.TextDecorationColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_TextDecorationColor
     */

    @Schema(title = "The **`text-decoration-color`** CSS property sets the color of decorations added to text by `text-decoration-line`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String WebkitTextDecorationColor() default "	";

    /**
     * The **`text-decoration-line`** CSS property sets the kind of decoration that is used on text in an element, such as an underline or overline.\n\n**Syntax**: `none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.TextDecorationLine"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"blink"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string"}]
     *
     * @see Property_TextDecorationLine
     */
    @Const({"blink", "grammar-error", "line-through", "none", "overline", "spelling-error", "underline"})
    @Schema(title = "The **`text-decoration-line`** CSS property sets the kind of decoration that is used on text in an element, such as an underline or overline.\n\n**Syntax**: `none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error`\n\n**Initial value**: `none`")
    String WebkitTextDecorationLine() default "	";

    /**
     * The **`text-decoration-skip`** CSS property sets what parts of an element’s content any text decoration affecting the element must skip over. It controls all text decoration lines drawn by the element and also any text decoration lines drawn by its ancestors.\n\n**Syntax**: `none | [ objects || [ spaces | [ leading-spaces || trailing-spaces ] ] || edges || box-decoration ]`\n\n**Initial value**: `objects`
     *
     * 参考定义: "#/definitions/Property.TextDecorationSkip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"box-decoration"},{"type":"string","const":"edges"},{"type":"string","const":"leading-spaces"},{"type":"string","const":"none"},{"type":"string","const":"objects"},{"type":"string","const":"spaces"},{"type":"string","const":"trailing-spaces"},{"type":"string"}]
     *
     * @see Property_TextDecorationSkip
     */
    @Const({"box-decoration", "edges", "leading-spaces", "none", "objects", "spaces", "trailing-spaces"})
    @Schema(title = "The **`text-decoration-skip`** CSS property sets what parts of an element’s content any text decoration affecting the element must skip over. It controls all text decoration lines drawn by the element and also any text decoration lines drawn by its ancestors.\n\n**Syntax**: `none | [ objects || [ spaces | [ leading-spaces || trailing-spaces ] ] || edges || box-decoration ]`\n\n**Initial value**: `objects`")
    String WebkitTextDecorationSkip() default "	";

    /**
     * The **`text-decoration-style`** CSS property sets the style of the lines specified by `text-decoration-line`. The style applies to all lines that are set with `text-decoration-line`.\n\n**Syntax**: `solid | double | dotted | dashed | wavy`\n\n**Initial value**: `solid`
     *
     * 参考定义: "#/definitions/Property.TextDecorationStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"solid"},{"type":"string","const":"wavy"}]
     *
     * @see Property_TextDecorationStyle
     */
    @Const({"dashed", "dotted", "double", "solid", "wavy"})
    @Schema(title = "The **`text-decoration-style`** CSS property sets the style of the lines specified by `text-decoration-line`. The style applies to all lines that are set with `text-decoration-line`.\n\n**Syntax**: `solid | double | dotted | dashed | wavy`\n\n**Initial value**: `solid`")
    String WebkitTextDecorationStyle() default "	";

    /**
     * The **`text-emphasis-color`** CSS property sets the color of emphasis marks. This value can also be set using the `text-emphasis` shorthand.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.TextEmphasisColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_TextEmphasisColor
     */

    @Schema(title = "The **`text-emphasis-color`** CSS property sets the color of emphasis marks. This value can also be set using the `text-emphasis` shorthand.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String WebkitTextEmphasisColor() default "	";

    /**
     * The **`text-emphasis-position`** CSS property sets where emphasis marks are drawn. Like ruby text, if there isn't enough room for emphasis marks, the line height is increased.\n\n**Syntax**: `[ over | under ] && [ right | left ]`\n\n**Initial value**: `over right`
     *
     * 参考定义: "#/definitions/Property.TextEmphasisPosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TextEmphasisPosition
     */

    @Schema(title = "The **`text-emphasis-position`** CSS property sets where emphasis marks are drawn. Like ruby text, if there isn't enough room for emphasis marks, the line height is increased.\n\n**Syntax**: `[ over | under ] && [ right | left ]`\n\n**Initial value**: `over right`")
    String WebkitTextEmphasisPosition() default "	";

    /**
     * The **`text-emphasis-style`** CSS property sets the appearance of emphasis marks. It can also be set, and reset, using the `text-emphasis` shorthand.\n\n**Syntax**: `none | [ [ filled | open ] || [ dot | circle | double-circle | triangle | sesame ] ] | <string>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.TextEmphasisStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"circle"},{"type":"string","const":"dot"},{"type":"string","const":"double-circle"},{"type":"string","const":"filled"},{"type":"string","const":"none"},{"type":"string","const":"open"},{"type":"string","const":"sesame"},{"type":"string","const":"triangle"},{"type":"string"}]
     *
     * @see Property_TextEmphasisStyle
     */
    @Const({"circle", "dot", "double-circle", "filled", "none", "open", "sesame", "triangle"})
    @Schema(title = "The **`text-emphasis-style`** CSS property sets the appearance of emphasis marks. It can also be set, and reset, using the `text-emphasis` shorthand.\n\n**Syntax**: `none | [ [ filled | open ] || [ dot | circle | double-circle | triangle | sesame ] ] | <string>`\n\n**Initial value**: `none`")
    String WebkitTextEmphasisStyle() default "	";

    /**
     * The **`-webkit-text-fill-color`** CSS property specifies the fill color of characters of text. If this property is not set, the value of the `color` property is used.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.WebkitTextFillColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_WebkitTextFillColor
     */

    @Schema(title = "The **`-webkit-text-fill-color`** CSS property specifies the fill color of characters of text. If this property is not set, the value of the `color` property is used.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String WebkitTextFillColor() default "	";

    /**
     * The **`text-orientation`** CSS property sets the orientation of the text characters in a line. It only affects text in vertical mode (when `writing-mode` is not `horizontal-tb`). It is useful for controlling the display of languages that use vertical script, and also for making vertical table headers.\n\n**Syntax**: `mixed | upright | sideways`\n\n**Initial value**: `mixed`
     *
     * 参考定义: "#/definitions/Property.TextOrientation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mixed"},{"type":"string","const":"sideways"},{"type":"string","const":"upright"}]
     *
     * @see Property_TextOrientation
     */
    @Const({"mixed", "sideways", "upright"})
    @Schema(title = "The **`text-orientation`** CSS property sets the orientation of the text characters in a line. It only affects text in vertical mode (when `writing-mode` is not `horizontal-tb`). It is useful for controlling the display of languages that use vertical script, and also for making vertical table headers.\n\n**Syntax**: `mixed | upright | sideways`\n\n**Initial value**: `mixed`")
    String WebkitTextOrientation() default "	";

    /**
     * The **`text-size-adjust`** CSS property controls the text inflation algorithm used on some smartphones and tablets. Other browsers will ignore this property.\n\n**Syntax**: `none | auto | <percentage>`\n\n**Initial value**: `auto` for smartphone browsers supporting inflation, `none` in other cases (and then not modifiable).
     *
     * 参考定义: "#/definitions/Property.TextSizeAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextSizeAdjust
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`text-size-adjust`** CSS property controls the text inflation algorithm used on some smartphones and tablets. Other browsers will ignore this property.\n\n**Syntax**: `none | auto | <percentage>`\n\n**Initial value**: `auto` for smartphone browsers supporting inflation, `none` in other cases (and then not modifiable).")
    String WebkitTextSizeAdjust() default "	";

    /**
     * The **`-webkit-text-stroke-color`** CSS property specifies the stroke color of characters of text. If this property is not set, the value of the `color` property is used.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`
     *
     * 参考定义: "#/definitions/Property.WebkitTextStrokeColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_WebkitTextStrokeColor
     */

    @Schema(title = "The **`-webkit-text-stroke-color`** CSS property specifies the stroke color of characters of text. If this property is not set, the value of the `color` property is used.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`")
    String WebkitTextStrokeColor() default "	";

    /**
     * The **`-webkit-text-stroke-width`** CSS property specifies the width of the stroke for text.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`
     *
     * 参考定义: "#/definitions/Property.WebkitTextStrokeWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_WebkitTextStrokeWidth
     */

    @Schema(title = "The **`-webkit-text-stroke-width`** CSS property specifies the width of the stroke for text.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`")
    String WebkitTextStrokeWidth() default "	";

    /**
     * The **`text-underline-position`** CSS property specifies the position of the underline which is set using the `text-decoration` property's `underline` value.\n\n**Syntax**: `auto | from-font | [ under || [ left | right ] ]`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.TextUnderlinePosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"from-font"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"under"},{"type":"string"}]
     *
     * @see Property_TextUnderlinePosition
     */
    @Const({"auto", "from-font", "left", "right", "under"})
    @Schema(title = "The **`text-underline-position`** CSS property specifies the position of the underline which is set using the `text-decoration` property's `underline` value.\n\n**Syntax**: `auto | from-font | [ under || [ left | right ] ]`\n\n**Initial value**: `auto`")
    String WebkitTextUnderlinePosition() default "	";

    /**
     * The `-webkit-touch-callout` CSS property controls the display of the default callout shown when you touch and hold a touch target.\n\n**Syntax**: `default | none`\n\n**Initial value**: `default`
     *
     * 参考定义: "#/definitions/Property.WebkitTouchCallout"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"default"},{"type":"string","const":"none"}]
     *
     * @see Property_WebkitTouchCallout
     */
    @Const({"default", "none"})
    @Schema(title = "The `-webkit-touch-callout` CSS property controls the display of the default callout shown when you touch and hold a touch target.\n\n**Syntax**: `default | none`\n\n**Initial value**: `default`")
    String WebkitTouchCallout() default "	";

    /**
     * The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.Transform"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Transform
     */
    @Const({"none"})
    @Schema(title = "The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`")
    String WebkitTransform() default "	";

    /**
     * The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`
     *
     * 参考定义: "#/definitions/Property.TransformOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
     *
     * @see Property_TransformOrigin
     */
    @Const({"bottom", "center", "left", "right", "top"})
    @Schema(title = "The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`")
    String WebkitTransformOrigin() default "	";

    /**
     * The **`transform-style`** CSS property sets whether children of an element are positioned in the 3D space or are flattened in the plane of the element.\n\n**Syntax**: `flat | preserve-3d`\n\n**Initial value**: `flat`
     *
     * 参考定义: "#/definitions/Property.TransformStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"flat"},{"type":"string","const":"preserve-3d"}]
     *
     * @see Property_TransformStyle
     */
    @Const({"flat", "preserve-3d"})
    @Schema(title = "The **`transform-style`** CSS property sets whether children of an element are positioned in the 3D space or are flattened in the plane of the element.\n\n**Syntax**: `flat | preserve-3d`\n\n**Initial value**: `flat`")
    String WebkitTransformStyle() default "	";

    /**
     * The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDelay
     */

    @Schema(title = "The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String WebkitTransitionDelay() default "	";

    /**
     * The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`
     *
     * 参考定义: "#/definitions/Property.TransitionDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDuration
     */

    @Schema(title = "The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`")
    String WebkitTransitionDuration() default "	";

    /**
     * The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all
     *
     * 参考定义: "#/definitions/Property.TransitionProperty"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TransitionProperty
     */
    @Const({"all", "none"})
    @Schema(title = "The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all")
    String WebkitTransitionProperty() default "	";

    /**
     * The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`
     *
     * 参考定义: "#/definitions/Property.TransitionTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_TransitionTimingFunction
     */

    @Schema(title = "The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`")
    String WebkitTransitionTimingFunction() default "	";

    /**
     * **Syntax**: `read-only | read-write | read-write-plaintext-only`\n\n**Initial value**: `read-only`
     *
     * 参考定义: "#/definitions/Property.WebkitUserModify"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"read-only"},{"type":"string","const":"read-write"},{"type":"string","const":"read-write-plaintext-only"}]
     *
     * @see Property_WebkitUserModify
     */
    @Const({"read-only", "read-write", "read-write-plaintext-only"})
    @Schema(title = "**Syntax**: `read-only | read-write | read-write-plaintext-only`\n\n**Initial value**: `read-only`")
    String WebkitUserModify() default "	";

    /**
     * The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`
     *
     * 参考定义: "#/definitions/Property.UserSelect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-none"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"element"},{"type":"string","const":"none"},{"type":"string","const":"text"}]
     *
     * @see Property_UserSelect
     */
    @Const({"-moz-none", "all", "auto", "contain", "element", "none", "text"})
    @Schema(title = "The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`")
    String WebkitUserSelect() default "	";

    /**
     * The **`writing-mode`** CSS property sets whether lines of text are laid out horizontally or vertically, as well as the direction in which blocks progress. When set for an entire document, it should be set on the root element (`html` element for HTML documents).\n\n**Syntax**: `horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr`\n\n**Initial value**: `horizontal-tb`
     *
     * 参考定义: "#/definitions/Property.WritingMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"horizontal-tb"},{"type":"string","const":"sideways-lr"},{"type":"string","const":"sideways-rl"},{"type":"string","const":"vertical-lr"},{"type":"string","const":"vertical-rl"}]
     *
     * @see Property_WritingMode
     */
    @Const({"horizontal-tb", "sideways-lr", "sideways-rl", "vertical-lr", "vertical-rl"})
    @Schema(title = "The **`writing-mode`** CSS property sets whether lines of text are laid out horizontally or vertically, as well as the direction in which blocks progress. When set for an entire document, it should be set on the root element (`html` element for HTML documents).\n\n**Syntax**: `horizontal-tb | vertical-rl | vertical-lr | sideways-rl | sideways-lr`\n\n**Initial value**: `horizontal-tb`")
    String WebkitWritingMode() default "	";

    /**
     * The `**all**` shorthand CSS property resets all of an element's properties except `unicode-bidi`, `direction`, and CSS Custom Properties. It can set properties to their initial or inherited values, or to the values specified in another stylesheet origin.\n\n**Syntax**: `initial | inherit | unset | revert`\n\n**Initial value**: There is no practical initial value for it.\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **37** | **27**  | **9.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.All"
     *
     *
     *
     *
     *
     * @see Property_All
     */

    @Schema(title = "The `**all**` shorthand CSS property resets all of an element's properties except `unicode-bidi`, `direction`, and CSS Custom Properties. It can set properties to their initial or inherited values, or to the values specified in another stylesheet origin.\n\n**Syntax**: `initial | inherit | unset | revert`\n\n**Initial value**: There is no practical initial value for it.\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **37** | **27**  | **9.1** | **79** | No  |")
    String all() default "	";

    /**
     * The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.Animation%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimation%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Animation
     */

    @Schema(title = "The **`animation`** shorthand CSS property applies an animation between styles. It is a shorthand for `animation-name`, `animation-duration`, `animation-timing-function`, `animation-delay`, `animation-iteration-count`, `animation-direction`, `animation-fill-mode`, and `animation-play-state`.\n\n**Syntax**: `<single-animation>#`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animation() default "	";

    /**
     * The **`background`** shorthand CSS property sets all background style properties at once, such as color, image, origin and size, or repeat method.\n\n**Syntax**: `[ <bg-layer> , ]* <final-bg-layer>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Background%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.FinalBgLayer%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_Background
     */

    @Schema(title = "The **`background`** shorthand CSS property sets all background style properties at once, such as color, image, origin and size, or repeat method.\n\n**Syntax**: `[ <bg-layer> , ]* <final-bg-layer>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String background() default "	";

    /**
     * The **`background-position`** CSS property sets the initial position for each background image. The position is relative to the position layer set by `background-origin`.\n\n**Syntax**: `<bg-position>#`\n\n**Initial value**: `0% 0%`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BackgroundPosition%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgPosition%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_BackgroundPosition
     */

    @Schema(title = "The **`background-position`** CSS property sets the initial position for each background image. The position is relative to the position layer set by `background-origin`.\n\n**Syntax**: `<bg-position>#`\n\n**Initial value**: `0% 0%`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String backgroundPosition() default "	";

    /**
     * The **`border`** shorthand CSS property sets an element's border. It sets the values of `border-width`, `border-style`, and `border-color`.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Border%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_Border
     */

    @Schema(title = "The **`border`** shorthand CSS property sets an element's border. It sets the values of `border-width`, `border-style`, and `border-color`.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String border() default "	";

    /**
     * The **`border-block`** CSS property is a shorthand property for setting the individual logical block border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderBlock
     */

    @Schema(title = "The **`border-block`** CSS property is a shorthand property for setting the individual logical block border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderBlock() default "	";

    /**
     * The **`border-block-end`** CSS property is a shorthand property for setting the individual logical block-end border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderBlockEnd
     */

    @Schema(title = "The **`border-block-end`** CSS property is a shorthand property for setting the individual logical block-end border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockEnd() default "	";

    /**
     * The **`border-block-start`** CSS property is a shorthand property for setting the individual logical block-start border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderBlockStart
     */

    @Schema(title = "The **`border-block-start`** CSS property is a shorthand property for setting the individual logical block-start border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockStart() default "	";

    /**
     * The **`border-bottom`** shorthand CSS property sets an element's bottom border. It sets the values of `border-bottom-width`, `border-bottom-style` and `border-bottom-color`.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderBottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderBottom
     */

    @Schema(title = "The **`border-bottom`** shorthand CSS property sets an element's bottom border. It sets the values of `border-bottom-width`, `border-bottom-style` and `border-bottom-color`.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderBottom() default "	";

    /**
     * The **`border-color`** shorthand CSS property sets the color of an element's border.\n\n**Syntax**: `<color>{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderColor
     */

    @Schema(title = "The **`border-color`** shorthand CSS property sets the color of an element's border.\n\n**Syntax**: `<color>{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderColor() default "	";

    /**
     * The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`\n\n| Chrome  |  Firefox  | Safari  |  Edge  |   IE   | | :-----: | :-------: | :-----: | :----: | :----: | | **16**  |  **15**   |  **6**  | **12** | **11** | | 7 _-x-_ | 3.5 _-x-_ | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.BorderImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImage
     */
    @Const({"none", "repeat", "round", "space", "stretch"})
    @Schema(title = "The **`border-image`** CSS property draws an image around a given element. It replaces the element's regular border.\n\n**Syntax**: `<'border-image-source'> || <'border-image-slice'> [ / <'border-image-width'> | / <'border-image-width'>? / <'border-image-outset'> ]? || <'border-image-repeat'>`\n\n| Chrome  |  Firefox  | Safari  |  Edge  |   IE   | | :-----: | :-------: | :-----: | :----: | :----: | | **16**  |  **15**   |  **6**  | **12** | **11** | | 7 _-x-_ | 3.5 _-x-_ | 3 _-x-_ |        |        |")
    String borderImage() default "	";

    /**
     * The **`border-inline`** CSS property is a shorthand property for setting the individual logical inline border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderInline
     */

    @Schema(title = "The **`border-inline`** CSS property is a shorthand property for setting the individual logical inline border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderInline() default "	";

    /**
     * The **`border-inline-end`** CSS property is a shorthand property for setting the individual logical inline-end border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderInlineEnd
     */

    @Schema(title = "The **`border-inline-end`** CSS property is a shorthand property for setting the individual logical inline-end border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderInlineEnd() default "	";

    /**
     * The **`border-inline-start`** CSS property is a shorthand property for setting the individual logical inline-start border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderInlineStart
     */

    @Schema(title = "The **`border-inline-start`** CSS property is a shorthand property for setting the individual logical inline-start border property values in a single place in the style sheet.\n\n**Syntax**: `<'border-top-width'> || <'border-top-style'> || <color>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderInlineStart() default "	";

    /**
     * The **`border-left`** shorthand CSS property sets all the properties of an element's left border.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderLeft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderLeft
     */

    @Schema(title = "The **`border-left`** shorthand CSS property sets all the properties of an element's left border.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderLeft() default "	";

    /**
     * The **`border-radius`** CSS property rounds the corners of an element's outer border edge. You can set a single radius to make circular corners, or two radii to make elliptical corners.\n\n**Syntax**: `<length-percentage>{1,4} [ / <length-percentage>{1,4} ]?`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BorderRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderRadius
     */

    @Schema(title = "The **`border-radius`** CSS property rounds the corners of an element's outer border edge. You can set a single radius to make circular corners, or two radii to make elliptical corners.\n\n**Syntax**: `<length-percentage>{1,4} [ / <length-percentage>{1,4} ]?`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String borderRadius() default "	";

    /**
     * The **`border-right`** shorthand CSS property sets all the properties of an element's right border.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.BorderRight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderRight
     */

    @Schema(title = "The **`border-right`** shorthand CSS property sets all the properties of an element's right border.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String borderRight() default "	";

    /**
     * The **`border-style`** shorthand CSS property sets the line style for all four sides of an element's border.\n\n**Syntax**: `<line-style>{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string"}]
     *
     * @see Property_BorderStyle
     */

    @Schema(title = "The **`border-style`** shorthand CSS property sets the line style for all four sides of an element's border.\n\n**Syntax**: `<line-style>{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderStyle() default "	";

    /**
     * The **`border-top`** shorthand CSS property sets all the properties of an element's top border.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderTop%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderTop
     */

    @Schema(title = "The **`border-top`** shorthand CSS property sets all the properties of an element's top border.\n\n**Syntax**: `<line-width> || <line-style> || <color>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderTop() default "	";

    /**
     * The **`border-width`** shorthand CSS property sets the width of an element's border.\n\n**Syntax**: `<line-width>{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_BorderWidth
     */

    @Schema(title = "The **`border-width`** shorthand CSS property sets the width of an element's border.\n\n**Syntax**: `<line-width>{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderWidth() default "	";

    /**
     * The **`column-rule`** shorthand CSS property sets the width, style, and color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'column-rule-width'> || <'column-rule-style'> || <'column-rule-color'>`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnRule%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_ColumnRule
     */

    @Schema(title = "The **`column-rule`** shorthand CSS property sets the width, style, and color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'column-rule-width'> || <'column-rule-style'> || <'column-rule-color'>`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |")
    String columnRule() default "	";

    /**
     * The **`columns`** CSS shorthand property sets the number of columns to use when drawing an element's contents, as well as those columns' widths.\n\n**Syntax**: `<'column-width'> || <'column-count'>`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE   | | :----: | :-----: | :-----: | :----: | :----: | | **50** | **52**  |  **9**  | **12** | **10** | |        |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.Columns%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Columns
     */
    @Const({"auto"})
    @Schema(title = "The **`columns`** CSS shorthand property sets the number of columns to use when drawing an element's contents, as well as those columns' widths.\n\n**Syntax**: `<'column-width'> || <'column-count'>`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE   | | :----: | :-----: | :-----: | :----: | :----: | | **50** | **52**  |  **9**  | **12** | **10** | |        |         | 3 _-x-_ |        |        |")
    String columns() default "	";

    /**
     * The **`flex`** CSS shorthand property sets how a flex _item_ will grow or shrink to fit the space available in its flex container.\n\n**Syntax**: `none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]`\n\n|  Chrome  | Firefox | Safari  |  Edge  |    IE    | | :------: | :-----: | :-----: | :----: | :------: | |  **29**  | **20**  |  **9**  | **12** |  **11**  | | 21 _-x-_ |         | 7 _-x-_ |        | 10 _-x-_ |
     *
     * 参考定义: "#/definitions/Property.Flex%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"content"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_Flex
     */
    @Const({"auto", "content", "fit-content", "max-content", "min-content", "none"})
    @Schema(title = "The **`flex`** CSS shorthand property sets how a flex _item_ will grow or shrink to fit the space available in its flex container.\n\n**Syntax**: `none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]`\n\n|  Chrome  | Firefox | Safari  |  Edge  |    IE    | | :------: | :-----: | :-----: | :----: | :------: | |  **29**  | **20**  |  **9**  | **12** |  **11**  | | 21 _-x-_ |         | 7 _-x-_ |        | 10 _-x-_ |")
    String flex() default "	";

    /**
     * The **`flex-flow`** CSS shorthand property specifies the direction of a flex container, as well as its wrapping behavior.\n\n**Syntax**: `<'flex-direction'> || <'flex-wrap'>`\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **28**  |  **9**  | **12** | **11** | | 21 _-x-_ |         | 7 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.FlexFlow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"nowrap"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"},{"type":"string","const":"wrap"},{"type":"string","const":"wrap-reverse"},{"type":"string"}]
     *
     * @see Property_FlexFlow
     */
    @Const({"column", "column-reverse", "nowrap", "row", "row-reverse", "wrap", "wrap-reverse"})
    @Schema(title = "The **`flex-flow`** CSS shorthand property specifies the direction of a flex container, as well as its wrapping behavior.\n\n**Syntax**: `<'flex-direction'> || <'flex-wrap'>`\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **28**  |  **9**  | **12** | **11** | | 21 _-x-_ |         | 7 _-x-_ |        |        |")
    String flexFlow() default "	";

    /**
     * The **`gap`** CSS property sets the gaps (gutters) between rows and columns. It is a shorthand for `row-gap` and `column-gap`.\n\n**Syntax**: `<'row-gap'> <'column-gap'>?`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **84** | **63**  | **14.1** | **84** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n|     Chrome      |     Firefox     |      Safari       |  Edge  | IE  | | :-------------: | :-------------: | :---------------: | :----: | :-: | |     **66**      |     **61**      |      **12**       | **16** | No  | | 57 _(grid-gap)_ | 52 _(grid-gap)_ | 10.1 _(grid-gap)_ |        |     |\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **66** | **61**  |   No   | **16** | No  |\n\n---
     *
     * 参考定义: "#/definitions/Property.Gap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_Gap
     */
    @Const({"normal"})
    @Schema(title = "The **`gap`** CSS property sets the gaps (gutters) between rows and columns. It is a shorthand for `row-gap` and `column-gap`.\n\n**Syntax**: `<'row-gap'> <'column-gap'>?`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **84** | **63**  | **14.1** | **84** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n|     Chrome      |     Firefox     |      Safari       |  Edge  | IE  | | :-------------: | :-------------: | :---------------: | :----: | :-: | |     **66**      |     **61**      |      **12**       | **16** | No  | | 57 _(grid-gap)_ | 52 _(grid-gap)_ | 10.1 _(grid-gap)_ |        |     |\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **66** | **61**  |   No   | **16** | No  |\n\n---")
    String gap() default "	";

    /**
     * The **`grid`** CSS property is a shorthand property that sets all of the explicit and implicit grid properties in a single declaration.\n\n**Syntax**: `<'grid-template'> | <'grid-template-rows'> / [ auto-flow && dense? ] <'grid-auto-columns'>? | [ auto-flow && dense? ] <'grid-auto-rows'>? / <'grid-template-columns'>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.Grid"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Grid
     */
    @Const({"none"})
    @Schema(title = "The **`grid`** CSS property is a shorthand property that sets all of the explicit and implicit grid properties in a single declaration.\n\n**Syntax**: `<'grid-template'> | <'grid-template-rows'> / [ auto-flow && dense? ] <'grid-auto-columns'>? | [ auto-flow && dense? ] <'grid-auto-rows'>? / <'grid-template-columns'>`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String grid() default "	";

    /**
     * The **`grid-area`** CSS shorthand property specifies a grid item’s size and location within a grid by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the edges of its grid area.\n\n**Syntax**: `<grid-line> [ / <grid-line> ]{0,3}`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridArea"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"},{"type":"string"}]
     *
     * @see Property_GridArea
     */

    @Schema(title = "The **`grid-area`** CSS shorthand property specifies a grid item’s size and location within a grid by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the edges of its grid area.\n\n**Syntax**: `<grid-line> [ / <grid-line> ]{0,3}`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridArea() default "	";

    /**
     * The **`grid-column`** CSS shorthand property specifies a grid item's size and location within a grid column by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start and inline-end edge of its grid area.\n\n**Syntax**: `<grid-line> [ / <grid-line> ]?`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridColumn"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"},{"type":"string"}]
     *
     * @see Property_GridColumn
     */

    @Schema(title = "The **`grid-column`** CSS shorthand property specifies a grid item's size and location within a grid column by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start and inline-end edge of its grid area.\n\n**Syntax**: `<grid-line> [ / <grid-line> ]?`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridColumn() default "	";

    /**
     * The **`grid-row`** CSS shorthand property specifies a grid item’s size and location within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start and inline-end edge of its grid area.\n\n**Syntax**: `<grid-line> [ / <grid-line> ]?`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridRow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"},{"type":"string"}]
     *
     * @see Property_GridRow
     */

    @Schema(title = "The **`grid-row`** CSS shorthand property specifies a grid item’s size and location within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start and inline-end edge of its grid area.\n\n**Syntax**: `<grid-line> [ / <grid-line> ]?`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridRow() default "	";

    /**
     * The **`grid-template`** CSS property is a shorthand property for defining grid columns, rows, and areas.\n\n**Syntax**: `none | [ <'grid-template-rows'> / <'grid-template-columns'> ] | [ <line-names>? <string> <track-size>? <line-names>? ]+ [ / <explicit-track-list> ]?`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridTemplate"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_GridTemplate
     */
    @Const({"none"})
    @Schema(title = "The **`grid-template`** CSS property is a shorthand property for defining grid columns, rows, and areas.\n\n**Syntax**: `none | [ <'grid-template-rows'> / <'grid-template-columns'> ] | [ <line-names>? <string> <track-size>? <line-names>? ]+ [ / <explicit-track-list> ]?`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridTemplate() default "	";

    /**
     * **Syntax**: `none | <integer>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.LineClamp"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_LineClamp
     */
    @Const({"none"})
    @Schema(title = "**Syntax**: `none | <integer>`\n\n**Initial value**: `none`")
    String lineClamp() default "	";

    /**
     * The **`list-style`** CSS shorthand property allows you set all the list style properties at once.\n\n**Syntax**: `<'list-style-type'> || <'list-style-position'> || <'list-style-image'>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.ListStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inside"},{"type":"string","const":"none"},{"type":"string","const":"outside"},{"type":"string"}]
     *
     * @see Property_ListStyle
     */
    @Const({"inside", "none", "outside"})
    @Schema(title = "The **`list-style`** CSS shorthand property allows you set all the list style properties at once.\n\n**Syntax**: `<'list-style-type'> || <'list-style-position'> || <'list-style-image'>`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String listStyle() default "	";

    /**
     * The **`margin`** CSS property sets the margin area on all four sides of an element. It is a shorthand for `margin-top`, `margin-right`, `margin-bottom`, and `margin-left`.\n\n**Syntax**: `[ <length> | <percentage> | auto ]{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.Margin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Margin
     */
    @Const({"auto"})
    @Schema(title = "The **`margin`** CSS property sets the margin area on all four sides of an element. It is a shorthand for `margin-top`, `margin-right`, `margin-bottom`, and `margin-left`.\n\n**Syntax**: `[ <length> | <percentage> | auto ]{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String margin() default "	";

    /**
     * The **`mask-border`** CSS shorthand property lets you create a mask along the edge of an element's border.\n\n**Syntax**: `<'mask-border-source'> || <'mask-border-slice'> [ / <'mask-border-width'>? [ / <'mask-border-outset'> ]? ]? || <'mask-border-repeat'> || <'mask-border-mode'>`\n\n|              Chrome              | Firefox |               Safari               |               Edge                | IE  | | :------------------------------: | :-----: | :--------------------------------: | :-------------------------------: | :-: | | **1** _(-webkit-mask-box-image)_ |   No    | **3.1** _(-webkit-mask-box-image)_ | **79** _(-webkit-mask-box-image)_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskBorder"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alpha"},{"type":"string","const":"luminance"},{"type":"string","const":"none"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MaskBorder
     */
    @Const({"alpha", "luminance", "none", "repeat", "round", "space", "stretch"})
    @Schema(title = "The **`mask-border`** CSS shorthand property lets you create a mask along the edge of an element's border.\n\n**Syntax**: `<'mask-border-source'> || <'mask-border-slice'> [ / <'mask-border-width'>? [ / <'mask-border-outset'> ]? ]? || <'mask-border-repeat'> || <'mask-border-mode'>`\n\n|              Chrome              | Firefox |               Safari               |               Edge                | IE  | | :------------------------------: | :-----: | :--------------------------------: | :-------------------------------: | :-: | | **1** _(-webkit-mask-box-image)_ |   No    | **3.1** _(-webkit-mask-box-image)_ | **79** _(-webkit-mask-box-image)_ | No  |")
    String maskBorder() default "	";

    /**
     * The **`offset`** CSS shorthand property sets all the properties required for animating an element along a defined path.\n\n**Syntax**: `[ <'offset-position'>? [ <'offset-path'> [ <'offset-distance'> || <'offset-rotate'> ]? ]? ]! [ / <'offset-anchor'> ]?`\n\n|    Chrome     | Firefox | Safari |  Edge  | IE  | | :-----------: | :-----: | :----: | :----: | :-: | |    **55**     | **72**  |   No   | **79** | No  | | 46 _(motion)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.Offset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Offset
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`offset`** CSS shorthand property sets all the properties required for animating an element along a defined path.\n\n**Syntax**: `[ <'offset-position'>? [ <'offset-path'> [ <'offset-distance'> || <'offset-rotate'> ]? ]? ]! [ / <'offset-anchor'> ]?`\n\n|    Chrome     | Firefox | Safari |  Edge  | IE  | | :-----------: | :-----: | :----: | :----: | :-: | |    **55**     | **72**  |   No   | **79** | No  | | 46 _(motion)_ |         |        |        |     |")
    String motion() default "	";

    /**
     * The **`offset`** CSS shorthand property sets all the properties required for animating an element along a defined path.\n\n**Syntax**: `[ <'offset-position'>? [ <'offset-path'> [ <'offset-distance'> || <'offset-rotate'> ]? ]? ]! [ / <'offset-anchor'> ]?`\n\n|    Chrome     | Firefox | Safari |  Edge  | IE  | | :-----------: | :-----: | :----: | :----: | :-: | |    **55**     | **72**  |   No   | **79** | No  | | 46 _(motion)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.Offset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Offset
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`offset`** CSS shorthand property sets all the properties required for animating an element along a defined path.\n\n**Syntax**: `[ <'offset-position'>? [ <'offset-path'> [ <'offset-distance'> || <'offset-rotate'> ]? ]? ]! [ / <'offset-anchor'> ]?`\n\n|    Chrome     | Firefox | Safari |  Edge  | IE  | | :-----------: | :-----: | :----: | :----: | :-: | |    **55**     | **72**  |   No   | **79** | No  | | 46 _(motion)_ |         |        |        |     |")
    String offset() default "	";

    /**
     * The **`outline`** CSS shorthand property set all the outline properties in a single declaration.\n\n**Syntax**: `[ <'outline-color'> || <'outline-style'> || <'outline-width'> ]`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.Outline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"$ref":"#/definitions/DataType.LineStyle"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string","const":"auto"},{"type":"string","const":"invert"},{"type":"string"}]
     *
     * @see Property_Outline
     */
    @Const({"auto", "invert"})
    @Schema(title = "The **`outline`** CSS shorthand property set all the outline properties in a single declaration.\n\n**Syntax**: `[ <'outline-color'> || <'outline-style'> || <'outline-width'> ]`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |")
    String outline() default "	";

    /**
     * The **`overscroll-behavior`** CSS property sets what a browser does when reaching the boundary of a scrolling area. It's a shorthand for `overscroll-behavior-x` and `overscroll-behavior-y`.\n\n**Syntax**: `[ contain | none | auto ]{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **63** | **59**  |   No   | **18** | No  |
     *
     * 参考定义: "#/definitions/Property.OverscrollBehavior"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_OverscrollBehavior
     */
    @Const({"auto", "contain", "none"})
    @Schema(title = "The **`overscroll-behavior`** CSS property sets what a browser does when reaching the boundary of a scrolling area. It's a shorthand for `overscroll-behavior-x` and `overscroll-behavior-y`.\n\n**Syntax**: `[ contain | none | auto ]{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **63** | **59**  |   No   | **18** | No  |")
    String overscrollBehavior() default "	";

    /**
     * The **`padding`** CSS shorthand property sets the padding area on all four sides of an element at once.\n\n**Syntax**: `[ <length> | <percentage> ]{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Padding%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_Padding
     */

    @Schema(title = "The **`padding`** CSS shorthand property sets the padding area on all four sides of an element at once.\n\n**Syntax**: `[ <length> | <percentage> ]{1,4}`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String padding() default "	";

    /**
     * The CSS **`place-items`** shorthand property allows you to align items along both the block and inline directions at once (i.e. the `align-items` and `justify-items` properties) in a relevant layout system such as Grid or Flexbox. If the second value is not set, the first value is also used for it.\n\n**Syntax**: `<'align-items'> <'justify-items'>?`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **59** | **45**  | **11** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.PlaceItems"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_PlaceItems
     */
    @Const({"baseline", "normal", "stretch"})
    @Schema(title = "The CSS **`place-items`** shorthand property allows you to align items along both the block and inline directions at once (i.e. the `align-items` and `justify-items` properties) in a relevant layout system such as Grid or Flexbox. If the second value is not set, the first value is also used for it.\n\n**Syntax**: `<'align-items'> <'justify-items'>?`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **59** | **45**  | **11** | **79** | No  |")
    String placeItems() default "	";

    /**
     * The **`place-self`** CSS shorthand property allows you to align an individual item in both the block and inline directions at once (i.e. the `align-self` and `justify-self` properties) in a relevant layout system such as Grid or Flexbox. If the second value is not present, the first value is also used for it.\n\n**Syntax**: `<'align-self'> <'justify-self'>?`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **59** | **45**  | **11** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.PlaceSelf"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_PlaceSelf
     */
    @Const({"auto", "baseline", "normal", "stretch"})
    @Schema(title = "The **`place-self`** CSS shorthand property allows you to align an individual item in both the block and inline directions at once (i.e. the `align-self` and `justify-self` properties) in a relevant layout system such as Grid or Flexbox. If the second value is not present, the first value is also used for it.\n\n**Syntax**: `<'align-self'> <'justify-self'>?`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **59** | **45**  | **11** | **79** | No  |")
    String placeSelf() default "	";

    /**
     * The **`text-emphasis`** CSS property applies emphasis marks to text (except spaces and control characters). It is a shorthand for `text-emphasis-style` and `text-emphasis-color`.\n\n**Syntax**: `<'text-emphasis-style'> || <'text-emphasis-color'>`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.TextEmphasis"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"circle"},{"type":"string","const":"dot"},{"type":"string","const":"double-circle"},{"type":"string","const":"filled"},{"type":"string","const":"none"},{"type":"string","const":"open"},{"type":"string","const":"sesame"},{"type":"string","const":"triangle"},{"type":"string"}]
     *
     * @see Property_TextEmphasis
     */
    @Const({"circle", "dot", "double-circle", "filled", "none", "open", "sesame", "triangle"})
    @Schema(title = "The **`text-emphasis`** CSS property applies emphasis marks to text (except spaces and control characters). It is a shorthand for `text-emphasis-style` and `text-emphasis-color`.\n\n**Syntax**: `<'text-emphasis-style'> || <'text-emphasis-color'>`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |")
    String textEmphasis() default "	";

    /**
     * The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.Transition%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleTransition%3Cstring%3E"},{"type":"string"}]
     *
     * @see Property_Transition
     */

    @Schema(title = "The **`transition`** CSS property is a shorthand property for `transition-property`, `transition-duration`, `transition-timing-function`, and `transition-delay`.\n\n**Syntax**: `<single-transition>#`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |")
    String transition() default "	";

    /**
     * The **`accent-color`** CSS property sets the color of the elements accent. An accent appears in elements such as `<input>` of `type=checkbox`, or `type=radio`.\n\n**Syntax**: `auto | <color>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **93** | **92**  |   No   | **93** | No  |
     *
     * 参考定义: "#/definitions/Property.AccentColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"auto"}]
     *
     * @see Property_AccentColor
     */
    @Const({"auto"})
    @Schema(title = "The **`accent-color`** CSS property sets the color of the elements accent. An accent appears in elements such as `<input>` of `type=checkbox`, or `type=radio`.\n\n**Syntax**: `auto | <color>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **93** | **92**  |   No   | **93** | No  |")
    String accentColor() default "	";

    /**
     * The CSS **`align-content`** property sets the distribution of space between and around content items along a flexbox's cross-axis or a grid's block axis.\n\n**Syntax**: `normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position>`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **28**  |  **9**  | **12** | **11** | | 21 _-x-_ |         | 7 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |\n\n---
     *
     * 参考定义: "#/definitions/Property.AlignContent"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_AlignContent
     */
    @Const({"baseline", "normal"})
    @Schema(title = "The CSS **`align-content`** property sets the distribution of space between and around content items along a flexbox's cross-axis or a grid's block axis.\n\n**Syntax**: `normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position>`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **28**  |  **9**  | **12** | **11** | | 21 _-x-_ |         | 7 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |\n\n---")
    String alignContent() default "	";

    /**
     * The CSS **`align-items`** property sets the `align-self` value on all direct children as a group. In Flexbox, it controls the alignment of items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis within their grid area.\n\n**Syntax**: `normal | stretch | <baseline-position> | [ <overflow-position>? <self-position> ]`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **52**  | **20**  |  **9**  | **12** | **11** | | 21 _-x-_ |         | 7 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |\n\n---
     *
     * 参考定义: "#/definitions/Property.AlignItems"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_AlignItems
     */
    @Const({"baseline", "normal", "stretch"})
    @Schema(title = "The CSS **`align-items`** property sets the `align-self` value on all direct children as a group. In Flexbox, it controls the alignment of items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis within their grid area.\n\n**Syntax**: `normal | stretch | <baseline-position> | [ <overflow-position>? <self-position> ]`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **52**  | **20**  |  **9**  | **12** | **11** | | 21 _-x-_ |         | 7 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |\n\n---")
    String alignItems() default "	";

    /**
     * The **`align-self`** CSS property overrides a grid or flex item's `align-items` value. In Grid, it aligns the item inside the grid area. In Flexbox, it aligns the item on the cross axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? <self-position>`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **36**  | **20**  |   **9**   | **12** | **11** | | 21 _-x-_ |         | 6.1 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  |      IE      | | :----: | :-----: | :------: | :----: | :----------: | | **57** | **52**  | **10.1** | **16** | **10** _-x-_ |\n\n---
     *
     * 参考定义: "#/definitions/Property.AlignSelf"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_AlignSelf
     */
    @Const({"auto", "baseline", "normal", "stretch"})
    @Schema(title = "The **`align-self`** CSS property overrides a grid or flex item's `align-items` value. In Grid, it aligns the item inside the grid area. In Flexbox, it aligns the item on the cross axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? <self-position>`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **36**  | **20**  |   **9**   | **12** | **11** | | 21 _-x-_ |         | 6.1 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  |      IE      | | :----: | :-----: | :------: | :----: | :----------: | | **57** | **52**  | **10.1** | **16** | **10** _-x-_ |\n\n---")
    String alignSelf() default "	";

    /**
     * The **`align-tracks`** CSS property sets the alignment in the masonry axis for grid containers that have masonry in their block axis.\n\n**Syntax**: `[ normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position> ]#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   |   n/a   |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.AlignTracks"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_AlignTracks
     */
    @Const({"baseline", "normal"})
    @Schema(title = "The **`align-tracks`** CSS property sets the alignment in the masonry axis for grid containers that have masonry in their block axis.\n\n**Syntax**: `[ normal | <baseline-position> | <content-distribution> | <overflow-position>? <content-position> ]#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   |   n/a   |   No   |  No  | No  |")
    String alignTracks() default "	";

    /**
     * The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDelay
     */

    @Schema(title = "The **`animation-delay`** CSS property specifies the amount of time to wait from applying the animation to an element before beginning to perform the animation. The animation can start later, immediately from its beginning, or immediately and partway through the animation.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationDelay() default "	";

    /**
     * The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationDirection"},{"type":"string"}]
     *
     * @see Property_AnimationDirection
     */

    @Schema(title = "The **`animation-direction`** CSS property sets whether an animation should play forward, backward, or alternate back and forth between playing the sequence forward and backward.\n\n**Syntax**: `<single-animation-direction>#`\n\n**Initial value**: `normal`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationDirection() default "	";

    /**
     * The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_AnimationDuration
     */

    @Schema(title = "The **`animation-duration`** CSS property sets the length of time that an animation takes to complete one cycle.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationDuration() default "	";

    /**
     * The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 5 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationFillMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SingleAnimationFillMode"},{"type":"string"}]
     *
     * @see Property_AnimationFillMode
     */

    @Schema(title = "The **`animation-fill-mode`** CSS property sets how a CSS animation applies styles to its target before and after its execution.\n\n**Syntax**: `<single-animation-fill-mode>#`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 5 _-x-_ |        |        |")
    String animationFillMode() default "	";

    /**
     * The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationIterationCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"infinite"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_AnimationIterationCount
     */
    @Const({"infinite"})
    @Schema(title = "The **`animation-iteration-count`** CSS property sets the number of times an animation sequence should be played before stopping.\n\n**Syntax**: `<single-animation-iteration-count>#`\n\n**Initial value**: `1`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationIterationCount() default "	";

    /**
     * The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationName"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_AnimationName
     */
    @Const({"none"})
    @Schema(title = "The **`animation-name`** CSS property specifies the names of one or more `@keyframes` at-rules describing the animation or animations to apply to the element.\n\n**Syntax**: `[ none | <keyframes-name> ]#`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationName() default "	";

    /**
     * The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationPlayState"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"paused"},{"type":"string","const":"running"},{"type":"string"}]
     *
     * @see Property_AnimationPlayState
     */
    @Const({"paused", "running"})
    @Schema(title = "The **`animation-play-state`** CSS property sets whether an animation is running or paused.\n\n**Syntax**: `<single-animation-play-state>#`\n\n**Initial value**: `running`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationPlayState() default "	";

    /**
     * The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.AnimationTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_AnimationTimingFunction
     */

    @Schema(title = "The **`animation-timing-function`** CSS property sets how an animation progresses through the duration of each cycle.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **43**  | **16**  |  **9**  | **12** | **10** | | 3 _-x-_ | 5 _-x-_ | 4 _-x-_ |        |        |")
    String animationTimingFunction() default "	";

    /**
     * The `**appearance**` CSS property is used to display an element using platform-native styling, based on the operating system's theme. The **`-moz-appearance`** and **`-webkit-appearance`** properties are non-standard versions of this property, used (respectively) by Gecko (Firefox) and by WebKit-based (e.g., Safari) and Blink-based (e.g., Chrome, Opera) browsers to achieve the same thing. Note that Firefox and Edge also support **`-webkit-appearance`**, for compatibility reasons.\n\n**Syntax**: `none | auto | textfield | menulist-button | <compat-auto>`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox |   Safari    |   Edge   | IE  | | :-----: | :-----: | :---------: | :------: | :-: | | **84**  | **80**  | **3** _-x-_ |  **84**  | No  | | 1 _-x-_ | 1 _-x-_ |             | 12 _-x-_ |     |
     *
     * 参考定义: "#/definitions/Property.Appearance"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.CompatAuto"},{"type":"string","const":"auto"},{"type":"string","const":"menulist-button"},{"type":"string","const":"none"},{"type":"string","const":"textfield"}]
     *
     * @see Property_Appearance
     */
    @Const({"auto", "menulist-button", "none", "textfield"})
    @Schema(title = "The `**appearance**` CSS property is used to display an element using platform-native styling, based on the operating system's theme. The **`-moz-appearance`** and **`-webkit-appearance`** properties are non-standard versions of this property, used (respectively) by Gecko (Firefox) and by WebKit-based (e.g., Safari) and Blink-based (e.g., Chrome, Opera) browsers to achieve the same thing. Note that Firefox and Edge also support **`-webkit-appearance`**, for compatibility reasons.\n\n**Syntax**: `none | auto | textfield | menulist-button | <compat-auto>`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox |   Safari    |   Edge   | IE  | | :-----: | :-----: | :---------: | :------: | :-: | | **84**  | **80**  | **3** _-x-_ |  **84**  | No  | | 1 _-x-_ | 1 _-x-_ |             | 12 _-x-_ |     |")
    String appearance() default "	";

    /**
     * The **`aspect-ratio`**  CSS property sets a **preferred aspect ratio** for the box, which will be used in the calculation of auto sizes and some other layout functions.\n\n**Syntax**: `auto | <ratio>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **88** | **89**  | **15** | **88** | No  |
     *
     * 参考定义: "#/definitions/Property.AspectRatio"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_AspectRatio
     */
    @Const({"auto"})
    @Schema(title = "The **`aspect-ratio`**  CSS property sets a **preferred aspect ratio** for the box, which will be used in the calculation of auto sizes and some other layout functions.\n\n**Syntax**: `auto | <ratio>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **88** | **89**  | **15** | **88** | No  |")
    String aspectRatio() default "	";

    /**
     * The **`backdrop-filter`** CSS property lets you apply graphical effects such as blurring or color shifting to the area behind an element. Because it applies to everything _behind_ the element, to see the effect you must make the element or its background at least partially transparent.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |   Safari    |  Edge  | IE  | | :----: | :-----: | :---------: | :----: | :-: | | **76** |   n/a   | **9** _-x-_ | **17** | No  |
     *
     * 参考定义: "#/definitions/Property.BackdropFilter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BackdropFilter
     */
    @Const({"none"})
    @Schema(title = "The **`backdrop-filter`** CSS property lets you apply graphical effects such as blurring or color shifting to the area behind an element. Because it applies to everything _behind_ the element, to see the effect you must make the element or its background at least partially transparent.\n\n**Syntax**: `none | <filter-function-list>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |   Safari    |  Edge  | IE  | | :----: | :-----: | :---------: | :----: | :-: | | **76** |   n/a   | **9** _-x-_ | **17** | No  |")
    String backdropFilter() default "	";

    /**
     * The **`backface-visibility`** CSS property sets whether the back face of an element is visible when turned towards the user.\n\n**Syntax**: `visible | hidden`\n\n**Initial value**: `visible`\n\n|  Chrome  | Firefox  |    Safari     |  Edge  |   IE   | | :------: | :------: | :-----------: | :----: | :----: | |  **36**  |  **16**  | **5.1** _-x-_ | **12** | **10** | | 12 _-x-_ | 10 _-x-_ |               |        |        |
     *
     * 参考定义: "#/definitions/Property.BackfaceVisibility"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"hidden"},{"type":"string","const":"visible"}]
     *
     * @see Property_BackfaceVisibility
     */
    @Const({"hidden", "visible"})
    @Schema(title = "The **`backface-visibility`** CSS property sets whether the back face of an element is visible when turned towards the user.\n\n**Syntax**: `visible | hidden`\n\n**Initial value**: `visible`\n\n|  Chrome  | Firefox  |    Safari     |  Edge  |   IE   | | :------: | :------: | :-----------: | :----: | :----: | |  **36**  |  **16**  | **5.1** _-x-_ | **12** | **10** | | 12 _-x-_ | 10 _-x-_ |               |        |        |")
    String backfaceVisibility() default "	";

    /**
     * The **`background-attachment`** CSS property sets whether a background image's position is fixed within the viewport, or scrolls with its containing block.\n\n**Syntax**: `<attachment>#`\n\n**Initial value**: `scroll`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BackgroundAttachment"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Attachment"},{"type":"string"}]
     *
     * @see Property_BackgroundAttachment
     */

    @Schema(title = "The **`background-attachment`** CSS property sets whether a background image's position is fixed within the viewport, or scrolls with its containing block.\n\n**Syntax**: `<attachment>#`\n\n**Initial value**: `scroll`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String backgroundAttachment() default "	";

    /**
     * The **`background-blend-mode`** CSS property sets how an element's background images should blend with each other and with the element's background color.\n\n**Syntax**: `<blend-mode>#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **35** | **30**  | **8**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BackgroundBlendMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BlendMode"},{"type":"string"}]
     *
     * @see Property_BackgroundBlendMode
     */

    @Schema(title = "The **`background-blend-mode`** CSS property sets how an element's background images should blend with each other and with the element's background color.\n\n**Syntax**: `<blend-mode>#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **35** | **30**  | **8**  | **79** | No  |")
    String backgroundBlendMode() default "	";

    /**
     * The **`background-clip`** CSS property sets whether an element's background extends underneath its border box, padding box, or content box.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `border-box`\n\n| Chrome | Firefox |   Safari    |  Edge  |  IE   | | :----: | :-----: | :---------: | :----: | :---: | | **1**  |  **4**  | **3** _-x-_ | **12** | **9** |
     *
     * 参考定义: "#/definitions/Property.BackgroundClip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string"}]
     *
     * @see Property_BackgroundClip
     */

    @Schema(title = "The **`background-clip`** CSS property sets whether an element's background extends underneath its border box, padding box, or content box.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `border-box`\n\n| Chrome | Firefox |   Safari    |  Edge  |  IE   | | :----: | :-----: | :---------: | :----: | :---: | | **1**  |  **4**  | **3** _-x-_ | **12** | **9** |")
    String backgroundClip() default "	";

    /**
     * The **`background-color`** CSS property sets the background color of an element.\n\n**Syntax**: `<color>`\n\n**Initial value**: `transparent`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BackgroundColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BackgroundColor
     */

    @Schema(title = "The **`background-color`** CSS property sets the background color of an element.\n\n**Syntax**: `<color>`\n\n**Initial value**: `transparent`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String backgroundColor() default "	";

    /**
     * The **`background-image`** CSS property sets one or more background images on an element.\n\n**Syntax**: `<bg-image>#`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BackgroundImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BackgroundImage
     */
    @Const({"none"})
    @Schema(title = "The **`background-image`** CSS property sets one or more background images on an element.\n\n**Syntax**: `<bg-image>#`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String backgroundImage() default "	";

    /**
     * The **`background-origin`** CSS property sets the background's origin: from the border start, inside the border, or inside the padding.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `padding-box`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **4**  | **3**  | **12** | **9** |
     *
     * 参考定义: "#/definitions/Property.BackgroundOrigin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string"}]
     *
     * @see Property_BackgroundOrigin
     */

    @Schema(title = "The **`background-origin`** CSS property sets the background's origin: from the border start, inside the border, or inside the padding.\n\n**Syntax**: `<box>#`\n\n**Initial value**: `padding-box`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **4**  | **3**  | **12** | **9** |")
    String backgroundOrigin() default "	";

    /**
     * The **`background-position-x`** CSS property sets the initial horizontal position for each background image. The position is relative to the position layer set by `background-origin`.\n\n**Syntax**: `[ center | [ [ left | right | x-start | x-end ]? <length-percentage>? ]! ]#`\n\n**Initial value**: `left`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **49**  | **1**  | **12** | **6** |
     *
     * 参考定义: "#/definitions/Property.BackgroundPositionX%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"x-end"},{"type":"string","const":"x-start"}]
     *
     * @see Property_BackgroundPositionX
     */
    @Const({"center", "left", "right", "x-end", "x-start"})
    @Schema(title = "The **`background-position-x`** CSS property sets the initial horizontal position for each background image. The position is relative to the position layer set by `background-origin`.\n\n**Syntax**: `[ center | [ [ left | right | x-start | x-end ]? <length-percentage>? ]! ]#`\n\n**Initial value**: `left`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **49**  | **1**  | **12** | **6** |")
    String backgroundPositionX() default "	";

    /**
     * The **`background-position-y`** CSS property sets the initial vertical position for each background image. The position is relative to the position layer set by `background-origin`.\n\n**Syntax**: `[ center | [ [ top | bottom | y-start | y-end ]? <length-percentage>? ]! ]#`\n\n**Initial value**: `top`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **49**  | **1**  | **12** | **6** |
     *
     * 参考定义: "#/definitions/Property.BackgroundPositionY%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"top"},{"type":"string","const":"y-end"},{"type":"string","const":"y-start"}]
     *
     * @see Property_BackgroundPositionY
     */
    @Const({"bottom", "center", "top", "y-end", "y-start"})
    @Schema(title = "The **`background-position-y`** CSS property sets the initial vertical position for each background image. The position is relative to the position layer set by `background-origin`.\n\n**Syntax**: `[ center | [ [ top | bottom | y-start | y-end ]? <length-percentage>? ]! ]#`\n\n**Initial value**: `top`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **49**  | **1**  | **12** | **6** |")
    String backgroundPositionY() default "	";

    /**
     * The **`background-repeat`** CSS property sets how background images are repeated. A background image can be repeated along the horizontal and vertical axes, or not repeated at all.\n\n**Syntax**: `<repeat-style>#`\n\n**Initial value**: `repeat`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BackgroundRepeat"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"type":"string"}]
     *
     * @see Property_BackgroundRepeat
     */

    @Schema(title = "The **`background-repeat`** CSS property sets how background images are repeated. A background image can be repeated along the horizontal and vertical axes, or not repeated at all.\n\n**Syntax**: `<repeat-style>#`\n\n**Initial value**: `repeat`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String backgroundRepeat() default "	";

    /**
     * The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **3**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BackgroundSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgSize%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_BackgroundSize
     */

    @Schema(title = "The **`background-size`** CSS property sets the size of the element's background image. The image can be left to its natural size, stretched, or constrained to fit the available space.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **3**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String backgroundSize() default "	";

    /**
     * **Syntax**: `clip | ellipsis | <string>`\n\n**Initial value**: `clip`
     *
     * 参考定义: "#/definitions/Property.BlockOverflow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clip"},{"type":"string","const":"ellipsis"},{"type":"string"}]
     *
     * @see Property_BlockOverflow
     */
    @Const({"clip", "ellipsis"})
    @Schema(title = "**Syntax**: `clip | ellipsis | <string>`\n\n**Initial value**: `clip`")
    String blockOverflow() default "	";

    /**
     * The **`block-size`** CSS property defines the horizontal or vertical size of an element's block, depending on its writing mode. It corresponds to either the `width` or the `height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'width'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BlockSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_BlockSize
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "auto", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`block-size`** CSS property defines the horizontal or vertical size of an element's block, depending on its writing mode. It corresponds to either the `width` or the `height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'width'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |")
    String blockSize() default "	";

    /**
     * The **`border-block-color`** CSS property defines the color of the logical block borders of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color` and `border-bottom-color`, or `border-right-color` and `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>{1,2}`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderBlockColor
     */

    @Schema(title = "The **`border-block-color`** CSS property defines the color of the logical block borders of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color` and `border-bottom-color`, or `border-right-color` and `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>{1,2}`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderBlockColor() default "	";

    /**
     * The **`border-block-end-color`** CSS property defines the color of the logical block-end border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockEndColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderBlockEndColor
     */

    @Schema(title = "The **`border-block-end-color`** CSS property defines the color of the logical block-end border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockEndColor() default "	";

    /**
     * The **`border-block-end-style`** CSS property defines the style of the logical block-end border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockEndStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderBlockEndStyle
     */

    @Schema(title = "The **`border-block-end-style`** CSS property defines the style of the logical block-end border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockEndStyle() default "	";

    /**
     * The **`border-block-end-width`** CSS property defines the width of the logical block-end border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockEndWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderBlockEndWidth
     */

    @Schema(title = "The **`border-block-end-width`** CSS property defines the width of the logical block-end border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockEndWidth() default "	";

    /**
     * The **`border-block-start-color`** CSS property defines the color of the logical block-start border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockStartColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderBlockStartColor
     */

    @Schema(title = "The **`border-block-start-color`** CSS property defines the color of the logical block-start border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockStartColor() default "	";

    /**
     * The **`border-block-start-style`** CSS property defines the style of the logical block start border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockStartStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderBlockStartStyle
     */

    @Schema(title = "The **`border-block-start-style`** CSS property defines the style of the logical block start border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockStartStyle() default "	";

    /**
     * The **`border-block-start-width`** CSS property defines the width of the logical block-start border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockStartWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderBlockStartWidth
     */

    @Schema(title = "The **`border-block-start-width`** CSS property defines the width of the logical block-start border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderBlockStartWidth() default "	";

    /**
     * The **`border-block-style`** CSS property defines the style of the logical block borders of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style` and `border-bottom-style`, or `border-left-style` and `border-right-style` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderBlockStyle
     */

    @Schema(title = "The **`border-block-style`** CSS property defines the style of the logical block borders of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style` and `border-bottom-style`, or `border-left-style` and `border-right-style` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderBlockStyle() default "	";

    /**
     * The **`border-block-width`** CSS property defines the width of the logical block borders of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width` and `border-bottom-width`, or `border-left-width`, and `border-right-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderBlockWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderBlockWidth
     */

    @Schema(title = "The **`border-block-width`** CSS property defines the width of the logical block borders of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width` and `border-bottom-width`, or `border-left-width`, and `border-right-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderBlockWidth() default "	";

    /**
     * The **`border-bottom-color`** CSS property sets the color of an element's bottom border. It can also be set with the shorthand CSS properties `border-color` or `border-bottom`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderBottomColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderBottomColor
     */

    @Schema(title = "The **`border-bottom-color`** CSS property sets the color of an element's bottom border. It can also be set with the shorthand CSS properties `border-color` or `border-bottom`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderBottomColor() default "	";

    /**
     * The **`border-bottom-left-radius`** CSS property rounds the bottom-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BorderBottomLeftRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderBottomLeftRadius
     */

    @Schema(title = "The **`border-bottom-left-radius`** CSS property rounds the bottom-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String borderBottomLeftRadius() default "	";

    /**
     * The **`border-bottom-right-radius`** CSS property rounds the bottom-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BorderBottomRightRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderBottomRightRadius
     */

    @Schema(title = "The **`border-bottom-right-radius`** CSS property rounds the bottom-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String borderBottomRightRadius() default "	";

    /**
     * The **`border-bottom-style`** CSS property sets the line style of an element's bottom `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.BorderBottomStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderBottomStyle
     */

    @Schema(title = "The **`border-bottom-style`** CSS property sets the line style of an element's bottom `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String borderBottomStyle() default "	";

    /**
     * The **`border-bottom-width`** CSS property sets the width of the bottom border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderBottomWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderBottomWidth
     */

    @Schema(title = "The **`border-bottom-width`** CSS property sets the width of the bottom border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderBottomWidth() default "	";

    /**
     * The **`border-collapse`** CSS property sets whether cells inside a `<table>` have shared or separate borders.\n\n**Syntax**: `collapse | separate`\n\n**Initial value**: `separate`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **5** |
     *
     * 参考定义: "#/definitions/Property.BorderCollapse"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"collapse"},{"type":"string","const":"separate"}]
     *
     * @see Property_BorderCollapse
     */
    @Const({"collapse", "separate"})
    @Schema(title = "The **`border-collapse`** CSS property sets whether cells inside a `<table>` have shared or separate borders.\n\n**Syntax**: `collapse | separate`\n\n**Initial value**: `separate`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **5** |")
    String borderCollapse() default "	";

    /**
     * The **`border-end-end-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius that depends on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderEndEndRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderEndEndRadius
     */

    @Schema(title = "The **`border-end-end-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius that depends on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |")
    String borderEndEndRadius() default "	";

    /**
     * The **`border-end-start-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius depending on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderEndStartRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderEndStartRadius
     */

    @Schema(title = "The **`border-end-start-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius depending on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |")
    String borderEndStartRadius() default "	";

    /**
     * The **`border-image-outset`** CSS property sets the distance by which an element's border image is set out from its border box.\n\n**Syntax**: `[ <length> | <number> ]{1,4}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.BorderImageOutset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImageOutset
     */

    @Schema(title = "The **`border-image-outset`** CSS property sets the distance by which an element's border image is set out from its border box.\n\n**Syntax**: `[ <length> | <number> ]{1,4}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |")
    String borderImageOutset() default "	";

    /**
     * The **`border-image-repeat`** CSS property defines how the edge regions of a source image are adjusted to fit the dimensions of an element's border image.\n\n**Syntax**: `[ stretch | repeat | round | space ]{1,2}`\n\n**Initial value**: `stretch`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.BorderImageRepeat"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_BorderImageRepeat
     */
    @Const({"repeat", "round", "space", "stretch"})
    @Schema(title = "The **`border-image-repeat`** CSS property defines how the edge regions of a source image are adjusted to fit the dimensions of an element's border image.\n\n**Syntax**: `[ stretch | repeat | round | space ]{1,2}`\n\n**Initial value**: `stretch`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |")
    String borderImageRepeat() default "	";

    /**
     * The **`border-image-slice`** CSS property divides the image specified by `border-image-source` into regions. These regions form the components of an element's border image.\n\n**Syntax**: `<number-percentage>{1,4} && fill?`\n\n**Initial value**: `100%`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.BorderImageSlice"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderImageSlice
     */

    @Schema(title = "The **`border-image-slice`** CSS property divides the image specified by `border-image-source` into regions. These regions form the components of an element's border image.\n\n**Syntax**: `<number-percentage>{1,4} && fill?`\n\n**Initial value**: `100%`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |")
    String borderImageSlice() default "	";

    /**
     * The **`border-image-source`** CSS property sets the source image used to create an element's border image.\n\n**Syntax**: `none | <image>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.BorderImageSource"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BorderImageSource
     */
    @Const({"none"})
    @Schema(title = "The **`border-image-source`** CSS property sets the source image used to create an element's border image.\n\n**Syntax**: `none | <image>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **15**  | **6**  | **12** | **11** |")
    String borderImageSource() default "	";

    /**
     * The **`border-image-width`** CSS property sets the width of an element's border image.\n\n**Syntax**: `[ <length-percentage> | <number> | auto ]{1,4}`\n\n**Initial value**: `1`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **13**  | **6**  | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.BorderImageWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_BorderImageWidth
     */
    @Const({"auto"})
    @Schema(title = "The **`border-image-width`** CSS property sets the width of an element's border image.\n\n**Syntax**: `[ <length-percentage> | <number> | auto ]{1,4}`\n\n**Initial value**: `1`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **15** | **13**  | **6**  | **12** | **11** |")
    String borderImageWidth() default "	";

    /**
     * The **`border-inline-color`** CSS property defines the color of the logical inline borders of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color` and `border-bottom-color`, or `border-right-color` and `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>{1,2}`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInlineColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string"}]
     *
     * @see Property_BorderInlineColor
     */

    @Schema(title = "The **`border-inline-color`** CSS property defines the color of the logical inline borders of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color` and `border-bottom-color`, or `border-right-color` and `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>{1,2}`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderInlineColor() default "	";

    /**
     * The **`border-inline-end-color`** CSS property defines the color of the logical inline-end border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome |           Firefox           |  Safari  |  Edge  | IE  | | :----: | :-------------------------: | :------: | :----: | :-: | | **69** |           **41**            | **12.1** | **79** | No  | |        | 3 _(-moz-border-end-color)_ |          |        |     |
     *
     * 参考定义: "#/definitions/Property.BorderInlineEndColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderInlineEndColor
     */

    @Schema(title = "The **`border-inline-end-color`** CSS property defines the color of the logical inline-end border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome |           Firefox           |  Safari  |  Edge  | IE  | | :----: | :-------------------------: | :------: | :----: | :-: | | **69** |           **41**            | **12.1** | **79** | No  | |        | 3 _(-moz-border-end-color)_ |          |        |     |")
    String borderInlineEndColor() default "	";

    /**
     * The **`border-inline-end-style`** CSS property defines the style of the logical inline end border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome |           Firefox           |  Safari  |  Edge  | IE  | | :----: | :-------------------------: | :------: | :----: | :-: | | **69** |           **41**            | **12.1** | **79** | No  | |        | 3 _(-moz-border-end-style)_ |          |        |     |
     *
     * 参考定义: "#/definitions/Property.BorderInlineEndStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderInlineEndStyle
     */

    @Schema(title = "The **`border-inline-end-style`** CSS property defines the style of the logical inline end border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome |           Firefox           |  Safari  |  Edge  | IE  | | :----: | :-------------------------: | :------: | :----: | :-: | | **69** |           **41**            | **12.1** | **79** | No  | |        | 3 _(-moz-border-end-style)_ |          |        |     |")
    String borderInlineEndStyle() default "	";

    /**
     * The **`border-inline-end-width`** CSS property defines the width of the logical inline-end border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome |           Firefox           |  Safari  |  Edge  | IE  | | :----: | :-------------------------: | :------: | :----: | :-: | | **69** |           **41**            | **12.1** | **79** | No  | |        | 3 _(-moz-border-end-width)_ |          |        |     |
     *
     * 参考定义: "#/definitions/Property.BorderInlineEndWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderInlineEndWidth
     */

    @Schema(title = "The **`border-inline-end-width`** CSS property defines the width of the logical inline-end border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome |           Firefox           |  Safari  |  Edge  | IE  | | :----: | :-------------------------: | :------: | :----: | :-: | | **69** |           **41**            | **12.1** | **79** | No  | |        | 3 _(-moz-border-end-width)_ |          |        |     |")
    String borderInlineEndWidth() default "	";

    /**
     * The **`border-inline-start-color`** CSS property defines the color of the logical inline start border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome |            Firefox            |  Safari  |  Edge  | IE  | | :----: | :---------------------------: | :------: | :----: | :-: | | **69** |            **41**             | **12.1** | **79** | No  | |        | 3 _(-moz-border-start-color)_ |          |        |     |
     *
     * 参考定义: "#/definitions/Property.BorderInlineStartColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderInlineStartColor
     */

    @Schema(title = "The **`border-inline-start-color`** CSS property defines the color of the logical inline start border of an element, which maps to a physical border color depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-color`, `border-right-color`, `border-bottom-color`, or `border-left-color` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-color'>`\n\n**Initial value**: `currentcolor`\n\n| Chrome |            Firefox            |  Safari  |  Edge  | IE  | | :----: | :---------------------------: | :------: | :----: | :-: | | **69** |            **41**             | **12.1** | **79** | No  | |        | 3 _(-moz-border-start-color)_ |          |        |     |")
    String borderInlineStartColor() default "	";

    /**
     * The **`border-inline-start-style`** CSS property defines the style of the logical inline start border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome |            Firefox            |  Safari  |  Edge  | IE  | | :----: | :---------------------------: | :------: | :----: | :-: | | **69** |            **41**             | **12.1** | **79** | No  | |        | 3 _(-moz-border-start-style)_ |          |        |     |
     *
     * 参考定义: "#/definitions/Property.BorderInlineStartStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderInlineStartStyle
     */

    @Schema(title = "The **`border-inline-start-style`** CSS property defines the style of the logical inline start border of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style`, `border-right-style`, `border-bottom-style`, or `border-left-style` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome |            Firefox            |  Safari  |  Edge  | IE  | | :----: | :---------------------------: | :------: | :----: | :-: | | **69** |            **41**             | **12.1** | **79** | No  | |        | 3 _(-moz-border-start-style)_ |          |        |     |")
    String borderInlineStartStyle() default "	";

    /**
     * The **`border-inline-start-width`** CSS property defines the width of the logical inline-start border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInlineStartWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderInlineStartWidth
     */

    @Schema(title = "The **`border-inline-start-width`** CSS property defines the width of the logical inline-start border of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width`, `border-right-width`, `border-bottom-width`, or `border-left-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String borderInlineStartWidth() default "	";

    /**
     * The **`border-inline-style`** CSS property defines the style of the logical inline borders of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style` and `border-bottom-style`, or `border-left-style` and `border-right-style` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInlineStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderInlineStyle
     */

    @Schema(title = "The **`border-inline-style`** CSS property defines the style of the logical inline borders of an element, which maps to a physical border style depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-style` and `border-bottom-style`, or `border-left-style` and `border-right-style` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderInlineStyle() default "	";

    /**
     * The **`border-inline-width`** CSS property defines the width of the logical inline borders of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width` and `border-bottom-width`, or `border-left-width`, and `border-right-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderInlineWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderInlineWidth
     */

    @Schema(title = "The **`border-inline-width`** CSS property defines the width of the logical inline borders of an element, which maps to a physical border width depending on the element's writing mode, directionality, and text orientation. It corresponds to the `border-top-width` and `border-bottom-width`, or `border-left-width`, and `border-right-width` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'border-top-width'>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String borderInlineWidth() default "	";

    /**
     * The **`border-left-color`** CSS property sets the color of an element's left border. It can also be set with the shorthand CSS properties `border-color` or `border-left`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderLeftColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderLeftColor
     */

    @Schema(title = "The **`border-left-color`** CSS property sets the color of an element's left border. It can also be set with the shorthand CSS properties `border-color` or `border-left`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderLeftColor() default "	";

    /**
     * The **`border-left-style`** CSS property sets the line style of an element's left `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.BorderLeftStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderLeftStyle
     */

    @Schema(title = "The **`border-left-style`** CSS property sets the line style of an element's left `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String borderLeftStyle() default "	";

    /**
     * The **`border-left-width`** CSS property sets the width of the left border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderLeftWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderLeftWidth
     */

    @Schema(title = "The **`border-left-width`** CSS property sets the width of the left border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderLeftWidth() default "	";

    /**
     * The **`border-right-color`** CSS property sets the color of an element's right border. It can also be set with the shorthand CSS properties `border-color` or `border-right`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderRightColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderRightColor
     */

    @Schema(title = "The **`border-right-color`** CSS property sets the color of an element's right border. It can also be set with the shorthand CSS properties `border-color` or `border-right`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderRightColor() default "	";

    /**
     * The **`border-right-style`** CSS property sets the line style of an element's right `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.BorderRightStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderRightStyle
     */

    @Schema(title = "The **`border-right-style`** CSS property sets the line style of an element's right `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String borderRightStyle() default "	";

    /**
     * The **`border-right-width`** CSS property sets the width of the right border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderRightWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderRightWidth
     */

    @Schema(title = "The **`border-right-width`** CSS property sets the width of the right border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderRightWidth() default "	";

    /**
     * The **`border-spacing`** CSS property sets the distance between the borders of adjacent `<table>` cells. This property applies only when `border-collapse` is `separate`.\n\n**Syntax**: `<length> <length>?`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.BorderSpacing%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderSpacing
     */

    @Schema(title = "The **`border-spacing`** CSS property sets the distance between the borders of adjacent `<table>` cells. This property applies only when `border-collapse` is `separate`.\n\n**Syntax**: `<length> <length>?`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **8** |")
    String borderSpacing() default "	";

    /**
     * The **`border-start-end-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius depending on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderStartEndRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderStartEndRadius
     */

    @Schema(title = "The **`border-start-end-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius depending on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |")
    String borderStartEndRadius() default "	";

    /**
     * The **`border-start-start-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius that depends on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |
     *
     * 参考定义: "#/definitions/Property.BorderStartStartRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderStartStartRadius
     */

    @Schema(title = "The **`border-start-start-radius`** CSS property defines a logical border radius on an element, which maps to a physical border radius that depends on the element's `writing-mode`, `direction`, and `text-orientation`. This is useful when building styles to work regardless of the text orientation and writing mode.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **89** | **66**  | **15** | **89** | No  |")
    String borderStartStartRadius() default "	";

    /**
     * The **`border-top-color`** CSS property sets the color of an element's top border. It can also be set with the shorthand CSS properties `border-color` or `border-top`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderTopColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_BorderTopColor
     */

    @Schema(title = "The **`border-top-color`** CSS property sets the color of an element's top border. It can also be set with the shorthand CSS properties `border-color` or `border-top`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderTopColor() default "	";

    /**
     * The **`border-top-left-radius`** CSS property rounds the top-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BorderTopLeftRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderTopLeftRadius
     */

    @Schema(title = "The **`border-top-left-radius`** CSS property rounds the top-left corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String borderTopLeftRadius() default "	";

    /**
     * The **`border-top-right-radius`** CSS property rounds the top-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BorderTopRightRadius%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_BorderTopRightRadius
     */

    @Schema(title = "The **`border-top-right-radius`** CSS property rounds the top-right corner of an element by specifying the radius (or the radius of the semi-major and semi-minor axes) of the ellipse defining the curvature of the corner.\n\n**Syntax**: `<length-percentage>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | |  **4**  |  **4**  |  **5**  | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String borderTopRightRadius() default "	";

    /**
     * The **`border-top-style`** CSS property sets the line style of an element's top `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.BorderTopStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"}]
     *
     * @see Property_BorderTopStyle
     */

    @Schema(title = "The **`border-top-style`** CSS property sets the line style of an element's top `border`.\n\n**Syntax**: `<line-style>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String borderTopStyle() default "	";

    /**
     * The **`border-top-width`** CSS property sets the width of the top border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.BorderTopWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_BorderTopWidth
     */

    @Schema(title = "The **`border-top-width`** CSS property sets the width of the top border of an element.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String borderTopWidth() default "	";

    /**
     * The **`bottom`** CSS property participates in setting the vertical position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **5** |
     *
     * 参考定义: "#/definitions/Property.Bottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Bottom
     */
    @Const({"auto"})
    @Schema(title = "The **`bottom`** CSS property participates in setting the vertical position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **5** |")
    String bottom() default "	";

    /**
     * The **`box-decoration-break`** CSS property specifies how an element's fragments should be rendered when broken across multiple lines, columns, or pages.\n\n**Syntax**: `slice | clone`\n\n**Initial value**: `slice`\n\n|    Chrome    | Firefox |   Safari    |     Edge     | IE  | | :----------: | :-----: | :---------: | :----------: | :-: | | **22** _-x-_ | **32**  | **7** _-x-_ | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.BoxDecorationBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clone"},{"type":"string","const":"slice"}]
     *
     * @see Property_BoxDecorationBreak
     */
    @Const({"clone", "slice"})
    @Schema(title = "The **`box-decoration-break`** CSS property specifies how an element's fragments should be rendered when broken across multiple lines, columns, or pages.\n\n**Syntax**: `slice | clone`\n\n**Initial value**: `slice`\n\n|    Chrome    | Firefox |   Safari    |     Edge     | IE  | | :----------: | :-----: | :---------: | :----------: | :-: | | **22** _-x-_ | **32**  | **7** _-x-_ | **79** _-x-_ | No  |")
    String boxDecorationBreak() default "	";

    /**
     * The **`box-shadow`** CSS property adds shadow effects around an element's frame. You can set multiple effects separated by commas. A box shadow is described by X and Y offsets relative to the element, blur and spread radius, and color.\n\n**Syntax**: `none | <shadow>#`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | | **10**  |  **4**  | **5.1** | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BoxShadow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_BoxShadow
     */
    @Const({"none"})
    @Schema(title = "The **`box-shadow`** CSS property adds shadow effects around an element's frame. You can set multiple effects separated by commas. A box shadow is described by X and Y offsets relative to the element, blur and spread radius, and color.\n\n**Syntax**: `none | <shadow>#`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | | **10**  |  **4**  | **5.1** | **12** | **9** | | 1 _-x-_ |         | 3 _-x-_ |        |       |")
    String boxShadow() default "	";

    /**
     * The **`box-sizing`** CSS property sets how the total width and height of an element is calculated.\n\n**Syntax**: `content-box | border-box`\n\n**Initial value**: `content-box`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | | **10**  | **29**  | **5.1** | **12** | **8** | | 1 _-x-_ | 1 _-x-_ | 3 _-x-_ |        |       |
     *
     * 参考定义: "#/definitions/Property.BoxSizing"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"}]
     *
     * @see Property_BoxSizing
     */
    @Const({"border-box", "content-box"})
    @Schema(title = "The **`box-sizing`** CSS property sets how the total width and height of an element is calculated.\n\n**Syntax**: `content-box | border-box`\n\n**Initial value**: `content-box`\n\n| Chrome  | Firefox | Safari  |  Edge  |  IE   | | :-----: | :-----: | :-----: | :----: | :---: | | **10**  | **29**  | **5.1** | **12** | **8** | | 1 _-x-_ | 1 _-x-_ | 3 _-x-_ |        |       |")
    String boxSizing() default "	";

    /**
     * The **`break-after`** CSS property sets how page, column, or region breaks should behave after a generated box. If there is no generated box, the property is ignored.\n\n**Syntax**: `auto | avoid | always | all | avoid-page | page | left | right | recto | verso | avoid-column | column | avoid-region | region`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  |   No   | **12** | **10** |\n\n---\n\n_Supported in Paged Media_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---
     *
     * 参考定义: "#/definitions/Property.BreakAfter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"avoid-column"},{"type":"string","const":"avoid-page"},{"type":"string","const":"avoid-region"},{"type":"string","const":"column"},{"type":"string","const":"left"},{"type":"string","const":"page"},{"type":"string","const":"recto"},{"type":"string","const":"region"},{"type":"string","const":"right"},{"type":"string","const":"verso"}]
     *
     * @see Property_BreakAfter
     */
    @Const({"all", "always", "auto", "avoid", "avoid-column", "avoid-page", "avoid-region", "column", "left", "page", "recto", "region", "right", "verso"})
    @Schema(title = "The **`break-after`** CSS property sets how page, column, or region breaks should behave after a generated box. If there is no generated box, the property is ignored.\n\n**Syntax**: `auto | avoid | always | all | avoid-page | page | left | right | recto | verso | avoid-column | column | avoid-region | region`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  |   No   | **12** | **10** |\n\n---\n\n_Supported in Paged Media_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---")
    String breakAfter() default "	";

    /**
     * The **`break-before`** CSS property sets how page, column, or region breaks should behave before a generated box. If there is no generated box, the property is ignored.\n\n**Syntax**: `auto | avoid | always | all | avoid-page | page | left | right | recto | verso | avoid-column | column | avoid-region | region`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  |   No   | **12** | **10** |\n\n---\n\n_Supported in Paged Media_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---
     *
     * 参考定义: "#/definitions/Property.BreakBefore"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"avoid-column"},{"type":"string","const":"avoid-page"},{"type":"string","const":"avoid-region"},{"type":"string","const":"column"},{"type":"string","const":"left"},{"type":"string","const":"page"},{"type":"string","const":"recto"},{"type":"string","const":"region"},{"type":"string","const":"right"},{"type":"string","const":"verso"}]
     *
     * @see Property_BreakBefore
     */
    @Const({"all", "always", "auto", "avoid", "avoid-column", "avoid-page", "avoid-region", "column", "left", "page", "recto", "region", "right", "verso"})
    @Schema(title = "The **`break-before`** CSS property sets how page, column, or region breaks should behave before a generated box. If there is no generated box, the property is ignored.\n\n**Syntax**: `auto | avoid | always | all | avoid-page | page | left | right | recto | verso | avoid-column | column | avoid-region | region`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  |   No   | **12** | **10** |\n\n---\n\n_Supported in Paged Media_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---")
    String breakBefore() default "	";

    /**
     * The **`break-inside`** CSS property sets how page, column, or region breaks should behave inside a generated box. If there is no generated box, the property is ignored.\n\n**Syntax**: `auto | avoid | avoid-page | avoid-column | avoid-region`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---\n\n_Supported in Paged Media_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---
     *
     * 参考定义: "#/definitions/Property.BreakInside"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"avoid-column"},{"type":"string","const":"avoid-page"},{"type":"string","const":"avoid-region"}]
     *
     * @see Property_BreakInside
     */
    @Const({"auto", "avoid", "avoid-column", "avoid-page", "avoid-region"})
    @Schema(title = "The **`break-inside`** CSS property sets how page, column, or region breaks should behave inside a generated box. If there is no generated box, the property is ignored.\n\n**Syntax**: `auto | avoid | avoid-page | avoid-column | avoid-region`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---\n\n_Supported in Paged Media_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **50** | **65**  | **10** | **12** | **10** |\n\n---")
    String breakInside() default "	";

    /**
     * The **`caption-side`** CSS property puts the content of a table's `<caption>` on the specified side. The values are relative to the `writing-mode` of the table.\n\n**Syntax**: `top | bottom | block-start | block-end | inline-start | inline-end`\n\n**Initial value**: `top`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.CaptionSide"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block-end"},{"type":"string","const":"block-start"},{"type":"string","const":"bottom"},{"type":"string","const":"inline-end"},{"type":"string","const":"inline-start"},{"type":"string","const":"top"}]
     *
     * @see Property_CaptionSide
     */
    @Const({"block-end", "block-start", "bottom", "inline-end", "inline-start", "top"})
    @Schema(title = "The **`caption-side`** CSS property puts the content of a table's `<caption>` on the specified side. The values are relative to the `writing-mode` of the table.\n\n**Syntax**: `top | bottom | block-start | block-end | inline-start | inline-end`\n\n**Initial value**: `top`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **8** |")
    String captionSide() default "	";

    /**
     * The **`caret-color`** CSS property sets the color of the **insertion caret**, the visible marker where the next character typed will be inserted. This is sometimes referred to as the **text input cursor**. The caret appears in elements such as `<input>` or those with the `contenteditable` attribute. The caret is typically a thin vertical line that flashes to help make it more noticeable. By default, it is black, but its color can be altered with this property.\n\n**Syntax**: `auto | <color>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **53**  | **11.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.CaretColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"auto"}]
     *
     * @see Property_CaretColor
     */
    @Const({"auto"})
    @Schema(title = "The **`caret-color`** CSS property sets the color of the **insertion caret**, the visible marker where the next character typed will be inserted. This is sometimes referred to as the **text input cursor**. The caret appears in elements such as `<input>` or those with the `contenteditable` attribute. The caret is typically a thin vertical line that flashes to help make it more noticeable. By default, it is black, but its color can be altered with this property.\n\n**Syntax**: `auto | <color>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **53**  | **11.1** | **79** | No  |")
    String caretColor() default "	";

    /**
     * The **`clear`** CSS property sets whether an element must be moved below (cleared) floating elements that precede it. The `clear` property applies to floating and non-floating elements.\n\n**Syntax**: `none | left | right | both | inline-start | inline-end`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Clear"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"both"},{"type":"string","const":"inline-end"},{"type":"string","const":"inline-start"},{"type":"string","const":"left"},{"type":"string","const":"none"},{"type":"string","const":"right"}]
     *
     * @see Property_Clear
     */
    @Const({"both", "inline-end", "inline-start", "left", "none", "right"})
    @Schema(title = "The **`clear`** CSS property sets whether an element must be moved below (cleared) floating elements that precede it. The `clear` property applies to floating and non-floating elements.\n\n**Syntax**: `none | left | right | both | inline-start | inline-end`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String clear() default "	";

    /**
     * The **`color-adjust`** CSS property sets what, if anything, the user agent may do to optimize the appearance of the element on the output device. By default, the browser is allowed to make any adjustments to the element's appearance it determines to be necessary and prudent given the type and capabilities of the output device.\n\n**Syntax**: `economy | exact`\n\n**Initial value**: `economy`\n\n|                Chrome                 | Firefox |                Safari                |                 Edge                  | IE  | | :-----------------------------------: | :-----: | :----------------------------------: | :-----------------------------------: | :-: | | **49** _(-webkit-print-color-adjust)_ | **48**  | **6** _(-webkit-print-color-adjust)_ | **79** _(-webkit-print-color-adjust)_ | No  |
     *
     * 参考定义: "#/definitions/Property.ColorAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"economy"},{"type":"string","const":"exact"}]
     *
     * @see Property_ColorAdjust
     */
    @Const({"economy", "exact"})
    @Schema(title = "The **`color-adjust`** CSS property sets what, if anything, the user agent may do to optimize the appearance of the element on the output device. By default, the browser is allowed to make any adjustments to the element's appearance it determines to be necessary and prudent given the type and capabilities of the output device.\n\n**Syntax**: `economy | exact`\n\n**Initial value**: `economy`\n\n|                Chrome                 | Firefox |                Safari                |                 Edge                  | IE  | | :-----------------------------------: | :-----: | :----------------------------------: | :-----------------------------------: | :-: | | **49** _(-webkit-print-color-adjust)_ | **48**  | **6** _(-webkit-print-color-adjust)_ | **79** _(-webkit-print-color-adjust)_ | No  |")
    String colorAdjust() default "	";

    /**
     * The **`color-scheme`** CSS property allows an element to indicate which color schemes it can comfortably be rendered in.\n\n**Syntax**: `normal | [ light | dark | <custom-ident> ]+`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **81** |   No    | **13** | **81** | No  |
     *
     * 参考定义: "#/definitions/Property.ColorScheme"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dark"},{"type":"string","const":"light"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_ColorScheme
     */
    @Const({"dark", "light", "normal"})
    @Schema(title = "The **`color-scheme`** CSS property allows an element to indicate which color schemes it can comfortably be rendered in.\n\n**Syntax**: `normal | [ light | dark | <custom-ident> ]+`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **81** |   No    | **13** | **81** | No  |")
    String colorScheme() default "	";

    /**
     * The **`column-count`** CSS property breaks an element's content into the specified number of columns.\n\n**Syntax**: `<integer> | auto`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnCount"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_ColumnCount
     */
    @Const({"auto"})
    @Schema(title = "The **`column-count`** CSS property breaks an element's content into the specified number of columns.\n\n**Syntax**: `<integer> | auto`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |")
    String columnCount() default "	";

    /**
     * The **`column-fill`** CSS property controls how an element's contents are balanced when broken into columns.\n\n**Syntax**: `auto | balance | balance-all`\n\n**Initial value**: `balance`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE   | | :----: | :-----: | :-----: | :----: | :----: | | **50** | **52**  |  **9**  | **12** | **10** | |        |         | 8 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnFill"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"balance"},{"type":"string","const":"balance-all"}]
     *
     * @see Property_ColumnFill
     */
    @Const({"auto", "balance", "balance-all"})
    @Schema(title = "The **`column-fill`** CSS property controls how an element's contents are balanced when broken into columns.\n\n**Syntax**: `auto | balance | balance-all`\n\n**Initial value**: `balance`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE   | | :----: | :-----: | :-----: | :----: | :----: | | **50** | **52**  |  **9**  | **12** | **10** | |        |         | 8 _-x-_ |        |        |")
    String columnFill() default "	";

    /**
     * The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **84** | **63**  | **14.1** | **84** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n|         Chrome         |        Firefox         |          Safari          |  Edge  | IE  | | :--------------------: | :--------------------: | :----------------------: | :----: | :-: | |         **66**         |         **61**         |          **12**          | **16** | No  | | 57 _(grid-column-gap)_ | 52 _(grid-column-gap)_ | 10.1 _(grid-column-gap)_ |        |     |\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  | **10**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |\n\n---
     *
     * 参考定义: "#/definitions/Property.ColumnGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_ColumnGap
     */
    @Const({"normal"})
    @Schema(title = "The **`column-gap`** CSS property sets the size of the gap (gutter) between an element's columns.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **84** | **63**  | **14.1** | **84** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n|         Chrome         |        Firefox         |          Safari          |  Edge  | IE  | | :--------------------: | :--------------------: | :----------------------: | :----: | :-: | |         **66**         |         **61**         |          **12**          | **16** | No  | | 57 _(grid-column-gap)_ | 52 _(grid-column-gap)_ | 10.1 _(grid-column-gap)_ |        |     |\n\n---\n\n_Supported in Multi-column Layout_\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  | **10**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |\n\n---")
    String columnGap() default "	";

    /**
     * The **`column-rule-color`** CSS property sets the color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnRuleColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_ColumnRuleColor
     */

    @Schema(title = "The **`column-rule-color`** CSS property sets the color of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |")
    String columnRuleColor() default "	";

    /**
     * The **`column-rule-style`** CSS property sets the style of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnRuleStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string"}]
     *
     * @see Property_ColumnRuleStyle
     */

    @Schema(title = "The **`column-rule-style`** CSS property sets the style of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-style'>`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |")
    String columnRuleStyle() default "	";

    /**
     * The **`column-rule-width`** CSS property sets the width of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnRuleWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_ColumnRuleWidth
     */

    @Schema(title = "The **`column-rule-width`** CSS property sets the width of the line drawn between columns in a multi-column layout.\n\n**Syntax**: `<'border-width'>`\n\n**Initial value**: `medium`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **52**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |")
    String columnRuleWidth() default "	";

    /**
     * The **`column-span`** CSS property makes it possible for an element to span across all columns when its value is set to `all`.\n\n**Syntax**: `none | all`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **50**  | **71**  |   **9**   | **12** | **10** | | 6 _-x-_ |         | 5.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnSpan"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"}]
     *
     * @see Property_ColumnSpan
     */
    @Const({"all", "none"})
    @Schema(title = "The **`column-span`** CSS property makes it possible for an element to span across all columns when its value is set to `all`.\n\n**Syntax**: `none | all`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **50**  | **71**  |   **9**   | **12** | **10** | | 6 _-x-_ |         | 5.1 _-x-_ |        |        |")
    String columnSpan() default "	";

    /**
     * The **`column-width`** CSS property sets the ideal column width in a multi-column layout. The container will have as many columns as can fit without any of them having a width less than the `column-width` value. If the width of the container is narrower than the specified value, the single column's width will be smaller than the declared column width.\n\n**Syntax**: `<length> | auto`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **50**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.ColumnWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ColumnWidth
     */
    @Const({"auto"})
    @Schema(title = "The **`column-width`** CSS property sets the ideal column width in a multi-column layout. The container will have as many columns as can fit without any of them having a width less than the `column-width` value. If the width of the container is narrower than the specified value, the single column's width will be smaller than the declared column width.\n\n**Syntax**: `<length> | auto`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **50**  | **50**  |  **9**  | **12** | **10** | | 1 _-x-_ |         | 3 _-x-_ |        |        |")
    String columnWidth() default "	";

    /**
     * The **`contain`** CSS property allows an author to indicate that an element and its contents are, as much as possible, _independent_ of the rest of the document tree. This allows the browser to recalculate layout, style, paint, size, or any combination of them for a limited area of the DOM and not the entire page, leading to obvious performance benefits.\n\n**Syntax**: `none | strict | content | [ size || layout || style || paint ]`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **52** | **69**  |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.Contain"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"content"},{"type":"string","const":"layout"},{"type":"string","const":"none"},{"type":"string","const":"paint"},{"type":"string","const":"size"},{"type":"string","const":"strict"},{"type":"string","const":"style"},{"type":"string"}]
     *
     * @see Property_Contain
     */
    @Const({"content", "layout", "none", "paint", "size", "strict", "style"})
    @Schema(title = "The **`contain`** CSS property allows an author to indicate that an element and its contents are, as much as possible, _independent_ of the rest of the document tree. This allows the browser to recalculate layout, style, paint, size, or any combination of them for a limited area of the DOM and not the entire page, leading to obvious performance benefits.\n\n**Syntax**: `none | strict | content | [ size || layout || style || paint ]`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **52** | **69**  |   No   | **79** | No  |")
    String contain() default "	";

    /**
     * The **`content`** CSS property replaces an element with a generated value. Objects inserted using the `content` property are **anonymous replaced elements**_._\n\n**Syntax**: `normal | none | [ <content-replacement> | <content-list> ] [/ <string> ]?`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.Content"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentList"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_Content
     */
    @Const({"none", "normal"})
    @Schema(title = "The **`content`** CSS property replaces an element with a generated value. Objects inserted using the `content` property are **anonymous replaced elements**_._\n\n**Syntax**: `normal | none | [ <content-replacement> | <content-list> ] [/ <string> ]?`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **8** |")
    String content() default "	";

    /**
     * The **`content-visibility`** CSS property controls whether or not an element renders its contents at all, along with forcing a strong set of containments, allowing user agents to potentially omit large swathes of layout and rendering work until it becomes needed. Basically it enables the user agent to skip an element's rendering work, including layout and painting, until it is needed, makes the initial page load much faster.\n\n**Syntax**: `visible | auto | hidden`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **85** |   No    |   No   | **85** | No  |
     *
     * 参考定义: "#/definitions/Property.ContentVisibility"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"hidden"},{"type":"string","const":"visible"}]
     *
     * @see Property_ContentVisibility
     */
    @Const({"auto", "hidden", "visible"})
    @Schema(title = "The **`content-visibility`** CSS property controls whether or not an element renders its contents at all, along with forcing a strong set of containments, allowing user agents to potentially omit large swathes of layout and rendering work until it becomes needed. Basically it enables the user agent to skip an element's rendering work, including layout and painting, until it is needed, makes the initial page load much faster.\n\n**Syntax**: `visible | auto | hidden`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **85** |   No    |   No   | **85** | No  |")
    String contentVisibility() default "	";

    /**
     * The **`counter-increment`** CSS property increases or decreases the value of a CSS counter by a given value.\n\n**Syntax**: `[ <custom-ident> <integer>? ]+ | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **2**  |  **1**  | **3**  | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.CounterIncrement"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_CounterIncrement
     */
    @Const({"none"})
    @Schema(title = "The **`counter-increment`** CSS property increases or decreases the value of a CSS counter by a given value.\n\n**Syntax**: `[ <custom-ident> <integer>? ]+ | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **2**  |  **1**  | **3**  | **12** | **8** |")
    String counterIncrement() default "	";

    /**
     * The **`counter-reset`** CSS property resets a CSS counter to a given value.\n\n**Syntax**: `[ <custom-ident> <integer>? ]+ | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **2**  |  **1**  | **3**  | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.CounterReset"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_CounterReset
     */
    @Const({"none"})
    @Schema(title = "The **`counter-reset`** CSS property resets a CSS counter to a given value.\n\n**Syntax**: `[ <custom-ident> <integer>? ]+ | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **2**  |  **1**  | **3**  | **12** | **8** |")
    String counterReset() default "	";

    /**
     * The **`counter-set`** CSS property sets a CSS counter to a given value. It manipulates the value of existing counters, and will only create new counters if there isn't already a counter of the given name on the element.\n\n**Syntax**: `[ <custom-ident> <integer>? ]+ | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **85** | **68**  |   No   | **85** | No  |
     *
     * 参考定义: "#/definitions/Property.CounterSet"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_CounterSet
     */
    @Const({"none"})
    @Schema(title = "The **`counter-set`** CSS property sets a CSS counter to a given value. It manipulates the value of existing counters, and will only create new counters if there isn't already a counter of the given name on the element.\n\n**Syntax**: `[ <custom-ident> <integer>? ]+ | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **85** | **68**  |   No   | **85** | No  |")
    String counterSet() default "	";

    /**
     * The **`empty-cells`** CSS property sets whether borders and backgrounds appear around `<table>` cells that have no visible content.\n\n**Syntax**: `show | hide`\n\n**Initial value**: `show`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.EmptyCells"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"hide"},{"type":"string","const":"show"}]
     *
     * @see Property_EmptyCells
     */
    @Const({"hide", "show"})
    @Schema(title = "The **`empty-cells`** CSS property sets whether borders and backgrounds appear around `<table>` cells that have no visible content.\n\n**Syntax**: `show | hide`\n\n**Initial value**: `show`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **8** |")
    String emptyCells() default "	";

    /**
     * The **`flex-basis`** CSS property sets the initial main size of a flex item. It sets the size of the content box unless otherwise set with `box-sizing`.\n\n**Syntax**: `content | <'width'>`\n\n**Initial value**: `auto`\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **22**  |  **9**  | **12** | **11** | | 22 _-x-_ |         | 7 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.FlexBasis%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-auto"},{"type":"string","const":"auto"},{"type":"string","const":"content"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_FlexBasis
     */
    @Const({"-moz-max-content", "-moz-min-content", "-webkit-auto", "auto", "content", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`flex-basis`** CSS property sets the initial main size of a flex item. It sets the size of the content box unless otherwise set with `box-sizing`.\n\n**Syntax**: `content | <'width'>`\n\n**Initial value**: `auto`\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **22**  |  **9**  | **12** | **11** | | 22 _-x-_ |         | 7 _-x-_ |        |        |")
    String flexBasis() default "	";

    /**
     * The **`flex-direction`** CSS property sets how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).\n\n**Syntax**: `row | row-reverse | column | column-reverse`\n\n**Initial value**: `row`\n\n|  Chrome  | Firefox | Safari  |  Edge  |    IE    | | :------: | :-----: | :-----: | :----: | :------: | |  **29**  | **20**  |  **9**  | **12** |  **11**  | | 21 _-x-_ |         | 7 _-x-_ |        | 10 _-x-_ |
     *
     * 参考定义: "#/definitions/Property.FlexDirection"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"column-reverse"},{"type":"string","const":"row"},{"type":"string","const":"row-reverse"}]
     *
     * @see Property_FlexDirection
     */
    @Const({"column", "column-reverse", "row", "row-reverse"})
    @Schema(title = "The **`flex-direction`** CSS property sets how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).\n\n**Syntax**: `row | row-reverse | column | column-reverse`\n\n**Initial value**: `row`\n\n|  Chrome  | Firefox | Safari  |  Edge  |    IE    | | :------: | :-----: | :-----: | :----: | :------: | |  **29**  | **20**  |  **9**  | **12** |  **11**  | | 21 _-x-_ |         | 7 _-x-_ |        | 10 _-x-_ |")
    String flexDirection() default "	";

    /**
     * The **`flex-grow`** CSS property sets the flex grow factor of a flex item main size.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`\n\n|  Chrome  | Firefox | Safari  |  Edge  |            IE            | | :------: | :-----: | :-----: | :----: | :----------------------: | |  **29**  | **20**  |  **9**  | **12** |          **11**          | | 22 _-x-_ |         | 7 _-x-_ |        | 10 _(-ms-flex-positive)_ |
     *
     * 参考定义: "#/definitions/Property.FlexGrow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FlexGrow
     */

    @Schema(title = "The **`flex-grow`** CSS property sets the flex grow factor of a flex item main size.\n\n**Syntax**: `<number>`\n\n**Initial value**: `0`\n\n|  Chrome  | Firefox | Safari  |  Edge  |            IE            | | :------: | :-----: | :-----: | :----: | :----------------------: | |  **29**  | **20**  |  **9**  | **12** |          **11**          | | 22 _-x-_ |         | 7 _-x-_ |        | 10 _(-ms-flex-positive)_ |")
    String flexGrow() default "	";

    /**
     * The **`flex-shrink`** CSS property sets the flex shrink factor of a flex item. If the size of all flex items is larger than the flex container, items shrink to fit according to `flex-shrink`.\n\n**Syntax**: `<number>`\n\n**Initial value**: `1`\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **20**  |  **9**  | **12** | **10** | | 22 _-x-_ |         | 8 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.FlexShrink"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_FlexShrink
     */

    @Schema(title = "The **`flex-shrink`** CSS property sets the flex shrink factor of a flex item. If the size of all flex items is larger than the flex container, items shrink to fit according to `flex-shrink`.\n\n**Syntax**: `<number>`\n\n**Initial value**: `1`\n\n|  Chrome  | Firefox | Safari  |  Edge  |   IE   | | :------: | :-----: | :-----: | :----: | :----: | |  **29**  | **20**  |  **9**  | **12** | **10** | | 22 _-x-_ |         | 8 _-x-_ |        |        |")
    String flexShrink() default "	";

    /**
     * The **`flex-wrap`** CSS property sets whether flex items are forced onto one line or can wrap onto multiple lines. If wrapping is allowed, it sets the direction that lines are stacked.\n\n**Syntax**: `nowrap | wrap | wrap-reverse`\n\n**Initial value**: `nowrap`\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **29**  | **28**  |   **9**   | **12** | **11** | | 21 _-x-_ |         | 6.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.FlexWrap"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"nowrap"},{"type":"string","const":"wrap"},{"type":"string","const":"wrap-reverse"}]
     *
     * @see Property_FlexWrap
     */
    @Const({"nowrap", "wrap", "wrap-reverse"})
    @Schema(title = "The **`flex-wrap`** CSS property sets whether flex items are forced onto one line or can wrap onto multiple lines. If wrapping is allowed, it sets the direction that lines are stacked.\n\n**Syntax**: `nowrap | wrap | wrap-reverse`\n\n**Initial value**: `nowrap`\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **29**  | **28**  |   **9**   | **12** | **11** | | 21 _-x-_ |         | 6.1 _-x-_ |        |        |")
    String flexWrap() default "	";

    /**
     * __float
     *
     * 参考定义: "#/definitions/Property.Float"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inline-end"},{"type":"string","const":"inline-start"},{"type":"string","const":"left"},{"type":"string","const":"none"},{"type":"string","const":"right"}]
     *
     * @see Property_Float
     */
    @Const({"inline-end", "inline-start", "left", "none", "right"})
    @Schema(title = "The **`float`** CSS property places an element on the left or right side of its container, allowing text and inline elements to wrap around it. The element is removed from the normal flow of the page, though still remaining a part of the flow (in contrast to absolute positioning).nn**Syntax**: `left | right | none | inline-start | inline-end`nn**Initial value**: `none`nn| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String __float() default "	";

    /**
     * The **`font-feature-settings`** CSS property controls advanced typographic features in OpenType fonts.\n\n**Syntax**: `normal | <feature-tag-value>#`\n\n**Initial value**: `normal`\n\n|  Chrome  | Firefox  | Safari  |  Edge  |   IE   | | :------: | :------: | :-----: | :----: | :----: | |  **48**  |  **34**  | **9.1** | **15** | **10** | | 16 _-x-_ | 15 _-x-_ |         |        |        |
     *
     * 参考定义: "#/definitions/Property.FontFeatureSettings"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontFeatureSettings
     */
    @Const({"normal"})
    @Schema(title = "The **`font-feature-settings`** CSS property controls advanced typographic features in OpenType fonts.\n\n**Syntax**: `normal | <feature-tag-value>#`\n\n**Initial value**: `normal`\n\n|  Chrome  | Firefox  | Safari  |  Edge  |   IE   | | :------: | :------: | :-----: | :----: | :----: | |  **48**  |  **34**  | **9.1** | **15** | **10** | | 16 _-x-_ | 15 _-x-_ |         |        |        |")
    String fontFeatureSettings() default "	";

    /**
     * The **`font-kerning`** CSS property sets the use of the kerning information stored in a font.\n\n**Syntax**: `auto | normal | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **33** | **32**  |  **9**  | **79** | No  | |        |         | 6 _-x-_ |        |     |
     *
     * 参考定义: "#/definitions/Property.FontKerning"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string","const":"normal"}]
     *
     * @see Property_FontKerning
     */
    @Const({"auto", "none", "normal"})
    @Schema(title = "The **`font-kerning`** CSS property sets the use of the kerning information stored in a font.\n\n**Syntax**: `auto | normal | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **33** | **32**  |  **9**  | **79** | No  | |        |         | 6 _-x-_ |        |     |")
    String fontKerning() default "	";

    /**
     * The **`font-language-override`** CSS property controls the use of language-specific glyphs in a typeface.\n\n**Syntax**: `normal | <string>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **34**  |   No   |  No  | No  | |        | 4 _-x-_ |        |      |     |
     *
     * 参考定义: "#/definitions/Property.FontLanguageOverride"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontLanguageOverride
     */
    @Const({"normal"})
    @Schema(title = "The **`font-language-override`** CSS property controls the use of language-specific glyphs in a typeface.\n\n**Syntax**: `normal | <string>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **34**  |   No   |  No  | No  | |        | 4 _-x-_ |        |      |     |")
    String fontLanguageOverride() default "	";

    /**
     * The **`font-optical-sizing`** CSS property sets whether text rendering is optimized for viewing at different sizes.\n\n**Syntax**: `auto | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **79** | **62**  | **11** | **17** | No  |
     *
     * 参考定义: "#/definitions/Property.FontOpticalSizing"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"}]
     *
     * @see Property_FontOpticalSizing
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`font-optical-sizing`** CSS property sets whether text rendering is optimized for viewing at different sizes.\n\n**Syntax**: `auto | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **79** | **62**  | **11** | **17** | No  |")
    String fontOpticalSizing() default "	";

    /**
     * The **`font-smooth`** CSS property controls the application of anti-aliasing when fonts are rendered.\n\n**Syntax**: `auto | never | always | <absolute-size> | <length>`\n\n**Initial value**: `auto`\n\n|              Chrome              |              Firefox               |              Safari              |               Edge                | IE  | | :------------------------------: | :--------------------------------: | :------------------------------: | :-------------------------------: | :-: | | **5** _(-webkit-font-smoothing)_ | **25** _(-moz-osx-font-smoothing)_ | **4** _(-webkit-font-smoothing)_ | **79** _(-webkit-font-smoothing)_ | No  |
     *
     * 参考定义: "#/definitions/Property.FontSmooth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AbsoluteSize"},{"type":"string"},{"type":"number"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"never"}]
     *
     * @see Property_FontSmooth
     */
    @Const({"always", "auto", "never"})
    @Schema(title = "The **`font-smooth`** CSS property controls the application of anti-aliasing when fonts are rendered.\n\n**Syntax**: `auto | never | always | <absolute-size> | <length>`\n\n**Initial value**: `auto`\n\n|              Chrome              |              Firefox               |              Safari              |               Edge                | IE  | | :------------------------------: | :--------------------------------: | :------------------------------: | :-------------------------------: | :-: | | **5** _(-webkit-font-smoothing)_ | **25** _(-moz-osx-font-smoothing)_ | **4** _(-webkit-font-smoothing)_ | **79** _(-webkit-font-smoothing)_ | No  |")
    String fontSmooth() default "	";

    /**
     * The **`font-synthesis`** CSS property controls which missing typefaces, bold or italic, may be synthesized by the browser.\n\n**Syntax**: `none | [ weight || style || small-caps ]`\n\n**Initial value**: `weight style`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **34**  | **9**  |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.FontSynthesis"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string","const":"small-caps"},{"type":"string","const":"style"},{"type":"string","const":"weight"},{"type":"string"}]
     *
     * @see Property_FontSynthesis
     */
    @Const({"none", "small-caps", "style", "weight"})
    @Schema(title = "The **`font-synthesis`** CSS property controls which missing typefaces, bold or italic, may be synthesized by the browser.\n\n**Syntax**: `none | [ weight || style || small-caps ]`\n\n**Initial value**: `weight style`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **34**  | **9**  |  No  | No  |")
    String fontSynthesis() default "	";

    /**
     * The **`font-variant-caps`** CSS property controls the use of alternate glyphs for capital letters.\n\n**Syntax**: `normal | small-caps | all-small-caps | petite-caps | all-petite-caps | unicase | titling-caps`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **52** | **34**  | **9.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.FontVariantCaps"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all-petite-caps"},{"type":"string","const":"all-small-caps"},{"type":"string","const":"normal"},{"type":"string","const":"petite-caps"},{"type":"string","const":"small-caps"},{"type":"string","const":"titling-caps"},{"type":"string","const":"unicase"}]
     *
     * @see Property_FontVariantCaps
     */
    @Const({"all-petite-caps", "all-small-caps", "normal", "petite-caps", "small-caps", "titling-caps", "unicase"})
    @Schema(title = "The **`font-variant-caps`** CSS property controls the use of alternate glyphs for capital letters.\n\n**Syntax**: `normal | small-caps | all-small-caps | petite-caps | all-petite-caps | unicase | titling-caps`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **52** | **34**  | **9.1** | **79** | No  |")
    String fontVariantCaps() default "	";

    /**
     * The **`font-variant-east-asian`** CSS property controls the use of alternate glyphs for East Asian scripts, like Japanese and Chinese.\n\n**Syntax**: `normal | [ <east-asian-variant-values> || <east-asian-width-values> || ruby ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **63** | **34**  | **9.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.FontVariantEastAsian"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EastAsianVariantValues"},{"type":"string","const":"full-width"},{"type":"string","const":"normal"},{"type":"string","const":"proportional-width"},{"type":"string","const":"ruby"},{"type":"string"}]
     *
     * @see Property_FontVariantEastAsian
     */
    @Const({"full-width", "normal", "proportional-width", "ruby"})
    @Schema(title = "The **`font-variant-east-asian`** CSS property controls the use of alternate glyphs for East Asian scripts, like Japanese and Chinese.\n\n**Syntax**: `normal | [ <east-asian-variant-values> || <east-asian-width-values> || ruby ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **63** | **34**  | **9.1** | **79** | No  |")
    String fontVariantEastAsian() default "	";

    /**
     * The **`font-variant-ligatures`** CSS property controls which ligatures and contextual forms are used in textual content of the elements it applies to. This leads to more harmonized forms in the resulting text.\n\n**Syntax**: `normal | none | [ <common-lig-values> || <discretionary-lig-values> || <historical-lig-values> || <contextual-alt-values> ]`\n\n**Initial value**: `normal`\n\n|  Chrome  | Firefox | Safari  |  Edge  | IE  | | :------: | :-----: | :-----: | :----: | :-: | |  **34**  | **34**  | **9.1** | **79** | No  | | 31 _-x-_ |         | 7 _-x-_ |        |     |
     *
     * 参考定义: "#/definitions/Property.FontVariantLigatures"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"common-ligatures"},{"type":"string","const":"contextual"},{"type":"string","const":"discretionary-ligatures"},{"type":"string","const":"historical-ligatures"},{"type":"string","const":"no-common-ligatures"},{"type":"string","const":"no-contextual"},{"type":"string","const":"no-discretionary-ligatures"},{"type":"string","const":"no-historical-ligatures"},{"type":"string","const":"none"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontVariantLigatures
     */
    @Const({"common-ligatures", "contextual", "discretionary-ligatures", "historical-ligatures", "no-common-ligatures", "no-contextual", "no-discretionary-ligatures", "no-historical-ligatures", "none", "normal"})
    @Schema(title = "The **`font-variant-ligatures`** CSS property controls which ligatures and contextual forms are used in textual content of the elements it applies to. This leads to more harmonized forms in the resulting text.\n\n**Syntax**: `normal | none | [ <common-lig-values> || <discretionary-lig-values> || <historical-lig-values> || <contextual-alt-values> ]`\n\n**Initial value**: `normal`\n\n|  Chrome  | Firefox | Safari  |  Edge  | IE  | | :------: | :-----: | :-----: | :----: | :-: | |  **34**  | **34**  | **9.1** | **79** | No  | | 31 _-x-_ |         | 7 _-x-_ |        |     |")
    String fontVariantLigatures() default "	";

    /**
     * The **`font-variant-numeric`** CSS property controls the usage of alternate glyphs for numbers, fractions, and ordinal markers.\n\n**Syntax**: `normal | [ <numeric-figure-values> || <numeric-spacing-values> || <numeric-fraction-values> || ordinal || slashed-zero ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **52** | **34**  | **9.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.FontVariantNumeric"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"diagonal-fractions"},{"type":"string","const":"lining-nums"},{"type":"string","const":"normal"},{"type":"string","const":"oldstyle-nums"},{"type":"string","const":"ordinal"},{"type":"string","const":"proportional-nums"},{"type":"string","const":"slashed-zero"},{"type":"string","const":"stacked-fractions"},{"type":"string","const":"tabular-nums"},{"type":"string"}]
     *
     * @see Property_FontVariantNumeric
     */
    @Const({"diagonal-fractions", "lining-nums", "normal", "oldstyle-nums", "ordinal", "proportional-nums", "slashed-zero", "stacked-fractions", "tabular-nums"})
    @Schema(title = "The **`font-variant-numeric`** CSS property controls the usage of alternate glyphs for numbers, fractions, and ordinal markers.\n\n**Syntax**: `normal | [ <numeric-figure-values> || <numeric-spacing-values> || <numeric-fraction-values> || ordinal || slashed-zero ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **52** | **34**  | **9.1** | **79** | No  |")
    String fontVariantNumeric() default "	";

    /**
     * The **`font-variant-position`** CSS property controls the use of alternate, smaller glyphs that are positioned as superscript or subscript.\n\n**Syntax**: `normal | sub | super`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  | Edge | IE  | | :----: | :-----: | :-----: | :--: | :-: | |   No   | **34**  | **9.1** |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.FontVariantPosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string","const":"sub"},{"type":"string","const":"super"}]
     *
     * @see Property_FontVariantPosition
     */
    @Const({"normal", "sub", "super"})
    @Schema(title = "The **`font-variant-position`** CSS property controls the use of alternate, smaller glyphs that are positioned as superscript or subscript.\n\n**Syntax**: `normal | sub | super`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  | Edge | IE  | | :----: | :-----: | :-----: | :--: | :-: | |   No   | **34**  | **9.1** |  No  | No  |")
    String fontVariantPosition() default "	";

    /**
     * The **`font-variation-settings`** CSS property provides low-level control over variable font characteristics, by specifying the four letter axis names of the characteristics you want to vary, along with their values.\n\n**Syntax**: `normal | [ <string> <number> ]#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **62** | **62**  | **11** | **17** | No  |
     *
     * 参考定义: "#/definitions/Property.FontVariationSettings"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_FontVariationSettings
     */
    @Const({"normal"})
    @Schema(title = "The **`font-variation-settings`** CSS property provides low-level control over variable font characteristics, by specifying the four letter axis names of the characteristics you want to vary, along with their values.\n\n**Syntax**: `normal | [ <string> <number> ]#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **62** | **62**  | **11** | **17** | No  |")
    String fontVariationSettings() default "	";

    /**
     * The **`forced-color-adjust`** CSS property allows authors to opt certain elements out of forced colors mode. This then restores the control of those values to CSS.\n\n**Syntax**: `auto | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |              Edge               |                 IE                  | | :----: | :-----: | :----: | :-----------------------------: | :---------------------------------: | | **89** |   No    |   No   |             **79**              | **10** _(-ms-high-contrast-adjust)_ | |        |         |        | 12 _(-ms-high-contrast-adjust)_ |                                     |
     *
     * 参考定义: "#/definitions/Property.ForcedColorAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"}]
     *
     * @see Property_ForcedColorAdjust
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`forced-color-adjust`** CSS property allows authors to opt certain elements out of forced colors mode. This then restores the control of those values to CSS.\n\n**Syntax**: `auto | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |              Edge               |                 IE                  | | :----: | :-----: | :----: | :-----------------------------: | :---------------------------------: | | **89** |   No    |   No   |             **79**              | **10** _(-ms-high-contrast-adjust)_ | |        |         |        | 12 _(-ms-high-contrast-adjust)_ |                                     |")
    String forcedColorAdjust() default "	";

    /**
     * The **`grid-auto-columns`** CSS property specifies the size of an implicitly-created grid column track or pattern of tracks.\n\n**Syntax**: `<track-size>+`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  |             IE              | | :----: | :-----: | :------: | :----: | :-------------------------: | | **57** | **70**  | **10.1** | **16** | **10** _(-ms-grid-columns)_ |
     *
     * 参考定义: "#/definitions/Property.GridAutoColumns%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_GridAutoColumns
     */

    @Schema(title = "The **`grid-auto-columns`** CSS property specifies the size of an implicitly-created grid column track or pattern of tracks.\n\n**Syntax**: `<track-size>+`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  |             IE              | | :----: | :-----: | :------: | :----: | :-------------------------: | | **57** | **70**  | **10.1** | **16** | **10** _(-ms-grid-columns)_ |")
    String gridAutoColumns() default "	";

    /**
     * The **`grid-auto-flow`** CSS property controls how the auto-placement algorithm works, specifying exactly how auto-placed items get flowed into the grid.\n\n**Syntax**: `[ row | column ] || dense`\n\n**Initial value**: `row`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridAutoFlow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"column"},{"type":"string","const":"dense"},{"type":"string","const":"row"},{"type":"string"}]
     *
     * @see Property_GridAutoFlow
     */
    @Const({"column", "dense", "row"})
    @Schema(title = "The **`grid-auto-flow`** CSS property controls how the auto-placement algorithm works, specifying exactly how auto-placed items get flowed into the grid.\n\n**Syntax**: `[ row | column ] || dense`\n\n**Initial value**: `row`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridAutoFlow() default "	";

    /**
     * The **`grid-auto-rows`** CSS property specifies the size of an implicitly-created grid row track or pattern of tracks.\n\n**Syntax**: `<track-size>+`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  |            IE            | | :----: | :-----: | :------: | :----: | :----------------------: | | **57** | **70**  | **10.1** | **16** | **10** _(-ms-grid-rows)_ |
     *
     * 参考定义: "#/definitions/Property.GridAutoRows%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_GridAutoRows
     */

    @Schema(title = "The **`grid-auto-rows`** CSS property specifies the size of an implicitly-created grid row track or pattern of tracks.\n\n**Syntax**: `<track-size>+`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  |            IE            | | :----: | :-----: | :------: | :----: | :----------------------: | | **57** | **70**  | **10.1** | **16** | **10** _(-ms-grid-rows)_ |")
    String gridAutoRows() default "	";

    /**
     * The **`grid-column-end`** CSS property specifies a grid item’s end position within the grid column by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the block-end edge of its grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridColumnEnd"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"}]
     *
     * @see Property_GridColumnEnd
     */

    @Schema(title = "The **`grid-column-end`** CSS property specifies a grid item’s end position within the grid column by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the block-end edge of its grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridColumnEnd() default "	";

    /**
     * The **`grid-column-start`** CSS property specifies a grid item’s start position within the grid column by contributing a line, a span, or nothing (automatic) to its grid placement. This start position defines the block-start edge of the grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridColumnStart"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"}]
     *
     * @see Property_GridColumnStart
     */

    @Schema(title = "The **`grid-column-start`** CSS property specifies a grid item’s start position within the grid column by contributing a line, a span, or nothing (automatic) to its grid placement. This start position defines the block-start edge of the grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridColumnStart() default "	";

    /**
     * The **`grid-row-end`** CSS property specifies a grid item’s end position within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-end edge of its grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridRowEnd"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"}]
     *
     * @see Property_GridRowEnd
     */

    @Schema(title = "The **`grid-row-end`** CSS property specifies a grid item’s end position within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-end edge of its grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridRowEnd() default "	";

    /**
     * The **`grid-row-start`** CSS property specifies a grid item’s start position within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start edge of its grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridRowStart"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GridLine"}]
     *
     * @see Property_GridRowStart
     */

    @Schema(title = "The **`grid-row-start`** CSS property specifies a grid item’s start position within the grid row by contributing a line, a span, or nothing (automatic) to its grid placement, thereby specifying the inline-start edge of its grid area.\n\n**Syntax**: `<grid-line>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridRowStart() default "	";

    /**
     * The **`grid-template-areas`** CSS property specifies named grid areas, establishing the cells in the grid and assigning them names.\n\n**Syntax**: `none | <string>+`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |
     *
     * 参考定义: "#/definitions/Property.GridTemplateAreas"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_GridTemplateAreas
     */
    @Const({"none"})
    @Schema(title = "The **`grid-template-areas`** CSS property specifies named grid areas, establishing the cells in the grid and assigning them names.\n\n**Syntax**: `none | <string>+`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |")
    String gridTemplateAreas() default "	";

    /**
     * The **`grid-template-columns`** CSS property defines the line names and track sizing functions of the grid columns.\n\n**Syntax**: `none | <track-list> | <auto-track-list> | subgrid <line-name-list>?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  |             IE              | | :----: | :-----: | :------: | :----: | :-------------------------: | | **57** | **52**  | **10.1** | **16** | **10** _(-ms-grid-columns)_ |
     *
     * 参考定义: "#/definitions/Property.GridTemplateColumns%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string","const":"subgrid"},{"type":"string"}]
     *
     * @see Property_GridTemplateColumns
     */
    @Const({"none", "subgrid"})
    @Schema(title = "The **`grid-template-columns`** CSS property defines the line names and track sizing functions of the grid columns.\n\n**Syntax**: `none | <track-list> | <auto-track-list> | subgrid <line-name-list>?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  |             IE              | | :----: | :-----: | :------: | :----: | :-------------------------: | | **57** | **52**  | **10.1** | **16** | **10** _(-ms-grid-columns)_ |")
    String gridTemplateColumns() default "	";

    /**
     * The **`grid-template-rows`** CSS property defines the line names and track sizing functions of the grid rows.\n\n**Syntax**: `none | <track-list> | <auto-track-list> | subgrid <line-name-list>?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  |            IE            | | :----: | :-----: | :------: | :----: | :----------------------: | | **57** | **52**  | **10.1** | **16** | **10** _(-ms-grid-rows)_ |
     *
     * 参考定义: "#/definitions/Property.GridTemplateRows%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.TrackBreadth%3C(string%7Cnumber)%3E"},{"type":"string","const":"none"},{"type":"string","const":"subgrid"},{"type":"string"}]
     *
     * @see Property_GridTemplateRows
     */
    @Const({"none", "subgrid"})
    @Schema(title = "The **`grid-template-rows`** CSS property defines the line names and track sizing functions of the grid rows.\n\n**Syntax**: `none | <track-list> | <auto-track-list> | subgrid <line-name-list>?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  |            IE            | | :----: | :-----: | :------: | :----: | :----------------------: | | **57** | **52**  | **10.1** | **16** | **10** _(-ms-grid-rows)_ |")
    String gridTemplateRows() default "	";

    /**
     * The **`hanging-punctuation`** CSS property specifies whether a punctuation mark should hang at the start or end of a line of text. Hanging punctuation may be placed outside the line box.\n\n**Syntax**: `none | [ first || [ force-end | allow-end ] || last ]`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   |   No    | **10** |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.HangingPunctuation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"allow-end"},{"type":"string","const":"first"},{"type":"string","const":"force-end"},{"type":"string","const":"last"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_HangingPunctuation
     */
    @Const({"allow-end", "first", "force-end", "last", "none"})
    @Schema(title = "The **`hanging-punctuation`** CSS property specifies whether a punctuation mark should hang at the start or end of a line of text. Hanging punctuation may be placed outside the line box.\n\n**Syntax**: `none | [ first || [ force-end | allow-end ] || last ]`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   |   No    | **10** |  No  | No  |")
    String hangingPunctuation() default "	";

    /**
     * The **`height`** CSS property specifies the height of an element. By default, the property defines the height of the content area. If `box-sizing` is set to `border-box`, however, it instead determines the height of the border area.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Height%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_Height
     */
    @Const({"-moz-max-content", "-moz-min-content", "-webkit-fit-content", "auto", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`height`** CSS property specifies the height of an element. By default, the property defines the height of the content area. If `box-sizing` is set to `border-box`, however, it instead determines the height of the border area.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String height() default "	";

    /**
     * The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`\n\n|  Chrome  | Firefox |    Safari     |  Edge  |      IE      | | :------: | :-----: | :-----------: | :----: | :----------: | |  **55**  | **43**  | **5.1** _-x-_ | **79** | **10** _-x-_ | | 13 _-x-_ | 6 _-x-_ |               |        |              |
     *
     * 参考定义: "#/definitions/Property.Hyphens"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"manual"},{"type":"string","const":"none"}]
     *
     * @see Property_Hyphens
     */
    @Const({"auto", "manual", "none"})
    @Schema(title = "The **`hyphens`** CSS property specifies how words should be hyphenated when text wraps across multiple lines. It can prevent hyphenation entirely, hyphenate at manually-specified points within the text, or let the browser automatically insert hyphens where appropriate.\n\n**Syntax**: `none | manual | auto`\n\n**Initial value**: `manual`\n\n|  Chrome  | Firefox |    Safari     |  Edge  |      IE      | | :------: | :-----: | :-----------: | :----: | :----------: | |  **55**  | **43**  | **5.1** _-x-_ | **79** | **10** _-x-_ | | 13 _-x-_ | 6 _-x-_ |               |        |              |")
    String hyphens() default "	";

    /**
     * The **`image-orientation`** CSS property specifies a layout-independent correction to the orientation of an image. It should _not_ be used for any other orientation adjustments; instead, the `transform` property should be used with the `rotate` `<transform-function>`.\n\n**Syntax**: `from-image | <angle> | [ <angle>? flip ]`\n\n**Initial value**: `from-image`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **81** | **26**  | **13.1** | **81** | No  |
     *
     * 参考定义: "#/definitions/Property.ImageOrientation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"flip"},{"type":"string","const":"from-image"},{"type":"string"}]
     *
     * @see Property_ImageOrientation
     */
    @Const({"flip", "from-image"})
    @Schema(title = "The **`image-orientation`** CSS property specifies a layout-independent correction to the orientation of an image. It should _not_ be used for any other orientation adjustments; instead, the `transform` property should be used with the `rotate` `<transform-function>`.\n\n**Syntax**: `from-image | <angle> | [ <angle>? flip ]`\n\n**Initial value**: `from-image`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **81** | **26**  | **13.1** | **81** | No  |")
    String imageOrientation() default "	";

    /**
     * **Syntax**: `[ from-image || <resolution> ] && snap?`\n\n**Initial value**: `1dppx`
     *
     * 参考定义: "#/definitions/Property.ImageResolution"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"from-image"},{"type":"string"}]
     *
     * @see Property_ImageResolution
     */
    @Const({"from-image"})
    @Schema(title = "**Syntax**: `[ from-image || <resolution> ] && snap?`\n\n**Initial value**: `1dppx`")
    String imageResolution() default "	";

    /**
     * The `initial-letter` CSS property sets styling for dropped, raised, and sunken initial letters.\n\n**Syntax**: `normal | [ <number> <integer>? ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox |   Safari    | Edge | IE  | | :----: | :-----: | :---------: | :--: | :-: | |   No   |   No    | **9** _-x-_ |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.InitialLetter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_InitialLetter
     */
    @Const({"normal"})
    @Schema(title = "The `initial-letter` CSS property sets styling for dropped, raised, and sunken initial letters.\n\n**Syntax**: `normal | [ <number> <integer>? ]`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox |   Safari    | Edge | IE  | | :----: | :-----: | :---------: | :--: | :-: | |   No   |   No    | **9** _-x-_ |  No  | No  |")
    String initialLetter() default "	";

    /**
     * The **`inline-size`** CSS property defines the horizontal or vertical size of an element's block, depending on its writing mode. It corresponds to either the `width` or the `height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'width'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.InlineSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_InlineSize
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "auto", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`inline-size`** CSS property defines the horizontal or vertical size of an element's block, depending on its writing mode. It corresponds to either the `width` or the `height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'width'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |")
    String inlineSize() default "	";

    /**
     * The **`inset`** CSS property is a shorthand that corresponds to the `top`, `right`, `bottom`, and/or `left` properties. It has the same multi-value syntax of the `margin` shorthand.\n\n**Syntax**: `<'top'>{1,4}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.Inset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Inset
     */
    @Const({"auto"})
    @Schema(title = "The **`inset`** CSS property is a shorthand that corresponds to the `top`, `right`, `bottom`, and/or `left` properties. It has the same multi-value syntax of the `margin` shorthand.\n\n**Syntax**: `<'top'>{1,4}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String inset() default "	";

    /**
     * The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.InsetBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetBlock
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |")
    String insetBlock() default "	";

    /**
     * The **`inset-block-end`** CSS property defines the logical block end offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.InsetBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetBlockEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-block-end`** CSS property defines the logical block end offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |")
    String insetBlockEnd() default "	";

    /**
     * The **`inset-block-start`** CSS property defines the logical block start offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.InsetBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetBlockStart
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-block-start`** CSS property defines the logical block start offset of an element, which maps to a physical inset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |")
    String insetBlockStart() default "	";

    /**
     * The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.InsetInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetInline
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline`** CSS property defines the logical start and end offsets of an element in the inline direction, which maps to physical offsets depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top` and `bottom`, or `right` and `left` properties depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |")
    String insetInline() default "	";

    /**
     * The **`inset-inline-end`** CSS property defines the logical inline end inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.InsetInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetInlineEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline-end`** CSS property defines the logical inline end inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |")
    String insetInlineEnd() default "	";

    /**
     * The **`inset-inline-start`** CSS property defines the logical inline start inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.InsetInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_InsetInlineStart
     */
    @Const({"auto"})
    @Schema(title = "The **`inset-inline-start`** CSS property defines the logical inline start inset of an element, which maps to a physical offset depending on the element's writing mode, directionality, and text orientation. It corresponds to the `top`, `right`, `bottom`, or `left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'top'>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **63**  | **14.1** | **87** | No  |")
    String insetInlineStart() default "	";

    /**
     * The **`isolation`** CSS property determines whether an element must create a new stacking context.\n\n**Syntax**: `auto | isolate`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **41** | **36**  | **8**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.Isolation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"isolate"}]
     *
     * @see Property_Isolation
     */
    @Const({"auto", "isolate"})
    @Schema(title = "The **`isolation`** CSS property determines whether an element must create a new stacking context.\n\n**Syntax**: `auto | isolate`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **41** | **36**  | **8**  | **79** | No  |")
    String isolation() default "	";

    /**
     * The CSS **`justify-content`** property defines how the browser distributes space between and around content items along the main-axis of a flex container, and the inline axis of a grid container.\n\n**Syntax**: `normal | <content-distribution> | <overflow-position>? [ <content-position> | left | right ]`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **52**  | **20**  |   **9**   | **12** | **11** | | 21 _-x-_ |         | 6.1 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |\n\n---
     *
     * 参考定义: "#/definitions/Property.JustifyContent"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string"}]
     *
     * @see Property_JustifyContent
     */
    @Const({"left", "normal", "right"})
    @Schema(title = "The CSS **`justify-content`** property defines how the browser distributes space between and around content items along the main-axis of a flex container, and the inline axis of a grid container.\n\n**Syntax**: `normal | <content-distribution> | <overflow-position>? [ <content-position> | left | right ]`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n|  Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :------: | :-----: | :-------: | :----: | :----: | |  **52**  | **20**  |   **9**   | **12** | **11** | | 21 _-x-_ |         | 6.1 _-x-_ |        |        |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **52**  | **10.1** | **16** | No  |\n\n---")
    String justifyContent() default "	";

    /**
     * The CSS **`justify-items`** property defines the default `justify-self` for all items of the box, giving them all a default way of justifying each box along the appropriate axis.\n\n**Syntax**: `normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ] | legacy | legacy && [ left | right | center ]`\n\n**Initial value**: `legacy`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **52** | **20**  | **9**  | **12** | **11** |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **45**  | **10.1** | **16** | No  |\n\n---
     *
     * 参考定义: "#/definitions/Property.JustifyItems"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"left"},{"type":"string","const":"legacy"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_JustifyItems
     */
    @Const({"baseline", "left", "legacy", "normal", "right", "stretch"})
    @Schema(title = "The CSS **`justify-items`** property defines the default `justify-self` for all items of the box, giving them all a default way of justifying each box along the appropriate axis.\n\n**Syntax**: `normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ] | legacy | legacy && [ left | right | center ]`\n\n**Initial value**: `legacy`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | | **52** | **20**  | **9**  | **12** | **11** |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **45**  | **10.1** | **16** | No  |\n\n---")
    String justifyItems() default "	";

    /**
     * The CSS **`justify-self`** property sets the way a box is justified inside its alignment container along the appropriate axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ]`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **45**  | **10.1** | **16** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  |      IE      | | :----: | :-----: | :------: | :----: | :----------: | | **57** | **45**  | **10.1** | **16** | **10** _-x-_ |\n\n---
     *
     * 参考定义: "#/definitions/Property.JustifySelf"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.SelfPosition"},{"type":"string","const":"auto"},{"type":"string","const":"baseline"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_JustifySelf
     */
    @Const({"auto", "baseline", "left", "normal", "right", "stretch"})
    @Schema(title = "The CSS **`justify-self`** property sets the way a box is justified inside its alignment container along the appropriate axis.\n\n**Syntax**: `auto | normal | stretch | <baseline-position> | <overflow-position>? [ <self-position> | left | right ]`\n\n**Initial value**: `auto`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **45**  | **10.1** | **16** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  |      IE      | | :----: | :-----: | :------: | :----: | :----------: | | **57** | **45**  | **10.1** | **16** | **10** _-x-_ |\n\n---")
    String justifySelf() default "	";

    /**
     * The **`justify-tracks`** CSS property sets the alignment in the masonry axis for grid containers that have masonry in their inline axis.\n\n**Syntax**: `[ normal | <content-distribution> | <overflow-position>? [ <content-position> | left | right ] ]#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   |   n/a   |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.JustifyTracks"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"left"},{"type":"string","const":"normal"},{"type":"string","const":"right"},{"type":"string"}]
     *
     * @see Property_JustifyTracks
     */
    @Const({"left", "normal", "right"})
    @Schema(title = "The **`justify-tracks`** CSS property sets the alignment in the masonry axis for grid containers that have masonry in their inline axis.\n\n**Syntax**: `[ normal | <content-distribution> | <overflow-position>? [ <content-position> | left | right ] ]#`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   |   n/a   |   No   |  No  | No  |")
    String justifyTracks() default "	";

    /**
     * The **`left`** CSS property participates in specifying the horizontal position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.Left%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Left
     */
    @Const({"auto"})
    @Schema(title = "The **`left`** CSS property participates in specifying the horizontal position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String left() default "	";

    /**
     * The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE    | | :-----: | :-----: | :-----: | :----: | :-----: | | **58**  | **69**  | **11**  | **14** | **5.5** | | 1 _-x-_ |         | 3 _-x-_ |        |         |
     *
     * 参考定义: "#/definitions/Property.LineBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"auto"},{"type":"string","const":"loose"},{"type":"string","const":"normal"},{"type":"string","const":"strict"}]
     *
     * @see Property_LineBreak
     */
    @Const({"anywhere", "auto", "loose", "normal", "strict"})
    @Schema(title = "The **`line-break`** CSS property sets how to break lines of Chinese, Japanese, or Korean (CJK) text when working with punctuation and symbols.\n\n**Syntax**: `auto | loose | normal | strict | anywhere`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE    | | :-----: | :-----: | :-----: | :----: | :-----: | | **58**  | **69**  | **11**  | **14** | **5.5** | | 1 _-x-_ |         | 3 _-x-_ |        |         |")
    String lineBreak() default "	";

    /**
     * The **`line-height-step`** CSS property sets the step unit for line box heights. When the property is set, line box heights are rounded up to the closest multiple of the unit.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |  n/a   |   No    |   No   | n/a  | No  |
     *
     * 参考定义: "#/definitions/Property.LineHeightStep%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_LineHeightStep
     */

    @Schema(title = "The **`line-height-step`** CSS property sets the step unit for line box heights. When the property is set, line box heights are rounded up to the closest multiple of the unit.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |  n/a   |   No    |   No   | n/a  | No  |")
    String lineHeightStep() default "	";

    /**
     * The **`list-style-image`** CSS property sets an image to be used as the list item marker.\n\n**Syntax**: `<image> | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.ListStyleImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ListStyleImage
     */
    @Const({"none"})
    @Schema(title = "The **`list-style-image`** CSS property sets an image to be used as the list item marker.\n\n**Syntax**: `<image> | none`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String listStyleImage() default "	";

    /**
     * The **`list-style-position`** CSS property sets the position of the `::marker` relative to a list item.\n\n**Syntax**: `inside | outside`\n\n**Initial value**: `outside`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.ListStylePosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"inside"},{"type":"string","const":"outside"}]
     *
     * @see Property_ListStylePosition
     */
    @Const({"inside", "outside"})
    @Schema(title = "The **`list-style-position`** CSS property sets the position of the `::marker` relative to a list item.\n\n**Syntax**: `inside | outside`\n\n**Initial value**: `outside`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String listStylePosition() default "	";

    /**
     * The **`list-style-type`** CSS property sets the marker (such as a disc, character, or custom counter style) of a list item element.\n\n**Syntax**: `<counter-style> | <string> | none`\n\n**Initial value**: `disc`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.ListStyleType"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ListStyleType
     */
    @Const({"none"})
    @Schema(title = "The **`list-style-type`** CSS property sets the marker (such as a disc, character, or custom counter style) of a list item element.\n\n**Syntax**: `<counter-style> | <string> | none`\n\n**Initial value**: `disc`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String listStyleType() default "	";

    /**
     * The **`margin-block`** CSS shorthand property defines the logical block start and end margins of an element, which maps to physical margins depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.MarginBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginBlock
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-block`** CSS shorthand property defines the logical block start and end margins of an element, which maps to physical margins depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String marginBlock() default "	";

    /**
     * The **`margin-block-end`** CSS property defines the logical block end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MarginBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginBlockEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-block-end`** CSS property defines the logical block end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String marginBlockEnd() default "	";

    /**
     * The **`margin-block-start`** CSS property defines the logical block start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MarginBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginBlockStart
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-block-start`** CSS property defines the logical block start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String marginBlockStart() default "	";

    /**
     * The **`margin-bottom`** CSS property sets the margin area on the bottom of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.MarginBottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginBottom
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-bottom`** CSS property sets the margin area on the bottom of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String marginBottom() default "	";

    /**
     * The **`margin-inline`** CSS shorthand property is a shorthand property that defines both the logical inline start and end margins of an element, which maps to physical margins depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.MarginInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInline
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline`** CSS shorthand property is a shorthand property that defines both the logical inline start and end margins of an element, which maps to physical margins depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'margin-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String marginInline() default "	";

    /**
     * The **`margin-inline-end`** CSS property defines the logical inline end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. In other words, it corresponds to the `margin-top`, `margin-right`, `margin-bottom` or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n|          Chrome          |        Firefox        |          Safari          |  Edge  | IE  | | :----------------------: | :-------------------: | :----------------------: | :----: | :-: | |          **69**          |        **41**         |         **12.1**         | **79** | No  | | 2 _(-webkit-margin-end)_ | 3 _(-moz-margin-end)_ | 3 _(-webkit-margin-end)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.MarginInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInlineEnd
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline-end`** CSS property defines the logical inline end margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. In other words, it corresponds to the `margin-top`, `margin-right`, `margin-bottom` or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n|          Chrome          |        Firefox        |          Safari          |  Edge  | IE  | | :----------------------: | :-------------------: | :----------------------: | :----: | :-: | |          **69**          |        **41**         |         **12.1**         | **79** | No  | | 2 _(-webkit-margin-end)_ | 3 _(-moz-margin-end)_ | 3 _(-webkit-margin-end)_ |        |     |")
    String marginInlineEnd() default "	";

    /**
     * The **`margin-inline-start`** CSS property defines the logical inline start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. It corresponds to the `margin-top`, `margin-right`, `margin-bottom`, or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n|           Chrome           |         Firefox         |           Safari           |  Edge  | IE  | | :------------------------: | :---------------------: | :------------------------: | :----: | :-: | |           **69**           |         **41**          |          **12.1**          | **79** | No  | | 2 _(-webkit-margin-start)_ | 3 _(-moz-margin-start)_ | 3 _(-webkit-margin-start)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.MarginInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginInlineStart
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-inline-start`** CSS property defines the logical inline start margin of an element, which maps to a physical margin depending on the element's writing mode, directionality, and text orientation. It corresponds to the `margin-top`, `margin-right`, `margin-bottom`, or `margin-left` property depending on the values defined for `writing-mode`, `direction`, and `text-orientation`.\n\n**Syntax**: `<'margin-left'>`\n\n**Initial value**: `0`\n\n|           Chrome           |         Firefox         |           Safari           |  Edge  | IE  | | :------------------------: | :---------------------: | :------------------------: | :----: | :-: | |           **69**           |         **41**          |          **12.1**          | **79** | No  | | 2 _(-webkit-margin-start)_ | 3 _(-moz-margin-start)_ | 3 _(-webkit-margin-start)_ |        |     |")
    String marginInlineStart() default "	";

    /**
     * The **`margin-left`** CSS property sets the margin area on the left side of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.MarginLeft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginLeft
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-left`** CSS property sets the margin area on the left side of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String marginLeft() default "	";

    /**
     * The **`margin-right`** CSS property sets the margin area on the right side of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.MarginRight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginRight
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-right`** CSS property sets the margin area on the right side of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String marginRight() default "	";

    /**
     * The **`margin-top`** CSS property sets the margin area on the top of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.MarginTop%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MarginTop
     */
    @Const({"auto"})
    @Schema(title = "The **`margin-top`** CSS property sets the margin area on the top of an element. A positive value places it farther from its neighbors, while a negative value places it closer.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String marginTop() default "	";

    /**
     * The **`mask-border-mode`** CSS property specifies the blending mode used in a mask border.\n\n**Syntax**: `luminance | alpha`\n\n**Initial value**: `alpha`
     *
     * 参考定义: "#/definitions/Property.MaskBorderMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alpha"},{"type":"string","const":"luminance"}]
     *
     * @see Property_MaskBorderMode
     */
    @Const({"alpha", "luminance"})
    @Schema(title = "The **`mask-border-mode`** CSS property specifies the blending mode used in a mask border.\n\n**Syntax**: `luminance | alpha`\n\n**Initial value**: `alpha`")
    String maskBorderMode() default "	";

    /**
     * The **`mask-border-outset`** CSS property specifies the distance by which an element's mask border is set out from its border box.\n\n**Syntax**: `[ <length> | <number> ]{1,4}`\n\n**Initial value**: `0`\n\n|                 Chrome                  | Firefox |                  Safari                   |                   Edge                   | IE  | | :-------------------------------------: | :-----: | :---------------------------------------: | :--------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-outset)_ |   No    | **3.1** _(-webkit-mask-box-image-outset)_ | **79** _(-webkit-mask-box-image-outset)_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskBorderOutset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MaskBorderOutset
     */

    @Schema(title = "The **`mask-border-outset`** CSS property specifies the distance by which an element's mask border is set out from its border box.\n\n**Syntax**: `[ <length> | <number> ]{1,4}`\n\n**Initial value**: `0`\n\n|                 Chrome                  | Firefox |                  Safari                   |                   Edge                   | IE  | | :-------------------------------------: | :-----: | :---------------------------------------: | :--------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-outset)_ |   No    | **3.1** _(-webkit-mask-box-image-outset)_ | **79** _(-webkit-mask-box-image-outset)_ | No  |")
    String maskBorderOutset() default "	";

    /**
     * The **`mask-border-repeat`** CSS property sets how the edge regions of a source image are adjusted to fit the dimensions of an element's mask border.\n\n**Syntax**: `[ stretch | repeat | round | space ]{1,2}`\n\n**Initial value**: `stretch`\n\n|                 Chrome                  | Firefox |                  Safari                   |                   Edge                   | IE  | | :-------------------------------------: | :-----: | :---------------------------------------: | :--------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-repeat)_ |   No    | **3.1** _(-webkit-mask-box-image-repeat)_ | **79** _(-webkit-mask-box-image-repeat)_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskBorderRepeat"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"repeat"},{"type":"string","const":"round"},{"type":"string","const":"space"},{"type":"string","const":"stretch"},{"type":"string"}]
     *
     * @see Property_MaskBorderRepeat
     */
    @Const({"repeat", "round", "space", "stretch"})
    @Schema(title = "The **`mask-border-repeat`** CSS property sets how the edge regions of a source image are adjusted to fit the dimensions of an element's mask border.\n\n**Syntax**: `[ stretch | repeat | round | space ]{1,2}`\n\n**Initial value**: `stretch`\n\n|                 Chrome                  | Firefox |                  Safari                   |                   Edge                   | IE  | | :-------------------------------------: | :-----: | :---------------------------------------: | :--------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-repeat)_ |   No    | **3.1** _(-webkit-mask-box-image-repeat)_ | **79** _(-webkit-mask-box-image-repeat)_ | No  |")
    String maskBorderRepeat() default "	";

    /**
     * The **`mask-border-slice`** CSS property divides the image set by `mask-border-source` into regions. These regions are used to form the components of an element's mask border.\n\n**Syntax**: `<number-percentage>{1,4} fill?`\n\n**Initial value**: `0`\n\n|                 Chrome                 | Firefox |                  Safari                  |                  Edge                   | IE  | | :------------------------------------: | :-----: | :--------------------------------------: | :-------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-slice)_ |   No    | **3.1** _(-webkit-mask-box-image-slice)_ | **79** _(-webkit-mask-box-image-slice)_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskBorderSlice"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_MaskBorderSlice
     */

    @Schema(title = "The **`mask-border-slice`** CSS property divides the image set by `mask-border-source` into regions. These regions are used to form the components of an element's mask border.\n\n**Syntax**: `<number-percentage>{1,4} fill?`\n\n**Initial value**: `0`\n\n|                 Chrome                 | Firefox |                  Safari                  |                  Edge                   | IE  | | :------------------------------------: | :-----: | :--------------------------------------: | :-------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-slice)_ |   No    | **3.1** _(-webkit-mask-box-image-slice)_ | **79** _(-webkit-mask-box-image-slice)_ | No  |")
    String maskBorderSlice() default "	";

    /**
     * The **`mask-border-source`** CSS property sets the source image used to create an element's mask border.\n\n**Syntax**: `none | <image>`\n\n**Initial value**: `none`\n\n|                 Chrome                  | Firefox |                  Safari                   |                   Edge                   | IE  | | :-------------------------------------: | :-----: | :---------------------------------------: | :--------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-source)_ |   No    | **3.1** _(-webkit-mask-box-image-source)_ | **79** _(-webkit-mask-box-image-source)_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskBorderSource"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MaskBorderSource
     */
    @Const({"none"})
    @Schema(title = "The **`mask-border-source`** CSS property sets the source image used to create an element's mask border.\n\n**Syntax**: `none | <image>`\n\n**Initial value**: `none`\n\n|                 Chrome                  | Firefox |                  Safari                   |                   Edge                   | IE  | | :-------------------------------------: | :-----: | :---------------------------------------: | :--------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-source)_ |   No    | **3.1** _(-webkit-mask-box-image-source)_ | **79** _(-webkit-mask-box-image-source)_ | No  |")
    String maskBorderSource() default "	";

    /**
     * The **`mask-border-width`** CSS property sets the width of an element's mask border.\n\n**Syntax**: `[ <length-percentage> | <number> | auto ]{1,4}`\n\n**Initial value**: `auto`\n\n|                 Chrome                 | Firefox |                  Safari                  |                  Edge                   | IE  | | :------------------------------------: | :-----: | :--------------------------------------: | :-------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-width)_ |   No    | **3.1** _(-webkit-mask-box-image-width)_ | **79** _(-webkit-mask-box-image-width)_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskBorderWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_MaskBorderWidth
     */
    @Const({"auto"})
    @Schema(title = "The **`mask-border-width`** CSS property sets the width of an element's mask border.\n\n**Syntax**: `[ <length-percentage> | <number> | auto ]{1,4}`\n\n**Initial value**: `auto`\n\n|                 Chrome                 | Firefox |                  Safari                  |                  Edge                   | IE  | | :------------------------------------: | :-----: | :--------------------------------------: | :-------------------------------------: | :-: | | **1** _(-webkit-mask-box-image-width)_ |   No    | **3.1** _(-webkit-mask-box-image-width)_ | **79** _(-webkit-mask-box-image-width)_ | No  |")
    String maskBorderWidth() default "	";

    /**
     * The **`mask-clip`** CSS property determines the area which is affected by a mask. The painted content of an element must be restricted to this area.\n\n**Syntax**: `[ <geometry-box> | no-clip ]#`\n\n**Initial value**: `border-box`\n\n|   Chrome    | Firefox |   Safari    |     Edge     | IE  | | :---------: | :-----: | :---------: | :----------: | :-: | | **1** _-x-_ | **53**  | **4** _-x-_ | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskClip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"no-clip"},{"type":"string"}]
     *
     * @see Property_MaskClip
     */
    @Const({"no-clip"})
    @Schema(title = "The **`mask-clip`** CSS property determines the area which is affected by a mask. The painted content of an element must be restricted to this area.\n\n**Syntax**: `[ <geometry-box> | no-clip ]#`\n\n**Initial value**: `border-box`\n\n|   Chrome    | Firefox |   Safari    |     Edge     | IE  | | :---------: | :-----: | :---------: | :----------: | :-: | | **1** _-x-_ | **53**  | **4** _-x-_ | **79** _-x-_ | No  |")
    String maskClip() default "	";

    /**
     * The **`mask-composite`** CSS property represents a compositing operation used on the current mask layer with the mask layers below it.\n\n**Syntax**: `<compositing-operator>#`\n\n**Initial value**: `add`\n\n| Chrome | Firefox | Safari | Edge  | IE  | | :----: | :-----: | :----: | :---: | :-: | |   No   | **53**  |   No   | 18-79 | No  |
     *
     * 参考定义: "#/definitions/Property.MaskComposite"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.CompositingOperator"},{"type":"string"}]
     *
     * @see Property_MaskComposite
     */

    @Schema(title = "The **`mask-composite`** CSS property represents a compositing operation used on the current mask layer with the mask layers below it.\n\n**Syntax**: `<compositing-operator>#`\n\n**Initial value**: `add`\n\n| Chrome | Firefox | Safari | Edge  | IE  | | :----: | :-----: | :----: | :---: | :-: | |   No   | **53**  |   No   | 18-79 | No  |")
    String maskComposite() default "	";

    /**
     * The **`mask-image`** CSS property sets the image that is used as mask layer for an element.\n\n**Syntax**: `<mask-reference>#`\n\n**Initial value**: `none`\n\n|   Chrome    | Firefox |   Safari    | Edge  | IE  | | :---------: | :-----: | :---------: | :---: | :-: | | **1** _-x-_ | **53**  | **4** _-x-_ | 16-79 | No  |
     *
     * 参考定义: "#/definitions/Property.MaskImage"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_MaskImage
     */
    @Const({"none"})
    @Schema(title = "The **`mask-image`** CSS property sets the image that is used as mask layer for an element.\n\n**Syntax**: `<mask-reference>#`\n\n**Initial value**: `none`\n\n|   Chrome    | Firefox |   Safari    | Edge  | IE  | | :---------: | :-----: | :---------: | :---: | :-: | | **1** _-x-_ | **53**  | **4** _-x-_ | 16-79 | No  |")
    String maskImage() default "	";

    /**
     * The **`mask-mode`** CSS property sets whether the mask reference defined by `mask-image` is treated as a luminance or alpha mask.\n\n**Syntax**: `<masking-mode>#`\n\n**Initial value**: `match-source`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **53**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.MaskMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.MaskingMode"},{"type":"string"}]
     *
     * @see Property_MaskMode
     */

    @Schema(title = "The **`mask-mode`** CSS property sets whether the mask reference defined by `mask-image` is treated as a luminance or alpha mask.\n\n**Syntax**: `<masking-mode>#`\n\n**Initial value**: `match-source`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **53**  |   No   |  No  | No  |")
    String maskMode() default "	";

    /**
     * The **`mask-origin`** CSS property sets the origin of a mask.\n\n**Syntax**: `<geometry-box>#`\n\n**Initial value**: `border-box`\n\n|   Chrome    | Firefox |   Safari    |     Edge     | IE  | | :---------: | :-----: | :---------: | :----------: | :-: | | **1** _-x-_ | **53**  | **4** _-x-_ | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.MaskOrigin"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string"}]
     *
     * @see Property_MaskOrigin
     */

    @Schema(title = "The **`mask-origin`** CSS property sets the origin of a mask.\n\n**Syntax**: `<geometry-box>#`\n\n**Initial value**: `border-box`\n\n|   Chrome    | Firefox |   Safari    |     Edge     | IE  | | :---------: | :-----: | :---------: | :----------: | :-: | | **1** _-x-_ | **53**  | **4** _-x-_ | **79** _-x-_ | No  |")
    String maskOrigin() default "	";

    /**
     * The **`mask-position`** CSS property sets the initial position, relative to the mask position layer set by `mask-origin`, for each defined mask image.\n\n**Syntax**: `<position>#`\n\n**Initial value**: `center`\n\n|   Chrome    | Firefox |    Safari     | Edge  | IE  | | :---------: | :-----: | :-----------: | :---: | :-: | | **1** _-x-_ | **53**  | **3.1** _-x-_ | 18-79 | No  |
     *
     * 参考定义: "#/definitions/Property.MaskPosition%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_MaskPosition
     */

    @Schema(title = "The **`mask-position`** CSS property sets the initial position, relative to the mask position layer set by `mask-origin`, for each defined mask image.\n\n**Syntax**: `<position>#`\n\n**Initial value**: `center`\n\n|   Chrome    | Firefox |    Safari     | Edge  | IE  | | :---------: | :-----: | :-----------: | :---: | :-: | | **1** _-x-_ | **53**  | **3.1** _-x-_ | 18-79 | No  |")
    String maskPosition() default "	";

    /**
     * The **`mask-repeat`** CSS property sets how mask images are repeated. A mask image can be repeated along the horizontal axis, the vertical axis, both axes, or not repeated at all.\n\n**Syntax**: `<repeat-style>#`\n\n**Initial value**: `no-repeat`\n\n|   Chrome    | Firefox |    Safari     | Edge  | IE  | | :---------: | :-----: | :-----------: | :---: | :-: | | **1** _-x-_ | **53**  | **3.1** _-x-_ | 18-79 | No  |
     *
     * 参考定义: "#/definitions/Property.MaskRepeat"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.RepeatStyle"},{"type":"string"}]
     *
     * @see Property_MaskRepeat
     */

    @Schema(title = "The **`mask-repeat`** CSS property sets how mask images are repeated. A mask image can be repeated along the horizontal axis, the vertical axis, both axes, or not repeated at all.\n\n**Syntax**: `<repeat-style>#`\n\n**Initial value**: `no-repeat`\n\n|   Chrome    | Firefox |    Safari     | Edge  | IE  | | :---------: | :-----: | :-----------: | :---: | :-: | | **1** _-x-_ | **53**  | **3.1** _-x-_ | 18-79 | No  |")
    String maskRepeat() default "	";

    /**
     * The **`mask-size`** CSS property specifies the sizes of the mask images. The size of the image can be fully or partially constrained in order to preserve its intrinsic ratio.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto`\n\n|   Chrome    | Firefox |   Safari    | Edge  | IE  | | :---------: | :-----: | :---------: | :---: | :-: | | **4** _-x-_ | **53**  | **4** _-x-_ | 18-79 | No  |
     *
     * 参考定义: "#/definitions/Property.MaskSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BgSize%3C(string%7Cnumber)%3E"},{"type":"string"}]
     *
     * @see Property_MaskSize
     */

    @Schema(title = "The **`mask-size`** CSS property specifies the sizes of the mask images. The size of the image can be fully or partially constrained in order to preserve its intrinsic ratio.\n\n**Syntax**: `<bg-size>#`\n\n**Initial value**: `auto`\n\n|   Chrome    | Firefox |   Safari    | Edge  | IE  | | :---------: | :-----: | :---------: | :---: | :-: | | **4** _-x-_ | **53**  | **4** _-x-_ | 18-79 | No  |")
    String maskSize() default "	";

    /**
     * The **`mask-type`** CSS property sets whether an SVG `<mask>` element is used as a _luminance_ or an _alpha_ mask. It applies to the `<mask>` element itself.\n\n**Syntax**: `luminance | alpha`\n\n**Initial value**: `luminance`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **24** | **35**  | **7**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MaskType"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alpha"},{"type":"string","const":"luminance"}]
     *
     * @see Property_MaskType
     */
    @Const({"alpha", "luminance"})
    @Schema(title = "The **`mask-type`** CSS property sets whether an SVG `<mask>` element is used as a _luminance_ or an _alpha_ mask. It applies to the `<mask>` element itself.\n\n**Syntax**: `luminance | alpha`\n\n**Initial value**: `luminance`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **24** | **35**  | **7**  | **79** | No  |")
    String maskType() default "	";

    /**
     * The `math-style` property indicates whether MathML equations should render with normal or compact height.\n\n**Syntax**: `normal | compact`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |  n/a   |   n/a   | **14.1** |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.MathStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"compact"},{"type":"string","const":"normal"}]
     *
     * @see Property_MathStyle
     */
    @Const({"compact", "normal"})
    @Schema(title = "The `math-style` property indicates whether MathML equations should render with normal or compact height.\n\n**Syntax**: `normal | compact`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |  n/a   |   n/a   | **14.1** |  No  | No  |")
    String mathStyle() default "	";

    /**
     * The `**max-block-size**` CSS property specifies the maximum size of an element in the direction opposite that of the writing direction as specified by `writing-mode`. That is, if the writing direction is horizontal, then `max-block-size` is equivalent to `max-height`; if the writing direction is vertical, `max-block-size` is the same as `max-width`.\n\n**Syntax**: `<'max-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MaxBlockSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_MaxBlockSize
     */
    @Const({"-moz-max-content", "-moz-min-content", "-webkit-fill-available", "fit-content", "max-content", "min-content", "none"})
    @Schema(title = "The `**max-block-size**` CSS property specifies the maximum size of an element in the direction opposite that of the writing direction as specified by `writing-mode`. That is, if the writing direction is horizontal, then `max-block-size` is equivalent to `max-height`; if the writing direction is vertical, `max-block-size` is the same as `max-width`.\n\n**Syntax**: `<'max-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |")
    String maxBlockSize() default "	";

    /**
     * The **`max-height`** CSS property sets the maximum height of an element. It prevents the used value of the `height` property from becoming larger than the value specified for `max-height`.\n\n**Syntax**: `none | <length-percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **18** |  **1**  | **1.3** | **12** | **7** |
     *
     * 参考定义: "#/definitions/Property.MaxHeight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"-webkit-min-content"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_MaxHeight
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fit-content", "-webkit-max-content", "-webkit-min-content", "fit-content", "intrinsic", "max-content", "min-content", "none"})
    @Schema(title = "The **`max-height`** CSS property sets the maximum height of an element. It prevents the used value of the `height` property from becoming larger than the value specified for `max-height`.\n\n**Syntax**: `none | <length-percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **18** |  **1**  | **1.3** | **12** | **7** |")
    String maxHeight() default "	";

    /**
     * The **`max-inline-size`** CSS property defines the horizontal or vertical maximum size of an element's block, depending on its writing mode. It corresponds to either the `max-width` or the `max-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'max-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |   Safari   |  Edge  | IE  | | :----: | :-----: | :--------: | :----: | :-: | | **57** | **41**  |  **12.1**  | **79** | No  | |        |         | 10.1 _-x-_ |        |     |
     *
     * 参考定义: "#/definitions/Property.MaxInlineSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_MaxInlineSize
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "fit-content", "max-content", "min-content", "none"})
    @Schema(title = "The **`max-inline-size`** CSS property defines the horizontal or vertical maximum size of an element's block, depending on its writing mode. It corresponds to either the `max-width` or the `max-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'max-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |   Safari   |  Edge  | IE  | | :----: | :-----: | :--------: | :----: | :-: | | **57** | **41**  |  **12.1**  | **79** | No  | |        |         | 10.1 _-x-_ |        |     |")
    String maxInlineSize() default "	";

    /**
     * **Syntax**: `none | <integer>`\n\n**Initial value**: `none`
     *
     * 参考定义: "#/definitions/Property.MaxLines"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_MaxLines
     */
    @Const({"none"})
    @Schema(title = "**Syntax**: `none | <integer>`\n\n**Initial value**: `none`")
    String maxLines() default "	";

    /**
     * The **`max-width`** CSS property sets the maximum width of an element. It prevents the used value of the `width` property from becoming larger than the value specified by `max-width`.\n\n**Syntax**: `none | <length-percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **7** |
     *
     * 参考定义: "#/definitions/Property.MaxWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"-webkit-min-content"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"none"}]
     *
     * @see Property_MaxWidth
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fit-content", "-webkit-max-content", "-webkit-min-content", "fit-content", "intrinsic", "max-content", "min-content", "none"})
    @Schema(title = "The **`max-width`** CSS property sets the maximum width of an element. It prevents the used value of the `width` property from becoming larger than the value specified by `max-width`.\n\n**Syntax**: `none | <length-percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **7** |")
    String maxWidth() default "	";

    /**
     * The **`min-block-size`** CSS property defines the minimum horizontal or vertical size of an element's block, depending on its writing mode. It corresponds to either the `min-width` or the `min-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'min-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MinBlockSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_MinBlockSize
     */
    @Const({"-moz-max-content", "-moz-min-content", "-webkit-fill-available", "auto", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`min-block-size`** CSS property defines the minimum horizontal or vertical size of an element's block, depending on its writing mode. It corresponds to either the `min-width` or the `min-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'min-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |")
    String minBlockSize() default "	";

    /**
     * The **`min-height`** CSS property sets the minimum height of an element. It prevents the used value of the `height` property from becoming smaller than the value specified for `min-height`.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **3**  | **1.3** | **12** | **7** |
     *
     * 参考定义: "#/definitions/Property.MinHeight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"-webkit-min-content"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_MinHeight
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fit-content", "-webkit-max-content", "-webkit-min-content", "auto", "fit-content", "intrinsic", "max-content", "min-content"})
    @Schema(title = "The **`min-height`** CSS property sets the minimum height of an element. It prevents the used value of the `height` property from becoming smaller than the value specified for `min-height`.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **3**  | **1.3** | **12** | **7** |")
    String minHeight() default "	";

    /**
     * The **`min-inline-size`** CSS property defines the horizontal or vertical minimal size of an element's block, depending on its writing mode. It corresponds to either the `min-width` or the `min-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'min-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MinInlineSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"}]
     *
     * @see Property_MinInlineSize
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "auto", "fit-content", "max-content", "min-content"})
    @Schema(title = "The **`min-inline-size`** CSS property defines the horizontal or vertical minimal size of an element's block, depending on its writing mode. It corresponds to either the `min-width` or the `min-height` property, depending on the value of `writing-mode`.\n\n**Syntax**: `<'min-width'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **41**  | **12.1** | **79** | No  |")
    String minInlineSize() default "	";

    /**
     * The **`min-width`** CSS property sets the minimum width of an element. It prevents the used value of the `width` property from becoming smaller than the value specified for `min-width`.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **7** |
     *
     * 参考定义: "#/definitions/Property.MinWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fill-available"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"-webkit-min-content"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"min-intrinsic"}]
     *
     * @see Property_MinWidth
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fill-available", "-webkit-fit-content", "-webkit-max-content", "-webkit-min-content", "auto", "fit-content", "intrinsic", "max-content", "min-content", "min-intrinsic"})
    @Schema(title = "The **`min-width`** CSS property sets the minimum width of an element. It prevents the used value of the `width` property from becoming smaller than the value specified for `min-width`.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **7** |")
    String minWidth() default "	";

    /**
     * The **`mix-blend-mode`** CSS property sets how an element's content should blend with the content of the element's parent and the element's background.\n\n**Syntax**: `<blend-mode>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **41** | **32**  | **8**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.MixBlendMode"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.BlendMode"}]
     *
     * @see Property_MixBlendMode
     */

    @Schema(title = "The **`mix-blend-mode`** CSS property sets how an element's content should blend with the content of the element's parent and the element's background.\n\n**Syntax**: `<blend-mode>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **41** | **32**  | **8**  | **79** | No  |")
    String mixBlendMode() default "	";

    /**
     * The **`offset-distance`** CSS property specifies a position along an `offset-path` for an element to be placed.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **55**         | **72**  |   No   | **79** | No  | | 46 _(motion-distance)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetDistance%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_OffsetDistance
     */

    @Schema(title = "The **`offset-distance`** CSS property specifies a position along an `offset-path` for an element to be placed.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **55**         | **72**  |   No   | **79** | No  | | 46 _(motion-distance)_ |         |        |        |     |")
    String motionDistance() default "	";

    /**
     * The **`offset-path`** CSS property specifies a motion path for an element to follow and defines the element's positioning within the parent container or SVG coordinate system.\n\n**Syntax**: `none | ray( [ <angle> && <size> && contain? ] ) | <path()> | <url> | [ <basic-shape> || <geometry-box> ]`\n\n**Initial value**: `none`\n\n|       Chrome       | Firefox | Safari |  Edge  | IE  | | :----------------: | :-----: | :----: | :----: | :-: | |       **55**       | **72**  |   No   | **79** | No  | | 46 _(motion-path)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetPath"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_OffsetPath
     */
    @Const({"none"})
    @Schema(title = "The **`offset-path`** CSS property specifies a motion path for an element to follow and defines the element's positioning within the parent container or SVG coordinate system.\n\n**Syntax**: `none | ray( [ <angle> && <size> && contain? ] ) | <path()> | <url> | [ <basic-shape> || <geometry-box> ]`\n\n**Initial value**: `none`\n\n|       Chrome       | Firefox | Safari |  Edge  | IE  | | :----------------: | :-----: | :----: | :----: | :-: | |       **55**       | **72**  |   No   | **79** | No  | | 46 _(motion-path)_ |         |        |        |     |")
    String motionPath() default "	";

    /**
     * The **`offset-rotate`** CSS property defines the orientation/direction of the element as it is positioned along the `offset-path`.\n\n**Syntax**: `[ auto | reverse ] || <angle>`\n\n**Initial value**: `auto`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **56**         | **72**  |   No   | **79** | No  | | 46 _(motion-rotation)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetRotate"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"reverse"},{"type":"string"}]
     *
     * @see Property_OffsetRotate
     */
    @Const({"auto", "reverse"})
    @Schema(title = "The **`offset-rotate`** CSS property defines the orientation/direction of the element as it is positioned along the `offset-path`.\n\n**Syntax**: `[ auto | reverse ] || <angle>`\n\n**Initial value**: `auto`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **56**         | **72**  |   No   | **79** | No  | | 46 _(motion-rotation)_ |         |        |        |     |")
    String motionRotation() default "	";

    /**
     * The **`object-fit`** CSS property sets how the content of a replaced element, such as an `<img>` or `<video>`, should be resized to fit its container.\n\n**Syntax**: `fill | contain | cover | none | scale-down`\n\n**Initial value**: `fill`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **32** | **36**  | **10** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ObjectFit"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"contain"},{"type":"string","const":"cover"},{"type":"string","const":"fill"},{"type":"string","const":"none"},{"type":"string","const":"scale-down"}]
     *
     * @see Property_ObjectFit
     */
    @Const({"contain", "cover", "fill", "none", "scale-down"})
    @Schema(title = "The **`object-fit`** CSS property sets how the content of a replaced element, such as an `<img>` or `<video>`, should be resized to fit its container.\n\n**Syntax**: `fill | contain | cover | none | scale-down`\n\n**Initial value**: `fill`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **32** | **36**  | **10** | **79** | No  |")
    String objectFit() default "	";

    /**
     * The **`object-position`** CSS property specifies the alignment of the selected replaced element's contents within the element's box. Areas of the box which aren't covered by the replaced element's object will show the element's background.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **32** | **36**  | **10** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ObjectPosition%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_ObjectPosition
     */

    @Schema(title = "The **`object-position`** CSS property specifies the alignment of the selected replaced element's contents within the element's box. Areas of the box which aren't covered by the replaced element's object will show the element's background.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **32** | **36**  | **10** | **79** | No  |")
    String objectPosition() default "	";

    /**
     * **Syntax**: `auto | <position>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **79** | **72**  |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.OffsetAnchor%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"},{"type":"string","const":"auto"}]
     *
     * @see Property_OffsetAnchor
     */
    @Const({"auto"})
    @Schema(title = "**Syntax**: `auto | <position>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **79** | **72**  |   No   | **79** | No  |")
    String offsetAnchor() default "	";

    /**
     * The **`offset-distance`** CSS property specifies a position along an `offset-path` for an element to be placed.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **55**         | **72**  |   No   | **79** | No  | | 46 _(motion-distance)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetDistance%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_OffsetDistance
     */

    @Schema(title = "The **`offset-distance`** CSS property specifies a position along an `offset-path` for an element to be placed.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **55**         | **72**  |   No   | **79** | No  | | 46 _(motion-distance)_ |         |        |        |     |")
    String offsetDistance() default "	";

    /**
     * The **`offset-path`** CSS property specifies a motion path for an element to follow and defines the element's positioning within the parent container or SVG coordinate system.\n\n**Syntax**: `none | ray( [ <angle> && <size> && contain? ] ) | <path()> | <url> | [ <basic-shape> || <geometry-box> ]`\n\n**Initial value**: `none`\n\n|       Chrome       | Firefox | Safari |  Edge  | IE  | | :----------------: | :-----: | :----: | :----: | :-: | |       **55**       | **72**  |   No   | **79** | No  | | 46 _(motion-path)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetPath"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.GeometryBox"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_OffsetPath
     */
    @Const({"none"})
    @Schema(title = "The **`offset-path`** CSS property specifies a motion path for an element to follow and defines the element's positioning within the parent container or SVG coordinate system.\n\n**Syntax**: `none | ray( [ <angle> && <size> && contain? ] ) | <path()> | <url> | [ <basic-shape> || <geometry-box> ]`\n\n**Initial value**: `none`\n\n|       Chrome       | Firefox | Safari |  Edge  | IE  | | :----------------: | :-----: | :----: | :----: | :-: | |       **55**       | **72**  |   No   | **79** | No  | | 46 _(motion-path)_ |         |        |        |     |")
    String offsetPath() default "	";

    /**
     * The **`offset-rotate`** CSS property defines the orientation/direction of the element as it is positioned along the `offset-path`.\n\n**Syntax**: `[ auto | reverse ] || <angle>`\n\n**Initial value**: `auto`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **56**         | **72**  |   No   | **79** | No  | | 46 _(motion-rotation)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetRotate"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"reverse"},{"type":"string"}]
     *
     * @see Property_OffsetRotate
     */
    @Const({"auto", "reverse"})
    @Schema(title = "The **`offset-rotate`** CSS property defines the orientation/direction of the element as it is positioned along the `offset-path`.\n\n**Syntax**: `[ auto | reverse ] || <angle>`\n\n**Initial value**: `auto`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **56**         | **72**  |   No   | **79** | No  | | 46 _(motion-rotation)_ |         |        |        |     |")
    String offsetRotate() default "	";

    /**
     * The **`offset-rotate`** CSS property defines the orientation/direction of the element as it is positioned along the `offset-path`.\n\n**Syntax**: `[ auto | reverse ] || <angle>`\n\n**Initial value**: `auto`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **56**         | **72**  |   No   | **79** | No  | | 46 _(motion-rotation)_ |         |        |        |     |
     *
     * 参考定义: "#/definitions/Property.OffsetRotate"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"reverse"},{"type":"string"}]
     *
     * @see Property_OffsetRotate
     */
    @Const({"auto", "reverse"})
    @Schema(title = "The **`offset-rotate`** CSS property defines the orientation/direction of the element as it is positioned along the `offset-path`.\n\n**Syntax**: `[ auto | reverse ] || <angle>`\n\n**Initial value**: `auto`\n\n|         Chrome         | Firefox | Safari |  Edge  | IE  | | :--------------------: | :-----: | :----: | :----: | :-: | |         **56**         | **72**  |   No   | **79** | No  | | 46 _(motion-rotation)_ |         |        |        |     |")
    String offsetRotation() default "	";

    /**
     * The **`order`** CSS property sets the order to lay out an item in a flex or grid container. Items in a container are sorted by ascending `order` value and then by their source code order.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `0`\n\n|  Chrome  | Firefox | Safari  |  Edge  |    IE    | | :------: | :-----: | :-----: | :----: | :------: | |  **29**  | **20**  |  **9**  | **12** |  **11**  | | 21 _-x-_ |         | 7 _-x-_ |        | 10 _-x-_ |
     *
     * 参考定义: "#/definitions/Property.Order"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_Order
     */

    @Schema(title = "The **`order`** CSS property sets the order to lay out an item in a flex or grid container. Items in a container are sorted by ascending `order` value and then by their source code order.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `0`\n\n|  Chrome  | Firefox | Safari  |  Edge  |    IE    | | :------: | :-----: | :-----: | :----: | :------: | |  **29**  | **20**  |  **9**  | **12** |  **11**  | | 21 _-x-_ |         | 7 _-x-_ |        | 10 _-x-_ |")
    String order() default "	";

    /**
     * The **`orphans`** CSS property sets the minimum number of lines in a block container that must be shown at the _bottom_ of a page, region, or column.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `2`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **25** |   No    | **1.3** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.Orphans"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_Orphans
     */

    @Schema(title = "The **`orphans`** CSS property sets the minimum number of lines in a block container that must be shown at the _bottom_ of a page, region, or column.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `2`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **25** |   No    | **1.3** | **12** | **8** |")
    String orphans() default "	";

    /**
     * The **`outline-color`** CSS property sets the color of an element's outline.\n\n**Syntax**: `<color> | invert`\n\n**Initial value**: `invert`, for browsers supporting it, `currentColor` for the other\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.OutlineColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"},{"type":"string","const":"invert"}]
     *
     * @see Property_OutlineColor
     */
    @Const({"invert"})
    @Schema(title = "The **`outline-color`** CSS property sets the color of an element's outline.\n\n**Syntax**: `<color> | invert`\n\n**Initial value**: `invert`, for browsers supporting it, `currentColor` for the other\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |")
    String outlineColor() default "	";

    /**
     * The **`outline-offset`** CSS property sets the amount of space between an outline and the edge or border of an element.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **1**  | **1.5** | **1.2** | **15** | No  |
     *
     * 参考定义: "#/definitions/Property.OutlineOffset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_OutlineOffset
     */

    @Schema(title = "The **`outline-offset`** CSS property sets the amount of space between an outline and the edge or border of an element.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **1**  | **1.5** | **1.2** | **15** | No  |")
    String outlineOffset() default "	";

    /**
     * The **`outline-style`** CSS property sets the style of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `auto | <'border-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.OutlineStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineStyle"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_OutlineStyle
     */
    @Const({"auto"})
    @Schema(title = "The **`outline-style`** CSS property sets the style of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `auto | <'border-style'>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |")
    String outlineStyle() default "	";

    /**
     * The CSS **`outline-width`** property sets the thickness of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.OutlineWidth%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.LineWidth%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_OutlineWidth
     */

    @Schema(title = "The CSS **`outline-width`** property sets the thickness of an element's outline. An outline is a line that is drawn around an element, outside the `border`.\n\n**Syntax**: `<line-width>`\n\n**Initial value**: `medium`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **1.5** | **1.2** | **12** | **8** |")
    String outlineWidth() default "	";

    /**
     * **Syntax**: `auto | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **56** | **66**  |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.OverflowAnchor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"}]
     *
     * @see Property_OverflowAnchor
     */
    @Const({"auto", "none"})
    @Schema(title = "**Syntax**: `auto | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **56** | **66**  |   No   | **79** | No  |")
    String overflowAnchor() default "	";

    /**
     * **Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **69**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.OverflowBlock"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
     *
     * @see Property_OverflowBlock
     */
    @Const({"auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **69**  |   No   |  No  | No  |")
    String overflowBlock() default "	";

    /**
     * The **`overflow-clip-box`** CSS property specifies relative to which box the clipping happens when there is an overflow. It is short hand for the `overflow-clip-box-inline` and `overflow-clip-box-block` properties.\n\n**Syntax**: `padding-box | content-box`\n\n**Initial value**: `padding-box`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **29**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.OverflowClipBox"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"content-box"},{"type":"string","const":"padding-box"}]
     *
     * @see Property_OverflowClipBox
     */
    @Const({"content-box", "padding-box"})
    @Schema(title = "The **`overflow-clip-box`** CSS property specifies relative to which box the clipping happens when there is an overflow. It is short hand for the `overflow-clip-box-inline` and `overflow-clip-box-block` properties.\n\n**Syntax**: `padding-box | content-box`\n\n**Initial value**: `padding-box`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **29**  |   No   |  No  | No  |")
    String overflowClipBox() default "	";

    /**
     * **Syntax**: `<visual-box> || <length [0,∞]>`\n\n**Initial value**: `0px`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **90** |   No    |   No   | **90** | No  |
     *
     * 参考定义: "#/definitions/Property.OverflowClipMargin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.VisualBox"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_OverflowClipMargin
     */

    @Schema(title = "**Syntax**: `<visual-box> || <length [0,∞]>`\n\n**Initial value**: `0px`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **90** |   No    |   No   | **90** | No  |")
    String overflowClipMargin() default "	";

    /**
     * **Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **69**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.OverflowInline"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
     *
     * @see Property_OverflowInline
     */
    @Const({"auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **69**  |   No   |  No  | No  |")
    String overflowInline() default "	";

    /**
     * The `**overflow-wrap**` CSS property applies to inline elements, setting whether the browser should insert line breaks within an otherwise unbreakable string to prevent text from overflowing its line box.\n\n**Syntax**: `normal | break-word | anywhere`\n\n**Initial value**: `normal`\n\n|     Chrome      |      Firefox      |     Safari      |       Edge       |          IE           | | :-------------: | :---------------: | :-------------: | :--------------: | :-------------------: | |     **23**      |      **49**       |      **7**      |      **18**      | **5.5** _(word-wrap)_ | | 1 _(word-wrap)_ | 3.5 _(word-wrap)_ | 1 _(word-wrap)_ | 12 _(word-wrap)_ |                       |
     *
     * 参考定义: "#/definitions/Property.OverflowWrap"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"anywhere"},{"type":"string","const":"break-word"},{"type":"string","const":"normal"}]
     *
     * @see Property_OverflowWrap
     */
    @Const({"anywhere", "break-word", "normal"})
    @Schema(title = "The `**overflow-wrap**` CSS property applies to inline elements, setting whether the browser should insert line breaks within an otherwise unbreakable string to prevent text from overflowing its line box.\n\n**Syntax**: `normal | break-word | anywhere`\n\n**Initial value**: `normal`\n\n|     Chrome      |      Firefox      |     Safari      |       Edge       |          IE           | | :-------------: | :---------------: | :-------------: | :--------------: | :-------------------: | |     **23**      |      **49**       |      **7**      |      **18**      | **5.5** _(word-wrap)_ | | 1 _(word-wrap)_ | 3.5 _(word-wrap)_ | 1 _(word-wrap)_ | 12 _(word-wrap)_ |                       |")
    String overflowWrap() default "	";

    /**
     * The **`overflow-x`** CSS property sets what shows when content overflows a block-level element's left and right edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **3.5** | **3**  | **12** | **5** |
     *
     * 参考定义: "#/definitions/Property.OverflowX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-hidden-unscrollable"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
     *
     * @see Property_OverflowX
     */
    @Const({"-moz-hidden-unscrollable", "auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "The **`overflow-x`** CSS property sets what shows when content overflows a block-level element's left and right edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **3.5** | **3**  | **12** | **5** |")
    String overflowX() default "	";

    /**
     * The **`overflow-y`** CSS property sets what shows when content overflows a block-level element's top and bottom edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **3.5** | **3**  | **12** | **5** |
     *
     * 参考定义: "#/definitions/Property.OverflowY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-hidden-unscrollable"},{"type":"string","const":"auto"},{"type":"string","const":"clip"},{"type":"string","const":"hidden"},{"type":"string","const":"scroll"},{"type":"string","const":"visible"}]
     *
     * @see Property_OverflowY
     */
    @Const({"-moz-hidden-unscrollable", "auto", "clip", "hidden", "scroll", "visible"})
    @Schema(title = "The **`overflow-y`** CSS property sets what shows when content overflows a block-level element's top and bottom edges. This may be nothing, a scroll bar, or the overflow content.\n\n**Syntax**: `visible | hidden | clip | scroll | auto`\n\n**Initial value**: `visible`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  | **3.5** | **3**  | **12** | **5** |")
    String overflowY() default "	";

    /**
     * The **`overscroll-behavior-block`** CSS property sets the browser's behavior when the block direction boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **77** | **73**  |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.OverscrollBehaviorBlock"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"none"}]
     *
     * @see Property_OverscrollBehaviorBlock
     */
    @Const({"auto", "contain", "none"})
    @Schema(title = "The **`overscroll-behavior-block`** CSS property sets the browser's behavior when the block direction boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **77** | **73**  |   No   | **79** | No  |")
    String overscrollBehaviorBlock() default "	";

    /**
     * The **`overscroll-behavior-inline`** CSS property sets the browser's behavior when the inline direction boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **77** | **73**  |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.OverscrollBehaviorInline"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"none"}]
     *
     * @see Property_OverscrollBehaviorInline
     */
    @Const({"auto", "contain", "none"})
    @Schema(title = "The **`overscroll-behavior-inline`** CSS property sets the browser's behavior when the inline direction boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **77** | **73**  |   No   | **79** | No  |")
    String overscrollBehaviorInline() default "	";

    /**
     * The **`overscroll-behavior-x`** CSS property sets the browser's behavior when the horizontal boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **63** | **59**  |   No   | **18** | No  |
     *
     * 参考定义: "#/definitions/Property.OverscrollBehaviorX"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"none"}]
     *
     * @see Property_OverscrollBehaviorX
     */
    @Const({"auto", "contain", "none"})
    @Schema(title = "The **`overscroll-behavior-x`** CSS property sets the browser's behavior when the horizontal boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **63** | **59**  |   No   | **18** | No  |")
    String overscrollBehaviorX() default "	";

    /**
     * The **`overscroll-behavior-y`** CSS property sets the browser's behavior when the vertical boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **63** | **59**  |   No   | **18** | No  |
     *
     * 参考定义: "#/definitions/Property.OverscrollBehaviorY"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"none"}]
     *
     * @see Property_OverscrollBehaviorY
     */
    @Const({"auto", "contain", "none"})
    @Schema(title = "The **`overscroll-behavior-y`** CSS property sets the browser's behavior when the vertical boundary of a scrolling area is reached.\n\n**Syntax**: `contain | none | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **63** | **59**  |   No   | **18** | No  |")
    String overscrollBehaviorY() default "	";

    /**
     * The **`padding-block`** CSS shorthand property defines the logical block start and end padding of an element, which maps to physical padding properties depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.PaddingBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingBlock
     */

    @Schema(title = "The **`padding-block`** CSS shorthand property defines the logical block start and end padding of an element, which maps to physical padding properties depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String paddingBlock() default "	";

    /**
     * The **`padding-block-end`** CSS property defines the logical block end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.PaddingBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingBlockEnd
     */

    @Schema(title = "The **`padding-block-end`** CSS property defines the logical block end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String paddingBlockEnd() default "	";

    /**
     * The **`padding-block-start`** CSS property defines the logical block start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.PaddingBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingBlockStart
     */

    @Schema(title = "The **`padding-block-start`** CSS property defines the logical block start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **41**  | **12.1** | **79** | No  |")
    String paddingBlockStart() default "	";

    /**
     * The **`padding-bottom`** CSS property sets the height of the padding area on the bottom of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.PaddingBottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingBottom
     */

    @Schema(title = "The **`padding-bottom`** CSS property sets the height of the padding area on the bottom of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String paddingBottom() default "	";

    /**
     * The **`padding-inline`** CSS shorthand property defines the logical inline start and end padding of an element, which maps to physical padding properties depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.PaddingInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInline
     */

    @Schema(title = "The **`padding-inline`** CSS shorthand property defines the logical inline start and end padding of an element, which maps to physical padding properties depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **66**  | **14.1** | **87** | No  |")
    String paddingInline() default "	";

    /**
     * The **`padding-inline-end`** CSS property defines the logical inline end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n|          Chrome           |        Firefox         |          Safari           |  Edge  | IE  | | :-----------------------: | :--------------------: | :-----------------------: | :----: | :-: | |          **69**           |         **41**         |         **12.1**          | **79** | No  | | 2 _(-webkit-padding-end)_ | 3 _(-moz-padding-end)_ | 3 _(-webkit-padding-end)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.PaddingInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInlineEnd
     */

    @Schema(title = "The **`padding-inline-end`** CSS property defines the logical inline end padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n|          Chrome           |        Firefox         |          Safari           |  Edge  | IE  | | :-----------------------: | :--------------------: | :-----------------------: | :----: | :-: | |          **69**           |         **41**         |         **12.1**          | **79** | No  | | 2 _(-webkit-padding-end)_ | 3 _(-moz-padding-end)_ | 3 _(-webkit-padding-end)_ |        |     |")
    String paddingInlineEnd() default "	";

    /**
     * The **`padding-inline-start`** CSS property defines the logical inline start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n|           Chrome            |         Firefox          |           Safari            |  Edge  | IE  | | :-------------------------: | :----------------------: | :-------------------------: | :----: | :-: | |           **69**            |          **41**          |          **12.1**           | **79** | No  | | 2 _(-webkit-padding-start)_ | 3 _(-moz-padding-start)_ | 3 _(-webkit-padding-start)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.PaddingInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingInlineStart
     */

    @Schema(title = "The **`padding-inline-start`** CSS property defines the logical inline start padding of an element, which maps to a physical padding depending on the element's writing mode, directionality, and text orientation.\n\n**Syntax**: `<'padding-left'>`\n\n**Initial value**: `0`\n\n|           Chrome            |         Firefox          |           Safari            |  Edge  | IE  | | :-------------------------: | :----------------------: | :-------------------------: | :----: | :-: | |           **69**            |          **41**          |          **12.1**           | **79** | No  | | 2 _(-webkit-padding-start)_ | 3 _(-moz-padding-start)_ | 3 _(-webkit-padding-start)_ |        |     |")
    String paddingInlineStart() default "	";

    /**
     * The **`padding-left`** CSS property sets the width of the padding area to the left of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.PaddingLeft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingLeft
     */

    @Schema(title = "The **`padding-left`** CSS property sets the width of the padding area to the left of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String paddingLeft() default "	";

    /**
     * The **`padding-right`** CSS property sets the width of the padding area on the right of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.PaddingRight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingRight
     */

    @Schema(title = "The **`padding-right`** CSS property sets the width of the padding area on the right of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String paddingRight() default "	";

    /**
     * The **`padding-top`** CSS property sets the height of the padding area on the top of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.PaddingTop%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_PaddingTop
     */

    @Schema(title = "The **`padding-top`** CSS property sets the height of the padding area on the top of an element.\n\n**Syntax**: `<length> | <percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String paddingTop() default "	";

    /**
     * The **`page-break-after`** CSS property adjusts page breaks _after_ the current element.\n\n**Syntax**: `auto | always | avoid | left | right | recto | verso`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.PageBreakAfter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"left"},{"type":"string","const":"recto"},{"type":"string","const":"right"},{"type":"string","const":"verso"}]
     *
     * @see Property_PageBreakAfter
     */
    @Const({"always", "auto", "avoid", "left", "recto", "right", "verso"})
    @Schema(title = "The **`page-break-after`** CSS property adjusts page breaks _after_ the current element.\n\n**Syntax**: `auto | always | avoid | left | right | recto | verso`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **4** |")
    String pageBreakAfter() default "	";

    /**
     * The **`page-break-before`** CSS property adjusts page breaks _before_ the current element.\n\n**Syntax**: `auto | always | avoid | left | right | recto | verso`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.PageBreakBefore"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"always"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"},{"type":"string","const":"left"},{"type":"string","const":"recto"},{"type":"string","const":"right"},{"type":"string","const":"verso"}]
     *
     * @see Property_PageBreakBefore
     */
    @Const({"always", "auto", "avoid", "left", "recto", "right", "verso"})
    @Schema(title = "The **`page-break-before`** CSS property adjusts page breaks _before_ the current element.\n\n**Syntax**: `auto | always | avoid | left | right | recto | verso`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **1**  | **1.2** | **12** | **4** |")
    String pageBreakBefore() default "	";

    /**
     * The **`page-break-inside`** CSS property adjusts page breaks _inside_ the current element.\n\n**Syntax**: `auto | avoid`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **19**  | **1.3** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.PageBreakInside"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"avoid"}]
     *
     * @see Property_PageBreakInside
     */
    @Const({"auto", "avoid"})
    @Schema(title = "The **`page-break-inside`** CSS property adjusts page breaks _inside_ the current element.\n\n**Syntax**: `auto | avoid`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  | **19**  | **1.3** | **12** | **8** |")
    String pageBreakInside() default "	";

    /**
     * The **`perspective`** CSS property determines the distance between the z=0 plane and the user in order to give a 3D-positioned element some perspective.\n\n**Syntax**: `none | <length>`\n\n**Initial value**: `none`\n\n|  Chrome  | Firefox  | Safari  |  Edge  |   IE   | | :------: | :------: | :-----: | :----: | :----: | |  **36**  |  **16**  |  **9**  | **12** | **10** | | 12 _-x-_ | 10 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.Perspective%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"none"}]
     *
     * @see Property_Perspective
     */
    @Const({"none"})
    @Schema(title = "The **`perspective`** CSS property determines the distance between the z=0 plane and the user in order to give a 3D-positioned element some perspective.\n\n**Syntax**: `none | <length>`\n\n**Initial value**: `none`\n\n|  Chrome  | Firefox  | Safari  |  Edge  |   IE   | | :------: | :------: | :-----: | :----: | :----: | |  **36**  |  **16**  |  **9**  | **12** | **10** | | 12 _-x-_ | 10 _-x-_ | 4 _-x-_ |        |        |")
    String perspective() default "	";

    /**
     * The **`perspective-origin`** CSS property determines the position at which the viewer is looking. It is used as the _vanishing point_ by the `perspective` property.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`\n\n|  Chrome  | Firefox  | Safari  |  Edge  |   IE   | | :------: | :------: | :-----: | :----: | :----: | |  **36**  |  **16**  |  **9**  | **12** | **10** | | 12 _-x-_ | 10 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.PerspectiveOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Position%3C(string%7Cnumber)%3E"}]
     *
     * @see Property_PerspectiveOrigin
     */

    @Schema(title = "The **`perspective-origin`** CSS property determines the position at which the viewer is looking. It is used as the _vanishing point_ by the `perspective` property.\n\n**Syntax**: `<position>`\n\n**Initial value**: `50% 50%`\n\n|  Chrome  | Firefox  | Safari  |  Edge  |   IE   | | :------: | :------: | :-----: | :----: | :----: | |  **36**  |  **16**  |  **9**  | **12** | **10** | | 12 _-x-_ | 10 _-x-_ | 4 _-x-_ |        |        |")
    String perspectiveOrigin() default "	";

    /**
     * The `**place-content**` CSS shorthand property allows you to align content along both the block and inline directions at once (i.e. the `align-content` and `justify-content` properties) in a relevant layout system such as Grid or Flexbox.\n\n**Syntax**: `<'align-content'> <'justify-content'>?`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **59** | **45**  | **9**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.PlaceContent"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.ContentDistribution"},{"$ref":"#/definitions/DataType.ContentPosition"},{"type":"string","const":"baseline"},{"type":"string","const":"normal"},{"type":"string"}]
     *
     * @see Property_PlaceContent
     */
    @Const({"baseline", "normal"})
    @Schema(title = "The `**place-content**` CSS shorthand property allows you to align content along both the block and inline directions at once (i.e. the `align-content` and `justify-content` properties) in a relevant layout system such as Grid or Flexbox.\n\n**Syntax**: `<'align-content'> <'justify-content'>?`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **59** | **45**  | **9**  | **79** | No  |")
    String placeContent() default "	";

    /**
     * The **`position`** CSS property sets how an element is positioned in a document. The `top`, `right`, `bottom`, and `left` properties determine the final location of positioned elements.\n\n**Syntax**: `static | relative | absolute | sticky | fixed`\n\n**Initial value**: `static`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Position"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-webkit-sticky"},{"type":"string","const":"absolute"},{"type":"string","const":"fixed"},{"type":"string","const":"relative"},{"type":"string","const":"static"},{"type":"string","const":"sticky"}]
     *
     * @see Property_Position
     */
    @Const({"-webkit-sticky", "absolute", "fixed", "relative", "static", "sticky"})
    @Schema(title = "The **`position`** CSS property sets how an element is positioned in a document. The `top`, `right`, `bottom`, and `left` properties determine the final location of positioned elements.\n\n**Syntax**: `static | relative | absolute | sticky | fixed`\n\n**Initial value**: `static`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String position() default "	";

    /**
     * The **`quotes`** CSS property sets how the browser should render quotation marks that are added using the `open-quotes` or `close-quotes` values of the CSS `content` property.\n\n**Syntax**: `none | auto | [ <string> <string> ]+`\n\n**Initial value**: depends on user agent\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **11** | **1.5** | **9**  | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.Quotes"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Quotes
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`quotes`** CSS property sets how the browser should render quotation marks that are added using the `open-quotes` or `close-quotes` values of the CSS `content` property.\n\n**Syntax**: `none | auto | [ <string> <string> ]+`\n\n**Initial value**: depends on user agent\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **11** | **1.5** | **9**  | **12** | **8** |")
    String quotes() default "	";

    /**
     * The **`resize`** CSS property sets whether an element is resizable, and if so, in which directions.\n\n**Syntax**: `none | both | horizontal | vertical | block | inline`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **1**  |  **4**  | **3**  | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.Resize"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"both"},{"type":"string","const":"horizontal"},{"type":"string","const":"inline"},{"type":"string","const":"none"},{"type":"string","const":"vertical"}]
     *
     * @see Property_Resize
     */
    @Const({"block", "both", "horizontal", "inline", "none", "vertical"})
    @Schema(title = "The **`resize`** CSS property sets whether an element is resizable, and if so, in which directions.\n\n**Syntax**: `none | both | horizontal | vertical | block | inline`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **1**  |  **4**  | **3**  | **79** | No  |")
    String resize() default "	";

    /**
     * The **`right`** CSS property participates in specifying the horizontal position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.Right%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Right
     */
    @Const({"auto"})
    @Schema(title = "The **`right`** CSS property participates in specifying the horizontal position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  |  **1**  | **1**  | **12** | **5.5** |")
    String right() default "	";

    /**
     * The **`rotate`** CSS property allows you to specify rotation transforms individually and independently of the `transform` property. This maps better to typical user interface usage, and saves having to remember the exact order of transform functions to specify in the `transform` property.\n\n**Syntax**: `none | <angle> | [ x | y | z | <number>{3} ] && <angle>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |   No   | **72**  | **14.1** |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.Rotate"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Rotate
     */
    @Const({"none"})
    @Schema(title = "The **`rotate`** CSS property allows you to specify rotation transforms individually and independently of the `transform` property. This maps better to typical user interface usage, and saves having to remember the exact order of transform functions to specify in the `transform` property.\n\n**Syntax**: `none | <angle> | [ x | y | z | <number>{3} ] && <angle>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |   No   | **72**  | **14.1** |  No  | No  |")
    String rotate() default "	";

    /**
     * The **`row-gap`** CSS property sets the size of the gap (gutter) between an element's grid rows.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **84** | **63**  | **14.1** | **84** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n|       Chrome        |       Firefox       |        Safari         |  Edge  | IE  | | :-----------------: | :-----------------: | :-------------------: | :----: | :-: | |       **66**        |       **61**        |        **12**         | **16** | No  | | 57 _(grid-row-gap)_ | 52 _(grid-row-gap)_ | 10.1 _(grid-row-gap)_ |        |     |\n\n---
     *
     * 参考定义: "#/definitions/Property.RowGap%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"normal"}]
     *
     * @see Property_RowGap
     */
    @Const({"normal"})
    @Schema(title = "The **`row-gap`** CSS property sets the size of the gap (gutter) between an element's grid rows.\n\n**Syntax**: `normal | <length-percentage>`\n\n**Initial value**: `normal`\n\n---\n\n_Supported in Flex Layout_\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **84** | **63**  | **14.1** | **84** | No  |\n\n---\n\n_Supported in Grid Layout_\n\n|       Chrome        |       Firefox       |        Safari         |  Edge  | IE  | | :-----------------: | :-----------------: | :-------------------: | :----: | :-: | |       **66**        |       **61**        |        **12**         | **16** | No  | | 57 _(grid-row-gap)_ | 52 _(grid-row-gap)_ | 10.1 _(grid-row-gap)_ |        |     |\n\n---")
    String rowGap() default "	";

    /**
     * The `**ruby-align**` CSS property defines the distribution of the different ruby elements over the base.\n\n**Syntax**: `start | center | space-between | space-around`\n\n**Initial value**: `space-around`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **38**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.RubyAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"space-around"},{"type":"string","const":"space-between"},{"type":"string","const":"start"}]
     *
     * @see Property_RubyAlign
     */
    @Const({"center", "space-around", "space-between", "start"})
    @Schema(title = "The `**ruby-align**` CSS property defines the distribution of the different ruby elements over the base.\n\n**Syntax**: `start | center | space-between | space-around`\n\n**Initial value**: `space-around`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **38**  |   No   |  No  | No  |")
    String rubyAlign() default "	";

    /**
     * **Syntax**: `separate | collapse | auto`\n\n**Initial value**: `separate`
     *
     * 参考定义: "#/definitions/Property.RubyMerge"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"collapse"},{"type":"string","const":"separate"}]
     *
     * @see Property_RubyMerge
     */
    @Const({"auto", "collapse", "separate"})
    @Schema(title = "**Syntax**: `separate | collapse | auto`\n\n**Initial value**: `separate`")
    String rubyMerge() default "	";

    /**
     * The `**ruby-position**` CSS property defines the position of a ruby element relatives to its base element. It can be position over the element (`over`), under it (`under`), or between the characters, on their right side (`inter-character`).\n\n**Syntax**: `[ alternate || [ over | under ] ] | inter-character`\n\n**Initial value**: `alternate`\n\n| Chrome  | Firefox |    Safari     | Edge  | IE  | | :-----: | :-----: | :-----------: | :---: | :-: | | **84**  | **38**  | **6.1** _-x-_ | 12-79 | No  | | 1 _-x-_ |         |               |       |     |
     *
     * 参考定义: "#/definitions/Property.RubyPosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"alternate"},{"type":"string","const":"inter-character"},{"type":"string","const":"over"},{"type":"string","const":"under"},{"type":"string"}]
     *
     * @see Property_RubyPosition
     */
    @Const({"alternate", "inter-character", "over", "under"})
    @Schema(title = "The `**ruby-position**` CSS property defines the position of a ruby element relatives to its base element. It can be position over the element (`over`), under it (`under`), or between the characters, on their right side (`inter-character`).\n\n**Syntax**: `[ alternate || [ over | under ] ] | inter-character`\n\n**Initial value**: `alternate`\n\n| Chrome  | Firefox |    Safari     | Edge  | IE  | | :-----: | :-----: | :-----------: | :---: | :-: | | **84**  | **38**  | **6.1** _-x-_ | 12-79 | No  | | 1 _-x-_ |         |               |       |     |")
    String rubyPosition() default "	";

    /**
     * The **`scale`** CSS property allows you to specify scale transforms individually and independently of the `transform` property. This maps better to typical user interface usage, and saves having to remember the exact order of transform functions to specify in the `transform` value.\n\n**Syntax**: `none | <number>{1,3}`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |   No   | **72**  | **14.1** |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.Scale"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_Scale
     */
    @Const({"none"})
    @Schema(title = "The **`scale`** CSS property allows you to specify scale transforms individually and independently of the `transform` property. This maps better to typical user interface usage, and saves having to remember the exact order of transform functions to specify in the `transform` value.\n\n**Syntax**: `none | <number>{1,3}`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |   No   | **72**  | **14.1** |  No  | No  |")
    String scale() default "	";

    /**
     * The **`scroll-behavior`** CSS property sets the behavior for a scrolling box when scrolling is triggered by the navigation or CSSOM scrolling APIs.\n\n**Syntax**: `auto | smooth`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **61** | **36**  |  n/a   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollBehavior"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"smooth"}]
     *
     * @see Property_ScrollBehavior
     */
    @Const({"auto", "smooth"})
    @Schema(title = "The **`scroll-behavior`** CSS property sets the behavior for a scrolling box when scrolling is triggered by the navigation or CSSOM scrolling APIs.\n\n**Syntax**: `auto | smooth`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **61** | **36**  |  n/a   | **79** | No  |")
    String scrollBehavior() default "	";

    /**
     * The **`scroll-margin`** shorthand property sets all of the scroll margins of an element at once, assigning values much like the `margin` property does for margins of an element.\n\n**Syntax**: `<length>{1,4}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |          Safari           |  Edge  | IE  | | :----: | :-----: | :-----------------------: | :----: | :-: | | **69** | **90**  |         **14.1**          | **79** | No  | |        |         | 11 _(scroll-snap-margin)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMargin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMargin
     */

    @Schema(title = "The **`scroll-margin`** shorthand property sets all of the scroll margins of an element at once, assigning values much like the `margin` property does for margins of an element.\n\n**Syntax**: `<length>{1,4}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |          Safari           |  Edge  | IE  | | :----: | :-----: | :-----------------------: | :----: | :-: | | **69** | **90**  |         **14.1**          | **79** | No  | |        |         | 11 _(scroll-snap-margin)_ |        |     |")
    String scrollMargin() default "	";

    /**
     * The `scroll-margin-block` shorthand property sets the scroll margins of an element in the block dimension.\n\n**Syntax**: `<length>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginBlock
     */

    @Schema(title = "The `scroll-margin-block` shorthand property sets the scroll margins of an element in the block dimension.\n\n**Syntax**: `<length>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollMarginBlock() default "	";

    /**
     * The `scroll-margin-block-end` property defines the margin of the scroll snap area at the end of the block dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginBlockEnd
     */

    @Schema(title = "The `scroll-margin-block-end` property defines the margin of the scroll snap area at the end of the block dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollMarginBlockEnd() default "	";

    /**
     * The `scroll-margin-block-start` property defines the margin of the scroll snap area at the start of the block dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginBlockStart
     */

    @Schema(title = "The `scroll-margin-block-start` property defines the margin of the scroll snap area at the start of the block dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollMarginBlockStart() default "	";

    /**
     * The `scroll-margin-bottom` property defines the bottom margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |              Safari              |  Edge  | IE  | | :----: | :-----: | :------------------------------: | :----: | :-: | | **69** | **68**  |             **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-bottom)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginBottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginBottom
     */

    @Schema(title = "The `scroll-margin-bottom` property defines the bottom margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |              Safari              |  Edge  | IE  | | :----: | :-----: | :------------------------------: | :----: | :-: | | **69** | **68**  |             **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-bottom)_ |        |     |")
    String scrollMarginBottom() default "	";

    /**
     * The `scroll-margin-inline` shorthand property sets the scroll margins of an element in the inline dimension.\n\n**Syntax**: `<length>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginInline
     */

    @Schema(title = "The `scroll-margin-inline` shorthand property sets the scroll margins of an element in the inline dimension.\n\n**Syntax**: `<length>{1,2}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollMarginInline() default "	";

    /**
     * The `scroll-margin-inline-end` property defines the margin of the scroll snap area at the end of the inline dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginInlineEnd
     */

    @Schema(title = "The `scroll-margin-inline-end` property defines the margin of the scroll snap area at the end of the inline dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollMarginInlineEnd() default "	";

    /**
     * The `scroll-margin-inline-start` property defines the margin of the scroll snap area at the start of the inline dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginInlineStart
     */

    @Schema(title = "The `scroll-margin-inline-start` property defines the margin of the scroll snap area at the start of the inline dimension that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollMarginInlineStart() default "	";

    /**
     * The `scroll-margin-left` property defines the left margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari             |  Edge  | IE  | | :----: | :-----: | :----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-left)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginLeft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginLeft
     */

    @Schema(title = "The `scroll-margin-left` property defines the left margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari             |  Edge  | IE  | | :----: | :-----: | :----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-left)_ |        |     |")
    String scrollMarginLeft() default "	";

    /**
     * The `scroll-margin-right` property defines the right margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari              |  Edge  | IE  | | :----: | :-----: | :-----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-right)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginRight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginRight
     */

    @Schema(title = "The `scroll-margin-right` property defines the right margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari              |  Edge  | IE  | | :----: | :-----: | :-----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-right)_ |        |     |")
    String scrollMarginRight() default "	";

    /**
     * The `scroll-margin-top` property defines the top margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |            Safari             |  Edge  | IE  | | :----: | :-----: | :---------------------------: | :----: | :-: | | **69** | **68**  |           **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-top)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginTop%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginTop
     */

    @Schema(title = "The `scroll-margin-top` property defines the top margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |            Safari             |  Edge  | IE  | | :----: | :-----: | :---------------------------: | :----: | :-: | | **69** | **68**  |           **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-top)_ |        |     |")
    String scrollMarginTop() default "	";

    /**
     * The **`scroll-padding`** shorthand property sets scroll padding on all sides of an element at once, much like the `padding` property does for padding on an element.\n\n**Syntax**: `[ auto | <length-percentage> ]{1,4}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPadding%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPadding
     */
    @Const({"auto"})
    @Schema(title = "The **`scroll-padding`** shorthand property sets scroll padding on all sides of an element at once, much like the `padding` property does for padding on an element.\n\n**Syntax**: `[ auto | <length-percentage> ]{1,4}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollPadding() default "	";

    /**
     * The `scroll-padding-block` shorthand property sets the scroll padding of an element in the block dimension.\n\n**Syntax**: `[ auto | <length-percentage> ]{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingBlock%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingBlock
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-block` shorthand property sets the scroll padding of an element in the block dimension.\n\n**Syntax**: `[ auto | <length-percentage> ]{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |")
    String scrollPaddingBlock() default "	";

    /**
     * The `scroll-padding-block-end` property defines offsets for the end edge in the block dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingBlockEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingBlockEnd
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-block-end` property defines offsets for the end edge in the block dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |")
    String scrollPaddingBlockEnd() default "	";

    /**
     * The `scroll-padding-block-start` property defines offsets for the start edge in the block dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingBlockStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingBlockStart
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-block-start` property defines offsets for the start edge in the block dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |")
    String scrollPaddingBlockStart() default "	";

    /**
     * The `scroll-padding-bottom` property defines offsets for the bottom of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingBottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingBottom
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-bottom` property defines offsets for the bottom of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollPaddingBottom() default "	";

    /**
     * The `scroll-padding-inline` shorthand property sets the scroll padding of an element in the inline dimension.\n\n**Syntax**: `[ auto | <length-percentage> ]{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingInline%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingInline
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-inline` shorthand property sets the scroll padding of an element in the inline dimension.\n\n**Syntax**: `[ auto | <length-percentage> ]{1,2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |")
    String scrollPaddingInline() default "	";

    /**
     * The `scroll-padding-inline-end` property defines offsets for the end edge in the inline dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingInlineEnd%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingInlineEnd
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-inline-end` property defines offsets for the end edge in the inline dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |")
    String scrollPaddingInlineEnd() default "	";

    /**
     * The `scroll-padding-inline-start` property defines offsets for the start edge in the inline dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingInlineStart%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingInlineStart
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-inline-start` property defines offsets for the start edge in the inline dimension of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **15** | **79** | No  |")
    String scrollPaddingInlineStart() default "	";

    /**
     * The `scroll-padding-left` property defines offsets for the left of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingLeft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingLeft
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-left` property defines offsets for the left of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollPaddingLeft() default "	";

    /**
     * The `scroll-padding-right` property defines offsets for the right of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingRight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingRight
     */
    @Const({"auto"})
    @Schema(title = "The `scroll-padding-right` property defines offsets for the right of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollPaddingRight() default "	";

    /**
     * The **`scroll-padding-top`** property defines offsets for the top of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollPaddingTop%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_ScrollPaddingTop
     */
    @Const({"auto"})
    @Schema(title = "The **`scroll-padding-top`** property defines offsets for the top of the optimal viewing region of the scrollport: the region used as the target region for placing things in view of the user. This allows the author to exclude regions of the scrollport that are obscured by other content (such as fixed-positioned toolbars or sidebars) or simply to put more breathing room between a targetted element and the edges of the scrollport.\n\n**Syntax**: `auto | <length-percentage>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **69** | **68**  | **14.1** | **79** | No  |")
    String scrollPaddingTop() default "	";

    /**
     * The `scroll-snap-align` property specifies the box’s snap position as an alignment of its snap area (as the alignment subject) within its snap container’s snapport (as the alignment container). The two values specify the snapping alignment in the block axis and inline axis, respectively. If only one value is specified, the second value defaults to the same value.\n\n**Syntax**: `[ none | start | end | center ]{1,2}`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **11** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollSnapAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"none"},{"type":"string","const":"start"},{"type":"string"}]
     *
     * @see Property_ScrollSnapAlign
     */
    @Const({"center", "end", "none", "start"})
    @Schema(title = "The `scroll-snap-align` property specifies the box’s snap position as an alignment of its snap area (as the alignment subject) within its snap container’s snapport (as the alignment container). The two values specify the snapping alignment in the block axis and inline axis, respectively. If only one value is specified, the second value defaults to the same value.\n\n**Syntax**: `[ none | start | end | center ]{1,2}`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **69** | **68**  | **11** | **79** | No  |")
    String scrollSnapAlign() default "	";

    /**
     * The **`scroll-margin`** shorthand property sets all of the scroll margins of an element at once, assigning values much like the `margin` property does for margins of an element.\n\n**Syntax**: `<length>{1,4}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |          Safari           |  Edge  | IE  | | :----: | :-----: | :-----------------------: | :----: | :-: | | **69** |  68-90  |         **14.1**          | **79** | No  | |        |         | 11 _(scroll-snap-margin)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMargin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMargin
     */

    @Schema(title = "The **`scroll-margin`** shorthand property sets all of the scroll margins of an element at once, assigning values much like the `margin` property does for margins of an element.\n\n**Syntax**: `<length>{1,4}`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |          Safari           |  Edge  | IE  | | :----: | :-----: | :-----------------------: | :----: | :-: | | **69** |  68-90  |         **14.1**          | **79** | No  | |        |         | 11 _(scroll-snap-margin)_ |        |     |")
    String scrollSnapMargin() default "	";

    /**
     * The `scroll-margin-bottom` property defines the bottom margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |              Safari              |  Edge  | IE  | | :----: | :-----: | :------------------------------: | :----: | :-: | | **69** | **68**  |             **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-bottom)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginBottom%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginBottom
     */

    @Schema(title = "The `scroll-margin-bottom` property defines the bottom margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |              Safari              |  Edge  | IE  | | :----: | :-----: | :------------------------------: | :----: | :-: | | **69** | **68**  |             **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-bottom)_ |        |     |")
    String scrollSnapMarginBottom() default "	";

    /**
     * The `scroll-margin-left` property defines the left margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari             |  Edge  | IE  | | :----: | :-----: | :----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-left)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginLeft%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginLeft
     */

    @Schema(title = "The `scroll-margin-left` property defines the left margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari             |  Edge  | IE  | | :----: | :-----: | :----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-left)_ |        |     |")
    String scrollSnapMarginLeft() default "	";

    /**
     * The `scroll-margin-right` property defines the right margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari              |  Edge  | IE  | | :----: | :-----: | :-----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-right)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginRight%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginRight
     */

    @Schema(title = "The `scroll-margin-right` property defines the right margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |             Safari              |  Edge  | IE  | | :----: | :-----: | :-----------------------------: | :----: | :-: | | **69** | **68**  |            **14.1**             | **79** | No  | |        |         | 11 _(scroll-snap-margin-right)_ |        |     |")
    String scrollSnapMarginRight() default "	";

    /**
     * The `scroll-margin-top` property defines the top margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |            Safari             |  Edge  | IE  | | :----: | :-----: | :---------------------------: | :----: | :-: | | **69** | **68**  |           **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-top)_ |        |     |
     *
     * 参考定义: "#/definitions/Property.ScrollMarginTop%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ScrollMarginTop
     */

    @Schema(title = "The `scroll-margin-top` property defines the top margin of the scroll snap area that is used for snapping this box to the snapport. The scroll snap area is determined by taking the transformed border box, finding its rectangular bounding box (axis-aligned in the scroll container’s coordinate space), then adding the specified outsets.\n\n**Syntax**: `<length>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |            Safari             |  Edge  | IE  | | :----: | :-----: | :---------------------------: | :----: | :-: | | **69** | **68**  |           **14.1**            | **79** | No  | |        |         | 11 _(scroll-snap-margin-top)_ |        |     |")
    String scrollSnapMarginTop() default "	";

    /**
     * The **`scroll-snap-stop`** CSS property defines whether the scroll container is allowed to pass over possible snap positions.\n\n**Syntax**: `normal | always`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **75** |   No    | **15** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollSnapStop"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"always"},{"type":"string","const":"normal"}]
     *
     * @see Property_ScrollSnapStop
     */
    @Const({"always", "normal"})
    @Schema(title = "The **`scroll-snap-stop`** CSS property defines whether the scroll container is allowed to pass over possible snap positions.\n\n**Syntax**: `normal | always`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **75** |   No    | **15** | **79** | No  |")
    String scrollSnapStop() default "	";

    /**
     * The **`scroll-snap-type`** CSS property sets how strictly snap points are enforced on the scroll container in case there is one.\n\n**Syntax**: `none | [ x | y | block | inline | both ] [ mandatory | proximity ]?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |      IE      | | :----: | :-----: | :-----: | :----: | :----------: | | **69** |  39-68  | **11**  | **79** | **10** _-x-_ | |        |         | 9 _-x-_ |        |              |
     *
     * 参考定义: "#/definitions/Property.ScrollSnapType"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"block"},{"type":"string","const":"both"},{"type":"string","const":"inline"},{"type":"string","const":"none"},{"type":"string","const":"x"},{"type":"string","const":"y"},{"type":"string"}]
     *
     * @see Property_ScrollSnapType
     */
    @Const({"block", "both", "inline", "none", "x", "y"})
    @Schema(title = "The **`scroll-snap-type`** CSS property sets how strictly snap points are enforced on the scroll container in case there is one.\n\n**Syntax**: `none | [ x | y | block | inline | both ] [ mandatory | proximity ]?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |      IE      | | :----: | :-----: | :-----: | :----: | :----------: | | **69** |  39-68  | **11**  | **79** | **10** _-x-_ | |        |         | 9 _-x-_ |        |              |")
    String scrollSnapType() default "	";

    /**
     * The **`scrollbar-color`** CSS property sets the color of the scrollbar track and thumb.\n\n**Syntax**: `auto | <color>{2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **64**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollbarColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_ScrollbarColor
     */
    @Const({"auto"})
    @Schema(title = "The **`scrollbar-color`** CSS property sets the color of the scrollbar track and thumb.\n\n**Syntax**: `auto | <color>{2}`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **64**  |   No   |  No  | No  |")
    String scrollbarColor() default "	";

    /**
     * The **`scrollbar-gutter`** CSS property allows authors to reserve space for the scrollbar, preventing unwanted layout changes as the content grows while also avoiding unnecessary visuals when scrolling isn't needed.\n\n**Syntax**: `auto | stable && both-edges?`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **94** |   No    |   No   | **94** | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollbarGutter"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"stable"},{"type":"string"}]
     *
     * @see Property_ScrollbarGutter
     */
    @Const({"auto", "stable"})
    @Schema(title = "The **`scrollbar-gutter`** CSS property allows authors to reserve space for the scrollbar, preventing unwanted layout changes as the content grows while also avoiding unnecessary visuals when scrolling isn't needed.\n\n**Syntax**: `auto | stable && both-edges?`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **94** |   No    |   No   | **94** | No  |")
    String scrollbarGutter() default "	";

    /**
     * The **`scrollbar-width`** property allows the author to set the maximum thickness of an element’s scrollbars when they are shown.\n\n**Syntax**: `auto | thin | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **64**  |   No   |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.ScrollbarWidth"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string","const":"thin"}]
     *
     * @see Property_ScrollbarWidth
     */
    @Const({"auto", "none", "thin"})
    @Schema(title = "The **`scrollbar-width`** property allows the author to set the maximum thickness of an element’s scrollbars when they are shown.\n\n**Syntax**: `auto | thin | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari | Edge | IE  | | :----: | :-----: | :----: | :--: | :-: | |   No   | **64**  |   No   |  No  | No  |")
    String scrollbarWidth() default "	";

    /**
     * The **`shape-image-threshold`** CSS property sets the alpha channel threshold used to extract the shape using an image as the value for `shape-outside`.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `0.0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **37** | **62**  | **10.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ShapeImageThreshold"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ShapeImageThreshold
     */

    @Schema(title = "The **`shape-image-threshold`** CSS property sets the alpha channel threshold used to extract the shape using an image as the value for `shape-outside`.\n\n**Syntax**: `<alpha-value>`\n\n**Initial value**: `0.0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **37** | **62**  | **10.1** | **79** | No  |")
    String shapeImageThreshold() default "	";

    /**
     * The **`shape-margin`** CSS property sets a margin for a CSS shape created using `shape-outside`.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **37** | **62**  | **10.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ShapeMargin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_ShapeMargin
     */

    @Schema(title = "The **`shape-margin`** CSS property sets a margin for a CSS shape created using `shape-outside`.\n\n**Syntax**: `<length-percentage>`\n\n**Initial value**: `0`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **37** | **62**  | **10.1** | **79** | No  |")
    String shapeMargin() default "	";

    /**
     * The **`shape-outside`** CSS property defines a shape—which may be non-rectangular—around which adjacent inline content should wrap. By default, inline content wraps around its margin box; `shape-outside` provides a way to customize this wrapping, making it possible to wrap text around complex objects rather than simple boxes.\n\n**Syntax**: `none | [ <shape-box> || <basic-shape> ] | <image>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **37** | **62**  | **10.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.ShapeOutside"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Box"},{"type":"string","const":"margin-box"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_ShapeOutside
     */
    @Const({"margin-box", "none"})
    @Schema(title = "The **`shape-outside`** CSS property defines a shape—which may be non-rectangular—around which adjacent inline content should wrap. By default, inline content wraps around its margin box; `shape-outside` provides a way to customize this wrapping, making it possible to wrap text around complex objects rather than simple boxes.\n\n**Syntax**: `none | [ <shape-box> || <basic-shape> ] | <image>`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **37** | **62**  | **10.1** | **79** | No  |")
    String shapeOutside() default "	";

    /**
     * The **`tab-size`** CSS property is used to customize the width of tab characters (U+0009).\n\n**Syntax**: `<integer> | <length>`\n\n**Initial value**: `8`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **21** | **91**  | **7**  | **79** | No  | |        | 4 _-x-_ |        |        |     |
     *
     * 参考定义: "#/definitions/Property.TabSize%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_TabSize
     */

    @Schema(title = "The **`tab-size`** CSS property is used to customize the width of tab characters (U+0009).\n\n**Syntax**: `<integer> | <length>`\n\n**Initial value**: `8`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **21** | **91**  | **7**  | **79** | No  | |        | 4 _-x-_ |        |        |     |")
    String tabSize() default "	";

    /**
     * The **`table-layout`** CSS property sets the algorithm used to lay out `<table>` cells, rows, and columns.\n\n**Syntax**: `auto | fixed`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **14** |  **1**  | **1**  | **12** | **5** |
     *
     * 参考定义: "#/definitions/Property.TableLayout"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"fixed"}]
     *
     * @see Property_TableLayout
     */
    @Const({"auto", "fixed"})
    @Schema(title = "The **`table-layout`** CSS property sets the algorithm used to lay out `<table>` cells, rows, and columns.\n\n**Syntax**: `auto | fixed`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **14** |  **1**  | **1**  | **12** | **5** |")
    String tableLayout() default "	";

    /**
     * The **`text-align`** CSS property sets the horizontal alignment of a block element or table-cell box. This means it works like `vertical-align` but in the horizontal direction.\n\n**Syntax**: `start | end | left | right | center | justify | match-parent`\n\n**Initial value**: `start`, or a nameless value that acts as `left` if _direction_ is `ltr`, `right` if _direction_ is `rtl` if `start` is not supported by the browser.\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.TextAlign"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"left"},{"type":"string","const":"match-parent"},{"type":"string","const":"right"},{"type":"string","const":"start"}]
     *
     * @see Property_TextAlign
     */
    @Const({"center", "end", "justify", "left", "match-parent", "right", "start"})
    @Schema(title = "The **`text-align`** CSS property sets the horizontal alignment of a block element or table-cell box. This means it works like `vertical-align` but in the horizontal direction.\n\n**Syntax**: `start | end | left | right | center | justify | match-parent`\n\n**Initial value**: `start`, or a nameless value that acts as `left` if _direction_ is `ltr`, `right` if _direction_ is `rtl` if `start` is not supported by the browser.\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String textAlign() default "	";

    /**
     * The **`text-align-last`** CSS property sets how the last line of a block or a line, right before a forced line break, is aligned.\n\n**Syntax**: `auto | start | end | left | right | center | justify`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **47** | **49**  |   No   | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.TextAlignLast"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"center"},{"type":"string","const":"end"},{"type":"string","const":"justify"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"start"}]
     *
     * @see Property_TextAlignLast
     */
    @Const({"auto", "center", "end", "justify", "left", "right", "start"})
    @Schema(title = "The **`text-align-last`** CSS property sets how the last line of a block or a line, right before a forced line break, is aligned.\n\n**Syntax**: `auto | start | end | left | right | center | justify`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **47** | **49**  |   No   | **12** | **5.5** |")
    String textAlignLast() default "	";

    /**
     * The **`text-combine-upright`** CSS property sets the combination of characters into the space of a single character. If the combined text is wider than 1em, the user agent must fit the contents within 1em. The resulting composition is treated as a single upright glyph for layout and decoration. This property only has an effect in vertical writing modes.\n\n**Syntax**: `none | all | [ digits <integer>? ]`\n\n**Initial value**: `none`\n\n|           Chrome           | Firefox |              Safari              | Edge  |                   IE                   | | :------------------------: | :-----: | :------------------------------: | :---: | :------------------------------------: | |           **48**           | **48**  | **5.1** _(-webkit-text-combine)_ | 15-79 | **11** _(-ms-text-combine-horizontal)_ | | 9 _(-webkit-text-combine)_ |         |                                  |       |                                        |
     *
     * 参考定义: "#/definitions/Property.TextCombineUpright"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextCombineUpright
     */
    @Const({"all", "none"})
    @Schema(title = "The **`text-combine-upright`** CSS property sets the combination of characters into the space of a single character. If the combined text is wider than 1em, the user agent must fit the contents within 1em. The resulting composition is treated as a single upright glyph for layout and decoration. This property only has an effect in vertical writing modes.\n\n**Syntax**: `none | all | [ digits <integer>? ]`\n\n**Initial value**: `none`\n\n|           Chrome           | Firefox |              Safari              | Edge  |                   IE                   | | :------------------------: | :-----: | :------------------------------: | :---: | :------------------------------------: | |           **48**           | **48**  | **5.1** _(-webkit-text-combine)_ | 15-79 | **11** _(-ms-text-combine-horizontal)_ | | 9 _(-webkit-text-combine)_ |         |                                  |       |                                        |")
    String textCombineUpright() default "	";

    /**
     * The **`text-decoration-color`** CSS property sets the color of decorations added to text by `text-decoration-line`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **36**  | **12.1** | **79** | No  | |        |         | 8 _-x-_  |        |     |
     *
     * 参考定义: "#/definitions/Property.TextDecorationColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_TextDecorationColor
     */

    @Schema(title = "The **`text-decoration-color`** CSS property sets the color of decorations added to text by `text-decoration-line`.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **36**  | **12.1** | **79** | No  | |        |         | 8 _-x-_  |        |     |")
    String textDecorationColor() default "	";

    /**
     * The **`text-decoration-line`** CSS property sets the kind of decoration that is used on text in an element, such as an underline or overline.\n\n**Syntax**: `none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **36**  | **12.1** | **79** | No  | |        |         | 8 _-x-_  |        |     |
     *
     * 参考定义: "#/definitions/Property.TextDecorationLine"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"blink"},{"type":"string","const":"grammar-error"},{"type":"string","const":"line-through"},{"type":"string","const":"none"},{"type":"string","const":"overline"},{"type":"string","const":"spelling-error"},{"type":"string","const":"underline"},{"type":"string"}]
     *
     * @see Property_TextDecorationLine
     */
    @Const({"blink", "grammar-error", "line-through", "none", "overline", "spelling-error", "underline"})
    @Schema(title = "The **`text-decoration-line`** CSS property sets the kind of decoration that is used on text in an element, such as an underline or overline.\n\n**Syntax**: `none | [ underline || overline || line-through || blink ] | spelling-error | grammar-error`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **36**  | **12.1** | **79** | No  | |        |         | 8 _-x-_  |        |     |")
    String textDecorationLine() default "	";

    /**
     * The **`text-decoration-skip`** CSS property sets what parts of an element’s content any text decoration affecting the element must skip over. It controls all text decoration lines drawn by the element and also any text decoration lines drawn by its ancestors.\n\n**Syntax**: `none | [ objects || [ spaces | [ leading-spaces || trailing-spaces ] ] || edges || box-decoration ]`\n\n**Initial value**: `objects`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | | 57-64  |   No    | **12.1** |  No  | No  | |        |         | 7 _-x-_  |      |     |
     *
     * 参考定义: "#/definitions/Property.TextDecorationSkip"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"box-decoration"},{"type":"string","const":"edges"},{"type":"string","const":"leading-spaces"},{"type":"string","const":"none"},{"type":"string","const":"objects"},{"type":"string","const":"spaces"},{"type":"string","const":"trailing-spaces"},{"type":"string"}]
     *
     * @see Property_TextDecorationSkip
     */
    @Const({"box-decoration", "edges", "leading-spaces", "none", "objects", "spaces", "trailing-spaces"})
    @Schema(title = "The **`text-decoration-skip`** CSS property sets what parts of an element’s content any text decoration affecting the element must skip over. It controls all text decoration lines drawn by the element and also any text decoration lines drawn by its ancestors.\n\n**Syntax**: `none | [ objects || [ spaces | [ leading-spaces || trailing-spaces ] ] || edges || box-decoration ]`\n\n**Initial value**: `objects`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | | 57-64  |   No    | **12.1** |  No  | No  | |        |         | 7 _-x-_  |      |     |")
    String textDecorationSkip() default "	";

    /**
     * The **`text-decoration-skip-ink`** CSS property specifies how overlines and underlines are drawn when they pass over glyph ascenders and descenders.\n\n**Syntax**: `auto | all | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **64** | **70**  |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.TextDecorationSkipInk"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"none"}]
     *
     * @see Property_TextDecorationSkipInk
     */
    @Const({"all", "auto", "none"})
    @Schema(title = "The **`text-decoration-skip-ink`** CSS property specifies how overlines and underlines are drawn when they pass over glyph ascenders and descenders.\n\n**Syntax**: `auto | all | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **64** | **70**  |   No   | **79** | No  |")
    String textDecorationSkipInk() default "	";

    /**
     * The **`text-decoration-style`** CSS property sets the style of the lines specified by `text-decoration-line`. The style applies to all lines that are set with `text-decoration-line`.\n\n**Syntax**: `solid | double | dotted | dashed | wavy`\n\n**Initial value**: `solid`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **36**  | **12.1** | **79** | No  | |        |         | 8 _-x-_  |        |     |
     *
     * 参考定义: "#/definitions/Property.TextDecorationStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"dashed"},{"type":"string","const":"dotted"},{"type":"string","const":"double"},{"type":"string","const":"solid"},{"type":"string","const":"wavy"}]
     *
     * @see Property_TextDecorationStyle
     */
    @Const({"dashed", "dotted", "double", "solid", "wavy"})
    @Schema(title = "The **`text-decoration-style`** CSS property sets the style of the lines specified by `text-decoration-line`. The style applies to all lines that are set with `text-decoration-line`.\n\n**Syntax**: `solid | double | dotted | dashed | wavy`\n\n**Initial value**: `solid`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **57** | **36**  | **12.1** | **79** | No  | |        |         | 8 _-x-_  |        |     |")
    String textDecorationStyle() default "	";

    /**
     * The **`text-decoration-thickness`** CSS property sets the stroke thickness of the decoration line that is used on text in an element, such as a line-through, underline, or overline.\n\n**Syntax**: `auto | from-font | <length> | <percentage> `\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **89** | **70**  | **12.1** | **89** | No  |
     *
     * 参考定义: "#/definitions/Property.TextDecorationThickness%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"from-font"}]
     *
     * @see Property_TextDecorationThickness
     */
    @Const({"auto", "from-font"})
    @Schema(title = "The **`text-decoration-thickness`** CSS property sets the stroke thickness of the decoration line that is used on text in an element, such as a line-through, underline, or overline.\n\n**Syntax**: `auto | from-font | <length> | <percentage> `\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **89** | **70**  | **12.1** | **89** | No  |")
    String textDecorationThickness() default "	";

    /**
     * The **`text-decoration-thickness`** CSS property sets the stroke thickness of the decoration line that is used on text in an element, such as a line-through, underline, or overline.\n\n**Syntax**: `auto | from-font | <length> | <percentage> `\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  | Edge  | IE  | | :----: | :-----: | :------: | :---: | :-: | | 87-89  | **70**  | **12.1** | 87-89 | No  |
     *
     * 参考定义: "#/definitions/Property.TextDecorationThickness%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"},{"type":"string","const":"from-font"}]
     *
     * @see Property_TextDecorationThickness
     */
    @Const({"auto", "from-font"})
    @Schema(title = "The **`text-decoration-thickness`** CSS property sets the stroke thickness of the decoration line that is used on text in an element, such as a line-through, underline, or overline.\n\n**Syntax**: `auto | from-font | <length> | <percentage> `\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  | Edge  | IE  | | :----: | :-----: | :------: | :---: | :-: | | 87-89  | **70**  | **12.1** | 87-89 | No  |")
    String textDecorationWidth() default "	";

    /**
     * The **`text-emphasis-color`** CSS property sets the color of emphasis marks. This value can also be set using the `text-emphasis` shorthand.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.TextEmphasisColor"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.Color"}]
     *
     * @see Property_TextEmphasisColor
     */

    @Schema(title = "The **`text-emphasis-color`** CSS property sets the color of emphasis marks. This value can also be set using the `text-emphasis` shorthand.\n\n**Syntax**: `<color>`\n\n**Initial value**: `currentcolor`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |")
    String textEmphasisColor() default "	";

    /**
     * The **`text-emphasis-position`** CSS property sets where emphasis marks are drawn. Like ruby text, if there isn't enough room for emphasis marks, the line height is increased.\n\n**Syntax**: `[ over | under ] && [ right | left ]`\n\n**Initial value**: `over right`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.TextEmphasisPosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TextEmphasisPosition
     */

    @Schema(title = "The **`text-emphasis-position`** CSS property sets where emphasis marks are drawn. Like ruby text, if there isn't enough room for emphasis marks, the line height is increased.\n\n**Syntax**: `[ over | under ] && [ right | left ]`\n\n**Initial value**: `over right`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |")
    String textEmphasisPosition() default "	";

    /**
     * The **`text-emphasis-style`** CSS property sets the appearance of emphasis marks. It can also be set, and reset, using the `text-emphasis` shorthand.\n\n**Syntax**: `none | [ [ filled | open ] || [ dot | circle | double-circle | triangle | sesame ] ] | <string>`\n\n**Initial value**: `none`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |
     *
     * 参考定义: "#/definitions/Property.TextEmphasisStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"circle"},{"type":"string","const":"dot"},{"type":"string","const":"double-circle"},{"type":"string","const":"filled"},{"type":"string","const":"none"},{"type":"string","const":"open"},{"type":"string","const":"sesame"},{"type":"string","const":"triangle"},{"type":"string"}]
     *
     * @see Property_TextEmphasisStyle
     */
    @Const({"circle", "dot", "double-circle", "filled", "none", "open", "sesame", "triangle"})
    @Schema(title = "The **`text-emphasis-style`** CSS property sets the appearance of emphasis marks. It can also be set, and reset, using the `text-emphasis` shorthand.\n\n**Syntax**: `none | [ [ filled | open ] || [ dot | circle | double-circle | triangle | sesame ] ] | <string>`\n\n**Initial value**: `none`\n\n|    Chrome    | Firefox | Safari |     Edge     | IE  | | :----------: | :-----: | :----: | :----------: | :-: | | **25** _-x-_ | **46**  | **7**  | **79** _-x-_ | No  |")
    String textEmphasisStyle() default "	";

    /**
     * The **`text-indent`** CSS property sets the length of empty space (indentation) that is put before lines of text in a block.\n\n**Syntax**: `<length-percentage> && hanging? && each-line?`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |
     *
     * 参考定义: "#/definitions/Property.TextIndent%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_TextIndent
     */

    @Schema(title = "The **`text-indent`** CSS property sets the length of empty space (indentation) that is put before lines of text in a block.\n\n**Syntax**: `<length-percentage> && hanging? && each-line?`\n\n**Initial value**: `0`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **3** |")
    String textIndent() default "	";

    /**
     * The **`text-justify`** CSS property sets what type of justification should be applied to text when `text-align``: justify;` is set on an element.\n\n**Syntax**: `auto | inter-character | inter-word | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | |  n/a   | **55**  |   No   | **12** | **11** |
     *
     * 参考定义: "#/definitions/Property.TextJustify"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"inter-character"},{"type":"string","const":"inter-word"},{"type":"string","const":"none"}]
     *
     * @see Property_TextJustify
     */
    @Const({"auto", "inter-character", "inter-word", "none"})
    @Schema(title = "The **`text-justify`** CSS property sets what type of justification should be applied to text when `text-align``: justify;` is set on an element.\n\n**Syntax**: `auto | inter-character | inter-word | none`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |   IE   | | :----: | :-----: | :----: | :----: | :----: | |  n/a   | **55**  |   No   | **12** | **11** |")
    String textJustify() default "	";

    /**
     * The **`text-orientation`** CSS property sets the orientation of the text characters in a line. It only affects text in vertical mode (when `writing-mode` is not `horizontal-tb`). It is useful for controlling the display of languages that use vertical script, and also for making vertical table headers.\n\n**Syntax**: `mixed | upright | sideways`\n\n**Initial value**: `mixed`\n\n|  Chrome  | Firefox |  Safari   |  Edge  | IE  | | :------: | :-----: | :-------: | :----: | :-: | |  **48**  | **41**  |  **14**   | **79** | No  | | 11 _-x-_ |         | 5.1 _-x-_ |        |     |
     *
     * 参考定义: "#/definitions/Property.TextOrientation"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"mixed"},{"type":"string","const":"sideways"},{"type":"string","const":"upright"}]
     *
     * @see Property_TextOrientation
     */
    @Const({"mixed", "sideways", "upright"})
    @Schema(title = "The **`text-orientation`** CSS property sets the orientation of the text characters in a line. It only affects text in vertical mode (when `writing-mode` is not `horizontal-tb`). It is useful for controlling the display of languages that use vertical script, and also for making vertical table headers.\n\n**Syntax**: `mixed | upright | sideways`\n\n**Initial value**: `mixed`\n\n|  Chrome  | Firefox |  Safari   |  Edge  | IE  | | :------: | :-----: | :-------: | :----: | :-: | |  **48**  | **41**  |  **14**   | **79** | No  | | 11 _-x-_ |         | 5.1 _-x-_ |        |     |")
    String textOrientation() default "	";

    /**
     * The **`text-overflow`** CSS property sets how hidden overflow content is signaled to users. It can be clipped, display an ellipsis ('`…`'), or display a custom string.\n\n**Syntax**: `[ clip | ellipsis | <string> ]{1,2}`\n\n**Initial value**: `clip`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **7**  | **1.3** | **12** | **6** |
     *
     * 参考定义: "#/definitions/Property.TextOverflow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"clip"},{"type":"string","const":"ellipsis"},{"type":"string"}]
     *
     * @see Property_TextOverflow
     */
    @Const({"clip", "ellipsis"})
    @Schema(title = "The **`text-overflow`** CSS property sets how hidden overflow content is signaled to users. It can be clipped, display an ellipsis ('`…`'), or display a custom string.\n\n**Syntax**: `[ clip | ellipsis | <string> ]{1,2}`\n\n**Initial value**: `clip`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **1**  |  **7**  | **1.3** | **12** | **6** |")
    String textOverflow() default "	";

    /**
     * The **`text-shadow`** CSS property adds shadows to text. It accepts a comma-separated list of shadows to be applied to the text and any of its `decorations`. Each shadow is described by some combination of X and Y offsets from the element, blur radius, and color.\n\n**Syntax**: `none | <shadow-t>#`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE   | | :----: | :-----: | :-----: | :----: | :----: | | **2**  | **3.5** | **1.1** | **12** | **10** |
     *
     * 参考定义: "#/definitions/Property.TextShadow"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextShadow
     */
    @Const({"none"})
    @Schema(title = "The **`text-shadow`** CSS property adds shadows to text. It accepts a comma-separated list of shadows to be applied to the text and any of its `decorations`. Each shadow is described by some combination of X and Y offsets from the element, blur radius, and color.\n\n**Syntax**: `none | <shadow-t>#`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE   | | :----: | :-----: | :-----: | :----: | :----: | | **2**  | **3.5** | **1.1** | **12** | **10** |")
    String textShadow() default "	";

    /**
     * The **`text-size-adjust`** CSS property controls the text inflation algorithm used on some smartphones and tablets. Other browsers will ignore this property.\n\n**Syntax**: `none | auto | <percentage>`\n\n**Initial value**: `auto` for smartphone browsers supporting inflation, `none` in other cases (and then not modifiable).\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **54** |   No    |   No   | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.TextSizeAdjust"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TextSizeAdjust
     */
    @Const({"auto", "none"})
    @Schema(title = "The **`text-size-adjust`** CSS property controls the text inflation algorithm used on some smartphones and tablets. Other browsers will ignore this property.\n\n**Syntax**: `none | auto | <percentage>`\n\n**Initial value**: `auto` for smartphone browsers supporting inflation, `none` in other cases (and then not modifiable).\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **54** |   No    |   No   | **79** | No  |")
    String textSizeAdjust() default "	";

    /**
     * The **`text-transform`** CSS property specifies how to capitalize an element's text. It can be used to make text appear in all-uppercase or all-lowercase, or with each word capitalized. It also can help improve legibility for ruby.\n\n**Syntax**: `none | capitalize | uppercase | lowercase | full-width | full-size-kana`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.TextTransform"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"capitalize"},{"type":"string","const":"full-size-kana"},{"type":"string","const":"full-width"},{"type":"string","const":"lowercase"},{"type":"string","const":"none"},{"type":"string","const":"uppercase"}]
     *
     * @see Property_TextTransform
     */
    @Const({"capitalize", "full-size-kana", "full-width", "lowercase", "none", "uppercase"})
    @Schema(title = "The **`text-transform`** CSS property specifies how to capitalize an element's text. It can be used to make text appear in all-uppercase or all-lowercase, or with each word capitalized. It also can help improve legibility for ruby.\n\n**Syntax**: `none | capitalize | uppercase | lowercase | full-width | full-size-kana`\n\n**Initial value**: `none`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String textTransform() default "	";

    /**
     * The **`text-underline-offset`** CSS property sets the offset distance of an underline text decoration line (applied using `text-decoration`) from its original position.\n\n**Syntax**: `auto | <length> | <percentage> `\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **70**  | **12.1** | **87** | No  |
     *
     * 参考定义: "#/definitions/Property.TextUnderlineOffset%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_TextUnderlineOffset
     */
    @Const({"auto"})
    @Schema(title = "The **`text-underline-offset`** CSS property sets the offset distance of an underline text decoration line (applied using `text-decoration`) from its original position.\n\n**Syntax**: `auto | <length> | <percentage> `\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  | IE  | | :----: | :-----: | :------: | :----: | :-: | | **87** | **70**  | **12.1** | **87** | No  |")
    String textUnderlineOffset() default "	";

    /**
     * The **`text-underline-position`** CSS property specifies the position of the underline which is set using the `text-decoration` property's `underline` value.\n\n**Syntax**: `auto | from-font | [ under || [ left | right ] ]`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  |  IE   | | :----: | :-----: | :------: | :----: | :---: | | **33** | **74**  | **12.1** | **12** | **6** | |        |         | 9 _-x-_  |        |       |
     *
     * 参考定义: "#/definitions/Property.TextUnderlinePosition"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"string","const":"from-font"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"under"},{"type":"string"}]
     *
     * @see Property_TextUnderlinePosition
     */
    @Const({"auto", "from-font", "left", "right", "under"})
    @Schema(title = "The **`text-underline-position`** CSS property specifies the position of the underline which is set using the `text-decoration` property's `underline` value.\n\n**Syntax**: `auto | from-font | [ under || [ left | right ] ]`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox |  Safari  |  Edge  |  IE   | | :----: | :-----: | :------: | :----: | :---: | | **33** | **74**  | **12.1** | **12** | **6** | |        |         | 9 _-x-_  |        |       |")
    String textUnderlinePosition() default "	";

    /**
     * The **`top`** CSS property participates in specifying the vertical position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **5** |
     *
     * 参考定义: "#/definitions/Property.Top%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"auto"}]
     *
     * @see Property_Top
     */
    @Const({"auto"})
    @Schema(title = "The **`top`** CSS property participates in specifying the vertical position of a positioned element. It has no effect on non-positioned elements.\n\n**Syntax**: `<length> | <percentage> | auto`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **5** |")
    String top() default "	";

    /**
     * The **`touch-action`** CSS property sets how an element's region can be manipulated by a touchscreen user (for example, by zooming features built into the browser).\n\n**Syntax**: `auto | none | [ [ pan-x | pan-left | pan-right ] || [ pan-y | pan-up | pan-down ] || pinch-zoom ] | manipulation`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |    IE    | | :----: | :-----: | :----: | :----: | :------: | | **36** | **52**  | **13** | **12** |  **11**  | |        |         |        |        | 10 _-x-_ |
     *
     * 参考定义: "#/definitions/Property.TouchAction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-ms-manipulation"},{"type":"string","const":"-ms-none"},{"type":"string","const":"-ms-pinch-zoom"},{"type":"string","const":"auto"},{"type":"string","const":"manipulation"},{"type":"string","const":"none"},{"type":"string","const":"pan-down"},{"type":"string","const":"pan-left"},{"type":"string","const":"pan-right"},{"type":"string","const":"pan-up"},{"type":"string","const":"pan-x"},{"type":"string","const":"pan-y"},{"type":"string","const":"pinch-zoom"},{"type":"string"}]
     *
     * @see Property_TouchAction
     */
    @Const({"-ms-manipulation", "-ms-none", "-ms-pinch-zoom", "auto", "manipulation", "none", "pan-down", "pan-left", "pan-right", "pan-up", "pan-x", "pan-y", "pinch-zoom"})
    @Schema(title = "The **`touch-action`** CSS property sets how an element's region can be manipulated by a touchscreen user (for example, by zooming features built into the browser).\n\n**Syntax**: `auto | none | [ [ pan-x | pan-left | pan-right ] || [ pan-y | pan-up | pan-down ] || pinch-zoom ] | manipulation`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |    IE    | | :----: | :-----: | :----: | :----: | :------: | | **36** | **52**  | **13** | **12** |  **11**  | |        |         |        |        | 10 _-x-_ |")
    String touchAction() default "	";

    /**
     * The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE    | | :-----: | :-----: | :-------: | :----: | :-----: | | **36**  | **16**  |   **9**   | **12** | **10**  | | 1 _-x-_ |         | 3.1 _-x-_ |        | 9 _-x-_ |
     *
     * 参考定义: "#/definitions/Property.Transform"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_Transform
     */
    @Const({"none"})
    @Schema(title = "The **`transform`** CSS property lets you rotate, scale, skew, or translate an element. It modifies the coordinate space of the CSS visual formatting model.\n\n**Syntax**: `none | <transform-list>`\n\n**Initial value**: `none`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE    | | :-----: | :-----: | :-------: | :----: | :-----: | | **36**  | **16**  |   **9**   | **12** | **10**  | | 1 _-x-_ |         | 3.1 _-x-_ |        | 9 _-x-_ |")
    String transform() default "	";

    /**
     * The **`transform-box`** CSS property defines the layout box to which the `transform` and `transform-origin` properties relate.\n\n**Syntax**: `content-box | border-box | fill-box | stroke-box | view-box`\n\n**Initial value**: `view-box`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **64** | **55**  | **11** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.TransformBox"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"border-box"},{"type":"string","const":"content-box"},{"type":"string","const":"fill-box"},{"type":"string","const":"stroke-box"},{"type":"string","const":"view-box"}]
     *
     * @see Property_TransformBox
     */
    @Const({"border-box", "content-box", "fill-box", "stroke-box", "view-box"})
    @Schema(title = "The **`transform-box`** CSS property defines the layout box to which the `transform` and `transform-origin` properties relate.\n\n**Syntax**: `content-box | border-box | fill-box | stroke-box | view-box`\n\n**Initial value**: `view-box`\n\n| Chrome | Firefox | Safari |  Edge  | IE  | | :----: | :-----: | :----: | :----: | :-: | | **64** | **55**  | **11** | **79** | No  |")
    String transformBox() default "	";

    /**
     * The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`\n\n| Chrome  |  Firefox  | Safari  |  Edge  |   IE    | | :-----: | :-------: | :-----: | :----: | :-----: | | **36**  |  **16**   |  **9**  | **12** | **10**  | | 1 _-x-_ | 3.5 _-x-_ | 2 _-x-_ |        | 9 _-x-_ |
     *
     * 参考定义: "#/definitions/Property.TransformOrigin%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"bottom"},{"type":"string","const":"center"},{"type":"string","const":"left"},{"type":"string","const":"right"},{"type":"string","const":"top"}]
     *
     * @see Property_TransformOrigin
     */
    @Const({"bottom", "center", "left", "right", "top"})
    @Schema(title = "The **`transform-origin`** CSS property sets the origin for an element's transformations.\n\n**Syntax**: `[ <length-percentage> | left | center | right | top | bottom ] | [ [ <length-percentage> | left | center | right ] && [ <length-percentage> | top | center | bottom ] ] <length>?`\n\n**Initial value**: `50% 50% 0`\n\n| Chrome  |  Firefox  | Safari  |  Edge  |   IE    | | :-----: | :-------: | :-----: | :----: | :-----: | | **36**  |  **16**   |  **9**  | **12** | **10**  | | 1 _-x-_ | 3.5 _-x-_ | 2 _-x-_ |        | 9 _-x-_ |")
    String transformOrigin() default "	";

    /**
     * The **`transform-style`** CSS property sets whether children of an element are positioned in the 3D space or are flattened in the plane of the element.\n\n**Syntax**: `flat | preserve-3d`\n\n**Initial value**: `flat`\n\n|  Chrome  | Firefox  | Safari  |  Edge  | IE  | | :------: | :------: | :-----: | :----: | :-: | |  **36**  |  **16**  |  **9**  | **12** | No  | | 12 _-x-_ | 10 _-x-_ | 4 _-x-_ |        |     |
     *
     * 参考定义: "#/definitions/Property.TransformStyle"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"flat"},{"type":"string","const":"preserve-3d"}]
     *
     * @see Property_TransformStyle
     */
    @Const({"flat", "preserve-3d"})
    @Schema(title = "The **`transform-style`** CSS property sets whether children of an element are positioned in the 3D space or are flattened in the plane of the element.\n\n**Syntax**: `flat | preserve-3d`\n\n**Initial value**: `flat`\n\n|  Chrome  | Firefox  | Safari  |  Edge  | IE  | | :------: | :------: | :-----: | :----: | :-: | |  **36**  |  **16**  |  **9**  | **12** | No  | | 12 _-x-_ | 10 _-x-_ | 4 _-x-_ |        |     |")
    String transformStyle() default "	";

    /**
     * The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **26**  | **16**  |  **9**  | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 4 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.TransitionDelay%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDelay
     */

    @Schema(title = "The **`transition-delay`** CSS property specifies the duration to wait before starting a property's transition effect when its value changes.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox | Safari  |  Edge  |   IE   | | :-----: | :-----: | :-----: | :----: | :----: | | **26**  | **16**  |  **9**  | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 4 _-x-_ |        |        |")
    String transitionDelay() default "	";

    /**
     * The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.TransitionDuration%3Cstring%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"}]
     *
     * @see Property_TransitionDuration
     */

    @Schema(title = "The **`transition-duration`** CSS property sets the length of time a transition animation should take to complete. By default, the value is `0s`, meaning that no animation will occur.\n\n**Syntax**: `<time>#`\n\n**Initial value**: `0s`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |")
    String transitionDuration() default "	";

    /**
     * The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.TransitionProperty"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"all"},{"type":"string","const":"none"},{"type":"string"}]
     *
     * @see Property_TransitionProperty
     */
    @Const({"all", "none"})
    @Schema(title = "The **`transition-property`** CSS property sets the CSS properties to which a transition effect should be applied.\n\n**Syntax**: `none | <single-transition-property>#`\n\n**Initial value**: all\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |")
    String transitionProperty() default "	";

    /**
     * The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |
     *
     * 参考定义: "#/definitions/Property.TransitionTimingFunction"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.EasingFunction"},{"type":"string"}]
     *
     * @see Property_TransitionTimingFunction
     */

    @Schema(title = "The **`transition-timing-function`** CSS property sets how intermediate values are calculated for CSS properties being affected by a transition effect.\n\n**Syntax**: `<easing-function>#`\n\n**Initial value**: `ease`\n\n| Chrome  | Firefox |  Safari   |  Edge  |   IE   | | :-----: | :-----: | :-------: | :----: | :----: | | **26**  | **16**  |   **9**   | **12** | **10** | | 1 _-x-_ | 4 _-x-_ | 3.1 _-x-_ |        |        |")
    String transitionTimingFunction() default "	";

    /**
     * The **`translate`** CSS property allows you to specify translation transforms individually and independently of the `transform` property. This maps better to typical user interface usage, and saves having to remember the exact order of transform functions to specify in the `transform` value.\n\n**Syntax**: `none | <length-percentage> [ <length-percentage> <length>? ]?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |   No   | **72**  | **14.1** |  No  | No  |
     *
     * 参考定义: "#/definitions/Property.Translate%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"none"}]
     *
     * @see Property_Translate
     */
    @Const({"none"})
    @Schema(title = "The **`translate`** CSS property allows you to specify translation transforms individually and independently of the `transform` property. This maps better to typical user interface usage, and saves having to remember the exact order of transform functions to specify in the `transform` value.\n\n**Syntax**: `none | <length-percentage> [ <length-percentage> <length>? ]?`\n\n**Initial value**: `none`\n\n| Chrome | Firefox |  Safari  | Edge | IE  | | :----: | :-----: | :------: | :--: | :-: | |   No   | **72**  | **14.1** |  No  | No  |")
    String translate() default "	";

    /**
     * The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox |   Safari    |   Edge   |      IE      | | :-----: | :-----: | :---------: | :------: | :----------: | | **54**  | **69**  | **3** _-x-_ |  **79**  | **10** _-x-_ | | 1 _-x-_ | 1 _-x-_ |             | 12 _-x-_ |              |
     *
     * 参考定义: "#/definitions/Property.UserSelect"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"-moz-none"},{"type":"string","const":"all"},{"type":"string","const":"auto"},{"type":"string","const":"contain"},{"type":"string","const":"element"},{"type":"string","const":"none"},{"type":"string","const":"text"}]
     *
     * @see Property_UserSelect
     */
    @Const({"-moz-none", "all", "auto", "contain", "element", "none", "text"})
    @Schema(title = "The `**user-select**` CSS property controls whether the user can select text. This doesn't have any effect on content loaded as chrome, except in textboxes.\n\n**Syntax**: `auto | text | none | contain | all`\n\n**Initial value**: `auto`\n\n| Chrome  | Firefox |   Safari    |   Edge   |      IE      | | :-----: | :-----: | :---------: | :------: | :----------: | | **54**  | **69**  | **3** _-x-_ |  **79**  | **10** _-x-_ | | 1 _-x-_ | 1 _-x-_ |             | 12 _-x-_ |              |")
    String userSelect() default "	";

    /**
     * The **`vertical-align`** CSS property sets vertical alignment of an inline, inline-block or table-cell box.\n\n**Syntax**: `baseline | sub | super | text-top | text-bottom | middle | top | bottom | <percentage> | <length>`\n\n**Initial value**: `baseline`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.VerticalAlign%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"baseline"},{"type":"string","const":"bottom"},{"type":"string","const":"middle"},{"type":"string","const":"sub"},{"type":"string","const":"super"},{"type":"string","const":"text-bottom"},{"type":"string","const":"text-top"},{"type":"string","const":"top"}]
     *
     * @see Property_VerticalAlign
     */
    @Const({"baseline", "bottom", "middle", "sub", "super", "text-bottom", "text-top", "top"})
    @Schema(title = "The **`vertical-align`** CSS property sets vertical alignment of an inline, inline-block or table-cell box.\n\n**Syntax**: `baseline | sub | super | text-top | text-bottom | middle | top | bottom | <percentage> | <length>`\n\n**Initial value**: `baseline`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String verticalAlign() default "	";

    /**
     * The **`widows`** CSS property sets the minimum number of lines in a block container that must be shown at the _top_ of a page, region, or column.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `2`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **25** |   No    | **1.3** | **12** | **8** |
     *
     * 参考定义: "#/definitions/Property.Widows"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_Widows
     */

    @Schema(title = "The **`widows`** CSS property sets the minimum number of lines in a block container that must be shown at the _top_ of a page, region, or column.\n\n**Syntax**: `<integer>`\n\n**Initial value**: `2`\n\n| Chrome | Firefox | Safari  |  Edge  |  IE   | | :----: | :-----: | :-----: | :----: | :---: | | **25** |   No    | **1.3** | **12** | **8** |")
    String widows() default "	";

    /**
     * The **`width`** CSS property sets an element's width. By default, it sets the width of the content area, but if `box-sizing` is set to `border-box`, it sets the width of the border area.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.Width%3C(string%7Cnumber)%3E"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string"},{"type":"number"},{"type":"string","const":"-moz-fit-content"},{"type":"string","const":"-moz-max-content"},{"type":"string","const":"-moz-min-content"},{"type":"string","const":"-webkit-fit-content"},{"type":"string","const":"-webkit-max-content"},{"type":"string","const":"auto"},{"type":"string","const":"fit-content"},{"type":"string","const":"intrinsic"},{"type":"string","const":"max-content"},{"type":"string","const":"min-content"},{"type":"string","const":"min-intrinsic"}]
     *
     * @see Property_Width
     */
    @Const({"-moz-fit-content", "-moz-max-content", "-moz-min-content", "-webkit-fit-content", "-webkit-max-content", "auto", "fit-content", "intrinsic", "max-content", "min-content", "min-intrinsic"})
    @Schema(title = "The **`width`** CSS property sets an element's width. By default, it sets the width of the content area, but if `box-sizing` is set to `border-box`, it sets the width of the border area.\n\n**Syntax**: `auto | <length> | <percentage> | min-content | max-content | fit-content | fit-content(<length-percentage>)`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String width() default "	";

    /**
     * The **`will-change`** CSS property hints to browsers how an element is expected to change. Browsers may set up optimizations before an element is actually changed. These kinds of optimizations can increase the responsiveness of a page by doing potentially expensive work before they are actually required.\n\n**Syntax**: `auto | <animateable-feature>#`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **36** | **36**  | **9.1** | **79** | No  |
     *
     * 参考定义: "#/definitions/Property.WillChange"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"$ref":"#/definitions/DataType.AnimateableFeature"},{"type":"string","const":"auto"},{"type":"string"}]
     *
     * @see Property_WillChange
     */
    @Const({"auto"})
    @Schema(title = "The **`will-change`** CSS property hints to browsers how an element is expected to change. Browsers may set up optimizations before an element is actually changed. These kinds of optimizations can increase the responsiveness of a page by doing potentially expensive work before they are actually required.\n\n**Syntax**: `auto | <animateable-feature>#`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari  |  Edge  | IE  | | :----: | :-----: | :-----: | :----: | :-: | | **36** | **36**  | **9.1** | **79** | No  |")
    String willChange() default "	";

    /**
     * The **`word-break`** CSS property sets whether line breaks appear wherever the text would otherwise overflow its content box.\n\n**Syntax**: `normal | break-all | keep-all | break-word`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  | **15**  | **3**  | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.WordBreak"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"break-all"},{"type":"string","const":"break-word"},{"type":"string","const":"keep-all"},{"type":"string","const":"normal"}]
     *
     * @see Property_WordBreak
     */
    @Const({"break-all", "break-word", "keep-all", "normal"})
    @Schema(title = "The **`word-break`** CSS property sets whether line breaks appear wherever the text would otherwise overflow its content box.\n\n**Syntax**: `normal | break-all | keep-all | break-word`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari |  Edge  |   IE    | | :----: | :-----: | :----: | :----: | :-----: | | **1**  | **15**  | **3**  | **12** | **5.5** |")
    String wordBreak() default "	";

    /**
     * The `**overflow-wrap**` CSS property applies to inline elements, setting whether the browser should insert line breaks within an otherwise unbreakable string to prevent text from overflowing its line box.\n\n**Syntax**: `normal | break-word`\n\n**Initial value**: `normal`
     *
     * 参考定义: "#/definitions/Property.WordWrap"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"break-word"},{"type":"string","const":"normal"}]
     *
     * @see Property_WordWrap
     */
    @Const({"break-word", "normal"})
    @Schema(title = "The `**overflow-wrap**` CSS property applies to inline elements, setting whether the browser should insert line breaks within an otherwise unbreakable string to prevent text from overflowing its line box.\n\n**Syntax**: `normal | break-word`\n\n**Initial value**: `normal`")
    String wordWrap() default "	";

    /**
     * The **`z-index`** CSS property sets the z-order of a positioned element and its descendants or flex items. Overlapping elements with a larger z-index cover those with a smaller one.\n\n**Syntax**: `auto | <integer>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |
     *
     * 参考定义: "#/definitions/Property.ZIndex"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"auto"},{"type":"number"},{"type":"string"}]
     *
     * @see Property_ZIndex
     */
    @Const({"auto"})
    @Schema(title = "The **`z-index`** CSS property sets the z-order of a positioned element and its descendants or flex items. Overlapping elements with a larger z-index cover those with a smaller one.\n\n**Syntax**: `auto | <integer>`\n\n**Initial value**: `auto`\n\n| Chrome | Firefox | Safari |  Edge  |  IE   | | :----: | :-----: | :----: | :----: | :---: | | **1**  |  **1**  | **1**  | **12** | **4** |")
    String zIndex() default "	";

    /**
     * The non-standard **`zoom`** CSS property can be used to control the magnification level of an element. `transform: scale()` should be used instead of this property, if possible. However, unlike CSS Transforms, `zoom` affects the layout size of the element.\n\n**Syntax**: `normal | reset | <number> | <percentage>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE    | | :----: | :-----: | :-----: | :----: | :-----: | | **1**  |   No    | **3.1** | **12** | **5.5** |
     *
     * 参考定义: "#/definitions/Property.Zoom"
     *
     *
     *
     * [{"$ref":"#/definitions/Globals"},{"type":"string","const":"normal"},{"type":"string","const":"reset"},{"type":"string"},{"type":"number"}]
     *
     * @see Property_Zoom
     */
    @Const({"normal", "reset"})
    @Schema(title = "The non-standard **`zoom`** CSS property can be used to control the magnification level of an element. `transform: scale()` should be used instead of this property, if possible. However, unlike CSS Transforms, `zoom` affects the layout size of the element.\n\n**Syntax**: `normal | reset | <number> | <percentage>`\n\n**Initial value**: `normal`\n\n| Chrome | Firefox | Safari  |  Edge  |   IE    | | :----: | :-----: | :-----: | :----: | :-----: | | **1**  |   No    | **3.1** | **12** | **5.5** |")
    String zoom() default "	";

}
