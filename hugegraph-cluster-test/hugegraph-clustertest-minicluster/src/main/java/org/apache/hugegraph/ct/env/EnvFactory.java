/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hugegraph.ct.env;

import org.apache.hugegraph.ct.base.EnvType;
import org.apache.hugegraph.ct.base.HGTestLogger;
import org.slf4j.Logger;

public class EnvFactory {

    private static final Logger LOG = HGTestLogger.ENV_LOG;
    private static BaseEnv env;

    public static BaseEnv getEnv() {
        if (env == null) {
            EnvType envType = EnvType.getSystemEnvType();
            switch (envType) {
                case SingleNode:
                    env = new SimpleEnv();
                    break;
                case MultiNode:
                    env = new MultiNodeEnv();
                    break;
                default:
                    LOG.error("No such env type: {}", envType);
            }
        }
        return env;
    }

}
