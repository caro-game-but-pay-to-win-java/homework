package MainPackage;

import java.time.LocalDate;
import java.time.LocalTime;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalTime time = LocalTime.of(8, 20, 45);
		LocalTime time2 = LocalTime.of(10, 25, 31);
		Work work = new Work(1, "Sửa lỗi Form", time);
		work.setFinishTime();
		Work work2 = new Work(2, "Fix Bug", time2);
		work2.setFinishTime();
		work.writeLogFile();
		work2.writeLogFile();
		System.out.println(Work.getNameOfLogFile(LocalDate.now()));
	}

}
