package com.towels.graphofcontent.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.towels.graphofcontent.exceptions.NodeNotFoundGraphException;


@Entity
@Table(name="GraphOfContent")
public class GraphOfContent implements Serializable {

	private static final long serialVersionUID = 403156608270084939L;

	private static final String NULL_VERTEX = "Null-Vertex not allowed.";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Node> vertices = new HashSet<Node>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<DirectedEdge> edges = new HashSet<DirectedEdge>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DirectedEdge addEdge(Node sourceVertex, Node targetVertex) {
		if( sourceVertex != null && targetVertex != null) {
			DirectedEdge edge = new DirectedEdge();
			edge.setSource(sourceVertex);
			edge.setTarget(targetVertex);
			if(edges.add(edge)) {
				return edge;
			} else {
				return null;
			}
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}

	public boolean addEdge(DirectedEdge e) {
		if(e != null && vertices.contains(e.getSource()) && vertices.contains(e.getTarget())){
			edges.add(e);
			return true;
		} else {
			return false;
		}
	}

	public boolean addVertex(Node v) {
		if(v != null) {
			return vertices.add(v);
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}

	public boolean containsEdge(Node sourceVertex, Node targetVertex) {
		if(sourceVertex != null && targetVertex != null){
			DirectedEdge edge = new DirectedEdge(sourceVertex, targetVertex);
			return edges.contains(edge);
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}

	public boolean containsEdge(DirectedEdge e) {
		return edges.contains(e);
	}

	public boolean containsVertex(Node v) {
		return vertices.contains(v);
	}

	public Set<DirectedEdge> getEdges() {
		return edges;
	}

	public Set<DirectedEdge> edgesOf(Node vertex) {
		if(vertex != null){
			Set<DirectedEdge> edgesOf = new HashSet<DirectedEdge>();
			for(DirectedEdge e : edges){
				if(e.getSource().equals(vertex) || e.getTarget().equals(vertex)){
					edgesOf.add(e);
				}
			}
			return edgesOf;
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}

	public boolean removeAllEdges(Collection<? extends DirectedEdge> c) {
		return this.edges.removeAll(c);
	}

	public boolean removeAllVertices(Collection<? extends Node> c) {
		boolean changed = false;
		for(Node v : c){
			changed |= removeVertex(v);
		}
		return changed;
	}

	public DirectedEdge removeEdge(Node sourceVertex, Node targetVertex) {
		if(sourceVertex != null && targetVertex != null){
			DirectedEdge edge = new DirectedEdge(sourceVertex, targetVertex);
			if(edges.remove(edge)) {
				return edge;
			} else {
				return null;
			}
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}

	public boolean removeEdge(DirectedEdge e) {
		return edges.remove(e);
	}

	public boolean removeVertex(Node v) {
		if(containsVertex(v)){
			removeAllEdges(outgoingEdgesOf(v));
			removeAllEdges(incomingEdgesOf(v));
			vertices.remove(v);
			return true;
		} else {
			return false;
		}
	}

	public Set<Node> getVertices() {
		return vertices;
	}

	public Node getEdgeSource(DirectedEdge e) {
		if(containsVertex(e.getSource())) {
			return e.getSource();
		} else {
			throw new NodeNotFoundGraphException();
		}
	}

	public Node getEdgeTarget(DirectedEdge e) {
		if(containsVertex(e.getSource())) {
			return e.getTarget();
		} else {
			throw new NodeNotFoundGraphException();
		}
	}

	public int inDegreeOf(Node vertex) {
		return incomingEdgesOf(vertex).size();
	}

	public Set<DirectedEdge> incomingEdgesOf(Node vertex) {
		if(vertex != null){
			if(vertices.contains(vertex)){
				Set<DirectedEdge> incoming = new HashSet<DirectedEdge>();
				for(DirectedEdge e : edges) {
					if(e.getTarget().equals(vertex)){
						incoming.add(e);
					}
				}
				return incoming;
			} else {
				throw new NodeNotFoundGraphException();
			}
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}

	public int outDegreeOf(Node vertex) {
		return outgoingEdgesOf(vertex).size();
	}

	public Set<DirectedEdge> outgoingEdgesOf(Node vertex) {
		if(vertex != null){
			if(vertices.contains(vertex)){
				Set<DirectedEdge> outgoing = new HashSet<DirectedEdge>();
				for(DirectedEdge e : edges) {
					if(e.getSource().equals(vertex)){
						outgoing.add(e);
					}
				}
				return outgoing;
			} else {
				throw new NodeNotFoundGraphException();
			}
		} else {
			throw new NullPointerException(NULL_VERTEX);
		}
	}	
	
}