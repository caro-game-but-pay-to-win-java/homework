package MainPackage;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Work {
	private Integer workID;
	private String workName;
	private LocalTime startTime;
	private LocalTime finishTime;
	
	public Work(Integer workID, String workName, LocalTime startTime) {
		this.workID = workID;
		this.workName = workName;
		this.startTime = startTime.truncatedTo(ChronoUnit.SECONDS);
		this.finishTime = null;
	}
	
	public void setFinishTime() {
		this.finishTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
	}
	
	public String getWorkName() {
		return this.workName;
	}
	
	public LocalTime getWorkStartTime() {
		return this.startTime;
	}
	
	public LocalTime getWorkFinishTime() {
		return this.finishTime;
	}
	
	public void writeLogFile() {
		try {
			String fileName = "src\\MainPackage\\" + getNameOfLogFile(LocalDate.now());
			File file = new File(fileName);
			if (file.exists()) {
				FileWriter writer = new FileWriter(fileName, true);
				writer.append(this.toString() + "\n");
				writer.close();
			} else {
				file.createNewFile();
				FileWriter writer = new FileWriter(fileName, true);
				writer.append(this.toString() + "\n");
				writer.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getNameOfLogFile(LocalDate objT) {
		return String.valueOf(objT.getYear()) + String.valueOf(objT.getMonth().toString()) + String.valueOf(objT.getDayOfMonth()) + ".txt";
	}
	
	@Override
	public String toString() {
		String returning = this.workID.toString() + "#" + this.workName + "#" + this.startTime.toString();
		if (this.finishTime != null) {
			 returning += "#" + this.finishTime.toString();
		}
		return returning;
	}
	
	
}
