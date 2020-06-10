package Services;

import java.awt.print.PrinterIOException;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScrapeService
{

	
	public void title() throws IOException
	
	{	try {
		final String url= "http://google.com";
		 Document doc = Jsoup.connect(url).get();
		 String title = doc.title();

	} catch (Exception ex )
	{
	ex.printStackTrace();
	
}
}}