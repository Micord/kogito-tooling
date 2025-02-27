/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.bpmn.client.dataproviders;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.stunner.bpmn.BPMNDefinitionSet;
import org.kie.workbench.common.stunner.bpmn.forms.dataproviders.ProcessDataEvent;
import org.kie.workbench.common.stunner.forms.client.session.StunnerFormsHandler;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessesDataProviderTest {

    @Mock
    private StunnerFormsHandler formsHandler;

    private ProcessesDataProvider tested;

    @Before
    public void setup() {
        tested = new ProcessesDataProvider(formsHandler);
    }

    @Test
    public void testOnProcessesUpdatedEvent() {
        ProcessDataEvent event = mock(ProcessDataEvent.class);
        when(event.getProcessIds()).thenReturn(new String[]{"p1", "p2"});
        tested.onProcessesUpdatedEvent(event);
        verify(formsHandler, times(1)).refreshCurrentSessionForms(eq(BPMNDefinitionSet.class));
        List<String> values = tested.getProcessIds();
        assertNotNull(values);
        assertEquals(2, values.size());
        assertTrue(values.contains("p1"));
        assertTrue(values.contains("p2"));
    }

    @Test
    public void testOnProcessesNOTUpdatedEvent() {
        tested.getProcessIds().add("p1");
        tested.getProcessIds().add("p2");
        ProcessDataEvent event = mock(ProcessDataEvent.class);
        when(event.getProcessIds()).thenReturn(new String[]{"p1", "p2"});
        tested.onProcessesUpdatedEvent(event);
        verify(formsHandler, never()).refreshCurrentSessionForms(any(Class.class));
    }
}
