package audio.graph

class ServerFileController {
	
    def scaffold=true

	def clean = {
		use( [org.codehaus.groovy.runtime.TimeCategory] ){
			Date migrosData = new Date()-2.hours
			
			def files = ServerFile.findAllByCreationLessThan(migrosData)
			if(files.size() == 0){
				render "no ServerFile to delete"
				return
			} 
			log.info "deleting ${files.size()} old ServerFiles"
			files.each{sf ->
				sf.delete()
			}
			render "old ServerFile removed"	
		}
	}
}
