package Model;

public class MusicVO {

	private String bgmName;
	private String path;
	
	public MusicVO(String bgmName, String path) {
		super();
		this.bgmName = bgmName;
		this.path = path;
	}
	public String getBgmName() {
		return bgmName;
	}
	public String getPath() {
		return path;
	}
	
	
}
