import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Window extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnComenzar;
	Integer mIndice;
	String[] arrCadenas;
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JScrollPane scrollPane;
	private JList<String> lstItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnComenzar = new JButton("Comenzar");
		btnComenzar.addActionListener(this);
		panel.add(btnComenzar, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		lstItems = new JList<String>();
		scrollPane.setViewportView(lstItems);
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == btnComenzar) {
			int i = 0;
			arrCadenas = new String[1];
			mIndice = arrCadenas.length;
			//arrCadenas = Redim(arrCadenas,1);
	        arrCadenas[0] = "MI"; //axioma
	        listModel.addElement("MI");
	        lstItems.setModel(listModel);
	        while (i < 100 && !arrCadenas[mIndice-1].equals("MU")){  //implementación del "aburrimiento". Lo lógico sería preguntar while ArrCadenas(i) <> "MU" 
	            GeneraMU(arrCadenas[i]);
	            listModel.addElement(arrCadenas[i]);
		        lstItems.setModel(listModel);
	            i++;
	        }
		}
	}
	
	//toma una cadena de entrada y la pasa por las 4 reglas con el objetivo de saber cual de ellas es aplicable
    public void GeneraMU(String cadena){
    	String cad;
    	int longitud;
    	String CadenaSal1, CadenaSal2, CadenaSal3, CadenaSal4;
    	longitud = cadena.length();
    	//toma último caracter de la cadena
    	cad = cadena.substring(longitud-1);
    	if (cad.equals("I")){
    		CadenaSal1 = Regla1(cadena);
    		BuscaMU(cadena, CadenaSal1);
    	}
    	CadenaSal2 = Regla2(cadena);
    	BuscaMU(cadena, CadenaSal2);
    	
    	if (cadena.indexOf("III") != -1) {
                //puede aplicarse regla 3
                CadenaSal3 = Regla3(cadena);
                BuscaMU(cadena, CadenaSal3);
    	}
    	if (cadena.indexOf("UU") != -1) {
                //Puede aplicar regla 4
                CadenaSal4 = Regla4(cadena);
                BuscaMU(cadena, CadenaSal4);
    	}
    }

 //Compara la cadena obtenida con la cadena de entrada y busca si es MU, si no es, la añade al array para usarla posteriormente 
 //como parámetro de entrada
    private void BuscaMU(String cadena, String CadenaSal){
        if (!CadenaSal.equals(cadena)) {  //si la cadena es igual es que no hubo transformación por la regla y no se tiene en cuenta.
            if (!CadenaSal.equals("MU")) {
                mIndice = mIndice + 1;  //actualiza el indice del array
                arrCadenas = Redim(arrCadenas,mIndice);
                arrCadenas[mIndice-1] = CadenaSal;
            }
            else{
            	mIndice = mIndice + 1;  //actualiza el indice del array
                arrCadenas = Redim(arrCadenas,mIndice);
                arrCadenas[mIndice-1] = CadenaSal;
    	        listModel.addElement(arrCadenas[mIndice-1]);
    	        lstItems.setModel(listModel);
            	JOptionPane.showMessageDialog(this, "¡¡¡MU ENCONTRADO!!!" + CadenaSal);
            }
        }
    }
    
    public String[] Redim(String[] arreglo, int longitud){
    	String[] nuevo = new String[longitud];
    	for(int i = 0; i< arreglo.length; i++)
    		nuevo[i] = arreglo[i];
    	return nuevo;
    }
    
    //Regla1 Si MxI añade U quedando MxIU
    public String Regla1(String Cadena) {
        Cadena = Cadena + "U";
        return Cadena;
    }

    //Regla 2 Añade x a Mx devolviendo Mxx
    public String Regla2(String Cadena) {
    	if (Cadena.length() <= 2 )
    		Cadena = Cadena + Cadena.substring(Cadena.length()-1);
    	else if(Cadena.length() > 2 )
    		Cadena = Cadena + Cadena.substring(Cadena.length()-1);
        return Cadena;
    }

    //Regla 3 sustituye III por U en cualquier lugar de la cadena
    public String Regla3(String Cadena) {
        Cadena = Cadena.replaceAll("III", "U");
        return Cadena;
        }

    //Regla 4 elimina UU en cualquier lugar de la cadena
    public String Regla4(String Cadena) {
        Cadena = Cadena.replaceAll("UU", "");
        return Cadena;

        }

}
