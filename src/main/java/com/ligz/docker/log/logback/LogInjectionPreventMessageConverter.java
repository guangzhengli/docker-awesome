package com.ligz.docker.log.logback;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.ligz.docker.log.masking.LogMasker;
import com.ligz.docker.log.masking.PatternLogMasker;
import com.ligz.docker.log.masking.loader.EnumPatternLoader;
import com.ligz.docker.log.masking.loader.PatternLoader;

public class LogInjectionPreventMessageConverter extends MessageConverter {

    private final LogMasker logMasker;

    public LogInjectionPreventMessageConverter() {
        super();
        PatternLoader patternLoader = new EnumPatternLoader();
        logMasker = new PatternLogMasker(patternLoader);
    }

    @Override
    public String convert(ILoggingEvent event) {
        return replaceUnsafeLogCharacters(super.convert(event));
    }

    private String replaceUnsafeLogCharacters(String message) {
        return logMasker.mask(message);
    }
}
