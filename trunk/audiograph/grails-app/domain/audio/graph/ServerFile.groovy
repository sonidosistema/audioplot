package audio.graph

import javax.persistence.*;
import com.google.appengine.api.datastore.Blob
@Entity
class ServerFile {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	Date creation = new Date()
	
    static constraints = {
    	id visible:false
    }
}
