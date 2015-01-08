package com.towels.graphofcontent.exceptions;

public class NodeNotFoundGraphException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423367808816583007L;
	
	public NodeNotFoundGraphException() {
		super();
	}
	
	public NodeNotFoundGraphException(String message) {
		super(message);
	}
}
