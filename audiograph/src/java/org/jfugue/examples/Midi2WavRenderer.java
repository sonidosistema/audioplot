package org.jfugue.examples;

/*
 * JFugue - API for Music Programming
 * This class copyright (C) 2003-2010  Karl Helgason and David Koelle
 *
 * http://www.jfugue.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.jfugue.JFugueException;
import org.jfugue.Pattern;
import org.jfugue.Player;

public class Midi2WavRenderer 
{
    /** No one needs to instantiate this class... */
    private Midi2WavRenderer() { }
    
    /**
     * Creates a WAV file based on the Pattern, using the sounds from the specified soundbank; 
     * to prevent memory problems, this method asks for an array of patches (instruments) to load. 
     * 
     * @param soundbankFile
     * @param patches
     * @param pattern
     * @param outputFile
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(File soundbankFile, int[] patches, Pattern pattern, File outputFile) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        Patch[] patchlist = new Patch[patches.length];
        for (int i = 0; i < patchlist.length; i++) {
            patchlist[i] = new Patch(0, patches[i]);
        }       
        createWavFile(soundbankFile, patchlist, pattern, outputFile);
    }
    
    /**
     * Creates a WAV file based on the Sequence, using the sounds from the specified soundbank; 
     * to prevent memory problems, this method asks for an array of patches (instruments) to load.
     *  
     * @param soundbankFile
     * @param patches
     * @param sequence
     * @param outputFile
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(File soundbankFile, int[] patches, Sequence sequence, File outputFile) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        Patch[] patchlist = new Patch[patches.length];
        for (int i = 0; i < patchlist.length; i++) {
            patchlist[i] = new Patch(0, patches[i]);
        }
        createWavFile(soundbankFile, patchlist, sequence, outputFile);
    }
        
    /**
     * Creates a WAV file based on the Pattern, using the sounds from the specified soundbank; 
     * to prevent memory problems, this method asks for an array of patches (instruments) to load. 
     * 
     * @param soundbankFile
     * @param patches
     * @param pattern
     * @param outputFile
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(File soundbankFile, Patch[] patches, Pattern pattern, File outputFile) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        // Create a Player with this Synthesizer, and get a Sequence        
        Player player = new Player(MidiSystem.getSequencer(false));
        Sequence sequence = player.getSequence(pattern);
                
        createWavFile(soundbankFile, patches, sequence, outputFile);
    }
    
    /**
     * Creates a WAV file based on the Sequence, using the sounds from the specified soundbank; 
     * to prevent memory problems, this method asks for an array of patches (instruments) to load.
     *  
     * @param soundbankFile
     * @param patches
     * @param sequence
     * @param outputFile
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(File soundbankFile, Patch[] patches, Sequence sequence, File outputFile) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {       
        // Load soundbank       
        Soundbank soundbank = MidiSystem.getSoundbank(soundbankFile);
        
        // Open the Synthesizer and load the requested instruments
        AudioInputStream stream = getAudioInputStream(sequence, soundbank, patches, null, null);
        try
        {
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outputFile);
        }
        finally
        {
            stream.close();
        }        
    }
    
    
    
    /** 
     * Creates a WAV file based on the Pattern, using the default soundbank.
     *  
     * @param pattern
     * @param outputFile
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(Pattern pattern, File outputFile) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        // Create a Player with this Synthesizer, and get a Sequence        
        Player player = new Player(MidiSystem.getSequencer(false));
        Sequence sequence = player.getSequence(pattern);
        
        createWavFile(sequence, outputFile);
    }
    
    /**
     * Creates a WAV file based on the Sequence, using the default soundbank.
     *  
     * @param sequence
     * @param outputFile
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(Sequence sequence, File outputFile) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        AudioInputStream stream = getAudioInputStream(sequence, null, null, null, null);
        try
        {
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outputFile);
        }
        finally
        {
            stream.close();
        }
    }    
    
    
    /**
     * Creates a WAV file based on the Sequence.
     *  
     * @param sequence
     * @param outputFile
     * @param soundbank
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(Sequence sequence, File outputFile, Soundbank soundbank) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        AudioInputStream stream = getAudioInputStream(sequence, soundbank, null, null, null);
        try
        {
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outputFile);
        }
        finally
        {
            stream.close();
        }
    }    

    /**
     * Creates a WAV file based on the Sequence.
     *  
     * @param sequence
     * @param outputFile
     * @param soundbank
     * @param format
     * @param info
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static void createWavFile(Sequence sequence, File outputFile, Soundbank soundbank, AudioFormat format, Map<String, Object> info) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        AudioInputStream stream = getAudioInputStream(sequence, soundbank, null, format, info);
        try
        {
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, outputFile);
        }
        finally
        {
            stream.close();
        }
    }
        

    /**
     * Get AudioInputStream from a Sequence.
     *  
     * @param sequence
     * @param soundbank
     * @param patchList,
     * @param format
     * @param info
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public static AudioInputStream getAudioInputStream(Sequence sequence, Soundbank soundbank, Patch[] patchList,  AudioFormat format, Map<String, Object> info) throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        Synthesizer synth = findAudioSynthesizer();
        if (synth == null) {
            throw new JFugueException("No AudioSynthesizer was found!");
        }
        
        // If no format was specified, let use 44100 Hz, Stereo, 16 bit signed
        if(format == null)
            format = new AudioFormat(44100, 16, 2, true, false);
        
        Method openStreamMethod = null;
        try
        {
            openStreamMethod = 
                synth.getClass().getMethod("openStream", 
                        new Class[] {AudioFormat.class, Map.class});        
        }
        catch(NoSuchMethodException e)
        {
            throw new JFugueException(e.getMessage());
        }
    
        if(soundbank != null)
        {
            if (!synth.isSoundbankSupported(soundbank)) {
                throw new JFugueException("Soundbank not supported by synthesizer");
            }        
        }

        AudioInputStream stream;
        try {
            stream = (AudioInputStream)
                openStreamMethod.invoke(synth, new Object[] {format, info});
        } catch (IllegalArgumentException e) {
            throw new JFugueException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new JFugueException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new JFugueException(e.getMessage());
        }
        
        // Load Soundbank into Synthesizer.
        if(soundbank != null)
        {
            if(patchList == null)
                synth.loadAllInstruments(soundbank);
            else
                synth.loadInstruments(soundbank, patchList);
        }

        // Play Sequence into AudioSynthesizer Receiver.
        double total = send(sequence, synth.getReceiver());

        synth.close();
        
        // Calculate how long the audio file needs to be.
        long len = (long) (stream.getFormat().getFrameRate() * (total + 4));
        stream = new AudioInputStream(stream, stream.getFormat(), len);

        return stream;
    }

    /**
     * Find available AudioSynthesizer.
     */
    private static Synthesizer findAudioSynthesizer() throws MidiUnavailableException 
    {
    
        @SuppressWarnings("rawtypes")
		Class audioSynthesizerClass;
        try {
            audioSynthesizerClass = Class.forName("com.sun.media.sound.AudioSynthesizer");
        } catch (ClassNotFoundException e) {
            // AudioSynthesizer not in classpath, return null
            return null;            
        }
        
        // First check if default synthesizer is AudioSynthesizer.
        Synthesizer synth = MidiSystem.getSynthesizer();
        if (audioSynthesizerClass.isInstance(synth)) {
            return synth;
        }       

        // If default synthesizer is not AudioSynthesizer, check others.
        MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < midiDeviceInfo.length; i++) {
            MidiDevice dev = MidiSystem.getMidiDevice(midiDeviceInfo[i]);
            if (audioSynthesizerClass.isInstance(dev)) {
                return (Synthesizer)dev;
            }
        }

        // No AudioSynthesizer was found, return null.
        return null;
    }

    /**
     * Send entry MIDI Sequence into Receiver using timestamps.
     */
    private static double send(Sequence seq, Receiver recv) 
    {
        float divtype = seq.getDivisionType();
        assert (seq.getDivisionType() == Sequence.PPQ);
        Track[] tracks = seq.getTracks();
        int[] trackspos = new int[tracks.length];
        int mpq = 500000;
        int seqres = seq.getResolution();
        long lasttick = 0;
        long curtime = 0;
        while (true) {
            MidiEvent selevent = null;
            int seltrack = -1;
            for (int i = 0; i < tracks.length; i++) {
                int trackpos = trackspos[i];
                Track track = tracks[i];
                if (trackpos < track.size()) {
                    MidiEvent event = track.get(trackpos);
                    if (selevent == null
                            || event.getTick() < selevent.getTick()) {
                        selevent = event;
                        seltrack = i;
                    }
                }
            }
            if (seltrack == -1)
                break;
            trackspos[seltrack]++;
            long tick = selevent.getTick();
            if (divtype == Sequence.PPQ)
                curtime += ((tick - lasttick) * mpq) / seqres;
            else
                curtime = (long) ((tick * 1000000.0 * divtype) / seqres);
            lasttick = tick;
            MidiMessage msg = selevent.getMessage();
            if (msg instanceof MetaMessage) {
                if (divtype == Sequence.PPQ)
                    if (((MetaMessage) msg).getType() == 0x51) {
                        byte[] data = ((MetaMessage) msg).getData();
                        mpq = ((data[0] & 0xff) << 16)
                                | ((data[1] & 0xff) << 8) | (data[2] & 0xff);
                    }
            } else {
                if (recv != null)
                    recv.send(msg, curtime);
            }
        }
        return curtime / 1000000.0;
    }

}