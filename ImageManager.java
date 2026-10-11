package part01;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageManager {
	private ArrayList<ImageRecord> images;

	public ImageManager() {
		images = new ArrayList<ImageRecord>();

	}

	/**
	 * adds a reference to an ImageRecord object to the arraylist
	 * @param image
	 */
	public void addImage(ImageRecord image) {
		if (image != null) {
			this.images.add(image);
		}
	}

	/**
	 * returns a reference to an ImageRecord object (identified by id) or null
	 * @param id
	 * @return img
	 */
	public ImageRecord searchId(int id) {
		if (id > 0 && id <= this.images.size()) {
			int index = id -= 1;
			ImageRecord img = this.images.get(index);
			return img;
		}
		return null;
	}

	/**
	 * returns a reference to an ImageAlbum instance for images with title equal to
	 * the String str.
	 * @param str
	 * @return
	 */
	public ImageAlbum searchTitle(String str) {
		int imagesAddedToAlbum = 0;
		ImageAlbum titleAlbum = new ImageAlbum();
		// loops through image record array
		for (int imageInAlbum = 0; imageInAlbum < this.images.size(); imageInAlbum++) {
			// Checks if current record in album has a title equal to input str
			if (this.images.get(imageInAlbum).getImgTitle().equalsIgnoreCase(str)) {
				// adds current record and increments counter
				titleAlbum.addRecordOrdered(this.images.get(imageInAlbum));
				imagesAddedToAlbum++;
			}
		}
		if (imagesAddedToAlbum > 0) {
			return titleAlbum;
		}
		return null;
	}

	/**
	 * Returns a reference to an ImageAlbum instance for images with description
	 * containing the String str.
	 * @param str
	 * @return
	 */
	public ImageAlbum searchDescription(String str) {
		int imagesAddedToAlbum = 0;
		ImageAlbum descAlbum = new ImageAlbum();
		// loops through image record array
		for (int imageInAlbum = 0; imageInAlbum < this.images.size(); imageInAlbum++) {
			// Checks if current record in album has a description which contains input str
			if (this.images.get(imageInAlbum).getImgDesc().contains(str)) {
				// adds current record and increments counter
				descAlbum.addRecordOrdered(this.images.get(imageInAlbum));
				imagesAddedToAlbum++;
			}
		}
		if (imagesAddedToAlbum > 0) {
			return descAlbum;
		}
		return null;
	}

	/**
	 * returns a reference to an ImageAlbum instance for images which match type.
	 * @param type
	 * @return
	 */
	public ImageAlbum searchGenre(ImageType type) {
		int imagesAddedToAlbum = 0;
		if (type != null) {
			// converts input type to string
			String genreDesc = type.toString();
			ImageAlbum genreAlbum = new ImageAlbum();
			// loops through records array
			for (int imageInAlbum = 0; imageInAlbum < this.images.size(); imageInAlbum++) {
				// Converts current record type to string, compares to input
				if (this.images.get(imageInAlbum).getImageType().toString().equals(genreDesc)) {
					// if image matches input add to album
					genreAlbum.addRecordOrdered(this.images.get(imageInAlbum));
					imagesAddedToAlbum++;
				}
			}
			// checks if the album is empty
			if (imagesAddedToAlbum > 0) {
				return genreAlbum;
			}
		}
		return null;
	}

	/**
	 * returns an ImageAlbum for images taken between start and end dates.
	 * @param start
	 * @param end
	 * @return
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		int imagesAddedToAlbum = 0;
		ImageAlbum datesAlbum = new ImageAlbum();
		for (int imageInAlbum = 0; imageInAlbum < this.images.size(); imageInAlbum++) {
			// Checks if current record in album has date that is not outside range of given
			// start and end dates
			if (!this.images.get(imageInAlbum).getDateTaken().isBefore(start)
					&& !this.images.get(imageInAlbum).getDateTaken().isAfter(end)) {
				// if image matches input add to album
				datesAlbum.addRecordOrdered(this.images.get(imageInAlbum));
				imagesAddedToAlbum++;
			}
		}
		// checks if the album is empty
		if (imagesAddedToAlbum > 0) {
			return datesAlbum;
		}
		return null;
	}

	/**
	 * returns a reference to an ImageAlbum instance for all images managed by an
	 * ImageManager object.
	 * @return
	 */
	public ImageAlbum getAllImages() {
		if (images.size() > 0) {
			ImageAlbum allImages = new ImageAlbum();
			for (ImageRecord image : this.images) {
				allImages.addRecordOrdered(image);
			}
			return allImages;
		}
		return null;
	}

}
