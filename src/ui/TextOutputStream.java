package ui;

import javax.swing.*;
import java.io.OutputStream;

public class TextOutputStream extends OutputStream {
    private JTextArea consoleRedirect;

    public TextOutputStream(JTextArea consoleRedirect) {
        this.consoleRedirect = consoleRedirect;
    }

    @Override
    public void write(int b) {
        consoleRedirect.append(String.valueOf((char) b));
        consoleRedirect.setCaretPosition(consoleRedirect.getDocument().getLength());
    }
}
