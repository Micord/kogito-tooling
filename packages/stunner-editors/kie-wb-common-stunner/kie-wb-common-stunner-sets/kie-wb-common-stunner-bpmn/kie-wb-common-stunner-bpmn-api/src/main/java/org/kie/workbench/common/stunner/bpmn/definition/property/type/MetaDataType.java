/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.bpmn.definition.property.type;

import java.util.Objects;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.workbench.common.stunner.core.definition.property.PropertyType;

@Portable
public class MetaDataType implements PropertyType {

    public static final String NAME = "stunner.bpmn.metaData";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MetaDataType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(NAME);
    }

    @Override
    public String toString() {
        return "MetadataType{" +
                "name='" + NAME + '\'' +
                '}';
    }
}