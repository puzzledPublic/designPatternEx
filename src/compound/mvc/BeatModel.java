package compound.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class BeatModel implements BeatModelInterface, MetaEventListener {
	
	private Sequencer sequencer;
	
	private List<BeatObserver> beatObservers = new ArrayList<>();
	
	private List<BPMObserver> bpmObservers = new ArrayList<>();
	
	private int bpm = 60;
	
	private Sequence sequence;
	
	private Track track;

	@Override
	public void meta(MetaMessage meta) {
		if(meta.getType() == 47) { //END_OF_TRACK_MESSAGE
			beatEvent();
			sequencer.setTickPosition(0);
			sequencer.start();
			sequencer.setTempoInBPM(getBPM());
		}
	}
	
	private void beatEvent() {
		notifyBeatObservers();
	}

	@Override
	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}
	
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);
			
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};
		
		sequence.deleteTrack(null);
		track = sequence.createTrack();
		
		makeTracks(trackList);
		track.add(makeEvent(192, 9, 1, 0, 4));
		
		try {
			sequencer.setSequence(sequence);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makeTracks(int[] list) {
		for(int i = 0; i < list.length; i++) {
			int key = list[i];
			
			if(key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i + 1));
			}
		}
	}
	
	public MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
		MidiEvent midiEvent = null;
		
		try {
			ShortMessage shortMessage = new ShortMessage();
			shortMessage.setMessage(command, channel, data1, data2);
			midiEvent = new MidiEvent(shortMessage, tick);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return midiEvent;
	}

	@Override
	public void on() {
		sequencer.start();
		setBPM(getBPM());
	}

	@Override
	public void off() {
		setBPM(0);
		sequencer.stop();
	}

	@Override
	public void setBPM(int bpm) {
		this.bpm = bpm;
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers();
	}

	@Override
	public int getBPM() {
		return bpm;
	}

	@Override
	public void registerObserver(BeatObserver beatObserver) {
		beatObservers.add(beatObserver);
	}

	public void notifyBeatObservers() {
		for(BeatObserver beatObserver : beatObservers) {
			beatObserver.updateBeat();
		}
	}
	
	@Override
	public void removeObserver(BeatObserver beatObserver) {
		beatObservers.remove(beatObserver);
	}

	@Override
	public void registerObserver(BPMObserver bpmObserver) {
		bpmObservers.add(bpmObserver);
	}

	public void notifyBPMObservers() {
		for(BPMObserver bpmObserver : bpmObservers) {
			bpmObserver.updateBPM();
		}
	}
	
	@Override
	public void removeObserver(BPMObserver bpmObserver) {
		bpmObservers.remove(bpmObserver);
	}
	
}
