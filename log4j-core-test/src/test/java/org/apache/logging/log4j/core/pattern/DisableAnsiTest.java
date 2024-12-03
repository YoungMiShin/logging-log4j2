/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.logging.log4j.core.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.test.appender.ListAppender;
import org.apache.logging.log4j.core.test.junit.LoggerContextSource;
import org.apache.logging.log4j.core.test.junit.Named;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@LoggerContextSource("log4j2-console-disableAnsi.xml")
class DisableAnsiTest {

    private static final String EXPECTED =
            "ERROR LoggerTest o.a.l.l.c.p.DisableAnsiTest org.apache.logging.log4j.core.pattern.DisableAnsiTest"
                    + Strings.LINE_SEPARATOR;

    private Logger logger;
    private ListAppender app;

    @BeforeEach
    void setUp(final LoggerContext context, @Named("List") final ListAppender app) {
        this.logger = context.getLogger("LoggerTest");
        this.app = app.clear();
    }

    @Test
    void testReplacement() {
        logger.error(this.getClass().getName());

        final List<String> msgs = app.getMessages();
        assertNotNull(msgs);
        assertEquals(1, msgs.size(), "Incorrect number of messages. Should be 1 is " + msgs.size());
        assertTrue(
                msgs.get(0).endsWith(EXPECTED),
                "Replacement failed - expected ending " + EXPECTED + ", actual " + msgs.get(0));
    }
}
