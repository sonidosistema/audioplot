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
		def fileDescr = getFileDescr(params.data)
		if(!fileDescr.size){
			flash.message="no or empty uploaded file"
			render (view:'index')
			return
		}
		
		
		def downloadedfile = request.getFile("data");
		File ftmp = File.createTempFile("data", ".txt")
		downloadedfile.transferTo(ftmp)
		
		
		Graph graph = graphService.createGraph(ftmp)
		ServerFile sf= serverFileService.newFile()
		soundService.playSound(graph, serverFileService.getFile(sf))
		
		//test instruments only
		def instrumentSounds=[:]
		SoundService.availableInstruments.each{inst ->
			ServerFile sfTmp= serverFileService.newFile()
			soundService.playSound(graph, serverFileService.getFile(sfTmp), inst)
			instrumentSounds[inst]=[
					playText:"play $inst",
					soundFile:sfTmp,
				]
		}
		
		render (view:'index', model:[
			loadedOk:true,
			fileDescription:fileDescr,
			graph:graph,
			soundFile: sf,
			//debug
			instrumentSounds:instrumentSounds
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