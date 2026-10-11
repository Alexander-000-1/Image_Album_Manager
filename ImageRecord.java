package part01;

import java.time.LocalDate;

public class ImageRecord {
	private String imgTitle;
	private String imgDesc;
	private ImageType imgType;
	private LocalDate dateTaken;
	private String thumbnail;
	private int recordId;
	private static int nextId = 1;

	public ImageRecord(String imgTitle, String imgDesc, ImageType imgType, LocalDate dateTaken, String thumbnail) throws Exception {
		this.recordId = nextId++;
		String errors = "";
		if(!setImgTitle(imgTitle)) {
			errors += "Title error\n";
		}
		if(!setImgDesc(imgDesc)) {
			errors += "Description error\n";
		}
		if(!setImageType(imgType)) {
			errors += "Type error\n";
		}
		if(!setDateTaken(dateTaken)) {
			errors += "Date error\n";
		}
		if(!setThumbnail(thumbnail)) {
			errors += "Thumbnail error\n";
		}
		if(errors.length() > 0) {
			throw new Exception(errors);
		}
	}

	public boolean setImgTitle(String imgTitle) {
		if (imgTitle != null) {
			this.imgTitle = imgTitle.trim();
			return true;
		}
		return false;
	}

	public boolean setImgDesc(String imgDesc) {
		if (imgDesc != null) {
			this.imgDesc = imgDesc.trim();
			return true;
		}
		return false;

	}

	public boolean setImageType(ImageType imgType) {
		if (imgType != null) {
			this.imgType = imgType;
			return true;
		}
		return false;

	}

	public boolean setDateTaken(LocalDate dateTaken) {
		if (dateTaken != null) {
			this.dateTaken = dateTaken;
			return true;
		}
		return false;
	}

	public boolean setThumbnail(String thumbnail) {
		if (thumbnail != null) {
			if (thumbnail.endsWith(".png")) {
				this.thumbnail = thumbnail.trim();
			} else {
				this.thumbnail = thumbnail.trim() + ".png";
			}
			return true;
		}
		return false;
		
	}

	public String getImgTitle() {
		return this.imgTitle;
	}

	public String getImgDesc() {
		return this.imgDesc;
	}

	public String getImageType() {
		return imgType.toString();
	}

	public LocalDate getDateTaken() {
		return this.dateTaken;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public int getRecordId() {
		return this.recordId;
	}

	public String toString() {
		String output = "| ";
		output += getRecordId() + " | ";
		output += getImgTitle() + " | ";
		output += getImgDesc() + " | ";
		output += getImageType() + " | ";
		output += String.format("%02d-%02d-%d | ", dateTaken.getDayOfMonth(), dateTaken.getMonthValue(),dateTaken.getYear());
		output += getThumbnail() + " | ";
		return output;
	}
}
