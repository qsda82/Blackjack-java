
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;

class rand {
	public static LinkedList<String> p_cards = new LinkedList<String>();// 玩家牌點數陣列
	public static LinkedList<String> p_flowers = new LinkedList<String>();// 玩家牌花色陣列
	public static LinkedList<String> c_cards = new LinkedList<String>();// 電腦牌點數陣列
	public static LinkedList<String> c_flowers = new LinkedList<String>();// 電腦牌花色陣列
	public static LinkedList<Integer> p_count = new LinkedList<Integer>();// 玩家牌點數總數
	public static LinkedList<Integer> c_count = new LinkedList<Integer>();// 電腦牌點數總數

	/* 將list設初始值 */
	public void p_listset() {
		p_count.add(0, 0);
	}

	public void c_listset() {
		c_count.add(0, 0);
	}

	/*** 玩家牌的初始值設定 ***/
	public void player_rand() {
		Random num = new Random();// 使用亂數的api

		int player_poker_num_int = num.nextInt(13) + 1;// 隨機1~13中選一個數字
		int count = 0;
		p_listset();// 初始陣列

		/* 將選出的隨機整數轉成String並存入list */
		for (int i = 1; i <= 10; i++) {
			if (player_poker_num_int == i)
				p_cards.add(Integer.toString(i));
		}
		/* 如果牌是J，其值為10 */
		if (player_poker_num_int == 11) {
			p_cards.add("J");
			player_poker_num_int = 10;
		}
		/* 如果牌是Q，其值為10 */
		if (player_poker_num_int == 12) {
			p_cards.add("Q");
			player_poker_num_int = 10;
		}
		/* 如果牌是K，其值為10 */
		if (player_poker_num_int == 13) {
			p_cards.add("K");
			player_poker_num_int = 10;
		}

		count = p_count.getLast() + player_poker_num_int;// 計算目前牌的總點數
		p_count.add(count);// 加入玩家點數陣列
		int flower = num.nextInt(4) + 1;// 隨機1~4中選一個數字代表花色
		/* 將花色加入玩家list中,，若亂數值為1則為菱形，2為愛心，3為黑桃，4為梅花 */
		if (flower == 1)
			p_flowers.add("菱形");
		else if (flower == 2)
			p_flowers.add("愛心");
		else if (flower == 3)
			p_flowers.add("黑陶");
		else if (flower == 4)
			p_flowers.add("梅花");

	}

	/*** 電腦牌的初始值設定 ***/
	public void computer_rand() {
		Random num = new Random();// 使用亂數的api
		int computer_poker_num_int = num.nextInt(13) + 1;// 隨機1~13中選一個數字
		int count = 0;
		c_listset();// 初始陣列
		/* 將選出的隨機整數轉成String並加入list */
		for (int i = 1; i <= 10; i++) {
			if (computer_poker_num_int == i)
				c_cards.add(Integer.toString(i));
		}
		/* 如果牌是J，其值為10 */
		if (computer_poker_num_int == 11) {
			c_cards.add("J");
			computer_poker_num_int = 10;
		}
		/* 如果牌是Q，其值為10 */
		if (computer_poker_num_int == 12) {
			c_cards.add("Q");
			computer_poker_num_int = 10;
		}
		/* 如果牌是K，其值為10 */
		if (computer_poker_num_int == 13) {
			c_cards.add("K");
			computer_poker_num_int = 10;
		}
		count = c_count.getLast() + computer_poker_num_int;// 計算目前牌的總點數
		c_count.add(count);// 加入電腦點數陣列
		int flower = num.nextInt(4) + 1;// 隨機1~4中選一個數字代表花色
		/* 將花色加入玩家list中,，若亂數值為1則為菱形，2為愛心，3為黑桃，4為梅花 */
		if (flower == 1)
			c_flowers.add("菱形");
		else if (flower == 2)
			c_flowers.add("愛心");
		else if (flower == 3)
			c_flowers.add("黑陶");
		else if (flower == 4)
			c_flowers.add("梅花");
	}
}

class gui_show extends JFrame {

	int count = 0;
	/* 物件的宣告 */
	static int explode = 0;
	static int c_add = 0;
	static int p_add = 0;// 建立紀錄是否超過21點
	static int no_add = 0;// 建立紀錄是否超過21點
	static int round = 0;// 建立紀錄回合變數
	JLabel intial_lab[] = new JLabel[5];// 建立初始電腦和玩家的兩張牌
	JLabel p_add_lab[] = new JLabel[3];// 建立後3張玩家的牌
	JLabel c_add_lab[] = new JLabel[3];// 建立後3張電腦的牌
	JLabel round_show[] = new JLabel[3];// 建立顯示回合的LABEL
	
	rand rand_card = new rand();// 使用亂數牌的物件(上方CLASS)
	Container cp = this.getContentPane();// 建立容器
	

	public gui_show(JLabel a) { // 建構元1

	}

	/* 介面顯示 */
	public gui_show() { // 建構元
		super("21點");// 視窗名
		JLabel dark1_show = new JLabel();// 建立LABEL顯示玩家底牌
		dark1_show.setBounds(80, -125, 300, 300);// 調整大小位置
		cp.add(dark1_show);// 放入容器
		dark1_show.setText("(底牌:" + rand.p_cards.getFirst() + ")");// 設定LABEL的字
		dark1_show.setFont(new java.awt.Font("Dialog", 1, 25));// 設定字型大小
		for (int i = 0; i <= 4; i++) {
			intial_lab[i] = new JLabel();// NEW我們的4張初始牌
		}
		for (int i = 0; i <= 2; i++) {
			round_show[i] = new JLabel();// NEW我們的回合顯示label
		}
		cp.setLayout(null);// 讓我們可以調整牌的大小
		JLabel player = new JLabel();// 顯示玩家
		JLabel computer = new JLabel();// 顯示電腦
		JButton btn = new JButton("加牌");// 建立加牌按鈕
		JButton no_btn = new JButton("不加牌");// 建立不加牌按鈕
		JButton close_btn = new JButton("重新開始");// 建立關閉按鈕
		player.setText("玩家:");// 設定LABEL的字
		computer.setText("電腦:");// 設定LABEL的字
		player.setBounds(15, -125, 300, 300);// 設定大小位置
		computer.setBounds(15, 380, 300, 300);// 設定大小位置
		btn.setBounds(800, 350, 100, 40);// 調整加牌按鈕位置大小
		no_btn.setBounds(700, 350, 100, 40);// 調整不加牌按鈕位置大小
		close_btn.setBounds(600, 350, 100, 40);// 調整關閉按鈕位置大小
		/** 字形顏色 **/
		player.setForeground(Color.white);
		computer.setForeground(Color.white);
		dark1_show.setForeground(Color.white);
		/*** 設定字型大小 ***/
		player.setFont(new java.awt.Font("Dialog", 1, 25));
		computer.setFont(new java.awt.Font("Dialog", 1, 25));
		btn.setFont(new java.awt.Font("Dialog", 1, 15));
		no_btn.setFont(new java.awt.Font("Dialog", 1, 15));
		close_btn.setFont(new java.awt.Font("Dialog", 1, 15));
		intial_lab[0].setBounds(10, 0, 300, 300);// 調整玩家底牌位置
		intial_lab[1].setBounds(150, 0, 300, 300);// 調整玩家首牌位置
		intial_lab[2].setBounds(10, 500, 300, 300);// 調整電腦底牌位置
		intial_lab[3].setBounds(150, 500, 300, 300);// 調整電腦首牌位置
		/* 將初始牌與按鈕加入視窗 */
		cp.add(player);
		cp.add(computer);
		cp.add(btn);
		cp.add(no_btn);
		cp.add(close_btn);
		cp.add(intial_lab[0]);
		cp.add(intial_lab[1]);
		cp.add(intial_lab[2]);
		cp.add(intial_lab[3]);
		/* 設定視窗大小和讓人看見 */
		this.setBounds(450, 100, 1000, 850);// 設定視窗大小位置
		this.setVisible(true);// 讓視窗顯示出來
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 關閉

		/* 讓按鈕被監聽，也就是按下按鈕後將會發生後續的動作 */
		btn.addActionListener(new actLis1(btn));
		no_btn.addActionListener(new actLis1(no_btn, btn));
		close_btn.addActionListener(new actLis1(close_btn, no_btn, btn));
		/* 匯入玩家底牌圖片 */
		ImageIcon p_imgz[] = new ImageIcon[2];
		p_imgz[0] = new ImageIcon("D:\\桌面\\撲克牌圖檔\\底牌.jpg");// 匯入檔案
		Image p_image = p_imgz[0].getImage();// 得到其檔案
		Image p_newing = p_image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 改變牌的大小
		p_imgz[0] = new ImageIcon(p_newing);// 將新大小的牌回傳給原本檔案的陣列值
		intial_lab[0].setIcon(p_imgz[0]);// 在LABEL上設定圖片

		/* 將底牌變成背景，讓其他牌可以覆蓋在上面 */
		this.getLayeredPane().add(intial_lab[0], new Integer(Integer.MIN_VALUE));
		intial_lab[0].setBounds(10, 50, p_imgz[0].getIconWidth(), p_imgz[0].getIconHeight());
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);

		/* 匯入玩家首牌圖片 */
		p_imgz[1] = new ImageIcon("D:\\桌面\\撲克牌圖檔\\" + rand.p_flowers.get(1) + rand.p_cards.get(1) + ".gif");// 匯入檔案
		Image p_image2 = p_imgz[1].getImage();// 得到其檔案
		Image p_newing2 = p_image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 改變牌的大小
		p_imgz[1] = new ImageIcon(p_newing2);// 將新大小的牌回傳給原本檔案的陣列值
		intial_lab[1].setIcon(p_imgz[1]);// 在LABEL上設定圖片
		/* 匯入電腦底牌圖片 */
		ImageIcon c_imgz[] = new ImageIcon[2];
		c_imgz[0] = new ImageIcon("D:\\桌面\\撲克牌圖檔\\底牌.jpg");// 匯入檔案
		Image c_image = c_imgz[0].getImage();// 得到其檔案
		Image c_newing = c_image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 改變牌的大小
		c_imgz[0] = new ImageIcon(c_newing);// 將新大小的牌回傳給原本檔案的陣列值
		intial_lab[2].setIcon(c_imgz[0]);// 在LABEL上設定圖片

		/* 將底牌變成背景，讓其他牌可以覆蓋在上面 */
		this.getLayeredPane().add(intial_lab[2], new Integer(Integer.MIN_VALUE));
		intial_lab[2].setBounds(10, 550, c_imgz[0].getIconWidth(), c_imgz[0].getIconHeight());
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);

		/* 匯入玩家首牌圖片 */
		c_imgz[1] = new ImageIcon("D:\\桌面\\撲克牌圖檔\\" + rand.c_flowers.get(1) + rand.c_cards.get(1) + ".gif");// 匯入檔案
		Image c_image2 = c_imgz[1].getImage();// 得到其檔案
		Image c_newing2 = c_image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 改變牌的大小
		c_imgz[1] = new ImageIcon(c_newing2);// 將新大小的牌回傳給原本檔案的陣列值
		intial_lab[3].setIcon(c_imgz[1]);// 在LABEL上設定圖片

		/** 設置背景圖片 **/
		ImageIcon background = new ImageIcon("D:\\桌面\\撲克牌圖檔\\背景.jpg");// 匯入檔案
		JLabel Jbackground = new JLabel(background);// 將檔案做成label
		Image back = background.getImage();// 得到其檔案
		Image back_new = back.getScaledInstance(1000, 850, java.awt.Image.SCALE_SMOOTH);// 改變牌的大小
		background = new ImageIcon(back_new);// 將新大小的牌回傳給原本檔案的陣列值
		Jbackground.setIcon(background);// 在LABEL上設定圖片
		this.getLayeredPane().add(Jbackground, new Integer(Integer.MIN_VALUE));
		Jbackground.setBounds(0, 0, 1000, 850);// 位置
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);
	}

	/* 顯示牌組 */
	class actLis1 implements ActionListener {
		JButton add_btn;// 加牌按鈕
		JButton no_add_btn;// 不要牌按鈕
		JButton close_add_btn;// 重新開始按鈕
		rand add = new rand();// 與 class rand建立連結

		public actLis1(JButton btn) {// 建構元
			add_btn = btn;
		}

		public actLis1(JButton btn1, JButton btn2) {// 建構元
			no_add_btn = btn1;
		}

		public actLis1(JButton btn1, JButton btn2, JButton btn3) {// 建構元
			close_add_btn = btn1;
		}

		/* 按下按鈕後顯示玩家的牌，並讓電腦自己選牌 */
		public void actionPerformed(ActionEvent e) {// 監聽的函式
			JButton btn1 = (JButton) e.getSource();// 確認是哪個按鈕被按
            /*顯示回合數label設定*/
			for (int i = 0; i <= 2; i++) {
				round_show[i].setBounds(50, 120, 300, 300);// 設定大小
				cp.add(round_show[i]);// 加入容器
				round_show[i].setForeground(Color.white);//字形顏色
				round_show[i].setFont(new java.awt.Font("Dialog", 1, 25));// 設定字型大小
			}

			if (explode != 1) {// 如果沒有人爆掉就執行,有人爆掉就不會執行

				if (btn1 == add_btn) {// 如果按下加牌按鈕
					if (no_add != 1) { // 如果沒有按下不加牌按鈕
						round = (count + 1);// 記錄第幾輪
						/* 建立label顯示第幾回合 */
						if (count == 0) {//若為首回合
							round_show[count].setText("第" + round + "回合");//顯示回合數
						} else if (count > 0) {//若為第2回合or第3回合
							round_show[count].setText("第" + round + "回合");//顯示回合數
							/*將之前的回合數label刪除，讓他不再顯示*/
							for (int i = count; i >= 1; i--) {
								cp.remove(round_show[i - 1]);
							}
							cp.revalidate();
							cp.repaint();
						}

						if (round <= 3) {// 判斷有無超過5張
							add.player_rand();// 玩家抽牌
							for (int i = 0; i <= 2; i++) {// 建立牌的物件
								p_add_lab[i] = new JLabel();

							}
							cp.setLayout(null);// 讓我們可以隨意調整容器大小

							/* 設定牌大小位置並加入視窗 */
							p_add_lab[count].setBounds(290 + (count * 140), 0, 300, 300);
							cp.add(p_add_lab[count]);

							/* 同上方程式碼 */
							ImageIcon imgz = new ImageIcon();
							imgz = new ImageIcon("D:\\桌面\\撲克牌圖檔\\" + add.p_flowers.get((count + 2))
									+ add.p_cards.get((count + 2)) + ".gif");// 匯入檔案
							Image image = imgz.getImage();// 得到其檔案
							Image newing = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 設定圖片大小
							imgz = new ImageIcon(newing);// 將新大小的牌回傳給原本檔案的陣列值
							p_add_lab[count].setIcon(imgz);// 在LABEL上設定圖片

							if (c_add != 1) {// 若電腦還要牌
								c_judge(count);// 換電腦選擇要不要牌
							}

							count++;// 紀錄第幾次點及按鈕，點一次就是一回合

						}
						if (round == 3) {// 若超過5張還沒輸=>過五關
							/* 建立label顯示"過五關"和顯示底牌 */
							if (add.p_count.getLast() <= 21) {// 若沒超過21點
								explode = 1;// 讓加牌和不加牌按鈕無法作用
								JLabel text = new JLabel();// 建立新label顯示過五關
								cp.setLayout(null);// 讓我們可以隨意更改容易大小
								/* 設定label大小 */
								text.setBounds(30, 200, 800, 300);// 設定位置
								text.setFont(new java.awt.Font("Dialog", 1, 25));// 設定字型大小

								/* 加入視窗中 */
								cp.add(text);
								/* label要顯示的字 */
								text.setText("過五關!");
								text.setForeground(Color.white);
								/* 顯示底牌 */
								card_show(0, add.p_flowers.getFirst(), add.p_cards.getFirst());
								card_show(500, add.c_flowers.getFirst(), add.c_cards.getFirst());
								JOptionPane.showMessageDialog(null, "你贏了");// 提示視窗顯示你贏了
							}
						}
					}
				} else if (btn1 == no_add_btn) {// 玩家選擇不要牌
					no_add = 1;// 讓加牌按鈕無法作用
					p_add = 1;// 紀錄玩家選擇不要牌
					round = (count + 1);// 記錄第幾輪
					/* 建立label顯示第幾回合 */

					if (count == 0) {//若為首回合
						round_show[count].setText("第" + round + "回合");//顯示回合數
					} else if (count > 0) {//若為第2回合or地3回合
						round_show[count].setText("第" + round + "回合");//顯示回合數
						/*將之前的回合數label刪除，讓他不再顯示*/
						for (int i = count; i >= 1; i--) {
							cp.remove(round_show[i - 1]);
						}
						cp.revalidate();
						cp.repaint();
					}
					if (c_add != 1) {// 若電腦還要牌
						c_judge(count);// 換電腦選擇要不要牌
						count++;// 回合數+1
					} else {// 若電腦不要牌拉
						/* 顯示底牌， 比較雙方牌點數 */
						explode = 1;// 使加牌何不加牌按鈕無法作用
						card_show(0, add.p_flowers.getFirst(), add.p_cards.getFirst());
						card_show(500, add.c_flowers.getFirst(), add.c_cards.getFirst());

						JLabel who_win2 = new JLabel();// 建立顯示誰輸誰贏的label
						who_win2.setBounds(0, 200, 800, 300);// 設定位置大小
						who_win2.setFont(new java.awt.Font("Dialog", 1, 25));// 顯示字型大小
						who_win2.setForeground(Color.white);
						cp.add(who_win2);
						if (add.c_count.getLast() > add.p_count.getLast()) {// 若電腦點數大於玩家點數
							who_win2.setText(
									"電腦點數為:" + add.c_count.getLast() + "  玩家點數為:" + add.p_count.getLast() + "\n 電腦獲勝");
							JOptionPane.showMessageDialog(null, "你輸了");// 跳出提示視窗顯示你輸了

						} else if (add.c_count.getLast() < add.p_count.getLast()) {// 若電腦點數小於玩家點數
							who_win2.setText(
									"電腦點數為:" + add.c_count.getLast() + "  玩家點數為:" + add.p_count.getLast() + "\n 玩家獲勝");
							JOptionPane.showMessageDialog(null, "你贏了");// 跳出提示視窗顯示你贏了

						} else if (add.c_count.getLast() == add.p_count.getLast()) {// 若電腦點數等於玩家點數
							who_win2.setText(
									"電腦點數為:" + add.c_count.getLast() + "  玩家點數為:" + add.p_count.getLast() + "\n 平手");
							JOptionPane.showMessageDialog(null, "平手");// 跳出提示視窗顯示平手

						}
					}

				}
			}
			if (btn1 == close_add_btn) {// 若按下重新開始
				/*** 清除所有陣列值 ***/
				add.c_cards.clear();
				add.c_count.clear();
				add.c_flowers.clear();
				add.p_cards.clear();
				add.p_flowers.clear();
				add.p_count.clear();
				explode = 0;
				round = 0;
				c_add = 0;
				p_add = 0;
				no_add = 0;
				dispose();// 關閉原來視窗
				close win_close = new close();// 呼叫class開啟新視窗
			}

			if (add.p_count.getLast() > 21) {// 若超過21點
				/* 建立顯示"爆了"的label */
				JLabel text = new JLabel();// 建立物件
				cp.setLayout(null);// 讓我們可以蕤意更改容器大小
				text.setBounds(150, 120, 300, 300);// 設定位置大小
				cp.add(text);// 加入視窗
				text.setFont(new java.awt.Font("Dialog", 1, 25));// 設定字型大小
				text.setText("玩家爆了!");// 顯示字樣
				text.setForeground(Color.white);
				explode = 1;// 加入陣列表示已經超過21點
				/* 顯示底牌 */
				card_show(0, add.p_flowers.getFirst(), add.p_cards.getFirst());
				card_show(500, add.c_flowers.getFirst(), add.c_cards.getFirst());
				JOptionPane.showMessageDialog(null, "你輸了!");// 提示視窗顯示你輸了
			}

		}
	}

	/** 顯示底牌函式 **/
	public void card_show(int y, String flower, String cards) {
		JLabel dark_show = new JLabel();// 建立物件
		dark_show.setBounds(10, y, 400, 300);// 設定位置
		cp.add(dark_show);// 加入視窗
		ImageIcon imgz = new ImageIcon();
		imgz = new ImageIcon("D:\\桌面\\撲克牌圖檔\\" + flower + cards + ".gif");// 匯入圖
		Image image = imgz.getImage();// 得到其檔案
		Image newing = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 設定大小
		imgz = new ImageIcon(newing);// 將新大小的牌回傳給原本檔案的陣列值
		dark_show.setIcon(imgz);// 將圖加到label
	}

	/* 判斷電腦是先加牌還是先不加牌 */
	int yes_count = 0;
	int no_count = 0;

	public void c_judge(int cardcount) {
		JButton no_add = new JButton();// 設定顯示電腦不加牌
		JButton add = new JButton();// 設定顯示電腦加牌(以按鈕方式)
		int card_count = cardcount;// 傳入count值，也就是加牌和不加牌扭按了幾次
		rand count = new rand();
		Random num = new Random();// 使用亂數的api
		int r_num = 0;
		int odds[] = new int[10];// 建立陣列
		if (count.c_count.getLast() <= 10) {// 當電腦總數小於10時
			int odd[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }; // 電腦選擇抽牌機率為90%
			odds = odd;// 把陣列odd給原來的陣列odds
			r_num = num.nextInt(10);// 隨機0~9中選一個數字，代表在90%機率下會抽還是不抽
		} else if ((count.c_count.getLast() <= 15) && (count.c_count.getLast() > 10)) {// 當電腦總數大於10小於等於15時
			int odd[] = { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 };// 電腦選擇抽牌機率為70%
			odds = odd;// 把陣列odd給原來的陣列odds
			r_num = num.nextInt(10);// 隨機0~9中選一個數字，代表在70%機率下會抽還是不抽
		} else if ((count.c_count.getLast() <= 17) && (count.c_count.getLast() > 15)) {// 當電腦總數大於15小於等於17時
			int odd[] = { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };// 電腦選擇抽牌機率為50%
			odds = odd;// 把陣列odd給原來的陣列odds
			r_num = num.nextInt(10);// 隨機0~9中選一個數字，代表在50%機率下會抽還是不抽
		} else if ((count.c_count.getLast() <= 19) && (count.c_count.getLast() > 17)) {// 當電腦總數大於17小於等於19時
			int odd[] = { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };// 電腦選擇抽牌機率為20%
			odds = odd;// 把陣列odd給原來的陣列odds
			r_num = num.nextInt(10);// 隨機0~9中選一個數字，代表在20%機率下會抽還是不抽
		} else if ((count.c_count.getLast() == 20)) {// 當電腦總數等於20時
			int odd[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };// 電腦選擇抽牌機率為10%
			odds = odd;// 把陣列odd給原來的陣列odds
			r_num = num.nextInt(10);// 隨機0~9中選一個數字，代表在10%機率下會抽還是不抽
		} else if ((count.c_count.getLast() == 21)) {// 當電腦總數等於21時
			int odd[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };// 電腦選擇抽牌機率為0%
			odds = odd;// 把陣列odd給原來的陣列odds
			r_num = 0;
		}
		if (count.p_count.getLast() <= 21) {
			if (odds[r_num] == 0) {// 電腦選擇不加牌

				int x;
				no_count = 1;// 電腦先選擇不加牌
				if (yes_count != 1) {// 電腦先選擇加牌
					cp.add(no_add);// 加入容器
					no_add.setBounds(220, 500, 150, 40);// 設定位置大小
					no_add.setText(no_count + ". 電腦選擇不加牌 ");// 編號順序為1

				}

				else if (yes_count == 1) {// 若電腦先選擇加牌
					x = 2;
					cp.add(no_add);// 加入容器
					no_add.setBounds(220, 500, 150, 40);// 設定位置大小
					no_add.setText(x + ". 電腦選擇不加牌 ");// 編號順序就變成2

				}

				c_add = 1;// 表示電腦不加牌
				if (p_add == 1) {// 如果玩家也不加牌
					explode = 1;// 讓加牌和不加牌按鈕無法作用
					/** 顯示底牌 **/
					card_show(0, count.p_flowers.getFirst(), count.p_cards.getFirst());
					card_show(500, count.c_flowers.getFirst(), count.c_cards.getFirst());

					JLabel who_win1 = new JLabel();// 建立新的label顯示誰輸誰贏
					who_win1.setBounds(0, 200, 800, 300);// 設定大小位置
					who_win1.setFont(new java.awt.Font("Dialog", 1, 25));// 設定字型大小
					who_win1.setForeground(Color.white);
					cp.add(who_win1);// 加入容器

					if (count.c_count.getLast() > count.p_count.getLast()) {// 若電腦總數大於玩家總數
						who_win1.setText("   電腦點數為:" + count.c_count.getLast() + "  玩家點數為:" + count.p_count.getLast()
								+ "\n 電腦獲勝");// 顯示結果
						JOptionPane.showMessageDialog(null, "你輸了");// 提示視窗顯示你輸了

					} else if (count.c_count.getLast() < count.p_count.getLast()) {// 若電腦總數小於玩家總數
						who_win1.setText("   電腦點數為:" + count.c_count.getLast() + "  玩家點數為:" + count.p_count.getLast()
								+ "\n 玩家獲勝");// 顯示結果
						JOptionPane.showMessageDialog(null, "你贏了");// 提示視窗顯示你贏了

					} else if (count.c_count.getLast() == count.p_count.getLast()) {// 若電腦總數等於玩家總數
						who_win1.setText(
								"   電腦點數為:" + count.c_count.getLast() + "  玩家點數為:" + count.p_count.getLast() + "\n 平手");// 顯示結果
						JOptionPane.showMessageDialog(null, "平手");// 提示視窗顯示你贏了

					}
				}
			} else if (odds[r_num] == 1) {// 若電腦選擇加牌
				if (round <= 3) {// 若牌數小於等於五張
					count.computer_rand();// 電腦抽牌

					yes_count = 1;// 電腦先選擇加牌，編號順序為1
					if (no_count == 1) {// 如果電腦先選擇不加牌
						yes_count = 2;// 編號順序為2
					}

					cp.add(add);// 加入容器
					add.setBounds(90, 500, 130, 40);// 設定位置大小
					add.setText(yes_count + ". 電腦選擇加牌 ");// 設定顯示編號

					for (int i = 0; i <= 2; i++) {// 建立牌的物件
						c_add_lab[i] = new JLabel();
					}
					cp.setLayout(null);// 讓我們可以隨意更改容器大小
					c_add_lab[card_count].setBounds(290 + (card_count * 140), 500, 300, 300);// 設定容器位置大小
					cp.add(c_add_lab[card_count]);// 加入容器

					ImageIcon imgz = new ImageIcon();
					imgz = new ImageIcon("D:\\桌面\\撲克牌圖檔\\" + count.c_flowers.get((card_count + 2))
							+ count.c_cards.get((card_count + 2)) + ".gif");// 匯入檔案
					Image image = imgz.getImage();// 得到其檔案
					Image newing = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// 設定牌大小
					imgz = new ImageIcon(newing);// 將新大小的牌回傳給原本檔案的陣列值
					c_add_lab[card_count].setIcon(imgz);// 將圖加到label
				}
				if (round == 3) {// 若牌數等於五張
					if (count.c_count.getLast() <= 21) {// 且總數小於21
						JLabel text = new JLabel();// 設定label顯示過五關
						cp.setLayout(null);
						/* 設定label大小 */
						text.setBounds(30, 200, 800, 300);
						text.setFont(new java.awt.Font("Dialog", 1, 25));// 設定字型大小
						explode = 1;// 讓加牌與布加牌按鈕無法作用
						/* 加入視窗中 */
						cp.add(text);
						/* label要顯示的字 */
						text.setText("電腦過五關!");
						text.setForeground(Color.white);
						/* 顯示底牌 */
						card_show(0, count.p_flowers.getFirst(), count.p_cards.getFirst());
						card_show(500, count.c_flowers.getFirst(), count.c_cards.getFirst());
						JOptionPane.showMessageDialog(null, "電腦過五關，你輸了");// 提示視窗顯示你輸了
					}
				}
			}
			if (count.c_count.getLast() > 21) {// 若總數超過21

				JLabel text = new JLabel();// 建立物件
				cp.setLayout(null);
				text.setBounds(150, 120, 300, 300);// 設定位置大小
				cp.add(text);// 加入視窗
				text.setFont(new java.awt.Font("Dialog", 1, 25));// 設定大小

				text.setText("電腦爆了!");// 顯示字樣
				text.setForeground(Color.white);
				explode = 1;// 讓加牌與布加牌按鈕無法作用
				/* 顯示底牌 */
				card_show(0, count.p_flowers.getFirst(), count.p_cards.getFirst());
				card_show(500, count.c_flowers.getFirst(), count.c_cards.getFirst());

				JOptionPane.showMessageDialog(null, "你贏了");// 提示視窗顯示你贏了
			}
		}
	}

}

class close {// 重新開啟一個新遊戲的class
	public close() {
		/* 輸入玩家底牌和首牌 */
		rand player = new rand();
		player.player_rand();
		player.player_rand();
		/* 輸入電腦底牌和首牌 */
		rand computer = new rand();
		computer.computer_rand();
		computer.computer_rand();
		gui_show dark_show = new gui_show();// 開始判斷
	}
}

public class project_21 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		close NewGame = new close();
	}
}