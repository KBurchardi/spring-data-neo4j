/**
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.neo4j.rest;


import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.springframework.data.neo4j.support.query.QueryEngine;

import java.util.Map;


public class SpringRestCypherQueryEngine implements QueryEngine<Map<String,Object>> {

    org.neo4j.rest.graphdb.query.RestCypherQueryEngine restCypherQueryEngine;

    public SpringRestCypherQueryEngine(RestCypherQueryEngine restCypherQueryEngine) {
        this.restCypherQueryEngine = restCypherQueryEngine;
    }

    @Override
    public SpringRestQueryResult<Map<String,Object>> query(String statement, Map<String, Object> params) {
        return new SpringRestQueryResult<Map<String, Object>>(restCypherQueryEngine.query(statement, params));
    }

}
