package audio.graph

import grails.test.*

class ServerFileServiceTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void test_new() {
		mockDomain(ServerFile)
		mockLogging(ServerFileService)
		def service =new ServerFileService()
		ServerFile sf=service.newFile()
		assert sf
		assert sf.id > 0
		
		File f = service.getFile(sf)
		assert f
		assert f.absolutePath
		println f.absolutePath
	}
}
