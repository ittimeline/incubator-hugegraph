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

package org.apache.hugegraph.ct.config;

import static org.apache.hugegraph.ct.base.ClusterConstant.APPLICATION_FILE;
import static org.apache.hugegraph.ct.base.ClusterConstant.CONFIG_FILE_PATH;
import static org.apache.hugegraph.ct.base.ClusterConstant.LOCALHOST;
import static org.apache.hugegraph.ct.base.ClusterConstant.PD_TEMPLATE_FILE;
import static org.apache.hugegraph.ct.base.EnvUtil.getAvailablePort;

import java.nio.file.Paths;
import java.util.List;

import lombok.Getter;

@Getter
public class PDConfig extends AbstractConfig {

    private final int raftPort;
    private final int grpcPort;
    private final int restPort;

    public PDConfig() {
        readTemplate(Paths.get(CONFIG_FILE_PATH + PD_TEMPLATE_FILE));
        this.fileName = APPLICATION_FILE;
        this.raftPort = getAvailablePort();
        this.grpcPort = getAvailablePort();
        this.restPort = getAvailablePort();
        properties.put("GRPC_PORT", String.valueOf(this.grpcPort));
        properties.put("REST_PORT", String.valueOf(this.restPort));
        properties.put("RAFT_ADDRESS", LOCALHOST + ":" + this.raftPort);
    }

    public void setRaftPeerList(List<String> raftPeerList) {
        String raftPeers = String.join(",", raftPeerList);
        setProperty("RAFT_PEERS_LIST", raftPeers);
    }

    public void setStoreCount(int storeCount) {
        setProperty("STORE_COUNT", String.valueOf(storeCount));
    }

    public void setStoreGrpcList(List<String> storeGrpcList) {
        String storeGrpcLists = String.join(",", storeGrpcList);
        setProperty("STORE_GRPC_LIST", storeGrpcLists);
    }

    public String getRaftAddress() {
        return LOCALHOST + ":" + this.raftPort;
    }

    public String getGrpcAddress() {
        return LOCALHOST + ":" + this.grpcPort;
    }
}
