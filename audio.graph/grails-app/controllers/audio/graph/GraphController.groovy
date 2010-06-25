package audio.graph

import audio.graph.data.Graph 
import java.io.File;

class GraphController{
	def graphService
	def serverFileService
	def index = {
	}
	def load = {	
		def downloadedfile = request.getFile('data');
		assert downloadedfile
		File ftmp = File.createTempFile("data", ".txt")
		downloadedfile.transferTo(ftmp)
		
		
		Graph graph = graphService.createGraph(ftmp)
		ftmp.delete()
		
		
		[description:graph.description]
	}
	
	def playgraph = {
		File sf=serverFileservice.newFile()
		
		File f = serverFileService.getFile(sf)
		
		[path : f.absolutePath]
	}
	
	//def soundService
	//def play = {
	//File f = soundService.playSound(graph)
	
	
	//}
	
}