/**
 * PAULA FERRER SALOM
 * https://youtu.be/x5IexioRNc8
 */
package practicafinal;

public class Vector {

    public double x;//Valor de la coordenada x del Vector posición
    public double y;//Valor de la coordenada y del Vector posición
    private double xraton;//Valor de la coordenada x del ratón
    private double yraton;//Valor de la coordenada y del ratón

    /**
     * Vector posición formado por las coordenadas X e Y.
     *
     * @param x
     * @param y
     */
    public Vector(double x, double y) {

        this.x = x;
        this.y = y;

    }

    /**
     * Método para poder cambiar el valor de la coordenada X del Vector.
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Método para poder cambiar el valor de la coordenada Y del Vector.
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
        /**
     * Método para poder cambiar el valor de la coordenada X del ratón.
     *
     * @param xraton
     */
    public void setXraton(double xraton) {
        this.xraton = xraton;
    }

    /**
     * Método para poder cambiar el valor de la coordenada Y del ratón.
     *
     * @param yraton
     */
    public void setYraton(double yraton) {
        this.yraton = yraton;
    }

    /**
     * Devuelve el valor de la coordenada X del Vector.
     *
     * @return x
     */
    public double getX() {
        return x;

    }

    /**
     * Devuelve el valor de la coordenada Y del Vector.
     *
     * @return y
     */
    public double getY() {
        return y;
    }



    /**
     * Devuelve el valor de la coordenada X del ratón.
     *
     * @return xraton
     */
    public double getXraton() {
        return xraton;

    }

    /**
     * Devuelve el valor de la coordenada Y del ratón.
     *
     * @return yraton
     */
    public double getYraton() {
        return yraton;
    }

    /**
     * Método que calcula el módulo de un Vector dado.
     *
     * @param x
     * @param y
     * @return modulo
     */
    public double modulo(double x, double y) {
        double modulo = Math.sqrt((Math.pow(x, 2)) + (Math.pow(y, 2)));
        return modulo;
    }

    /**
     * Método que calcula la nueva posición del Vector. Dado el Vector (x,y)
     * cálcula la nueva posición del Vector sumando la velocidad a cada
     * coordenada.
     *
     * @param velocidadx
     * @param velocidady
     */
    public void newPos(double velocidadx, double velocidady) {
        x += velocidadx;
        y += velocidady;
    }

    /**
     * Método que resta la posición del ratón a la del Vector utilizado para
     * calcular la aceleración en el caso "follow mouse".
     *
     */
    public void resta() {
        xraton = xraton - x;
        yraton = yraton - y;
    }

    /**
     * Método que calcula el Vector unitario utilizado para calcular la
     * aceleración en el caso "follow mouse". Dado un Vector calcula su módulo y
     * divide cada coordenada por él.
     *
     */
    public void unitario() {
        xraton = xraton / modulo(xraton, yraton);
        yraton = yraton / modulo(xraton, yraton);

    }

    /**
     * Método que multiplica el Vector (xraton, yraton) por un escalar,
     * utilizado para calcular la aceleración en el caso "follow mouse".
     *
     * @param escalar
     */
    public void mult(double escalar) {
        xraton *= escalar;
        yraton *= escalar;

    }

}
