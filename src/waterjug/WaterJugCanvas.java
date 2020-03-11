/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterjug;

import framework.Canvas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

/**
 *
 * @author Zach
 */
public class WaterJugCanvas extends Canvas
{
    public WaterJugCanvas(WaterJugState state)
    {
        super(state);
        
        background = new RoundRectangle2D.Double(0, 0, 500, 250, 10, 10);
    
        base3Gal = new Line2D.Double(20, 230, 113, 230);
        wall3Gal1 = new Line2D.Double(20, 230, 20, 20);
        wall3Gal2 = new Line2D.Double(113, 230, 113, 20);
    
        base4Gal = new Line2D.Double(133, 230, 257, 230);
        wall4Gal1 = new Line2D.Double(133, 230, 133, 20);
        wall4Gal2 = new Line2D.Double(257, 230, 257, 20);
    
        setPreferredSize(new Dimension(400, 250));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GRAY);
        g2.fill(background);
        
        g2.setFont(new Font("Serif", Font.BOLD, 50));
        g2.setColor(Color.RED);
        g2.drawString("X", 48, 110);
        g2.drawString("Y", 175, 110);
        
        actorSetup();
        
        g2.setColor(new Color(0, 0, 230, 124));
        g2.fill(contents3Gal);
        g2.fill(top3Gal);
        g2.fill(contents4Gal);
        g2.fill(top4Gal);
        
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(4));
        
        g2.draw(base3Gal);
        g2.draw(wall3Gal1);
        g2.draw(wall3Gal2);
        
        g2.draw(base4Gal);
        g2.draw(wall4Gal1);
        g2.draw(wall4Gal2);
        
        g2.setFont(new Font("Sans Serif", Font.BOLD, 16));
        g2.drawString("X: " + xContents, 277, 98);
        g2.drawString("Y: " + yContents, 277, 168);
        
    }
    
     public static void main(String[] args)
     {
        JFrame frame = new JFrame("WaterJugCanvas Test");
        frame.add(new WaterJugCanvas(new WaterJugState(0, 0)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
     }
    
     private void actorSetup()
     {
         WaterJugState s = (WaterJugState) getCurrState();
         top3Gal = new GeneralPath();
         top4Gal = new GeneralPath();
         switch (s.getXGallons())
         {
             case 0:
                 contents3Gal = new Rectangle2D.Double();
                 xContents = "Empty";
                 break;
             case 1:
                 contents3Gal = new Rectangle2D.Double(20, 170, 93, 60);
                 xContents = "1 Gallon";
                 top3Gal.moveTo(20, 170);
                 top3Gal.quadTo(65, 165, 113, 170);
                 top3Gal.closePath();
                 break;
             case 2:
                 xContents = "2 Gallons";
                 contents3Gal = new Rectangle2D.Double(20, 110, 93, 120);
                 top3Gal.moveTo(20, 110);
                 top3Gal.quadTo(65, 105, 113, 110);
                 top3Gal.closePath();
                 break;
             case 3:
                 xContents = "3 Gallons";
                 contents3Gal = new Rectangle2D.Double(20, 50, 93, 180);
                 top3Gal.moveTo(20, 50);
                 top3Gal.quadTo(65, 45, 113, 50);
                 top3Gal.closePath();
                 break;
         }
         switch (s.getYGallons())
         {
             case 0:
                 yContents = "Empty";
                 contents4Gal = new Rectangle2D.Double();
                 break;
             case 1:
                 yContents = "1 Gallon";
                 contents4Gal = new Rectangle2D.Double(133, 185, 124, 45);
                 top4Gal.moveTo(133, 185);
                 top4Gal.quadTo(195, 180, 257, 185);
                 top4Gal.closePath();
                 break;
             case 2:
                 yContents = "2 Gallons";
                 contents4Gal = new Rectangle2D.Double(133, 140, 124, 95);
                 top4Gal.moveTo(133, 140);
                 top4Gal.quadTo(195, 135, 257, 140);
                 top4Gal.closePath();
                 break;
             case 3:
                 yContents = "3 Gallons";
                 contents4Gal = new Rectangle2D.Double(133, 95, 124, 135);
                 top4Gal.moveTo(133, 95);
                 top4Gal.quadTo(195, 90, 257, 95);
                 top4Gal.closePath();
                 break;
             case 4:
                 yContents = "4 Gallons";
                 contents4Gal = new Rectangle2D.Double(133, 50, 124, 180);
                 top4Gal.moveTo(133, 50);
                 top4Gal.quadTo(195, 45, 257, 50);
                 top4Gal.closePath();
                 break;
         }
     }
     
    RoundRectangle2D background;
    
    Line2D base3Gal;
    Line2D wall3Gal1;
    Line2D wall3Gal2;
    
    Line2D base4Gal;
    Line2D wall4Gal1;
    Line2D wall4Gal2;
    
    Rectangle2D contents3Gal;
    GeneralPath top3Gal;
    
    Rectangle2D contents4Gal;
    GeneralPath top4Gal;
    
    String xContents;
    String yContents;
}