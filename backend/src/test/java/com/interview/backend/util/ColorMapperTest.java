package com.interview.backend.util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorMapperTest {

    @ParameterizedTest
    @CsvSource({
            "1, blau",
            "2, grün",
            "3, violett",
            "7, weiß",
            "99, unbekannt"
    })
    void mapColor_ShouldReturnCorrectColor(String input, String expected) {
        assertEquals(expected, ColorMapper.mapColor(input));
    }
}