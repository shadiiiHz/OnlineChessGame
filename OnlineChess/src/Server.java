import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Server {
	
	public static ArrayList<Integer> MovesI = new ArrayList<Integer>();
	public static ArrayList<Integer> MovesJ = new ArrayList<Integer>();
	
	public static Border border = new LineBorder(Color.BLACK);
	public static ServerSocket listener;
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();
	public static boolean turn = true;
	public static void main(String[] args) throws IOException {
		listener = new ServerSocket(123);
		System.out.println("SERVER STARTED");
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class Handler extends Thread {
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		public Handler(Socket socket) throws IOException {

			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			if (Server.sockets.size() <= 1) {
				System.out.println("Client Connected !");
				Server.sockets.add(socket);
				if (Server.sockets.size() == 2) {
					START();
				}
			} else {
				out.println("SERVER-BUSY");
				out.flush();
				System.out.println("Server is busy !");
			}

		}

		public void run() {
			try {
				while (true) {

					String command = in.readLine();

					System.out.println("Client : " + command);
					if (command.startsWith("DEFAULT")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						boolean TransferTurn = Boolean.valueOf(CircleAddress[5]);
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("KNOWN" + " " + Integer.toString(i) + " " + Integer.toString(j) + " "
									+ Integer.toString(previousI) + " " + Integer.toString(previousJ));
							OUTSC.flush();
						}
						turn = !turn;
//						
					}
					if (command.startsWith("Change-Pawn2-Border")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						boolean TransferTurn = Boolean.valueOf(CircleAddress[5]);
						String TransferResult = CircleAddress[6];
						
						
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("ChangeP2-Border" + " " + Integer.toString(i) + " " + Integer.toString(j)
									+ " " + Integer.toString(previousI) + " " + Integer.toString(previousJ) + " "
									+ TransferResult);
							OUTSC.flush();
						}
						
						turn = !turn;
						

					}
					if (command.startsWith("Change-Pawn1-Border")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						boolean TransferTurn = Boolean.valueOf(CircleAddress[5]);
						String TransferResult = CircleAddress[6];
						System.out.println(TransferResult);
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("ChangeP1-Border" + " " + Integer.toString(i) + " " + Integer.toString(j)
									+ " " + Integer.toString(previousI) + " " + Integer.toString(previousJ) + " "
									+ TransferResult);
							OUTSC.flush();
						}
						turn = !turn;
						
						
					}
					if (command.startsWith("Change-Pawn2")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						boolean TransferTurn = Boolean.valueOf(CircleAddress[5]);
						String TransferResult = CircleAddress[6];
						System.out.println(TransferResult);
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("ChangeP2" + " " + Integer.toString(i) + " " + Integer.toString(j) + " "
									+ Integer.toString(previousI) + " " + Integer.toString(previousJ) + " "
									+ TransferResult);
							OUTSC.flush();
						}
						turn = !turn;
						
						

					}
					if (command.startsWith("Change-Pawn1")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						boolean TransferTurn = Boolean.valueOf(CircleAddress[5]);
						String TransferResult = CircleAddress[6];
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("ChangeP1" + " " + Integer.toString(i) + " " + Integer.toString(j) + " "
									+ Integer.toString(previousI) + " " + Integer.toString(previousJ) + " "
									+ TransferResult);
							OUTSC.flush();
						}
						turn = !turn;
						
						
					}
					if (command.startsWith("BTN-CLICKED")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						boolean TransferTurn = Boolean.valueOf(CircleAddress[5]);
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("NEW-BTN" + " " + Integer.toString(i) + " " + Integer.toString(j) + " "
									+ Integer.toString(previousI) + " " + Integer.toString(previousJ));
							OUTSC.flush();
						}
						turn = !turn;
						
						
					}
					
					if (command.startsWith("RED")) {
						String[] CircleAddress = command.split(" ");
						int i = Integer.parseInt(CircleAddress[1]);
						int j = Integer.parseInt(CircleAddress[2]);
						int previousI = Integer.parseInt(CircleAddress[3]);
						int previousJ = Integer.parseInt(CircleAddress[4]);
						for (Socket sc : sockets) {

							PrintWriter OUTSC = new PrintWriter(sc.getOutputStream(), true);

							OUTSC.println("RED_BACK" + " " + Integer.toString(i) + " " + Integer.toString(j) + " "
									+ Integer.toString(previousI) + " " + Integer.toString(previousJ));
							OUTSC.flush();
						}
						turn = !turn;
					}
					if (command.startsWith("Turn_Update")) {
						out.println("TURN" + " " + ((turn) ? "1" : "2"));
						out.flush();
					}
				}
			} catch (Exception e) {

			}
		}
	}

	public static void START() {
		int count = 1;
		for (Socket sc : sockets) {
			PrintWriter OUTSC;
			try {
				OUTSC = new PrintWriter(sc.getOutputStream(), true);
				OUTSC.println("start" + " " + Integer.toString(count));
				OUTSC.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
	}
	
}
