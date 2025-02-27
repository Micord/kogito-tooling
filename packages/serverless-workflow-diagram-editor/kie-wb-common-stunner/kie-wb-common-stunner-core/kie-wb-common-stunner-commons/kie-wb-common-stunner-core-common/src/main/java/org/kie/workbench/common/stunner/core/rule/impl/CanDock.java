/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.core.rule.impl;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jsinterop.annotations.JsType;

@JsType
public final class CanDock extends AbstractRule {

    private final String role;
    private final Set<String> allowedRoles;

    public static CanDock build(String name,
                                String role,
                                String[] allowedRoles) {

        return new CanDock(name,
                           role,
                           Arrays.stream(allowedRoles).collect(Collectors.toSet()));
    }

    public CanDock(String name,
                   String role,
                   Set<String> allowedRoles) {
        super(name);
        this.role = role;
        this.allowedRoles = allowedRoles;
    }

    public String getRole() {
        return role;
    }

    public Set<String> getAllowedRoles() {
        return allowedRoles;
    }
}
