package CTRL;

import java.util.ArrayList;

import Model.MusicVO;
import javazoom.jl.player.MP3Player;

public class musicCTRL {
	MP3Player mp3 = new MP3Player();
	ArrayList<MusicVO> musicList = new ArrayList<MusicVO>();

	public musicCTRL() {

		musicList.add(new MusicVO("mainbgm", "./music/mainbgm.mp3"));
		musicList.add(new MusicVO("프롤로그", "./music/pro.mp3"));
		musicList.add(new MusicVO("게임진행", "./music/start2.mp3"));
		musicList.add(new MusicVO("손님", "./music/customer.mp3"));
		musicList.add(new MusicVO("엔딩", "./music/ending.mp3"));
	}

	public void playstop(String s) {

		for (int i = 0; i < musicList.size(); i++) {
			MusicVO m = musicList.get(i);
			String a = m.getBgmName();
			if (a.equals(s)) {
				if (mp3.isPlaying()) {

					mp3.stop();
				}
				mp3.play(m.getPath());
			}
		}
	}

	public MusicVO gamebgm() {
		MusicVO m = musicList.get(2);
		mp3.play(m.getPath());
		return m;
	}

	public MusicVO bgmstop() {
		MusicVO m = musicList.get(3);
		mp3.stop();
		return m;
	}

	public MusicVO custbgm() {
		MusicVO m = musicList.get(3);
		mp3.play(m.getPath());
		return m;

	}
	
	public void isPlayingTest() {

		System.out.println(mp3.isPlaying());
	}

	public void stop() {
		
		mp3.stop();
		

//		for (int i = 0; i < musicList.size(); i++) {
//			MusicVO m = musicList.get(i);
//			String a = m.getBgmName();
//			System.out.println(a);
//			System.out.println(s);
//			if (a.equals(s)) {
//
//				if (mp3.isPlaying()) {
//					mp3.stop();
//				} else
//					mp3.stop();
//
//			}
//		}
	}
}
