package com.towels.graphofcontent.business;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.towels.graphofcontent.dao.GraphOfContentDAO;
import com.towels.graphofcontent.dao.LectureDAO;
import com.towels.graphofcontent.dao.NodeDAO;
import com.towels.graphofcontent.data.DirectedEdge;
import com.towels.graphofcontent.data.GraphOfContent;
import com.towels.graphofcontent.data.Node;
import com.towels.graphofcontent.dto.EdgeDTO;
import com.towels.graphofcontent.dto.GraphDTO;
import com.towels.graphofcontent.dto.NodeDTO;

@Stateless(name="GraphService")
public class GraphServiceBean {
	
	@EJB 
	private LectureDAO lectureDAO;
	
	@EJB
	private GraphOfContentDAO graphDAO;
	
	@EJB 
	private NodeDAO nodeDAO;
	
	
	public GraphDTO getGraph(Long lectureID) {
		GraphOfContent goc = graphDAO.findGraphOfContentByLectureID(lectureID);
		if(goc != null) {
			return buildGraphDTO(goc);
		} else {
			return null;
		}
	}
	
	private GraphDTO buildGraphDTO(GraphOfContent goc) {
		Set<Node> nodes = goc.getVertices();
		Set<DirectedEdge> edges = goc.getEdges();
		
		GraphDTO graphDTO = new GraphDTO();
		
		for(DirectedEdge edge : edges) {
			EdgeDTO edgeDTO = new EdgeDTO();
			edgeDTO.id = edge.getId().toString();
			edgeDTO.source = edge.getSource().getId().toString();
			edgeDTO.target = edge.getTarget().getId().toString();
			
			graphDTO.edges.add(edgeDTO);
		}
		
		for(Node node : nodes){
			NodeDTO nodeDTO = new NodeDTO();
			nodeDTO.id = node.getId().toString();
			nodeDTO.label = node.getTitle();
			nodeDTO.x = node.getX(); //TODO Where does the coordinates came from?
			nodeDTO.y = node.getY();
			nodeDTO.size = 1;
			nodeDTO.type = node.getNodetype().name();
			
			graphDTO.nodes.add(nodeDTO);
		}	
		
		return graphDTO;
	}
	
	public NodeDTO addNode(Long lectureID, NodeDTO nodeDTO){
		return null;
	}
	
}
