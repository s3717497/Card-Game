package file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import model.Player;

// Tutelab 09 solution by Caspar
public class GameLoaderBinary extends AbstractGameLoader
{
	// binary file implementations for "Template" method pattern

	// FILE FORMAT: file written as a DataOutputStream via saveAllPlayersImpl()
	// POST-CONDITION: underlying file will be closed
	protected Collection<Player> loadAllPlayersImpl(FileInputStream fis) throws GameLoaderException
	{
		Collection<Player> players = new ArrayList<Player>();
		try (DataInputStream dis = new DataInputStream(fis))
		{
			// while not EOF
			while (dis.available() != 0)
				// we will not reuse any of the attributes so no need to declare vars
				players.add(new Player(dis.readUTF(), dis.readUTF(), dis.readInt()));
		}
		catch (Exception e)
		{
			throw new GameLoaderException("Error loading players: check source is valid .dat");
		}
		return players;
	}

	// POST-CONDITION: underlying file will be closed
	protected void saveAllPlayersImpl(FileOutputStream fos, Collection<Player> players) throws GameLoaderException
	{
		try (DataOutputStream dos = new DataOutputStream(fos))
		{
			for (Player player : players)
				appendPlayerImpl(dos, player);
		}
		catch (Exception e)
		{
			throw new GameLoaderException("Error saving players");
		}
	}

	// POST-CONDITION: underlying file will be closed
	protected void appendPlayerImpl(FileOutputStream fos, Player player) throws GameLoaderException
	{
		try (DataOutputStream dos = new DataOutputStream(fos))
		{
			appendPlayerImpl(dos, player);
		}
		catch (Exception e)
		{
			throw new GameLoaderException("Error appending player");
		}
	}

	// helper method for single player write
	private void appendPlayerImpl(DataOutputStream dos, Player player) throws IOException
	{
		dos.writeUTF(player.getPlayerId());
		dos.writeUTF(player.getName());
		dos.writeInt(player.getPoints());
	}
}
