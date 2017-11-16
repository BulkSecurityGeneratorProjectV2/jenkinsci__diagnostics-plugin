/*
 * The MIT License
 *
 * Copyright (c) 2016, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.diagnostics.diagnostics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Nonnull;

import org.apache.commons.lang.math.RandomUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

/**
 * Test diagnostic.
 * 
 */
public class TestDiagnostic1 extends TestDiagnostic {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance
     */
    public TestDiagnostic1() {
        super();
    }

    /**
     * Creates an instance
     * 
     * @param initialDelay number milliseconds to delay the first execution
     * @param period period to wait between successive executions in milliseconds
     * @param runs number of times this diagnostic should be executed on the diagnostic session
     * @param hostname to execute the test
     */
    @DataBoundConstructor
    public TestDiagnostic1(int initialDelay, int period, int runs) {
        super(initialDelay, period, runs);
    }

    @Override
    public String getFileName() {
        return getClass().getName();
    }

    @Override
    public void runDiagnostic(@Nonnull PrintWriter out, int run) throws IOException {
        out.println("=== Result of TestDiagnostic1 run:[" + run + "] date:[" + new Date() + "] ===");
        runsFinished++;
        try {
            Thread.sleep(RandomUtils.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace(out);
        }
    }

    /**
     * Our descriptor.
     */
    @Extension
    public static class DescriptorImpl extends DiagnosticDescriptor<TestDiagnostic1> {
        @Override
        public String getDisplayName() {
            return "TestDiagnostic1";
        }

        @Override
        public boolean isSelectedByDefault() {
            return false;
        }

        @Override
        public int getInitialDelay() {
            return 0;
        }

        @Override
        public int getRuns() {
            return 10;
        }

        @Override
        public int getPeriod() {
            return 100;
        }

    }
}
