package audio.graph

import audio.graph.data.Graph 

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


class GraphController{
	GraphService graphService
	SoundService soundService
	ServerFileService serverFileService
	
	String demoDirectory='web-app/data/demo'
	
	def index = {
	}
	
	def load = {	
		Map fileDescr = getFileDescr(params.data)
		if(!fileDescr.size){
			flash.message="no or empty uploaded file"
			render (view:'index')
			return
		}
		
		buildSound (params.data.getBytes(), fileDescr)
	}
	
	def demo = {
		File f=getDemoFile(params.name)
		buildSound (f, [filename:f.name, size:f.size()]);
	}
	
	def buildSound(byte[] bytes, Map fileDescr){
		buildSound(new ByteArrayInputStream(bytes), fileDescr)
	}
	
	def buildSound(File dataFile, Map fileDescr){
		buildSound(new FileInputStream(dataFile), fileDescr)
	}
	
	def buildSound(InputStream input, Map fileDescr){
		Graph graph = graphService.createGraph(input)
		ServerFile sf = soundService.playSound(graph)
		
		render (view:'index', model:[
			loadedOk:true,
			fileDescription:fileDescr,
			graph:graph,
			soundFile: sf,
			]
		)
	}
	
	/**
	 * action to build the select widget
	 */
	def demoWidget={
		List names= new File(demoDirectory).list().toList()*.replaceAll (/\.txt/, "")
		[names:names]
	}
	
	/**
	 * return the File from the demo directory, based on the passed name
	 * @param name
	 * @return
	 */
	File getDemoFile(String name){
		return new File("$demoDirectory/${name}.txt")
	}
	
	/**
	 * get some parameters from a {@link CommonsMultipartFile}
	 * @param uploadedFile
	 * @return
	 */
	Map getFileDescr(uploadedFile){
		[
			filename:uploadedFile.originalFilename,
			size: uploadedFile.size
		]
	}
	
	/**
	 * return the .wav stream, given an id
	 * id is passed as 123.wav (.wav is removed to look to a domain bean SeverFile
	 */
	def play = {
		def sid=(params.id.replaceAll('.wav', '')) as Long
		
		byte[] bytes = serverFileService.loadData(ServerFile.get(sid))
		response.setContentType("audio/wav")
		response.setContentLength(bytes.size() as Integer)
		response.outputStream << bytes
		response.outputStream.flush()
		return 
	}
}