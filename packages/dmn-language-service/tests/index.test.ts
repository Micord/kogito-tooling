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

import { DmnDocumentData, DmnLanguageService } from "../src";
import { readFile } from "fs/promises";
import { readFileSync } from "fs";
import { resolve } from "path";
import { DmnDecision } from "../src/DmnDecision";

const tests = [
  { modelPath: "./fixtures/model.dmn", expected: ["recursive.dmn", "nested.dmn"] },
  { modelPath: "./fixtures/recursive.dmn", expected: ["nested.dmn"] },
  { modelPath: "./fixtures/nested.dmn", expected: [] },
];

describe("DmnLanguageService", () => {
  const service = new DmnLanguageService({
    getDmnImportedModel: () =>
      new Promise((res) =>
        res({
          relativePath: "",
          content: "",
        })
      ),
  });

  it("getImportedModelRelativePaths - empty string", () => {
    expect(service.getImportedModelRelativePaths("")).toEqual([]);
  });

  it("getImportedModelRelativePaths", () => {
    tests.forEach(({ modelPath, expected }) => {
      const path = resolve(__dirname, modelPath);
      const file = readFileSync(path, "utf8");
      expect(service.getImportedModelRelativePaths(file)).toEqual(expected);
    });
  });

  it("getImportedModelRelativePaths - multiple files", async () => {
    const files = (
      await Promise.all(
        tests.map(({ modelPath }) => {
          return readFile(resolve(__dirname, modelPath));
        })
      )
    ).map((e) => e.toString("utf8"));

    expect(service.getImportedModelRelativePaths(files)).toEqual(tests.flatMap((e) => e.expected));
  });

  it("getAllImportedModelsResources - empty", async () => {
    const service = new DmnLanguageService({
      getDmnImportedModel: () =>
        new Promise((res) =>
          res({
            relativePath: "",
            content: "",
          })
        ),
    });

    expect(await service.getAllImportedModelsResources([""])).toEqual([]);
  });

  it("getAllImportedModelsResources - get resources", async () => {
    const pathRecursive = resolve(__dirname, "./fixtures/recursive.dmn");
    const fileRecursive = readFileSync(pathRecursive, "utf8");

    const pathNested = resolve(__dirname, "./fixtures/nested.dmn");
    const fileNested = readFileSync(pathNested, "utf8");

    const expected = {
      relativePath: pathNested,
      content: fileNested,
    };

    const service = new DmnLanguageService({ getDmnImportedModel: () => new Promise((res) => res(expected)) });

    expect(await service.getAllImportedModelsResources([fileRecursive])).toEqual([expected]);
  });

  it("getDmnDocumentData - get decisions", async () => {
    const pathRecursive = resolve(__dirname, "./fixtures/decisions.dmn");
    const fileRecursive = readFileSync(pathRecursive, "utf8");

    const pathNested = resolve(__dirname, "./fixtures/nested.dmn");
    const fileNested = readFileSync(pathNested, "utf8");

    const expected = {
      relativePath: pathNested,
      content: fileNested,
    };

    const dmnDocumentData: DmnDocumentData = new DmnDocumentData(
      "https://kiegroup.org/dmn/_57B8BED3-0077-4154-8435-30E57EA6F02E",
      "My Model Name",
      [new DmnDecision("Decision-1"), new DmnDecision("Decision-2"), new DmnDecision("Decision-3")]
    );
    const service = new DmnLanguageService({ getDmnImportedModel: () => new Promise((res) => res(expected)) });

    expect(service.getDmnDocumentData(fileRecursive)).toEqual(dmnDocumentData);
  });
});
