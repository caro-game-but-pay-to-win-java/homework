package MainPackage;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimeLabel extends JLabel implements ActionListener
{
	  private Timer Tick = new Timer(1000, this);
	
	  public TimeLabel()
	  {
		  Tick.start();
	  }
	
	  public void actionPerformed(ActionEvent event)
	  {
		  setText(LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString());
	  }
}