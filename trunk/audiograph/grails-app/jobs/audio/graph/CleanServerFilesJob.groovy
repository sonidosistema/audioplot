package audio.graph


class CleanServerFilesJob {
	ServerFileService serverFileService
	
	def timeout = 1*3600*0000 // execute job once in 1 hours
	
	def execute() {
		use( [
			org.codehaus.groovy.runtime.TimeCategory]
		){
			Date migrosData = new Date()-2.hours
			
			def files = ServerFile.findAllByCreationLessThan(migrosData)
			if(files.size() == 0){
				render "no ServerFile to delete"
				return
			} 
			log.info "deleting ${files.size()} old ServerFiles"
			files.each{sf ->
				serverFileService.delete sf
			}
			render "old ServerFile removed"
		}
	}
}
