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
import * as React from "react";

import { ChartBar, ChartGroup } from "@patternfly/react-charts";
import { XYChart } from "./XYChart";

export class BarChart extends XYChart {
  buildChartGroup(): any {
    return (
      <ChartGroup
        offset={(this.props.barWidth || 0) + (this.props.barOffset || 10)}
        horizontal={this.props.horizontalBars}
      >
        {this.dataSetToXYData()
          .map((line) => {
            return this.seriesLines(line);
          })
          .map((lineData, i) => {
            return <ChartBar key={i} data={lineData} y={(d) => d.yVal} barWidth={this.props.barWidth} />;
          })}
      </ChartGroup>
    );
  }
}
