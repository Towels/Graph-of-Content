package com.towels.graphofcontent.business;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.PersistenceContext;

import com.towels.graphofcontent.dao.GraphOfContentDAO;
import com.towels.graphofcontent.dao.LectureDAO;
import com.towels.graphofcontent.dao.NodeDAO;
import com.towels.graphofcontent.data.DirectedEdge;
import com.towels.graphofcontent.data.GraphOfContent;
import com.towels.graphofcontent.data.Node;
import com.towels.graphofcontent.dto.EdgeDTO;
import com.towels.graphofcontent.dto.GraphDTO;
import com.towels.graphofcontent.dto.NodeDTO;
import com.towels.graphofcontent.util.NodeType;

@Stateless(name="GraphService")
public class GraphServiceBean {
	Logger logger = Logger.getLogger(GraphServiceBean.class.getCanonicalName());
	@EJB 
	private LectureDAO lectureDAO;
	
	@EJB
	private GraphOfContentDAO graphDAO;
	
	@EJB 
	private NodeDAO nodeDAO;
	
	@PersistenceContext
	EntityManager em;
	
	public GraphDTO getGraph(Long lectureID) {
		GraphOfContent goc = graphDAO.findGraphOfContentByLectureID(lectureID);
		if(goc != null) {
			return buildGraphDTO(goc);
		} else {
			return null;
		}
	}
	
	public boolean addNode(Long lectureID, NodeDTO nodeDTO){
		GraphOfContent goc = graphDAO.findGraphOfContentByLectureID(lectureID);
		Node newNode = parseNodeDTO(nodeDTO);
		if(newNode != null && newNode.getId() == null) {
			goc.addVertex(newNode);
			graphDAO.update(goc);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addEdge(Long lectureID, EdgeDTO edgeDTO){
		GraphOfContent goc = graphDAO.findGraphOfContentByLectureID(lectureID);
		DirectedEdge newEdge = parseEdgeDTO(edgeDTO);
		if(newEdge != null && newEdge.getId() == null) {
			goc.addEdge(newEdge);
			graphDAO.update(goc);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * DTO Builder
	 */
	private GraphDTO buildGraphDTO(GraphOfContent goc) {
		Set<Node> nodes = goc.getVertices();
		Set<DirectedEdge> edges = goc.getEdges();
		
		GraphDTO graphDTO = new GraphDTO();
		
		for(DirectedEdge edge : edges) {
			EdgeDTO edgeDTO = buildEdgeDTO(edge);
			
			graphDTO.edges.add(edgeDTO);
		}
		
		for(Node node : nodes){
			NodeDTO nodeDTO = buildNodeDTO(node);
			graphDTO.nodes.add(nodeDTO);
		}	
		
		return graphDTO;
	}
	
	private NodeDTO buildNodeDTO(Node node) {
		NodeDTO nodeDTO = new NodeDTO();
		nodeDTO.id = node.getId().toString();
		nodeDTO.label = node.getTitle();
		nodeDTO.x = node.getX(); //TODO Where does the coordinates came from?
		nodeDTO.y = node.getY();
		nodeDTO.type = node.getNodetype().name();
		return nodeDTO;
	}
	
	private EdgeDTO buildEdgeDTO(DirectedEdge edge) {
		EdgeDTO edgeDTO = new EdgeDTO();
		edgeDTO.id = edge.getId().toString();
		edgeDTO.source = edge.getSource().getId().toString();
		edgeDTO.target = edge.getTarget().getId().toString();
		return edgeDTO;
	}
	/*
	 * DTO Parser
	 */
	private Node parseNodeDTO(NodeDTO nodeDTO){
		Node node = null;
		try{
			node = new Node();
			if(nodeDTO.id != null && !nodeDTO.id.isEmpty()){
				node.setId(Long.parseLong(nodeDTO.id));
			}
			node.setX(nodeDTO.x);
			node.setY(nodeDTO.y);
			node.setNodetype(NodeType.valueOf(nodeDTO.type));
			node.setTitle(nodeDTO.label);
		} catch(IllegalArgumentException e) {
			logger.log(Level.WARNING, "Exception while DTO-Parsing", e);
		}
		return node;
	}
	
	private DirectedEdge parseEdgeDTO(EdgeDTO edgeDTO){
		DirectedEdge edge = null;
		try{
			edge = new DirectedEdge();
			if(edgeDTO.id != null && !edgeDTO.id.isEmpty()){
				edge.setId(Long.parseLong(edgeDTO.id));
			}
			Long sourceID = Long.parseLong(edgeDTO.source);
			Long targetID = Long.parseLong(edgeDTO.target);
			Node source = nodeDAO.findNodeById(sourceID);
			Node target = nodeDAO.findNodeById(targetID);
			if(source != null && target != null){
				edge.setSource(source);
				edge.setTarget(target);
			} else {
				logger.log(Level.WARNING, "Source(id="+sourceID+") or Target(id="+targetID+") not found!");
				return null;
			}
		} catch(IllegalArgumentException e) {
			logger.log(Level.WARNING, "Exception while DTO-Parsing", e);
		}
		return edge;
	}
}
