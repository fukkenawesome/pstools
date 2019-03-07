package pstool;

import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PsSupportTool {

	static ArrayList<String> bestSongsEvar = new ArrayList();
	static int testingValue = 10; // 10 seconds
	static int productionValue = 2700; // 45 minutes
	//Initial 10 minute delay
	long initalDelay = 600000; 
	
	public PsSupportTool(){
		if(loadSettings()){
			 timer.schedule(new Task(), initalDelay);
		}	
	}
	
	public static void openBrowser(String uri){
		if (Desktop.isDesktopSupported()) {
		    try {
				Desktop.getDesktop().browse(new URI(uri.trim()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean loadSettings(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("configureSettings.properties"));
			String conf = prop.getProperty("config");
			bestSongsEvar.addAll(Arrays.asList(conf.split(",")));
			//System.out.println(conf);
			System.out.println("config loaded..");
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getRandomSong(){
		return bestSongsEvar.get(new Random().nextInt(bestSongsEvar.size()));
	}
	
	static Timer timer = new Timer();

    static class Task extends TimerTask {
        @Override
        public void run() {
        	System.out.println("\\(^-^)/ ...Partayyyy... (o^^)o");
        	openBrowser(getRandomSong());
            int delay = (productionValue + new Random().nextInt(productionValue)) * 1000;
            timer.schedule(new Task(), delay);
           
        }

    }
	
	public static void main (String[] args){
		PsSupportTool tool = new PsSupportTool();
		
	}
}
