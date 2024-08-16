package remi;

import java.awt.*;
import java.awt.event.*;

public class App
    extends Frame implements MouseListener, MouseMotionListener, KeyListener
{
    String msg = "";
    String keyMsg = "";
    int mouseX = 0, mouseY = 0;
    
    public App()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        addWindowListener(new MyWindowAdapter());
    }
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        msg = msg + " -- click received";
        repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent me)
    {
        mouseX = 100;
        mouseY = 100;
        msg = "Mouse entered";
        repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent me)
    {
        mouseX = 100;
        mouseY = 100;
        msg = "Mouse exited";
        repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent me)
    {
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Button down";
        repaint();
    }
    
    @Override
    public void mouseReleased(MouseEvent me)
    {
        mouseX = me.getX() ;
        mouseY = me.getY() ;
        msg = "Button Released" ;
        repaint() ;
    }
    
    @Override
    public void mouseDragged(MouseEvent me)
    {
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "*" +" mouse at " + mouseX + ", " + mouseY;
        repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent me)
    {
        msg = "Moving mouse at " + me.getX() + ", " + me.getY();
        repaint();
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawString(msg, mouseX, mouseY);
        g.drawString(keyMsg, 10, 200);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        msg = "key " + e.getKeyChar() + " pressed";
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        msg = "key " + e.getKeyChar() + " released";
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        keyMsg += e.getKeyChar();
        repaint();
    }
    
    public static void main(String[] args)
    {
        App appwin = new App() ;
        appwin.setSize(new Dimension(300, 300)) ;
        appwin.setTitle("MouseEventsDemo") ;
        appwin.setVisible(true) ;
    }
    
    class MyWindowAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent we)
        {
            System.exit(0) ;
        }
    }
}