/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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

const { varsWithName, getOrDefault, composeEnv } = require("@kie-tools-scripts/build-env");

module.exports = composeEnv(
  [require("@kie-tools/root-env/env"), require("@kie-tools/serverless-logic-web-tools-swf-builder-image-env/env")],
  {
    vars: varsWithName({
      SERVERLESS_LOGIC_WEB_TOOLS__swfBuilderImageBuildTags: {
        default: "latest",
        description: "",
      },
      SERVERLESS_LOGIC_WEB_TOOLS__swfBuilderKogitoImageTag: {
        default: "1.41",
        description: "",
      },
    }),
    get env() {
      return {
        swfBuilderImage: {
          buildTags: getOrDefault(this.vars.SERVERLESS_LOGIC_WEB_TOOLS__swfBuilderImageBuildTags),
          kogitoImageTag: getOrDefault(this.vars.SERVERLESS_LOGIC_WEB_TOOLS__swfBuilderKogitoImageTag),
        },
      };
    },
  }
);
