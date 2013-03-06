package potencia_logaritmo;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.math.*;


public class Interfaz extends JFrame {
	Potencia pot;
	Vector <BigInteger> logs;
	boolean temp;
	boolean escribir;
	Primo prim;
	Logaritmo log;
	BigInteger resultado;
	public String primo,base,potencia;
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton Primos = null;
	private JButton Potencia = null;
	private JButton Logaritmo = null;
	private JLabel jLabel = null;
	private JTextField a = null;
	private JTextField b = null;
	private JTextField p = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JTextField tiempo = null;
	private JRadioButton jRadioButton = null;
	private JLabel jLabel41 = null;
	/**
	 * This is the default constructor
	 */
	public Interfaz() {
		super();
		initialize();
	}
	
	private boolean comprobar_cadena(String par){
		boolean salida;
		try{ 
		     BigInteger numero = new BigInteger(par); 
		     salida=false;
		}catch(NumberFormatException e){ 
		     //La cadena no se puede convertir a entero 
			salida=true;
		}  
		try{
			BigDecimal num = new BigDecimal(par);
			salida=false;
		}catch(NumberFormatException e){
			salida=true;
		}
		return salida;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(776, 265);
		this.setContentPane(getJContentPane());
		this.setTitle("Practica 1");
		temp=false;
		escribir=true;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel41 = new JLabel();
			jLabel41.setBounds(new Rectangle(500, 176, 82, 16));
			jLabel41.setText("Activar tiempo");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(500, 130, 177, 16));
			jLabel4.setText("a=opcion, b=pasadas, p =primo");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(500, 88, 91, 16));
			jLabel3.setText("log [a] b (mod p)");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(500, 44, 67, 16));
			jLabel2.setText("a^b (mod p)");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(53, 14, 128, 16));
			jLabel.setText("Selecciona una opci�n");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLogaritmo(), null);
			jContentPane.add(getPrimos(), null);
			jContentPane.add(getPotencia(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getA(), null);
			jContentPane.add(getB(), null);
			jContentPane.add(getP(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getTiempo(), null);
			jContentPane.add(getJRadioButton(), null);
			jContentPane.add(jLabel41, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes Primos	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPrimos() {
		if (Primos == null) {
			Primos = new JButton();
			Primos.setText("Primos");
			Primos.setBounds(new Rectangle(75, 125, 92, 26));
			Primos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					primo = p.getText();
					base = a.getText();
					potencia = b.getText();
					BigDecimal aus;
					long ti;
					if(base.length()==0 || potencia.length()==0 || comprobar_cadena(base) || comprobar_cadena(potencia)){
						JOptionPane.showMessageDialog(null, "Error en los par�metros falta alguno o todos los par�metros\n");
					}
					else{
						if(base != null && base.equals("0")){
							escribir=false;
							if(primo.length()==0 ||comprobar_cadena(primo)){
								JOptionPane.showMessageDialog(null, "No se ha escrito un n�mero en el campo primo");
							}
							else{
								resultado = new BigInteger(primo);
								aus=new BigDecimal(potencia);
								if(Primo.comprobar_impar(resultado)){
									if(aus.compareTo(BigDecimal.valueOf(1)) >=1)
										prim = new Primo(primo,Integer.parseInt(potencia));
									else
										prim = new Primo(primo,potencia);
									ti = System.nanoTime();
									if(prim.algoritmo()){
										JOptionPane.showMessageDialog(null, "Es probable que el valor sea primo");
									}
									else{
										JOptionPane.showMessageDialog(null, "No es primo");
									}
									ti = System.nanoTime() - ti;
									if(aus.compareTo(BigDecimal.valueOf(1)) >=1)
										b.setText(prim.porcentaje());
									else
										b.setText(prim.pasadas());
									if(temp)
										tiempo.setText(ti+" ns");
									else
										tiempo.setText("");
										
								}
								else{
									JOptionPane.showMessageDialog(null, "El n�mero p introducido es par o menor que 5");
								}	
							}
						}
						else{
							escribir=false;
							Random ran = new Random();
							aus=new BigDecimal(potencia);

							int num=ran.nextInt(9);
							while(num == 0){
								num=ran.nextInt(9);
							}
							String auxiliar = new String();
							auxiliar += num+"";
							for(int i=1; i < Integer.parseInt(base); i++){
								auxiliar += ran.nextInt(9)+"";
							}
	
							resultado = new BigInteger(auxiliar);
							BigInteger aux [];
							aux =resultado.divideAndRemainder(BigInteger.valueOf(2));
							
							
							if(aux[1].compareTo(BigInteger.valueOf(0))==0)
								resultado = resultado.subtract(BigInteger.valueOf(1));
	
							p.setText(resultado.toString());
							
							if(Primo.comprobar_impar(resultado)){
								if(aus.compareTo(BigDecimal.valueOf(1)) >=1)
									prim = new Primo(primo,Integer.parseInt(potencia));
								else
									prim = new Primo(primo,potencia);
								ti = System.nanoTime();
								if(prim.algoritmo()){
									JOptionPane.showMessageDialog(null, "Es probable que el valor sea primo");
								}
								else{
									JOptionPane.showMessageDialog(null, "No es primo");
								}
								ti = System.nanoTime() - ti;
								if(aus.compareTo(BigDecimal.valueOf(1)) >=1)
									b.setText(prim.porcentaje());
								else
									b.setText(prim.pasadas());
								

								if(temp)
									tiempo.setText(ti+" ns");
								else
									tiempo.setText("");
									
							}
							else{
								JOptionPane.showMessageDialog(null, "El n�mero p introducido es par o menor que 5");
							}	
							
	
						}
					}
				}
			});
		}
			
		return Primos;
	}

	/**
	 * This method initializes Potencia	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPotencia() {
		if (Potencia == null) {
			Potencia = new JButton();
			Potencia.setText("Potencia");
			Potencia.setBounds(new Rectangle(75, 44, 94, 26));
			Potencia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					primo = p.getText();
					base = a.getText();
					potencia = b.getText();
					if(primo.length()==0|| base.length()==0 || potencia.length()==0 || comprobar_cadena(primo)
							|| comprobar_cadena(base) || comprobar_cadena(potencia)){
						JOptionPane.showMessageDialog(null, "Error en los par�metros falta alguno o todos los par�metros\n");
					}
					else{
						pot = new Potencia(base,potencia,primo);
						escribir=false;
						long ti = System.nanoTime();
						resultado = pot.Apotenciacion();
						ti = System.nanoTime() - ti;
						if(temp)
							tiempo.setText(ti+" ns");
						else
							tiempo.setText("");
						JOptionPane.showMessageDialog(null, "El resultado de hacer la potencia es:\n" +resultado);
					}
				}
			});
		}
		return Potencia;
	}

	/**
	 * This method initializes Logaritmo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLogaritmo() {
		if (Logaritmo == null) {
			Logaritmo = new JButton();
			Logaritmo.setText("Logaritmo");
			Logaritmo.setBounds(new Rectangle(75, 85, 92, 26));
			Logaritmo.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					primo = p.getText();
					base = a.getText();
					potencia = b.getText();
					if(primo.length()==0|| base.length()==0 || potencia.length()==0 || comprobar_cadena(primo)
                                            || comprobar_cadena(base) || comprobar_cadena(potencia)){
						JOptionPane.showMessageDialog(null, "Error en los par�metros falta alguno o todos los par�metros\n");
					}
					else{
						escribir=false;
						log = new Logaritmo(base,potencia,primo);
						logs = new Vector <BigInteger>();
						long ti = System.currentTimeMillis();
						logs = log.AShanks();
						ti = System.currentTimeMillis() - ti;
						if(temp)
							tiempo.setText(ti+" ms");
						else
							tiempo.setText("");
						String mensaje;
						mensaje = logs.size()+"\n";
						if(logs.size() > 0)
							for(int i=0; i < logs.size(); i++)
								mensaje += logs.elementAt(i)+"\n";
						JOptionPane.showMessageDialog(null, "El n�mero de soluciones al hacer el logaritmo es: " +mensaje);
					}
				}
			}
                       );
		}
		return Logaritmo;
	}

	/**
	 * This method initializes a	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getA() {
		if (a == null) {
			a = new JTextField();
			a.setBounds(new Rectangle(270, 43, 181, 20));
			a.setText("Introduzca a");
			a.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(escribir)
						a.setText("");
				}
			});
		}
		return a;
	}

	/**
	 * This method initializes b	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getB() {
		if (b == null) {
			b = new JTextField();
			b.setBounds(new Rectangle(270, 85, 182, 20));
			b.setText("Introduzca b");
			b.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(escribir)
						b.setText("");
				}
			});
		}
		return b;
	}

	/**
	 * This method initializes p	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getP() {
		if (p == null) {
			p = new JTextField();
			p.setBounds(new Rectangle(270, 128, 181, 20));
			p.setText("Introduzca p");
			p.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(escribir)
						p.setText("");
				}
			});
		}
		return p;
	}
	
	/**
	 * This method initializes tiempo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTiempo() {
		if (tiempo == null) {
			tiempo = new JTextField();
			tiempo.setBounds(new Rectangle(270, 176, 181, 20));
			tiempo.setText("Duraci�n del algoritmo");
		}
		return tiempo;
	}

	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			jRadioButton.setBounds(new Rectangle(103, 174, 21, 21));
			jRadioButton.setText("");
			jRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(temp)
						temp=false;
					else
						temp=true;
				}
			});
		}
		return jRadioButton;
	}

	public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }


}  //  @jve:decl-index=0:visual-constraint="26,110"


