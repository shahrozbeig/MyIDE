package myEditor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes", "unused" })
class Editor implements ActionListener {
	String								fname				= "";
	String								cname				= "";
	String								result			= "";
	String								str				= "";
	String								hname				= "";
	boolean								flg				= false;
	// JFileChooser jfc;
	FileDialog							fd;
	JFrame								jf;
	JLabel								jl, jl1;
	JTextField							jtf;
	JTextArea							jta, jta1, jtin, jtdec;
	JButton								jcompile, jrun, jclear, jbdec;
	JScrollPane							jp, jp1, jp2, jsp, jsp1;
	JMenu									file, look, skin, about, font, fnt, fntsize,
			edit, execute, color, program;
	JMenuItem							compile, run, exit, abt, jnw, save, cut, cut1,
			copy, copy1, paste, paste1, del, del1, undo, open, saveas, selectall,
			selectall1, clear, dec;
	JMenuBar								jmb;
	JRadioButtonMenuItem				sys, metl, nimb, motif, c1, c2, c3, c4, c5,
			c6, c7, c8, c9, java, html, cp, cpp, py;
	JPopupMenu							jpm;
	JTabbedPane							jtab;
	JTextField							jff;
	// UndoManager und;
	JList									jlist;

	JPanel								jpdec, jpin, jpout;
	String								path				= System
																		.getProperty("user.dir");

	Color									colr;
	ButtonGroup							bg;
	// Runtime r;
	int									sflg				= 0, cflg = 0, oflg = 0,
			htmlflag = 0, javaflag = 1, cpflag = 0, cppflag = 0, pyflag = 0;
	int									htmlno			= 0;

	private static final String	COMMIT_ACTION	= "commit";

	@SuppressWarnings("resource")
	Editor() {
		// .......................................................Directories............................................
		javaflag = 1;
		// String path=System.getProperty("user.home");
		File j1 = new File(path + "/JAVA");
		File c11 = new File(path + "/C");
		File cp1 = new File(path + "/CPP");
		File h1 = new File(path + "/HTML");
		File p1 = new File(path + "/PYTHON");

		if (!j1.exists())
			j1.mkdir();
		if (!c11.exists())
			c11.mkdir();
		if (!cp1.exists())
			cp1.mkdir();
		if (!h1.exists())
			h1.mkdir();
		if (!p1.exists())
			p1.mkdir();

		// .................................................................................................................

		jf = new JFrame("Shahroz's Editor -- Compile C/C++/HTML/JAVA/Python");// frame
		jf.setSize(730, 650);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);

		// ...........................................................MENU
		// BAR............................................//
		JMenuBar jmb = new JMenuBar();// menubar

		file = new JMenu("File ");// menu
		edit = new JMenu("Edit");
		execute = new JMenu("Execute");
		program = new JMenu("Program");
		look = new JMenu("Look ");
		skin = new JMenu("Skin");
		color = new JMenu("Color");
		font = new JMenu("Font ");
		about = new JMenu("About ");

		file.setMnemonic('f');

		// ................................................................................................................//

		// ...........................................................FILE
		// MENU............................................//
		jnw = new JMenuItem("New");// File Menu
		jnw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		jnw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent fe) {
				if (javaflag == 1) {
					cflg = 0;
					sflg = 0;
					jta.setText("");
					jta.setBackground(Color.white);
					jta.setForeground(Color.BLACK);
					jta.setText("public class " + "\n" + "{" + "\n" + "            "
							+ "public static void main(String arg[])" + "\n"
							+ "            " + "{" + "\n" + "\n" + "            "
							+ "}" + "\n" + "}");
				} else if (htmlflag == 1) {
					jta.setText("");
					jta.setText("<html>\n<title></title>\n<head>\n<body>\n\n</body>\n</head>\n</html>");
					Color coloring = new Color(20, 25, 125);
					jta.setBackground(coloring);
					jta.setForeground(Color.white);
				} else if (cpflag == 1) {
					jta.setText("");
					jta.setText("#include<stdio.h> \n#include<conio.h> \nint main(int argc,char *argv[])\n{ \n \n\n\n\n\nreturn 0; \n}");
					jta.setBackground(Color.white);
					jta.setForeground(Color.BLACK);
				} else if (cppflag == 1) {
					jta.setText("");
					jta.setText("#include<iostream.h> \n#include<conio.h> \nint main(int argc,char *argv[])\n{ \n\n\n\n\n\nreturn 0; \n}");
					jta.setBackground(Color.white);
					jta.setForeground(Color.BLACK);
				} else if (pyflag == 1) {
					jta.setText("");
					jta.setText("#write yout program below\nimport sys");
					jta.setBackground(Color.white);
					jta.setForeground(Color.BLACK);
				}

			}
		});

		open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jta.setText("// Either Open file to execute,\nor write your code ");
					oflg = 1;
					javaflag = 0;

					fd = new FileDialog(jf, "File Chooser");
					fd.setVisible(true);
					FileInputStream fin = new FileInputStream(new File(fd
							.getDirectory() + fd.getFile()));
					DataInputStream din = new DataInputStream(
							new BufferedInputStream(fin));
					String s = "";

					fname = "";
					cname = "";
					fname = fd.getFile();

					cname = fname.substring(0, fname.indexOf('.'));
					String ext = fname.substring(fname.indexOf('.') + 1,
							fname.length());
					if (ext.equals("java")) {
						javaflag = 1;
						cpflag = 0;
						htmlflag = 0;
						cppflag = 0;
						pyflag = 0;
						jta.setText("");
						jta.setCaretColor(Color.BLACK);
						jff.setText("\t         JAVA");
						jta.setBackground(Color.white);
						jta.setForeground(Color.BLACK);
						jcompile.setVisible(true);
						jbdec.setVisible(true);
					} else if (ext.equals("c")) {
						javaflag = 0;
						cpflag = 1;
						htmlflag = 0;
						cppflag = 0;
						pyflag = 0;
						jta.setText("");
						jta.setCaretColor(Color.BLACK);
						jff.setText("\t         C");
						jta.setBackground(Color.white);
						jta.setForeground(Color.BLACK);
						jbdec.setVisible(false);
					} else if (ext.equals("cpp")) {
						javaflag = 0;
						cpflag = 0;
						htmlflag = 0;
						cppflag = 1;
						pyflag = 0;
						jta.setText("");
						jta.setCaretColor(Color.BLACK);
						jff.setText("\t         CPP");
						jta.setBackground(Color.white);
						jta.setForeground(Color.BLACK);
						jbdec.setVisible(false);
					} else if (ext.equals("py")) {
						javaflag = 0;
						cpflag = 0;
						htmlflag = 0;
						cppflag = 0;
						pyflag = 1;
						jta.setText("");
						jta.setCaretColor(Color.BLACK);
						jff.setText("\t         PYTHON");
						jta.setBackground(Color.white);
						jta.setForeground(Color.BLACK);
						jcompile.setVisible(false);
						jbdec.setVisible(false);
					} else if (ext.equals("html")) {
						javaflag = 0;
						cpflag = 0;
						htmlflag = 1;
						cppflag = 0;
						pyflag = 0;
						jta.setText("");
						jta.setCaretColor(Color.WHITE);
						jff.setText("\t         HTML");
						jta.setBackground(Color.white);
						jta.setForeground(Color.BLACK);
						Color coloring = new Color(20, 25, 125);
						jta.setBackground(coloring);
						jta.setForeground(Color.white);
						jcompile.setVisible(false);
						jbdec.setVisible(false);
					}

					if (s == "") {
						while (true) {
							String s1 = din.readLine();
							if (s1 != null) {
								s = s + s1;
								s = s + "\n";
							} else
								break;
						}
					}

					jta.setText(s);
					jta1.setText("Class name:" + cname + "\nFile name:" + fname
							+ "\nFile Extension:." + ext);
					fin.close();
					din.close();
				} catch (Exception e1) {
					jta1.setText(e1.getMessage());
				}
			}

		});
		saveas = new JMenuItem("Save As");
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					fname = "";
					String str = "";
					cname = "";
					String demo = null;
					FileOutputStream fw = null;
					while (true) {
						demo = JOptionPane
								.showInputDialog("Please Enter your program Name without extension");
						if (demo == null)// || demo.length()>0)
							jta1.setText("Please Enter the file name without extension");
						else {
							cname = demo;
							break;
						}
					}

					sflg = 1;
					str = jta.getText();
					if (javaflag == 1) {
						fname = cname + ".java";
						fw = new FileOutputStream(path + "/Java/" + fname);
					} else if (cpflag == 1) {
						fname = cname + ".c";
						fw = new FileOutputStream(path + "/C/" + fname);
					} else if (cppflag == 1) {
						fname = cname + ".cpp";
						fw = new FileOutputStream(path + "/CPP/" + fname);
					} else if (htmlflag == 1) {
						fname = cname + ".html";
						fw = new FileOutputStream(path + "/HTML/" + fname);
					} else if (pyflag == 1) {
						fname = cname + ".py";
						fw = new FileOutputStream(path + "/PYTHON/" + fname);
					}

					PrintStream pw = new PrintStream(new BufferedOutputStream(fw));
					StringTokenizer st = new StringTokenizer(str, "\n");
					while (st.hasMoreTokens()) {
						pw.println(st.nextToken());
					}

					pw.flush();
					pw.close();

					jta1.setText("File is Successfully Saved");
				} catch (Exception t) {
					jta1.setText(t.getMessage());
				}
			}

		});

		save = new JMenuItem("Save");// Save menu
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (javaflag == 1 || cpflag == 1 || cppflag == 1 || htmlflag == 1
						|| pyflag == 1) {
					if (sflg == 1 || oflg == 1) {
						try {

							String str = jta.getText();
							// fname=jtf.getText().trim()+".java";
							sflg = 1;
							FileOutputStream fw = null;
							/*
							 * /if(cpflag==1 || cppflag==1) { File f=new
							 * File(path+"/C/"+cname+".exe"); if(f.exists()) {
							 * f.delete(); }/
							 */
							if (javaflag == 1) {
								fname = cname + ".java";
								fw = new FileOutputStream(path + "/Java/" + fname);
							} else if (cpflag == 1) {
								fname = cname + ".c";
								fw = new FileOutputStream(path + "/C/" + fname);
							} else if (cppflag == 1) {
								fname = cname + ".cpp";
								fw = new FileOutputStream(path + "/CPP/" + fname);
							} else if (htmlflag == 1) {
								fname = cname + ".html";
								fw = new FileOutputStream(path + "/HTML/" + fname);
							} else if (pyflag == 1) {
								fname = cname + ".py";
								fw = new FileOutputStream(path + "/PYTHON/" + fname);
							}

							PrintStream pw = new PrintStream(fw);
							StringTokenizer st = new StringTokenizer(str, "\n");
							while (st.hasMoreTokens()) {
								pw.println(st.nextToken());
							}

							pw.flush();
							pw.close();

							jta1.setText("File is Successfully Saved");
						} catch (Exception t) {
							jta1.setText(t.getMessage());
						}
					} else {
						jta1.setText("Please use Saveas first");
					}
				}

			}
		});

		clear = new JMenuItem("Clear");
		clear.addActionListener(this);

		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		file.add(jnw);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.add(clear);
		file.add(exit);
		jmb.add(file);
		// ..............................................................................................//

		// ............................................EDIT
		// MENU.........................................//
		cut = new JMenuItem("Cut");// Edit Menu
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		del = new JMenuItem("Delete");
		undo = new JMenuItem("Undo");
		selectall = new JMenuItem("SelectAll");

		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		del.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.CTRL_MASK));

		cut.addActionListener(this);

		copy.addActionListener(this);

		paste.addActionListener(this);

		del.addActionListener(this);

		selectall.addActionListener(this);

		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(del);
		edit.add(selectall);
		jmb.add(edit);
		// .................................................................................//

		// .............................EXECUTE
		// MENU....................................................//

		compile = new JMenuItem("Compile");// Execute Menu
		compile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		compile.addActionListener(this);
		run = new JMenuItem("Run");
		run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		run.addActionListener(this);
		dec = new JMenuItem("Decompile");
		dec.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		dec.addActionListener(this);

		execute.add(compile);
		execute.add(run);
		execute.add(dec);

		jmb.add(execute);

		// .................................................................................//

		// ...................PROGRAM
		// MENU.............................................................//

		ButtonGroup bg2 = new ButtonGroup();// Program Menu
		java = new JRadioButtonMenuItem("Java", true);// Skin
		cp = new JRadioButtonMenuItem("C", false);
		cpp = new JRadioButtonMenuItem("C++", false);
		html = new JRadioButtonMenuItem("Html", false);
		py = new JRadioButtonMenuItem("Python", false);

		java.addActionListener(this);
		html.addActionListener(this);
		cp.addActionListener(this);
		cpp.addActionListener(this);
		py.addActionListener(this);

		bg2.add(java);
		bg2.add(html);
		bg2.add(cp);
		bg2.add(cpp);
		bg2.add(py);

		program.add(java);
		program.add(cp);
		program.add(cpp);
		program.add(py);
		program.add(html);

		jmb.add(program);

		// .................................................................................//

		// ......................... LOOK MENU
		// ........................................................//
		bg = new ButtonGroup();// Skin Menu
		sys = new JRadioButtonMenuItem("System", false);// Skin
		metl = new JRadioButtonMenuItem("Metal", true);
		nimb = new JRadioButtonMenuItem("Nimbus", false);
		motif = new JRadioButtonMenuItem("Motif", false);
		sys.addActionListener(this);
		metl.addActionListener(this);
		nimb.addActionListener(this);
		motif.addActionListener(this);
		bg.add(sys);
		bg.add(metl);
		bg.add(nimb);
		bg.add(motif);
		skin.add(sys);
		skin.add(metl);
		skin.add(nimb);
		skin.add(motif);

		ButtonGroup bg1 = new ButtonGroup();// Color Menu

		c1 = new JRadioButtonMenuItem("White & Blue", false);// Skin
		c2 = new JRadioButtonMenuItem("Blue & White", false);
		c3 = new JRadioButtonMenuItem("White & Brown", false);
		c4 = new JRadioButtonMenuItem("Gray & Black", false);
		c5 = new JRadioButtonMenuItem("Green & Yellow", false);
		c6 = new JRadioButtonMenuItem("White & Green", false);
		c7 = new JRadioButtonMenuItem("White & Red", false);
		c8 = new JRadioButtonMenuItem("Purple & Black", false);
		c9 = new JRadioButtonMenuItem("Default", true);

		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);
		c5.addActionListener(this);
		c6.addActionListener(this);
		c7.addActionListener(this);
		c8.addActionListener(this);
		c9.addActionListener(this);

		bg1.add(c1);
		bg1.add(c2);
		bg1.add(c3);
		bg1.add(c4);
		bg1.add(c5);
		bg1.add(c6);
		bg1.add(c7);
		bg1.add(c8);
		bg1.add(c9);

		color.add(c1);
		color.add(c2);
		color.add(c3);
		color.add(c4);
		color.add(c5);
		color.add(c6);
		color.add(c7);
		color.add(c8);
		color.add(c9);
		/*
		 * /color.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Color
		 * cl=JColorChooser.showDialog(jf,"Choose a color",colr); if(colr==null)
		 * colr=Color.LIGHT_GRAY; jf.getContentPane().setBackground(cl); } });/
		 */

		look.add(skin);
		look.add(color);

		Integer count[] = new Integer[20];
		int in = 10;
		for (int i = 0; i < 20; i++) {
			count[i] = in;
			in = in + 2;

		}
		jlist = new JList(count);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				jta.setFont(new Font("Italic", Font.BOLD, (Integer) jlist
						.getSelectedValue()));
			}
		});

		font.add(new JScrollPane(jlist));

		look.add(font);

		jmb.add(look);

		// .................................................................................//

		// ............................... ABOUT MENU
		// ..................................................//

		abt = new JMenuItem("About");// About Menu
		abt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s2 = "<html><b>Developed By:</b><font color='red'><br>SHAHROZ BEG</font><br>Version:1.6</html>";
				JOptionPane.showMessageDialog(null, s2, "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.add(abt);
		jmb.add(about);

		jf.setJMenuBar(jmb);

		// .................................................................................//

		// .............................. BUTTONS
		// ...................................................//
		jcompile = new JButton("Compile");// Buttons
		jcompile.setBounds(70, 355, 90, 35);
		jcompile.addActionListener(this);
		jf.add(jcompile);
		jrun = new JButton("Run");
		jrun.setBounds(220, 355, 90, 35);
		jrun.addActionListener(this);
		jf.add(jrun);
		jbdec = new JButton("Decompile");
		jbdec.setBounds(370, 355, 95, 35);
		jbdec.addActionListener(this);
		jf.add(jbdec);
		jclear = new JButton("Clear");
		jclear.setBounds(540, 355, 90, 35);
		jclear.addActionListener(this);
		jf.add(jclear);

		// ...............................Language
		// Indicator..................................................//

		jff = new JTextField();
		jff.setBounds(0, 0, 730, 33);
		jff.setText("\t         JAVA");
		jff.setEditable(false);
		jff.setBackground(Color.GRAY);
		jff.setForeground(Color.BLACK);
		jff.setFont(new Font("Plain", Font.BOLD, 30));
		jf.add(jff);

		// ............................. TEXTAREA
		// ...................................................//

		jta = new JTextArea(500, 500);// textarea
		jta.setSelectionColor(new Color(135, 206, 250));
		jta.setSelectedTextColor(Color.WHITE);
		jta.setBackground(Color.WHITE);
		jta.setForeground(Color.BLACK);
		jta.setFont(new Font("Italic", Font.BOLD, 20));
		jta.setBounds(5, 25, 250, 200);
		jta.setText("public class " + "\n" + "{" + "\n" + "            "
				+ "public static void main(String arg[])" + "\n" + "            "
				+ "{" + "\n" + "\n" + "            " + "}" + "\n" + "}");

		// jf.add(jta);

		jp = new JScrollPane(jta);// scrollpane
		// jp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		jp.setBounds(5, 25, 710, 320);

		TextLineNumber tln = new TextLineNumber(jta);
		jp.setRowHeaderView(tln);

		String s = "";
		try {
			FileInputStream fin = new FileInputStream("Editor.java");
			DataInputStream din = new DataInputStream(new BufferedInputStream(fin));

			if (s == "") {
				while (true) {
					String s1 = din.readLine();
					if (s1 != null) {
						s = s + s1;
						s = s + "\n";
					} else
						break;
				}
			}
		} catch (Exception dd) {
			jta1.setText(dd.getMessage());
		}

		String scol[] = s.split("\\W");
		java.util.List<String> keywords = new ArrayList<String>(5);
		keywords.add("System.out.println()");
		for (int i = 0; i < scol.length; i++) {
			if (!scol[i].equals(""))
				keywords.add(scol[i]);
		}

		Autocomplete autoComplete = new Autocomplete(jta, keywords);
		jta.getDocument().addDocumentListener(autoComplete);

		jta.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		jta.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());

		jf.add(jp);

		createTab();// JTabbedPane

		// ...................................POPUP MENU
		// ..............................................//

		jpm = new JPopupMenu();// PopupMenu

		jpm.setForeground(Color.BLUE);
		cut1 = new JMenuItem("Cut");// Edit Menu
		copy1 = new JMenuItem("Copy");
		paste1 = new JMenuItem("Paste");
		del1 = new JMenuItem("Delete");
		selectall1 = new JMenuItem("SelectAll");
		// undo=new JMenuItem("Undo");

		cut1.addActionListener(this);
		copy1.addActionListener(this);
		paste1.addActionListener(this);
		del1.addActionListener(this);
		selectall1.addActionListener(this);
		jpm.add(cut1);
		jpm.add(copy1);
		jpm.add(paste1);
		jpm.add(del1);
		jpm.add(selectall1);
		jta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e))
					jpm.show(jta, e.getX(), e.getY());
			}
		});
		jta.add(jpm);

		// .................................................................................//

		jf.setLayout(null);
		jf.setVisible(true);
	}

	void createTab() {
		JTabbedPane jtab = new JTabbedPane();// SwingConstants.BOTTOM);//Tab Pane
		// jtab.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		jtab.setBounds(5, 405, 710, 190);

		jpin = new JPanel();// JPannel
		// jpin.setSize(100,100);
		jpout = new JPanel();
		jpdec = new JPanel();

		jtin = new JTextArea(7, 57);// JTextArea
		jtin.setFont(new Font("Italic", Font.BOLD, 15));
		jta1 = new JTextArea(7, 57);
		jta1.setFont(new Font("Italic", Font.BOLD, 15));
		jta1.setBackground(Color.BLACK);
		jta1.setForeground(Color.WHITE);
		jta1.setEditable(false);
		jtdec = new JTextArea(7, 57);
		jtdec.setFont(new Font("Italic", Font.BOLD, 15));

		jsp = new JScrollPane(jtin);
		TextLineNumber tln = new TextLineNumber(jtin);
		jsp.setRowHeaderView(tln);

		jsp1 = new JScrollPane(jtdec);
		TextLineNumber tl = new TextLineNumber(jtin);
		jsp1.setRowHeaderView(tl);

		jpin.add(jsp);// Location
		jpout.add(new JScrollPane(jta1));
		jpdec.add(jsp1);

		jtab.addTab("Input", null, jpin, "Input");
		jtab.addTab("Output", null, jpout, "Output");
		jtab.addTab("Decompiler", null, jpdec, "Decompile");

		jtab.setSelectedIndex(1);

		jf.add(jtab);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jcompile || e.getSource() == compile && sflg == 1)// Compile
		{
			if (javaflag == 1 || cpflag == 1 || cppflag == 1) {
				cflg = 1;
				str = "";
				if (sflg == 1) {
					try {
						jta1.setText("");
						result = "";
						jta1.setText(fname);

						ProcessBuilder pb = null;

						if (javaflag == 1) {
							pb = new ProcessBuilder("javac ", fname);
							pb.directory(new File(path + "/JAVA/"));
							// error=Runtime.getRuntime().exec(path+"\\JAVA\\javac "+fname);
						} else if (cpflag == 1 || cppflag == 1) {
							// error=Runtime.getRuntime().exec(path+"\\C\\TCC "+fname);
							pb = new ProcessBuilder("TCC.exe", fname);
							if (cpflag == 1)
								pb.directory(new File(path + "/C/"));
							else if (cppflag == 1)
								pb.directory(new File(path + "/CPP/"));
						}
						// pb.redirectErrorStream(true);
						Process error = pb.start();

						BufferedReader err = new BufferedReader(
								new InputStreamReader(error.getErrorStream()));
						// error.getOutputStream().close();
						// error.getInputStream().close();

						while (true) {
							String temp = err.readLine();
							if (temp != null) {
								result = result + temp;
								result = result + "\n";
							} else
								break;
						}

						if (result.equals("")) {
							err.close();
						} else {
							jta1.setText(result);
							err.close();
						}
						error.getInputStream().close();
						error.getOutputStream().close();
						try {
							error.waitFor();
						} catch (Exception ex) {
							jta1.setText(ex.getMessage());
						}
						if (error.exitValue() == 0) {
							jta1.setText("Compilation Successful ");
						}
					} catch (Exception e2) {
						jta1.setText(e2.getMessage());
					}

				} else {
					jta1.setText("Error:Please save  the file");

				}

			} else if (pyflag == 1)
				jta1.setText("No need to compile this Python program.\nDirectly run the program");
			else if (htmlflag == 1)
				jta1.setText("It is an HTML so no need to compile.\nDirectly run the program");

		} else if (sflg == 0) {
			// jta1.setText("Error:Plz Save the File First");
		}
		if (e.getSource() == jrun || e.getSource() == run
				|| e.getSource() == jbdec || e.getSource() == dec)// Run
		{

			try {
				if (javaflag == 1 || pyflag == 1 || cpflag == 1 || cppflag == 1
						|| e.getSource() == jbdec || e.getSource() == dec) {

					String temp = "";
					String temp1 = "";
					result = "";

					ProcessBuilder pb = null;

					if (javaflag == 1) {

						String s1 = jtin.getText();
						String[] s = s1.split("\n");
						List ls = new ArrayList();
						ls.add("java");
						ls.add(cname);
						for (int i = 0; i < s.length; i++)
							ls.add(s[i]);
						pb = new ProcessBuilder(ls);
						pb.directory(new File(path + "/JAVA/"));
						// p=Runtime.getRuntime().exec(path+"JAVA\\java "+cname);
					}

					if (pyflag == 1) {
						String s1 = jtin.getText();
						String[] s = s1.split("\n");
						List ls = new ArrayList();
						ls.add("python");
						ls.add(fname);
						for (int i = 0; i < s.length; i++)
							ls.add(s[i]);
						pb = new ProcessBuilder(ls);
						pb.directory(new File(path + "/PYTHON/"));
						// p=Runtime.getRuntime().exec(path+"\\PYTHON\\python "+fname);
					}
					if (cpflag == 1) {
						String s1 = jtin.getText();
						String[] s = s1.split("\n");
						List ls = new ArrayList();
						ls.add(path + "/C/" + cname);
						for (int i = 0; i < s.length; i++)
							ls.add(s[i]);
						pb = new ProcessBuilder(ls);
						pb.directory(new File(path + "/C/"));
						// p=Runtime.getRuntime().exec(path+"\\C\\"+cname);
					}
					if (cppflag == 1) {
						String s1 = jtin.getText();
						String[] s = s1.split("\n");
						List ls = new ArrayList();
						ls.add(path + "/CPP/" + cname);
						for (int i = 0; i < s.length; i++)
							ls.add(s[i]);
						pb = new ProcessBuilder(ls);
						pb.directory(new File(path + "/CPP/"));
						// p=Runtime.getRuntime().exec(path+"\\CPP\\"+cname);
					}

					if (e.getSource() == jbdec || e.getSource() == dec) {
						if (javaflag == 1) {
							pb = new ProcessBuilder("javap ", cname);
							pb.directory(new File(path + "/JAVA/"));
							// p=Runtime.getRuntime().exec(path+"\\JAVA\\javap "+cname+".class");
						} else
							jta1.setText("Only Java programs can be decompiled.");
					}
					Process p = pb.start();
					BufferedReader it = new BufferedReader(new InputStreamReader(
							p.getInputStream()));
					BufferedReader error = new BufferedReader(new InputStreamReader(
							p.getErrorStream()));
					// DataOutputStream bw = new
					// DataOutputStream(p.getOutputStream());
					// BufferedWriter bw=new BufferedWriter(new
					// OutputStreamWriter(p.getOutputStream()));
					// bw.write("f");
					// bw.close();
					// bw.write("Shahroz");
					// bw.flush();
					// bw.write("Beg");
					// bw.flush();
					// bw.close();

					/*
					 * /String s1=jtin.getText(); String[] s=s1.split(" ");
					 * 
					 * for(int i=0;i<s.length;i++) { Scanner sc=new Scanner(s[i]);
					 * 
					 * if(sc.hasNextInt()) { bw.writeInt((int)sc.nextInt());
					 * bw.flush();
					 * 
					 * } else if(sc.hasNextDouble()) {
					 * bw.writeDouble(sc.nextDouble()); bw.flush();
					 * 
					 * } else if(sc.hasNextLine()) { bw.writeBytes(sc.nextLine());
					 * bw.flush(); } //bw.flush();
					 * 
					 * }
					 * 
					 * //getInput(out); bw.close(); /
					 */

					while (true) {
						temp = it.readLine();
						if (temp != null) {
							result = result + temp;
							result = result + "\n";
						} else
							break;
					}
					if (result.equals("")) {
						while (true) {
							temp1 = error.readLine();
							if (temp1 != null) {
								result = result + temp1;
								result = result + "\n";
							} else
								break;
						}
					}

					it.close();
					error.close();
					if (e.getSource() == jbdec || e.getSource() == dec) {
						if (javaflag == 1)
							jtdec.setText(result);
					} else
						jta1.setText(result);

				} else if (htmlflag == 1) {
					try {
						File f = new File(path + "/HTML/" + fname);
						Desktop.getDesktop().browse(f.toURI());
					} catch (Exception ii) {
						jta1.setText(ii.getMessage());
					}

				}
			}

			catch (Exception ll) {
				jta1.setText(ll.getMessage());
			}
		}

		// ........................................PROGRAM
		// FLAGS..............................//
		if (e.getSource() == java) {
			cpflag = 0;
			javaflag = 1;
			htmlflag = 0;
			cppflag = 0;
			pyflag = 0;
			jta.setText("");
			jta.setCaretColor(Color.BLACK);
			jff.setText("\t         JAVA");
			jta.setBackground(Color.white);
			jta.setForeground(Color.BLACK);
			jta.setText("public class " + "\n" + "{" + "\n" + "            "
					+ "public static void main(String arg[])" + "\n"
					+ "            " + "{" + "\n" + "\n" + "            " + "}"
					+ "\n" + "}");
			jcompile.setVisible(true);
			jbdec.setVisible(true);

		}
		if (e.getSource() == cp) {
			cpflag = 1;
			javaflag = 0;
			htmlflag = 0;
			cppflag = 0;
			pyflag = 0;
			jta.setText("");
			jta.setCaretColor(Color.BLACK);
			jff.setText("\t         C");
			jta.setText("#include<stdio.h> \n#include<conio.h> \nint main(int argc,char *argv[])  \n{ \n\n\n\n\n\nreturn 0; \n}");
			jta.setBackground(Color.white);
			jta.setForeground(Color.BLACK);
			jbdec.setVisible(false);

		}
		if (e.getSource() == cpp) {
			cpflag = 0;
			javaflag = 0;
			htmlflag = 0;
			cppflag = 1;
			pyflag = 0;
			jta.setText("");
			jta.setCaretColor(Color.BLACK);
			jff.setText("\t         C++");
			jta.setText("#include<iostream.h> \n#include<conio.h> \nint main(int argc,char *argv[])\n{\n\n\n\n\n\nreturn 0; \n}");
			jta.setBackground(Color.white);
			jta.setForeground(Color.BLACK);
			jbdec.setVisible(false);

		}
		if (e.getSource() == html) {
			cpflag = 0;
			javaflag = 0;
			htmlflag = 1;
			cppflag = 0;
			pyflag = 0;
			jta.setText("");
			jta.setCaretColor(Color.WHITE);
			jff.setText("\t         HTML");
			jta.setText("<html>\n<title></title>\n<head>\n<body>\n\n</body>\n</head>\n</html>");
			Color coloring = new Color(20, 25, 125);
			jta.setBackground(coloring);
			jta.setForeground(Color.white);
			jcompile.setVisible(false);
			jbdec.setVisible(false);

		}
		if (e.getSource() == py) {
			pyflag = 1;
			cpflag = 0;
			javaflag = 0;
			htmlflag = 0;
			cppflag = 0;
			jta.setText("");
			jta.setCaretColor(Color.BLACK);
			jff.setText("\t         PYTHON");
			jta.setText("#Write down your prgram below\nimport sys");
			jta.setBackground(Color.white);
			jta.setForeground(Color.BLACK);
			jcompile.setVisible(false);
			jbdec.setVisible(false);

		}

		// .......................................................................................//
		// if(oflg==1 & sflg==0 && javaflag==1)
		// jta1.setText("Error:Please Save the file first");

		// else if(cflg==0 && javaflag==1)
		// {
		// jta1.setText("Error: Please. . . Compile the File First");
		// }

		try {
			if (e.getSource() == sys) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(jf);
			} else if (e.getSource() == metl) {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(jf);
			} else if (e.getSource() == nimb) {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				SwingUtilities.updateComponentTreeUI(jf);
			} else if (e.getSource() == motif) {
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				SwingUtilities.updateComponentTreeUI(jf);
			}

		} catch (Exception exp) {
			jta1.setText("Problem in changing the GUI");
		}
		if (e.getSource() == copy || e.getSource() == copy1)
			jta.copy();
		if (e.getSource() == cut || e.getSource() == cut1)
			jta.cut();
		if (e.getSource() == paste || e.getSource() == paste1)
			jta.paste();
		if (e.getSource() == del || e.getSource() == del1)
			jta.setText(jta.getText().replace(jta.getSelectedText(), ""));
		if (e.getSource() == selectall || e.getSource() == selectall1)
			jta.selectAll();

		if (e.getSource() == jclear || e.getSource() == clear) {
			jta1.setText("");
			jtin.setText("");
			jtdec.setText("");
		}
		if (e.getSource() == c1) {
			Color coloring = new Color(100, 55, 120);
			jta.setBackground(Color.white);
			jta.setForeground(coloring);

		}
		// ------------------------------------------------------------
		if (e.getSource() == c2) {
			Color coloring = new Color(20, 25, 125);
			jta.setBackground(coloring);
			jta.setForeground(Color.white);

		}
		// ------------------------------------------------------------
		if (e.getSource() == c3) {
			Color coloring = new Color(150, 55, 20);
			jta.setBackground(coloring);
			jta.setForeground(Color.white);

		}
		// ...............................................................
		if (e.getSource() == c4) {
			Color coloring = new Color(150, 55, 20);
			jta.setBackground(Color.gray);
			jta.setForeground(Color.black);

		}
		// ------------------------------------------------------------
		if (e.getSource() == c5) {
			Color coloring = new Color(55, 100, 25);
			jta.setBackground(coloring);
			jta.setForeground(Color.yellow);

		}
		// ------------------------------------------------------------
		if (e.getSource() == c6)

		{
			jta.setBackground(Color.white);
			Color coloring = new Color(55, 100, 25);
			jta.setForeground(coloring);

		}
		// ------------------------------------------------------------
		if (e.getSource() == c7)

		{
			jta.setBackground(Color.WHITE);
			jta.setForeground(Color.RED);

		}
		if (e.getSource() == c8) {
			Color colo = new Color(150, 150, 255);
			jta.setBackground(colo);

			Color colo1 = new Color(20, 25, 125);
			jta.setForeground(colo1);

		}
		if (e.getSource() == c9) {

			jta.setBackground(Color.WHITE);
			jta.setForeground(Color.BLACK);

		}
	}

	public static void main(String... xxx) {
		new Editor();
	}
}
