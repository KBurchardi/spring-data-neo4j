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
package org.springframework.data.neo4j.fieldaccess;

import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.helpers.collection.IterableWrapper;
import org.springframework.data.neo4j.support.GraphDatabaseContext;

/**
 * Simple wrapper to create an Iterable over @NodeEntities or @RelationshipEntities from an iterable over Nodes or Relationships.
 * Creates NodeEntities on the fly while iterating the Iterator from original iterable.
 */
public class GraphBackedEntityIterableWrapper<STATE extends PropertyContainer, ENTITY> extends IterableWrapper<ENTITY, STATE> {
    private final Class<ENTITY> targetType;
    private final GraphDatabaseContext graphDatabaseContext;

    public GraphBackedEntityIterableWrapper(Iterable<STATE> iterable, Class<ENTITY> targetType, final GraphDatabaseContext graphDatabaseContext) {
        super(iterable);
        this.targetType = targetType;
        this.graphDatabaseContext = graphDatabaseContext;
    }

    @Override
    protected ENTITY underlyingObjectToObject(STATE s) {
        return graphDatabaseContext.createEntityFromState(s, targetType);
    }

    public static <S extends PropertyContainer, E> GraphBackedEntityIterableWrapper<S, E> create(
            Iterable<S> iterable, Class<E> targetType, final GraphDatabaseContext graphDatabaseContext) {
        return new GraphBackedEntityIterableWrapper<S, E>(iterable, targetType, graphDatabaseContext);
    }
}