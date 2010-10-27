/**
 * 
 */
package audio.graph.sound

;

import groovy.util.GroovyTestCase;
import org.jfugue.MicrotoneNotation 
import org.jfugue.Pattern 
import org.jfugue.Player 

/**
 * @author alex
 *
 */
class MelodyStreamerTest extends GroovyTestCase {
	
	boolean isLive=false
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void test_stream_a_simple_sound(){
		MelodyStreamer streamer = [:]
		String str ="F5q A5i G5s F5s   G5i"
		def tmpFile=tempFile()
		streamer.streamIt("Piano", str, tmpFile)
		assertFile(tmpFile)
	}
	
	public void test_stream_duration_in_length(){
		MelodyStreamer streamer = [:]
		String str ="B4/2.00 C5/1.00 D5/0.5 E5/0.25 F5/0.125 G5/0.0625"
		def tmpFile=tempFile()
		streamer.streamIt("Flute", str, tmpFile)
		assertFile(tmpFile)
	}
	public void test_microtone(){
		MicrotoneNotation microtone = new MicrotoneNotation()
		microtone.put("B1", 400.00);
		microtone.put("B2", 405.50);
		microtone.put("B3", 415.67);
		microtone.put("B4", 429.54);
		Pattern pattern = microtone.getPattern("<B1>q <B2>q <B3>q <B4>q");
		
		MelodyStreamer streamer = [:]
		streamer.streamIt("Flute", pattern, null)                           
	}
	
	/**
	 * if isLive, return null, which will force the melody streamer to play directly the piece
	 * @return
	 */
	private File tempFile(){
		if(isLive)
			return null
		
		File soundFile= File.createTempFile('melo-stream.', '.wav')
		//soundFile.deleteOnExit()
		println soundFile.absolutePath
		return soundFile
		
	}
	
	private assertFile(File soundFile){
		if(soundFile){
			assert soundFile.exists()
			assert soundFile.size()>0
		}
	}
	
}
