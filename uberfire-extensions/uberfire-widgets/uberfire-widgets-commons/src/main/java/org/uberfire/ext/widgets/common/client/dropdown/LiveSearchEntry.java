/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.widgets.common.client.dropdown;

public class LiveSearchEntry<TYPE> {

    private TYPE key;
    private String value;

    public LiveSearchEntry(TYPE key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(TYPE key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TYPE getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
