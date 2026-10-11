package part01;

import java.time.LocalDate;
import java.util.Scanner;

public class QUBImages {
	static final String TITLE = "QUB Images";
	static final String[] OPTIONS = { "Add Image", "Search", "Display All", "Exit" };
	static final String[] GENRES = { "Astronomy", "Architecture", "Sport", "Landscape", "Portrait", "Nature", "Aerial","Food", "Other" };
	static final String[] SEARCHES = { "unique id", "title", "description", "type", "date range" };
	static final int QUIT = OPTIONS.length;
	static final String PROMPT = "---->";
	static Menu menu = new Menu(TITLE, OPTIONS);
	static Menu genreMenu = new Menu(PROMPT + "Images Types", GENRES);
	static Menu searchMenu = new Menu("Search by", SEARCHES);
	static Scanner input = new Scanner(System.in);
	static ImageManager manager = new ImageManager();

	public static void main(String[] args) {
		defaultImages();
		int choice;
		do {
			choice = menu.getUserChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT);
		System.out.println("\nGoodbye!");
		input.close();
	}

	/**
	 * Takes the users choice from menu Adds image or outputs result from search
	 * @param choice
	 */
	public static void processChoice(int choice) {
		switch (choice) {
		case 1:
			// creates new imageRecord and adds to image manager
			ImageRecord image = newRecord();
			manager.addImage(image);
			break;
		case 2:
			// Takes user choice from menu output
			int searchBy = searchMenu.getUserChoice();
			if (searchBy == 1) {
				// if choice is 1 print record from ID search
				ImageRecord idSearch = searchIdInput();
				if (idSearch != null) {
					System.out.println(idSearch);
				}
			} else {
				// creates album based on selected search type
				ImageAlbum searchAlbum = searchType(searchBy);
				// outputs album created from search
				displayImages(searchAlbum);
			}
			break;
		case 3:
			// creates album of all images in manager
			ImageAlbum album = manager.getAllImages();
			// outputs album
			displayImages(album);
			break;
		default:
			System.out.println("Invalid Option\n");
			break;

		}

	}

	/**
	 * Takes user inputs. Constructs ImageRecord instance based on input values.
	 * Returns image.
	 * @return ImageRecord
	 */
	public static ImageRecord newRecord() {
		System.out.print(PROMPT + "Enter an image title: ");
		String title = validateString();

		System.out.print(PROMPT + "Enter image description: ");
		String desc = validateString();

		int selection = genreMenu.getUserChoice();
		ImageType type = inputImgType(selection);

		System.out.print(PROMPT + "Enter the image date in format (YYYY-MM-DD):");
		LocalDate date = inputImgDate();

		System.out.print(PROMPT + "Enter thumbnail png:");
		String thumbnail = validateString();

		ImageRecord img;
		try {
			img = new ImageRecord(title, desc, type, date, thumbnail);
			System.out.println(); // new line
			return img;
		} catch (Exception e) {
			System.out.println("Image cant be created\n" + e.getMessage());
			return null;
		}

	}

	/**
	 * Returns Image Type based on user choice
	 * @param selection
	 * @return ImageType
	 */
	public static ImageType inputImgType(int selection) {
		switch (selection) {
		case 1:
			return ImageType.ASTRONOMY;
		case 2:
			return ImageType.ARCHITECTURE;
		case 3:
			return ImageType.SPORT;
		case 4:
			return ImageType.LANDSCAPE;
		case 5:
			return ImageType.PORTRAIT;
		case 6:
			return ImageType.NATURE;
		case 7:
			return ImageType.AERIAL;
		case 8:
			return ImageType.FOOD;
		case 9:
			return ImageType.OTHER;
		default:
			return null;
		}

	}

	/**
	 * Takes string input from user. Parses String to date. Repeats prompt until
	 * valid date and prints error message if invalid.
	 * @return
	 */
	public static LocalDate inputImgDate() {
		LocalDate date;
		while (true) {
			try {
				String dateInput = input.nextLine();
				date = LocalDate.parse(dateInput);
				return date;
			} catch (Exception e) {
				System.out.print("Invalid date format\n Re-enter date:");
			}
		}
	}

	/**
	 * Takes user choice for search from menu Prompts user for search value Returns
	 * Image Album created from search result
	 * @param choice
	 * @return ImageAlbum
	 */
	public static ImageAlbum searchType(int choice) {
		ImageAlbum albumCreatedFromSearch = null;
		switch (choice) {
		case 2:
			System.out.print(PROMPT + "Enter title for search:");
			String title = validateString();
			albumCreatedFromSearch = manager.searchTitle(title);
			break;
		case 3:
			System.out.print(PROMPT + "Enter description for search:");
			String description = validateString();
			albumCreatedFromSearch = manager.searchDescription(description);
			break;
		case 4:
			int selection = genreMenu.getUserChoice();
			ImageType genre = inputImgType(selection);
			albumCreatedFromSearch = manager.searchGenre(genre);
			break;
		case 5:
			System.out.print(PROMPT + "Enter date range start (YYYY-MM-DD):");
			LocalDate start = inputImgDate();

			System.out.print(PROMPT + "Enter date range end (YYYY-MM-DD):");
			LocalDate end = inputImgDate();
			if (end.isBefore(start)) {
				System.out.println("Invalid end date");
				return null;
			}
			albumCreatedFromSearch = manager.searchDates(start, end);
			break;
		default:
			break;
		}
		return albumCreatedFromSearch;
	}

	/**
	 * validates int input for ID search Returns the record from a valid ID
	 * @return
	 */
	public static ImageRecord searchIdInput() {
		// sets image to first record
		ImageRecord image = manager.searchId(1);
		// checks if there are any images in manager
		if (image == null) {
			System.out.println("No images available");
			return null;
		}
		// Loops until valid image created
		do {
			try {
				System.out.print(PROMPT + "Enter ID:");
				int id = Integer.parseInt(input.nextLine());
				image = manager.searchId(id);
				if (image == null) {
					// prints error if ID is an int but not in album
					System.out.println("ID not found");
				}
			} catch (Exception e) {
				// prints error if value is not an int
				System.out.println("Invalid ID");
			}
		} while (image == null);
		return image;
	}

	/**
	 * Loops album and prints all records If album is empty prints error
	 * @param album
	 */
	public static void displayImages(ImageAlbum album) {
		if (album != null && album.getSize() > 0) {
			// gets first record in album
			ImageRecord image = album.getFirst();
			// loops album until record returns null
			while (image != null) {
				// prints current and gets next image
				System.out.println(image);
				image = album.getNext();
			}
		} else {
			System.out.println("The album is empty");
		}
		// new line
		System.out.println();
	}

	/**
	 * Checks if string is empty. Loops until valid
	 * @return String
	 */
	public static String validateString() {
		while (true) {
			String inputString = input.nextLine();
			// checks if string is not blanks and doesnt start with special character
			if (!inputString.isBlank() && inputString.matches("^[a-zA-Z0-9].*")) {
				return inputString;
			}
			System.out.println("Invalid String (String cant be empty or start with a special character)");
			System.out.print("Try again:");
		}
	}

	/**
	 * Default images stored in manager on startup
	 */
	public static void defaultImages() {
		try {
			manager.addImage(new ImageRecord("Andromeda", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY,LocalDate.parse("2023-01-01"), "Andromeda.png"));
			manager.addImage(new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.",ImageType.ARCHITECTURE, LocalDate.parse("2023-02-01"), "LanyonQUB.png"));
			manager.addImage(new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf ",ImageType.SPORT, LocalDate.parse("2023-03-01"), "KermitGolf.png"));
			manager.addImage(new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.",ImageType.LANDSCAPE, LocalDate.parse("2023-04-01"), "Mournes.png"));
			manager.addImage(new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.",ImageType.PORTRAIT, LocalDate.parse("2023-03-01"), "Homer.png"));
			manager.addImage(new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE,LocalDate.parse("2023-04-01"), "RedKite.png"));
			manager.addImage(new ImageRecord("Central Park", "An overhead view of Central Park New York USA.",ImageType.AERIAL, LocalDate.parse("2023-05-01"), "CentralPark.png"));
			manager.addImage(new ImageRecord("Apples", "A bunch of apples.", ImageType.FOOD,LocalDate.parse("2023-06-01"), "Apples.png"));
			manager.addImage(new ImageRecord("Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER,LocalDate.parse("2023-07-01"), "ChatGPT.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
