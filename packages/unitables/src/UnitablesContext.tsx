/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import * as React from "react";
import { useContext, useCallback } from "react";
import { UnitablesRowApi } from "./UnitablesRow";

export interface UnitablesContextType {
  isBeeTableChange: React.MutableRefObject<boolean>;
  rowsRefs: Map<number, UnitablesRowApi>;
  rowsInputs: Array<Record<string, any>>;
}

export const UnitablesContext = React.createContext<UnitablesContextType>({} as any);

export function useUnitablesContext() {
  return useContext(UnitablesContext);
}

export function useUnitablesRow(rowIndex: number) {
  const { rowsRefs, rowsInputs } = useUnitablesContext();

  const submitRow = useCallback(async () => {
    rowsRefs.get(rowIndex)?.submit();
  }, [rowsRefs, rowIndex]);

  return { submitRow, rowInputs: rowsInputs[rowIndex] };
}
