/**
 * PAULA FERRER SALOM
 * https://youtu.be/x5IexioRNc8
 */
package practicafinal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class Circulo extends JComponent {

    Vector posicion;
    private static int DIAMETRO = 70;// valor del diametro de la bola
    private Color COLOR;//color de la bola
    private double aceleracionx;//aceleración de la bola en el eje x
    private double aceleraciony;//aceleración de la bola en el eje y
    private double vx = 0;//velocidad de la bola en el eje x
    private double vy = 0;//velocidad de la bola en el eje y
    private final double bounce = -1;//valor que cambiará la dirección de la bola

    public Circulo() {
    }

    /**
     * Un círculo está compuesto por dos coordenadas, la del eje x y la del eje
     * y, además de un color.
     *
     * @param x
     * @param y
     * @param color
     */
    public Circulo(double x, double y, Color color) {
        this.COLOR = color;
        posicion = new Vector(x, y);
    }

    /**
     * Método pinta un círculo. Se pinta el círculo con el color establecido y
     * su contorno en negro en las coordenadas x e y correspondientes.
     *
     * @param g
     * @param x
     * @param y
     * @param COLOR
     *
     * @return g
     */
    public Graphics pintarCirc(Graphics g, double x, double y, Color COLOR) {
        Graphics2D FORMA = (Graphics2D) g;
        Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
        FORMA.setColor(COLOR);
        Ellipse2D elipse = new Ellipse2D.Double(x, y, DIAMETRO, DIAMETRO);
        FORMA.fill(elipse);
        FORMA.setColor(Color.BLACK);
        FORMA.setStroke(stroke);
        FORMA.draw(elipse);
        return g;
    }

    /**
     * Devuelve el valor de la coordenada X del Vector.
     *
     * @return x
     */
    public double GetX() {
        double x = posicion.getX();
        return x;
    }

    /**
     * Devuelve el valor de la coordenada Y del Vector.
     *
     * @return y
     */
    public double GetY() {
        double y = posicion.getY();
        return y;
    }

    /**
     * Devuelve el color del círculo.
     *
     * @return COLOR
     */
    public Color getCOLOR() {
        return COLOR;
    }

    /**
     * Devuelve el diámetro del círculo.
     *
     * @return DIAMETRO
     */
    public int getDIAMETRO() {
        return DIAMETRO;
    }

    /**
     * Método que establece el comportamiento de la bola con los límites cuando
     * se encuentra en el modo con paredes.
     *
     * @param raton
     */
    public void setParedes(boolean raton) {
        if (posicion.getX() > 900 - DIAMETRO || posicion.getX() < 0) {
            vx *= bounce;
        }
        if (posicion.getY() > 550 - DIAMETRO || posicion.getY() < 0) {
            vy *= bounce;
            if (posicion.getY() > 550 - DIAMETRO && !raton) {
                posicion.setY(550 - DIAMETRO);
                vy += 0.7;
                vx = 0;
                aceleracionx = 0;
            }
        }

        if (posicion.getY() < -1) {
            posicion.setY(0);
            vy += 0.3;
        }
        if (raton) {
            limvelocidad();
        }
    }

    /**
     * Método que establece el comportamiento de la bola con los límites cuando
     * se encuentra en el modo sin paredes.
     *
     * @param raton
     */
    public void setSinParedes(boolean raton) {
        if (posicion.getX() > 900 - DIAMETRO + 20) {
            posicion.setX(0);
        }
        if (posicion.getX() < -DIAMETRO) {
            posicion.setX(900 - DIAMETRO);
        }
        if (posicion.getY() < -DIAMETRO) {
            posicion.setY(550 - DIAMETRO);
        }
        if (posicion.getX() < 0) {
            posicion.setX(900 - DIAMETRO);
        }
        if (posicion.getY() > 550 - DIAMETRO + 20) {
            posicion.setY(-DIAMETRO);
        }
        if (vy > 8) {
            vy = 8;
            vx = 0;
            aceleracionx = 0;
        }
        if (raton) {
            limvelocidad();
        }

    }

    /**
     * Método para poder establecer el valor de la aceleración cuando no se
     * encuentra en el modo "follow mouse".
     *
     */
    public void setGrav() {
        this.aceleraciony = 0.05;
        this.aceleracionx = 0.001;

    }

 
    /**
     * Método para poder establecer el valor de la aceleración en el modo
     * "follow mouse".
     *
     */
    public void setAceRaton() {

        posicion.resta();
        posicion.unitario();
        posicion.mult(0.1);
        aceleracionx = posicion.getXraton();
        aceleraciony = posicion.getYraton();

    }
    
    /**
     * Método para poder establecer el valor de la velocidad.
     *
     */
    public void setVel() {
        vy += aceleraciony;
        vx += aceleracionx;
    }
    
    /**
     * Método para poder establecer la nueva posición de la bola.
     *
     */
    public void setPos() {
        posicion.newPos(vx, vy);
    }


    /**
     * Método para poder cambiar el valor de la coordenada x del ratón.
     *
     * @param x
     */
    public void setXR(double x) {
        posicion.setXraton(x);
    }

    /**
     * Método para poder cambiar el valor de la coordenada y del ratón.
     *
     * @param y
     */
    public void setYR(double y) {
        posicion.setYraton(y);
    }


    /**
     * Método que establece unos límites de velocidad. Utilizado cuando se sigue
     * el ratón para evitar que la bola tome velocidades muy elevadas.
     *
     */
    public void limvelocidad() {
        if (vy > 5) {
            vy = 5;
        }
        if (vy < -8) {
            vy = -8;
        }
        if (vx < -5) {
            vx = -5;
        }

        if (vx > 5) {
            vx = 5;
        }
    }
}
