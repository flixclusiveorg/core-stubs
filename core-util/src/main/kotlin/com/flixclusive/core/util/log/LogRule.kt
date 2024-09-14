package com.flixclusive.core.util.log

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A JUnit [TestRule] that intercepts and prints log messages during tests.
 *
 * This rule utilizes MockK to mock the static methods of the [Log] class.
 * It intercepts calls to [Log.d] and [Log.e] and prints the log messages to the console.
 */
class LogRule : TestRule {
    /**
     * The rules to intercept and print log messages during tests.
     * */
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                mockkStatic(Log::class)
                every { Log.d(any(), any()) } answers {
                    println(args[1])
                    0
                }

                every { Log.e(any(), any()) } answers {
                    println(args[1])
                    0
                }

                base?.evaluate()
            }
        }
    }
}