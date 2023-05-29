/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
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
package org.kie.workbench.common.dmn.client.editors.expressions.jsinterop.props;

public class OutputClauseProps extends Clause {

    /**
     * <b>defaultOutputEntry</b>: Expression [0..1]
     * In an Incomplete table, this attribute lists an instance of Expression that is selected when no rules match
     * for the decision table, which is also an instance of LiteralExpression
     */
    public final ExpressionProps defaultOutputValue;

    public OutputClauseProps(final String id,
                             final String name,
                             final String dataType,
                             final Double width,
                             final ClauseUnaryTests unaryTests,
                             final ExpressionProps defaultOutputValue) {
        super(id, name, dataType, width, unaryTests);
        this.defaultOutputValue = defaultOutputValue;
    }
}
