package audio.graph

import javax.persistence.*;
class ServerFile {
	Long id

	Date creation = new Date()
	
    static constraints = {
    	id visible:false
    }
}
