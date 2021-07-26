/**
 * PAULA FERRER SALOM 
 * https://youtu.be/x5IexioRNc8
 */
package practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JPanel;

public final class PanelCirculos extends JPanel implements ActionListener {

    Circulo circ = new Circulo();
    private int NUMCIR;//Cantidad de circulos que se visualizarán
    private Circulo[] circulos;//Array de una dimensión formado por un conjunto de círculos
    private final long DELAY = 1;//Valor de retardo
    private final int dimensionx = 900;//Dimensión del panel en el eje x
    private final int dimensiony = 550;//Dimensión del panel en el eje y
    double xr;//Posición del ratón en el eje x
    double yr;//Posición del ratón en el eje y
    private boolean paredes;//Valor booleano que establece si hay o no paredes
    private boolean raton;//Valor booleano que establece si se sigue o no el ratón

    public PanelCirculos() {
    }

    /**
     * Panel con la cantidad de círculos pasada por parámetro, que llama al
     * método crearcirc() para llenar el array de círculos.
     *
     * @param NUMCIR
     */
    public PanelCirculos(Integer NUMCIR) {
        
        this.NUMCIR = NUMCIR;
        crearcirc();
    }

    /**
     * Método que pinta los círculos en el panel y llama al método mover(i) para
     * cambiar la posición de cada círculo.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        try{
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, dimensionx, dimensiony);
        for (int i = 0; i < NUMCIR; i++) {

            circ.pintarCirc(g, circulos[i].GetX(), circulos[i].GetY(), circulos[i].getCOLOR());
            mover(i);
            repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException err) {
                System.out.println(err);
            }
        }
        }catch (ArrayIndexOutOfBoundsException error){
            System.out.println("El número introducido es demasiado grande.");
        }
    }

    /**
     * Método que crea los círculos y los guarda dentro del array de círculos.
     * Cada vez que se llame a este método se creará una nueva combinación de
     * círculos, cada uno con su propio color y posición.
     *
     */
    public void crearcirc() {
        circulos = new Circulo[100];
        Random aleatorio = new Random();
        for (int i = 0; i < 100; i++) {
            Color COLOR = new Color((int) (Math.random() * 0x1000000));
            double x = aleatorio.nextInt(dimensionx - (3 * circ.getDIAMETRO()));
            double y = aleatorio.nextInt(dimensiony - (3 * circ.getDIAMETRO()));
            circulos[i] = new Circulo(x, y, COLOR);
        }
    }

    /**
     * Método que establece el tipo de movimiento de los círculos en función de
     * lo indicado por el usuario a través de las checkBox. Según el tipo de
     * movimiento se establece la gravedad y la posición de la bola, en el caso
     * de seguimiento de ratón se introducen las coordenadas del ratón
     * "escuchadas" por el panel para poder calcular la aceleración; también
     * establecemos si hay o no paredes.
     *
     * @param i
     */
    private void mover(int i) {

        if (paredes && !raton) {
            circulos[i].setGrav();
            circulos[i].setVel();
            circulos[i].setPos();
            circulos[i].setParedes(false);
        }
        if (!paredes && !raton) {
            circulos[i].setGrav();
            circulos[i].setVel();
            circulos[i].setPos();
            circulos[i].setSinParedes(false);
        }
        if (paredes && raton) {
            circulos[i].setXR(xr);
            circulos[i].setYR(yr);
            circulos[i].setAceRaton();
            circulos[i].setVel();
            circulos[i].setPos();
            circulos[i].setParedes(true);
        }
        if (!paredes && raton) {
            circulos[i].setXR(xr);
            circulos[i].setYR(yr);
            circulos[i].setAceRaton();
            circulos[i].setVel();
            circulos[i].setPos();
            circulos[i].setSinParedes(true);
        }

    }

    public void addM() {
        addMouseMotionListener(new MouseHandler());
    }

    /**
     * Método para poder cambiar el valor de la variable booleana paredes para
     * establecer si hay o no paredes.
     *
     * @param paredes
     */
    public void setParedes(boolean paredes) {
        this.paredes = paredes;
    }

    /**
     * Método para poder cambiar el valor de la variable booleana raton para
     * establecer si se sigue o no al ratón.
     *
     * @param raton
     */
    public void setRaton(boolean raton) {
        this.raton = raton;
    }

    /**
     * Método para poder cambiar el valor de la variable NUMCIR para establecer
     * la cantidad de circulos que se visualizarán.
     *
     * @param NUMCIR
     */
    public void setNUMCIR(int NUMCIR) {
        this.NUMCIR = NUMCIR;
    }

    private class MouseHandler extends MouseAdapter {

        /**
         * Control de sucesos del ratón. Cada vez que se mueve el ratón se
         * guarda la posición de este en las variables xr(para el eje x) y
         * xy(para el eje y)
         *
         * @param e
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            for (int i = 0; i < NUMCIR; i++) {
                xr = e.getX();
                yr = e.getY();
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

}
