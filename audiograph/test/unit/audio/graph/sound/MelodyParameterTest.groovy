/**
 * 
 */
package audio.graph.sound



import com.sun.speech.freetts.Voice 
import com.sun.speech.freetts.VoiceManager 
import javax.speech.*;
import javax.speech.synthesis.*;

/**
 * @author alex
 *
 */
class MelodyParameterTest extends GroovyTestCase {
	
	boolean isLive=true
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void test_extremes(){
		ToneBuilder builder=[min:100, max:1000, size:100]
		StringBuffer buffer=new StringBuffer()
		int n=10
		(0..n).each{
			buffer<<" "+builder.buildTone(it/n)	
		}
		MelodyStreamer streamer = [:]
		streamer.streamIt("Flute", builder.string2pattern(buffer.toString()).getMusicString())
	}	
	
	public void test_size(){
		ToneBuilder builder=[min:100, max:1000, size:2000]
		StringBuffer buffer=new StringBuffer()
		
		def prec=0.03
		(0..6).each{
			buffer<<" "+builder.buildTone(0.5+(it%2)*prec)
		}
		MelodyStreamer streamer = [:]
		streamer.streamIt("Flute", builder.string2pattern(buffer.toString()).getMusicString())
	}	
	
	public void test_say(){
		say("test function x ranges from 3.34 to 6.75. y ranges to -3.25 to 10.05")
	}
	
	public void test_function_sin(){
		say("function sinusoide")
		def n=100
		def data=(0..n).collect{Math.sin(it.toDouble()/n*7)}
		function_play(data)
	}
	
	public void test_function_power_2(){
		say("function power 2")
		
		def n=100
		def data=(0..n).collect{
			def x = (it.toDouble()/n-0.5)*3
			Math.pow(x, 2)
		}
		function_play(data)
	}
	
	public void test_function_power_4(){
		say("function power 4")
		
		def n=100
		def data=(0..n).collect{
			def x = (it.toDouble()/n-0.5)*3
			Math.pow(x, 4)
		}
		function_play(data)
	}
	
	public void test_function_hyperbole(){
		say("function hyperbole, one local minimum, and one local maximum")

		def n=100
		def data=(0..n).collect{
			def x=(it.toDouble()/n-0.5)*3
			(x-1)*x*(x+1)
		}
		function_play(data)
	}
	
	private void function_play(data){
		def max=data.max()
		def min=data.min()
		data=data.collect{(it-min)/(max-min)}
		
		ToneBuilder builder=[min:200, max:1000, size:2000]
		StringBuffer buffer=new StringBuffer()
		
		data.each{
			buffer<<" "+builder.buildTone(it)
		}
		MelodyStreamer streamer = [tempo:500]
		streamer.streamIt("Flute", builder.string2pattern(buffer.toString()).getMusicString())		
	}
	
	private void say(String str){
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice helloVoice = voiceManager.getVoice("kevin16");
		helloVoice.allocate();
		helloVoice.speak(str);
		
		/* Clean up and leave.
		 */
		helloVoice.deallocate();
		
		
	}
	
}
