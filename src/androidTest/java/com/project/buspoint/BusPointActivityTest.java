package com.project.buspoint;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.project.buspoint.BusPointActivityTest \
 * com.project.buspoint.tests/android.test.InstrumentationTestRunner
 */
public class BusPointActivityTest extends ActivityInstrumentationTestCase2<BusPointActivity> {

    public BusPointActivityTest() {
        super("com.project.buspoint", BusPointActivity.class);
    }

}
