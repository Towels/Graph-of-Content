package com.towels.graphofcontent.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.towels.graphofcontent.dao.FileObjectDAO;
import com.towels.graphofcontent.data.FileObject;
import com.towels.graphofcontent.dto.FileObjectDTO;

@Stateless
@Path("file")
public class FileObjectResource extends Application {
	
	public static String basePath = "/home/towel/Desktop/ServerFiles";

	@PersistenceContext
	EntityManager em;
	
	@EJB
	FileObjectDAO dao;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FileObjectDTO uploadStream(FileObjectDTO dto){
	    FileObject fileobj = new FileObject(dto);
	    return new FileObjectDTO( dao.store(fileobj));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FileObjectDTO> getAllFiles(){
		List<FileObjectDTO> res = new ArrayList<FileObjectDTO>();
		for(FileObject file: dao.getAllFiles()){
			res.add(new FileObjectDTO(file));
		}
		return res;
	}
	
	@GET
	@Path("{id}/details")
	@Produces(MediaType.APPLICATION_JSON)
	public FileObjectDTO getFile(@PathParam("id") Long id){
		return new FileObjectDTO(dao.findFileById(id));
	}
	
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFileObjectInformation(@PathParam("id") Long id, FileObjectDTO dto){
		dao.update(new FileObject(dto));
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteFileObject(@PathParam("id") Long id){
		FileObject file = dao.findFileById(id);
		dao.removeReferencesInNodes(file);
		dao.delete(file);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(@PathParam("id") Long id) throws IOException{
		FileObject file= dao.findFileById(id);
		return Response.ok(new File(basePath + "/" + id.toString()))
				.header("Content-Disposition","attachment; filename=\"" + file.getTitle() + "." + file.getFileType().name().toLowerCase() + "\"")
				.build();
	}
	
	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public void uploadStream(@PathParam("id") Long id, InputStream payload ) throws IOException
	{
	    OutputStream os = new FileOutputStream(basePath + "/" + id.toString());
	    IOUtils.copy(payload,os);
	}
}
