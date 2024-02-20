package ClassesProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class GetRoom {

    public List<String> getAvailableRooms(String floor, String bedType, JComboBox<String> roomNo_Box,
            JTextField cost_fld) {
        List<String> availableRooms = new ArrayList<>();

        // retrieve the available rooms based on the selected floor and bed type
        try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("Rooms Details")) {
                    String[] rowData = new String[5]; // create an array with 5 elements
                    for (int i = 0; i < 5; i++) {
                        // read the next 5 lines and add the data to the corresponding column
                        rowData[i] = br.readLine();
                    }
                    if (rowData[1].equals(floor) && rowData[2].equals(bedType) && rowData[4].equals("Not Booked")) {
                        availableRooms.add(rowData[0]);
					    	// add the room number to the array list
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableRooms;
		 
    }
  public void updateGUIComponents(JComboBox<String> roomNo_Box, JTextField cost_fld, List<String> availableRooms) {
    // Update JComboBox based on available rooms
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(availableRooms.toArray(new String[0]));
    roomNo_Box.setModel(model);

}

}
