/*******************************************************************************
 * Copyright 2013-2018 QaProSoft (http://www.qaprosoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.qaprosoft.carina.core.foundation.resources;

import com.qaprosoft.carina.core.foundation.commons.SpecialKeywords;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.resources.L10N;
import com.qaprosoft.carina.core.foundation.utils.resources.I18N;
import com.qaprosoft.carina.core.foundation.utils.resources.L10Nparser;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

public class L10nTest {

    private L10nTest() {
        try {
            L10N.init();
        } catch (Exception e) {
            LOGGER.error("L10N bundle is not initialized successfully!", e);
        }

        try {
            I18N .init();
        } catch (Exception e) {
            LOGGER.error("I18N bundle is not initialized successfully!", e);
        }

        try {
            L10Nparser.init();
        } catch (Exception e) {
            LOGGER.error("L10Nparser bundle is not initialized successfully!", e);
        }
    }

    protected static final Logger LOGGER = Logger.getLogger(L10nTest.class);

    private static final String MESSAGE_EN_US = "Test information";

    private static final String MESSAGE_RU_RU = "Тестовая информация";

    private static final String DEFAULT_L10N_LANG = "en_US";


    @Test
    public void testL10nBase() {

        Locale defaultLocale = L10N.getDefaultLocale();

        LOGGER.info("DefaultLocale in Test=" + defaultLocale);

        Assert.assertEquals(defaultLocale, Locale.US, "Default locale should be en_US");

        L10N.init();

        Assert.assertFalse(L10N.isUTF8(), "Property 'l10n_encoding' value should not be UTF-8 by default.");

        String msgEn = L10N.getText("testMessage");

        LOGGER.info(msgEn);

        Assert.assertEquals(msgEn, MESSAGE_EN_US);

    }

    @Test
    public void testL10nUTF8() {

        String countryCode = "ru_RU";

        LOGGER.info("countryCode=" + countryCode);

        Locale countryLoc = LocaleUtils.toLocale(countryCode);

        L10Nparser.setActualLocale(countryLoc, true);

        Locale actualLocale = L10Nparser.getActualLocale();

        Assert.assertEquals(actualLocale, countryLoc, "Actual locale should be ru_RU");

        LOGGER.info("actualLocale in Test=" + actualLocale);

        R.CONFIG.put(Configuration.Parameter.L10N_ENCODING.getKey(), SpecialKeywords.L10N_UTF8_ENCODING);

        L10N.init();

        Assert.assertEquals(L10N.getDefaultLocale(), countryLoc, "Default updated locale should be ru_RU");

        Assert.assertTrue(L10N.isUTF8(), "Property 'l10n_encoding' value should be set to UTF-8.");

        LOGGER.info("Localized text:" + L10N.getText("testMessage"));

        Assert.assertTrue(L10Nparser.checkLocalizationText( MESSAGE_RU_RU,"testMessage", false));

        R.CONFIG.put(Configuration.Parameter.L10N_ENCODING.getKey(), SpecialKeywords.L10N_ISO8859_1_ENCODING);

        L10Nparser.setActualLocale(LocaleUtils.toLocale(DEFAULT_L10N_LANG), true);
    }

    @Test
    public void testL10nUTF8andISOcompare() {

        String countryCode = "jp_JP";

        LOGGER.info("countryCode=" + countryCode);

        Locale countryLoc = LocaleUtils.toLocale(countryCode);

        L10Nparser.setActualLocale(countryLoc, true);

        Locale actualLocale = L10Nparser.getActualLocale();

        Assert.assertEquals(actualLocale, countryLoc, "Actual locale should be jp_JP");

        R.CONFIG.put(Configuration.Parameter.L10N_ENCODING.getKey(), SpecialKeywords.L10N_ISO8859_1_ENCODING);

        LOGGER.info("actualLocale in Test=" + actualLocale);

        Assert.assertFalse(L10N.isUTF8(), "Property 'l10n_encoding' value should not be UTF-8 by default.");

        L10N.init();

        String msgIso = L10N.getText("testMessage");

        LOGGER.info("Localized ISO-8859-1 text:" + msgIso);

        R.CONFIG.put(Configuration.Parameter.L10N_ENCODING.getKey(), SpecialKeywords.L10N_UTF8_ENCODING);

        Assert.assertEquals(L10N.getDefaultLocale(), countryLoc, "Default updated locale should be ru_RU");

        Assert.assertTrue(L10N.isUTF8(), "Property 'l10n_encoding' value should be set to UTF-8.");

        String msgUTF= L10N.getText("testTextUTF8");

        LOGGER.info("Localized UTF-8 text:" + msgUTF);

        Assert.assertEquals(msgIso, msgUTF);

        R.CONFIG.put(Configuration.Parameter.L10N_ENCODING.getKey(), SpecialKeywords.L10N_ISO8859_1_ENCODING);

        L10Nparser.setActualLocale(LocaleUtils.toLocale(DEFAULT_L10N_LANG), true);
    }




}