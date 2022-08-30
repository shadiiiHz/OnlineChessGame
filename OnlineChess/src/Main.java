import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//import Main.MainThread;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Main extends JFrame {

	private JPanel contentPane;
	private Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;

	public static JButton[][] btnOverCome = new JButton[8][8];
	public static JButton[][] btn = new JButton[8][8];

	static ImageIcon King1 = new ImageIcon("Picture/Queen1.png");
	static ImageIcon Queen1 = new ImageIcon("Picture/King1.png");
	static ImageIcon Bishop1 = new ImageIcon("Picture/Bishop1.png");
	static ImageIcon Pawn1 = new ImageIcon("Picture/Pawn1.png");
	static ImageIcon Knight1 = new ImageIcon("Picture/Knight1.png");
	static ImageIcon Rook1 = new ImageIcon("Picture/Rook1.png");
	static ImageIcon King2 = new ImageIcon("Picture/Queen2.png");
	static ImageIcon Queen2 = new ImageIcon("Picture/King2.png");
	static ImageIcon Bishop2 = new ImageIcon("Picture/Bishop2.png");
	static ImageIcon Pawn2 = new ImageIcon("Picture/Pawn2.png");
	static ImageIcon Knight2 = new ImageIcon("Picture/Knight2.png");
	static ImageIcon Rook2 = new ImageIcon("Picture/Rook2.png");
	static ImageIcon circle = new ImageIcon("Picture/circle.png");
	static ImageIcon RedCircle = new ImageIcon("Picture/8888.png");
	public static ArrayList<Integer> MovesI = new ArrayList<Integer>();
	public static ArrayList<Integer> MovesJ = new ArrayList<Integer>();
	public static boolean turn = true;
	public static Border border = new LineBorder(Color.BLACK);
	private static Main frame;
	public static JLabel lblTURN = new JLabel("White Turn");

	/**
	 * Launch the application.
	 */
	public static boolean EndGameWhite() {
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				if (btn[i][j].getIcon() == King2) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean EndGameBlack() {
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				if (btn[i][j].getIcon() == King1) {
					return false;
				}
			}
		}
		return true;
	}
	public static void checkTurn(boolean t, JLabel turnShow) {
		if (t == true) {

			turnShow.setText("White Turn");
		} else {

			turnShow.setText("Black Turn");
		}
	}

	public static boolean Wait() {
		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				if (btn[i][j].getIcon() == circle || btn[i][j].getBorder() == border) {
					return true;
				}
			}
		}
		return false;
	}

	public static void board(Icon icon) {
		lbl:for (int i = 0; i < btnOverCome.length; i++) {
			for (int j = 0; j < btnOverCome.length; j++) {
				if (btnOverCome[i][j].getIcon() == null) {
					btnOverCome[i][j].setIcon(icon);
					break lbl;
				}

			}
			
		}
	}

//	public void checkTurn(JLabel turnShow) {
//		if (turn == true) {
//
//			turnShow.setText("White Turn");
//		} else {
//
//			turnShow.setText("Black Turn");
//		}
//	}

	public static void ClearBorder(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				if (btn[m][n].getBorder() == border) {
					btn[m][n].setBorder(null);
				}
			}
		}

	}

	public static void ClearChoice(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				if (btn[m][n].getIcon() == circle) {
					btn[m][n].setIcon(null);

				}
			}
		}

	}
	public static void ClearChoiceRed(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				if (btn[m][n].getIcon() == RedCircle) {
					btn[m][n].setIcon(null);

				}
			}
		}

	}
	public static void PawnChoiceBlack(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				if (m == i + 1 && n == j && (btn[m][n].getIcon() == null)) {

					btn[m][n].setIcon(circle);
				}
			}
		}

	}

	public static void PawnChoiceBlackFirst(int i, int j) {
		// ----------------------------btn[i+1][j]--------------------------------
		if (btn[i + 1][j].getIcon() == null) {

			btn[i + 1][j].setIcon(circle);
		}
		// ----------------------------btn[i+2][j]--------------------------------
		if (btn[i + 2][j].getIcon() == null) {

			btn[i + 2][j].setIcon(circle);
		}
	}

//
	//
	public static void PawnChoiceBlackAttack(int i, int j) {
		// ------------------btn[i+1][j+1]-----------------
		if (j < 7) {
			if ((btn[i + 1][j + 1].getIcon() == Pawn2) || (btn[i + 1][j + 1].getIcon() == King2)
					|| (btn[i + 1][j + 1].getIcon() == Knight2) || (btn[i + 1][j + 1].getIcon() == Bishop2)
					|| (btn[i + 1][j + 1].getIcon() == Queen2) || (btn[i + 1][j + 1].getIcon() == Rook2)) {
				btn[i + 1][j + 1].setBorder(border);

			}
		}
		// ------------------btn[i+1][j-1]------------------
		if (j > 0) {
			if ((btn[i + 1][j - 1].getIcon() == Pawn2) || (btn[i + 1][j - 1].getIcon() == King2)
					|| (btn[i + 1][j - 1].getIcon() == Knight2) || (btn[i + 1][j - 1].getIcon() == Bishop2)
					|| (btn[i + 1][j - 1].getIcon() == Queen2) || (btn[i + 1][j - 1].getIcon() == Rook2)) {
				btn[i + 1][j - 1].setBorder(border);

			}
		}
	}

	public static void PawnChoiceWhite(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				if (m == i - 1 && n == j && btn[m][n].getIcon() == null) {
					btn[m][n].setIcon(circle);

				}
			}
		}

	}

	public static void PawnChoiceWhiteFirst(int i, int j) {
		// ----------------------------btn[i-1][j]--------------------------------
		if (btn[i - 1][j].getIcon() == null) {

			btn[i - 1][j].setIcon(circle);
		}
		// ----------------------------btn[i-2][j]--------------------------------
		if (btn[i - 2][j].getIcon() == null) {

			btn[i - 2][j].setIcon(circle);
		}

	}

	public static void PawnChoiceWhiteAttack(int i, int j) {
		// ------------------btn[i-1][j+1]----------------------------
		if (j < 7) {
			if ((btn[i - 1][j + 1].getIcon() == Pawn1) || (btn[i - 1][j + 1].getIcon() == King1)
					|| (btn[i - 1][j + 1].getIcon() == Knight1) || (btn[i - 1][j + 1].getIcon() == Bishop1)
					|| (btn[i - 1][j + 1].getIcon() == Queen1) || (btn[i - 1][j + 1].getIcon() == Rook1)) {
				btn[i - 1][j + 1].setBorder(border);

			}
		}
		// ------------------btn[i-1][j-1]----------------------------
		if (j > 0) {
			if ((btn[i - 1][j - 1].getIcon() == Pawn1) || (btn[i - 1][j - 1].getIcon() == King1)
					|| (btn[i - 1][j - 1].getIcon() == Knight1) || (btn[i - 1][j - 1].getIcon() == Bishop1)
					|| (btn[i - 1][j - 1].getIcon() == Queen1) || (btn[i - 1][j - 1].getIcon() == Rook1)) {
				btn[i - 1][j - 1].setBorder(border);

			}
		}
	}

	public static void RookChoiceBlack(int i, int j) {

		int n = j;
		for (int m = i + 1; m < btn.length; m++) {
			if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
					|| (btn[m][n].getIcon() == Rook2)) {
				btn[m][n].setBorder(border);
				break;
			}
			if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
					|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
					|| (btn[m][n].getIcon() == Rook1)) {

				break;
			}
			if (btn[m][n].getIcon() == null) {
				btn[m][n].setIcon(circle);
			}
			if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == Bishop2)
					|| (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Queen2) || (btn[m][n].getIcon() == Rook2))) {
				btn[m][n].setBorder(border);
			}

		}
		int m = i;
		for (int k = j + 1; k < btn.length; k++) {
			if ((btn[m][k].getIcon() == Pawn2) || (btn[m][k].getIcon() == King2) || (btn[m][k].getIcon() == Knight2)
					|| (btn[m][k].getIcon() == Bishop2) || (btn[m][k].getIcon() == Queen2)
					|| (btn[m][k].getIcon() == Rook2)) {
				btn[m][k].setBorder(border);
				break;
			}
			if ((btn[m][k].getIcon() == Pawn1) || (btn[m][k].getIcon() == King1) || (btn[m][k].getIcon() == Knight1)
					|| (btn[m][k].getIcon() == Bishop1) || (btn[m][k].getIcon() == Queen1)
					|| (btn[m][k].getIcon() == Rook1)) {

				break;
			}
			if (btn[m][k].getIcon() == null) {
				btn[m][k].setIcon(circle);
			}
			if (btn[m][k].getIcon() != null && ((btn[m][k].getIcon() == Pawn2) || (btn[m][k].getIcon() == Bishop2)
					|| (btn[m][k].getIcon() == King2) || (btn[m][k].getIcon() == Knight2)
					|| (btn[m][k].getIcon() == Queen2) || (btn[m][k].getIcon() == Rook2))) {
				btn[m][k].setBorder(border);
			}

		}
		if (i > 0) {
			int p1 = j;
			for (int p2 = i - 1; p2 >= 0; p2--) {
				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == Bishop2)
								|| (btn[p2][p1].getIcon() == King2) || (btn[p2][p1].getIcon() == Knight2)
								|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2))) {
					btn[p2][p1].setBorder(border);
				}

			}
		}
		if (j > 0) {
			int u1 = i;
			for (int u2 = j - 1; u2 >= 0; u2--) {
				if ((btn[u1][u2].getIcon() == Pawn2) || (btn[u1][u2].getIcon() == King2)
						|| (btn[u1][u2].getIcon() == Knight2) || (btn[u1][u2].getIcon() == Bishop2)
						|| (btn[u1][u2].getIcon() == Queen2) || (btn[u1][u2].getIcon() == Rook2)) {
					btn[u1][u2].setBorder(border);
					break;
				}
				if ((btn[u1][u2].getIcon() == Pawn1) || (btn[u1][u2].getIcon() == King1)
						|| (btn[u1][u2].getIcon() == Knight1) || (btn[u1][u2].getIcon() == Bishop1)
						|| (btn[u1][u2].getIcon() == Queen1) || (btn[u1][u2].getIcon() == Rook1)) {

					break;
				}
				if (btn[u1][u2].getIcon() == null) {
					btn[u1][u2].setIcon(circle);
				}
				if (btn[u1][u2].getIcon() != null
						&& ((btn[u1][u2].getIcon() == Pawn2) || btn[u1][u2].getIcon() == Bishop2)
						|| (btn[u1][u2].getIcon() == King2) || (btn[u1][u2].getIcon() == Knight2)
						|| (btn[u1][u2].getIcon() == Queen2) || (btn[u1][u2].getIcon() == Rook2)) {
					btn[u1][u2].setBorder(border);
				}

			}
		}

	}

	public static void RookChoiceWhite(int i, int j) {
		int n = j;
		for (int m = i + 1; m < btn.length; m++) {
			if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
					|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
					|| (btn[m][n].getIcon() == Rook1)) {
				btn[m][n].setBorder(border);
				break;
			}
			if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
					|| (btn[m][n].getIcon() == Rook2)) {

				break;
			}
			if (btn[m][n].getIcon() == null) {
				btn[m][n].setIcon(circle);
			}
			if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == Bishop1)
					|| (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
					|| (btn[m][n].getIcon() == Queen1) || (btn[m][n].getIcon() == Rook1))) {
				btn[m][n].setBorder(border);
			}

		}
		int m = i;
		for (int k = j + 1; k < btn.length; k++) {
			if ((btn[m][k].getIcon() == Pawn1) || (btn[m][k].getIcon() == King1) || (btn[m][k].getIcon() == Knight1)
					|| (btn[m][k].getIcon() == Bishop1) || (btn[m][k].getIcon() == Queen1)
					|| (btn[m][k].getIcon() == Rook1)) {
				btn[m][k].setBorder(border);
				break;
			}
			if ((btn[m][k].getIcon() == Pawn2) || (btn[m][k].getIcon() == King2) || (btn[m][k].getIcon() == Knight2)
					|| (btn[m][k].getIcon() == Bishop2) || (btn[m][k].getIcon() == Queen2)
					|| (btn[m][k].getIcon() == Rook2)) {

				break;
			}
			if (btn[m][k].getIcon() == null) {
				btn[m][k].setIcon(circle);
			}
			if (btn[m][k].getIcon() != null && ((btn[m][k].getIcon() == Pawn1) || (btn[m][k].getIcon() == Bishop1)
					|| (btn[m][k].getIcon() == King1) || (btn[m][k].getIcon() == Knight1)
					|| (btn[m][k].getIcon() == Queen1) || (btn[m][k].getIcon() == Rook1))) {
				btn[m][k].setBorder(border);
			}

		}
		if (i > 0) {
			int p1 = j;
			for (int p2 = i - 1; p2 >= 0; p2--) {
				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == Bishop1)
								|| (btn[p2][p1].getIcon() == King1) || (btn[p2][p1].getIcon() == Knight1)
								|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1))) {
					btn[p2][p1].setBorder(border);
				}

			}
		}
		if (j > 0) {
			int u1 = i;
			for (int u2 = j - 1; u2 >= 0; u2--) {
				if ((btn[u1][u2].getIcon() == Pawn1) || (btn[u1][u2].getIcon() == King1)
						|| (btn[u1][u2].getIcon() == Knight1) || (btn[u1][u2].getIcon() == Bishop1)
						|| (btn[u1][u2].getIcon() == Queen1) || (btn[u1][u2].getIcon() == Rook1)) {
					btn[u1][u2].setBorder(border);
					break;
				}
				if ((btn[u1][u2].getIcon() == Pawn2) || (btn[u1][u2].getIcon() == King2)
						|| (btn[u1][u2].getIcon() == Knight2) || (btn[u1][u2].getIcon() == Bishop2)
						|| (btn[u1][u2].getIcon() == Queen2) || (btn[u1][u2].getIcon() == Rook2)) {

					break;
				}
				if (btn[u1][u2].getIcon() == null) {
					btn[u1][u2].setIcon(circle);
				}
				if (btn[u1][u2].getIcon() != null
						&& ((btn[u1][u2].getIcon() == Pawn1) || btn[u1][u2].getIcon() == Bishop1)
						|| (btn[u1][u2].getIcon() == King1) || (btn[u1][u2].getIcon() == Knight1)
						|| (btn[u1][u2].getIcon() == Queen1) || (btn[u1][u2].getIcon() == Rook1)) {
					btn[u1][u2].setBorder(border);
				}

			}
		}
	}

	public static void KnightChoiceBlack(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				int row = Math.abs(m - i);
				int colmn = Math.abs(n - j);
				if (((row == 1 && colmn == 2) || (row == 2 && colmn == 1))) {

					if (btn[m][n].getIcon() == null) {
						btn[m][n].setIcon(circle);
					}
					if (btn[m][n].getIcon() != null
							&& ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == Bishop2)
									|| (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
									|| (btn[m][n].getIcon() == Queen2) || (btn[m][n].getIcon() == Rook2))) {
						btn[m][n].setBorder(border);
					}

				}
			}
		}

	}

	public static void KnightChoiceWhite(int i, int j) {
		for (int m = 0; m < btn.length; m++) {
			for (int n = 0; n < btn.length; n++) {
				int row = Math.abs(m - i);
				int colmn = Math.abs(n - j);
				if (((row == 1 && colmn == 2) || (row == 2 && colmn == 1))) {

					if (btn[m][n].getIcon() == null) {
						btn[m][n].setIcon(circle);
					}
					if (btn[m][n].getIcon() != null
							&& ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == Bishop1)
									|| (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
									|| (btn[m][n].getIcon() == Queen1) || (btn[m][n].getIcon() == Rook1))) {
						btn[m][n].setBorder(border);
					}

				}
			}
		}
	}

	public static void KingChoicBlack(int i, int j) {
		int m = i + 1;
		for (int n = j - 1; n <= j + 1; n++) {
			if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
					|| (btn[m][n].getIcon() == Rook2)) {
				btn[m][n].setBorder(border);

			}

			if (btn[m][n].getIcon() == null) {
				btn[m][n].setIcon(circle);
			}
			if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == Bishop2)
					|| (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Queen2) || (btn[m][n].getIcon() == Rook2))) {
				btn[m][n].setBorder(border);
			}

		}
		if (i > 0) {
			int p = i - 1;
			for (int k = j - 1; k <= j + 1; k++) {
				if ((btn[p][k].getIcon() == Pawn2) || (btn[p][k].getIcon() == King2) || (btn[p][k].getIcon() == Knight2)
						|| (btn[p][k].getIcon() == Bishop2) || (btn[p][k].getIcon() == Queen2)
						|| (btn[p][k].getIcon() == Rook2)) {
					btn[p][k].setBorder(border);

				}

				if (btn[p][k].getIcon() == null) {
					btn[p][k].setIcon(circle);
				}
				if (btn[p][k].getIcon() != null && ((btn[p][k].getIcon() == Pawn2) || (btn[p][k].getIcon() == Bishop2)
						|| (btn[p][k].getIcon() == King2) || (btn[p][k].getIcon() == Knight2)
						|| (btn[p][k].getIcon() == Queen2) || (btn[p][k].getIcon() == Rook2))) {
					btn[p][k].setBorder(border);
				}

			}
		}
		// ----------------------------------------btn[i][j+1]-----------------------------------
		if ((btn[i][j + 1].getIcon() == Pawn2) || (btn[i][j + 1].getIcon() == King2)
				|| (btn[i][j + 1].getIcon() == Knight2) || (btn[i][j + 1].getIcon() == Bishop2)
				|| (btn[i][j + 1].getIcon() == Queen2) || (btn[i][j + 1].getIcon() == Rook2)) {
			btn[i][j + 1].setBorder(border);

		}

		if (btn[i][j + 1].getIcon() == null) {
			btn[i][j + 1].setIcon(circle);
		}
		if (btn[i][j + 1].getIcon() != null
				&& ((btn[i][j + 1].getIcon() == Pawn2) || (btn[i][j + 1].getIcon() == Bishop2)
						|| (btn[i][j + 1].getIcon() == King2) || (btn[i][j + 1].getIcon() == Knight2)
						|| (btn[i][j + 1].getIcon() == Queen2) || (btn[i][j + 1].getIcon() == Rook2))) {
			btn[i][j + 1].setBorder(border);
		}

		// ---------------------------------------btn[i][j-1]-----------------------------------
		if ((btn[i][j - 1].getIcon() == Pawn2) || (btn[i][j - 1].getIcon() == King2)
				|| (btn[i][j - 1].getIcon() == Knight2) || (btn[i][j - 1].getIcon() == Bishop2)
				|| (btn[i][j - 1].getIcon() == Queen2) || (btn[i][j - 1].getIcon() == Rook2)) {
			btn[i][j - 1].setBorder(border);

		}

		if (btn[i][j - 1].getIcon() == null) {
			btn[i][j - 1].setIcon(circle);
		}
		if (btn[i][j - 1].getIcon() != null
				&& ((btn[i][j - 1].getIcon() == Pawn2) || (btn[i][j - 1].getIcon() == Bishop2)
						|| (btn[i][j - 1].getIcon() == King2) || (btn[i][j - 1].getIcon() == Knight2)
						|| (btn[i][j - 1].getIcon() == Queen2) || (btn[i][j - 1].getIcon() == Rook2))) {
			btn[i][j - 1].setBorder(border);
		}
	}

	public static void KingChoicWhite(int i, int j) {
		if (i < 7) {
			int m = i + 1;
			for (int n = j - 1; n <= j + 1; n++) {
				if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
						|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
						|| (btn[m][n].getIcon() == Rook1)) {
					btn[m][n].setBorder(border);

				}

				if (btn[m][n].getIcon() == null) {
					btn[m][n].setIcon(circle);
				}
				if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == Bishop1)
						|| (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
						|| (btn[m][n].getIcon() == Queen1) || (btn[m][n].getIcon() == Rook1))) {
					btn[m][n].setBorder(border);
				}

			}
		}
		int p = i - 1;
		for (int k = j - 1; k <= j + 1; k++) {
			if ((btn[p][k].getIcon() == Pawn1) || (btn[p][k].getIcon() == King1) || (btn[p][k].getIcon() == Knight1)
					|| (btn[p][k].getIcon() == Bishop1) || (btn[p][k].getIcon() == Queen1)
					|| (btn[p][k].getIcon() == Rook2)) {
				btn[p][k].setBorder(border);

			}

			if (btn[p][k].getIcon() == null) {
				btn[p][k].setIcon(circle);
			}
			if (btn[p][k].getIcon() != null && ((btn[p][k].getIcon() == Pawn1) || (btn[p][k].getIcon() == Bishop1)
					|| (btn[p][k].getIcon() == King1) || (btn[p][k].getIcon() == Knight1)
					|| (btn[p][k].getIcon() == Queen1) || (btn[p][k].getIcon() == Rook1))) {
				btn[p][k].setBorder(border);
			}

		}

		// ----------------------------------------btn[i][j+1]-----------------------------------
		if ((btn[i][j + 1].getIcon() == Pawn1) || (btn[i][j + 1].getIcon() == King1)
				|| (btn[i][j + 1].getIcon() == Knight1) || (btn[i][j + 1].getIcon() == Bishop1)
				|| (btn[i][j + 1].getIcon() == Queen1) || (btn[i][j + 1].getIcon() == Rook1)) {
			btn[i][j + 1].setBorder(border);

		}

		if (btn[i][j + 1].getIcon() == null) {
			btn[i][j + 1].setIcon(circle);
		}
		if (btn[i][j + 1].getIcon() != null
				&& ((btn[i][j + 1].getIcon() == Pawn1) || (btn[i][j + 1].getIcon() == Bishop1)
						|| (btn[i][j + 1].getIcon() == King1) || (btn[i][j + 1].getIcon() == Knight1)
						|| (btn[i][j + 1].getIcon() == Queen1) || (btn[i][j + 1].getIcon() == Rook1))) {
			btn[i][j + 1].setBorder(border);
		}

		// ---------------------------------------btn[i][j-1]-----------------------------------
		if ((btn[i][j - 1].getIcon() == Pawn1) || (btn[i][j - 1].getIcon() == King1)
				|| (btn[i][j - 1].getIcon() == Knight1) || (btn[i][j - 1].getIcon() == Bishop1)
				|| (btn[i][j - 1].getIcon() == Queen1) || (btn[i][j - 1].getIcon() == Rook1)) {
			btn[i][j - 1].setBorder(border);

		}

		if (btn[i][j - 1].getIcon() == null) {
			btn[i][j - 1].setIcon(circle);
		}
		if (btn[i][j - 1].getIcon() != null
				&& ((btn[i][j - 1].getIcon() == Pawn1) || (btn[i][j - 1].getIcon() == Bishop1)
						|| (btn[i][j - 1].getIcon() == King1) || (btn[i][j - 1].getIcon() == Knight1)
						|| (btn[i][j - 1].getIcon() == Queen1) || (btn[i][j - 1].getIcon() == Rook1))) {
			btn[i][j - 1].setBorder(border);
		}
	}

	public static void KingSpecialWhite1(int i, int j) {
		if (btn[i][j + 1].getIcon() == null && btn[i][j + 2].getIcon() == null) {
			btn[i][j + 2].setIcon(RedCircle);
		}

	}

	public static void KingSpecialWhite2(int i, int j) {
		if (btn[i][j - 1].getIcon() == null && btn[i][j - 2].getIcon() == null && btn[i][j - 3].getIcon() == null) {
			btn[i][j - 2].setIcon(RedCircle);
		}

	}

	public static void KingSpecialBlack1(int i, int j) {
		if (btn[i][j + 1].getIcon() == null && btn[i][j + 2].getIcon() == null) {
			btn[i][j + 2].setIcon(RedCircle);
		}

	}

	public static void KingSpecialBlack2(int i, int j) {
		if (btn[i][j - 1].getIcon() == null && btn[i][j - 2].getIcon() == null && btn[i][j - 3].getIcon() == null) {
			btn[i][j - 2].setIcon(RedCircle);
		}

	}

	public static void BishopChoiceBlack(int i, int j) {
		int n = j + 1;
		for (int m = i + 1; m < btn.length; m++) {
			if (n < 8) {

				if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
						|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
						|| (btn[m][n].getIcon() == Rook2)) {
					btn[m][n].setBorder(border);
					break;
				}
				if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
						|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
						|| (btn[m][n].getIcon() == Rook1)) {

					break;
				}
				if (btn[m][n].getIcon() == null) {
					btn[m][n].setIcon(circle);
				}
				if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == Bishop2)
						|| (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
						|| (btn[m][n].getIcon() == Queen2) || (btn[m][n].getIcon() == Rook2))) {
					btn[m][n].setBorder(border);
				}

				n = n + 1;
			} else {
				break;
			}

		}
		int p1 = j - 1;
		for (int p2 = i + 1; p2 < btn.length; p2++) {
			if (p1 >= 0) {

				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == Bishop2)
								|| (btn[p2][p1].getIcon() == King2) || (btn[p2][p1].getIcon() == Knight2)
								|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2))) {
					btn[p2][p1].setBorder(border);
				}

				p1 = p1 - 1;
			} else {
				break;
			}

		}
		int a = j - 1;
		for (int b = i - 1; b >= 0; b--) {

			if (a >= 0) {

				if ((btn[b][a].getIcon() == Pawn2) || (btn[b][a].getIcon() == King2) || (btn[b][a].getIcon() == Knight2)
						|| (btn[b][a].getIcon() == Bishop2) || (btn[b][a].getIcon() == Queen2)
						|| (btn[b][a].getIcon() == Rook1)) {
					btn[b][a].setBorder(border);
					break;
				}
				if ((btn[b][a].getIcon() == Pawn1) || (btn[b][a].getIcon() == King1) || (btn[b][a].getIcon() == Knight1)
						|| (btn[b][a].getIcon() == Bishop1) || (btn[b][a].getIcon() == Queen1)
						|| (btn[b][a].getIcon() == Rook1)) {

					break;
				}
				if (btn[b][a].getIcon() == null) {
					btn[b][a].setIcon(circle);
				}
				if (btn[b][a].getIcon() != null && ((btn[b][a].getIcon() == Pawn2) || (btn[b][a].getIcon() == Bishop2)
						|| (btn[b][a].getIcon() == King2) || (btn[b][a].getIcon() == Knight2)
						|| (btn[b][a].getIcon() == Queen2) || (btn[b][a].getIcon() == Rook2))) {
					btn[b][a].setBorder(border);
				}
				a = a - 1;
			} else {
				break;
			}

		}
		int v = j + 1;
		for (int x = i - 1; x > 0; x--) {

			if (v < 8) {

				if ((btn[x][v].getIcon() == Pawn2) || (btn[x][v].getIcon() == King2) || (btn[x][v].getIcon() == Knight2)
						|| (btn[x][v].getIcon() == Bishop2) || (btn[x][v].getIcon() == Queen2)
						|| (btn[x][v].getIcon() == Rook2)) {
					btn[x][v].setBorder(border);
					break;
				}
				if ((btn[x][v].getIcon() == Pawn1) || (btn[x][v].getIcon() == King1) || (btn[x][v].getIcon() == Knight1)
						|| (btn[x][v].getIcon() == Bishop1) || (btn[x][v].getIcon() == Queen1)
						|| (btn[x][v].getIcon() == Rook1)) {

					break;
				}
				if (btn[x][v].getIcon() == null) {
					btn[x][v].setIcon(circle);
				}
				if (btn[x][v].getIcon() != null && ((btn[x][v].getIcon() == Pawn2) || (btn[x][v].getIcon() == Bishop2)
						|| (btn[x][v].getIcon() == King2) || (btn[x][v].getIcon() == Knight2)
						|| (btn[x][v].getIcon() == Queen2) || (btn[x][v].getIcon() == Rook2))) {
					btn[x][v].setBorder(border);
				}
				v = v + 1;
			} else {
				break;
			}

		}
	}

	public static void BishopChoiceWhite(int i, int j) {
		int n = j + 1;
		for (int m = i + 1; m < btn.length; m++) {
			if (n < 8) {

				if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
						|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
						|| (btn[m][n].getIcon() == Rook1)) {
					btn[m][n].setBorder(border);
					break;
				}
				if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
						|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
						|| (btn[m][n].getIcon() == Rook2)) {

					break;
				}
				if (btn[m][n].getIcon() == null) {
					btn[m][n].setIcon(circle);
				}
				if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == Bishop1)
						|| (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
						|| (btn[m][n].getIcon() == Queen1) || (btn[m][n].getIcon() == Rook1))) {
					btn[m][n].setBorder(border);
				}

				n = n + 1;
			} else {
				break;
			}

		}
		int p1 = j - 1;
		for (int p2 = i + 1; p2 < btn.length; p2++) {
			if (p1 >= 0) {

				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == Bishop1)
								|| (btn[p2][p1].getIcon() == King1) || (btn[p2][p1].getIcon() == Knight1)
								|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1))) {
					btn[p2][p1].setBorder(border);
				}

				p1 = p1 - 1;
			} else {
				break;
			}

		}
		int a = j - 1;
		for (int b = i - 1; b >= 0; b--) {

			if (a >= 0) {

				if ((btn[b][a].getIcon() == Pawn1) || (btn[b][a].getIcon() == King1) || (btn[b][a].getIcon() == Knight1)
						|| (btn[b][a].getIcon() == Bishop1) || (btn[b][a].getIcon() == Queen1)
						|| (btn[b][a].getIcon() == Rook1)) {
					btn[b][a].setBorder(border);
					break;
				}
				if ((btn[b][a].getIcon() == Pawn2) || (btn[b][a].getIcon() == King2) || (btn[b][a].getIcon() == Knight2)
						|| (btn[b][a].getIcon() == Bishop2) || (btn[b][a].getIcon() == Queen2)
						|| (btn[b][a].getIcon() == Rook2)) {

					break;
				}
				if (btn[b][a].getIcon() == null) {
					btn[b][a].setIcon(circle);
				}
				if (btn[b][a].getIcon() != null && ((btn[b][a].getIcon() == Pawn1) || (btn[b][a].getIcon() == Bishop1)
						|| (btn[b][a].getIcon() == King1) || (btn[b][a].getIcon() == Knight1)
						|| (btn[b][a].getIcon() == Queen1) || (btn[b][a].getIcon() == Rook1))) {
					btn[b][a].setBorder(border);
				}
				a = a - 1;
			} else {
				break;
			}

		}
		int v = j + 1;
		for (int x = i - 1; x > 0; x--) {

			if (v < 8) {

				if ((btn[x][v].getIcon() == Pawn1) || (btn[x][v].getIcon() == King1) || (btn[x][v].getIcon() == Knight1)
						|| (btn[x][v].getIcon() == Bishop1) || (btn[x][v].getIcon() == Queen1)
						|| (btn[x][v].getIcon() == Rook1)) {
					btn[x][v].setBorder(border);
					break;
				}
				if ((btn[x][v].getIcon() == Pawn2) || (btn[x][v].getIcon() == King2) || (btn[x][v].getIcon() == Knight2)
						|| (btn[x][v].getIcon() == Bishop2) || (btn[x][v].getIcon() == Queen2)
						|| (btn[x][v].getIcon() == Rook2)) {

					break;
				}
				if (btn[x][v].getIcon() == null) {
					btn[x][v].setIcon(circle);
					System.out.println("1");
				}
				if (btn[x][v].getIcon() != null && ((btn[x][v].getIcon() == Pawn1) || (btn[x][v].getIcon() == Bishop1)
						|| (btn[x][v].getIcon() == King1) || (btn[x][v].getIcon() == Knight1)
						|| (btn[x][v].getIcon() == Queen1) || (btn[x][v].getIcon() == Rook1))) {
					btn[x][v].setBorder(border);
				}
				v = v + 1;
			} else {
				break;
			}

		}

	}

	public static void QueenChoiceBlack(int i, int j) {
		int n = j;
		for (int m = i + 1; m < btn.length; m++) {
			if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
					|| (btn[m][n].getIcon() == Rook2)) {
				btn[m][n].setBorder(border);
				break;
			}
			if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
					|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
					|| (btn[m][n].getIcon() == Rook1)) {

				break;
			}
			if (btn[m][n].getIcon() == null) {
				btn[m][n].setIcon(circle);
			}
			if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == Bishop2)
					|| (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Queen2) || (btn[m][n].getIcon() == Rook2))) {
				btn[m][n].setBorder(border);
			}

		}
		int m = i;
		for (int k = j + 1; k < btn.length; k++) {
			if ((btn[m][k].getIcon() == Pawn2) || (btn[m][k].getIcon() == King2) || (btn[m][k].getIcon() == Knight2)
					|| (btn[m][k].getIcon() == Bishop2) || (btn[m][k].getIcon() == Queen2)
					|| (btn[m][k].getIcon() == Rook2)) {
				btn[m][k].setBorder(border);
				break;
			}
			if ((btn[m][k].getIcon() == Pawn1) || (btn[m][k].getIcon() == King1) || (btn[m][k].getIcon() == Knight1)
					|| (btn[m][k].getIcon() == Bishop1) || (btn[m][k].getIcon() == Queen1)
					|| (btn[m][k].getIcon() == Rook1)) {

				break;
			}
			if (btn[m][k].getIcon() == null) {
				btn[m][k].setIcon(circle);
			}
			if (btn[m][k].getIcon() != null && ((btn[m][k].getIcon() == Pawn2) || (btn[m][k].getIcon() == Bishop2)
					|| (btn[m][k].getIcon() == King2) || (btn[m][k].getIcon() == Knight2)
					|| (btn[m][k].getIcon() == Queen2) || (btn[m][k].getIcon() == Rook2))) {
				btn[m][k].setBorder(border);
			}

		}
		if (i > 0) {
			int p1 = j;
			for (int p2 = i - 1; p2 >= 0; p2--) {
				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == Bishop2)
								|| (btn[p2][p1].getIcon() == King2) || (btn[p2][p1].getIcon() == Knight2)
								|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2))) {
					btn[p2][p1].setBorder(border);
				}

			}

		}
		if (j > 0) {
			int u1 = i;
			for (int u2 = j - 1; u2 >= 0; u2--) {
				if ((btn[u1][u2].getIcon() == Pawn2) || (btn[u1][u2].getIcon() == King2)
						|| (btn[u1][u2].getIcon() == Knight2) || (btn[u1][u2].getIcon() == Bishop2)
						|| (btn[u1][u2].getIcon() == Queen2) || (btn[u1][u2].getIcon() == Rook2)) {
					btn[u1][u2].setBorder(border);
					break;
				}
				if ((btn[u1][u2].getIcon() == Pawn1) || (btn[u1][u2].getIcon() == King1)
						|| (btn[u1][u2].getIcon() == Knight1) || (btn[u1][u2].getIcon() == Bishop1)
						|| (btn[u1][u2].getIcon() == Queen1) || (btn[u1][u2].getIcon() == Rook1)) {

					break;
				}
				if (btn[u1][u2].getIcon() == null) {
					btn[u1][u2].setIcon(circle);
				}
				if (btn[u1][u2].getIcon() != null
						&& ((btn[u1][u2].getIcon() == Pawn2) || btn[u1][u2].getIcon() == Bishop2)
						|| (btn[u1][u2].getIcon() == King2) || (btn[u1][u2].getIcon() == Knight2)
						|| (btn[u1][u2].getIcon() == Queen2) || (btn[u1][u2].getIcon() == Rook2)) {
					btn[u1][u2].setBorder(border);
				}

			}
		}
		int n1 = j + 1;
		for (int m1 = i + 1; m1 < btn.length; m1++) {
			if (n1 < 8) {

				if ((btn[m1][n1].getIcon() == Pawn2) || (btn[m1][n1].getIcon() == King2)
						|| (btn[m1][n1].getIcon() == Knight2) || (btn[m1][n1].getIcon() == Bishop2)
						|| (btn[m1][n1].getIcon() == Queen2) || (btn[m1][n1].getIcon() == Rook2)) {
					btn[m1][n1].setBorder(border);
					break;
				}
				if ((btn[m1][n1].getIcon() == Pawn1) || (btn[m1][n1].getIcon() == King1)
						|| (btn[m1][n1].getIcon() == Knight1) || (btn[m1][n1].getIcon() == Bishop1)
						|| (btn[m1][n1].getIcon() == Queen1) || (btn[m1][n1].getIcon() == Rook1)) {

					break;
				}
				if (btn[m1][n1].getIcon() == null) {
					btn[m1][n1].setIcon(circle);
				}
				if (btn[m1][n1].getIcon() != null
						&& ((btn[m1][n1].getIcon() == Pawn2) || (btn[m1][n1].getIcon() == Bishop2)
								|| (btn[m1][n1].getIcon() == King2) || (btn[m1][n1].getIcon() == Knight2)
								|| (btn[m1][n1].getIcon() == Queen2) || (btn[m1][n1].getIcon() == Rook2))) {
					btn[m1][n1].setBorder(border);
				}

				n1 = n1 + 1;
			} else {
				break;
			}

		}
		int p1 = j - 1;
		for (int p2 = i + 1; p2 < btn.length; p2++) {
			if (p1 >= 0) {

				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == Bishop2)
								|| (btn[p2][p1].getIcon() == King2) || (btn[p2][p1].getIcon() == Knight2)
								|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2))) {
					btn[p2][p1].setBorder(border);
				}

				p1 = p1 - 1;
			} else {
				break;
			}

		}
		int a = j - 1;
		for (int b = i - 1; b >= 0; b--) {

			if (a >= 0) {

				if ((btn[b][a].getIcon() == Pawn2) || (btn[b][a].getIcon() == King2) || (btn[b][a].getIcon() == Knight2)
						|| (btn[b][a].getIcon() == Bishop2) || (btn[b][a].getIcon() == Queen2)
						|| (btn[b][a].getIcon() == Rook2)) {
					btn[b][a].setBorder(border);
					break;
				}
				if ((btn[b][a].getIcon() == Pawn1) || (btn[b][a].getIcon() == King1) || (btn[b][a].getIcon() == Knight1)
						|| (btn[b][a].getIcon() == Bishop1) || (btn[b][a].getIcon() == Queen1)
						|| (btn[b][a].getIcon() == Rook1)) {

					break;
				}
				if (btn[b][a].getIcon() == null) {
					btn[b][a].setIcon(circle);
				}
				if (btn[b][a].getIcon() != null && ((btn[b][a].getIcon() == Pawn2) || (btn[b][a].getIcon() == Bishop2)
						|| (btn[b][a].getIcon() == King2) || (btn[b][a].getIcon() == Knight2)
						|| (btn[b][a].getIcon() == Queen2) || (btn[b][a].getIcon() == Rook2))) {
					btn[b][a].setBorder(border);
				}
				a = a - 1;
			} else {
				break;
			}

		}
		int v = j + 1;
		for (int x = i - 1; x > 0; x--) {

			if (v < 8) {

				btn[x][v].setIcon(circle);
				if ((btn[x][v].getIcon() == Pawn2) || (btn[x][v].getIcon() == King2) || (btn[x][v].getIcon() == Knight2)
						|| (btn[x][v].getIcon() == Bishop2) || (btn[x][v].getIcon() == Queen2)
						|| (btn[x][v].getIcon() == Rook2)) {
					btn[x][v].setBorder(border);
					break;
				}
				if ((btn[x][v].getIcon() == Pawn1) || (btn[x][v].getIcon() == King1) || (btn[x][v].getIcon() == Knight1)
						|| (btn[x][v].getIcon() == Bishop1) || (btn[x][v].getIcon() == Queen1)
						|| (btn[x][v].getIcon() == Rook1)) {

					break;
				}
				if (btn[x][v].getIcon() == null) {
					btn[x][v].setIcon(circle);
				}
				if (btn[x][v].getIcon() != null && ((btn[x][v].getIcon() == Pawn2) || (btn[x][v].getIcon() == Bishop2)
						|| (btn[x][v].getIcon() == King2) || (btn[x][v].getIcon() == Knight2)
						|| (btn[x][v].getIcon() == Queen2) || (btn[x][v].getIcon() == Rook2))) {
					btn[x][v].setBorder(border);
				}
				v = v + 1;
			} else {
				break;
			}

		}
	}

	public static void QueenChoiceWhite(int i, int j) {
		int n = j;
		for (int m = i + 1; m < btn.length; m++) {
			if ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
					|| (btn[m][n].getIcon() == Bishop1) || (btn[m][n].getIcon() == Queen1)
					|| (btn[m][n].getIcon() == Rook1)) {
				btn[m][n].setBorder(border);
				break;
			}
			if ((btn[m][n].getIcon() == Pawn2) || (btn[m][n].getIcon() == King2) || (btn[m][n].getIcon() == Knight2)
					|| (btn[m][n].getIcon() == Bishop2) || (btn[m][n].getIcon() == Queen2)
					|| (btn[m][n].getIcon() == Rook2)) {

				break;
			}
			if (btn[m][n].getIcon() == null) {
				btn[m][n].setIcon(circle);
			}
			if (btn[m][n].getIcon() != null && ((btn[m][n].getIcon() == Pawn1) || (btn[m][n].getIcon() == Bishop1)
					|| (btn[m][n].getIcon() == King1) || (btn[m][n].getIcon() == Knight1)
					|| (btn[m][n].getIcon() == Queen1) || (btn[m][n].getIcon() == Rook1))) {
				btn[m][n].setBorder(border);
			}

		}
		int m = i;
		for (int k = j + 1; k < btn.length; k++) {
			if ((btn[m][k].getIcon() == Pawn1) || (btn[m][k].getIcon() == King1) || (btn[m][k].getIcon() == Knight1)
					|| (btn[m][k].getIcon() == Bishop1) || (btn[m][k].getIcon() == Queen1)
					|| (btn[m][k].getIcon() == Rook1)) {
				btn[m][k].setBorder(border);
				break;
			}
			if ((btn[m][k].getIcon() == Pawn2) || (btn[m][k].getIcon() == King2) || (btn[m][k].getIcon() == Knight2)
					|| (btn[m][k].getIcon() == Bishop2) || (btn[m][k].getIcon() == Queen2)
					|| (btn[m][k].getIcon() == Rook2)) {

				break;
			}
			if (btn[m][k].getIcon() == null) {
				btn[m][k].setIcon(circle);
			}
			if (btn[m][k].getIcon() != null && ((btn[m][k].getIcon() == Pawn1) || (btn[m][k].getIcon() == Bishop1)
					|| (btn[m][k].getIcon() == King1) || (btn[m][k].getIcon() == Knight1)
					|| (btn[m][k].getIcon() == Queen1) || (btn[m][k].getIcon() == Rook1))) {
				btn[m][k].setBorder(border);
			}

		}
		if (i > 0) {
			int p1 = j;
			for (int p2 = i - 1; p2 >= 0; p2--) {
				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == Bishop1)
								|| (btn[p2][p1].getIcon() == King1) || (btn[p2][p1].getIcon() == Knight1)
								|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1))) {
					btn[p2][p1].setBorder(border);
				}

			}
		}
		if (j > 0) {
			int u1 = i;
			for (int u2 = j - 1; u2 >= 0; u2--) {
				if ((btn[u1][u2].getIcon() == Pawn1) || (btn[u1][u2].getIcon() == King1)
						|| (btn[u1][u2].getIcon() == Knight1) || (btn[u1][u2].getIcon() == Bishop1)
						|| (btn[u1][u2].getIcon() == Queen1) || (btn[u1][u2].getIcon() == Rook1)) {
					btn[u1][u2].setBorder(border);
					break;
				}
				if ((btn[u1][u2].getIcon() == Pawn2) || (btn[u1][u2].getIcon() == King2)
						|| (btn[u1][u2].getIcon() == Knight2) || (btn[u1][u2].getIcon() == Bishop2)
						|| (btn[u1][u2].getIcon() == Queen2) || (btn[u1][u2].getIcon() == Rook2)) {

					break;
				}
				if (btn[u1][u2].getIcon() == null) {
					btn[u1][u2].setIcon(circle);
				}
				if (btn[u1][u2].getIcon() != null
						&& ((btn[u1][u2].getIcon() == Pawn1) || btn[u1][u2].getIcon() == Bishop1)
						|| (btn[u1][u2].getIcon() == King1) || (btn[u1][u2].getIcon() == Knight1)
						|| (btn[u1][u2].getIcon() == Queen1) || (btn[u1][u2].getIcon() == Rook1)) {
					btn[u1][u2].setBorder(border);
				}

			}
		}
		int n1 = j + 1;
		for (int m1 = i + 1; m1 < btn.length; m1++) {
			if (n1 < 8) {

				if ((btn[m1][n1].getIcon() == Pawn1) || (btn[m1][n1].getIcon() == King1)
						|| (btn[m1][n1].getIcon() == Knight1) || (btn[m1][n1].getIcon() == Bishop1)
						|| (btn[m1][n1].getIcon() == Queen1) || (btn[m1][n1].getIcon() == Rook1)) {
					btn[m1][n1].setBorder(border);
					break;
				}
				if ((btn[m1][n1].getIcon() == Pawn2) || (btn[m1][n1].getIcon() == King2)
						|| (btn[m1][n1].getIcon() == Knight2) || (btn[m1][n1].getIcon() == Bishop2)
						|| (btn[m1][n1].getIcon() == Queen2) || (btn[m1][n1].getIcon() == Rook2)) {

					break;
				}
				if (btn[m1][n1].getIcon() == null) {
					btn[m1][n1].setIcon(circle);
				}
				if (btn[m1][n1].getIcon() != null
						&& ((btn[m1][n1].getIcon() == Pawn1) || (btn[m1][n1].getIcon() == Bishop1)
								|| (btn[m1][n1].getIcon() == King1) || (btn[m1][n1].getIcon() == Knight1)
								|| (btn[m1][n1].getIcon() == Queen1) || (btn[m1][n1].getIcon() == Rook1))) {
					btn[m1][n1].setBorder(border);
				}

				n1 = n1 + 1;
			} else {
				break;
			}

		}
		int p1 = j - 1;
		for (int p2 = i + 1; p2 < btn.length; p2++) {
			if (p1 >= 0) {

				if ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == King1)
						|| (btn[p2][p1].getIcon() == Knight1) || (btn[p2][p1].getIcon() == Bishop1)
						|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1)) {
					btn[p2][p1].setBorder(border);
					break;
				}
				if ((btn[p2][p1].getIcon() == Pawn2) || (btn[p2][p1].getIcon() == King2)
						|| (btn[p2][p1].getIcon() == Knight2) || (btn[p2][p1].getIcon() == Bishop2)
						|| (btn[p2][p1].getIcon() == Queen2) || (btn[p2][p1].getIcon() == Rook2)) {

					break;
				}
				if (btn[p2][p1].getIcon() == null) {
					btn[p2][p1].setIcon(circle);
				}
				if (btn[p2][p1].getIcon() != null
						&& ((btn[p2][p1].getIcon() == Pawn1) || (btn[p2][p1].getIcon() == Bishop1)
								|| (btn[p2][p1].getIcon() == King1) || (btn[p2][p1].getIcon() == Knight1)
								|| (btn[p2][p1].getIcon() == Queen1) || (btn[p2][p1].getIcon() == Rook1))) {
					btn[p2][p1].setBorder(border);
				}

				p1 = p1 - 1;
			} else {
				break;
			}

		}
		int a = j - 1;
		for (int b = i - 1; b >= 0; b--) {

			if (a >= 0) {

				if ((btn[b][a].getIcon() == Pawn1) || (btn[b][a].getIcon() == King1) || (btn[b][a].getIcon() == Knight1)
						|| (btn[b][a].getIcon() == Bishop1) || (btn[b][a].getIcon() == Queen1)) {
					btn[b][a].setBorder(border);
					break;
				}
				if ((btn[b][a].getIcon() == Pawn2) || (btn[b][a].getIcon() == King2) || (btn[b][a].getIcon() == Knight2)
						|| (btn[b][a].getIcon() == Bishop2) || (btn[b][a].getIcon() == Queen2)
						|| (btn[b][a].getIcon() == Rook2)) {

					break;
				}
				if (btn[b][a].getIcon() == null) {
					btn[b][a].setIcon(circle);
				}
				if (btn[b][a].getIcon() != null && ((btn[b][a].getIcon() == Pawn1) || (btn[b][a].getIcon() == Bishop1)
						|| (btn[b][a].getIcon() == King1) || (btn[b][a].getIcon() == Knight1)
						|| (btn[b][a].getIcon() == Queen1) || (btn[b][a].getIcon() == Rook1))) {
					btn[b][a].setBorder(border);
				}
				a = a - 1;
			} else {
				break;
			}

		}
		int v = j + 1;
		for (int x = i - 1; x > 0; x--) {

			if (v < 8) {

				if ((btn[x][v].getIcon() == Pawn1) || (btn[x][v].getIcon() == King1) || (btn[x][v].getIcon() == Knight1)
						|| (btn[x][v].getIcon() == Bishop1) || (btn[x][v].getIcon() == Queen1)
						|| (btn[x][v].getIcon() == Rook1)) {
					btn[x][v].setBorder(border);
					break;
				}
				if ((btn[x][v].getIcon() == Pawn2) || (btn[x][v].getIcon() == King2) || (btn[x][v].getIcon() == Knight2)
						|| (btn[x][v].getIcon() == Bishop2) || (btn[x][v].getIcon() == Queen2)
						|| (btn[x][v].getIcon() == Rook2)) {

					break;
				}
				if (btn[x][v].getIcon() == null) {
					btn[x][v].setIcon(circle);
				}
				if (btn[x][v].getIcon() != null && ((btn[x][v].getIcon() == Pawn1) || (btn[x][v].getIcon() == Bishop1)
						|| (btn[x][v].getIcon() == King1) || (btn[x][v].getIcon() == Knight1)
						|| (btn[x][v].getIcon() == Queen1) || (btn[x][v].getIcon() == Rook1))) {
					btn[x][v].setBorder(border);
				}
				v = v + 1;
			} else {
				break;
			}

		}
	}

	public static void changeTurn() {
		turn = !turn;
	}

	private static int playerNumber;

	public static class MainThread extends Thread {
		public void run() {

			while (true) {

				String line = null;
				try {
					line = in.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				System.out.println("Server : " + line);

//				if (line.startsWith("SERVER-BUSY")) {
//					frame.dispose();
//				}
				if (line.startsWith("start")) {
					playerNumber = line.charAt(line.length() - 1) - '0';
					System.out.println(playerNumber);

				}
				if (line.startsWith("KNOWN")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					board(btn[i][j].getIcon());
					btn[i][j].setIcon(btn[previousI][previousJ].getIcon());
					btn[previousI][previousJ].setIcon(null);
					ClearChoice(i, j);
					ClearChoiceRed(i, j);
					ClearBorder(i, j);
					out.println("Turn_Update");
					out.flush();

				}
				if (line.startsWith("ChangeP2")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					String Result = coordinate[5];

					if (Result.equals("king")) {
						btn[i][j].setIcon(King2);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("bishop")) {
						btn[i][j].setIcon(Bishop2);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("queen")) {
						btn[i][j].setIcon(Queen2);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("rook")) {

						btn[i][j].setIcon(Rook2);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("knight")) {
						btn[i][j].setIcon(Knight2);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}

				}

				if (line.startsWith("ChangeP1")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					String Result = coordinate[5];

					if (Result.equals("king")) {
						btn[i][j].setIcon(King1);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("bishop")) {
						btn[i][j].setIcon(Bishop1);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("queen")) {
						btn[i][j].setIcon(Queen1);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("rook")) {
						btn[i][j].setIcon(Rook1);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("knight")) {
						btn[i][j].setIcon(Knight1);
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}

				}
				if (line.startsWith("ChangeP2-Border")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					String Result = coordinate[5];
					if (Result.equals("king")) {
						btn[i][j].setIcon(King2);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("bishop")) {
						btn[i][j].setIcon(Bishop2);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("queen")) {
						btn[i][j].setIcon(Queen2);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("rook")) {

						btn[i][j].setIcon(Rook2);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("knight")) {
						btn[i][j].setIcon(Knight2);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}

				}
				if (line.startsWith("ChangeP1-Border")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					String Result = coordinate[5];
					if (Result.equals("king")) {
						btn[i][j].setIcon(King1);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("bishop")) {
						btn[i][j].setIcon(Bishop1);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("queen")) {
						btn[i][j].setIcon(Queen1);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("rook")) {
						btn[i][j].setIcon(Rook1);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (Result.equals("knight")) {
						btn[i][j].setIcon(Knight1);
						board(btn[i][j].getIcon());
						btn[previousI][previousJ].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
				}

				if (line.startsWith("NEW-BTN")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					btn[i][j].setIcon(btn[previousI][previousJ].getIcon());

					btn[previousI][previousJ].setIcon(null);
					ClearChoice(i, j);
					ClearChoiceRed(i, j);
					ClearBorder(i, j);
					out.println("Turn_Update");
					out.flush();
				}
				if (line.startsWith("RED_BACK")) {
					String[] coordinate = line.split(" ");
					int i = Integer.parseInt(coordinate[1]);
					int j = Integer.parseInt(coordinate[2]);
					int previousI = Integer.parseInt(coordinate[3]);
					int previousJ = Integer.parseInt(coordinate[4]);
					if (btn[i][j] == btn[7][6]) {

						btn[i][j].setIcon(King2);
						btn[previousI][previousJ].setIcon(null);
						btn[i][j - 1].setIcon(Rook2);
						btn[7][7].setIcon(null);
						ClearChoice(i, j);
					
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (btn[i][j] == btn[7][2]) {
						btn[i][j].setIcon(King2);
						btn[previousI][previousJ].setIcon(null);
						btn[i][j + 1].setIcon(Rook2);
						btn[7][0].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (btn[i][j] == btn[0][6]) {

						btn[i][j].setIcon(King1);
						btn[previousI][previousJ].setIcon(null);
						btn[i][j - 1].setIcon(Rook1);
						btn[0][7].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
					if (btn[i][j] == btn[0][2]) {
						btn[i][j].setIcon(King1);
						btn[previousI][previousJ].setIcon(null);
						btn[i][j + 1].setIcon(Rook1);
						btn[0][0].setIcon(null);
						ClearChoice(i, j);
						ClearBorder(i, j);
						out.println("Turn_Update");
						out.flush();
					}
				}
				if (line.startsWith("TURN")) {
					turn = ((line.charAt(line.length() - 1) - '0') == 2) ? false : true;
					System.out.println("CLIENT : " + turn);
					checkTurn(turn, lblTURN);
				}
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainThread mainThread = new MainThread();
					Main frame = new Main();
					frame.setVisible(true);
					mainThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Main() throws IOException {
		socket = new Socket("localhost", 123);

		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1328, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(567, 13, 699, 696);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(8, 8, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 13, 546, 543);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(8, 8, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 569, 497, 132);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// JLabel lblTURN = new JLabel("White Turn");
		lblTURN.setBounds(67, 41, 151, 43);
		panel_2.add(lblTURN);

//		JLabel lblNewLabel = new JLabel("White Turn");
//		lblNewLabel.setBounds(38, 37, 56, 16);
//		panel_2.add(lblNewLabel);

		for (int i = 0; i < btnOverCome.length; i++) {
			for (int j = 0; j < btnOverCome.length; j++) {
				btnOverCome[i][j] = new JButton();
				btnOverCome[i][j].setBounds(0, 0, 100, 100);
				panel_1.add(btnOverCome[i][j]);
			}
		}

		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				btn[i][j] = new JButton();
				btn[i][j].setBounds(0, 0, 100, 100);
				btn[i][j].setName(Integer.toString(i) + "_" + Integer.toString(j));
				panel.add(btn[i][j]);
				if ((i + j) % 2 != 0) {
					btn[i][j].setBackground(new Color(51, 204, 255));
				}
				if ((i + j) % 2 == 0) {
					btn[i][j].setBackground(new Color(255, 255, 255));
				}

			}
		}

		// ---------------------setIcone the buttons------------------------

		btn[0][0].setIcon(Rook1);
		btn[0][1].setIcon(Knight1);
		btn[0][2].setIcon(Bishop1);
		btn[0][3].setIcon(Queen1);
		btn[0][4].setIcon(King1);
		btn[0][5].setIcon(Bishop1);
		btn[0][6].setIcon(Knight1);
		btn[0][7].setIcon(Rook1);
		btn[1][0].setIcon(Pawn1);
		btn[1][1].setIcon(Pawn1);
		btn[1][2].setIcon(Pawn1);
		btn[1][3].setIcon(Pawn1);
		btn[1][4].setIcon(Pawn1);
		btn[1][5].setIcon(Pawn1);
		btn[1][6].setIcon(Pawn1);
		btn[1][7].setIcon(Pawn1);

		btn[7][0].setIcon(Rook2);
		btn[7][1].setIcon(Knight2);
		btn[7][2].setIcon(Bishop2);
		btn[7][3].setIcon(Queen2);
		btn[7][4].setIcon(King2);
		btn[7][5].setIcon(Bishop2);
		btn[7][6].setIcon(Knight2);
		btn[7][7].setIcon(Rook2);
		btn[6][0].setIcon(Pawn2);
		btn[6][1].setIcon(Pawn2);
		btn[6][2].setIcon(Pawn2);
		btn[6][3].setIcon(Pawn2);
		btn[6][4].setIcon(Pawn2);
		btn[6][5].setIcon(Pawn2);
		btn[6][6].setIcon(Pawn2);
		btn[6][7].setIcon(Pawn2);

		// ----------------------------------------------------------------------------

		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn.length; j++) {
				final int tmpI = i;
				final int tmpJ = j;
				btn[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if ((playerNumber == 1 && turn) || (playerNumber == 2 && !turn)) {
							
//						out.println("BTN-CLICKED" + " " + ((JButton) e.getSource()).getName() +" "+String.valueOf(turn));
//						out.flush();
							MovesI.add(tmpI);
							MovesJ.add(tmpJ);
							if (btn[tmpI][tmpJ].getIcon() == Pawn1 && playerNumber == 2) {// Black pawn
																							// move
								if (e.getSource() == btn[1][0] || e.getSource() == btn[1][1]
										|| e.getSource() == btn[1][2] || e.getSource() == btn[1][3]
										|| e.getSource() == btn[1][4] || e.getSource() == btn[1][5]
										|| e.getSource() == btn[1][6] || e.getSource() == btn[1][7]) {

									PawnChoiceBlackFirst(tmpI, tmpJ);
									PawnChoiceBlackAttack(tmpI, tmpJ);

								} else {
									PawnChoiceBlack(tmpI, tmpJ);
									PawnChoiceBlackAttack(tmpI, tmpJ);

								}

							}
							if (btn[tmpI][tmpJ].getIcon() == Pawn2 && playerNumber == 1) {// White pawn move
								if (e.getSource() == btn[6][0] || e.getSource() == btn[6][1]
										|| e.getSource() == btn[6][2] || e.getSource() == btn[6][3]
										|| e.getSource() == btn[6][4] || e.getSource() == btn[6][5]
										|| e.getSource() == btn[6][6] || e.getSource() == btn[6][7]) {

									PawnChoiceWhiteFirst(tmpI, tmpJ);
									PawnChoiceWhiteAttack(tmpI, tmpJ);

								} else {
									PawnChoiceWhite(tmpI, tmpJ);
									PawnChoiceWhiteAttack(tmpI, tmpJ);

								}

							}
							if (btn[tmpI][tmpJ].getIcon() == Rook1 || btn[tmpI][tmpJ].getIcon() == Rook2) {// Rook move
								if (btn[tmpI][tmpJ].getIcon() == Rook1 && playerNumber == 2) {
									RookChoiceBlack(tmpI, tmpJ);

								}
								if (btn[tmpI][tmpJ].getIcon() == Rook2 && playerNumber == 1) {
									RookChoiceWhite(tmpI, tmpJ);

								}

							}
							if (btn[tmpI][tmpJ].getIcon() == Knight1 || btn[tmpI][tmpJ].getIcon() == Knight2) {// Knight
																												// move
								if (btn[tmpI][tmpJ].getIcon() == Knight1 && playerNumber == 2) {
									KnightChoiceBlack(tmpI, tmpJ);

								}
								if (btn[tmpI][tmpJ].getIcon() == Knight2 && playerNumber == 1) {
									KnightChoiceWhite(tmpI, tmpJ);

								}

							}
							if (btn[tmpI][tmpJ].getIcon() == King1 || btn[tmpI][tmpJ].getIcon() == King2) {// king move
								if (btn[tmpI][tmpJ].getIcon() == King1 && playerNumber == 2) {

									if ((e.getSource() == btn[0][4] && btn[0][7].getIcon() == Rook1
											&& btn[0][5].getIcon() == null && btn[0][6].getIcon() == null)
											|| (e.getSource() == btn[0][4] && btn[0][0].getIcon() == Rook1
													&& btn[0][3].getIcon() == null && btn[0][2].getIcon() == null
													&& btn[0][1].getIcon() == null)) {

										if ((e.getSource() == btn[0][4] && btn[0][7].getIcon() == Rook1
												&& btn[0][5].getIcon() == null && btn[0][6].getIcon() == null)) {

											KingSpecialBlack1(0, 4);
											KingChoicBlack(tmpI, tmpJ);

										}
										if ((e.getSource() == btn[0][4] && btn[0][0].getIcon() == Rook1
												&& btn[0][3].getIcon() == null && btn[0][2].getIcon() == null
												&& btn[0][1].getIcon() == null)) {
											KingSpecialBlack2(0, 4);
											KingChoicBlack(tmpI, tmpJ);

										}
									} else {
										KingChoicBlack(tmpI, tmpJ);

									}

								}

								if (btn[tmpI][tmpJ].getIcon() == King2 && playerNumber == 1) {

									if ((e.getSource() == btn[7][4] && btn[7][7].getIcon() == Rook2
											&& btn[7][5].getIcon() == null && btn[7][6].getIcon() == null)
											|| (e.getSource() == btn[7][4] && btn[7][0].getIcon() == Rook2
													&& btn[7][3].getIcon() == null && btn[7][2].getIcon() == null
													&& btn[7][1].getIcon() == null)) {
										
										if ((e.getSource() == btn[7][4] && btn[7][7].getIcon() == Rook2
												&& btn[7][5].getIcon() == null && btn[7][6].getIcon() == null)) {
										
											KingSpecialWhite1(7, 4);
											KingChoicWhite(tmpI, tmpJ);

										}
										if ((e.getSource() == btn[7][4] && btn[7][0].getIcon() == Rook2
												&& btn[7][3].getIcon() == null && btn[7][2].getIcon() == null
												&& btn[7][1].getIcon() == null)) {
											KingSpecialWhite2(7, 4);
											KingChoicWhite(tmpI, tmpJ);

										}
									} else {
										KingChoicWhite(tmpI, tmpJ);

									}

								}



							}
							if (btn[tmpI][tmpJ].getIcon() == Bishop1 || btn[tmpI][tmpJ].getIcon() == Bishop2) {

								if (btn[tmpI][tmpJ].getIcon() == Bishop1 && playerNumber == 2) {
									BishopChoiceBlack(tmpI, tmpJ);

								}
								if (btn[tmpI][tmpJ].getIcon() == Bishop2 && playerNumber == 1) {
									BishopChoiceWhite(tmpI, tmpJ);

								} // Bishop move

							}
							if (btn[tmpI][tmpJ].getIcon() == Queen1 || btn[tmpI][tmpJ].getIcon() == Queen2) {

								if (btn[tmpI][tmpJ].getIcon() == Queen1 && playerNumber == 2) {
									QueenChoiceBlack(tmpI, tmpJ);

								}
								if (btn[tmpI][tmpJ].getIcon() == Queen2 && playerNumber == 1) {
									QueenChoiceWhite(tmpI, tmpJ);

								}

							} // Queen move
							if (btn[tmpI][tmpJ].getIcon() == RedCircle) {
								out.println("RED" + " " + Integer.toString(tmpI) + " " + Integer.toString(tmpJ) + " "
										+ Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
										+ Integer.toString(MovesJ.get(MovesJ.size() - 2)));
								out.flush();
							}
							if (btn[tmpI][tmpJ].getIcon() == circle || btn[tmpI][tmpJ].getBorder() == border) {
								if (btn[tmpI][tmpJ].getIcon() == circle) {

									if (((e.getSource() == btn[0][0] || e.getSource() == btn[0][1]
											|| e.getSource() == btn[0][2] || e.getSource() == btn[0][3]
											|| e.getSource() == btn[0][4] || e.getSource() == btn[0][5]
											|| e.getSource() == btn[0][6] || e.getSource() == btn[0][7])
											&& (btn[tmpI + 1][tmpJ].getIcon() == Pawn2))
											|| ((e.getSource() == btn[7][0] || e.getSource() == btn[7][1]
													|| e.getSource() == btn[7][2] || e.getSource() == btn[7][3]
													|| e.getSource() == btn[7][4] || e.getSource() == btn[7][5]
													|| e.getSource() == btn[7][6] || e.getSource() == btn[7][7])
													&& (btn[tmpI - 1][tmpJ].getIcon() == Pawn1))) {

										if (((e.getSource() == btn[0][0] || e.getSource() == btn[0][1]
												|| e.getSource() == btn[0][2] || e.getSource() == btn[0][3]
												|| e.getSource() == btn[0][4] || e.getSource() == btn[0][5]
												|| e.getSource() == btn[0][6] || e.getSource() == btn[0][7])
												&& (btn[tmpI + 1][tmpJ].getIcon() == Pawn2))) {
											String result;
											result = JOptionPane.showInputDialog(null, "Enter Your desired piece");

											out.println("Change-Pawn2" + " " + Integer.toString(tmpI) + " "
													+ Integer.toString(tmpJ) + " "
													+ Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
													+ Integer.toString(MovesJ.get(MovesJ.size() - 2)) + " "
													+ String.valueOf(turn) + " " + result);
											out.flush();
										}
										if (((e.getSource() == btn[7][0] || e.getSource() == btn[7][1]
												|| e.getSource() == btn[7][2] || e.getSource() == btn[7][3]
												|| e.getSource() == btn[7][4] || e.getSource() == btn[7][5]
												|| e.getSource() == btn[7][6] || e.getSource() == btn[7][7])
												&& (btn[tmpI - 1][tmpJ].getIcon() == Pawn1))) {
											String result;
											result = JOptionPane.showInputDialog(null, "Enter Your desired piece");
											out.println("Change-Pawn1" + " " + Integer.toString(tmpI) + " "
													+ Integer.toString(tmpJ) + " "
													+ Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
													+ Integer.toString(MovesJ.get(MovesJ.size() - 2)) + " "
													+ String.valueOf(turn) + " " + result);
											out.flush();

										}
									} else {

										out.println("BTN-CLICKED" + " " + Integer.toString(tmpI) + " "
												+ Integer.toString(tmpJ) + " "
												+ Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
												+ Integer.toString(MovesJ.get(MovesJ.size() - 2)) + " "
												+ String.valueOf(turn));
										out.flush();
									}
								}


								if (btn[tmpI][tmpJ].getBorder() == border) {
									changeTurn();

									if (((e.getSource() == btn[0][0] || e.getSource() == btn[0][1]
											|| e.getSource() == btn[0][2] || e.getSource() == btn[0][3]
											|| e.getSource() == btn[0][4] || e.getSource() == btn[0][5]
											|| e.getSource() == btn[0][6] || e.getSource() == btn[0][7])
											&& (btn[tmpI + 1][tmpJ + 1].getIcon() == Pawn2
													|| btn[tmpI + 1][tmpJ - 1].getIcon() == Pawn2))
											|| ((e.getSource() == btn[7][0] || e.getSource() == btn[7][1]
													|| e.getSource() == btn[7][2] || e.getSource() == btn[7][3]
													|| e.getSource() == btn[7][4] || e.getSource() == btn[7][5]
													|| e.getSource() == btn[7][6] || e.getSource() == btn[7][7])
													&& (btn[tmpI - 1][tmpJ + 1].getIcon() == Pawn1
															|| btn[tmpI - 1][tmpJ - 1].getIcon() == Pawn1))) {
										if (((e.getSource() == btn[0][0] || e.getSource() == btn[0][1]
												|| e.getSource() == btn[0][2] || e.getSource() == btn[0][3]
												|| e.getSource() == btn[0][4] || e.getSource() == btn[0][5]
												|| e.getSource() == btn[0][6] || e.getSource() == btn[0][7])
												&& (btn[tmpI + 1][tmpJ + 1].getIcon() == Pawn2
														|| btn[tmpI + 1][tmpJ - 1].getIcon() == Pawn2))) {
											String result;
											result = JOptionPane.showInputDialog(null, "Enter Your desired piece");
											out.println("Change-Pawn2-Border" + " " + Integer.toString(tmpI) + " "
													+ Integer.toString(tmpJ) + " "
													+ Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
													+ Integer.toString(MovesJ.get(MovesJ.size() - 2)) + " "
													+ String.valueOf(turn) + " " + result);
											out.flush();
										}
										if (((e.getSource() == btn[7][0] || e.getSource() == btn[7][1]
												|| e.getSource() == btn[7][2] || e.getSource() == btn[7][3]
												|| e.getSource() == btn[7][4] || e.getSource() == btn[7][5]
												|| e.getSource() == btn[7][6] || e.getSource() == btn[7][7])
												&& (btn[tmpI - 1][tmpJ + 1].getIcon() == Pawn1
														|| btn[tmpI - 1][tmpJ - 1].getIcon() == Pawn1))) {
											String result;
											result = JOptionPane.showInputDialog(null, "Enter Your desired piece");
											out.println("Change-Pawn1-Border" + " " + Integer.toString(tmpI) + " "
													+ Integer.toString(tmpJ) + " "
													+ Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
													+ Integer.toString(MovesJ.get(MovesJ.size() - 2)) + " "
													+ String.valueOf(turn) + " " + result);
											out.flush();
										}
									} else {

										out.println(
												"DEFAULT" + " " + Integer.toString(tmpI) + " " + Integer.toString(tmpJ)
														+ " " + Integer.toString(MovesI.get(MovesI.size() - 2)) + " "
														+ Integer.toString(MovesJ.get(MovesJ.size() - 2)) + " "
														+ String.valueOf(turn));
										out.flush();
									}
								}

							}

						}

					}

				});
			}
		}

	}
}
