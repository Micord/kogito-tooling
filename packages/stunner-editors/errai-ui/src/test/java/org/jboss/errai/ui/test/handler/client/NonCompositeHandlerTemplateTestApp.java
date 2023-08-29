/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License. 
 */


package org.jboss.errai.ui.test.handler.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.ui.test.handler.client.res.HandledComponent;
import org.jboss.errai.ui.test.handler.client.res.NonCompositeHandledComponent;

@Dependent
public class NonCompositeHandlerTemplateTestApp implements HandlerTemplateTestApp {

  @Inject
  private RootPanel root;

  @Inject
  private NonCompositeHandledComponent component;

  @PostConstruct
  public void setup() {
    root.getElement().appendChild(component.getRoot());
  }

  @PreDestroy
  public void tearDown() {
    root.clear();
  }

  @Override
  public HandledComponent getComponent() {
    return component;
  }
}
