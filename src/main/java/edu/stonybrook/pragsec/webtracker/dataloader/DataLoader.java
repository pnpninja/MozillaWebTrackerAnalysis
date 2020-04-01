package edu.stonybrook.pragsec.webtracker.dataloader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;

import edu.stonybrook.pragsec.webtracker.dao.WebsiteDetailDAO;
import edu.stonybrook.pragsec.webtracker.database.DBOperator;
import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;
import edu.stonybrook.pragsec.webtracker.utils.URLUtils;
public class DataLoader {
	private static final String SAMPLE_CSV_FILE_PATH = "/Users/prateek/Downloads/top-10k.csv";
	public static void main(String[] args) throws Exception {
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
	            CSVReader csvReader = new CSVReader(reader);
	        ) {
	            // Reading Records One by One in a String array
	            String[] nextRecord;
	            while ((nextRecord = csvReader.readNext()) != null) {
	                System.out.println("Rank : " + nextRecord[0]);
	                System.out.println("Valid Site - "+ URLUtils.getValidUrl("https://www."+nextRecord[1]));
	                WebsiteDetailDAO wddao = DBOperator.getInstance();
	                WebsiteDetail tempObj = new WebsiteDetail();
	                tempObj.setURL(URLUtils.getValidUrl("https://www."+nextRecord[1]));
	                tempObj.setLogs_fetched(false);
	                tempObj.setScreenshot_taken(false);
	                wddao.persist(tempObj);
	                System.out.println(wddao.getByURL(URLUtils.getValidUrl("https://www."+nextRecord[1])).getURL());
	                
	            }
	        }
	}
}
