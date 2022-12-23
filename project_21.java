
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
	public static LinkedList<String> p_cards = new LinkedList<String>();// ���a�P�I�ư}�C
	public static LinkedList<String> p_flowers = new LinkedList<String>();// ���a�P���}�C
	public static LinkedList<String> c_cards = new LinkedList<String>();// �q���P�I�ư}�C
	public static LinkedList<String> c_flowers = new LinkedList<String>();// �q���P���}�C
	public static LinkedList<Integer> p_count = new LinkedList<Integer>();// ���a�P�I���`��
	public static LinkedList<Integer> c_count = new LinkedList<Integer>();// �q���P�I���`��

	/* �Nlist�]��l�� */
	public void p_listset() {
		p_count.add(0, 0);
	}

	public void c_listset() {
		c_count.add(0, 0);
	}

	/*** ���a�P����l�ȳ]�w ***/
	public void player_rand() {
		Random num = new Random();// �ϥζüƪ�api

		int player_poker_num_int = num.nextInt(13) + 1;// �H��1~13����@�ӼƦr
		int count = 0;
		p_listset();// ��l�}�C

		/* �N��X���H������নString�æs�Jlist */
		for (int i = 1; i <= 10; i++) {
			if (player_poker_num_int == i)
				p_cards.add(Integer.toString(i));
		}
		/* �p�G�P�OJ�A��Ȭ�10 */
		if (player_poker_num_int == 11) {
			p_cards.add("J");
			player_poker_num_int = 10;
		}
		/* �p�G�P�OQ�A��Ȭ�10 */
		if (player_poker_num_int == 12) {
			p_cards.add("Q");
			player_poker_num_int = 10;
		}
		/* �p�G�P�OK�A��Ȭ�10 */
		if (player_poker_num_int == 13) {
			p_cards.add("K");
			player_poker_num_int = 10;
		}

		count = p_count.getLast() + player_poker_num_int;// �p��ثe�P���`�I��
		p_count.add(count);// �[�J���a�I�ư}�C
		int flower = num.nextInt(4) + 1;// �H��1~4����@�ӼƦr�N����
		/* �N���[�J���alist��,�A�Y�üƭȬ�1�h���٧ΡA2���R�ߡA3���®�A4������ */
		if (flower == 1)
			p_flowers.add("�٧�");
		else if (flower == 2)
			p_flowers.add("�R��");
		else if (flower == 3)
			p_flowers.add("�³�");
		else if (flower == 4)
			p_flowers.add("����");

	}

	/*** �q���P����l�ȳ]�w ***/
	public void computer_rand() {
		Random num = new Random();// �ϥζüƪ�api
		int computer_poker_num_int = num.nextInt(13) + 1;// �H��1~13����@�ӼƦr
		int count = 0;
		c_listset();// ��l�}�C
		/* �N��X���H������নString�å[�Jlist */
		for (int i = 1; i <= 10; i++) {
			if (computer_poker_num_int == i)
				c_cards.add(Integer.toString(i));
		}
		/* �p�G�P�OJ�A��Ȭ�10 */
		if (computer_poker_num_int == 11) {
			c_cards.add("J");
			computer_poker_num_int = 10;
		}
		/* �p�G�P�OQ�A��Ȭ�10 */
		if (computer_poker_num_int == 12) {
			c_cards.add("Q");
			computer_poker_num_int = 10;
		}
		/* �p�G�P�OK�A��Ȭ�10 */
		if (computer_poker_num_int == 13) {
			c_cards.add("K");
			computer_poker_num_int = 10;
		}
		count = c_count.getLast() + computer_poker_num_int;// �p��ثe�P���`�I��
		c_count.add(count);// �[�J�q���I�ư}�C
		int flower = num.nextInt(4) + 1;// �H��1~4����@�ӼƦr�N����
		/* �N���[�J���alist��,�A�Y�üƭȬ�1�h���٧ΡA2���R�ߡA3���®�A4������ */
		if (flower == 1)
			c_flowers.add("�٧�");
		else if (flower == 2)
			c_flowers.add("�R��");
		else if (flower == 3)
			c_flowers.add("�³�");
		else if (flower == 4)
			c_flowers.add("����");
	}
}

class gui_show extends JFrame {

	int count = 0;
	/* ���󪺫ŧi */
	static int explode = 0;
	static int c_add = 0;
	static int p_add = 0;// �إ߬����O�_�W�L21�I
	static int no_add = 0;// �إ߬����O�_�W�L21�I
	static int round = 0;// �إ߬����^�X�ܼ�
	JLabel intial_lab[] = new JLabel[5];// �إߪ�l�q���M���a����i�P
	JLabel p_add_lab[] = new JLabel[3];// �إ߫�3�i���a���P
	JLabel c_add_lab[] = new JLabel[3];// �إ߫�3�i�q�����P
	JLabel round_show[] = new JLabel[3];// �إ���ܦ^�X��LABEL
	
	rand rand_card = new rand();// �ϥζüƵP������(�W��CLASS)
	Container cp = this.getContentPane();// �إ߮e��
	

	public gui_show(JLabel a) { // �غc��1

	}

	/* ������� */
	public gui_show() { // �غc��
		super("21�I");// �����W
		JLabel dark1_show = new JLabel();// �إ�LABEL��ܪ��a���P
		dark1_show.setBounds(80, -125, 300, 300);// �վ�j�p��m
		cp.add(dark1_show);// ��J�e��
		dark1_show.setText("(���P:" + rand.p_cards.getFirst() + ")");// �]�wLABEL���r
		dark1_show.setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�r���j�p
		for (int i = 0; i <= 4; i++) {
			intial_lab[i] = new JLabel();// NEW�ڭ̪�4�i��l�P
		}
		for (int i = 0; i <= 2; i++) {
			round_show[i] = new JLabel();// NEW�ڭ̪��^�X���label
		}
		cp.setLayout(null);// ���ڭ̥i�H�վ�P���j�p
		JLabel player = new JLabel();// ��ܪ��a
		JLabel computer = new JLabel();// ��ܹq��
		JButton btn = new JButton("�[�P");// �إߥ[�P���s
		JButton no_btn = new JButton("���[�P");// �إߤ��[�P���s
		JButton close_btn = new JButton("���s�}�l");// �إ��������s
		player.setText("���a:");// �]�wLABEL���r
		computer.setText("�q��:");// �]�wLABEL���r
		player.setBounds(15, -125, 300, 300);// �]�w�j�p��m
		computer.setBounds(15, 380, 300, 300);// �]�w�j�p��m
		btn.setBounds(800, 350, 100, 40);// �վ�[�P���s��m�j�p
		no_btn.setBounds(700, 350, 100, 40);// �վ㤣�[�P���s��m�j�p
		close_btn.setBounds(600, 350, 100, 40);// �վ��������s��m�j�p
		/** �r���C�� **/
		player.setForeground(Color.white);
		computer.setForeground(Color.white);
		dark1_show.setForeground(Color.white);
		/*** �]�w�r���j�p ***/
		player.setFont(new java.awt.Font("Dialog", 1, 25));
		computer.setFont(new java.awt.Font("Dialog", 1, 25));
		btn.setFont(new java.awt.Font("Dialog", 1, 15));
		no_btn.setFont(new java.awt.Font("Dialog", 1, 15));
		close_btn.setFont(new java.awt.Font("Dialog", 1, 15));
		intial_lab[0].setBounds(10, 0, 300, 300);// �վ㪱�a���P��m
		intial_lab[1].setBounds(150, 0, 300, 300);// �վ㪱�a���P��m
		intial_lab[2].setBounds(10, 500, 300, 300);// �վ�q�����P��m
		intial_lab[3].setBounds(150, 500, 300, 300);// �վ�q�����P��m
		/* �N��l�P�P���s�[�J���� */
		cp.add(player);
		cp.add(computer);
		cp.add(btn);
		cp.add(no_btn);
		cp.add(close_btn);
		cp.add(intial_lab[0]);
		cp.add(intial_lab[1]);
		cp.add(intial_lab[2]);
		cp.add(intial_lab[3]);
		/* �]�w�����j�p�M���H�ݨ� */
		this.setBounds(450, 100, 1000, 850);// �]�w�����j�p��m
		this.setVisible(true);// ��������ܥX��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����

		/* �����s�Q��ť�A�]�N�O���U���s��N�|�o�ͫ��򪺰ʧ@ */
		btn.addActionListener(new actLis1(btn));
		no_btn.addActionListener(new actLis1(no_btn, btn));
		close_btn.addActionListener(new actLis1(close_btn, no_btn, btn));
		/* �פJ���a���P�Ϥ� */
		ImageIcon p_imgz[] = new ImageIcon[2];
		p_imgz[0] = new ImageIcon("D:\\�ୱ\\���J�P����\\���P.jpg");// �פJ�ɮ�
		Image p_image = p_imgz[0].getImage();// �o����ɮ�
		Image p_newing = p_image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// ���ܵP���j�p
		p_imgz[0] = new ImageIcon(p_newing);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
		intial_lab[0].setIcon(p_imgz[0]);// �bLABEL�W�]�w�Ϥ�

		/* �N���P�ܦ��I���A����L�P�i�H�л\�b�W�� */
		this.getLayeredPane().add(intial_lab[0], new Integer(Integer.MIN_VALUE));
		intial_lab[0].setBounds(10, 50, p_imgz[0].getIconWidth(), p_imgz[0].getIconHeight());
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);

		/* �פJ���a���P�Ϥ� */
		p_imgz[1] = new ImageIcon("D:\\�ୱ\\���J�P����\\" + rand.p_flowers.get(1) + rand.p_cards.get(1) + ".gif");// �פJ�ɮ�
		Image p_image2 = p_imgz[1].getImage();// �o����ɮ�
		Image p_newing2 = p_image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// ���ܵP���j�p
		p_imgz[1] = new ImageIcon(p_newing2);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
		intial_lab[1].setIcon(p_imgz[1]);// �bLABEL�W�]�w�Ϥ�
		/* �פJ�q�����P�Ϥ� */
		ImageIcon c_imgz[] = new ImageIcon[2];
		c_imgz[0] = new ImageIcon("D:\\�ୱ\\���J�P����\\���P.jpg");// �פJ�ɮ�
		Image c_image = c_imgz[0].getImage();// �o����ɮ�
		Image c_newing = c_image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// ���ܵP���j�p
		c_imgz[0] = new ImageIcon(c_newing);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
		intial_lab[2].setIcon(c_imgz[0]);// �bLABEL�W�]�w�Ϥ�

		/* �N���P�ܦ��I���A����L�P�i�H�л\�b�W�� */
		this.getLayeredPane().add(intial_lab[2], new Integer(Integer.MIN_VALUE));
		intial_lab[2].setBounds(10, 550, c_imgz[0].getIconWidth(), c_imgz[0].getIconHeight());
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);

		/* �פJ���a���P�Ϥ� */
		c_imgz[1] = new ImageIcon("D:\\�ୱ\\���J�P����\\" + rand.c_flowers.get(1) + rand.c_cards.get(1) + ".gif");// �פJ�ɮ�
		Image c_image2 = c_imgz[1].getImage();// �o����ɮ�
		Image c_newing2 = c_image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// ���ܵP���j�p
		c_imgz[1] = new ImageIcon(c_newing2);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
		intial_lab[3].setIcon(c_imgz[1]);// �bLABEL�W�]�w�Ϥ�

		/** �]�m�I���Ϥ� **/
		ImageIcon background = new ImageIcon("D:\\�ୱ\\���J�P����\\�I��.jpg");// �פJ�ɮ�
		JLabel Jbackground = new JLabel(background);// �N�ɮװ���label
		Image back = background.getImage();// �o����ɮ�
		Image back_new = back.getScaledInstance(1000, 850, java.awt.Image.SCALE_SMOOTH);// ���ܵP���j�p
		background = new ImageIcon(back_new);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
		Jbackground.setIcon(background);// �bLABEL�W�]�w�Ϥ�
		this.getLayeredPane().add(Jbackground, new Integer(Integer.MIN_VALUE));
		Jbackground.setBounds(0, 0, 1000, 850);// ��m
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);
	}

	/* ��ܵP�� */
	class actLis1 implements ActionListener {
		JButton add_btn;// �[�P���s
		JButton no_add_btn;// ���n�P���s
		JButton close_add_btn;// ���s�}�l���s
		rand add = new rand();// �P class rand�إ߳s��

		public actLis1(JButton btn) {// �غc��
			add_btn = btn;
		}

		public actLis1(JButton btn1, JButton btn2) {// �غc��
			no_add_btn = btn1;
		}

		public actLis1(JButton btn1, JButton btn2, JButton btn3) {// �غc��
			close_add_btn = btn1;
		}

		/* ���U���s����ܪ��a���P�A�����q���ۤv��P */
		public void actionPerformed(ActionEvent e) {// ��ť���禡
			JButton btn1 = (JButton) e.getSource();// �T�{�O���ӫ��s�Q��
            /*��ܦ^�X��label�]�w*/
			for (int i = 0; i <= 2; i++) {
				round_show[i].setBounds(50, 120, 300, 300);// �]�w�j�p
				cp.add(round_show[i]);// �[�J�e��
				round_show[i].setForeground(Color.white);//�r���C��
				round_show[i].setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�r���j�p
			}

			if (explode != 1) {// �p�G�S���H�z���N����,���H�z���N���|����

				if (btn1 == add_btn) {// �p�G���U�[�P���s
					if (no_add != 1) { // �p�G�S�����U���[�P���s
						round = (count + 1);// �O���ĴX��
						/* �إ�label��ܲĴX�^�X */
						if (count == 0) {//�Y�����^�X
							round_show[count].setText("��" + round + "�^�X");//��ܦ^�X��
						} else if (count > 0) {//�Y����2�^�Xor��3�^�X
							round_show[count].setText("��" + round + "�^�X");//��ܦ^�X��
							/*�N���e���^�X��label�R���A���L���A���*/
							for (int i = count; i >= 1; i--) {
								cp.remove(round_show[i - 1]);
							}
							cp.revalidate();
							cp.repaint();
						}

						if (round <= 3) {// �P�_���L�W�L5�i
							add.player_rand();// ���a��P
							for (int i = 0; i <= 2; i++) {// �إߵP������
								p_add_lab[i] = new JLabel();

							}
							cp.setLayout(null);// ���ڭ̥i�H�H�N�վ�e���j�p

							/* �]�w�P�j�p��m�å[�J���� */
							p_add_lab[count].setBounds(290 + (count * 140), 0, 300, 300);
							cp.add(p_add_lab[count]);

							/* �P�W��{���X */
							ImageIcon imgz = new ImageIcon();
							imgz = new ImageIcon("D:\\�ୱ\\���J�P����\\" + add.p_flowers.get((count + 2))
									+ add.p_cards.get((count + 2)) + ".gif");// �פJ�ɮ�
							Image image = imgz.getImage();// �o����ɮ�
							Image newing = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// �]�w�Ϥ��j�p
							imgz = new ImageIcon(newing);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
							p_add_lab[count].setIcon(imgz);// �bLABEL�W�]�w�Ϥ�

							if (c_add != 1) {// �Y�q���٭n�P
								c_judge(count);// ���q����ܭn���n�P
							}

							count++;// �����ĴX���I�Ϋ��s�A�I�@���N�O�@�^�X

						}
						if (round == 3) {// �Y�W�L5�i�٨S��=>�L����
							/* �إ�label���"�L����"�M��ܩ��P */
							if (add.p_count.getLast() <= 21) {// �Y�S�W�L21�I
								explode = 1;// ���[�P�M���[�P���s�L�k�@��
								JLabel text = new JLabel();// �إ߷slabel��ܹL����
								cp.setLayout(null);// ���ڭ̥i�H�H�N���e���j�p
								/* �]�wlabel�j�p */
								text.setBounds(30, 200, 800, 300);// �]�w��m
								text.setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�r���j�p

								/* �[�J������ */
								cp.add(text);
								/* label�n��ܪ��r */
								text.setText("�L����!");
								text.setForeground(Color.white);
								/* ��ܩ��P */
								card_show(0, add.p_flowers.getFirst(), add.p_cards.getFirst());
								card_show(500, add.c_flowers.getFirst(), add.c_cards.getFirst());
								JOptionPane.showMessageDialog(null, "�AĹ�F");// ���ܵ�����ܧAĹ�F
							}
						}
					}
				} else if (btn1 == no_add_btn) {// ���a��ܤ��n�P
					no_add = 1;// ���[�P���s�L�k�@��
					p_add = 1;// �������a��ܤ��n�P
					round = (count + 1);// �O���ĴX��
					/* �إ�label��ܲĴX�^�X */

					if (count == 0) {//�Y�����^�X
						round_show[count].setText("��" + round + "�^�X");//��ܦ^�X��
					} else if (count > 0) {//�Y����2�^�Xor�a3�^�X
						round_show[count].setText("��" + round + "�^�X");//��ܦ^�X��
						/*�N���e���^�X��label�R���A���L���A���*/
						for (int i = count; i >= 1; i--) {
							cp.remove(round_show[i - 1]);
						}
						cp.revalidate();
						cp.repaint();
					}
					if (c_add != 1) {// �Y�q���٭n�P
						c_judge(count);// ���q����ܭn���n�P
						count++;// �^�X��+1
					} else {// �Y�q�����n�P��
						/* ��ܩ��P�A �������P�I�� */
						explode = 1;// �ϥ[�P�󤣥[�P���s�L�k�@��
						card_show(0, add.p_flowers.getFirst(), add.p_cards.getFirst());
						card_show(500, add.c_flowers.getFirst(), add.c_cards.getFirst());

						JLabel who_win2 = new JLabel();// �إ���ֿܽ��Ĺ��label
						who_win2.setBounds(0, 200, 800, 300);// �]�w��m�j�p
						who_win2.setFont(new java.awt.Font("Dialog", 1, 25));// ��ܦr���j�p
						who_win2.setForeground(Color.white);
						cp.add(who_win2);
						if (add.c_count.getLast() > add.p_count.getLast()) {// �Y�q���I�Ƥj�󪱮a�I��
							who_win2.setText(
									"�q���I�Ƭ�:" + add.c_count.getLast() + "  ���a�I�Ƭ�:" + add.p_count.getLast() + "\n �q�����");
							JOptionPane.showMessageDialog(null, "�A��F");// ���X���ܵ�����ܧA��F

						} else if (add.c_count.getLast() < add.p_count.getLast()) {// �Y�q���I�Ƥp�󪱮a�I��
							who_win2.setText(
									"�q���I�Ƭ�:" + add.c_count.getLast() + "  ���a�I�Ƭ�:" + add.p_count.getLast() + "\n ���a���");
							JOptionPane.showMessageDialog(null, "�AĹ�F");// ���X���ܵ�����ܧAĹ�F

						} else if (add.c_count.getLast() == add.p_count.getLast()) {// �Y�q���I�Ƶ��󪱮a�I��
							who_win2.setText(
									"�q���I�Ƭ�:" + add.c_count.getLast() + "  ���a�I�Ƭ�:" + add.p_count.getLast() + "\n ����");
							JOptionPane.showMessageDialog(null, "����");// ���X���ܵ�����ܥ���

						}
					}

				}
			}
			if (btn1 == close_add_btn) {// �Y���U���s�}�l
				/*** �M���Ҧ��}�C�� ***/
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
				dispose();// ������ӵ���
				close win_close = new close();// �I�sclass�}�ҷs����
			}

			if (add.p_count.getLast() > 21) {// �Y�W�L21�I
				/* �إ����"�z�F"��label */
				JLabel text = new JLabel();// �إߪ���
				cp.setLayout(null);// ���ڭ̥i�H�B�N���e���j�p
				text.setBounds(150, 120, 300, 300);// �]�w��m�j�p
				cp.add(text);// �[�J����
				text.setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�r���j�p
				text.setText("���a�z�F!");// ��ܦr��
				text.setForeground(Color.white);
				explode = 1;// �[�J�}�C��ܤw�g�W�L21�I
				/* ��ܩ��P */
				card_show(0, add.p_flowers.getFirst(), add.p_cards.getFirst());
				card_show(500, add.c_flowers.getFirst(), add.c_cards.getFirst());
				JOptionPane.showMessageDialog(null, "�A��F!");// ���ܵ�����ܧA��F
			}

		}
	}

	/** ��ܩ��P�禡 **/
	public void card_show(int y, String flower, String cards) {
		JLabel dark_show = new JLabel();// �إߪ���
		dark_show.setBounds(10, y, 400, 300);// �]�w��m
		cp.add(dark_show);// �[�J����
		ImageIcon imgz = new ImageIcon();
		imgz = new ImageIcon("D:\\�ୱ\\���J�P����\\" + flower + cards + ".gif");// �פJ��
		Image image = imgz.getImage();// �o����ɮ�
		Image newing = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// �]�w�j�p
		imgz = new ImageIcon(newing);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
		dark_show.setIcon(imgz);// �N�ϥ[��label
	}

	/* �P�_�q���O���[�P�٬O�����[�P */
	int yes_count = 0;
	int no_count = 0;

	public void c_judge(int cardcount) {
		JButton no_add = new JButton();// �]�w��ܹq�����[�P
		JButton add = new JButton();// �]�w��ܹq���[�P(�H���s�覡)
		int card_count = cardcount;// �ǤJcount�ȡA�]�N�O�[�P�M���[�P����F�X��
		rand count = new rand();
		Random num = new Random();// �ϥζüƪ�api
		int r_num = 0;
		int odds[] = new int[10];// �إ߰}�C
		if (count.c_count.getLast() <= 10) {// ��q���`�Ƥp��10��
			int odd[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }; // �q����ܩ�P���v��90%
			odds = odd;// ��}�Codd����Ӫ��}�Codds
			r_num = num.nextInt(10);// �H��0~9����@�ӼƦr�A�N��b90%���v�U�|���٬O����
		} else if ((count.c_count.getLast() <= 15) && (count.c_count.getLast() > 10)) {// ��q���`�Ƥj��10�p�󵥩�15��
			int odd[] = { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 };// �q����ܩ�P���v��70%
			odds = odd;// ��}�Codd����Ӫ��}�Codds
			r_num = num.nextInt(10);// �H��0~9����@�ӼƦr�A�N��b70%���v�U�|���٬O����
		} else if ((count.c_count.getLast() <= 17) && (count.c_count.getLast() > 15)) {// ��q���`�Ƥj��15�p�󵥩�17��
			int odd[] = { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };// �q����ܩ�P���v��50%
			odds = odd;// ��}�Codd����Ӫ��}�Codds
			r_num = num.nextInt(10);// �H��0~9����@�ӼƦr�A�N��b50%���v�U�|���٬O����
		} else if ((count.c_count.getLast() <= 19) && (count.c_count.getLast() > 17)) {// ��q���`�Ƥj��17�p�󵥩�19��
			int odd[] = { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };// �q����ܩ�P���v��20%
			odds = odd;// ��}�Codd����Ӫ��}�Codds
			r_num = num.nextInt(10);// �H��0~9����@�ӼƦr�A�N��b20%���v�U�|���٬O����
		} else if ((count.c_count.getLast() == 20)) {// ��q���`�Ƶ���20��
			int odd[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };// �q����ܩ�P���v��10%
			odds = odd;// ��}�Codd����Ӫ��}�Codds
			r_num = num.nextInt(10);// �H��0~9����@�ӼƦr�A�N��b10%���v�U�|���٬O����
		} else if ((count.c_count.getLast() == 21)) {// ��q���`�Ƶ���21��
			int odd[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };// �q����ܩ�P���v��0%
			odds = odd;// ��}�Codd����Ӫ��}�Codds
			r_num = 0;
		}
		if (count.p_count.getLast() <= 21) {
			if (odds[r_num] == 0) {// �q����ܤ��[�P

				int x;
				no_count = 1;// �q������ܤ��[�P
				if (yes_count != 1) {// �q������ܥ[�P
					cp.add(no_add);// �[�J�e��
					no_add.setBounds(220, 500, 150, 40);// �]�w��m�j�p
					no_add.setText(no_count + ". �q����ܤ��[�P ");// �s�����Ǭ�1

				}

				else if (yes_count == 1) {// �Y�q������ܥ[�P
					x = 2;
					cp.add(no_add);// �[�J�e��
					no_add.setBounds(220, 500, 150, 40);// �]�w��m�j�p
					no_add.setText(x + ". �q����ܤ��[�P ");// �s�����ǴN�ܦ�2

				}

				c_add = 1;// ��ܹq�����[�P
				if (p_add == 1) {// �p�G���a�]���[�P
					explode = 1;// ���[�P�M���[�P���s�L�k�@��
					/** ��ܩ��P **/
					card_show(0, count.p_flowers.getFirst(), count.p_cards.getFirst());
					card_show(500, count.c_flowers.getFirst(), count.c_cards.getFirst());

					JLabel who_win1 = new JLabel();// �إ߷s��label��ֿܽ��Ĺ
					who_win1.setBounds(0, 200, 800, 300);// �]�w�j�p��m
					who_win1.setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�r���j�p
					who_win1.setForeground(Color.white);
					cp.add(who_win1);// �[�J�e��

					if (count.c_count.getLast() > count.p_count.getLast()) {// �Y�q���`�Ƥj�󪱮a�`��
						who_win1.setText("   �q���I�Ƭ�:" + count.c_count.getLast() + "  ���a�I�Ƭ�:" + count.p_count.getLast()
								+ "\n �q�����");// ��ܵ��G
						JOptionPane.showMessageDialog(null, "�A��F");// ���ܵ�����ܧA��F

					} else if (count.c_count.getLast() < count.p_count.getLast()) {// �Y�q���`�Ƥp�󪱮a�`��
						who_win1.setText("   �q���I�Ƭ�:" + count.c_count.getLast() + "  ���a�I�Ƭ�:" + count.p_count.getLast()
								+ "\n ���a���");// ��ܵ��G
						JOptionPane.showMessageDialog(null, "�AĹ�F");// ���ܵ�����ܧAĹ�F

					} else if (count.c_count.getLast() == count.p_count.getLast()) {// �Y�q���`�Ƶ��󪱮a�`��
						who_win1.setText(
								"   �q���I�Ƭ�:" + count.c_count.getLast() + "  ���a�I�Ƭ�:" + count.p_count.getLast() + "\n ����");// ��ܵ��G
						JOptionPane.showMessageDialog(null, "����");// ���ܵ�����ܧAĹ�F

					}
				}
			} else if (odds[r_num] == 1) {// �Y�q����ܥ[�P
				if (round <= 3) {// �Y�P�Ƥp�󵥩󤭱i
					count.computer_rand();// �q����P

					yes_count = 1;// �q������ܥ[�P�A�s�����Ǭ�1
					if (no_count == 1) {// �p�G�q������ܤ��[�P
						yes_count = 2;// �s�����Ǭ�2
					}

					cp.add(add);// �[�J�e��
					add.setBounds(90, 500, 130, 40);// �]�w��m�j�p
					add.setText(yes_count + ". �q����ܥ[�P ");// �]�w��ܽs��

					for (int i = 0; i <= 2; i++) {// �إߵP������
						c_add_lab[i] = new JLabel();
					}
					cp.setLayout(null);// ���ڭ̥i�H�H�N���e���j�p
					c_add_lab[card_count].setBounds(290 + (card_count * 140), 500, 300, 300);// �]�w�e����m�j�p
					cp.add(c_add_lab[card_count]);// �[�J�e��

					ImageIcon imgz = new ImageIcon();
					imgz = new ImageIcon("D:\\�ୱ\\���J�P����\\" + count.c_flowers.get((card_count + 2))
							+ count.c_cards.get((card_count + 2)) + ".gif");// �פJ�ɮ�
					Image image = imgz.getImage();// �o����ɮ�
					Image newing = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);// �]�w�P�j�p
					imgz = new ImageIcon(newing);// �N�s�j�p���P�^�ǵ��쥻�ɮת��}�C��
					c_add_lab[card_count].setIcon(imgz);// �N�ϥ[��label
				}
				if (round == 3) {// �Y�P�Ƶ��󤭱i
					if (count.c_count.getLast() <= 21) {// �B�`�Ƥp��21
						JLabel text = new JLabel();// �]�wlabel��ܹL����
						cp.setLayout(null);
						/* �]�wlabel�j�p */
						text.setBounds(30, 200, 800, 300);
						text.setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�r���j�p
						explode = 1;// ���[�P�P���[�P���s�L�k�@��
						/* �[�J������ */
						cp.add(text);
						/* label�n��ܪ��r */
						text.setText("�q���L����!");
						text.setForeground(Color.white);
						/* ��ܩ��P */
						card_show(0, count.p_flowers.getFirst(), count.p_cards.getFirst());
						card_show(500, count.c_flowers.getFirst(), count.c_cards.getFirst());
						JOptionPane.showMessageDialog(null, "�q���L�����A�A��F");// ���ܵ�����ܧA��F
					}
				}
			}
			if (count.c_count.getLast() > 21) {// �Y�`�ƶW�L21

				JLabel text = new JLabel();// �إߪ���
				cp.setLayout(null);
				text.setBounds(150, 120, 300, 300);// �]�w��m�j�p
				cp.add(text);// �[�J����
				text.setFont(new java.awt.Font("Dialog", 1, 25));// �]�w�j�p

				text.setText("�q���z�F!");// ��ܦr��
				text.setForeground(Color.white);
				explode = 1;// ���[�P�P���[�P���s�L�k�@��
				/* ��ܩ��P */
				card_show(0, count.p_flowers.getFirst(), count.p_cards.getFirst());
				card_show(500, count.c_flowers.getFirst(), count.c_cards.getFirst());

				JOptionPane.showMessageDialog(null, "�AĹ�F");// ���ܵ�����ܧAĹ�F
			}
		}
	}

}

class close {// ���s�}�Ҥ@�ӷs�C����class
	public close() {
		/* ��J���a���P�M���P */
		rand player = new rand();
		player.player_rand();
		player.player_rand();
		/* ��J�q�����P�M���P */
		rand computer = new rand();
		computer.computer_rand();
		computer.computer_rand();
		gui_show dark_show = new gui_show();// �}�l�P�_
	}
}

public class project_21 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		close NewGame = new close();
	}
}