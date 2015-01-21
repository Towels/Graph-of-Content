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
			return buildDTO(goc);
		} else {
			return null;
		}
	}
	
	private GraphDTO buildDTO(GraphOfContent goc) {
		Set<Node> nodes = goc.getVertices();
		Set<DirectedEdge> edges = goc.getEdges();
		
		GraphDTO graphDTO = new GraphDTO();
		
		for(DirectedEdge edge : edges) {
			EdgeDTO edgeDTO = new EdgeDTO();
			edgeDTO.id = edge.getId();
			edgeDTO.source = edge.getSource().getId();
			edgeDTO.target = edge.getTarget().getId();
			
			graphDTO.edges.add(edgeDTO);
		}
		
		for(Node node : nodes){
			NodeDTO nodeDTO = new NodeDTO();
			nodeDTO.id = node.getId();
			nodeDTO.label = node.getTitle();
			nodeDTO.x = 1; //TODO Where does the coordinates came from?
			nodeDTO.y = 1;
			nodeDTO.size = 1;
			nodeDTO.type = node.getNodetype().name();
			
			graphDTO.nodes.add(nodeDTO);
		}	
		
		return graphDTO;
	}
	
}
