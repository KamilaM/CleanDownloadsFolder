import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
public class OrganizeDownloads
{


	private static final String PHOTOS_NAME = "Photos";
	private static final String MOVIES_NAME = "Movies";
	private static final String DOCUMENTS_NAME = "Documents";
	private static final String PDFS_NAME = "PDFs";
	private static final String PATH_TO_FILE = "/i325420/Downloads";
	// public String FOLDER;
	File downloads = new File(PATH_TO_FILE);
	File[] content = downloads.listFiles();

	public void cleanDownloads(String PATH_TO_FILE) throws IOException
	{

		checkIfSubfolderExists();
		moveFiles(content);
	}

	public String moveFiles(final File[] fileToMove) throws IOException
	{

		for (File f : content)

		{

			if (f.isDirectory())
			{
				File[] content = f.listFiles();
				moveFiles(content);
			}

			String fileType = "Undetermined";
			final File file = new File(String.valueOf(fileToMove));
			fileType = Files.probeContentType(file.toPath());
			return fileType;

			switch (fileType)
			{
				case fileType.contains("text/"):
					moveToDocuments();
					break;


				case fileType.contains("/image"):
					moveToPhotos();
					break;
				case fileType.contains("/pdf"):
					moveToPDFs();
					break;
				case fileType.contains("video/"):
					moveToMovies();
					break;
				default:
					System.out.println("Couldn't recognize a file format for a file" + " " + file.toString());
					break;
			}
		}
		return null;
	}


	private void moveToDocuments()
	{


	}

	private void moveToPhotos()
	{
	}

	private void moveToPDFs()
	{
	}

	private void moveToMovies()
	{
	}

	public List<String> Folders()
	{
		final List<String> FOLDER = new ArrayList<String>();
		FOLDER.add("Documents");
		FOLDER.add("Photos");
		FOLDER.add("PDFs");
		FOLDER.add("Movies");

		return FOLDER;
	}


	private void checkIfSubfolderExists()
	{


		List<String> existingFiles = new ArrayList<String>();

		for (File f : content)
		{
			existingFiles.add(f.getAbsolutePath());
		}

		String absolutePath = null;
		for (String folder : Folders())
		{
			absolutePath = PATH_TO_FILE + "//" + folder;
			if (!existingFiles.contains(absolutePath))
			{
				createDirectory(absolutePath);
			}
		}
		File Directory = new File(absolutePath);
		if (!Directory.isDirectory())
		{
			createDirectory(absolutePath);
		}
	}

	private void createDirectory(String filePath)
	{
		new File(filePath).mkdir();
	}
}
