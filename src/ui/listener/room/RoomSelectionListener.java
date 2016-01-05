package ui.listener.room;

import javax.swing.JTable;

import model.Room;

public interface RoomSelectionListener {

	public void onRoomSelection(Room room, JTable table);
}
