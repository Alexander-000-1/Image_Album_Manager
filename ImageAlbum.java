package part01;

import java.util.ArrayList;

public class ImageAlbum {
	private ArrayList<ImageRecord> imgAlbum;
	private int currentImageIndex;

	public ImageAlbum() {
		imgAlbum = new ArrayList<ImageRecord>();
	}

	/**
	 * Takes an ImageRecord and inserts into arrayList in date order
	 * @param image
	 */
	public void addRecordOrdered(ImageRecord image) {
		// if array has no elements insert date into array
		if (this.imgAlbum.size() == 0) {
			this.imgAlbum.add(image);
			return;
		}

		// loop list
		for (int i = 0; i < this.imgAlbum.size(); i++) {
			// if new date is before element in array
			if (image.getDateTaken().isBefore(this.imgAlbum.get(i).getDateTaken())) {
				// insert date in before element
				this.imgAlbum.add(i, image);
				return;
			}
		}
		//if the image is after all other images
		//image added to end of list
		this.imgAlbum.add(image);
	}

	/**
	 * returns a reference to the first or ‘earliest’ ImageRecord instance in an ImageAlbum.
	 * Returns null if there are no images in the album
	 * 
	 * @return ImageRecord
	 */
	public ImageRecord getFirst() {
		currentImageIndex = 0;
		if((imgAlbum.size() > currentImageIndex) && imgAlbum != null) {
			return imgAlbum.get(currentImageIndex);
		}
		return null;
	}

	/**
	 * returns a reference to an ImageRecord instance
	 * – the image ‘next’ to the last image accessed –
	 * Returns null if no images in album
	 * or no next image exists
	 * @return ImageRecord
	 */
	public ImageRecord getNext() {
		if(imgAlbum.size() > currentImageIndex + 1) {
			return imgAlbum.get(++currentImageIndex);
		}
		return null;
	}

	/**
	 * Returns a reference to an ImageRecord instance
	 * – the image ‘previous’ to the last image accessed –
	 * Returns null if no images in the album
	 * or no ‘previous’ image exists
	 * @return
	 */ 
	public ImageRecord getPrevious() {
		if(currentImageIndex - 1 >= 0) {
			return imgAlbum.get(--currentImageIndex);
		}
		return null;
	}
	
	/**
	 * returns the size of the image album array
	 * @return
	 */
	public int getSize() {
		return this.imgAlbum.size();
	}
}
