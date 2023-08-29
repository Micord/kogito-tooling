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


package org.kie.workbench.common.stunner.client.widgets.palette.categories.group;

import org.kie.workbench.common.stunner.client.widgets.palette.BS3PaletteWidgetPresenter;
import org.kie.workbench.common.stunner.client.widgets.palette.categories.items.DefinitionPaletteItemWidget;
import org.kie.workbench.common.stunner.core.client.components.palette.DefaultPaletteGroup;
import org.uberfire.client.mvp.UberElement;

public interface DefinitionPaletteGroupWidgetView extends UberElement<DefinitionPaletteGroupWidgetView.Presenter> {

    void addItem(DefinitionPaletteItemWidget categoryItem);

    void addAnchors();

    void showMoreAnchor();

    void showLessAnchor();

    interface Presenter extends BS3PaletteWidgetPresenter<DefaultPaletteGroup> {

        void showMore();

        void showLess();

        DefaultPaletteGroup getItem();
    }

    void initView();
}
