package audio.graph

/**
 * removes all files created before a given time (now - 5.hours for examples)
 */
class CleanServerFilesJob {
	def serverFileService
    def timeout = 1*3600*1000 // execute job once in 1 hour

    def execute() {
		use( [org.codehaus.groovy.runtime.TimeCategory] ){
			Date migrosData = new Date()-5.hours
			ServerFile.findAllByCreationLessThan(migrosData).each{it.delete()}
				
		}
    }
}
