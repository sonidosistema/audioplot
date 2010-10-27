package audio.graph

import java.io.File;

// creates a new file where sound will be saved

class ServerFileService {
	static long idCounter=1
	def fileDirectory
	boolean transactional = true
	
	ServerFileService(){
		File ftmp=File.createTempFile ('data-dir', '')
		ftmp.delete()
		ftmp.mkdir()
		ftmp.deleteOnExit()
		fileDirectory=ftmp.absolutePath
	}
	
	ServerFile newFile() {
		return new ServerFile().save()
		
	}
	
	File getFile(ServerFile sf){
		log.debug "sf = $sf"
		return new File("$fileDirectory/file-$sf.id")
	}
}
