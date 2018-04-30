import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VacheTaurreau extends JFrame {

	//Class Generated Serial Version UID
	private static final long serialVersionUID = -789106938248574630L;
	//The Panel containing GUI components
	private JPanel contentPane;
	//The TextField containing the value requested by the user
	private JTextField requestedValue;
	//The generated secret value generated automatically by the system
	private String generatedSecretValue;
	//the number of attempts provided by the user
	private int counter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacheTaurreau frame = new VacheTaurreau();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VacheTaurreau() {
		setTitle("Local Vache Taureau");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		counter=0;
		
		JLabel secretValue = new JLabel("");
		secretValue.setBounds(95, 36, 100, 14);
		secretValue.setHorizontalAlignment(SwingConstants.CENTER);
		secretValue.setDisplayedMnemonic('*');
		secretValue.setToolTipText("Secret Value");
		contentPane.add(secretValue);
		
		JLabel lblSecretValue = new JLabel("Secret Value");
		lblSecretValue.setBounds(117, 11, 89, 14);
		contentPane.add(lblSecretValue);
		
		JLabel lblRequestedValue = new JLabel("Requested Value");
		lblRequestedValue.setBounds(107, 59, 100, 14);
		contentPane.add(lblRequestedValue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 145, 138, 137);
		contentPane.add(scrollPane);
		
		JTextPane result = new JTextPane();
		result.setEditable(false);
		scrollPane.setViewportView(result);
		
		requestedValue = new JTextField();
		requestedValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar()==KeyEvent.VK_ENTER) {

					if(requestedValue.getText().toString().length()!=4) {
						JOptionPane.showMessageDialog(null, "V�rifiez votre valeur en entr�e!");
						requestedValue.requestFocus();
					}else {
					result.setText(result.getText()+requestedValue.getText()+" ===> "+results(requestedValue.getText())+'\n');
					requestedValue.requestFocus();
					counter++;

						if(requestedValue.getText().equals(generatedSecretValue)) {
							secretValue.setText(generatedSecretValue);
							JOptionPane.showMessageDialog(null, "Great Job! \n you finished in "+counter+" step(s)!");
						}
					}
					requestedValue.setText("");
				
				}
			}
		});
		requestedValue.setBounds(108, 84, 86, 20);
		contentPane.add(requestedValue);
		requestedValue.setColumns(4);
		
		generatedSecretValue=generateSecretValue();
		secretValue.setText("****");
		
		
		
		
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.setBounds(107, 111, 89, 23);
		btnVerify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(requestedValue.getText().toString().length()!=4) {
					JOptionPane.showMessageDialog(null, "V�rifiez votre valeur en entr�e!");
					requestedValue.requestFocus();
				}else {
				result.setText(result.getText()+'\n'+requestedValue.getText()+" ===> "+results(requestedValue.getText())+'\n');
				requestedValue.requestFocus();
				counter++;

					if(requestedValue.getText().equals(generatedSecretValue)) {
						secretValue.setText(generatedSecretValue);
						JOptionPane.showMessageDialog(null, "Great Job! \n you finished in "+counter+" step(s)!");
					}
				}
				requestedValue.setText("");
			}
		});
		contentPane.add(btnVerify);		
		
		JButton startNewGame = new JButton("Start a new Game");
		startNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				generatedSecretValue=generateSecretValue();
				requestedValue.setText("");
				result.setText("");
				secretValue.setText("****");
				counter=0;
				requestedValue.requestFocus();
			}
		});
		startNewGame.setBounds(83, 293, 138, 34);
		contentPane.add(startNewGame);
	}
	
	/**
	 * Comparing the user input with the generated secret value 
	 */
	private String results(String input) {
		String vs="";
		String ts="";
		
		for(int i=0; i<4; i++) {
			if(generatedSecretValue.contains(String.valueOf(input.charAt(i)))){
				if(generatedSecretValue.indexOf(String.valueOf(input.charAt(i)))==i)
					ts+='T';
					else
						vs+='V';
			}
		}		
		return ts+vs;
	}
	
	/**
	 * Generating a completely random value composed of four unique digits and not starting with zero
	 */
	private String generateSecretValue() {
		String s="";	
		while(s.length()<4 && s.charAt(0)!='0') {
			int r=(int)(Math.random()*10);
			if(!s.contains(String.valueOf(r)))
				s+=String.valueOf(r);	
		}
		System.out.println(s);
		return s;
	}	
}