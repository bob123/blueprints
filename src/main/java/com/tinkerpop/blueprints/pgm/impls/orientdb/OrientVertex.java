package com.tinkerpop.blueprints.pgm.impls.orientdb;

import com.orientechnologies.orient.core.db.graph.OGraphVertex;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.StringFactory;
import com.tinkerpop.blueprints.pgm.impls.orientdb.util.OrientElementSequence;

import java.util.Set;

/**
 * @author Luca Garulli (http://www.orientechnologies.com)
 */
public class OrientVertex extends OrientElement implements Vertex {

    public OrientVertex(final OrientGraph iGraph) {
        super(iGraph, new OGraphVertex(iGraph.getRawGraph()));
        this.raw.save();
    }

    public OrientVertex(final OrientGraph iGraph, final OGraphVertex vertex) {
        super(iGraph, vertex);
    }

    public Iterable<Edge> getOutEdges() {
        return new OrientElementSequence<Edge>(graph, ((OGraphVertex) this.raw).getOutEdges().iterator());
    }

    public Iterable<Edge> getInEdges() {
        return new OrientElementSequence<Edge>(graph, ((OGraphVertex) this.raw).getInEdges().iterator());
    }

    public Set<String> getPropertyKeys() {
        final Set<String> set = super.getPropertyKeys();
        set.remove(OGraphVertex.FIELD_IN_EDGES);
        set.remove(OGraphVertex.FIELD_OUT_EDGES);
        return set;
    }

    public OGraphVertex getRawVertex() {
        return (OGraphVertex) this.raw;
    }

    public String toString() {
        return StringFactory.vertexString(this);
    }

}
