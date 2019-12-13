package BC;
import com.sun.net.httpserver.HttpServer;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
public class main {
	static Credentials bank;
	static String bankAddress;
	static String bankPrivateKey;
	static String bankPublicKey;
	static Credentials BMW;
	static String BMWAddress;
	static String BMWPrivateKey;
	static String BMWPublicKey;
	static Credentials companyA;
	static String companyAAddress;
	static String companyAPrivateKey;
	static String companyAPublicKey;
	static Credentials companyB;
	static String companyBAddress;
	static String companyBPrivateKey;
	static String companyBPublicKey;
	static ApplicationContext context;
	static Service service;
	static ChannelEthereumService channelEthereumService;
    static Web3j web3j;
    static String AssetAddress = "0x2649bf898cd7b68f72a9a46a8bc7587f4e547c67";
    //gas
    static BigInteger gasPrice = new BigInteger("300000000");
    static BigInteger gasLimit = new BigInteger("300000000");
    static Map<String,String>Pr2Ad=new HashMap<>();
    static public void init() {
    	try {
    		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    		service = context.getBean(Service.class);
    		service.run();
    		channelEthereumService = new ChannelEthereumService();
    	    channelEthereumService.setChannelService(service);
    	    channelEthereumService.setTimeout(10000);
    	    web3j = Web3j.build(channelEthereumService, service.getGroupId());
  /*  		bank=Credentials.create(Keys.createEcKeyPair());
    		bankAddress=bank.getAddress();
    		bankPrivateKey=bank.getEcKeyPair().getPrivateKey().toString(16);
    		bankPublicKey=bank.getEcKeyPair().getPublicKey().toString(16);
    		BMW=Credentials.create(Keys.createEcKeyPair());
    		BMWAddress=BMW.getAddress();
    		BMWPrivateKey=BMW.getEcKeyPair().getPrivateKey().toString(16);
    		BMWPublicKey=BMW.getEcKeyPair().getPublicKey().toString(16);
    		companyA=Credentials.create(Keys.createEcKeyPair());
    		companyAAddress=companyA.getAddress();
    		companyAPrivateKey=companyA.getEcKeyPair().getPrivateKey().toString(16);
    		companyAPublicKey=companyA.getEcKeyPair().getPublicKey().toString(16);
    		companyB=Credentials.create(Keys.createEcKeyPair());
    		companyBAddress=companyB.getAddress();
    		companyBPrivateKey=companyB.getEcKeyPair().getPrivateKey().toString(16);
    		companyBPublicKey=companyB.getEcKeyPair().getPublicKey().toString(16);	*/
    	}catch(Exception e) {}
		Pr2Ad.put(BMWPrivateKey, BMWAddress);
		Pr2Ad.put(bankPrivateKey, bankAddress);
		Pr2Ad.put(companyAPrivateKey, companyAAddress);
		Pr2Ad.put(companyBPrivateKey, companyBAddress);	    
	    //gas
	    BigInteger gasPrice = new BigInteger("3000000000");
	    BigInteger gasLimit = new BigInteger("3000000000");
    }
	public static void main(String[]args) {
		init();
		//Use for debug
/*		try {
			Asset contract =Asset.deploy(web3j, bank, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(contract.issue(bankAddress, new BigInteger("100")).send());
			System.out.println(bankPrivateKey);
			System.out.println(bankAddress);
			System.out.println(BMWPrivateKey);
			System.out.println(BMWAddress);
			System.out.println(companyAPrivateKey);
			System.out.println(companyAAddress);
			System.out.println(companyBPrivateKey);
			System.out.println(companyBAddress);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(1254), 0);
			server.createContext("/issue", new issueHandler());
	        server.createContext("/getBalance", new getBalanceHandler());
	        server.createContext("/sendCash",new sendCashHandler());
	        server.createContext("/sendLoan", new sendLoanHandler());
	        server.createContext("/evaluate",new evaluateHandler());
	        server.createContext("/sendOtherLoan",new sendOtherLoanHandler());
	        server.createContext("/loan",new loanHandler());
	        server.createContext("/payLoan", new payLoanHandler());
	        server.start();
	        System.out.println("Server is ready!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static Map<String,String>formData2Dic(String formData){
		Map<String,String> result=new HashMap<>();
		if(formData==null||formData.trim().length()==0) {
			return result;
		}
		final String[]items=formData.split("&");
		Arrays.stream(items).forEach(item -> {
			final String[]keyAndVal=item.split("=");
			if(keyAndVal.length==2) {
				try {
					final String key=URLDecoder.decode(keyAndVal[0],"utf8");
					final String val=URLDecoder.decode(keyAndVal[1],"utf8");
					result.put(key,val);
				}catch(UnsupportedEncodingException e) {}
			}
		});
		return result;
	}
	static class payLoanHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("PayLoanHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String loanAddress=queryStringInfo.get("loanAddress");
			Credentials msgSender=GenCredential.create(from);
			System.out.println(1);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			System.out.println(2);
			try {
				System.out.println(3);
				System.out.println(loanAddress);
				TransactionReceipt temp=contract.payLoan(loanAddress).send();
				System.out.println(4);
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(temp.toString().getBytes());
				os.close();
				System.out.println("PayLoanHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class loanHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("LoanHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String loanAddress=queryStringInfo.get("loanAddress");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.loan(loanAddress).send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(temp.toString().getBytes());
				os.close();
				System.out.println("LoanHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class sendOtherLoanHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("SendCashHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String to=queryStringInfo.get("to");
			String amount=queryStringInfo.get("amount");
			String loanAddress=queryStringInfo.get("loanAddress");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.sendOthersLoan(loanAddress, to, new BigInteger(amount)).send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(("Sent Loan: "+temp.getOutput().toString()).getBytes());
				os.close();
				System.out.println("SendCashHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class evaluateHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("EvaluateHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String to=queryStringInfo.get("to");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.evaluate(to).send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(temp.toString().getBytes());
				os.close();
				System.out.println("EvaluateHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class sendLoanHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("SendLoanHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String to=queryStringInfo.get("to");
			String amount=queryStringInfo.get("amount");
			String time=queryStringInfo.get("time");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.sendLoan(to, new BigInteger(amount), new BigInteger(time)).send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(("Loan: "+ temp.getOutput().toString()+"\n" + temp.toString()).getBytes());
				os.close();
				System.out.println("SendLoanHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class sendCashHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("SendCashHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String to=queryStringInfo.get("to");
			String amount=queryStringInfo.get("amount");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.sendCash(to, new BigInteger(amount)).send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(temp.toString().getBytes());
				os.close();
				System.out.println("SendCashHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class issueHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("IssueHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			String to=queryStringInfo.get("to");
			String amount=queryStringInfo.get("amount");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.issue(to, new BigInteger(amount)).send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(temp.toString().getBytes());
				os.close();
				System.out.println("IssueHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
			
		}
	}
	static class getBalanceHandler implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException{
			System.out.println("GetBalanceHandling....");
			String queryString=exchange.getRequestURI().getQuery();
			Map<String,String>queryStringInfo=formData2Dic(queryString);
			String from = queryStringInfo.get("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load(AssetAddress, web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			try {
				TransactionReceipt temp=contract.getBalane().send();
				exchange.sendResponseHeaders(200, 0);
				OutputStream os=exchange.getResponseBody();
				os.write(("Your balance: "+temp.getOutput().toString()).getBytes());
				os.close();
				System.out.println("GetBalanceHandler return.");
			} catch (Exception e) {
				OutputStream os=exchange.getResponseBody();
				os.write("fail".getBytes());
				os.close();
				e.printStackTrace();
			}
		}
	}
	public void test() throws Exception {
	    //initial
		
	
	    //deploy contract
//	    Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, bank, new StaticGasProvider(gasPrice, gasLimit));
	    
	    String str="123";
		JSONObject object=JSONObject.fromObject(str);
		String judge=object.getString("SERVICE");
		if(judge.equals("issue")) {
			String amount=object.getString("amount");
			String receiver=object.getString("receiver");
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.issue(receiver, new BigInteger(amount)).send();
		}
		else if(judge.contentEquals("getBalance")) {
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.getBalane().send();
			BigInteger answer=contract.getGetBalaneOutput(temp).getValue1();
		}
		else if(judge.contentEquals("evaluate")) {
			String company=object.getString("companyAddress");
			Credentials msgSender=GenCredential.create(bankPrivateKey);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.evaluate(company).send();
		}
		else if(judge.contentEquals("sendCash")){
			String amount=object.getString("amount");
			String receiver=object.getString("receiver");
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.sendCash(receiver, new BigInteger(amount)).send();
		}
		else if(judge.contentEquals("sendLoan")) {
			String amount=object.getString("amount");
			String receiver=object.getString("receiver");
			String time=object.getString("time");
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.sendLoan(receiver, new BigInteger(amount), new BigInteger(time)).send();
		}
		else if(judge.contentEquals("sendOthersLoan")) {
			String amount=object.getString("amount");
			String receiver=object.getString("receiver");
			String loanAddress=object.getString("loanAddress");
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.sendOthersLoan(loanAddress, receiver, new BigInteger(amount)).send();
		}
		else if(judge.contentEquals("loan")) {
			String loanAddress=object.getString("loanAddress");
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.loan(loanAddress).send();
		}
		else if(judge.contentEquals("payLoan")) {
			String loanAddress=object.getString("loanAddress");
			String from=object.getString("from");
			Credentials msgSender=GenCredential.create(from);
			Asset contract =Asset.load("0xdc2b1d7a7ce4bd8905ecb923577da0a5e3598ea3", web3j, msgSender, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt temp=contract.payLoan(loanAddress).send();
		}
		else {
			System.out.println("No match to "+judge);
		}
	 

	}
}
