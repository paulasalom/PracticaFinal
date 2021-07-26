/**
 * PAULA FERRER SALOM 
 * https://youtu.be/x5IexioRNc8
 */
package practicafinal;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PracticaFinal extends JFrame {

    private PanelCirculos panel;
    JPanel botones = new JPanel();
    Label balls = new Label();
    JTextField espacio = new JTextField();
    JTextField espacio1 = new JTextField();
    JCheckBox pared = new JCheckBox("With  walls");
    JCheckBox raton = new JCheckBox("Follow Mouse");
    JFormattedTextField texto = new JFormattedTextField();
    Integer valor = 3;//iniciamos la cantidad de bolas a 3

    public PracticaFinal() {
        setSize(1140, 590);
        setLayout(new BorderLayout());
        setResizable(false);
        setTitle("Flying Balls");
        setDefaultCloseOperation(PracticaFinal.EXIT_ON_CLOSE);
        inicializacion();
    }

    /**
     * Comoponentes gráficas de la ventana. Añadimos tambien el método para que
     * el panel escuche los movimientos de ratón (addM()).
     *
     */
    private void inicializacion() {
        panel = new PanelCirculos(valor);
        panel.addM();
        add(panel, BorderLayout.CENTER);
        panelBotones();
        add(botones, BorderLayout.EAST);
        
    }

  

    /**
     * Panel izquierdo formado por el texto #Balls, una caja de texto que
     * establece el número de bolas que se visualizarán y dos checkBox que
     * establecerán el modo con o sin paredes y el modo seguir o no al ratón.
     *
     */
    private void panelBotones() {

        botones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Box verticalBox = Box.createVerticalBox();
        Box vertical = Box.createVerticalBox();

        //establecemos un espacio para centrar las compomentes
        espacio.setEditable(false);
        espacio.setText("         ");
        espacio.setBorder(null);
        espacio.setFont(new java.awt.Font("Times New Roman", 1, 100));

        //establecemos un espacio para centrar las compomentes
        espacio1.setEditable(false);
        espacio1.setText("         ");
        espacio1.setBorder(null);
        espacio1.setFont(new java.awt.Font("Times New Roman", 1, 40));

        //Texto #Balls
        balls.setText("   #Balls");
        balls.setFont(new java.awt.Font("Times New Roman", 1, 50));

         //caja de texto para establecer el numero de bolas
        texto.setFont(new java.awt.Font("Times New Roman", 1, 50));
        texto.setHorizontalAlignment(SwingConstants.RIGHT);
        texto.setAlignmentX(CENTER_ALIGNMENT);
        texto.setValue(new Integer(valor));
        texto.addActionListener(leerTexto);
        
        //etiqueta With walls
        pared.setFont(new java.awt.Font("Times New Roman", 1, 20));
        pared.addItemListener(paredC);

        //etiqueta Follow mouse
        raton.setFont(new java.awt.Font("Times New Roman", 1, 20));
        raton.addItemListener(ratonC);

       

        //añadimos los componentes checkbox a una caja
        vertical.setAlignmentX(CENTER_ALIGNMENT);
        vertical.add(pared);
        vertical.add(raton);

        //añadimos todas las componentes a una caja para añadirlas al panel izquierdo
        verticalBox.add(espacio);
        verticalBox.add(balls);
        verticalBox.add(texto);
        verticalBox.add(espacio1);
        verticalBox.add(vertical);
        botones.add(verticalBox);

    }
      /**
     * Escuchador que establece el número de círculos que se visualizarán en el
     * panel según el texto introducido.
     *
     */
    ActionListener leerTexto = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evento) {
            panel.crearcirc();
            valor = (Integer) texto.getValue();
            panel.setNUMCIR(valor);

        }

    };

    /**
     * Escuchador que cambia el valor del booleano raton de PanelCirculos en
 función de si se encuentra o no pulsado el checkBox.
     *
     */
    ItemListener ratonC = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                panel.setRaton(true);
            } else {
                panel.setRaton(false);
            }
        }
    };

    /**
     * Escuchador que cambia el valor del booleano paredes de PanelCirculos en
 función de si se encuentra o no pulsado el checkBox.
     *
     */
    ItemListener paredC = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED) {
                panel.setParedes(true);
            } else {
                panel.setParedes(false);
            }
        }
    };

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new PracticaFinal().setVisible(true);
    }

}
