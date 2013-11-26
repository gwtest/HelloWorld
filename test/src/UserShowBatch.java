import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.sina.api.commons.util.ApacheHttpClient;
import cn.sina.api.commons.util.ApiHttpClient;



public class UserShowBatch {
	
	public static int callUserShowInterface(String uids) throws IOException {
        ApiHttpClient httpclient = new ApacheHttpClient(150, 2000, 3000, 1024 * 1024);
        String showurl = "http://10.75.25.166:8088/2/users/show_batch.json";
        StringBuffer sb = new StringBuffer();
        sb.append("source=").append("2975945008").append('&');
        sb.append("uids=").append(uids);
        String url = String.format("%s?%s", showurl, sb.toString());
        String rst = httpclient.get(url);
        
        JsonWrapper jw = new JsonWrapper(rst);
        
        return jw.getNode("users").size();
    }
	
	public static void main(String[] args) {	
		ArrayList<List<Integer>> outlist= new ArrayList<List<Integer>>();
		for(int m = 0; m < 10;m++){
			BufferedReader reader;
			ArrayList<Integer> list = new ArrayList<Integer>();
			try {
				reader = new BufferedReader(new FileReader("/data1/guowu/tmp"));
				String line = reader.readLine();
				int i = 0;
				StringBuffer sb = new StringBuffer();
       
				while (line != null) {	
        	    i++;
                if(i == 20){
                	sb.substring(0, sb.length()-1);
                	int size = callUserShowInterface(sb.toString());
                	list.add(size);
                	sb = new StringBuffer();
                	i = 0;
                }
                sb.append(line).append(",");
    	        line = reader.readLine();
            }
         reader.close(); 
        }catch(Exception e){
        }
	   outlist.add(list);
	 }
		for(List<Integer> in : outlist){	
			for(int sz : in){
				System.out.print(sz+" ");
			}
			System.out.println("----");
		}	
		System.exit(0);
	}
}
