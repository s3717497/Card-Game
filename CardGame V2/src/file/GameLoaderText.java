package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import model.Player;

// Tutelab 09 solution by Caspar
public class GameLoaderText extends AbstractGameLoader
{
	// text file implementations for "Template" method pattern

	// FILE FORMAT: id, name, points (one player per line)
	// POST-CONDITION: underlying file will be closed
	protected Collection<Player> loadAllPlayersImpl(FileInputStream fis) throws GameLoaderException
	{
		Collection<Player> players = new ArrayList<Player>();
		try (Scanner scanner = new Scanner(fis))
		{
			// comma OR whitespace (should be platform neutral for newlines)
			scanner.useDelimiter(",|\\s");
			while (scanner.hasNext())
				// we will not reuse any of the attributes so no need to declare vars
				players.add(new Player(scanner.next(), scanner.next(), Integer.parseInt(scanner.next())));
		}
		catch (Exception e)
		{
			throw new GameLoaderException("Error loading players: check file format");
		}
		return players;
	}

	// POST-CONDITION: underlying file will be closed
	protected void saveAllPlayersImpl(FileOutputStream fos, Collection<Player> players) throws GameLoaderException
	{
		try (PrintStream out = new PrintStream(fos))
		{
			for (Player player : players)
				appendPlayerImpl(out, player);
		}
		catch (Exception e)
		{
			throw new GameLoaderException("Error saving players");
		}
	}

	// POST-CONDITION: underlying file will be closed
	protected void appendPlayerImpl(FileOutputStream fos, Player player) throws GameLoaderException
	{
		try (PrintStream out = new PrintStream(fos))
		{
			appendPlayerImpl(out, player);
		}
		catch (Exception e)
		{
			throw new GameLoaderException("Error appending player");
		}
	}

	// helper method for single player write
	private void appendPlayerImpl(PrintStream out, Player player)
	{
		out.printf("%s,%s,%d\n", player.getPlayerId(), player.getName(), player.getPoints());
	}
}
