package id.bagus.m2_myunittesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import id.bagus.m2_myunittesting.ui.main.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityEspressoTest {
    private val dummyVolume = "504.0"
    private val dummyCircumference = "100.0"
    private val dummySurfaceArea = "396.0"
    private val dummyLength = "12.0"
    private val dummyWidth = "7.0"
    private val dummyHeight = "6.0"
    private val emptyInput = ""
    private val fieldEmpty ="Field ini tidak boleh kosong"

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetCircumference() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_calculate_circumference)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_circumference)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)))
    }

    @Test
    fun assertGetSurfaceArea(){
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength),closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth),closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight),closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_calculate_surface_area)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_surface_area)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummySurfaceArea)))
    }

    @Test
    fun assertGetVolume(){
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength),closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth),closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight),closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_calculate_volume)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calculate_volume)).perform(click())
        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummyVolume)))
    }

    @Test
    fun assertEmptyInput(){
        onView(withId(R.id.edt_length)).perform(typeText(emptyInput),closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength),closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(emptyInput),closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_width)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth),closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(emptyInput),closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.edt_height)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight),closeSoftKeyboard())
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
    }

}