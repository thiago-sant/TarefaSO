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
		if (ip.contains("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec("ifconfig");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("mtu")) {
						String roteador[] = linha.split(":");
						linha = buffer.readLine();
						if (linha.contains("inet")) {
							String numero [] = linha.split(" ");
							System.out.println(roteador[0] + " - "+numero[9]);
							linha = buffer.readLine();
						}
					}
					linha = buffer.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
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
					if (linha.contains("M")) {		// vai buscar a linha que contém o M maiusculo
						String vetorMedia[] = linha.split(","); // a cada virgula na linha o programa cria uma posição no vetor
						System.out.println("Tempo médio - "+vetorMedia[2]); // vai printar a segunda posição do vetor, a que tem o valor da média
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // fim do windows
		if (ping.contains("Linux")) {
			Process p;
			try {
				p = Runtime.getRuntime().exec("ping -4 -c 10 www.uol.com.br");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha!=null) {
					if (linha.contains("rtt")) {
						String vetorMedia[] = linha.split("/");
						System.out.println(vetorMedia[5]);
						linha = buffer.readLine();
					}
					linha = buffer.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} // fim linux
		return ping;
	}
	
}
	
