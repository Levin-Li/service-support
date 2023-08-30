package com.levin.commons.service.support;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 匹配配置
 */
@Data
@Accessors(chain = true)
@FieldNameConstants
@ToString
public class MatchConfig
        implements Serializable {

    protected final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 配置名称
     */
    protected final String key;

    /**
     * 配置名称
     */
    protected final String name;

    /**
     * 描述
     */
    protected final String description;

    /**
     * 是否启用
     */
    protected boolean enable = true;

    /**
     * 排除的类包，startsWith 匹配
     */
    protected List<String> excludePackages = Collections.emptyList();

    /**
     * 排除的 URL ，Ant path 匹配
     */
    protected List<String> excludePathPatterns = Collections.emptyList();

    /**
     * 包含的类包，startsWith 匹配
     */
    protected List<String> includePackages = Collections.emptyList();

    /**
     * 包含的 URL ，Ant path 匹配
     */
    protected List<String> includePathPatterns = Collections.emptyList();

    protected MatchConfig(String key, String name, String description) {
        this.key = key;
        this.name = name;
        this.description = description;
    }

    /**
     * Path是否匹配
     *
     * @param path
     * @return
     */
    public boolean isPathMatched(String path) {
        return enable
                //没有被排除
                && excludePathPatterns.stream().filter(StringUtils::hasText).noneMatch(p -> antPathMatcher.match(p, path))
                //并且被包含
                && (
                includePathPatterns.stream().noneMatch(StringUtils::hasText)
                        || includePathPatterns.stream().filter(StringUtils::hasText).anyMatch(p -> antPathMatcher.match(p, path))
        );
    }

    /**
     * 包是否匹配
     *
     * @param packageName
     * @return
     */
    public boolean isPackageMatched(String packageName) {
        return enable
                //没有被排除
                && excludePackages.stream().filter(StringUtils::hasText).noneMatch(p -> packageName.startsWith(p))
                //并且被包含
                && (
                includePackages.stream().noneMatch(StringUtils::hasText)
                        || includePackages.stream().filter(StringUtils::hasText).anyMatch(p -> packageName.startsWith(p)
                )
        );
    }

    /**
     * 检查 包名和路径 是否都匹配
     *
     * @param packageName null或者空字符将忽略匹配
     * @param path        null或者空字符将忽略匹配
     * @return
     */
    public boolean isMatched(String packageName, String path) {
        return enable
                && (!StringUtils.hasText(packageName) || isPackageMatched(packageName))
                && (!StringUtils.hasText(path) || isPathMatched(path));
    }

}
