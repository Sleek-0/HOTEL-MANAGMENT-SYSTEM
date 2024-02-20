package interfaces;

import java.io.File;
import java.io.IOException;

public interface CheckInInfo {
  // Interface for writing check-in information to a file

  void WriteCheckinData(
      String nationality,
      String gmail,
      String address,
      String CheckInDate,
      String cost,
      String gender,
      String roomNo_B,
      File file,
      String fullName,
      String mobileNumber)
      throws IOException;
}
