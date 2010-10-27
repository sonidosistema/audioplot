package audio.graph

import audio.graph.data.Graph 
import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

class GraphController{
	GraphService graphService
	ServerFileService serverFileService
	SoundService soundService
	
	def index = {
	}
	
	def load = {	
		def downloadedfile = request.getFile("data");
		assert downloadedfile
		File ftmp = File.createTempFile("data", ".txt")
		downloadedfile.transferTo(ftmp)
		
		def fileDescr = getFileDescr(params.data)
		
		Graph graph = graphService.createGraph(ftmp)
		ServerFile sf= serverFileService.newFile()
		soundService.playSound(graph, serverFileService.getFile(sf))
		
		render (view:'index', model:[
			fileDescription:fileDescr,
			description:graph.description,
			datapoints: graph.datapoints,
			soundFile: sf,
			]
		)
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
		File f = serverFileService.getFile(ServerFile.get(sid))
		response.setContentType("audio/wav")
		response.setContentLength(f.size() as Integer)
		response.outputStream << f.readBytes()
		response.outputStream.flush()
		return 
	}
}