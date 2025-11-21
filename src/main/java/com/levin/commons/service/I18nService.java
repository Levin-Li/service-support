package com.levin.commons.service;


import com.levin.commons.service.domain.I18nEnum;

public interface I18nService {

    String getLabel(I18nEnum i18nEnum, String lang);

}
