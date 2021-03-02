package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController() {
		super();
	}
	
	public String ip() {
		String ip = System.getProperty("os.name");
		if (ip.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ipconfig");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String nome = null;
				while (linha != null) {
					if (linha.contains("Adaptador")) {
						 nome = linha;
						 linha = buffer.readLine();
					} else {
					if (linha.contains("IPv4")) {
					System.out.println(nome +" "+linha);
					linha = buffer.readLine();
					} else {
						linha = buffer.readLine();

					}
				}
					
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				System.err.println();
			}
			
		}
		return ip;
	}


	public String ping() {
		String ping = System.getProperty("os.name");
		if (ping.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ping -4 -n 10 www.uol.com.br");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("M")) {		// vai buscar a linha que cont�m o M maiusculo
						String vetorMedia[] = linha.split(","); // a cada virgula na linha o programa cria uma posi��o no vetor
						System.out.println("Tempo m�dio - "+vetorMedia[2]); // vai printar a segunda posi��o do vetor, a que tem o valor da m�dia
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ping;
	}
	
}
	
