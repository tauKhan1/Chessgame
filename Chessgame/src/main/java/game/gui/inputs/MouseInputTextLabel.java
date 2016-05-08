package game.gui.inputs;

import javax.swing.JLabel;

/**
 * Luokka näyttää syötetyn siirron lähtö- tai kohderuudun ikkunassa.
 */
public class MouseInputTextLabel extends JLabel {

    private char[] convertChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private String text;

    /**
     * Luo tekstikentän.
     *
     * @param text Pohjateksti, joka on aina näkyvillä
     */
    public MouseInputTextLabel(String text) {
        super();
        this.text = text;
    }

    /**
     * Metodi päivittää näytettävän ruudun syötteen mukaiseksi.
     *
     * @param row Ruudun rivi
     * @param col Ruudun sarake
     */
    public void update(int row, int col) {
        String update = "" + convertChars[col - 1] + row;
        super.setText(text + update);
    }
    
    /**
     * Metodi päivittää kentän sisällön alkuperäiseksi.
     */
    public void clear() {
        super.setText(text);
    }
}
