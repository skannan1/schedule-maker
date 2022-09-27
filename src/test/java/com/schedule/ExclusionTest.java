package com.schedule;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExclusionTest {
    private Exclusion excl1;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    @Before
    public void setUp() {
        try {
            Date cal = sdf.parse("10/08/2022");
            excl1 = new Exclusion(cal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExclusion(){
        try {
            Date cal = sdf.parse("10/08/2022");
            assertEquals(true,excl1.shouldExclude(cal));
            cal = sdf.parse("10/09/2022");
            assertEquals(false,excl1.shouldExclude(cal));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
