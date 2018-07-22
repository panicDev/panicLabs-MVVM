package id.panicLabs.core

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        Assert.assertEquals("com.kimboo.core.test", appContext.getPackageName())
    }
}
