package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

import model.Player;

//Tutelab 09 solution by Caspar
//this class contains some common functionality for text and binary loader implementations
public abstract class AbstractGameLoader implements GameLoader
{
	/**
	 * Since there is no common parent between FileOutputStream and FileInputStream I use generics
	 * 
	 * NOTE: Java's "retrofitted" generics requires a bit of extra work here 
	 * so we pass the Class<T> so we can return the correct type!
	 * 
	 * @param path - relative or absolute path to file to open
	 * @param output - create an output stream or input stream
	 * @param append - set append flag for output stream
	 * @param type - either a FileInputStream.class or a FileOutputStream.class (must match output)
	 * @return an appropriate FileStream for further file reading/writing
	 * 
	 * @throws GameLoaderException
	 */
	private <T> T createFileStream(String path, boolean output, boolean append, Class<T> type)
			throws GameLoaderException
	{
		T fs;
		try
		{
			// reading
			if (!output && !append)
				fs = type.cast(new FileInputStream(path));
			// writing
			else if (output && !append)
				fs = type.cast(new FileOutputStream(path));
			// appending
			else if (output && append)
				fs = type.cast(new FileOutputStream(path, true));
			else
				throw new IllegalArgumentException("Cannot append to InputStream");
		}
		catch (FileNotFoundException e)
		{
			throw new GameLoaderException(String.format("Path not found: %s", path));
		}
		// the compiler should normally pick up incorrect usage by matching the return type with the type.cast call
		catch (ClassCastException e)
		{
			throw new GameLoaderException(String.format("Implementation Error: Check Class<T>", path));
		}
		return fs;
	}

	public Collection<Player> loadAllPlayers(String path) throws GameLoaderException
	{
		// open the file and pass it the subclass impl to do a specific type of read (e.g. text/binary)
		return loadAllPlayersImpl(createFileStream(path, false, false, FileInputStream.class));
	}

	@Override
	public void saveAllPlayers(String path, Collection<Player> players) throws GameLoaderException
	{
		saveAllPlayersImpl(createFileStream(path, true, false, FileOutputStream.class), players);
	}

	@Override
	public void appendPlayer(String path, Player player) throws GameLoaderException
	{
		appendPlayerImpl(createFileStream(path, true, true, FileOutputStream.class), player);
	}

	// implementations for "Template" method pattern
	protected abstract Collection<Player> loadAllPlayersImpl(FileInputStream fis) throws GameLoaderException;

	protected abstract void saveAllPlayersImpl(FileOutputStream fos, Collection<Player> players)
			throws GameLoaderException;

	protected abstract void appendPlayerImpl(FileOutputStream fos, Player player) throws GameLoaderException;
}
