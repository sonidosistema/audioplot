package audio.graph

import java.io.File;

class ServerFileService {
	File tempDir
	
	boolean transactional = true
	
	File getTempDir(){
		if(tempDir==null){
			tempDir=File.createTempFile ("server-file-service-", "")
			tempDir.delete()
			tempDir.mkdir()
			tempDir.deleteOnExit()
		}
		tempDir
	}
	
	private File localFile(ServerFile sf){
		return new File(getTempDir().absolutePath+"/sf-$sf.id")
	}
	
	def storeData(ServerFile sf, byte[] bytes){
		File f = localFile(sf)
		f<<bytes
	}
	
	
	byte[] loadData (ServerFile sf){
		File f = localFile(sf)
		f.bytes
	}
	
	def delete(ServerFile sf){
		localFile(sf).delete()
		sf.delete()
	}
}
