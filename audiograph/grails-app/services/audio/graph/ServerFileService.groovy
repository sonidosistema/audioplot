package audio.graph
// creates a new file where sound will be saved

class ServerFileService {
	static long idCounter=1
	def fileDirectory='data'
	boolean transactional = true
	
	ServerFile newFile() {
		return new ServerFile().save()
		
	}
	
	File getFile(ServerFile sf){
		return new File("$fileDirectory/file-$sf.id")
	}
}
