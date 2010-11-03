package audio.graph


class CleanServerFilesJob {
	ServerFileService serverFileService
	
	static triggers = {
        simple name:'simpleTrigger', startDelay:10000, repeatInterval: 1*3600*1000, repeatCount: -1

	}
	
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
			files.each{sf -> serverFileService.delete sf }
		}
	}
}
