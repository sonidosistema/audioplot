package audio.graph

import audio.graph.data.Graph 
import java.io.File;

class GraphController{
	def graphService
	def serverFileService
	def soundService
	def index = {
	}
	def load = {	
		def downloadedfile = request.getFile("data");
		assert downloadedfile
		File ftmp = File.createTempFile("data", ".txt")
		downloadedfile.transferTo(ftmp)
		
		
		Graph graph = graphService.createGraph(ftmp)
		ftmp.delete()
		ServerFile sf= serverFileService.newFile()
		soundService.playSound(graph, serverFileService.getFile(sf))
		
		
		[description:graph.description, datapoints: graph.datapoints, soundFile: sf]
	}
	
	
	def play = {		
		File f = serverFileService.getFile(ServerFile.get(params.serverFileId as Long))
		response.setContentType("audio/midi")
		response.setContentLength(f.size() as Integer)
		response.outputStream<< f.readBytes()
		response.outputStream.flush()
	}
}