import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
//import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.util.*;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;


public class Library {

	private JFrame frame;
	private JTextField textUsername;
	private JTextField textMobile;
	private JTextField textBook;
	static  ArrayList<String> allBooks = new ArrayList<String>();
	static ArrayList<String> booksAvailable = new ArrayList<String>();
	private JTextField textReturnUsername;
	private JTextField textReturnMobile;
	private JTextField textReturnBook;
	private static ResultSet resultSetData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnect connect = new DBConnect();
					resultSetData = connect.getData();
					//System.out.println("resultSetData in main is below");
					
					while(resultSetData.next()){
						//System.out.println(resultSetData);
						String bookName = resultSetData.getString("bookName");
						String isWith = resultSetData.getString("isWith");
						//System.out.println(bookName + "is with" + isWith);
						allBooks.add(bookName);
						//System.out.println(isWith);
						if(isWith.equals("library")){
							booksAvailable.add(bookName);
						}
					}
					
					
					Library window = new Library();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Library() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 755, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 93, 719, 336);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("BORROW", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.WHITE, 5));
		panel_3.setBounds(10, 11, 694, 337);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(267, 10, 0, 0);
		panel_3.add(label);
		
		textUsername = new JTextField();
		textUsername.setBounds(267, 48, 86, 20);
		panel_3.add(textUsername);
		textUsername.setColumns(10);
		
		textMobile = new JTextField();
		textMobile.setBounds(267, 96, 86, 20);
		panel_3.add(textMobile);
		textMobile.setColumns(10);
		
		textBook = new JTextField();
		textBook.setBounds(267, 148, 86, 20);
		panel_3.add(textBook);
		textBook.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(87, 48, 86, 20);
		panel_3.add(lblName);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMobile.setBounds(87, 96, 86, 27);
		panel_3.add(lblMobile);
		
		JLabel lblBook = new JLabel("Book");
		lblBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setBounds(87, 148, 73, 20);
		panel_3.add(lblBook);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookRequired = textBook.getText();
				String mobile = textMobile.getText();
				String userName = textUsername.getText();
				textUsername.setText(null);
				textMobile.setText(null);
				textBook.setText(null);
				
				if(booksAvailable.contains(bookRequired)){
					DBConnect connect = new DBConnect();
					connect.setData(bookRequired, userName, mobile);
					booksAvailable.remove(bookRequired);
					//System.out.println(booksAvailable);
					JOptionPane.showMessageDialog(null, "SUCCESS! You can borrow the book!");
				}
				else{
					JOptionPane.showMessageDialog(null, "This book is not available!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSubmit.setBounds(264, 202, 89, 23);
		panel_3.add(btnSubmit);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textUsername.setText(null);
				textMobile.setText(null);
				textBook.setText(null);
			}
		});
		btnReset.setBounds(385, 202, 89, 23);
		panel_3.add(btnReset);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("RETURN", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(83, 41, 67, 33);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MOBILE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(83, 85, 67, 33);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblBook_1 = new JLabel("Book");
		lblBook_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBook_1.setBounds(83, 129, 67, 33);
		panel_1.add(lblBook_1);
		
		textReturnUsername = new JTextField();
		textReturnUsername.setBounds(160, 47, 86, 20);
		panel_1.add(textReturnUsername);
		textReturnUsername.setColumns(10);
		
		textReturnMobile = new JTextField();
		textReturnMobile.setBounds(160, 91, 86, 20);
		panel_1.add(textReturnMobile);
		textReturnMobile.setColumns(10);
		
		textReturnBook = new JTextField();
		textReturnBook.setBounds(160, 135, 86, 20);
		panel_1.add(textReturnBook);
		textReturnBook.setColumns(10);
		
		JButton btnSubmit_1 = new JButton("SUBMIT");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookReturned = textReturnBook.getText();
				String userName = "library"; //It is with library after returned
				String mobile = "";
				textReturnUsername.setText(null);
				textReturnMobile.setText(null);
				textReturnBook.setText(null);
				if(allBooks.contains(bookReturned)&& booksAvailable.contains(bookReturned)== false){
					DBConnect connect = new DBConnect();
					connect.setData(bookReturned, userName, mobile);
					booksAvailable.add(bookReturned);
					//System.out.println(booksAvailable);
					JOptionPane.showMessageDialog(null, "THANK YOU!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Enter the correct book name", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubmit_1.setBounds(157, 189, 89, 23);
		panel_1.add(btnSubmit_1);
		
		JButton btnReset_1 = new JButton("RESET");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textReturnUsername.setText(null);
				textReturnMobile.setText(null);
				textReturnBook.setText(null);
			}
		});
		btnReset_1.setBounds(282, 189, 89, 23);
		panel_1.add(btnReset_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("ALL BOOKS", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTextArea txtrClickRefresh = new JTextArea();
		txtrClickRefresh.setText("CLICK REFRESH!!!");
		txtrClickRefresh.setBounds(10, 11, 553, 241);
		panel_2.add(txtrClickRefresh);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrClickRefresh.setText("ALL BOOKS\n");
				for(int counter=0; counter< allBooks.size(); counter++){
					txtrClickRefresh.append(" "+(counter+1)+". "+allBooks.get(counter)+"\n");
				}
				txtrClickRefresh.append("\nAVAILABLE NOW\n");
				for(int counter=0; counter< booksAvailable.size(); counter++){
					txtrClickRefresh.append(" "+(counter+1)+". "+booksAvailable.get(counter)+"\n");
				}

			}
		});
		btnRefresh.setBounds(179, 263, 89, 23);
		panel_2.add(btnRefresh);
		
		JLabel lblLibraryRecords = DefaultComponentFactory.getInstance().createTitle("Library Records");
		lblLibraryRecords.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLibraryRecords.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryRecords.setBounds(211, 28, 273, 40);
		frame.getContentPane().add(lblLibraryRecords);
		//textArea.setText(allBooks);
		
	}
}
