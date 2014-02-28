import java.io.*;
import java.net.*;

public class Fetch
{	private static final int HTTP_PORT = 80;
	public static void main(String[] args)
	throws IOException, UnknownHostException, MalformedURLException
	{	if(args.length==0)
		{	System.out.println("Usage: java Fetch <url list>");
			System.exit(-1);
		}
		for(String url : args)
		{	try
			{	System.out.println(fetchURL(new URL(url)));
			}
			catch(MalformedURLException mue)
			{	System.out.println(fetchURL(new URL("http://" + url + "/")))	;
			}
			System.out.println();
		}
	}
	private static String fetchURL(URL url)
	throws IOException, UnknownHostException
	{	String header = "";
		Socket s = new Socket(url.getHost(), HTTP_PORT);
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.println("HEAD / HTTP/1.1");
		pw.println("Host: " + url.getHost());
		pw.println("Connection: Close\n");
		pw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String loop;
		while((loop=br.readLine())!=null)
			header += loop + "\n";
		pw.close();
		br.close();
		s.close();
		return header;
	}
}