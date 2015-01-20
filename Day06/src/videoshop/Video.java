package videoshop;

import java.io.Serializable;

public class Video implements Serializable {
	
	public static enum MediaType {
		VHS, DVD, BLUERAY;
		
		public String getName() {
			switch (this) {
			case VHS:
				return "VHS";
			case DVD:
				return "DVD";
			case BLUERAY:
				return "BlueRay";
			default:
				return null;
			}
		}
		
		public static String getName(int type) {
			switch (type) {
			case Video.MEDIA_TYPE_VHS:
				return "VHS";
			case Video.MEDIA_TYPE_DVD:
				return "DVD";
			case Video.MEDIA_TYPE_BLUERAY:
				return "BlueRay";
			default:
				return null;
			}
		}
	}
	
	public static final int MEDIA_TYPE_VHS = 0;
	public static final int MEDIA_TYPE_DVD = 1;
	public static final int MEDIA_TYPE_BLUERAY = 2;
	
	private String title;
	private int runningTime;
	private String director;
	private String genre;
	private int mediaType;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getMediaType() {
		return mediaType;
	}
	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}
	
}
