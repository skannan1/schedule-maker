package com.schedule;

import com.schedule.excl.Exclusion;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ExclusionTest {
    private Exclusion excl1;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    @Before
    public void setUp() {
            LocalDate cal = LocalDate.of(2022,10,8);
            excl1 = new Exclusion(cal);
    }

    @Test
    public void testExclusion(){
            LocalDate cal = LocalDate.of(2022,10,8);
            assertEquals(true,excl1.shouldExclude(cal));
            cal = LocalDate.of(2022,10,9);
            assertEquals(false,excl1.shouldExclude(cal));
    }
}
