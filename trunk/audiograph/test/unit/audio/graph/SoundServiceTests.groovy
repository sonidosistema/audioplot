package audio.graph
import audio.graph.data.DataPoints 
import audio.graph.data.Graph 


import grails.test.*

class SoundServiceTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	
	void  testPlaySound() {
		mockDomain(ServerFile)

		DataPoints data = new DataPoints()
		Graph graph = new Graph()
		data.x = [1, 1.5, 2, 2.5]
		data.y = [10, 15, 20, 25]
		graph.datapoints = data
		SoundService soundService = new SoundService()
		ServerFileService sfService = new ServerFileService()
		soundService.serverFileService=sfService
		ServerFile sf = soundService.playSound(graph)
		
		println sfService.blobStore
		assert sf
		assert sfService.getBlob(sf).bytes.size() > 0
	}
}
