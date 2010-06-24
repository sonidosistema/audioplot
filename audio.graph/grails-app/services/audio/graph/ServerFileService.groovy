package audio.graph

class ServerFileService {
	static long idCounter=1
	def fileDirectory='D:serverSound'
    boolean transactional = true

    ServerFile newFile() {
       return new ServerFile(id:idCounter++)
    }
	
	File getFile(ServerFile sf){
		return new File("$fileDirectory/file-$sf.id")
	}
}
