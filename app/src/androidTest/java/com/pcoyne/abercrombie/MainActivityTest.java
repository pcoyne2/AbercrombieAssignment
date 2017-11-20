package com.pcoyne.abercrombie;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by Patrick Coyne on 11/20/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends InstrumentationTestCase{

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testResponse() throws Exception{

    }

}